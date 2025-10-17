package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListWithoutValTestData {
    public void enter(Data data, List<Data> list) {
        Data ignored1 = list.get(0);

        Data[0:" "][10:" "]first[0:" = list.get(0);"][10:" = list.get(0);"][0:"\n        Data "][10:"\n        Data "]second[0:" = list.get(1);"][10:" = list.get(1);"][0:"\n        Data "][10:"\n        Data "]third[0:" = list.get(2);"][10:" = list.get(2);"][0:"\n        Data "][10:"\n        Data "]fourth[0:" "][10:" "]= list[0:".get(3)"][10:".get(3)"];

        Data ignored21 = data.[1:"getList()"][11:"getList()"].get(4);
        Data ignored22 = data.[2:"getList()"][12:"getList()"].get(5);

        Data[2:" "][13:" "]getter1[2:" = data.getList().get(0);"][13:" = data.getList().get(0);"]get(0);[2:"\n        Data "][13:"\n        Data "]getter2[2:" = data.getList().get(1);"][13:" = data.getList().get(1);"]5:"getList()"].get(1);[2:"\n        Data "][13:"\n        Data "]getter3[2:" "][13:" "]= data.[4:"getList()"][16:"getList()"][2:".get(2)"][13:".get(2)"];
        getter1 = data;

        Data[4:" "][17:" "]deepGetter1[4:" = data.getData().getList().get(0);"][17:" = data.getData().getList().get(0);"]st()"].get(0);[4:"\n        Data "][17:"\n        Data "]deepGetter2[4:" "][17:" "]= data.[6:"getData()"][21:"getData()"].[5:"getList()"][20:"getList()"][4:".get(1)"][17:".get(1)"];
        deepGetter2 = data;

        Data wrongParent1 = data.[7:"getList()"][22:"getList()"].get(0);
        Data wrongParent2 = data.[9:"getData()"][24:"getData()"].[8:"getList()"][23:"getList()"].get(1);

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
