package data;

public class PatternMatchingRecordPatternEdgeCasesTestData {

    record Box<T>(T value) {}
    record ComponentAlias(Box<String> box, int count) {}
    record IntBox(int value) {}

    public void componentMethodVariants(Object o) {
        if (o instanceof ComponentAlias(final Box<String> box, final int count))) {
            System.out.println(box + " " + count);
        }
    }

    public void finalBinding(Object o) {
        if (o instanceof IntBox(final int value))) {
            System.out.println(value);
        }
    }

    static class NegativeCases {
        public void mismatchedAccessOrder(Object o) {
            if (o instanceof ComponentAlias alias) {
                int count = alias.count();
                Box<String> box = alias.box();
                System.out.println(box + " " + count);
            }
        }

        public void usesRecordVariable(Object o) {
            if (o instanceof IntBox box) {
                int value = box.value();
                System.out.println(box);
                System.out.println(value);
            }
        }
    }
}
