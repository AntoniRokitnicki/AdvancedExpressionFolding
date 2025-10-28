package data;

public class PatternMatchingRecordPatternTestData {

    record Customer(String name, int age) {}
    record Purchase(Customer customer, double total) {}

    public void main(Object o) {
        if (o instanceof Customer) {
            Customer customer = (Customer) o;
            String name = customer.name();
            int age = customer.age();
            System.out.println(name + age);
        }

        if (o instanceof Purchase) {
            Purchase purchase = (Purchase) o;
            var buyer = purchase.customer();
            double total = purchase.total();
            System.out.println(buyer + " " + total);
        }
    }

    static class NegativeCases {
        public void missingComponent(Object o) {
            if (o instanceof Customer) {
                Customer customer = (Customer) o;
                String name = customer.name();
                System.out.println(name);
            }
        }

        public void mismatchedOrder(Object o) {
            if (o instanceof Purchase) {
                Purchase purchase = (Purchase) o;
                double total = purchase.total();
                Customer buyer = purchase.customer();
                System.out.println(total + " " + buyer);
            }
        }

        public void usesRecordVariable(Object o) {
            if (o instanceof Purchase) {
                Purchase purchase = (Purchase) o;
                Customer buyer = purchase.customer();
                double total = purchase.total();
                System.out.println(purchase);
                System.out.println(buyer + " " + total);
            }
        }
    }
}
