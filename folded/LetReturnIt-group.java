package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        [0:"String"][13:"String"]ring var1 = "][14:"String var1 = "]getData(someString)[1:";\n        if (var1 != null) {\n            return var1;\n        }"][14:";\n        if (var1 != null) {\n            return var1;\n        }"]
        [2:"String"][15:"String"]ring var2 = "][16:"String var2 = "]getData(someString)[3:";\n        if (var2 == null) {\n            return null;\n        }"][16:";\n        if (var2 == null) {\n            return null;\n        }"]
        [4:"String"][17:"String"] var4 = getData(someString)[5:";\n        if (var4 != null) {\n            return var4;\n        }"][18:";\n        if (var4 != null) {\n            return var4;\n        }"]
        var4[6:".toString()"][19:".toString()"];
        [7:"String"][20:"String"] var5 = getData(someString)[8:";\n        if (var5 == null) {\n            return null;\n        }"][21:";\n        if (var5 == null) {\n            return null;\n        }"]
        System.out.println(var5 + "1");


        [9:"String"][22:"String"] var6 = getData(someString)[10:";\n        if (var6 == null) {\n            return null;\n        }"][23:";\n        if (var6 == null) {\n            return null;\n        }"]
        while (true) {
            if (LocalDate.now().isAfter(LocalDate.now())) {
                if (var6 == null) {
                    System.out.println("1");
                }
            }
            break;
        }


        [11:"String"][24:"String"] var7 = getData(someString)[12:";\n        if (var7 != null) {\n            return var7;\n        }"][25:";\n        if (var7 != null) {\n            return var7;\n        }"]
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(var7 + "1");
            }
        });
        return null;
    }

    private static String getData(String someString) {
        try {
            return ClassLoader.getSystemResource("a").toString();
        } catch (Exception e) {
            return null;
        }
    }


}