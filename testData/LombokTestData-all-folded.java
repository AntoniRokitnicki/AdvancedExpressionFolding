package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.extension.PsiClassExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */
@SuppressWarnings("ALL")
@Getter @Setter @Serial public class LombokTestData {const serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;

    public Optional<LombokTestData> asOptional() {
        return data;
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

        @Setterˣ public class LombokSettersPartial {
            LombokTestData data;
            boolean ok;
        }

        @Setter public class LombokSettersFinalField {
            LombokTestData data;
            final boolean ok = true;
        }
    }

    @ToString public class ToStringFull {
        LombokTestData data;
        boolean ok;

        @ToStringˣ public class ToStringPartial {
            LombokTestData data;
            boolean ok;
        }

        @ToStringˣ public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @EqualsAndHashCodeˣ public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @EqualsAndHashCodeˣ public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Equalsˣ public class EqualsPartial {
            LombokTestData data;
            boolean ok;
        }

        @Equalsˣ public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @HashCodeˣ public class HashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @HashCodeˣ public class HashCodePartialTwo {
            LombokTestData data;
            boolean ok;
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

        @Dataˣ public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;
        }
    }

    public class FoldOn {
        @Getter public class FoldOnPublic{
            boolean ok;
        }

        @Getter class FoldOnClass{
            boolean ok;
        }

        @SuppressWarnings("ALL")
        @Getter class FoldOnWithAnnotation {
            boolean ok;
        }
    }
    
    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {
            return dirty2;
        }

        public boolean isDirty2() {
            return dirty;
        }

        @EqualsAndHashCode public class DirtyData {
            boolean dirty;
            @Getter private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }
        }

        public class DirtySingle {
            boolean dirty;
            @Getter boolean ok;
            public boolean isDirty() {
                return dirty2;
            }
        }
    }

    @Setter public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        @Data public class DirtyData {
            boolean dirty;
            private boolean ok;
        }

        @Setter public class DirtySingle {
            boolean dirty;
            boolean ok;
        }
    }

}
