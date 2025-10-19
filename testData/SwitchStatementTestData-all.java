package data;

public class SwitchStatementTestData {
    String describe(int value) <fold text='{...}' expand='true'>{
        String result;
        <fold text='when' expand='false'>switch</fold> <fold text='' expand='false'>(</fold>value<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='case 0 ->' expand='false'>case 0:</fold>
                result = "zero";
                <fold text='' expand='false'>break;</fold>
            <fold text='case 1, 2 ->' expand='false'>case 1:
            case 2:</fold>
                result = "small";
                <fold text='' expand='false'>break;</fold>
            <fold text='case 3 ->' expand='false'>case 3:</fold>
                return "three";
            <fold text='else ->' expand='false'>default:</fold>
                result = "other";
        }</fold>
        return result;
    }</fold>

    void show(int number) <fold text='{...}' expand='true'>{
        <fold text='when' expand='false'>switch</fold> <fold text='' expand='false'>(</fold>number<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='case 10 ->' expand='false'>case 10:</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"ten"' expand='false'>"ten"</fold>);
                <fold text='' expand='false'>break;</fold>
            }</fold>
            <fold text='else ->' expand='false'>default:</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"other"' expand='false'>"other"</fold>);
        }</fold>
    }</fold>
}
