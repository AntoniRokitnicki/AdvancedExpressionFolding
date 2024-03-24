package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentWithoutValTestData {
    public void enter(Data data, Data[] array) {
        Data ignored1 = array[0];

        Data first = array[0];

        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];

        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
        getter1 = data;

        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
        deepGetter2 = data;
        
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];

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
