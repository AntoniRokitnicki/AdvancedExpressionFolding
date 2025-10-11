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
[0:"p"]ublic class LombokTestData {[0:"\n\n    "][0:"private static final long serialVersionUID = 1234567L;"]

    LombokTestData data;
    boolean ok;
    String string;[0:"\n\n    "][0:"public LombokTestData getData() {\n        return data;\n    }"][0:"\n\n    "][0:"public void setData(LombokTestData data) {\n        this.data = data;\n    }"][0:"\n\n    "][0:"public boolean isOk() {\n        return ok;\n    }"][0:"\n\n    "][0:"public void setOk(boolean ok) {\n        this.ok = ok;\n    }"][0:"\n\n    "][0:"public String getString() {\n        return string;\n    }"][0:"\n\n    "][0:"public void setString(String string) {\n        this.string = string;\n    }"]

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    [1:"p"]ublic class LombokGetters {
        LombokTestData data;
        boolean ok;[1:"\n\n        "][1:"public LombokTestData getData() {\n            return data;\n        }"][1:"\n\n        "][1:"public boolean isOk() {\n            return ok;\n        }"]

        public class LombokGettersPartial {
            LombokTestData data;
            [2:"b"]oolean ok;[2:"\n\n            "][2:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    [3:"p"]ublic class LombokSetters {
        LombokTestData data;
        boolean ok;[3:"\n\n        "][3:"public LombokTestData getData() {\n            return data;\n        }"][3:"\n\n        "][3:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"][3:"\n\n        "][3:"public boolean isOk() {\n            return ok;\n        }"][3:"\n\n        "][3:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]

        public class LombokSettersPartial {
            [4:"L"]ombokTestData data;
            boolean ok;[4:"\n\n            "][4:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]
        }

        public class LombokSettersFinalField {
            [5:"L"]ombokTestData data;
            final boolean ok = true;[5:"\n\n            "][5:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]
        }
    }

    [6:"p"]ublic class ToStringFull {
        LombokTestData data;
        boolean ok;[6:"\n\n        "][6:"@Override\n        public String toString() {\n            return \"ToStringFull{\" +\n                    \"data=\" + data +\n                    \", ok=\" + ok +\n                    '}';\n        }"]+\n                    "]", ok=" + ok[7:" +\n                    "][8:" +\n                    "]'}';
        }

        public class ToStringPartial {
            [9:"L"]ombokTestData data;
            boolean ok;[9:"\n\n            "][9:"@Override\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]0:" +\n                        "][11:" +\n                        "]'}';
            }
        }

        public class ToStringPartial2 {
            [12:"L"]ombokTestData data;
            boolean ok;
            String string;[12:"\n\n            "][12:"@Override\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]3:" +\n                        "][14:" +\n                        "]'}';
            }
        }
    }

    [15:"p"]ublic class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;[15:"\n\n        "][15:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"][15:"\n\n        "][15:"@Override\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]

        public class EqualsAndHashCodePartial {
            [16:"L"]ombokTestData data;
            boolean ok;[16:"\n\n            "][16:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"][16:"\n\n            "][16:"@Override\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]
        }

        public class EqualsAndHashCodePartialTwo {
            [17:"L"]ombokTestData data;
            [18:"b"]oolean ok;
            String string;[17:"\n\n            "][18:"\n\n            "][17:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][18:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][17:"\n\n            "][18:"\n\n            "][17:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][18:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }
    }

    [19:"p"]ublic class EqualsFull {
        LombokTestData data;
        boolean ok;[19:"\n\n        "][19:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsFull that = (EqualsFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]

        public class EqualsPartial {
            [20:"L"]ombokTestData data;
            boolean ok;[20:"\n\n            "][20:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartial that = (EqualsPartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"]
        }

        public class EqualsPartialTwo {
            [21:"L"]ombokTestData data;
            [22:"b"]oolean ok;
            String string;[21:"\n\n            "][22:"\n\n            "][21:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][22:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"]
        }
    }

    [23:"p"]ublic class HashCodeFull {
        LombokTestData data;
        boolean ok;[23:"\n\n        "][23:"@Override\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]

        public class HashCodePartial {
            [24:"L"]ombokTestData data;
            boolean ok;[24:"\n\n            "][24:"@Override\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]
        }

        public class HashCodePartialTwo {
            [25:"L"]ombokTestData data;
            [26:"b"]oolean ok;
            String string;[25:"\n\n            "][26:"\n\n            "][25:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][26:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }
    }

    [27:"p"]ublic class DataFull {
        LombokTestData data;
        boolean ok;[27:"\n\n        "][27:"public LombokTestData getData() {\n            return data;\n        }"][27:"\n\n        "][27:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"][27:"\n\n        "][27:"public boolean isOk() {\n            return ok;\n        }"][27:"\n\n        "][27:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"][27:"\n\n        "][27:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof DataFull)) return false;\n            DataFull dataFull = (DataFull) o;\n            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n        }"][27:"\n\n        "][27:"@Override\n        public int hashCode() {\n            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n        }"][27:"\n\n        "][27:"@Override\n        public String toString() {\n            return new ToStringBuilder(this)\n                    .append(\"data\", data)\n                    .append(\"ok\", ok)\n                    .toString();\n        }"]

        [28:"p"]ublic class DataWithoutToString {
            LombokTestData data;
            boolean ok;[28:"\n\n            "][28:"public LombokTestData getData() {\n                return data;\n            }"][28:"\n\n            "][28:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][28:"\n\n            "][28:"public boolean isOk() {\n                return ok;\n            }"][28:"\n\n            "][28:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][28:"\n\n            "][28:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][28:"\n\n            "][28:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]

        }

        [29:"p"]ublic class DataWithPartialGetters {
            [30:"L"]ombokTestData data;
            boolean ok;[30:"\n\n            "][30:"public LombokTestData getData() {\n                return data;\n            }"][29:"\n\n            "][29:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][29:"\n\n            "][29:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][29:"\n\n            "][29:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][29:"\n\n            "][29:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"][29:"\n\n            "][29:"@Override\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]
        }

        [31:"p"]ublic class DataWithPartialSetters {
            [32:"L"]ombokTestData data;
            boolean ok;[31:"\n\n            "][31:"public LombokTestData getData() {\n                return data;\n            }"][32:"\n\n            "][32:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][31:"\n\n            "][31:"public boolean isOk() {\n                return ok;\n            }"][31:"\n\n            "][31:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][31:"\n\n            "][31:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"][31:"\n\n            "][31:"@Override\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]
        }
    }

    public class FoldOn {
        [33:"p"]ublic class FoldOnPublic {
            boolean ok;[33:"\n\n            "][33:"public boolean isOk() {\n                return ok;\n            }"]
        }

        [34:"c"]lass FoldOnClass {
            boolean ok;[34:"\n\n            "][34:"public boolean isOk() {\n                return ok;\n            }"]
        }

        @SuppressWarnings("ALL")
        [35:"c"]lass FoldOnWithAnnotation {
            boolean ok;[35:"\n\n            "][35:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    public class DirtyLombokGetters {
        [36:"b"]oolean dirty;
        [37:"p"]rivate boolean dirty2;[36:"\n\n        "][36:"public boolean isDirty() {\n            return dirty2;\n        }"][37:"\n\n        "][37:"public boolean isDirty2() {\n            return dirty;\n        }"]

        [38:"p"]ublic class DirtyData {
            [39:"b"]oolean dirty;
            [40:"p"]rivate boolean ok;[39:"\n\n            "][39:"public boolean isDirty() {\n                return !dirty;\n            }"][40:"\n\n            "][40:"public boolean isOk() {\n                return ok;\n            }"][38:"\n\n            "][38:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"][38:"\n\n            "][38:"@Override\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }

        public class DirtySingle {
            [41:"b"]oolean dirty;
            [42:"b"]oolean ok;[42:"\n\n            "][42:"public boolean isOk() {\n                return ok;\n            }"][41:"\n\n            "][41:"public boolean isDirty() {\n                return dirty2;\n            }"]
        }
    }

    public class SupportedDirtyLombokGetters {
        [43:"p"]rivate List<String> wrapper;
        [44:"p"]rivate List<String> wrapperWrongRef;
        [45:"p"]rivate List<String> wrapperDeeplyHiddenRef;

        [46:"p"]rivate List<String> localMethodWrappedList;
        [47:"p"]rivate List<String> thisLocalMethodWrappedList;
        [48:"p"]rivate List<String> lazyLoadedList;
        [49:"p"]rivate List<String> oneLineLazyLoadedList;
        [50:"p"]rivate List<String> defensiveCopyList;[43:"\n\n        "][43:"public List<String> getWrapper() {\n            return Collections.unmodifiableList(wrapper);\n        }"][45:"\n\n        "][45:"public List<String> getWrapperDeeplyHiddenRef() {\n            if (wrapper != null) {\n                try {\n                    return wrapperDeeplyHiddenRef;\n                } catch (Exception e) {\n                    throw new RuntimeException(e);\n                }\n            }\n            return null;\n        }"][44:"\n\n        "][44:"public List<String> getWrapperWrongRef() {\n            return Collections.unmodifiableList(wrapper);\n        }"][46:"\n\n        "][46:"public List<String> getLocalMethodWrappedList() {\n            return localWrap(localMethodWrappedList);\n        }"][47:"\n\n        "][47:"public List<String> getThisLocalMethodWrappedList() {\n            return this.localWrap(thisLocalMethodWrappedList);\n        }"][48:"\n\n        "][48:"public List<String> getLazyLoadedList() {\n            if (lazyLoadedList == null) {\n                lazyLoadedList = new ArrayList<>();\n            }\n            return lazyLoadedList;\n        }"][50:"\n\n        "][50:"public List<String> getDefensiveCopyList() {\n            return new ArrayList<>(defensiveCopyList);\n        }"][49:"\n\n        "][49:"public List<String> getOneLineLazyLoadedList() {\n            if (oneLineLazyLoadedList == null) oneLineLazyLoadedList = new ArrayList<>();\n            return oneLineLazyLoadedList;\n        }"]

        private List<String> localWrap(List<String> list) {
            return null;
        }
    }

    public class DirtyLombokSetters {
        [51:"b"]oolean dirty;
        [52:"p"]rivate boolean dirty2;
        [53:"b"]oolean withoutThis;[51:"\n\n        "][51:"public void setDirty(boolean dirty) {\n            this.dirty2 = dirty;\n        }"][52:"\n\n        "][52:"public void setDirty2(boolean dirty2) {\n            this.dirty = dirty2;\n        }"][53:"\n\n        "][53:"public void setWithoutThis(boolean withoutThiss) {\n            withoutThis = withoutThiss;\n        }"]

        [54:"p"]ublic class DirtyData {
            [55:"b"]oolean dirty;
            [56:"p"]rivate boolean ok;[55:"\n\n            "][55:"public void setDirty(boolean dirty) {\n                this.dirty = !dirty;\n            }"][56:"\n\n            "][56:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][54:"\n\n            "][54:"public boolean isDirty() {\n                return dirty;\n            }"][54:"\n\n            "][54:"public boolean isOk() {\n                return ok;\n            }"][54:"\n\n            "][54:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"][54:"\n\n            "][54:"@Override\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }

        public class DirtySingle {
            [57:"b"]oolean dirty;
            [58:"b"]oolean ok;[58:"\n\n            "][58:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][57:"\n\n            "][57:"public void setDirty(boolean dirty) {\n                this.ok = dirty;\n            }"]
        }
    }

    public class LogAnnotation {
        [59:"p"]ublic class LogJava {[59:"\n            "][59:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [60:"p"]ublic class LogJava2 {[60:"\n            "][60:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [61:"p"]ublic class LogDiffrentFieldName {[61:"\n            "][61:"public static final Logger logger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [62:"p"]ublic class LogCustomNameDeprecated {[62:"\n            "][62:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [63:"p"]ublic class TripleLogJava {[63:"\n            "][63:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][63:"\n            "][63:"public static final Logger log2 = Logger.getLogger(\"LogAnnotation.class\");"][63:"\n            "][63:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        [64:"p"]ublic class NoArgsConstructor {[64:"\n            "][64:"public NoArgsConstructor() {\n            }"]
        }
        [65:"c"]lass NoArgsConstructorPrivate {[65:"\n            "][65:"private NoArgsConstructorPrivate() {\n            }"]
        }
        [66:"p"]ublic class NoArgsConstructorCommentBeforeSuper {[66:"\n            "][66:"public NoArgsConstructorCommentBeforeSuper() {\n                // comment\n                super();\n            }"]
        }
        [67:"p"]ublic class NoArgsConstructorCommentAfterSuper {[67:"\n            "][67:"public NoArgsConstructorCommentAfterSuper() {\n                super();\n                // comment\n            }"]
        }
        [68:"p"]ublic class ProtectedNoArgsConstructorComment {[68:"\n            "][68:"protected ProtectedNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        // comment hidden as it is common and adds no meaningful value
        [69:"p"]ublic class PrivateNoArgsConstructorComment {[69:"\n            "][69:"private PrivateNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        [70:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[70:"\n            "][70:"public NoArgsConstructorSuper() {\n                super();\n            }"]
        }
        public class NoArgsConstructorSuperParent extends Parent {
            public NoArgsConstructorSuperParent() {
                super(null);
            }
        }

    }

    public class AllArgsConstructorAnnotation {
        [71:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[71:"\n            "][71:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
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

        [72:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[72:"\n            "][72:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [73:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[73:"\n            "][73:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [74:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[74:"\n            "][74:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [75:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[75:"\n\n            "][75:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
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

        [76:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[76:"\n\n            "][76:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [77:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[77:"\n\n            "][77:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]

            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [78:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[78:"\n\n            "][78:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }

    public class ValueAnnotation {
        [79:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[79:"\n            "][79:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][79:"\n            "][79:"public String getField1() {\n                return field1;\n            }"][79:"\n            "][79:"public int getField2() {\n                return field2;\n            }"][79:"\n            "][79:"public boolean isField3() {\n                return field3;\n            }"][79:"\n            "][79:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                ValueArgs valueArgs = (ValueArgs) o;\n                if (field2 != valueArgs.field2) return false;\n                if (field3 != valueArgs.field3) return false;\n                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;\n            }"][79:"\n            "][79:"@Override\n            public int hashCode() {\n                int result = field1 != null ? field1.hashCode() : 0;\n                result = 31 * result + field2;\n                result = 31 * result + (field3 ? 1 : 0);\n                return result;\n            }"][79:"\n\n            "][79:"@Override\n            public String toString() {\n                return \"ValueArgs{\" +\n                        \"field1='\" + field1 + '\\'' +\n                        \", field2=\" + field2 +\n                        \", field3=\" + field3 +\n                        '}';\n            }"]+\n                        "][81:" +\n                        "]", field3=" + field3[80:" +\n                        "][81:" +\n                        "]'}';
            }
        }
        [82:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[82:"\n            "][82:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][82:"\n            "][82:"public String getField1() {\n                return field1;\n            }"][82:"\n            "][82:"public int getField2() {\n                return field2;\n            }"][82:"\n            "][82:"public boolean isField3() {\n                return field3;\n            }"][82:"\n            "][82:"@Override\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof ValueArgsSuper)) return false;\n\n                ValueArgsSuper that = (ValueArgsSuper) o;\n                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);\n            }"][82:"\n\n            "][82:"@Override\n            public int hashCode() {\n                int result = Objects.hashCode(field1);\n                result = 31 * result + field2;\n                result = 31 * result + Boolean.hashCode(field3);\n                return result;\n            }"]
        }
        [83:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[83:"\n            "][83:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][83:"\n            "][83:"public String getField1() {\n                return field1;\n            }"][83:"\n            "][83:"public int getField2() {\n                return field2;\n            }"][83:"\n            "][83:"public boolean isField3() {\n                return field3;\n            }"]
        }
    }
    class SingleField {
        [84:"p"]ublic static class AllArgs {
            private String field1;[84:"\n            "][84:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [85:"p"]ublic static class ReqArgs {
            private final String field1;[85:"\n            "][85:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [86:"p"]ublic static class Value {
            private final String field1;[86:"\n            "][86:"public Value(String field1) {\n                this.field1 = field1;\n            }"][86:"\n            "][86:"public String getField1() {\n                return field1;\n            }"][86:"\n            "][86:"@Override\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof Value)) return false;\n\n                Value value = (Value) o;\n                return Objects.equals(field1, value.field1);\n            }"][86:"\n            "][86:"@Override\n            public int hashCode() {\n                return Objects.hashCode(field1);\n            }"]
        }
        [87:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[87:"\n            "][87:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"][87:"\n            "][87:"public String getField1() {\n                return field1;\n            }"]
        }
        class Modifers {
            [88:"s"]tatic class AllArgsDefault {
                private String field1;[88:"\n                "][88:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [89:"s"]tatic class AllArgsPrivate {
                private String field1;[89:"\n                "][89:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [90:"s"]tatic class AllArgsProteced {
                private String field1;[90:"\n                "][90:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]
            }
        }
    }

    class FieldLevelData {
        [91:"p"]rivate String name;
        private String ignored;[91:"\n\n        "][91:"public String getName() {\n            return name;\n        }"][91:"\n\n        "][91:"public void setName(String name) {\n            this.name = name;\n        }"][91:"\n\n        "][91:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][91:"\n\n        "][91:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }

    class FieldLevelValue {
        [92:"p"]rivate final String name = "1";
        private String ignored;[92:"\n\n        "][92:"public String getName() {\n            return name;\n        }"][92:"\n\n        "][92:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][92:"\n\n        "][92:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }
    class FieldLevelNotFinalNotValue {
        [93:"p"]rivate String name;
        private String ignored;[93:"\n\n        "][93:"public String getName() {\n            return name;\n        }"][93:"\n\n        "][93:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][93:"\n\n        "][93:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }

    [94:"c"]lass ClassWithBuilder {
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
        [95:"p"]ublic class FiveConstructors {
            [96:"p"]rivate int field1;
            [97:"p"]rivate String field2;
            [98:"p"]rivate double field3;
            private boolean field4;[95:"\n            "][95:"public FiveConstructors() {\n            }"][96:"\n            "][96:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"][96:"\n            "][97:"\n            "][96:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][97:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][96:"\n            "][97:"\n            "][98:"\n            "][96:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][97:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][98:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][95:"\n            "][95:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        [99:"p"]ublic class FiveConstructorsMod {
            [100:"p"]rivate int field1;
            [101:"p"]rivate String field2;
            [102:"p"]rivate double field3;
            private boolean field4;[99:"\n\n            "][99:"public FiveConstructorsMod() {\n            }"][100:"\n\n            "][100:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"][100:"\n\n            "][101:"\n\n            "][100:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][101:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][100:"\n\n            "][101:"\n\n            "][102:"\n\n            "][100:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][101:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][102:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][99:"\n\n            "][99:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        class SingleConstructor {
            [103:"p"]rivate final int field1;
            private int field2;[103:"\n            "][103:"SingleConstructor(int field1) {\n                this.field1 = field1;\n            }"]
        }

        private class SingleDoubleConstructor {
            [104:"p"]rivate final int field1;
            [104:"p"]rivate final int field2;
            private int field3;[104:"\n\n            "][104:"private SingleDoubleConstructor(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"]
        }

        public class SingleConstructorNoMod {
            [105:"p"]rivate final int field1;
            private int field2;[105:"\n            "][105:"public SingleConstructorNoMod(int field1) {\n                this.field1 = field1;\n            }"]
        }

        public class SingleDoubleConstructorNoMod {
            [106:"p"]rivate final int field1;
            [106:"p"]rivate final int field2;
            private int field3;[106:"\n\n            "][106:"public SingleDoubleConstructorNoMod(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"]
        }

    }

    [107:"c"]lass Builders {

        class FirstBuilder {

        }

        class SecondBuilder {

        }

        class BuildersBuilder {

        }

    }

}
