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

        const List<String> LIST = new ArrayList<>();
        const List<String> LIST2 = List.of("1");

        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, LIST2.getFirst());

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
        val s = Singleton.🧍;
        println(Singleton.🧍.ok);
        println(Singleton.🧍.main(Singleton.🧍.main(Singleton.instance)));
    }

    @Getter static class Singleton {
        static Singleton INSTANCE = ::new;
        boolean ok;

        Singleton main(Singleton s) {
            return this;
        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
    }

    @ToStringˣ static class ConstClass {
        boolean ok;
        String string;

        public ConstClass() {
        }

        public ConstClass(boolean ok) {
            this.ok = <<;
        }

        public ConstClass(boolean ok, String string) {
            this.ok = <<;
            this.string = <<;
        }
    }


    static class SubConstClass extends ConstClass {
    }

    static  class SubConstClass2 extends ConstClass {
    }

    class SimpleGetSet{
        private String s;

        public String get() {
            return s;
        }

        public void set(String s) {
            this.s = <<;
        }

        void main(SimpleGetSet s) {
            println(s.get());
            s.get();
            s.set("1");
            s.set(s.get());
        }
    }

}
