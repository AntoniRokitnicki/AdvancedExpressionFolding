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
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        const HashMap<String, String> MAP = ::new;
        const HashMap<String, String> MAP2 = ::new;
        const Map<String, String> MAP3 = new HashMap<>();
        const Map<String, String> MAP_TREE = new TreeMap<>();
        const Map<String, String> MAP4 = Map.of();

        const List<String> LIST = new ArrayList<>();
        const List<String> LIST2 = List.of();
        const List<String> LIST_SINGLE = List.of("1");
        const List<String> LIST_LINKED = new LinkedList<>();


        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.getFirst());

        const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
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

        private final List<String> LIST = [];
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 = ::new(true);
        public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.getFirst());

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
        var s = Singleton.🧍;
        System.out.println(Singleton.🧍.isOk());
        System.out.println(Singleton.🧍.main(Singleton.🧍.main(Singleton.getInstance())));

        var s2 = Singleton.🧍.LOCAL;
        System.out.println(Singleton.🧍.LOCAL.isOk());
        System.out.println(Singleton.🧍.LOCAL.main(Singleton.🧍.LOCAL.main(Singleton.getInstance())));
    }

    static class Singleton {
        static Singleton INSTANCE = ::new;
        Singleton LOCAL = ::new;
        @Getter boolean ok;

        Singleton main(Singleton s) {
            return this;
        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
    }

    @NoArgsConstructor @AllArgsConstructor @ToStringˣ static class ConstClass {
        boolean ok;
        String string;

        public ConstClass(boolean ok) {
            this.ok = ok;
        }
    }


    static class SubConstClass extends ConstClass {
    }

    static final class SubConstClass2 extends ConstClass {
    }

    @Getter @Setter class NamelessProperty {
        private NamelessProperty prop;

        public NamelessProperty get() {
            return prop;
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
