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

    public Optional<LombokPatternOffNegativeTestData> asOptional() {[20:"\n        "][20:"return"][20:" "][21:"Optional.ofNullable("]data[21:")"][20:";"][20:"\n    "]}

    [23:"p"]ublic class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[23:"\n\n        "][23:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][23:"\n\n        "][23:"public boolean isOk() {\n            return ok;\n        }"]

        public class LombokGettersPartial {
            LombokPatternOffNegativeTestData data;
            [26:"b"]oolean ok;[26:"\n\n            "][26:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    [28:"p"]ublic class LombokSetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[28:"\n\n        "][28:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][28:"\n\n        "][28:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"][28:"\n\n        "][28:"public boolean isOk() {\n            return ok;\n        }"][28:"\n\n        "][28:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]

        public class LombokSettersPartial {
            [41:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[41:"\n\n            "][41:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]
        }

        public class LombokSettersFinalField {
            [47:"L"]ombokPatternOffNegativeTestData data;
            final boolean ok = true;[47:"\n\n            "][47:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]
        }
    }

    [53:"p"]ublic class ToStringFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[53:"\n\n        "][54:"@Override"][54:"\n        "]public String toString() {
            return "ToStringFull{[55:"\" +\n                    \""]data=[55:"\" + "]data[55:" +\n                    \""], ok=[55:"\" + "]ok[55:" +\n                    "]'}'[55:";"]
        }

        public class ToStringPartial {
            [57:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[57:"\n\n            "][59:"@Override"][59:"\n            "]public String toString() {[58:"\n                "][58:"return"][58:" "]"ToStringPartial{[60:"\" +\n                        \""]data=[60:"\" + "]data[60:" +\n                        "]'}'[58:";"][58:"\n            "]}
        }

        public class ToStringPartial2 {
            [62:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;
            String string;[62:"\n\n            "][64:"@Override"][64:"\n            "]public String toString() {[63:"\n                "][63:"return"][63:" "]"ToStringPartial{[65:"\" +\n                        \""]data=[65:"\" + "]data[65:" +\n                        "]'}'[63:";"][63:"\n            "]}
        }
    }

    [67:"p"]ublic class EqualsAndHashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[67:"\n\n        "][68:"@Override"][68:"\n        "]public boolean equals(Object o) {
            if [69:"("]this == o[69:")"] return true;
            if [70:"("]o == null || [79:"getClass()"] != o.[80:"getClass()"][70:")"] return false;
            [71:"EqualsAndHashCodeFull"] that = [72:"(EqualsAndHashCodeFull) "]o;
            return ok == that.ok && ([73:"data != null ? "]data[73:"."]equals(that.data[73:")"][73:" : "]that.data == null);
        }[67:"\n\n        "][85:"@Override"][85:"\n        "]public int hashCode() {
            [86:"int"] result = ([87:"data != null ? "]data[87:"."]hashCode()[87:" : "]0);
            result = 31 * result[89:" + ("]ok ? 1 : 0[89:")"];
            return result;
        }

        public class EqualsAndHashCodePartial {
            [93:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[93:"\n\n            "][94:"@Override"][94:"\n            "]public boolean equals(Object o) {
                if [95:"("]this == o[95:")"] return true;
                if [96:"("]o == null || [103:"getClass()"] != o.[104:"getClass()"][96:")"] return false;
                [97:"EqualsAndHashCodePartial"] that = [98:"(EqualsAndHashCodePartial) "]o;
                return [99:"data != null ? "]data[99:"."]equals(that.data[99:")"][99:" : "]that.data == null;
            }[93:"\n\n            "][110:"@Override"][110:"\n            "]public int hashCode() {[109:"\n                "][109:"return"][109:" "][111:"data != null ? "]data[111:"."]hashCode()[111:" : "]0[109:";"][109:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            [113:"L"]ombokPatternOffNegativeTestData data;
            [114:"b"]oolean ok;
            String string;[113:"\n\n            "][115:"@Override"][115:"\n            "]public boolean equals(Object o) {
                if [116:"("]this == o[116:")"] return true;
                if [117:"("]o == null || [126:"getClass()"] != o.[127:"getClass()"][117:")"] return false;
                [118:"EqualsAndHashCodePartialTwo"] that = [119:"(EqualsAndHashCodePartialTwo) "]o;
                return ok == that.ok && ([120:"data != null ? "]data[120:"."]equals(that.data[120:")"][120:" : "]that.data == null);
            }[113:"\n\n            "][132:"@Override"][132:"\n            "]public int hashCode() {
                [133:"int"] result = ([134:"data != null ? "]data[134:"."]hashCode()[134:" : "]0);
                result = 31 * result[136:" + ("]ok ? 1 : 0[136:")"];
                return result;
            }
        }
    }

    [140:"p"]ublic class EqualsFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[140:"\n\n        "][141:"@Override"][141:"\n        "]public boolean equals(Object o) {
            if [142:"("]this == o[142:")"] return true;
            if [143:"("]o == null || [152:"getClass()"] != o.[153:"getClass()"][143:")"] return false;
            [144:"EqualsFull"] that = [145:"(EqualsFull) "]o;
            return ok == that.ok && ([146:"data != null ? "]data[146:"."]equals(that.data[146:")"][146:" : "]that.data == null);
        }

        public class EqualsPartial {
            [158:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[158:"\n\n            "][159:"@Override"][159:"\n            "]public boolean equals(Object o) {
                if [160:"("]this == o[160:")"] return true;
                if [161:"("]o == null || [168:"getClass()"] != o.[169:"getClass()"][161:")"] return false;
                [162:"EqualsPartial"] that = [163:"(EqualsPartial) "]o;
                return [164:"data != null ? "]data[164:"."]equals(that.data[164:")"][164:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            [174:"L"]ombokPatternOffNegativeTestData data;
            [175:"b"]oolean ok;
            String string;[174:"\n\n            "][176:"@Override"][176:"\n            "]public boolean equals(Object o) {
                if [177:"("]this == o[177:")"] return true;
                if [178:"("]o == null || [187:"getClass()"] != o.[188:"getClass()"][178:")"] return false;
                [179:"EqualsPartialTwo"] that = [180:"(EqualsPartialTwo) "]o;
                return ok == that.ok && ([181:"data != null ? "]data[181:"."]equals(that.data[181:")"][181:" : "]that.data == null);
            }
        }
    }

    [193:"p"]ublic class HashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[193:"\n\n        "][194:"@Override"][194:"\n        "]public int hashCode() {
            [195:"int"] result = ([196:"data != null ? "]data[196:"."]hashCode()[196:" : "]0);
            result = 31 * result[198:" + ("]ok ? 1 : 0[198:")"];
            return result;
        }

        public class HashCodePartial {
            [202:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[202:"\n\n            "][204:"@Override"][204:"\n            "]public int hashCode() {[203:"\n                "][203:"return"][203:" "][205:"data != null ? "]data[205:"."]hashCode()[205:" : "]0[203:";"][203:"\n            "]}
        }

        public class HashCodePartialTwo {
            [207:"L"]ombokPatternOffNegativeTestData data;
            [208:"b"]oolean ok;
            String string;[207:"\n\n            "][209:"@Override"][209:"\n            "]public int hashCode() {
                [210:"int"] result = ([211:"data != null ? "]data[211:"."]hashCode()[211:" : "]0);
                result = 31 * result[213:" + ("]ok ? 1 : 0[213:")"];
                return result;
            }
        }
    }

    [217:"p"]ublic class DataFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[217:"\n\n        "][217:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"][217:"\n\n        "][217:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"][217:"\n\n        "][217:"public boolean isOk() {\n            return ok;\n        }"][217:"\n\n        "][217:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"][217:"\n\n        "][230:"@Override"][230:"\n        "]public boolean equals(Object o) {
            if [231:"("]this == o[231:")"] return true;
            if [232:"("]!(o instanceof DataFull)[232:")"] return false;
            [233:"DataFull"] dataFull = [234:"(DataFull) "]o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[235:"isEquals()"];
        }[217:"\n\n        "][242:"@Override"][242:"\n        "]public int hashCode() {[241:"\n            "][241:"return"][241:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[241:";"][241:"\n        "]}[217:"\n\n        "][243:"@Override"][243:"\n        "]public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        [244:"p"]ublic class DataWithoutToString {
            LombokPatternOffNegativeTestData data;
            boolean ok;[244:"\n\n            "][244:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][244:"\n\n            "][244:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][244:"\n\n            "][244:"public boolean isOk() {\n                return ok;\n            }"][244:"\n\n            "][244:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][244:"\n\n            "][257:"@Override"][257:"\n            "]public boolean equals(Object o) {
                if [258:"("]this == o[258:")"] return true;
                if [259:"("]!(o instanceof LombokPatternOffNegativeTestData.DataFull)[259:")"] return false;
                [260:"LombokPatternOffNegativeTestData.DataFull"] dataFull = [261:"(LombokPatternOffNegativeTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[262:"isEquals()"];
            }[244:"\n\n            "][269:"@Override"][269:"\n            "]public int hashCode() {[268:"\n                "][268:"return"][268:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[268:";"][268:"\n            "]}

        }

        [270:"p"]ublic class DataWithPartialGetters {
            [271:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[271:"\n\n            "][271:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][270:"\n\n            "][270:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][270:"\n\n            "][270:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][270:"\n\n            "][283:"@Override"][283:"\n            "]public boolean equals(Object o) {
                if [284:"("]this == o[284:")"] return true;
                if [285:"("]!(o instanceof LombokPatternOffNegativeTestData.DataFull)[285:")"] return false;
                [286:"LombokPatternOffNegativeTestData.DataFull"] dataFull = [287:"(LombokPatternOffNegativeTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[288:"isEquals()"];
            }[270:"\n\n            "][295:"@Override"][295:"\n            "]public int hashCode() {[294:"\n                "][294:"return"][294:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[294:";"][294:"\n            "]}[270:"\n\n            "][296:"@Override"][296:"\n            "]public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        [297:"p"]ublic class DataWithPartialSetters {
            [298:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[297:"\n\n            "][297:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"][298:"\n\n            "][298:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][297:"\n\n            "][297:"public boolean isOk() {\n                return ok;\n            }"][297:"\n\n            "][306:"@Override"][306:"\n            "]public boolean equals(Object o) {
                if [307:"("]this == o[307:")"] return true;
                if [308:"("]!(o instanceof LombokPatternOffNegativeTestData.DataFull)[308:")"] return false;
                [309:"LombokPatternOffNegativeTestData.DataFull"] dataFull = [310:"(LombokPatternOffNegativeTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[311:"isEquals()"];
            }[297:"\n\n            "][318:"@Override"][318:"\n            "]public int hashCode() {[317:"\n                "][317:"return"][317:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[317:";"][317:"\n            "]}[297:"\n\n            "][319:"@Override"][319:"\n            "]public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }
    }

    public class FoldOn {
        [320:"p"]ublic class FoldOnPublic {
            boolean ok;[320:"\n\n            "][320:"public boolean isOk() {\n                return ok;\n            }"]
        }

        [322:"c"]lass FoldOnClass {
            boolean ok;[322:"\n\n            "][322:"public boolean isOk() {\n                return ok;\n            }"]
        }

        @SuppressWarnings("ALL")
        [324:"c"]lass FoldOnWithAnnotation {
            boolean ok;[324:"\n\n            "][324:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {[326:"\n            "][326:"return"][326:" "]dirty2[326:";"][326:"\n        "]}

        public boolean isDirty2() {[327:"\n            "][327:"return"][327:" "]dirty[327:";"][327:"\n        "]}

        [328:"p"]ublic class DirtyData {
            boolean dirty;
            [329:"p"]rivate boolean ok;

            public boolean isDirty() {[330:"\n                "][330:"return"][330:" "]!dirty[330:";"][330:"\n            "]}[329:"\n\n            "][329:"public boolean isOk() {\n                return ok;\n            }"][328:"\n\n            "][332:"@Override"][332:"\n            "]public boolean equals(Object o) {
                if [333:"("]this == o[333:")"] return true;
                if [334:"("]!(o instanceof DirtyData)[334:")"] return false;

                [335:"DirtyData"] dirtyData = [336:"(DirtyData) "]o;

                if [337:"("]dirty != dirtyData.dirty[337:")"] return false;
                if [338:"("]ok != dirtyData.ok[338:")"] return false;

                return true;
            }[328:"\n\n            "][345:"@Override"][345:"\n            "]public int hashCode() {
                [346:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[347:" + ("]ok ? 1 : 0[347:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [350:"b"]oolean ok;[350:"\n\n            "][350:"public boolean isOk() {\n                return ok;\n            }"]

            public boolean isDirty() {[352:"\n                "][352:"return"][352:" "]dirty2[352:";"][352:"\n            "]}
        }
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {[353:"\n            "]this.dirty2 = dirty[353:";"][353:"\n        "]}

        public void setDirty2(boolean dirty2) {[354:"\n            "]this.dirty = dirty2[354:";"][354:"\n        "]}

        [355:"p"]ublic class DirtyData {
            boolean dirty;
            [356:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {[357:"\n                "]this.dirty = !dirty[357:";"][357:"\n            "]}[356:"\n\n            "][356:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][355:"\n\n            "][355:"public boolean isDirty() {\n                return dirty;\n            }"][355:"\n\n            "][355:"public boolean isOk() {\n                return ok;\n            }"][355:"\n\n            "][365:"@Override"][365:"\n            "]public boolean equals(Object o) {
                if [366:"("]this == o[366:")"] return true;
                if [367:"("]!(o instanceof DirtyData)[367:")"] return false;

                [368:"DirtyData"] dirtyData = [369:"(DirtyData) "]o;

                if [370:"("]dirty != dirtyData.dirty[370:")"] return false;
                if [371:"("]ok != dirtyData.ok[371:")"] return false;

                return true;
            }[355:"\n\n            "][378:"@Override"][378:"\n            "]public int hashCode() {
                [379:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[380:" + ("]ok ? 1 : 0[380:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [383:"b"]oolean ok;[383:"\n\n            "][383:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]

            public void setDirty(boolean dirty) {[389:"\n                "]this.ok = dirty[389:";"][389:"\n            "]}
        }
    }

    public class LogAnnotation {
        [390:"p"]ublic class LogJava {[390:"\n            "][390:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [391:"p"]ublic class LogJava2 {[391:"\n            "][391:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [392:"p"]ublic class LogDiffrentFieldName {[392:"\n            "][393:"public static final "][393:"Logger"] logger = Logger.getLogger("LogAnnotation.class");
        }

        [394:"p"]ublic class LogCustomNameDeprecated {[394:"\n            "][394:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [396:"p"]ublic class TripleLogJava {[396:"\n            "][396:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][396:"\n            "][397:"public static final "][397:"Logger"] log2 = Logger.getLogger("LogAnnotation.class");[396:"\n            "][396:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        [398:"p"]ublic class NoArgsConstructor {[398:"\n            "][398:"public NoArgsConstructor() {\n            }"]
        }
        [399:"c"]lass NoArgsConstructorPrivate {[399:"\n            "][399:"private NoArgsConstructorPrivate() {\n            }"]
        }
        [400:"p"]ublic class NoArgsConstructorSuperBefore {[400:"\n            "][400:"public NoArgsConstructorSuperBefore() {\n                // comment\n                super();\n            }"]
        }
        [402:"p"]ublic class NoArgsConstructorSuperAfter {[402:"\n            "][402:"public NoArgsConstructorSuperAfter() {\n                super();\n                // comment\n            }"]
        }

        [404:"p"]ublic class ProtectedNoArgsConstructorSuperAfter {[404:"\n            "][404:"protected ProtectedNoArgsConstructorSuperAfter() {\n                // comment\n            }"]
        }
        [405:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[405:"\n            "][405:"public NoArgsConstructorSuper() {\n                super();\n            }"]
        }
        public class NoArgsConstructorSuperParent extends Paren[407:"t"] {
            public NoArgsConstructorSuperParent() {[408:"\n                "]super(null)[408:";"][408:"\n            "]}
        }

    }

    public class AllArgsConstructorAnnotation {
        [409:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[409:"\n            "][409:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [416:"field1"];
                this.field2 = field1;
                this.field3 = [417:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [420:"field1"];
                this.field1 = field2;
                this.field3 = [421:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [424:"field1"];
                this.field2 = [425:"field2"];
                this.field3 = [426:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [430:"field1"];
                this.field2 = [431:"field2"];
                this.field3 = [432:"field3"];
                // comment
            }
        }

        [436:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[436:"\n            "][436:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [443:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[443:"\n            "][443:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[450:"\n                "][450:"return"][450:" "]new StaticNameArgs(field1, field2, field3)[450:";"][450:"\n            "]}
        }

        [451:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[451:"\n            "][451:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [458:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[458:"\n\n            "][458:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [465:"field1"];
                this.field2 = [466:"field2"];
                this.field3 = [467:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [471:"field1"];
                this.field2 = [472:"field2"];
                this.field3 = [473:"field3"];
                // comment
            }
        }

        [477:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[477:"\n\n            "][477:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [484:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[484:"\n\n            "][484:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[491:"\n                "][491:"return"][491:" "]new StaticNameArgs(field1, field2, field3)[491:";"][491:"\n            "]}
        }

        [492:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[492:"\n\n            "][492:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }

    public class ValueAnnotation {
        [499:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[499:"\n            "][499:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][499:"\n            "][499:"public String getField1() {\n                return field1;\n            }"][499:"\n            "][499:"public int getField2() {\n                return field2;\n            }"][499:"\n            "][499:"public boolean isField3() {\n                return field3;\n            }"][499:"\n            "][509:"@Override"][509:"\n            "]public boolean equals(Object o) {
                if [510:"("]this == o[510:")"] return true;
                if [511:"("]o == null || [520:"getClass()"] != o.[521:"getClass()"][511:")"] return false;
                [512:"ValueArgs"] valueArgs = [513:"(ValueArgs) "]o;
                if [514:"("]field2 != valueArgs.field2[514:")"] return false;
                if [515:"("]field3 != valueArgs.field3[515:")"] return false;
                return [516:"field1 != null ? "]field1[516:"."]equals(valueArgs.field1[516:")"][516:" : "]valueArgs.field1 == null;
            }[499:"\n            "][528:"@Override"][528:"\n            "]public int hashCode() {
                [529:"int"] result = [530:"field1 != null ? "]field1[530:"."]hashCode()[530:" : "]0;
                result = 31 * result + field2;
                result = 31 * result[531:" + ("]field3 ? 1 : 0[531:")"];
                return result;
            }[499:"\n\n            "][535:"@Override"][535:"\n            "]public String toString() {
                return "ValueArgs{[536:"\" +\n                        \""]field1='[536:"\" + "]field1[536:" + "]'\''[536:" +\n                        \""], field2=[536:"\" + "]field2[536:" +\n                        \""], field3=[536:"\" + "]field3[536:" +\n                        "]'}'[536:";"]
            }
        }
        [538:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[538:"\n            "][538:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][538:"\n            "][538:"public String getField1() {\n                return field1;\n            }"][538:"\n            "][538:"public int getField2() {\n                return field2;\n            }"][538:"\n            "][538:"public boolean isField3() {\n                return field3;\n            }"][538:"\n            "][548:"@Override"][548:"\n            "]public [549:"final"] boolean equals(Object o) {
                if [550:"("]this == o[550:")"] return true;
                if [551:"("]!(o instanceof ValueArgsSuper)[551:")"] return false;

                [552:"ValueArgsSuper"] that = [553:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }[538:"\n\n            "][559:"@Override"][559:"\n            "]public int hashCode() {
                [560:"int"] result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }
        }
        [562:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[562:"\n            "][562:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][562:"\n            "][562:"public String getField1() {\n                return field1;\n            }"][562:"\n            "][562:"public int getField2() {\n                return field2;\n            }"][562:"\n            "][562:"public boolean isField3() {\n                return field3;\n            }"]
        }
    }
    class SingleField {
        [572:"p"]ublic static class AllArgs {
            private String field1;[572:"\n            "][572:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [578:"p"]ublic static class ReqArgs {
            private final String field1;[578:"\n            "][578:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [584:"p"]ublic static class Value {
            private final String field1;[584:"\n            "][584:"public Value(String field1) {\n                this.field1 = field1;\n            }"][584:"\n            "][584:"public String getField1() {\n                return field1;\n            }"][584:"\n            "][591:"@Override"][591:"\n            "]public [592:"final"] boolean equals(Object o) {
                if [593:"("]this == o[593:")"] return true;
                if [594:"("]!(o instanceof Value)[594:")"] return false;

                [595:"Value"] value = [596:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }[584:"\n            "][603:"@Override"][603:"\n            "]public int hashCode() {[602:"\n                "][602:"return"][602:" "]Objects.hashCode(field1)[602:";"][602:"\n            "]}
        }
        [604:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[604:"\n            "][604:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"][604:"\n            "][604:"public String getField1() {\n                return field1;\n            }"]
        }
        class Modifers {
            [611:"s"]tatic class AllArgsDefault {
                private String field1;[611:"\n                "][611:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [617:"s"]tatic class AllArgsPrivate {
                private String field1;[617:"\n                "][617:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [623:"s"]tatic class AllArgsProteced {
                private String field1;[623:"\n                "][623:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]
            }
        }
    }

    class FieldLevelData {
        [629:"p"]rivate String name;
        private String ignored;[629:"\n\n        "][629:"public String getName() {\n            return name;\n        }"][629:"\n\n        "][629:"public void setName(String name) {\n            this.name = name;\n        }"][629:"\n\n        "][636:"@Override"][636:"\n        "]public [637:"final"] boolean equals(Object o) {
            if [638:"("]this == o[638:")"] return true;
            if [639:"("]!(o instanceof FieldLevelData)[639:")"] return false;
            [640:"FieldLevelData"] that = [641:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[629:"\n\n        "][648:"@Override"][648:"\n        "]public int hashCode() {[647:"\n            "][647:"return"][647:" "]Objects.hashCode(name)[647:";"][647:"\n        "]}
    }

    class FieldLevelValue {
        [649:"p"]rivate final String name = "1";
        private String ignored;[649:"\n\n        "][649:"public String getName() {\n            return name;\n        }"][649:"\n\n        "][651:"@Override"][651:"\n        "]public [652:"final"] boolean equals(Object o) {
            if [653:"("]this == o[653:")"] return true;
            if [654:"("]!(o instanceof FieldLevelData)[654:")"] return false;
            [655:"FieldLevelData"] that = [656:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[649:"\n\n        "][663:"@Override"][663:"\n        "]public int hashCode() {[662:"\n            "][662:"return"][662:" "]Objects.hashCode(name)[662:";"][662:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        [664:"p"]rivate String name;
        private String ignored;[664:"\n\n        "][664:"public String getName() {\n            return name;\n        }"][664:"\n\n        "][666:"@Override"][666:"\n        "]public [667:"final"] boolean equals(Object o) {
            if [668:"("]this == o[668:")"] return true;
            if [669:"("]!(o instanceof FieldLevelData)[669:")"] return false;
            [670:"FieldLevelData"] that = [671:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[664:"\n\n        "][678:"@Override"][678:"\n        "]public int hashCode() {[677:"\n            "][677:"return"][677:" "]Objects.hashCode(name)[677:";"][677:"\n        "]}
    }

    [679:"c"]lass ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [680:"name"];
                return this;
            }
            public ClassWithBuilder build() {[682:"\n                "][682:"return"][682:" "]new ClassWithBuilder()[682:";"][682:"\n            "]}
        }
    }

    class Constructors {
        [683:"p"]ublic class FiveConstructors {
            [684:"p"]rivate int field1;
            [685:"p"]rivate String field2;
            [686:"p"]rivate double field3;
            private boolean field4;[683:"\n            "][683:"public FiveConstructors() {\n            }"][684:"\n            "][684:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"][684:"\n            "][684:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][684:"\n            "][684:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][683:"\n            "][683:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        [710:"p"]ublic class FiveConstructorsMod {
            [711:"p"]rivate int field1;
            [712:"p"]rivate String field2;
            [713:"p"]rivate double field3;
            private boolean field4;[710:"\n            "][710:"public FiveConstructorsMod() {\n            }"][711:"\n            "][711:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"][711:"\n            "][711:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][711:"\n            "][711:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][710:"\n            "][710:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }
    }

}
