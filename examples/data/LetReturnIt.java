package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        String var1 = getData(someString);
        if (var1 != null) {
            return var1;
        }
        String var2 = getData(someString);
        if (var2 == null) {
            return null;
        }
        String var4 = getData(someString);
        if (var4 != null) {
            return var4;
        }
        var4.toString();
        String var5 = getData(someString);
        if (var5 == null) {
            return null;
        }
        System.out.println(var5 + "1");


        String var6 = getData(someString);
        if (var6 == null) {
            return null;
        }
        while (true) {
            if (LocalDate.now().isAfter(LocalDate.now())) {
                if (var6 == null) {
                    System.out.println("1");
                }
            }
            break;
        }


        String var7 = getData(someString);
        if (var7 != null) {
            return var7;
        }
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