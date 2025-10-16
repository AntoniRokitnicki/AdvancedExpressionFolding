package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) {
        [0:"Data"] ignored1 = list.get(0);

        [23:"Data"] first[1:" = list.get(0);"][1:"\n        Data "]second[1:" = list.get(1);"][1:"\n        Data "]third[1:" = list.get(2);"][1:"\n        Data "]fourth[1:" "]= list[1:".get(3)"];

        [5:"Data"] ignored21 = data.[6:"getList()"].get(4);
        [7:"Data"] ignored22 = data.[8:"getList()"].get(5);

        [32:"Data"] getter1[8:" = data.getList().get(0);"][8:"\n        Data "]getter2[8:" = data.getList().get(1);"][8:"\n        Data "]getter3[8:" "]= data.[12:"getList()"][8:".get(2)"];
        getter1 = data;

        [39:"Data"] deepGetter1[12:" = data.getData().getList().get(0);"][12:"\n        Data "]deepGetter2[12:" "]= data.[15:"getData()"].[14:"getList()"][12:".get(1)"];
        deepGetter2 = data;

        [16:"Data"] wrongParent1 = data.[17:"getList()"].get(0);
        [18:"Data"] wrongParent2 = data.[20:"getData()"].[19:"getList()"].get(1);

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
