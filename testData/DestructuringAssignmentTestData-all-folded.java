package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentTestData {
    public void enter(Data data, Data[] array) {
        System.out.println();
        val a1, a2) = array;
        val a4 = data.array[4];
        val a5 = data.array[5];

        val a6, a7) = data.array;

        val a8, a9) = data.data.array;

        // wrong "parent"
        val a10 = data.array[0];
        val a11 = data.data.array[1];

        blackhole(a1, a2, a4, a5, a6, a7, a8, a9, a10, a11);
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
