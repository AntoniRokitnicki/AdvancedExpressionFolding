package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public class SuppressWarningsHideTestData {

    private int unusedField;

    private static List rawStaticList = new ArrayList();

    public SuppressWarningsHideTestData() {
        Date date = new Date();
        date.toInstant();
    }

    public SuppressWarningsHideTestData(String ignoredParam) {
    }

    public void methodWithWarnings() {
        List list = new ArrayList();
        List<String> stringList = list;
    }

    public void methodWithAnnotatedParam(int unusedParam) {
        System.out.println("Method called");
    }

    public void methodWithAnnotatedLocalVar() {
        int unusedLocalVar = 42;

        Date oldDate = new Date();
        oldDate.toInstant();
    }

}
