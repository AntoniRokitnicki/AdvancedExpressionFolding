package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        [0:"String"][27:"String"]ring var1 = "][28:"String var1 = "]getData(someString)[1:";\n        if (var1 != null) {\n            return var1;\n        }"][28:";\n        if (var1 != null) {\n            return var1;\n        }"]
        [2:"String"][29:"String"]ring var2 = "][30:"String var2 = "]getData(someString)[3:";\n        if (var2 == null) {\n            return null;\n        }"][30:";\n        if (var2 == null) {\n            return null;\n        }"]
        [4:"String"][31:"String"] var4 = getData(someString)[5:";\n        if (var4 != null) {\n            return var4;\n        }"][32:";\n        if (var4 != null) {\n            return var4;\n        }"]
        var4[6:".toString()"][33:".toString()"];
        [7:"String"][34:"String"] var5 = getData(someString)[8:";\n        if (var5 == null) {\n            return null;\n        }"][35:";\n        if (var5 == null) {\n            return null;\n        }"]
        [9:"System.out."][36:"System.out."]println[10:"("][11:"("][37:"("][38:"("]var5[10:" + \""][11:" + \""][37:" + \""][38:" + \""]1");


        [12:"String"][39:"String"] var6 = getData(someString)[13:";\n        if (var6 == null) {\n            return null;\n        }"][40:";\n        if (var6 == null) {\n            return null;\n        }"]
        while [14:"("][41:"("]true[14:")"][41:")"] {
            if [42:"("]LocalDate.now()[43:".isAfter("]LocalDate.now()[43:")"][42:")"] {
                if [44:"("]var6 == null[44:")"] {
                    [45:"System.out."]println([46:"\"1\""]);
                }
            }
            break;
        }


        [15:"String"][47:"String"] var7 = getData(someString)[16:";\n        if (var7 != null) {\n            return var7;\n        }"][48:";\n        if (var7 != null) {\n            return var7;\n        }"]
        new Thread(new Runnable() {
            [23:"@Override"][52:"@Override"][23:"\n            "][52:"\n            "]public void run() {[17:"\n                "][48:"\n                "][18:"System.out."][21:"System.out."][24:"System.out."][49:"System.out."][51:"System.out."][53:"System.out."][56:"System.out."]println[19:"("][20:"("][22:"("][25:"("][26:"("][50:"("][51:"("][52:"("][54:"("][55:"("][57:"("][58:"("]var7[19:" + \""][20:" + \""][22:" + \""][25:" + \""][26:" + \""][50:" + \""][51:" + \""][52:" + \""][54:" + \""][55:" + \""][57:" + \""][58:" + \""]1")[17:";"][48:";"][17:"\n            "][48:"\n            "]}
        });
        return null;
    }

    private static String getData(String someString) {
        try {
            return ClassLoader.getSystemResource("a").toString();
        } catch [59:"("][60:"("]Exception e[59:")"][60:")"] {
            return null;
        }
    }


}