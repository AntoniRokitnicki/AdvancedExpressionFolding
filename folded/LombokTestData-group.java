package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.logging.Logger;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.processor.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */
@SuppressWarnings("ALL")
[0:"p"]ublic class LombokTestData {[0:"

    "][0:"private static final long serialVersionUID = 1234567L;"]

    LombokTestData data;
    boolean ok;
    String string;[0:"

    "][0:"public LombokTestData getData() {
        return data;
    }"][0:"

    "][0:"public void setData(LombokTestData data) {
        this.data = data;
    }"][0:"

    "][0:"public boolean isOk() {
        return ok;
    }"][0:"

    "][0:"public void setOk(boolean ok) {
        this.ok = ok;
    }"][0:"

    "][0:"public String getString() {
        return string;
    }"][0:"

    "][0:"public void setString(String string) {
        this.string = string;
    }"]

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    [0:"p"]ublic class LombokGetters {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"public LombokTestData getData() {
            return data;
        }"][0:"

        "][0:"public boolean isOk() {
            return ok;
        }"]

        public class LombokGettersPartial {
            LombokTestData data;
            [0:"b"]oolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"]
        }
    }

    [0:"p"]ublic class LombokSetters {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"public LombokTestData getData() {
            return data;
        }"][0:"

        "][0:"public void setData(LombokTestData data) {
            this.data = data;
        }"][0:"

        "][0:"public boolean isOk() {
            return ok;
        }"][0:"

        "][0:"public void setOk(boolean ok) {
            this.ok = ok;
        }"]

        public class LombokSettersPartial {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"public void setData(LombokTestData data) {
                this.data = data;
            }"]
        }

        public class LombokSettersFinalField {
            [0:"L"]ombokTestData data;
            final boolean ok = true;[0:"

            "][0:"public void setData(LombokTestData data) {
                this.data = data;
            }"]
        }
    }

    [0:"p"]ublic class ToStringFull {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"@Override
        public String toString() {
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
        }"]{[0:" +
                    "]{[0:" +
                    "]} [0:" +
                    "]{[0:" +
                    "]} [0:" +
                    "]{[0:" +
                    "]}}

        public class ToStringPartial {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"@Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }"]{[0:" +
                        "]{[0:" +
                        "]} [0:" +
                        "]{[0:" +
                        "]}}
        }

        public class ToStringPartial2 {
            [0:"L"]ombokTestData data;
            boolean ok;
            String string;[0:"

            "][0:"@Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }"]{[0:" +
                        "]{[0:" +
                        "]} [0:" +
                        "]{[0:" +
                        "]}}
        }
    }

    [0:"p"]ublic class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }"][0:"

        "][0:"@Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }"]

        public class EqualsAndHashCodePartial {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }"]
        }

        public class EqualsAndHashCodePartialTwo {
            [0:"L"]ombokTestData data;
            [0:"b"]oolean ok;
            String string;[0:"

            "]{[0:"

            "]}[0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }"]{[0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }"]}[0:"

            "]{[0:"

            "]}[0:"@Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]{[0:"@Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]}
        }
    }

    [0:"p"]ublic class EqualsFull {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }"]

        public class EqualsPartial {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }"]
        }

        public class EqualsPartialTwo {
            [0:"L"]ombokTestData data;
            [0:"b"]oolean ok;
            String string;[0:"

            "]{[0:"

            "]}[0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }"]{[0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }"]}
        }
    }

    [0:"p"]ublic class HashCodeFull {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"@Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }"]

        public class HashCodePartial {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"@Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }"]
        }

        public class HashCodePartialTwo {
            [0:"L"]ombokTestData data;
            [0:"b"]oolean ok;
            String string;[0:"

            "]{[0:"

            "]}[0:"@Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]{[0:"@Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]}
        }
    }

    [0:"p"]ublic class DataFull {
        LombokTestData data;
        boolean ok;[0:"

        "][0:"public LombokTestData getData() {
            return data;
        }"][0:"

        "][0:"public void setData(LombokTestData data) {
            this.data = data;
        }"][0:"

        "][0:"public boolean isOk() {
            return ok;
        }"][0:"

        "][0:"public void setOk(boolean ok) {
            this.ok = ok;
        }"][0:"

        "][0:"@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }"][0:"

        "][0:"@Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
        }"][0:"

        "][0:"@Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }"]

        [0:"p"]ublic class DataWithoutToString {
            LombokTestData data;
            boolean ok;[0:"

            "][0:"public LombokTestData getData() {
                return data;
            }"][0:"

            "][0:"public void setData(LombokTestData data) {
                this.data = data;
            }"][0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }"]

        }

        [0:"p"]ublic class DataWithPartialGetters {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"public LombokTestData getData() {
                return data;
            }"][0:"

            "][0:"public void setData(LombokTestData data) {
                this.data = data;
            }"][0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }"][0:"

            "][0:"@Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }"]
        }

        [0:"p"]ublic class DataWithPartialSetters {
            [0:"L"]ombokTestData data;
            boolean ok;[0:"

            "][0:"public LombokTestData getData() {
                return data;
            }"][0:"

            "][0:"public void setData(LombokTestData data) {
                this.data = data;
            }"][0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }"][0:"

            "][0:"@Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }"]
        }
    }

    public class FoldOn {
        [0:"p"]ublic class FoldOnPublic {
            boolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"]
        }

        [0:"c"]lass FoldOnClass {
            boolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"]
        }

        @SuppressWarnings("ALL")
        [0:"c"]lass FoldOnWithAnnotation {
            boolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"]
        }
    }

    public class DirtyLombokGetters {
        [0:"b"]oolean dirty;
        [0:"p"]rivate boolean dirty2;[0:"

        "][0:"public boolean isDirty() {
            return dirty2;
        }"][0:"

        "][0:"public boolean isDirty2() {
            return dirty;
        }"]

        [0:"p"]ublic class DirtyData {
            [0:"b"]oolean dirty;
            [0:"p"]rivate boolean ok;[0:"

            "][0:"public boolean isDirty() {
                return !dirty;
            }"][0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]
        }

        public class DirtySingle {
            [0:"b"]oolean dirty;
            [0:"b"]oolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"public boolean isDirty() {
                return dirty2;
            }"]
        }
    }

    public class SupportedDirtyLombokGetters {
        [0:"p"]rivate List<String> wrapper;
        [0:"p"]rivate List<String> wrapperWrongRef;
        [0:"p"]rivate List<String> wrapperDeeplyHiddenRef;

        [0:"p"]rivate List<String> localMethodWrappedList;
        [0:"p"]rivate List<String> thisLocalMethodWrappedList;
        [0:"p"]rivate List<String> lazyLoadedList;
        [0:"p"]rivate List<String> oneLineLazyLoadedList;
        [0:"p"]rivate List<String> defensiveCopyList;[0:"

        "][0:"public List<String> getWrapper() {
            return Collections.unmodifiableList(wrapper);
        }"][0:"

        "][0:"public List<String> getWrapperDeeplyHiddenRef() {
            if (wrapper != null) {
                try {
                    return wrapperDeeplyHiddenRef;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }"][0:"

        "][0:"public List<String> getWrapperWrongRef() {
            return Collections.unmodifiableList(wrapper);
        }"][0:"

        "][0:"public List<String> getLocalMethodWrappedList() {
            return localWrap(localMethodWrappedList);
        }"][0:"

        "][0:"public List<String> getThisLocalMethodWrappedList() {
            return this.localWrap(thisLocalMethodWrappedList);
        }"][0:"

        "][0:"public List<String> getLazyLoadedList() {
            if (lazyLoadedList == null) {
                lazyLoadedList = new ArrayList<>();
            }
            return lazyLoadedList;
        }"][0:"

        "][0:"public List<String> getDefensiveCopyList() {
            return new ArrayList<>(defensiveCopyList);
        }"][0:"

        "][0:"public List<String> getOneLineLazyLoadedList() {
            if (oneLineLazyLoadedList == null) oneLineLazyLoadedList = new ArrayList<>();
            return oneLineLazyLoadedList;
        }"]

        private List<String> localWrap(List<String> list) {
            return null;
        }
    }

    public class DirtyLombokSetters {
        [0:"b"]oolean dirty;
        [0:"p"]rivate boolean dirty2;
        [0:"b"]oolean withoutThis;[0:"

        "][0:"public void setDirty(boolean dirty) {
            this.dirty2 = dirty;
        }"][0:"

        "][0:"public void setDirty2(boolean dirty2) {
            this.dirty = dirty2;
        }"][0:"

        "][0:"public void setWithoutThis(boolean withoutThiss) {
            withoutThis = withoutThiss;
        }"]

        [0:"p"]ublic class DirtyData {
            [0:"b"]oolean dirty;
            [0:"p"]rivate boolean ok;[0:"

            "][0:"public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }"][0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"][0:"

            "][0:"public boolean isDirty() {
                return dirty;
            }"][0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]
        }

        public class DirtySingle {
            [0:"b"]oolean dirty;
            [0:"b"]oolean ok;[0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"][0:"

            "][0:"public void setDirty(boolean dirty) {
                this.ok = dirty;
            }"]
        }
    }

    public class LogAnnotation {
        [0:"p"]ublic class LogJava {[0:"
            "][0:"Logger log = Logger.getLogger("LogAnnotation.class");"]
        }

        [0:"p"]ublic class LogJava2 {[0:"
            "][0:"Logger log = Logger.getLogger("LogAnnotation.class");"]
        }

        [0:"p"]ublic class LogDiffrentFieldName {[0:"
            "][0:"public static final Logger logger = Logger.getLogger("LogAnnotation.class");"]
        }

        [0:"p"]ublic class LogCustomNameDeprecated {[0:"
            "][0:"@Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");"]
        }

        [0:"p"]ublic class TripleLogJava {[0:"
            "][0:"Logger log = Logger.getLogger("LogAnnotation.class");"][0:"
            "][0:"public static final Logger log2 = Logger.getLogger("LogAnnotation.class");"][0:"
            "][0:"static Logger log3 = Logger.getLogger("LogAnnotation.class");"]
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        [0:"p"]ublic class NoArgsConstructor {[0:"
            "][0:"public NoArgsConstructor() {
            }"]
        }
        [0:"c"]lass NoArgsConstructorPrivate {[0:"
            "][0:"private NoArgsConstructorPrivate() {
            }"]
        }
        [0:"p"]ublic class NoArgsConstructorCommentBeforeSuper {[0:"
            "][0:"public NoArgsConstructorCommentBeforeSuper() {
                // comment
                super();
            }"]
        }
        [0:"p"]ublic class NoArgsConstructorCommentAfterSuper {[0:"
            "][0:"public NoArgsConstructorCommentAfterSuper() {
                super();
                // comment
            }"]
        }
        [0:"p"]ublic class ProtectedNoArgsConstructorComment {[0:"
            "][0:"protected ProtectedNoArgsConstructorComment() {
                // comment
            }"]
        }
        // comment hidden as it is common and adds no meaningful value
        [0:"p"]ublic class PrivateNoArgsConstructorComment {[0:"
            "][0:"private PrivateNoArgsConstructorComment() {
                // comment
            }"]
        }
        [0:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[0:"
            "][0:"public NoArgsConstructorSuper() {
                super();
            }"]
        }
        public class NoArgsConstructorSuperParent extends Parent {
            public NoArgsConstructorSuperParent() {
                super(null);
            }
        }

    }

    public class AllArgsConstructorAnnotation {
        [0:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[0:"
            "][0:"public AllArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
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

        [0:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[0:"
            "][0:"public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
        }

        [0:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[0:"
            "][0:"private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [0:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[0:"
            "][0:"protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [0:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"

            "][0:"public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
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

        [0:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"

            "][0:"public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
        }

        [0:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"

            "][0:"private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]

            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [0:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"

            "][0:"protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]
        }
    }

    public class ValueAnnotation {
        [0:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"
            "][0:"public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"][0:"
            "][0:"public String getField1() {
                return field1;
            }"][0:"
            "][0:"public int getField2() {
                return field2;
            }"][0:"
            "][0:"public boolean isField3() {
                return field3;
            }"][0:"
            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }"][0:"
            "][0:"@Override
            public int hashCode() {
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }"][0:"

            "][0:"@Override
            public String toString() {
                return "ValueArgs{" +
                        "field1='" + field1 + '\'' +
                        ", field2=" + field2 +
                        ", field3=" + field3 +
                        '}';
            }"]{[0:" +
                        "]{[0:" +
                        "]} [0:" +
                        "]{[0:" +
                        "]} [0:" +
                        "]{[0:" +
                        "]} [0:" +
                        "]{[0:" +
                        "]}}
        }
        [0:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"
            "][0:"public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"][0:"
            "][0:"public String getField1() {
                return field1;
            }"][0:"
            "][0:"public int getField2() {
                return field2;
            }"][0:"
            "][0:"public boolean isField3() {
                return field3;
            }"][0:"
            "][0:"@Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }"]
        }
        [0:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[0:"
            "][0:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"][0:"
            "][0:"public String getField1() {
                return field1;
            }"][0:"
            "][0:"public int getField2() {
                return field2;
            }"][0:"
            "][0:"public boolean isField3() {
                return field3;
            }"]
        }
    }
    class SingleField {
        [0:"p"]ublic static class AllArgs {
            private String field1;[0:"
            "][0:"public AllArgs(String field1) {
                this.field1 = field1;
            }"]
        }
        [0:"p"]ublic static class ReqArgs {
            private final String field1;[0:"
            "][0:"public ReqArgs(String field1) {
                this.field1 = field1;
            }"]
        }
        [0:"p"]ublic static class Value {
            private final String field1;[0:"
            "][0:"public Value(String field1) {
                this.field1 = field1;
            }"][0:"
            "][0:"public String getField1() {
                return field1;
            }"][0:"
            "][0:"@Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Value)) return false;

                Value value = (Value) o;
                return Objects.equals(field1, value.field1);
            }"][0:"
            "][0:"@Override
            public int hashCode() {
                return Objects.hashCode(field1);
            }"]
        }
        [0:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[0:"
            "][0:"public ValueWithoutEqualsAndHashCode(String field1) {
                this.field1 = field1;
            }"][0:"
            "][0:"public String getField1() {
                return field1;
            }"]
        }
        class Modifers {
            [0:"s"]tatic class AllArgsDefault {
                private String field1;[0:"
                "][0:"AllArgsDefault(String field1) {
                    this.field1 = field1;
                }"]
            }
            [0:"s"]tatic class AllArgsPrivate {
                private String field1;[0:"
                "][0:"private AllArgsPrivate(String field1) {
                    this.field1 = field1;
                }"]
            }
            [0:"s"]tatic class AllArgsProteced {
                private String field1;[0:"
                "][0:"protected AllArgsProteced(String field1) {
                    this.field1 = field1;
                }"]
            }
        }
    }

    class FieldLevelData {
        [0:"p"]rivate String name;
        private String ignored;[0:"

        "][0:"public String getName() {
            return name;
        }"][0:"

        "][0:"public void setName(String name) {
            this.name = name;
        }"][0:"

        "][0:"@Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }"][0:"

        "][0:"@Override
        public int hashCode() {
            return Objects.hashCode(name);
        }"]
    }

    class FieldLevelValue {
        [0:"p"]rivate final String name = "1";
        private String ignored;[0:"

        "][0:"public String getName() {
            return name;
        }"][0:"

        "][0:"@Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }"][0:"

        "][0:"@Override
        public int hashCode() {
            return Objects.hashCode(name);
        }"]
    }
    class FieldLevelNotFinalNotValue {
        [0:"p"]rivate String name;
        private String ignored;[0:"

        "][0:"public String getName() {
            return name;
        }"][0:"

        "][0:"@Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }"][0:"

        "][0:"@Override
        public int hashCode() {
            return Objects.hashCode(name);
        }"]
    }

    [0:"c"]lass ClassWithBuilder {
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
        [0:"p"]ublic class FiveConstructors {
            [0:"p"]rivate int field1;
            [0:"p"]rivate String field2;
            [0:"p"]rivate double field3;
            private boolean field4;[0:"
            "][0:"public FiveConstructors() {
            }"][0:"
            "][0:"public FiveConstructors(int field1) {
                this.field1 = field1;
            }"][0:"
            "]{[0:"
            "]}[0:"public FiveConstructors(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]{[0:"public FiveConstructors(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]}[0:"
            "]{[0:"
            "]{[0:"
            "]}}[0:"public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]{[0:"public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]{[0:"public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]}}[0:"
            "][0:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }"]
        }

        [0:"p"]ublic class FiveConstructorsMod {
            [0:"p"]rivate int field1;
            [0:"p"]rivate String field2;
            [0:"p"]rivate double field3;
            private boolean field4;[0:"

            "][0:"public FiveConstructorsMod() {
            }"][0:"

            "][0:"private FiveConstructorsMod(int field1) {
                this.field1 = field1;
            }"][0:"

            "]{[0:"

            "]}[0:"FiveConstructorsMod(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]{[0:"FiveConstructorsMod(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]}[0:"

            "]{[0:"

            "]{[0:"

            "]}}[0:"protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]{[0:"protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]{[0:"protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }"]}}[0:"

            "][0:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }"]
        }

        class SingleConstructor {
            [0:"p"]rivate final int field1;
            private int field2;[0:"
            "][0:"SingleConstructor(int field1) {
                this.field1 = field1;
            }"]
        }

        private class SingleDoubleConstructor {
            [0:"p"]rivate final int field1;
            [0:"p"]rivate final int field2;
            private int field3;[0:"

            "][0:"private SingleDoubleConstructor(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]
        }

        public class SingleConstructorNoMod {
            [0:"p"]rivate final int field1;
            private int field2;[0:"
            "][0:"public SingleConstructorNoMod(int field1) {
                this.field1 = field1;
            }"]
        }

        public class SingleDoubleConstructorNoMod {
            [0:"p"]rivate final int field1;
            [0:"p"]rivate final int field2;
            private int field3;[0:"

            "][0:"public SingleDoubleConstructorNoMod(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }"]
        }

    }

    [0:"c"]lass Builders {

        class FirstBuilder {

        }

        class SecondBuilder {

        }

        class BuildersBuilder {

        }

    }

}
