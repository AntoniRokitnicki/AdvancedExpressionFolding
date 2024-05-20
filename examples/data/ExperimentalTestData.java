package data;


import java.util.*;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.ExperimentalExt#singletonField(com.intellij.psi.PsiReferenceExpression)}
 * {@link com.intellij.advancedExpressionFolding.extension.NullableExt#foldFieldConstructor(com.intellij.psi.PsiField, com.intellij.openapi.editor.Document)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */
public class ExperimentalTestData {

    static class Const {
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();

        private static final List<String> LIST = new ArrayList<>();
        private static final List<String> LIST2 = List.of();
        private static final List<String> LIST_SINGLE = List.of("1");
        private static final List<String> LIST_LINKED = new LinkedList<>();


        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));

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
        final ConstClass SELF = new ConstClass();
        ConstClass SELF_ANN = new ConstClass() {
        };
        public final ConstClass SELF_SUB = new SubConstClass();
        public final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private final HashMap<String, String> MAP = new HashMap<>();
        private final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private final Map<String, String> MAP3 = new HashMap<>();

        private final List<String> LIST = new ArrayList<>();
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));

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

    void main() {
        var s = Singleton.INSTANCE;
        System.out.println(Singleton.INSTANCE.isOk());
        System.out.println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.getInstance())));

        var s2 = Singleton.INSTANCE.LOCAL;
        System.out.println(Singleton.INSTANCE.LOCAL.isOk());
        System.out.println(Singleton.INSTANCE.LOCAL.main(Singleton.INSTANCE.LOCAL.main(Singleton.getInstance())));
    }

    static class Singleton {
        static Singleton INSTANCE = new Singleton();
        Singleton LOCAL = new Singleton();
        boolean ok;

        Singleton main(Singleton s) {
            return this;
        }

        public boolean isOk() {
            return ok;
        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
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

    class NamelessProperty {
        private NamelessProperty prop;

        public NamelessProperty get() {
            return prop;
        }

        public data.ExperimentalTestData.NamelessProperty getProp() {
            return prop;
        }

        public void setProp(NamelessProperty prop) {
            this.prop = prop;
        }

        public void set(NamelessProperty s) {
            this.prop = s;
        }

        NamelessProperty main(NamelessProperty s, NamelessProperty namelessProperty) {
            s.set(namelessProperty.getProp().get());
            s.set(namelessProperty.getProp());
            s.setProp(namelessProperty.getProp().get());
            s.setProp(namelessProperty.getProp());


            s.set(namelessProperty.get());
            s.set(namelessProperty.get().get()); //TODO:

            System.out.println(s.get());
            s.get();
            s.set(namelessProperty);
            s.set(s.get());
            s.set(namelessProperty.get().get().get());
            s.set(main(s.get(), namelessProperty.get()));
            return namelessProperty.get();
        }
    }


}
