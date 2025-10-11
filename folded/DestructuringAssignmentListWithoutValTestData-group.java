package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListWithoutValTestData {
    public void enter(Data data, List<Data> list) {
        Data ignored1 = list.get(0);

        Data[0:" "]{[0:" "]}first[0:" = list.get(0);"]{[0:" = list.get(0);"]}[0:"
        Data "]{[0:"
        Data "]}second[0:" = list.get(1);"]{[0:" = list.get(1);"]}[0:"
        Data "]{[0:"
        Data "]}third[0:" = list.get(2);"]{[0:" = list.get(2);"]}[0:"
        Data "]{[0:"
        Data "]}fourth[0:" "]{[0:" "]}= list[0:".get(3)"]{[0:".get(3)"]};

        Data ignored21 = data.[0:"getList()"]{[0:"getList()"]}.get(4);
        Data ignored22 = data.[0:"getList()"]{[0:"getList()"]}.get(5);

        Data[0:" "]{[0:" "]}getter1[0:" = data.getList().get(0);"]{[0:" = data.getList().get(0);"]{[0:"getList()"]}}[0:"
        Data "]{[0:"
        Data "]}getter2[0:" = data.getList().get(1);"]{[0:" = data.getList().get(1);"]{[0:"getList()"]{[0:"getList()"]}}}[0:"
        Data "]{[0:"
        Data "]}getter3[0:" "]{[0:" "]}= data.[0:"getList()"]{[0:"getList()"]}[0:".get(2)"]{[0:".get(2)"]};
        getter1 = data;

        Data[0:" "]{[0:" "]}deepGetter1[0:" = data.getData().getList().get(0);"]{[0:" = data.getData().getList().get(0);"]{[0:"getData()"] [0:"getList()"]}}[0:"
        Data "]{[0:"
        Data "]}deepGetter2[0:" "]{[0:" "]}= data.[0:"getData()"]{[0:"getData()"]}.[0:"getList()"]{[0:"getList()"]}[0:".get(1)"]{[0:".get(1)"]};
        deepGetter2 = data;

        Data wrongParent1 = data.[0:"getList()"]{[0:"getList()"]}.get(0);
        Data wrongParent2 = data.[0:"getData()"]{[0:"getData()"]}.[0:"getList()"]{[0:"getList()"]}.get(1);

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
