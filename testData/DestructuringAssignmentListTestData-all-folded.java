package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) {
        val ignored1 = list.get(0);

        val (first, second, third, fourth) = list[3];

        val ignored21 = data.list[4];
        val ignored22 = data.list[5];

        var (getter1, getter2, getter3) = data.list[2];
        getter1 = data;

        var (deepGetter1, deepGetter2) = data.data.list[1];
        deepGetter2 = data;

        val wrongParent1 = data.list.get(0);
        val wrongParent2 = data.data.list[1];

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
