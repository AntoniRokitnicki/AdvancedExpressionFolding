package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */
@SuppressWarnings("ALL")
public class LombokTestData {

    private static final long serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;

    public LombokTestData getData() {
        return data;
    }

    public void setData(LombokTestData data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    public class LombokGetters {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public boolean isOk() {
            return ok;
        }

        public class LombokGettersPartial {
            LombokTestData data;
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }
    }

    public class LombokSetters {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public void setData(LombokTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        public class LombokSettersPartial {
            LombokTestData data;
            boolean ok;

            public void setData(LombokTestData data) {
                this.data = data;
            }
        }

        public class LombokSettersFinalField {
            LombokTestData data;
            final boolean ok = true;

            public void setData(LombokTestData data) {
                this.data = data;
            }
        }
    }

    public class ToStringFull {
        LombokTestData data;
        boolean ok;

        @Override
        public String toString() {
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
        }

        public class ToStringPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }

        public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }
    }

    public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        public class EqualsPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class HashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class HashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class DataFull {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public void setData(LombokTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        public class DataWithoutToString {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

        }

        public class DataWithPartialGetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }
    }

    public class FoldOn {
        public class FoldOnPublic {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }

        class FoldOnClass {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }

        @SuppressWarnings("ALL")
        class FoldOnWithAnnotation {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
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

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public boolean isOk() {
                return ok;
            }

            public boolean isDirty() {
                return dirty2;
            }
        }
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {
            this.dirty2 = dirty;
        }

        public void setDirty2(boolean dirty2) {
            this.dirty = dirty2;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public boolean isDirty() {
                return dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
        }
    }

    public class LogAnnotation {
        public class LogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogJava2 {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogDiffrentFieldName {
            public static final Logger logger = Logger.getLogger("LogAnnotation.class");
        }

        public class LogCustomNameDeprecated {
            @Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");
        }

        public class TripleLogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
            public static final Logger log2 = Logger.getLogger("LogAnnotation.class");
            static Logger log3 = Logger.getLogger("LogAnnotation.class");
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        public class NoArgsConstructor {
            public NoArgsConstructor() {
            }
        }
        public class NoArgsConstructorSuperBefore {
            public NoArgsConstructorSuperBefore() {
                // comment
                super();
            }
        }
        public class NoArgsConstructorSuperAfter {
            public NoArgsConstructorSuperAfter() {
                super();
                // comment
            }
        }
        public class NoArgsConstructorSuper {
            private String field1;
            public NoArgsConstructorSuper() {
                super();
            }
        }
        public class NoArgsConstructorSuperParent extends Parent {
            public NoArgsConstructorSuperParent() {
                super(null);
            }
        }

    }

    public class AllArgsConstructorAnnotation {
        public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
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

        public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }
    }


    public class RequiredArgsConstructorAnnotation {
        public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
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

        public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }

            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }
    }

    public class ValueAnnotation {
        public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }
            @Override
            public int hashCode() {
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }

            @Override
            public String toString() {
                return "ValueArgs{" +
                        "field1='" + field1 + '\'' +
                        ", field2=" + field2 +
                        ", field3=" + field3 +
                        '}';
            }
        }
        public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }

            @Override
            public int hashCode() {
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }
        }
        public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
        }
    }
    class SingleField {
        public static class AllArgs {
            private String field1;
            public AllArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class ReqArgs {
            private final String field1;
            public ReqArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class Value {
            private final String field1;
            public Value(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Value)) return false;

                Value value = (Value) o;
                return Objects.equals(field1, value.field1);
            }
            @Override
            public int hashCode() {
                return Objects.hashCode(field1);
            }
        }
        public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
        }
        class Modifers {
            static class AllArgsDefault {
                private String field1;
                AllArgsDefault(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsPrivate {
                private String field1;
                private AllArgsPrivate(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsProteced {
                private String field1;
                protected AllArgsProteced(String field1) {
                    this.field1 = field1;
                }
            }
        }
    }


}
