package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        [0:"String"] var1 = getData(someString)[1:";\n        if (var1 != null) {\n            return var1;\n        }"]
        [2:"String"] var2 = getData(someString)[3:";\n        if (var2 == null) {\n            return null;\n        }"]
        [4:"String"] var4 = getData(someString)[5:";\n        if (var4 != null) {\n            return var4;\n        }"]
        var4[6:".toString()"];
        [7:"String"] var5 = getData(someString)[8:";\n        if (var5 == null) {\n            return null;\n        }"]
        [9:"System.out."]println[10:"("]var5[10:" + \""]1");


        [12:"String"] var6 = getData(someString)[13:";\n        if (var6 == null) {\n            return null;\n        }"]
        while [14:"("]true[14:")"] {
            if [42:"("]LocalDate.now()[43:".isAfter("]LocalDate.now()[43:")"][42:")"] {
                if [44:"("]var6 == null[44:")"] {
                    [45:"System.out."]println([46:"\"1\""]);
                }
            }
            break;
        }


        [15:"String"] var7 = getData(someString)[16:";\n        if (var7 != null) {\n            return var7;\n        }"]
        new Thread(new Runnable() {
            [23:"@Override"][23:"\n            "]public void run() {[17:"\n                "][18:"System.out."]println[19:"("]var7[19:" + \""]1")[17:";"][17:"\n            "]}
        });
        return null;
    }

    private static String getData(String someString) {
        try {
            return ClassLoader.getSystemResource("a").toString();
        } catch [59:"("]Exception e[59:")"] {
            return null;
        }
    }


}