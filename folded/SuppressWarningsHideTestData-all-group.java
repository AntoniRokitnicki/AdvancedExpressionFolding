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
        [2:"Date"] date = new Date();
        date.[3:"getYear()"];
    }

    public SuppressWarningsHideTestData(@SuppressWarnings("unused") String ignoredParam) {
    }

    [6:"@SuppressWarnings({\"rawtypes\", \"unchecked\"})"][6:"\n    "]public void methodWithWarnings() {
        [7:"List"] list = [8:"new ArrayList()"];
        [9:"List<String>"] stringList = list;
    }

    public void methodWithAnnotatedParam(@SuppressWarnings("unused") int unusedParam) {[13:"\n        "][14:"System.out."]println([15:"\"Method called\""])[13:";"][13:"\n    "]}

    public void methodWithAnnotatedLocalVar() {
        [24:"@SuppressWarnings(\"unused\")\n        int"] unusedLocalVar = 42;

        [25:"@SuppressWarnings(\"deprecation\")\n        Date"] oldDate = new Date();
        oldDate.[26:"getMonth()"];
    }

}