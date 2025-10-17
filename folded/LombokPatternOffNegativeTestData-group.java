package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombokPatternOff()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.processor.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokPatternOffTestData()}
 */
@SuppressWarnings("ALL")
[0:"p"]ublic class LombokPatternOffNegativeTestData {[0:"\n\n    "][0:"private static final long serialVersionUID = 1234567L;"]

    LombokPatternOffNegativeTestData data;
    boolean ok;
    String string;[0:"\n\n    "][0:"public LombokPatternOffNegativeTestData getData() {\n        return data;\n    }"][0:"\n\n    "][0:"public void setData(LombokPatternOffNegativeTestData data) {\n        this.data = data;\n    }"][0:"\n\n    "][0:"public boolean isOk() {\n        return ok;\n    }"][0:"\n\n    "][0:"public void setOk(boolean ok) {\n        this.ok = ok;\n    }"][0:"\n\n    "][0:"public String getString() {\n        return string;\n    }"][0:"\n\n    "][0:"public void setString(String string) {\n        this.string = string;\n    }"]

    public Optional<LombokPatternOffNegativeTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    [1:"p"]ublic class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[1:"\n\n        "][1:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][1:"\n\n        "][1:"public boolean isOk() {\n            return ok;\n        }"]

        public class LombokGettersPartial {
            LombokPatternOffNegativeTestData data;
            [2:"b"]oolean ok;[2:"\n\n            "][2:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    [3:"p"]ublic class LombokSetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[3:"\n\n        "][3:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][3:"\n\n        "][3:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"][3:"\n\n        "][3:"public boolean isOk() {\n            return ok;\n        }"][3:"\n\n        "][3:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]

        public class LombokSettersPartial {
            [4:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[4:"\n\n            "][4:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]
        }

        public class LombokSettersFinalField {
            [5:"L"]ombokPatternOffNegativeTestData data;
            final boolean ok = true;[5:"\n\n            "][5:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]
        }
    }

    [6:"p"]ublic class ToStringFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[6:"\n\n        "][6:"@Override\n        public String toString() {\n            return \"ToStringFull{\" +\n                    \"data=\" + data +\n                    \", ok=\" + ok +\n                    '}';\n        }"]+\n                    "]", ok=" + ok[7:" +\n                    "][8:" +\n                    "]'}';
        }

        public class ToStringPartial {
            [9:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[9:"\n\n            "][9:"@Override\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]0:" +\n                        "][11:" +\n                        "]'}';
            }
        }

        public class ToStringPartial2 {
            [12:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;
            String string;[12:"\n\n            "][12:"@Override\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]3:" +\n                        "][14:" +\n                        "]'}';
            }
        }
    }

    [15:"p"]ublic class EqualsAndHashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[15:"\n\n        "][15:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"][15:"\n\n        "][15:"@Override\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]

        public class EqualsAndHashCodePartial {
            [16:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[16:"\n\n            "][16:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"][16:"\n\n            "][16:"@Override\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]
        }

        public class EqualsAndHashCodePartialTwo {
            [17:"L"]ombokPatternOffNegativeTestData data;
            [18:"b"]oolean ok;
            String string;[17:"\n\n            "][18:"\n\n            "][17:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][18:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][17:"\n\n            "][18:"\n\n            "][17:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][18:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }
    }

    [19:"p"]ublic class EqualsFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[19:"\n\n        "][19:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsFull that = (EqualsFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]

        public class EqualsPartial {
            [20:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[20:"\n\n            "][20:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartial that = (EqualsPartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"]
        }

        public class EqualsPartialTwo {
            [21:"L"]ombokPatternOffNegativeTestData data;
            [22:"b"]oolean ok;
            String string;[21:"\n\n            "][22:"\n\n            "][21:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][22:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"]
        }
    }

    [23:"p"]ublic class HashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[23:"\n\n        "][23:"@Override\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]

        public class HashCodePartial {
            [24:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[24:"\n\n            "][24:"@Override\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]
        }

        public class HashCodePartialTwo {
            [25:"L"]ombokPatternOffNegativeTestData data;
            [26:"b"]oolean ok;
            String string;[25:"\n\n            "][26:"\n\n            "][25:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][26:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }
    }

    [27:"p"]ublic class DataFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[27:"\n\n        "][27:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][27:"\n\n        "][27:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"][27:"\n\n        "][27:"public boolean isOk() {\n            return ok;\n        }"][27:"\n\n        "][27:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"][27:"\n\n        "][27:"@Override\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof DataFull)) return false;\n            DataFull dataFull = (DataFull) o;\n            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n        }"][27:"\n\n        "][27:"@Override\n        public int hashCode() {\n            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n        }"][27:"\n\n        "][27:"@Override\n        public String toString() {\n            return new ToStringBuilder(this)\n                    .append(\"data\", data)\n                    .append(\"ok\", ok)\n                    .toString();\n        }"]

        [28:"p"]ublic class DataWithoutToString {
            LombokPatternOffNegativeTestData data;
            boolean ok;[28:"\n\n            "][28:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][28:"\n\n            "][28:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][28:"\n\n            "][28:"public boolean isOk() {\n                return ok;\n            }"][28:"\n\n            "][28:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][28:"\n\n            "][28:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][28:"\n\n            "][28:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]

        }

        [29:"p"]ublic class DataWithPartialGetters {
            [30:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[30:"\n\n            "][30:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][29:"\n\n            "][29:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][29:"\n\n            "][29:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][29:"\n\n            "][29:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][29:"\n\n            "][29:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"][29:"\n\n            "][29:"@Override\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]
        }

        [31:"p"]ublic class DataWithPartialSetters {
            [32:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[31:"\n\n            "][31:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][32:"\n\n            "][32:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][31:"\n\n            "][31:"public boolean isOk() {\n                return ok;\n            }"][31:"\n\n            "][31:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"][31:"\n\n            "][31:"@Override\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"][31:"\n\n            "][31:"@Override\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]
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

    public class DirtyLombokSetters {
        [43:"b"]oolean dirty;
        [44:"p"]rivate boolean dirty2;[43:"\n\n        "][43:"public void setDirty(boolean dirty) {\n            this.dirty2 = dirty;\n        }"][44:"\n\n        "][44:"public void setDirty2(boolean dirty2) {\n            this.dirty = dirty2;\n        }"]

        [45:"p"]ublic class DirtyData {
            [46:"b"]oolean dirty;
            [47:"p"]rivate boolean ok;[46:"\n\n            "][46:"public void setDirty(boolean dirty) {\n                this.dirty = !dirty;\n            }"][47:"\n\n            "][47:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][45:"\n\n            "][45:"public boolean isDirty() {\n                return dirty;\n            }"][45:"\n\n            "][45:"public boolean isOk() {\n                return ok;\n            }"][45:"\n\n            "][45:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"][45:"\n\n            "][45:"@Override\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }

        public class DirtySingle {
            [48:"b"]oolean dirty;
            [49:"b"]oolean ok;[49:"\n\n            "][49:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][48:"\n\n            "][48:"public void setDirty(boolean dirty) {\n                this.ok = dirty;\n            }"]
        }
    }

    public class LogAnnotation {
        [50:"p"]ublic class LogJava {[50:"\n            "][50:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [51:"p"]ublic class LogJava2 {[51:"\n            "][51:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [52:"p"]ublic class LogDiffrentFieldName {[52:"\n            "][52:"public static final Logger logger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [53:"p"]ublic class LogCustomNameDeprecated {[53:"\n            "][53:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [54:"p"]ublic class TripleLogJava {[54:"\n            "][54:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][54:"\n            "][54:"public static final Logger log2 = Logger.getLogger(\"LogAnnotation.class\");"][54:"\n            "][54:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        [55:"p"]ublic class NoArgsConstructor {[55:"\n            "][55:"public NoArgsConstructor() {\n            }"]
        }
        [56:"c"]lass NoArgsConstructorPrivate {[56:"\n            "][56:"private NoArgsConstructorPrivate() {\n            }"]
        }
        [57:"p"]ublic class NoArgsConstructorSuperBefore {[57:"\n            "][57:"public NoArgsConstructorSuperBefore() {\n                // comment\n                super();\n            }"]
        }
        [58:"p"]ublic class NoArgsConstructorSuperAfter {[58:"\n            "][58:"public NoArgsConstructorSuperAfter() {\n                super();\n                // comment\n            }"]
        }

        [59:"p"]ublic class ProtectedNoArgsConstructorSuperAfter {[59:"\n            "][59:"protected ProtectedNoArgsConstructorSuperAfter() {\n                // comment\n            }"]
        }
        [60:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[60:"\n            "][60:"public NoArgsConstructorSuper() {\n                super();\n            }"]
        }
        public class NoArgsConstructorSuperParent extends Parent {
            public NoArgsConstructorSuperParent() {
                super(null);
            }
        }

    }

    public class AllArgsConstructorAnnotation {
        [61:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[61:"\n            "][61:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
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

        [62:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[62:"\n            "][62:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [63:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[63:"\n            "][63:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [64:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[64:"\n            "][64:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [65:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[65:"\n\n            "][65:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
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

        [66:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[66:"\n\n            "][66:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [67:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[67:"\n\n            "][67:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]

            public static StaticNameArgs of(String field1, int field2, boolean field3) {
                return new StaticNameArgs(field1, field2, field3);
            }
        }

        [68:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[68:"\n\n            "][68:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }

    public class ValueAnnotation {
        [69:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[69:"\n            "][69:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][69:"\n            "][69:"public String getField1() {\n                return field1;\n            }"][69:"\n            "][69:"public int getField2() {\n                return field2;\n            }"][69:"\n            "][69:"public boolean isField3() {\n                return field3;\n            }"][69:"\n            "][69:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                ValueArgs valueArgs = (ValueArgs) o;\n                if (field2 != valueArgs.field2) return false;\n                if (field3 != valueArgs.field3) return false;\n                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;\n            }"][69:"\n            "][69:"@Override\n            public int hashCode() {\n                int result = field1 != null ? field1.hashCode() : 0;\n                result = 31 * result + field2;\n                result = 31 * result + (field3 ? 1 : 0);\n                return result;\n            }"][69:"\n\n            "][69:"@Override\n            public String toString() {\n                return \"ValueArgs{\" +\n                        \"field1='\" + field1 + '\\'' +\n                        \", field2=\" + field2 +\n                        \", field3=\" + field3 +\n                        '}';\n            }"]+\n                        "][71:" +\n                        "]", field3=" + field3[70:" +\n                        "][71:" +\n                        "]'}';
            }
        }
        [72:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[72:"\n            "][72:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][72:"\n            "][72:"public String getField1() {\n                return field1;\n            }"][72:"\n            "][72:"public int getField2() {\n                return field2;\n            }"][72:"\n            "][72:"public boolean isField3() {\n                return field3;\n            }"][72:"\n            "][72:"@Override\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof ValueArgsSuper)) return false;\n\n                ValueArgsSuper that = (ValueArgsSuper) o;\n                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);\n            }"][72:"\n\n            "][72:"@Override\n            public int hashCode() {\n                int result = Objects.hashCode(field1);\n                result = 31 * result + field2;\n                result = 31 * result + Boolean.hashCode(field3);\n                return result;\n            }"]
        }
        [73:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[73:"\n            "][73:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][73:"\n            "][73:"public String getField1() {\n                return field1;\n            }"][73:"\n            "][73:"public int getField2() {\n                return field2;\n            }"][73:"\n            "][73:"public boolean isField3() {\n                return field3;\n            }"]
        }
    }
    class SingleField {
        [74:"p"]ublic static class AllArgs {
            private String field1;[74:"\n            "][74:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [75:"p"]ublic static class ReqArgs {
            private final String field1;[75:"\n            "][75:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [76:"p"]ublic static class Value {
            private final String field1;[76:"\n            "][76:"public Value(String field1) {\n                this.field1 = field1;\n            }"][76:"\n            "][76:"public String getField1() {\n                return field1;\n            }"][76:"\n            "][76:"@Override\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof Value)) return false;\n\n                Value value = (Value) o;\n                return Objects.equals(field1, value.field1);\n            }"][76:"\n            "][76:"@Override\n            public int hashCode() {\n                return Objects.hashCode(field1);\n            }"]
        }
        [77:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[77:"\n            "][77:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"][77:"\n            "][77:"public String getField1() {\n                return field1;\n            }"]
        }
        class Modifers {
            [78:"s"]tatic class AllArgsDefault {
                private String field1;[78:"\n                "][78:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [79:"s"]tatic class AllArgsPrivate {
                private String field1;[79:"\n                "][79:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [80:"s"]tatic class AllArgsProteced {
                private String field1;[80:"\n                "][80:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]
            }
        }
    }

    class FieldLevelData {
        [81:"p"]rivate String name;
        private String ignored;[81:"\n\n        "][81:"public String getName() {\n            return name;\n        }"][81:"\n\n        "][81:"public void setName(String name) {\n            this.name = name;\n        }"][81:"\n\n        "][81:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][81:"\n\n        "][81:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }

    class FieldLevelValue {
        [82:"p"]rivate final String name = "1";
        private String ignored;[82:"\n\n        "][82:"public String getName() {\n            return name;\n        }"][82:"\n\n        "][82:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][82:"\n\n        "][82:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }
    class FieldLevelNotFinalNotValue {
        [83:"p"]rivate String name;
        private String ignored;[83:"\n\n        "][83:"public String getName() {\n            return name;\n        }"][83:"\n\n        "][83:"@Override\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"][83:"\n\n        "][83:"@Override\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]
    }

    [84:"c"]lass ClassWithBuilder {
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
        [85:"p"]ublic class FiveConstructors {
            [86:"p"]rivate int field1;
            [87:"p"]rivate String field2;
            [88:"p"]rivate double field3;
            private boolean field4;[85:"\n            "][85:"public FiveConstructors() {\n            }"][86:"\n            "][86:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"][86:"\n            "][87:"\n            "][86:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][87:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][86:"\n            "][87:"\n            "][88:"\n            "][86:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][87:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][88:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][85:"\n            "][85:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        [89:"p"]ublic class FiveConstructorsMod {
            [90:"p"]rivate int field1;
            [91:"p"]rivate String field2;
            [92:"p"]rivate double field3;
            private boolean field4;[89:"\n            "][89:"public FiveConstructorsMod() {\n            }"][90:"\n            "][90:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"][90:"\n            "][91:"\n            "][90:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][91:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][90:"\n            "][91:"\n            "][92:"\n            "][90:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][91:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][92:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][89:"\n            "][89:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }
    }

}
