package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) {
        val var1 = getData(someString)?.let { return it }
        val var2 = getData(someString) ?: return null
        val var4 = getData(someString)?.let { return it }
        var4;
        val var5 = getData(someString) ?: return null
        System.out.println("$var51");


        val var6 = getData(someString) ?: return null
        while true {
            if LocalDate.now() > LocalDate.now() {
                if var6 == null {
                    System.out.println("1");
                }
            }
            break;
        }


        val var7 = getData(someString)?.let { return it }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("$var71");
            }
        });
        return null;
    }

    private static String getData(String someString) {
        try {
            return ClassLoader.getSystemResource("a").toString();
        } catch Exception e {
            return null;
        }
    }


}