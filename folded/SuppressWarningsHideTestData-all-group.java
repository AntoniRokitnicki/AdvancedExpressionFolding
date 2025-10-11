package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public class SuppressWarningsHideTestData {

    @SuppressWarnings("unused")
    private int unusedField;

    @SuppressWarnings("unchecked")
    private static List rawStaticList = [0:"new ArrayList()"];

    [1:"@SuppressWarnings(\"deprecation\")"][1:"\n    "]public SuppressWarningsHideTestData() {
        [2:"Date"][4:"Date"] date = new Date();
        date.[3:"getYear()"][5:"getYear()"];
    }

    public SuppressWarningsHideTestData(@SuppressWarnings("unused") String ignoredParam) {
    }

    [6:"@SuppressWarnings({\"rawtypes\", \"unchecked\"})"][6:"\n    "]public void methodWithWarnings() {
        [7:"List"][10:"List"] list = [8:"new ArrayList()"][11:"new ArrayList()"];
        [9:"List<String>"][12:"List<String>"] stringList = list;
    }

    public void methodWithAnnotatedParam(@SuppressWarnings("unused") int unusedParam) {[13:"\n        "][14:"System.out."][16:"System.out."][18:"System.out."][19:"System.out."][20:"System.out."][22:"System.out."]println([15:"\"Method called\""][17:"\"Method called\""][19:"\"Method called\""][20:"\"Method called\""][21:"\"Method called\""][23:"\"Method called\""])[13:";"][13:"\n    "]}

    public void methodWithAnnotatedLocalVar() {
        [24:"@SuppressWarnings(\"unused\")\n        int"][27:"@SuppressWarnings(\"unused\")\n        int"] unusedLocalVar = 42;

        [25:"@SuppressWarnings(\"deprecation\")\n        Date"][28:"@SuppressWarnings(\"deprecation\")\n        Date"] oldDate = new Date();
        oldDate.[26:"getMonth()"][29:"getMonth()"];
    }

}