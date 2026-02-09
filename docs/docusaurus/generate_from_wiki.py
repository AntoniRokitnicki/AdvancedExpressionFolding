#!/usr/bin/env python3
from __future__ import annotations

import argparse
import html
import json
import re
from dataclasses import dataclass, field
from pathlib import Path
from typing import Iterable, List

ROOT = Path(__file__).resolve().parents[2]
WIKI_FEATURES = ROOT / "wiki-clone" / "docs" / "features"
MANUAL_FEATURES_DIR = ROOT / "docs" / "docusaurus" / "features"


def camel_to_kebab(value: str) -> str:
    value = re.sub("([a-z0-9])([A-Z])", r"\1-\2", value)
    value = value.replace("_", "-")
    return value.lower()


def humanize_state(value: str) -> str:
    words = [word for word in camel_to_kebab(value).split("-") if word]
    if not words:
        return value
    return " ".join(word.capitalize() for word in words)


@dataclass
class FeatureDoc:
    title: str
    state_key: str
    slug: str
    sidebar_label: str
    description: str
    body_lines: List[str]
    default_value: str | None = None
    controlled_by: List[str] = field(default_factory=list)
    related_features: List[str] = field(default_factory=list)


FRONTMATTER_TEMPLATE = """---\ntitle: {title}\nslug: /features/{slug}\nsidebar_label: {sidebar_label}\ndescription: {description}\n---\n\n"""


INFO_BLOCK_TEMPLATE = """:::info Toggle summary\n- **State key:** `{state_key}`\n{default_line}{controlled_line}{related_line}:::\n\n"""


def parse_manual_slugs() -> set[str]:
    slugs: set[str] = set()
    for path in MANUAL_FEATURES_DIR.glob("*.md"):
        text = path.read_text(encoding="utf-8")
        match = re.search(r"^slug:\s*/features/([\w\-/]+)", text, re.MULTILINE)
        if match:
            slugs.add(match.group(1))
    return slugs


def extract_feature(path: Path) -> FeatureDoc | None:
    raw_lines = path.read_text(encoding="utf-8").splitlines()
    title_index = next(
        (idx for idx, line in enumerate(raw_lines) if re.match(r"^#+\s+", line)),
        None,
    )
    if title_index is None:
        return None

    title_line = raw_lines[title_index]
    title_match = re.match(
        r"^#+\s+(?P<title>.+?)\s*\(State field:\s*(?P<state>[^)]+)\)",
        title_line,
    )
    if title_match:
        title = title_match.group("title").strip()
        state = title_match.group("state").strip()
    else:
        state = path.stem
        candidate_title = title_line.lstrip("# ").strip()
        if not candidate_title or candidate_title.lower().startswith("example"):
            title = humanize_state(state)
        else:
            title = candidate_title

    if title and title.islower():
        title = title[:1].upper() + title[1:]

    slug = camel_to_kebab(state)
    sidebar_label = title

    # gather lines after removing the heading and metadata entries
    body_lines: List[str] = []
    default_value: str | None = None
    controlled_by: List[str] = []
    related_features: List[str] = []
    skip_indices: set[int] = {title_index}
    for idx, line in enumerate(raw_lines):
        if line.startswith("Default:"):
            default_value = line.split(":", 1)[1].strip()
            skip_indices.add(idx)
        elif line.startswith("Controlled by:"):
            value = line.split(":", 1)[1].strip()
            extracted = [item.strip(" `") for item in value.split(",") if item.strip(" `")]
            controlled_by.extend(extracted)
            skip_indices.add(idx)
        elif line.startswith("Related features:"):
            value = line.split(":", 1)[1].strip()
            if value and value.lower() != "(none)":
                related_features.extend([item.strip() for item in value.split(",") if item.strip()])
            skip_indices.add(idx)

    for idx, line in enumerate(raw_lines):
        if idx in skip_indices:
            continue
        heading_match = re.match(r"^(#+)(\s+.*)", line)
        if heading_match:
            hashes = len(heading_match.group(1))
            if hashes >= 3:
                line = "#" * (hashes - 1) + heading_match.group(2)
        body_lines.append(line)

    description = derive_description(raw_lines) or f"Overview of {humanize_state(state).lower()}."

    info_block = INFO_BLOCK_TEMPLATE.format(
        state_key=state,
        default_line=f"- **Default:** {default_value}\n" if default_value else "",
        controlled_line=(
            "- **Controlled by:** "
            + ", ".join(f"`{item}`" for item in controlled_by)
            + "\n"
            if controlled_by
            else ""
        ),
        related_line=(
            "- **Related features:** "
            + ", ".join(related_features)
            + "\n"
            if related_features
            else ""
        ),
    )

    # ensure there is an empty line after the info block unless body starts with blank already
    if body_lines and body_lines[0].strip():
        body_lines.insert(0, "")
    body_lines.insert(0, info_block.rstrip("\n"))

    return FeatureDoc(
        title=title,
        state_key=state,
        slug=slug,
        sidebar_label=title,
        description=description,
        body_lines=body_lines,
        default_value=default_value,
        controlled_by=controlled_by,
        related_features=related_features,
    )


def derive_description(lines: Iterable[str]) -> str | None:
    lines = list(lines)
    for idx, line in enumerate(lines):
        heading_match = re.match(r"###\s+(.+)", line)
        if heading_match:
            # look ahead for first non-empty, non-heading line
            for next_line in lines[idx + 1 :]:
                stripped = next_line.strip()
                if not stripped:
                    continue
                if stripped.startswith("#"):
                    break
                return stripped
    return None


