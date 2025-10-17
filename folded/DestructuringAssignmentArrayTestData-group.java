package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayTestData {
    public void enter(Data data, Data[] array) {
        [0:"Data"][21:"Data"] ignored1 = array[0];

        [23:"Data"]Data "][22:"Data "]first[1:" = array[0];"][22:" = array[0];"][1:"\n\n        Data "][22:"\n\n        Data "]ata"][24:"Data"] second[1:" = array[1];"][22:" = array[1];"][1:"\n        Data "][22:"\n        Data "]ata"][25:"Data"] third[1:" = array[2];"][22:" = array[2];"][1:"\n        Data "][22:"\n        Data "]ata"][26:"Data"] fourth[1:" "][22:" "]= array[1:"[3]"][22:"[3]"];

        [5:"Data"][27:"Data"] ignored21 = data.[6:"getArray()"][28:"getArray()"][4];
        [7:"Data"][29:"Data"] ignored22 = data.[8:"getArray()"][30:"getArray()"][5];

        [32:"Data"]Data "][31:"Data "]getter1[8:" = data.getArray()[0];"][31:" = data.getArray()[0];"])"][0];[8:"\n        Data "][31:"\n        Data "]ata"][34:"Data"] getter2[8:" = data.getArray()[1];"][31:" = data.getArray()[1];"])"][35:"getArray()"][1];[8:"\n        Data "][31:"\n        Data "]Data"][36:"Data"] getter3[8:" "][31:" "]= data.[12:"getArray()"][37:"getArray()"][8:"[2]"][31:"[2]"];
        getter1 = data;

        [39:"Data"]"Data "][38:"Data "]deepGetter1[12:" = data.getData().getArray()[0];"][38:" = data.getData().getArray()[0];"]tArray()"][0];[12:"\n        Data "][38:"\n        Data "]Data"][42:"Data"] deepGetter2[12:" "][38:" "]= data.[15:"getData()"][44:"getData()"].[14:"getArray()"][43:"getArray()"][12:"[1]"][38:"[1]"];
        deepGetter2 = data;
        
        [16:"Data"][45:"Data"] wrongParent1 = data.[17:"getArray()"][46:"getArray()"][0];
        [18:"Data"][47:"Data"] wrongParent2 = data.[20:"getData()"][49:"getData()"].[19:"getArray()"][48:"getArray()"][1];

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }

    static class Data {
        public Data getData() {
            return null;
        }

        public Data[] getArray() {
            return null;
        }

        public List<Data> getList() {
            return null;
        }
    }

    void blackhole(Data... datas) {}

}
