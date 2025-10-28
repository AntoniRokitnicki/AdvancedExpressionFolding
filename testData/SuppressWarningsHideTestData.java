package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.Date;
import java.util.List;</fold>

@SuppressWarnings("rawtypes")
public class SuppressWarningsHideTestData {

    @SuppressWarnings("unused")
    private int unusedField;

    @SuppressWarnings("unchecked")
    private static List rawStaticList = new ArrayList();

    <fold text='' expand='true'>@SuppressWarnings("deprecation")</fold><fold text='' expand='true'>
    </fold>public SuppressWarningsHideTestData() <fold text='{...}' expand='true'>{
        Date date = new Date();
        date.getYear();
    }</fold>

    public SuppressWarningsHideTestData(@SuppressWarnings("unused") String ignoredParam) <fold text='{}' expand='true'>{
    }</fold>

    <fold text='' expand='true'>@SuppressWarnings({"rawtypes", "unchecked"})</fold><fold text='' expand='true'>
    </fold>public void methodWithWarnings() <fold text='{...}' expand='true'>{
        List list = new ArrayList();
        List<String> stringList = list;
    }</fold>

    public void methodWithAnnotatedParam(@SuppressWarnings("unused") int unusedParam) <fold text='{...}' expand='true'>{
        System.out.println("Method called");
    }</fold>

    <fold text='' expand='true'>@SuppressWarnings("deprecation")</fold><fold text='' expand='true'>
    </fold>public void methodWithAnnotatedLocalVar() <fold text='{...}' expand='true'>{
        @SuppressWarnings("unused")
        int unusedLocalVar = 42;

        Date oldDate = new Date();
        oldDate.getMonth();
    }</fold>

}