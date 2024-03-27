package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListWithoutValTestData {
    public void enter(Data data, List<Data> list) {
        Data ignored1 = list.get(0);

        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);

        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);

        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
        getter1 = data;

        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
        deepGetter2 = data;

        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);

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
