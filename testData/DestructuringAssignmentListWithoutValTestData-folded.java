package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListWithoutValTestData {
    public void enter(Data data, List<Data> list) {
        Data ignored1 = list.getFirst();

        Data (first, second, third, fourth) = list;

        Data ignored21 = data.list.get(4);
        Data ignored22 = data.list.get(5);

        Data (getter1, getter2, getter3) = data.list;
        getter1 = data;

        Data (deepGetter1, deepGetter2) = data.data.list;
        deepGetter2 = data;

        Data wrongParent1 = data.list.getFirst();
        Data wrongParent2 = data.data.list.get(1);

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
