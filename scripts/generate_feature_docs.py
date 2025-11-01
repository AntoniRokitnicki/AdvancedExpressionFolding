import os
import re
from pathlib import Path
from textwrap import dedent

ROOT = Path(__file__).resolve().parents[1]
WIKI_DIR = ROOT / "wiki-clone" / "docs" / "features"
OUTPUT_DIR = ROOT / "docs" / "features-implementation"
SRC_DIR = ROOT / "src" / "com" / "intellij" / "advancedExpressionFolding"
TEST_DIR = ROOT / "test"
EXAMPLES_DIR = ROOT / "examples"
FOLDED_DIR = ROOT / "folded"
SETTINGS_DIR = SRC_DIR / "settings"
CHECKBOX_PROVIDER = SETTINGS_DIR / "view" / "CheckboxesProvider.kt"

OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

feature_meta = {}

state_field_pattern = re.compile(r"State field:\s*([^)]+)")
header_pattern = re.compile(r"^#+\s*(.+)$")

for doc_path in sorted(WIKI_DIR.glob("*.md")):
    content = doc_path.read_text(encoding="utf-8")
    lines = [line for line in content.splitlines() if line.strip()]
    if not lines:
        continue
    title_line = lines[0]
    header_match = header_pattern.match(title_line)
    if header_match:
        title = header_match.group(1).strip()
    else:
        title = doc_path.stem
    state_match = state_field_pattern.search(content)
    if state_match:
        toggle = state_match.group(1).strip()
    else:
        toggle = doc_path.stem
    feature_meta[toggle] = {
        "title": title,
        "doc_path": doc_path,
        "content": content,
    }

# gather defaults from settings
settings_defaults = {}
default_pattern = re.compile(r"override\s+(?:var|val)\s+(\w+)\s*[:=][^=]*=\s*([^,\n)]+)")
for path in SETTINGS_DIR.rglob("*.kt"):
    text = path.read_text(encoding="utf-8")
    for match in default_pattern.finditer(text):
        name = match.group(1)
        value = match.group(2).strip()
        settings_defaults.setdefault(name, []).append({
            "path": path.relative_to(ROOT).as_posix(),
            "line": text[:match.start()].count("\n") + 1,
            "value": value,
        })

# prepare checkbox mapping
checkbox_map = {}
if CHECKBOX_PROVIDER.exists():
    text = CHECKBOX_PROVIDER.read_text(encoding="utf-8")
    for idx, line in enumerate(text.splitlines(), start=1):
        for toggle in feature_meta:
            if toggle in line:
                checkbox_map.setdefault(toggle, []).append({
                    "path": CHECKBOX_PROVIDER.relative_to(ROOT).as_posix(),
                    "line": idx,
                    "line_text": line.strip(),
                })

# compile toggle pattern for scanning source/test/example files
toggles = list(feature_meta.keys())
# order by length descending to avoid substrings triggering early (not critical but helps)
toggles.sort(key=len, reverse=True)

def scan_directory(base_dir: Path, extensions):
    mapping = {toggle: [] for toggle in feature_meta}
    for path in base_dir.rglob("*"):
        if path.is_file() and path.suffix in extensions:
            try:
                text = path.read_text(encoding="utf-8")
            except UnicodeDecodeError:
                continue
            lines = text.splitlines()
            for idx, line in enumerate(lines, start=1):
                for toggle in toggles:
                    if toggle in line:
                        mapping[toggle].append({
                            "path": path.relative_to(ROOT).as_posix(),
                            "line": idx,
                            "line_text": line.strip(),
                        })
            
    return mapping

src_mapping = scan_directory(SRC_DIR, {".kt"})
test_mapping = scan_directory(TEST_DIR, {".kt"})
examples_mapping = scan_directory(EXAMPLES_DIR, {".java", ".kt"})
folded_mapping = scan_directory(FOLDED_DIR, {".java"})

for toggle, meta in feature_meta.items():
    title = meta["title"]
    content = meta["content"]
    doc_path = meta["doc_path"]

    impl_entries = [entry for entry in src_mapping.get(toggle, []) if "settings" not in entry["path"]]
    impl_entries.sort(key=lambda e: e["path"])
    impl_entries = impl_entries[:8]

    default_entries = settings_defaults.get(toggle, [])
    tests = test_mapping.get(toggle, [])[:8]
    example_entries = examples_mapping.get(toggle, [])[:6]
    folded_entries = folded_mapping.get(toggle, [])[:6]
    checkboxes = checkbox_map.get(toggle, [])[:4]

    frontmatter = dedent(f"""---
title: {title}
option: {toggle}
source: {doc_path.relative_to(ROOT).as_posix()}
---
""")

    header = f"# {title}\n\n"

    impl_section_lines = ["## Implementation Summary", ""]
    if impl_entries:
        impl_section_lines.append("This toggle gates the processors listed below; each entry shows the guard in the production code.")
        impl_section_lines.append("")
        for entry in impl_entries:
            impl_section_lines.append(f"- `{entry['path']}:{entry['line']}` – `{entry['line_text']}`")
        impl_section_lines.append("")
    else:
        impl_section_lines.append("No production usages were located in the scanned packages.")
        impl_section_lines.append("")

    default_lines = ["## Default Configuration", ""]
    if default_entries:
        for entry in default_entries:
            default_lines.append(f"- Defined in `{entry['path']}:{entry['line']}` with default `{entry['value']}`.")
        default_lines.append("")
    else:
        default_lines.append("- No explicit default found in the settings sources.")
        default_lines.append("")

    if checkboxes:
        default_lines.append("### Settings UI Registration")
        default_lines.append("")
        for entry in checkboxes:
            default_lines.append(f"- `{entry['path']}:{entry['line']}` – `{entry['line_text']}`")
        default_lines.append("")

    tests_lines = ["## Tests", ""]
    if tests:
        tests_lines.append("Automated folding tests cover this toggle at the locations below.")
        tests_lines.append("")
        for entry in tests:
            tests_lines.append(f"- `{entry['path']}:{entry['line']}` – `{entry['line_text']}`")
        tests_lines.append("")
    else:
        tests_lines.append("- No direct test references found.")
        tests_lines.append("")

    examples_lines = ["## Example Inputs", ""]
    if example_entries or folded_entries:
        for entry in example_entries:
            examples_lines.append(f"- `{entry['path']}:{entry['line']}` – `{entry['line_text']}`")
        for entry in folded_entries:
            examples_lines.append(f"- `{entry['path']}:{entry['line']}` – `{entry['line_text']}`")
        examples_lines.append("")
    else:
        examples_lines.append("- No sample inputs were matched automatically.")
        examples_lines.append("")

    legacy_lines = ["## Legacy Reference", ""]
    legacy_lines.append("Content imported from the historical wiki entry for completeness.")
    legacy_lines.append("")
    legacy_lines.append(content.strip())
    legacy_lines.append("")

    assembled = frontmatter + header + "\n".join(impl_section_lines + default_lines + tests_lines + examples_lines + legacy_lines)

    out_path = OUTPUT_DIR / doc_path.name
    out_path.write_text(assembled, encoding="utf-8")