def write_feature(doc: FeatureDoc, output_dir: Path) -> None:
    output_dir.mkdir(parents=True, exist_ok=True)
    output_path = output_dir / f"{doc.slug}.md"
    frontmatter = FRONTMATTER_TEMPLATE.format(
        title=doc.title,
        slug=doc.slug,
        sidebar_label=doc.sidebar_label,
        description=doc.description,
    )
    content = frontmatter + "\n".join(doc.body_lines).rstrip() + "\n"
    if output_path.exists() and output_path.read_text(encoding="utf-8") == content:
        return
    output_path.write_text(content, encoding="utf-8")


def render_summary_row(label: str, value: str) -> str:
    return f"      <dt>{html.escape(label)}</dt>\n      <dd>{value}</dd>\n"


def render_feature_html(doc: FeatureDoc) -> str:
    summary_rows = [
        render_summary_row("State key", f"<code>{html.escape(doc.state_key)}</code>"),
    ]
    if doc.default_value:
        summary_rows.append(render_summary_row("Default", html.escape(doc.default_value)))
    if doc.controlled_by:
        controls = ", ".join(f"<code>{html.escape(item)}</code>" for item in doc.controlled_by)
        summary_rows.append(render_summary_row("Controlled by", controls))
    if doc.related_features:
        related = ", ".join(html.escape(item) for item in doc.related_features)
        summary_rows.append(render_summary_row("Related features", related))

    body_markdown = "\n".join(doc.body_lines).strip()
    body_html = f"<pre>{html.escape(body_markdown)}</pre>" if body_markdown else ""

    summary_html = "".join(summary_rows)

    return (
        "<!DOCTYPE html>\n"
        "<html lang=\"en\">\n"
        "  <head>\n"
        "    <meta charset=\"utf-8\" />\n"
        f"    <title>{html.escape(doc.title)}</title>\n"
        f"    <meta name=\"description\" content=\"{html.escape(doc.description)}\" />\n"
        "  </head>\n"
        "  <body>\n"
        f"    <h1>{html.escape(doc.title)}</h1>\n"
        "    <section aria-labelledby=\"feature-summary\">\n"
        "      <h2 id=\"feature-summary\">Summary</h2>\n"
        "      <dl>\n"
        f"{summary_html}"
        "      </dl>\n"
        "    </section>\n"
        "    <section aria-labelledby=\"feature-body\">\n"
        "      <h2 id=\"feature-body\">Details</h2>\n"
        f"      {body_html}\n"
        "    </section>\n"
        "  </body>\n"
        "</html>\n"
    )


def write_feature_html(doc: FeatureDoc, output_dir: Path) -> None:
    output_dir.mkdir(parents=True, exist_ok=True)
    output_path = output_dir / f"{doc.slug}.html"
    content = render_feature_html(doc)
    if output_path.exists() and output_path.read_text(encoding="utf-8") == content:
        return
    output_path.write_text(content, encoding="utf-8")


def ensure_category(
    output_dir: Path,
    *,
    label: str,
    title: str,
    description: str,
    position: int,
) -> None:
    output_dir.mkdir(parents=True, exist_ok=True)
    category_path = output_dir / "_category_.json"
    if category_path.exists():
        return
    payload = {
        "label": label,
        "position": position,
        "link": {
            "type": "generated-index",
            "title": title,
            "description": description,
        },
    }
    category_path.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")


def resolve_path(value: str | Path) -> Path:
    candidate = Path(value)
    if not candidate.is_absolute():
        return ROOT / candidate
    return candidate


def main() -> None:
    parser = argparse.ArgumentParser(
        description="Generate Docusaurus documentation pages from the wiki feature markdown files.",
    )
    parser.add_argument(
        "--output-dir",
        default="docs/docusaurus/features/generated",
        type=str,
        help="Directory where generated markdown files will be written. Relative paths are resolved from the repository root.",
    )
    parser.add_argument(
        "--no-category",
        action="store_true",
        help="Skip creating a default _category_.json file in the output directory.",
    )
    parser.add_argument(
        "--category-label",
        default="Toggle Reference (auto-generated)",
        help="Label to use for the generated category, when --no-category is not set.",
    )
    parser.add_argument(
        "--category-title",
        default="Toggle Reference",
        help="Title for the generated category index, when --no-category is not set.",
    )
    parser.add_argument(
        "--category-description",
        default="Auto-generated reference pages converted from the historical GitHub wiki.",
        help="Description for the generated category index, when --no-category is not set.",
    )
    parser.add_argument(
        "--category-position",
        type=int,
        default=99,
        help="Sidebar position for the generated category, when --no-category is not set.",
    )
    parser.add_argument(
        "--html-output-dir",
        type=str,
        default=None,
        help="Optional directory where raw HTML versions of the generated pages will be written.",
    )
    args = parser.parse_args()

    output_dir = resolve_path(args.output_dir)
    html_output_dir = resolve_path(args.html_output_dir) if args.html_output_dir else None

    if not args.no_category:
        ensure_category(
            output_dir,
            label=args.category_label,
            title=args.category_title,
            description=args.category_description,
            position=args.category_position,
        )

    manual_slugs = parse_manual_slugs()
    for path in sorted(WIKI_FEATURES.glob("*.md")):
        doc = extract_feature(path)
        if not doc:
            continue
        if doc.slug in manual_slugs:
            # Skip generation when a curated guide already exists for this slug.
            continue
        write_feature(doc, output_dir)
        if html_output_dir:
            write_feature_html(doc, html_output_dir)


if __name__ == "__main__":
    main()
