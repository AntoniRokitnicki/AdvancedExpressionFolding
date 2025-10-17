package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayWithoutValTestData {
    public void enter(Data data, Data[] array) {
        Data ignored1 = array[0];

        Data[0:" "][9:" "]first[0:" = array[0];"][9:" = array[0];"][0:"\n\n        Data "][9:"\n\n        Data "]second[0:" = array[1];"][9:" = array[1];"][0:"\n        Data "][9:"\n        Data "]third[0:" = array[2];"][9:" = array[2];"][0:"\n        Data "][9:"\n        Data "]fourth[0:" "][9:" "]= array[0:"[3]"][9:"[3]"];

        Data ignored21 = data.[1:"getArray()"][10:"getArray()"][4];
        Data ignored22 = data.[2:"getArray()"][11:"getArray()"][5];

        Data[2:" "][12:" "]getter1[2:" = data.getArray()[0];"][12:" = data.getArray()[0];"])"][0];[2:"\n        Data "][12:"\n        Data "]getter2[2:" "][12:" "]= data.[3:"getArray()"][14:"getArray()"][2:"[1]"][12:"[1]"];
        getter1 = data;

        Data[3:" "][15:" "]deepGetter1[3:" = data.getData().getArray()[0];"][15:" = data.getData().getArray()[0];"]tArray()"][0];[3:"\n        Data "][15:"\n        Data "]deepGetter2[3:" "][15:" "]= data.[5:"getData()"][19:"getData()"].[4:"getArray()"][18:"getArray()"][3:"[1]"][15:"[1]"];
        deepGetter2 = data;
        
        Data wrongParent1 = data.[6:"getArray()"][20:"getArray()"][0];
        Data wrongParent2 = data.[8:"getData()"][22:"getData()"].[7:"getArray()"][21:"getArray()"][1];

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
