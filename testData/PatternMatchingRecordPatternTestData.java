package data;

public class PatternMatchingRecordPatternTestData {

    record Customer(String name, int age) {}
    record Purchase(Customer customer, double total) {}

    public void main(Object o) <fold text='{...}' expand='true'>{
        if (o instanceof <fold text='Customer(String name, int age)' expand='true'>Customer</fold><fold text='))' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>Customer customer = (Customer) o;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>String name = customer.name();</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>int age = customer.age();<fold text='' expand='true'></fold>
            </fold>System.out.println(name + age);
        }</fold>

        if (o instanceof <fold text='Purchase(var buyer, double total)' expand='true'>Purchase</fold><fold text='))' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>Purchase purchase = (Purchase) o;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>var buyer = purchase.customer();</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>double total = purchase.total();</fold><fold text='' expand='true'>
            </fold>System.out.println(buyer + " " + total);
        }</fold>
    }</fold>

    static class NegativeCases <fold text='{...}' expand='true'>{
        public void missingComponent(Object o) <fold text='{...}' expand='true'>{
            if (o instanceof Customer<fold text=' customer)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='true'>Customer customer = (Customer) o;</fold><fold text='' expand='true'>
                </fold>String name = customer.name();
                System.out.println(name);
            }</fold>
        }</fold>

        public void mismatchedOrder(Object o) <fold text='{...}' expand='true'>{
            if (o instanceof Purchase<fold text=' purchase)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='true'>Purchase purchase = (Purchase) o;</fold><fold text='' expand='true'>
                </fold>double total = purchase.total();
                Customer buyer = purchase.customer();
                System.out.println(total + " " + buyer);
            }</fold>
        }</fold>

        public void usesRecordVariable(Object o) <fold text='{...}' expand='true'>{
            if (o instanceof Purchase<fold text=' purchase)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='true'>Purchase purchase = (Purchase) o;</fold><fold text='' expand='true'>
                </fold>Customer buyer = purchase.customer();
                double total = purchase.total();
                System.out.println(purchase);
                System.out.println(buyer + " " + total);
            }</fold>
        }</fold>
    }</fold>
}
