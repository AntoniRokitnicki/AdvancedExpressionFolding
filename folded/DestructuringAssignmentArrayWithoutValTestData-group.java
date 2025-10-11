package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayWithoutValTestData {
    public void enter(Data data, Data[] array) {
        Data ignored1 = array[0];

        Data[0:" "]{[0:" "]}first[0:" = array[0];"]{[0:" = array[0];"]}[0:"

        Data "]{[0:"

        Data "]}second[0:" = array[1];"]{[0:" = array[1];"]}[0:"
        Data "]{[0:"
        Data "]}third[0:" = array[2];"]{[0:" = array[2];"]}[0:"
        Data "]{[0:"
        Data "]}fourth[0:" "]{[0:" "]}= array[0:"[3]"]{[0:"[3]"]};

        Data ignored21 = data.[0:"getArray()"]{[0:"getArray()"]}[4];
        Data ignored22 = data.[0:"getArray()"]{[0:"getArray()"]}[5];

        Data[0:" "]{[0:" "]}getter1[0:" = data.getArray()[0];"]{[0:" = data.getArray()[0];"]{[0:"getArray()"]}}[0:"
        Data "]{[0:"
        Data "]}getter2[0:" "]{[0:" "]}= data.[0:"getArray()"]{[0:"getArray()"]}[0:"[1]"]{[0:"[1]"]};
        getter1 = data;

        Data[0:" "]{[0:" "]}deepGetter1[0:" = data.getData().getArray()[0];"]{[0:" = data.getData().getArray()[0];"]{[0:"getData()"] [0:"getArray()"]}}[0:"
        Data "]{[0:"
        Data "]}deepGetter2[0:" "]{[0:" "]}= data.[0:"getData()"]{[0:"getData()"]}.[0:"getArray()"]{[0:"getArray()"]}[0:"[1]"]{[0:"[1]"]};
        deepGetter2 = data;
        
        Data wrongParent1 = data.[0:"getArray()"]{[0:"getArray()"]}[0];
        Data wrongParent2 = data.[0:"getData()"]{[0:"getData()"]}.[0:"getArray()"]{[0:"getArray()"]}[1];

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
