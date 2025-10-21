#!/usr/bin/env zsh

# This script fixes bad rename detection after Java→Kotlin conversions.
# It finds all .java files deleted in the last commit, hard-resets one commit back,
# renames those same files to .kt (preserving paths), and commits the rename-only changes.
# Optionally, when run with --remove, it deletes those renamed .kt files and commits the removals.
# The goal is to split a mixed “delete + add” conversion commit into a clean Git history:
# one commit for file renames and another for Kotlin content changes.
set -eu

# usage: ./split-java-to-kotlin-rename.zsh [--remove]
DO_REMOVE=true
[[ "${1:-}" == "--remove" ]] && DO_REMOVE=true

# 1) Remember last commit hash
OLD_COMMIT=$(git rev-parse HEAD)

# 2) Collect deleted .java files from last commit
DELETED_JAVA=("${(@f)$(git show --name-only --diff-filter=D ${OLD_COMMIT} | grep '\.java$' || true)}")

if [[ ${#DELETED_JAVA[@]} -eq 0 ]]; then
  echo "No deleted *.java files found in ${OLD_COMMIT}"
  exit 0
fi

echo "Found ${#DELETED_JAVA[@]} deleted *.java files in ${OLD_COMMIT}"

# 3) Revert the last commit
git reset --hard "${OLD_COMMIT}^"

# 4) Perform rename-only: *.java -> *.kt
for f in "${DELETED_JAVA[@]}"; do
  if [[ -f "$f" ]]; then
    dest="${f%.java}.kt"
    mkdir -p "$(dirname "$dest")"
    git mv -f "$f" "$dest"
    echo "Renamed: $f → $dest"
  else
    echo "Skip: $f not found after reset"
  fi
done

git commit -m "rename-only: *.java → *.kt (split from ${OLD_COMMIT})"

# 5) Optional: remove renamed .kt files
if $DO_REMOVE; then
  for f in "${DELETED_JAVA[@]}"; do
    dest="${f%.java}.kt"
    if [[ -f "$dest" ]]; then
      git rm -f "$dest"
      echo "Removed: $dest"
    else
      echo "Skip remove: $dest missing"
    fi
  done
  git commit -m "remove renamed .kt files matching deleted .java list (from ${OLD_COMMIT})"
fi

echo "Done."
