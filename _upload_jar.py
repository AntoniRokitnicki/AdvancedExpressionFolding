import requests
import os
import requests
from pathlib import Path

def get_latest_jar(directory, file_prefix):
    path = Path(directory)
    jar_files = list(path.glob(f"{file_prefix}*"))
    if not jar_files:
        raise FileNotFoundError(f"No files found in the directory '{directory}' with the prefix '{file_prefix}'.")
    return max(jar_files, key=os.path.getmtime)

def upload_jar_to_github_release(token, owner, repo, version, jar_path):
    release_url = f"https://api.github.com/repos/{owner}/{repo}/releases"
    release_data = {
        "tag_name": version,
        "name": version,
        "body": version
    }

    response = requests.post(release_url, json=release_data, headers={"Authorization": f"Bearer {token}"})
    release_id = response.json()["id"]

    upload_url = f"https://uploads.github.com/repos/{owner}/{repo}/releases/{release_id}/assets?name={repo}-{version}.jar"
    file_content = open(jar_path, "rb").read()

    upload_headers = {
        "Authorization": f"Bearer {token}",
        "Accept": "application/vnd.github+json",
        "Content-Type": "application/octet-stream"
    }

    response = requests.post(upload_url, data=file_content, headers=upload_headers)
    print(f"response: {response}")


github_token = os.environ.get("GITHUB_TOKEN")

import xml.etree.ElementTree as ET
tree = ET.parse('plugin_repository.xml')
root = tree.getroot()
last_idea_plugin = root.find('.//idea-plugin[1]')
version = f"v{last_idea_plugin.find('version').text}"

github_owner = "AntoniRokitnicki"
github_repo = "AdvancedExpressionFolding"

dir = os.environ.get("AEF_BUILD_DIR")
file_prefix = "advanced-expression-folding"
jar_file_path = get_latest_jar(dir, file_prefix)
print(f"jar_file_path: {jar_file_path}")

upload_jar_to_github_release(github_token, github_owner, github_repo, version, jar_file_path)
