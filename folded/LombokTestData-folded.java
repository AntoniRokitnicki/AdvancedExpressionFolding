package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.processor.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */
@SuppressWarnings("ALL")
@Builder(ClassWithBuilder) @Getter @Setter @Serial public class LombokTestData {

    LombokTestData data;
    boolean ok;
    String string;

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    @Getter public class LombokGetters {
        LombokTestData data;
        boolean ok;

        public class LombokGettersPartial {
            LombokTestData data;
            @Getter boolean ok;
        }
    }

    @Getter @Setter public class LombokSetters {
        LombokTestData data;
        boolean ok;

        public class LombokSettersPartial {
            @Setter LombokTestData data;
            boolean ok;
        }

        public class LombokSettersFinalField {
            @Setter LombokTestData data;
            final boolean ok = true;
        }
    }

    @ToString public class ToStringFull {
        LombokTestData data;
        boolean ok;

        public class ToStringPartial {
            @ToString LombokTestData data;
            boolean ok;
        }

        public class ToStringPartial2 {
            @ToString LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        public class EqualsAndHashCodePartial {
            @EqualsAndHashCode LombokTestData data;
            boolean ok;
        }

        public class EqualsAndHashCodePartialTwo {
            @EqualsAndHashCode LombokTestData data;
            @EqualsAndHashCode boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokTestData data;
        boolean ok;

        public class EqualsPartial {
            @Equals LombokTestData data;
            boolean ok;
        }

        public class EqualsPartialTwo {
            @Equals LombokTestData data;
            @Equals boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        public class HashCodePartial {
            @HashCode LombokTestData data;
            boolean ok;
        }

        public class HashCodePartialTwo {
            @HashCode LombokTestData data;
            @HashCode boolean ok;
            String string;
        }
    }

    @Data public class DataFull {
        LombokTestData data;
        boolean ok;

        @Data public class DataWithoutToString {
            LombokTestData data;
            boolean ok;

        }

        @Setter @ToString @EqualsAndHashCode public class DataWithPartialGetters {
            @Getter LombokTestData data;
            boolean ok;
        }

        @Getter @ToString @EqualsAndHashCode public class DataWithPartialSetters {
            @Setter LombokTestData data;
            boolean ok;
        }
    }

    public class FoldOn {
        @Getter public class FoldOnPublic {
            boolean ok;
        }

        @Getter class FoldOnClass {
            boolean ok;
        }

        @SuppressWarnings("ALL")
        @Getter class FoldOnWithAnnotation {
            boolean ok;
        }
    }

    public class DirtyLombokGetters {
        @Getter(dirtyNoReference) boolean dirty;
        @Getter(dirtyNoReference) private boolean dirty2;

        @EqualsAndHashCode public class DirtyData {
            @Getter(dirty) boolean dirty;
            @Getter private boolean ok;
        }

        public class DirtySingle {
            @Getter(dirtyNoReference) boolean dirty;
            @Getter boolean ok;
        }
    }

    public class SupportedDirtyLombokGetters {
        @Getter(wrapper = Collections::unmodifiableList) private List<String> wrapper;
        @Getter(dirtyNoReference) private List<String> wrapperWrongRef;
        @Getter(dirty) private List<String> wrapperDeeplyHiddenRef;

        @Getter(wrapper = this::localWrap) private List<String> localMethodWrappedList;
        @Getter(wrapper = this::localWrap) private List<String> thisLocalMethodWrappedList;
        @Getter(lazy = ArrayList::new) private List<String> lazyLoadedList;
        @Getter(lazy = ArrayList::new) private List<String> oneLineLazyLoadedList;
        private Supplier<List<String>> lazyLoadedListSupplier;
        @Getter(lazy = lazyLoadedListSupplier::get) private List<String> lazyLoadedListFromSupplier;
        private Supplier<List<String>> oneLineLazyLoadedListSupplier;
        @Getter(lazy = oneLineLazyLoadedListSupplier::get) private List<String> oneLineLazyLoadedListFromSupplier;
        @Getter(wrapper = ArrayList::new) private List<String> defensiveCopyList;

        private List<String> localWrap(List<String> list) {
            return null;
        }
    }

    public class DirtyLombokSetters {
        @Setter(dirtyNoReference) boolean dirty;
        @Setter(dirtyNoReference) private boolean dirty2;
        @Setter boolean withoutThis;

