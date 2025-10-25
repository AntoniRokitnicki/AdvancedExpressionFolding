package data;

public class PatternMatchingRecordPatternEdgeCasesTestData {

    record Box<T>(T value) {}
    record ComponentAlias(Box<String> box, int count) {}
    record IntBox(int value) {}

    public void componentMethodVariants(Object o) {
        if (o instanceof ComponentAlias) {
            ComponentAlias alias = (ComponentAlias) o;
            final Box<String> box = alias.box();
            final int count = alias.count();
            System.out.println(box + " " + count);
        }
    }

    public void finalBinding(Object o) {
        if (o instanceof IntBox) {
            IntBox box = (IntBox) o;
            final int value = box.value();
            System.out.println(value);
        }
    }

    static class NegativeCases {
        public void mismatchedAccessOrder(Object o) {
            if (o instanceof ComponentAlias) {
                ComponentAlias alias = (ComponentAlias) o;
                int count = alias.count();
                Box<String> box = alias.box();
                System.out.println(box + " " + count);
            }
        }

        public void usesRecordVariable(Object o) {
            if (o instanceof IntBox) {
                IntBox box = (IntBox) o;
                int value = box.value();
                System.out.println(box);
                System.out.println(value);
            }
        }
    }
}
