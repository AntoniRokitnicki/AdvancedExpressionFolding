package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        [0:"String var1 = "]{[0:"String var1 = "]{[0:"String"]{[0:"String"]}}}getData(someString)[0:";
        if (var1 != null) {
            return var1;
        }"]{[0:";
        if (var1 != null) {
            return var1;
        }"]}
        [0:"String var2 = "]{[0:"String var2 = "]{[0:"String"]{[0:"String"]}}}getData(someString)[0:";
        if (var2 == null) {
            return null;
        }"]{[0:";
        if (var2 == null) {
            return null;
        }"]}
        [0:"String"]{[0:"String"]} var4 = getData(someString)[0:";
        if (var4 != null) {
            return var4;
        }"]{[0:";
        if (var4 != null) {
            return var4;
        }"]}
        var4[0:".toString()"]{[0:".toString()"]};
        [0:"String"]{[0:"String"]} var5 = getData(someString)[0:";
        if (var5 == null) {
            return null;
        }"]{[0:";
        if (var5 == null) {
            return null;
        }"]}
        System.out.println(var5 + "1");


        [0:"String"]{[0:"String"]} var6 = getData(someString)[0:";
        if (var6 == null) {
            return null;
        }"]{[0:";
        if (var6 == null) {
            return null;
        }"]}
        while (true) {
            if (LocalDate.now().isAfter(LocalDate.now())) {
                if (var6 == null) {
                    System.out.println("1");
                }
            }
            break;
        }


        [0:"String"]{[0:"String"]} var7 = getData(someString)[0:";
        if (var7 != null) {
            return var7;
        }"]{[0:";
        if (var7 != null) {
            return var7;
        }"]}
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