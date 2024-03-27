package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentTestData {
    public void enter(Data data, Data[] array) {
        val ignored1 = array[0];

        val (first, second, third, fourth) = array;

        val ignored21 = data.array[4];
        val ignored22 = data.array[5];

        var (getter1, getter2, getter3) = data.array;
        getter1 = data;

        var (deepGetter1, deepGetter2) = data.data.array;
        deepGetter2 = data;

        val wrongParent1 = data.array[0];
        val wrongParent2 = data.data.array[1];

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