        @Getter @EqualsAndHashCode public class DirtyData {
            @Setter(dirty) boolean dirty;
            @Setter private boolean ok;
        }

        public class DirtySingle {
            @Setter(dirtyNoReference) boolean dirty;
            @Setter boolean ok;
        }
    }

    public class LogAnnotation {
        @Log public class LogJava {
        }

        @Log public class LogJava2 {
        }

        @Log(logger) public class LogDiffrentFieldName {
        }

        @Log(xlogger) public class LogCustomNameDeprecated {
        }

        @Log @Log(log2) @Log(log3) public class TripleLogJava {
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        @NoArgsConstructor public class NoArgsConstructor {
        }
        @NoArgsConstructor(private) class NoArgsConstructorPrivate {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorCommentBeforeSuper {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorCommentAfterSuper {
        }
        @NoArgsConstructor(protected,// comment) public class ProtectedNoArgsConstructorComment {
        }
        // comment hidden as it is common and adds no meaningful value
        @NoArgsConstructor(private) public class PrivateNoArgsConstructorComment {
        }
        @NoArgsConstructor public class NoArgsConstructorSuper {
            private String field1;
        }
        public class NoArgsConstructorSuperParent extends Parent {
            public NoArgsConstructorSuperParent() {
                super(null);
            }
        }

    }

    public class AllArgsConstructorAnnotation {
        @AllArgsConstructor public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field1;
                this.field3 = field3;
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field1 = field2;
                this.field3 = field3;
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                // comment
            }
        }

        @AllArgsConstructor public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
        }

        @AllArgsConstructor(private) public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        @AllArgsConstructor(protected) public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
        }
    }


    public class RequiredArgsConstructorAnnotation {
        @RequiredArgsConstructor public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                // comment
            }
        }

        @RequiredArgsConstructor public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }

        @RequiredArgsConstructor(private) public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        @RequiredArgsConstructor(protected) public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
    }

    public class ValueAnnotation {
        @Value public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @Value public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @LightValue public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
    }
    class SingleField {
        @AllArgsConstructor public static class AllArgs {
            private String field1;
        }
        @RequiredArgsConstructor public static class ReqArgs {
            private final String field1;
        }
        @Value public static class Value {
            private final String field1;
        }
        @LightValue public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
        }
        class Modifers {
            @AllArgsConstructor(default) static class AllArgsDefault {
                private String field1;
            }
            @AllArgsConstructor(private) static class AllArgsPrivate {
                private String field1;
            }
            @AllArgsConstructor(protected) static class AllArgsProteced {
                private String field1;
            }
        }
    }

    class FieldLevelData {
        @Data private String name;
        private String ignored;
    }

    class FieldLevelValue {
        @Value private final String name = "1";
        private String ignored;
    }
    class FieldLevelNotFinalNotValue {
        @Getter @EqualsAndHashCode private String name;
        private String ignored;
    }

    @Builder class ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = name;
                return this;
            }
            public ClassWithBuilder build() {
                return new ClassWithBuilder();
            }
        }
    }

    class Constructors {
        @NoArgsConstructor @AllArgsConstructor public class FiveConstructors {
            @Constructor(2-1) @Constructor(3-1) @Constructor(4-1) private int field1;
            @Constructor(3-2) @Constructor(4-2) private String field2;
            @Constructor(4-3) private double field3;
            private boolean field4;
        }

        @NoArgsConstructor @AllArgsConstructor public class FiveConstructorsMod {
            @Constructor(2-1,private) @Constructor(3-1,default) @Constructor(4-1,protected) private int field1;
            @Constructor(3-2,default) @Constructor(4-2,protected) private String field2;
            @Constructor(4-3,protected) private double field3;
            private boolean field4;
        }

        class SingleConstructor {
            @Constructor(default) private final int field1;
            private int field2;
        }

        private class SingleDoubleConstructor {
            @Constructor(1,private) private final int field1;
            @Constructor(2,private) private final int field2;
            private int field3;
        }

        public class SingleConstructorNoMod {
            @Constructor private final int field1;
            private int field2;
        }

        public class SingleDoubleConstructorNoMod {
            @Constructor(1) private final int field1;
            @Constructor(2) private final int field2;
            private int field3;
        }

    }

    @Builder(FirstBuilder) @Builder(SecondBuilder) @Builder class Builders {

        class FirstBuilder {

        }

        class SecondBuilder {

        }

        class BuildersBuilder {

        }

    }

}
