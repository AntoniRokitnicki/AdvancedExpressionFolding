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
        System.out.println(var5 + "1");


        [9:"String"] var6 = getData(someString)[10:";\n        if (var6 == null) {\n            return null;\n        }"]
        while (true) {
            if (LocalDate.now().isAfter(LocalDate.now())) {
                if (var6 == null) {
                    System.out.println("1");
                }
            }
            break;
        }


        [11:"String"] var7 = getData(someString)[12:";\n        if (var7 != null) {\n            return var7;\n        }"]
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