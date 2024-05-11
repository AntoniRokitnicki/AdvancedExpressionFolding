package data;

import static data.ConstTestData.EOrder.SECOND;

public class ConstTestData {

    <fold text='econst' expand='false'>static final</fold> EOrder ORDER1 = EOrder.FIRST;
    <fold text='econst' expand='false'>static final</fold> EOrder ORDER2 = SECOND;

    <fold text='const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>private static final </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>protected static final </fold><fold text='' expand='false'>String</fold> PROTECTED_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>static final</fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> VERSION = 1;
    <fold text='const' expand='false'>static final</fold> int VERSION_REF = VERSION;


    enum EOrder <fold text='{...}' expand='true'>{
        FIRST,
        SECOND;
    }</fold>

}

