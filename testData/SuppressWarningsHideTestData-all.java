package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.Date;
import java.util.List;</fold>

@SuppressWarnings("rawtypes")
public class SuppressWarningsHideTestData {

    @SuppressWarnings("unused")
    private int unusedField;

    @SuppressWarnings("unchecked")
    private static List rawStaticList = <fold text='[]' expand='false'>new ArrayList()</fold>;

    <fold text='' expand='true'>@SuppressWarnings("deprecation")</fold><fold text='' expand='true'>
    </fold>public SuppressWarningsHideTestData() <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Date</fold> date = new Date();
        date.<fold text='year' expand='false'>getYear()</fold>;
    }</fold>

    public SuppressWarningsHideTestData(@SuppressWarnings("unused") String ignoredParam) <fold text='{}' expand='true'>{
    }</fold>

    <fold text='' expand='true'>@SuppressWarnings({"rawtypes", "unchecked"})</fold><fold text='' expand='true'>
    </fold>public void methodWithWarnings() <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>List</fold> list = <fold text='[]' expand='false'>new ArrayList()</fold>;
        <fold text='val' expand='false'>List<String></fold> stringList = list;
    }</fold>

    public void methodWithAnnotatedParam(@SuppressWarnings("unused") int unusedParam) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
        </fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Method called"' expand='false'>"Method called"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
    </fold>}</fold>

    <fold text='' expand='true'>@SuppressWarnings("deprecation")</fold><fold text='' expand='true'>
    </fold>public void methodWithAnnotatedLocalVar() <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>@SuppressWarnings("unused")
        int</fold> unusedLocalVar = 42;

        <fold text='val' expand='false'>Date</fold> oldDate = new Date();
        oldDate.<fold text='month' expand='false'>getMonth()</fold>;
    }</fold>

}