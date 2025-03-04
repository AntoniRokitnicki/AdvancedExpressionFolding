package data;

import java.util.*;

public class ConstructorReferenceNotationTestData {

    static class Const {
        public static final ConstClass SELF = ::new;
        public static final ConstClass SELF_ANN = ::new{};
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private static final HashMap<String, String> MAP = ::new;
        private static final HashMap<String, String> MAP2 = ::new;
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();

        private static final List<String> LIST = new ArrayList<>();
        private static final List<String> LIST2 = List.of();
        private static final List<String> LIST_SINGLE = List.of("1");
        private static final List<String> LIST_LINKED = new LinkedList<>();


        public static final ConstClass SELF_PARAM_1 = ::new(true);
        public static final ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));

        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        protected static ConstClass SELF_NULL = null;
        protected static ConstClass EMPTY;
    }
    static class Fields {
        final ConstClass SELF = ::new;
        ConstClass SELF_ANN = ::new{};
        public final ConstClass SELF_SUB = new SubConstClass();
        public final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private final HashMap<String, String> MAP = ::new;
        private final HashMap<String, String> MAP2 = ::new;
        private final Map<String, String> MAP3 = new HashMap<>();

        private final List<String> LIST = new ArrayList<>();
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 = ::new(true);
        public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));

        public final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        protected ConstClass SELF_NULL = null;
        protected ConstClass EMPTY;
    }

    static class ConstClass {
        boolean ok;
        String string;

        public ConstClass() {
        }

        public ConstClass(boolean ok) {
            this.ok = ok;
        }

        public ConstClass(boolean ok, String string) {
            this.ok = ok;
            this.string = string;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ConstClass.class.getSimpleName() + "[", "]")
                    .add("string='" + string + "'")
                    .toString();
        }
    }

    static class SubConstClass extends ConstClass {
    }

    static final class SubConstClass2 extends ConstClass {
    }
}
