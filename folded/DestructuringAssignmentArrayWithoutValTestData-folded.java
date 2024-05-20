package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayWithoutValTestData {
    public void enter(Data data, Data[] array) {
        Data ignored1 = array[0];

        Data (first, second, third, fourth) = array;

        Data ignored21 = data.array[4];
        Data ignored22 = data.array[5];

        Data (getter1, getter2) = data.array;
        getter1 = data;

        Data (deepGetter1, deepGetter2) = data.data.array;
        deepGetter2 = data;
        
        Data wrongParent1 = data.array[0];
        Data wrongParent2 = data.data.array[1];

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
