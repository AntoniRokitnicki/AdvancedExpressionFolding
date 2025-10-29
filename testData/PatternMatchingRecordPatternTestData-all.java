package data;

public class PatternMatchingRecordPatternTestData {

    record Customer(String name, int age) {}
    record Purchase(Customer customer, double total) {}

    public void main(Object o) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>o instanceof Customer<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Customer</fold> customer = <fold text='' expand='false'>(Customer) </fold>o;
            <fold text='val' expand='false'>String</fold> name = customer.<fold text='name' expand='false'>name()</fold>;
            <fold text='val' expand='false'>int</fold> age = customer.<fold text='age' expand='false'>age()</fold>;
            <fold text='' expand='false'>System.out.</fold>println(name + age);
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof Purchase<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Purchase</fold> purchase = <fold text='' expand='false'>(Purchase) </fold>o;
            <fold text='val' expand='false'>var</fold> buyer = purchase.<fold text='customer' expand='false'>customer()</fold>;
            <fold text='val' expand='false'>double</fold> total = purchase.<fold text='total' expand='false'>total()</fold>;
            <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>buyer<fold text='' expand='false'> + "</fold> <fold text='$' expand='false'>" + </fold>total<fold text='")' expand='false'>)</fold>;
        }</fold>
    }</fold>

    static class NegativeCases <fold text='{...}' expand='true'>{
        public void missingComponent(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof Customer<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Customer</fold> customer = <fold text='' expand='false'>(Customer) </fold>o;
                <fold text='val' expand='false'>String</fold> name = customer.<fold text='name' expand='false'>name()</fold>;
                <fold text='' expand='false'>System.out.</fold>println(name);
            }</fold>
        }</fold>

        public void mismatchedOrder(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof Purchase<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Purchase</fold> purchase = <fold text='' expand='false'>(Purchase) </fold>o;
                <fold text='val' expand='false'>double</fold> total = purchase.<fold text='total' expand='false'>total()</fold>;
                <fold text='val' expand='false'>Customer</fold> buyer = purchase.<fold text='customer' expand='false'>customer()</fold>;
                <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>total<fold text='' expand='false'> + "</fold> <fold text='$' expand='false'>" + </fold>buyer<fold text='")' expand='false'>)</fold>;
            }</fold>
        }</fold>

        public void usesRecordVariable(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof Purchase<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Purchase</fold> purchase = <fold text='' expand='false'>(Purchase) </fold>o;
                <fold text='val' expand='false'>Customer</fold> buyer = purchase.<fold text='customer' expand='false'>customer()</fold>;
                <fold text='val' expand='false'>double</fold> total = purchase.<fold text='total' expand='false'>total()</fold>;
                <fold text='' expand='false'>System.out.</fold>println(purchase);
                <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>buyer<fold text='' expand='false'> + "</fold> <fold text='$' expand='false'>" + </fold>total<fold text='")' expand='false'>)</fold>;
            }</fold>
        }</fold>
    }</fold>
}
