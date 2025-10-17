package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) {
        [0:"Data"][32:"Data"] ignored1 = list[1:".get"][33:".get"]([1:"0"][33:"0"]);

        [35:"Data"]Data "][34:"Data "]first[2:" = list.get(0);"][34:" = list.get(0);"]t"]([36:"0"]);[2:"\n        Data "][34:"\n        Data "]ata"][37:"Data"] second[2:" = list.get(1);"][34:" = list.get(1);"]("][38:".get("]1[4:")"][38:")"];[2:"\n        Data "][34:"\n        Data "]ata"][39:"Data"] third[2:" = list.get(2);"][34:" = list.get(2);"]("][40:".get("]2[6:")"][40:")"];[2:"\n        Data "][34:"\n        Data "]ata"][41:"Data"] fourth[2:" "][34:" "]= list[8:".get("][42:".get("]get(3)"][34:".get(3)"]8:")"][42:")"];

        [9:"Data"][43:"Data"] ignored21 = data.[11:"getList()"][45:"getList()"][10:".get("][44:".get("]4[10:")"][44:")"];
        [12:"Data"][46:"Data"] ignored22 = data.[14:"getList()"][48:"getList()"][13:".get("][47:".get("]5[13:")"][47:")"];

        [50:"Data"]"Data "][49:"Data "]getter1[14:" = data.getList().get(0);"][49:" = data.getList().get(0);"]51:".get"]([51:"0"]);[14:"\n        Data "][49:"\n        Data "]Data"][53:"Data"] getter2[14:" = data.getList().get(1);"][49:" = data.getList().get(1);"]55:"getList()"][16:".get("][54:".get("]1[16:")"][54:")"];[14:"\n        Data "][49:"\n        Data "]Data"][56:"Data"] getter3[14:" "][49:" "]= data.[20:"getList()"][58:"getList()"][19:".get("][57:".get("].get(2)"][49:".get(2)"]19:")"][57:")"];
        getter1 = data;

        [60:"Data"]"Data "][59:"Data "]deepGetter1[20:" = data.getData().getList().get(0);"][59:" = data.getData().getList().get(0);"]st()"][61:".get"]([61:"0"]);[20:"\n        Data "][59:"\n        Data "]Data"][64:"Data"] deepGetter2[20:" "][59:" "]= data.[24:"getData()"][67:"getData()"].[23:"getList()"][66:"getList()"][22:".get("][65:".get("].get(1)"][59:".get(1)"]22:")"][65:")"];
        deepGetter2 = data;

        [25:"Data"][68:"Data"] wrongParent1 = data.[27:"getList()"][70:"getList()"][26:".get"][69:".get"]([26:"0"][69:"0"]);
        [28:"Data"][71:"Data"] wrongParent2 = data.[31:"getData()"][74:"getData()"].[30:"getList()"][73:"getList()"][29:".get("][72:".get("]1[29:")"][72:")"];

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }

    static class Data {
        [75:"public"][75:" "][75:"Data"][75:" "]getData[75:"()"] {[75:"\n            "][75:"return"][75:" "]null[75:";"][75:"\n        "]}

        [76:"public"][76:" "][76:"Data[]"][76:" "]getArray[76:"()"] {[76:"\n            "][76:"return"][76:" "]null[76:";"][76:"\n        "]}

        [77:"public"][77:" "][77:"List<Data>"][77:" "]getList[77:"()"] {[77:"\n            "][77:"return"][77:" "]null[77:";"][77:"\n        "]}
    }

    void blackhole(Data... datas) {}

}
