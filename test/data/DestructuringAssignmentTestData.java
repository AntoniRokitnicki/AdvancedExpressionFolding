package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentTestData {
    public void enter(Data data, Data[] array) {
        System.out.println();
        Data a1 = array[0];
        Data a2 = array[1];
        Data a4 = data.getArray()[4];
        Data a5 = data.getArray()[5];

        Data a6 = data.getArray()[0];
        Data a7 = data.getArray()[1];

        Data a8 = data.getData().getArray()[0];
        Data a9 = data.getData().getArray()[1];

        // wrong "parent"
        Data a10 = data.getArray()[0];
        Data a11 = data.getData().getArray()[1];

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
