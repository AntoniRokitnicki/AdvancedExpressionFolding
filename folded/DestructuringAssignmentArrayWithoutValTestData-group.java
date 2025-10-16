package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayWithoutValTestData {
    public void enter(Data data, Data[] array) {
        Data ignored1 = array[0];

        Data[0:" "]first[0:" = array[0];"][0:"\n\n        Data "]second[0:" = array[1];"][0:"\n        Data "]third[0:" = array[2];"][0:"\n        Data "]fourth[0:" "]= array[0:"[3]"];

        Data ignored21 = data.[1:"getArray()"][4];
        Data ignored22 = data.[2:"getArray()"][5];

        Data[2:" "]getter1[2:" = data.getArray()[0];"][2:"\n        Data "]getter2[2:" "]= data.[3:"getArray()"][2:"[1]"];
        getter1 = data;

        Data[3:" "]deepGetter1[3:" = data.getData().getArray()[0];"][3:"\n        Data "]deepGetter2[3:" "]= data.[5:"getData()"].[4:"getArray()"][3:"[1]"];
        deepGetter2 = data;
        
        Data wrongParent1 = data.[6:"getArray()"][0];
        Data wrongParent2 = data.[8:"getData()"].[7:"getArray()"][1];

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
