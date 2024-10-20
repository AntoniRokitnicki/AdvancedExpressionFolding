package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) {
        val ignored1 = list.get(0);

        val first, second, third, fourth) = list;

        val ignored21 = data.list.get(4);
        val ignored22 = data.list.get(5);

        var getter1, getter2, getter3) = data.list;
        getter1 = data;

        val deepGetter1, deepGetter2) = data.data.list;
        deepGetter2 = data;

        val wrongParent1 = data.list.get(0);
        val wrongParent2 = data.data.list.get(1);

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
