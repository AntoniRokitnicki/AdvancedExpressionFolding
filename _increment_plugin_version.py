import xml.etree.ElementTree as ET

tree = ET.parse('plugin_repository.xml')
root = tree.getroot()
last_idea_plugin = root.find('.//idea-plugin[1]')

new_idea_plugin = ET.Element('idea-plugin')
new_idea_plugin.set('downloads', str(int(last_idea_plugin.get('downloads')) + 1))
new_idea_plugin.set('size', str(int(last_idea_plugin.get('size')) + 1))
new_idea_plugin.set('date', str(int(last_idea_plugin.get('date')) + 1))
new_idea_plugin.set('updatedDate', str(int(last_idea_plugin.get('updatedDate')) + 1))
for elem in last_idea_plugin:
    if elem.tag == 'description':
        new_elem = ET.Element(elem.tag)
        new_elem.text = elem.text
        new_elem.text = '\n' + elem.text if elem.text else elem.text
        new_elem.tail = elem.tail
        new_idea_plugin.append(new_elem)
    else:
        new_idea_plugin.append(ET.Element(elem.tag, attrib=elem.attrib))
        new_idea_plugin[-1].text = elem.text
        new_idea_plugin[-1].tail = elem.tail

version = new_idea_plugin.find('version').text
version_parts = list(map(int, version.split('.')))
version_parts[2] += 1
new_version = '.'.join(map(str, version_parts))

new_idea_plugin.find('version').text = new_version
new_idea_plugin.find('download-url').text = new_idea_plugin.find('download-url').text.replace(version, new_version)


category = root.find('.//category[@name="Formatting"]')
category.insert(0, new_idea_plugin)

tree.write('plugin_repository.xml')


file_path = 'gradle.properties'

with open(file_path, 'r') as file:
    lines = file.readlines()

for i in range(len(lines)):
    if 'pluginVersion' in lines[i]:
        lines[i] = f'pluginVersion = {new_version}\n'

with open(file_path, 'w') as file:
    file.writelines(lines)

print(new_version)
