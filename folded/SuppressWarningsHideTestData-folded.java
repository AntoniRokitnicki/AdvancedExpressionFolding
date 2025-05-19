package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public class SuppressWarningsHideTestData {

    @SuppressWarnings("unused")
    private int unusedField;

    @SuppressWarnings("unchecked")
    private static List rawStaticList = new ArrayList();

    public SuppressWarningsHideTestData() {
        Date date = new Date();
        date.getYear();
    }

    public SuppressWarningsHideTestData(@SuppressWarnings("unused") String ignoredParam) {
    }

    public void methodWithWarnings() {
        List list = new ArrayList();
        List<String> stringList = list;
    }

    public void methodWithAnnotatedParam(@SuppressWarnings("unused") int unusedParam) {
        System.out.println("Method called");
    }

    public void methodWithAnnotatedLocalVar() {
        @SuppressWarnings("unused")
        int unusedLocalVar = 42;

        @SuppressWarnings("deprecation")
        Date oldDate = new Date();
        oldDate.getMonth();
    }

}