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

    public Optional<LombokTestData> asOptional() {[20:"\n        "][20:"return"][20:" "][21:"Optional.ofNullable("]data[21:")"][20:";"][20:"\n    "]}

    [23:"p"]ublic class LombokGetters {
        LombokTestData data;
        boolean ok;[23:"\n\n        "][23:"public LombokTestData getData() {\n            return data;\n        }"][23:"\n\n        "][23:"public boolean isOk() {\n            return ok;\n        }"]

        public class LombokGettersPartial {
            LombokTestData data;
            [26:"b"]oolean ok;[26:"\n\n            "][26:"public boolean isOk() {\n                return ok;\n            }"]
        }
    }

    [28:"p"]ublic class LombokSetters {
        LombokTestData data;
        boolean ok;[28:"\n\n        "][28:"public LombokTestData getData() {\n            return data;\n        }"][28:"\n\n        "][28:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"][28:"\n\n        "][28:"public boolean isOk() {\n            return ok;\n        }"][28:"\n\n        "][28:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]

        public class LombokSettersPartial {
            [41:"L"]ombokTestData data;
            boolean ok;[41:"\n\n            "][41:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]
        }

        public class LombokSettersFinalField {
            [47:"L"]ombokTestData data;
            final boolean ok = true;[47:"\n\n            "][47:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]
        }
    }

    [53:"p"]ublic class ToStringFull {
        LombokTestData data;
        boolean ok;[53:"\n\n        "][54:"@Override"][54:"\n        "]public String toString() {
            return "ToStringFull{[55:"\" +\n                    \""]data=[55:"\" + "]data[55:" +\n                    \""], ok=[55:"\" + "]ok[55:" +\n                    "]'}'[55:";"]
        }

        public class ToStringPartial {
            [57:"L"]ombokTestData data;
            boolean ok;[57:"\n\n            "][59:"@Override"][59:"\n            "]public String toString() {[58:"\n                "][58:"return"][58:" "]"ToStringPartial{[60:"\" +\n                        \""]data=[60:"\" + "]data[60:" +\n                        "]'}'[58:";"][58:"\n            "]}
        }

        public class ToStringPartial2 {
            [62:"L"]ombokTestData data;
            boolean ok;
            String string;[62:"\n\n            "][64:"@Override"][64:"\n            "]public String toString() {[63:"\n                "][63:"return"][63:" "]"ToStringPartial{[65:"\" +\n                        \""]data=[65:"\" + "]data[65:" +\n                        "]'}'[63:";"][63:"\n            "]}
        }
    }

    [67:"p"]ublic class EqualsAndHashCodeFull {
        LombokTestData data;
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
            [93:"L"]ombokTestData data;
            boolean ok;[93:"\n\n            "][94:"@Override"][94:"\n            "]public boolean equals(Object o) {
                if [95:"("]this == o[95:")"] return true;
                if [96:"("]o == null || [103:"getClass()"] != o.[104:"getClass()"][96:")"] return false;
                [97:"EqualsAndHashCodePartial"] that = [98:"(EqualsAndHashCodePartial) "]o;
                return [99:"data != null ? "]data[99:"."]equals(that.data[99:")"][99:" : "]that.data == null;
            }[93:"\n\n            "][110:"@Override"][110:"\n            "]public int hashCode() {[109:"\n                "][109:"return"][109:" "][111:"data != null ? "]data[111:"."]hashCode()[111:" : "]0[109:";"][109:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            [113:"L"]ombokTestData data;
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
        LombokTestData data;
        boolean ok;[140:"\n\n        "][141:"@Override"][141:"\n        "]public boolean equals(Object o) {
            if [142:"("]this == o[142:")"] return true;
            if [143:"("]o == null || [152:"getClass()"] != o.[153:"getClass()"][143:")"] return false;
            [144:"EqualsFull"] that = [145:"(EqualsFull) "]o;
            return ok == that.ok && ([146:"data != null ? "]data[146:"."]equals(that.data[146:")"][146:" : "]that.data == null);
        }

        public class EqualsPartial {
            [158:"L"]ombokTestData data;
            boolean ok;[158:"\n\n            "][159:"@Override"][159:"\n            "]public boolean equals(Object o) {
                if [160:"("]this == o[160:")"] return true;
                if [161:"("]o == null || [168:"getClass()"] != o.[169:"getClass()"][161:")"] return false;
                [162:"EqualsPartial"] that = [163:"(EqualsPartial) "]o;
                return [164:"data != null ? "]data[164:"."]equals(that.data[164:")"][164:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            [174:"L"]ombokTestData data;
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
        LombokTestData data;
        boolean ok;[193:"\n\n        "][194:"@Override"][194:"\n        "]public int hashCode() {
            [195:"int"] result = ([196:"data != null ? "]data[196:"."]hashCode()[196:" : "]0);
            result = 31 * result[198:" + ("]ok ? 1 : 0[198:")"];
            return result;
        }

        public class HashCodePartial {
            [202:"L"]ombokTestData data;
            boolean ok;[202:"\n\n            "][204:"@Override"][204:"\n            "]public int hashCode() {[203:"\n                "][203:"return"][203:" "][205:"data != null ? "]data[205:"."]hashCode()[205:" : "]0[203:";"][203:"\n            "]}
        }

        public class HashCodePartialTwo {
            [207:"L"]ombokTestData data;
            [208:"b"]oolean ok;
            String string;[207:"\n\n            "][209:"@Override"][209:"\n            "]public int hashCode() {
                [210:"int"] result = ([211:"data != null ? "]data[211:"."]hashCode()[211:" : "]0);
                result = 31 * result[213:" + ("]ok ? 1 : 0[213:")"];
                return result;
            }
        }
    }

    [217:"p"]ublic class DataFull {
        LombokTestData data;
        boolean ok;[217:"\n\n        "][217:"public LombokTestData getData() {\n            return data;\n        }"][217:"\n\n        "][217:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"][217:"\n\n        "][217:"public boolean isOk() {\n            return ok;\n        }"][217:"\n\n        "][217:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"][217:"\n\n        "][230:"@Override"][230:"\n        "]public boolean equals(Object o) {
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
            LombokTestData data;
            boolean ok;[244:"\n\n            "][244:"public LombokTestData getData() {\n                return data;\n            }"][244:"\n\n            "][244:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][244:"\n\n            "][244:"public boolean isOk() {\n                return ok;\n            }"][244:"\n\n            "][244:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][244:"\n\n            "][257:"@Override"][257:"\n            "]public boolean equals(Object o) {
                if [258:"("]this == o[258:")"] return true;
                if [259:"("]!(o instanceof LombokTestData.DataFull)[259:")"] return false;
                [260:"LombokTestData.DataFull"] dataFull = [261:"(LombokTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[262:"isEquals()"];
            }[244:"\n\n            "][269:"@Override"][269:"\n            "]public int hashCode() {[268:"\n                "][268:"return"][268:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[268:";"][268:"\n            "]}

        }

        [270:"p"]ublic class DataWithPartialGetters {
            [271:"L"]ombokTestData data;
            boolean ok;[271:"\n\n            "][271:"public LombokTestData getData() {\n                return data;\n            }"][270:"\n\n            "][270:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][270:"\n\n            "][270:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][270:"\n\n            "][283:"@Override"][283:"\n            "]public boolean equals(Object o) {
                if [284:"("]this == o[284:")"] return true;
                if [285:"("]!(o instanceof LombokTestData.DataFull)[285:")"] return false;
                [286:"LombokTestData.DataFull"] dataFull = [287:"(LombokTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[288:"isEquals()"];
            }[270:"\n\n            "][295:"@Override"][295:"\n            "]public int hashCode() {[294:"\n                "][294:"return"][294:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[294:";"][294:"\n            "]}[270:"\n\n            "][296:"@Override"][296:"\n            "]public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        [297:"p"]ublic class DataWithPartialSetters {
            [298:"L"]ombokTestData data;
            boolean ok;[297:"\n\n            "][297:"public LombokTestData getData() {\n                return data;\n            }"][298:"\n\n            "][298:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][297:"\n\n            "][297:"public boolean isOk() {\n                return ok;\n            }"][297:"\n\n            "][306:"@Override"][306:"\n            "]public boolean equals(Object o) {
                if [307:"("]this == o[307:")"] return true;
                if [308:"("]!(o instanceof LombokTestData.DataFull)[308:")"] return false;
                [309:"LombokTestData.DataFull"] dataFull = [310:"(LombokTestData.DataFull) "]o;
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

    public class SupportedDirtyLombokGetters {
        private List<String> wrapper;
        private List<String> wrapperWrongRef;
        private List<String> wrapperDeeplyHiddenRef;

        private List<String> localMethodWrappedList;
        private List<String> thisLocalMethodWrappedList;
        private List<String> lazyLoadedList;
        private List<String> oneLineLazyLoadedList;
        private List<String> defensiveCopyList;

        public List<String> getWrapper() {[353:"\n            "][353:"return"][353:" "]Collections.unmodifiableList(wrapper)[353:";"][353:"\n        "]}

        public List<String> getWrapperDeeplyHiddenRef() {
            if [354:"("]wrapper != null[354:")"] {
                [356:"try"][356:" "][356:"{"]
                [356:"    "]return wrapperDeeplyHiddenRef;[356:"\n                "][356:"}"][356:" "][356:"catch (Exception e) {\n                    throw new RuntimeException(e);\n                }"]
            }
            return null;
        }

        public List<String> getWrapperWrongRef() {[358:"\n            "][358:"return"][358:" "]Collections.unmodifiableList(wrapper)[358:";"][358:"\n        "]}

        public List<String> getLocalMethodWrappedList() {[359:"\n            "][359:"return"][359:" "]localWrap(localMethodWrappedList)[359:";"][359:"\n        "]}

        public List<String> getThisLocalMethodWrappedList() {[360:"\n            "][360:"return"][360:" "]this.localWrap(thisLocalMethodWrappedList)[360:";"][360:"\n        "]}

        public List<String> getLazyLoadedList() {
            if [361:"("]lazyLoadedList == null[361:")"] {
                lazyLoadedList = [363:"new ArrayList<>()"];
            }
            return lazyLoadedList;
        }

        public List<String> getDefensiveCopyList() {[364:"\n            "][364:"return"][364:" "]new ArrayList<>(defensiveCopyList)[364:";"][364:"\n        "]}

        public List<String> getOneLineLazyLoadedList() {
            if [365:"("]oneLineLazyLoadedList == null[365:")"] oneLineLazyLoadedList = [367:"new ArrayList<>()"];
            return oneLineLazyLoadedList;
        }

        private List<String> localWrap(List<String> list) {[368:"\n            "][368:"return"][368:" "]null[368:";"][368:"\n        "]}
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;
        [369:"b"]oolean withoutThis;

        public void setDirty(boolean dirty) {[370:"\n            "]this.dirty2 = dirty[370:";"][370:"\n        "]}

        public void setDirty2(boolean dirty2) {[371:"\n            "]this.dirty = dirty2[371:";"][371:"\n        "]}[369:"\n\n        "][369:"public void setWithoutThis(boolean withoutThiss) {\n            withoutThis = withoutThiss;\n        }"]

        [373:"p"]ublic class DirtyData {
            boolean dirty;
            [374:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {[375:"\n                "]this.dirty = !dirty[375:";"][375:"\n            "]}[374:"\n\n            "][374:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][373:"\n\n            "][373:"public boolean isDirty() {\n                return dirty;\n            }"][373:"\n\n            "][373:"public boolean isOk() {\n                return ok;\n            }"][373:"\n\n            "][383:"@Override"][383:"\n            "]public boolean equals(Object o) {
                if [384:"("]this == o[384:")"] return true;
                if [385:"("]!(o instanceof DirtyData)[385:")"] return false;

                [386:"DirtyData"] dirtyData = [387:"(DirtyData) "]o;

                if [388:"("]dirty != dirtyData.dirty[388:")"] return false;
                if [389:"("]ok != dirtyData.ok[389:")"] return false;

                return true;
            }[373:"\n\n            "][396:"@Override"][396:"\n            "]public int hashCode() {
                [397:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[398:" + ("]ok ? 1 : 0[398:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [401:"b"]oolean ok;[401:"\n\n            "][401:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]

            public void setDirty(boolean dirty) {[407:"\n                "]this.ok = dirty[407:";"][407:"\n            "]}
        }
    }

    public class LogAnnotation {
        [408:"p"]ublic class LogJava {[408:"\n            "][408:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [409:"p"]ublic class LogJava2 {[409:"\n            "][409:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [410:"p"]ublic class LogDiffrentFieldName {[410:"\n            "][411:"public static final "][411:"Logger"] logger = Logger.getLogger("LogAnnotation.class");
        }

        [412:"p"]ublic class LogCustomNameDeprecated {[412:"\n            "][412:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [414:"p"]ublic class TripleLogJava {[414:"\n            "][414:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][414:"\n            "][415:"public static final "][415:"Logger"] log2 = Logger.getLogger("LogAnnotation.class");[414:"\n            "][414:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
        }
    }


    public class Parent {
        public Parent(String child) {
        }
    }

    public class NoArgsConstructorAnnotation {
        [416:"p"]ublic class NoArgsConstructor {[416:"\n            "][416:"public NoArgsConstructor() {\n            }"]
        }
        [417:"c"]lass NoArgsConstructorPrivate {[417:"\n            "][417:"private NoArgsConstructorPrivate() {\n            }"]
        }
        [418:"p"]ublic class NoArgsConstructorCommentBeforeSuper {[418:"\n            "][418:"public NoArgsConstructorCommentBeforeSuper() {\n                // comment\n                super();\n            }"]
        }
        [420:"p"]ublic class NoArgsConstructorCommentAfterSuper {[420:"\n            "][420:"public NoArgsConstructorCommentAfterSuper() {\n                super();\n                // comment\n            }"]
        }
        [422:"p"]ublic class ProtectedNoArgsConstructorComment {[422:"\n            "][422:"protected ProtectedNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        // comment hidden as it is common and adds no meaningful value
        [423:"p"]ublic class PrivateNoArgsConstructorComment {[423:"\n            "][423:"private PrivateNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        [424:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[424:"\n            "][424:"public NoArgsConstructorSuper() {\n                super();\n            }"]
        }
        public class NoArgsConstructorSuperParent extends Paren[426:"t"] {
            public NoArgsConstructorSuperParent() {[427:"\n                "]super(null)[427:";"][427:"\n            "]}
        }

    }

    public class AllArgsConstructorAnnotation {
        [428:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[428:"\n            "][428:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [435:"field1"];
                this.field2 = field1;
                this.field3 = [436:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [439:"field1"];
                this.field1 = field2;
                this.field3 = [440:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [443:"field1"];
                this.field2 = [444:"field2"];
                this.field3 = [445:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [449:"field1"];
                this.field2 = [450:"field2"];
                this.field3 = [451:"field3"];
                // comment
            }
        }

        [455:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[455:"\n            "][455:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [462:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[462:"\n            "][462:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[469:"\n                "][469:"return"][469:" "]new StaticNameArgs(field1, field2, field3)[469:";"][469:"\n            "]}
        }

        [470:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[470:"\n            "][470:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [477:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[477:"\n\n            "][477:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [484:"field1"];
                this.field2 = [485:"field2"];
                this.field3 = [486:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [490:"field1"];
                this.field2 = [491:"field2"];
                this.field3 = [492:"field3"];
                // comment
            }
        }

        [496:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[496:"\n\n            "][496:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }

        [503:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[503:"\n\n            "][503:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[510:"\n                "][510:"return"][510:" "]new StaticNameArgs(field1, field2, field3)[510:";"][510:"\n            "]}
        }

        [511:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[511:"\n\n            "][511:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]
        }
    }

    public class ValueAnnotation {
        [518:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[518:"\n            "][518:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][518:"\n            "][518:"public String getField1() {\n                return field1;\n            }"][518:"\n            "][518:"public int getField2() {\n                return field2;\n            }"][518:"\n            "][518:"public boolean isField3() {\n                return field3;\n            }"][518:"\n            "][528:"@Override"][528:"\n            "]public boolean equals(Object o) {
                if [529:"("]this == o[529:")"] return true;
                if [530:"("]o == null || [539:"getClass()"] != o.[540:"getClass()"][530:")"] return false;
                [531:"ValueArgs"] valueArgs = [532:"(ValueArgs) "]o;
                if [533:"("]field2 != valueArgs.field2[533:")"] return false;
                if [534:"("]field3 != valueArgs.field3[534:")"] return false;
                return [535:"field1 != null ? "]field1[535:"."]equals(valueArgs.field1[535:")"][535:" : "]valueArgs.field1 == null;
            }[518:"\n            "][547:"@Override"][547:"\n            "]public int hashCode() {
                [548:"int"] result = [549:"field1 != null ? "]field1[549:"."]hashCode()[549:" : "]0;
                result = 31 * result + field2;
                result = 31 * result[550:" + ("]field3 ? 1 : 0[550:")"];
                return result;
            }[518:"\n\n            "][554:"@Override"][554:"\n            "]public String toString() {
                return "ValueArgs{[555:"\" +\n                        \""]field1='[555:"\" + "]field1[555:" + "]'\''[555:" +\n                        \""], field2=[555:"\" + "]field2[555:" +\n                        \""], field3=[555:"\" + "]field3[555:" +\n                        "]'}'[555:";"]
            }
        }
        [557:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[557:"\n            "][557:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][557:"\n            "][557:"public String getField1() {\n                return field1;\n            }"][557:"\n            "][557:"public int getField2() {\n                return field2;\n            }"][557:"\n            "][557:"public boolean isField3() {\n                return field3;\n            }"][557:"\n            "][567:"@Override"][567:"\n            "]public [568:"final"] boolean equals(Object o) {
                if [569:"("]this == o[569:")"] return true;
                if [570:"("]!(o instanceof ValueArgsSuper)[570:")"] return false;

                [571:"ValueArgsSuper"] that = [572:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }[557:"\n\n            "][578:"@Override"][578:"\n            "]public int hashCode() {
                [579:"int"] result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }
        }
        [581:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[581:"\n            "][581:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][581:"\n            "][581:"public String getField1() {\n                return field1;\n            }"][581:"\n            "][581:"public int getField2() {\n                return field2;\n            }"][581:"\n            "][581:"public boolean isField3() {\n                return field3;\n            }"]
        }
    }
    class SingleField {
        [591:"p"]ublic static class AllArgs {
            private String field1;[591:"\n            "][591:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [597:"p"]ublic static class ReqArgs {
            private final String field1;[597:"\n            "][597:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]
        }
        [603:"p"]ublic static class Value {
            private final String field1;[603:"\n            "][603:"public Value(String field1) {\n                this.field1 = field1;\n            }"][603:"\n            "][603:"public String getField1() {\n                return field1;\n            }"][603:"\n            "][610:"@Override"][610:"\n            "]public [611:"final"] boolean equals(Object o) {
                if [612:"("]this == o[612:")"] return true;
                if [613:"("]!(o instanceof Value)[613:")"] return false;

                [614:"Value"] value = [615:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }[603:"\n            "][622:"@Override"][622:"\n            "]public int hashCode() {[621:"\n                "][621:"return"][621:" "]Objects.hashCode(field1)[621:";"][621:"\n            "]}
        }
        [623:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[623:"\n            "][623:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"][623:"\n            "][623:"public String getField1() {\n                return field1;\n            }"]
        }
        class Modifers {
            [630:"s"]tatic class AllArgsDefault {
                private String field1;[630:"\n                "][630:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [636:"s"]tatic class AllArgsPrivate {
                private String field1;[636:"\n                "][636:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]
            }
            [642:"s"]tatic class AllArgsProteced {
                private String field1;[642:"\n                "][642:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]
            }
        }
    }

    class FieldLevelData {
        [648:"p"]rivate String name;
        private String ignored;[648:"\n\n        "][648:"public String getName() {\n            return name;\n        }"][648:"\n\n        "][648:"public void setName(String name) {\n            this.name = name;\n        }"][648:"\n\n        "][655:"@Override"][655:"\n        "]public [656:"final"] boolean equals(Object o) {
            if [657:"("]this == o[657:")"] return true;
            if [658:"("]!(o instanceof FieldLevelData)[658:")"] return false;
            [659:"FieldLevelData"] that = [660:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[648:"\n\n        "][667:"@Override"][667:"\n        "]public int hashCode() {[666:"\n            "][666:"return"][666:" "]Objects.hashCode(name)[666:";"][666:"\n        "]}
    }

    class FieldLevelValue {
        [668:"p"]rivate final String name = "1";
        private String ignored;[668:"\n\n        "][668:"public String getName() {\n            return name;\n        }"][668:"\n\n        "][670:"@Override"][670:"\n        "]public [671:"final"] boolean equals(Object o) {
            if [672:"("]this == o[672:")"] return true;
            if [673:"("]!(o instanceof FieldLevelData)[673:")"] return false;
            [674:"FieldLevelData"] that = [675:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[668:"\n\n        "][682:"@Override"][682:"\n        "]public int hashCode() {[681:"\n            "][681:"return"][681:" "]Objects.hashCode(name)[681:";"][681:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        [683:"p"]rivate String name;
        private String ignored;[683:"\n\n        "][683:"public String getName() {\n            return name;\n        }"][683:"\n\n        "][685:"@Override"][685:"\n        "]public [686:"final"] boolean equals(Object o) {
            if [687:"("]this == o[687:")"] return true;
            if [688:"("]!(o instanceof FieldLevelData)[688:")"] return false;
            [689:"FieldLevelData"] that = [690:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[683:"\n\n        "][697:"@Override"][697:"\n        "]public int hashCode() {[696:"\n            "][696:"return"][696:" "]Objects.hashCode(name)[696:";"][696:"\n        "]}
    }

    [698:"c"]lass ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [699:"name"];
                return this;
            }
            public ClassWithBuilder build() {[701:"\n                "][701:"return"][701:" "]new ClassWithBuilder()[701:";"][701:"\n            "]}
        }
    }

    class Constructors {
        [702:"p"]ublic class FiveConstructors {
            [703:"p"]rivate int field1;
            [704:"p"]rivate String field2;
            [705:"p"]rivate double field3;
            private boolean field4;[702:"\n            "][702:"public FiveConstructors() {\n            }"][703:"\n            "][703:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"][703:"\n            "][703:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][703:"\n            "][703:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][702:"\n            "][702:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        [729:"p"]ublic class FiveConstructorsMod {
            [730:"p"]rivate int field1;
            [731:"p"]rivate String field2;
            [732:"p"]rivate double field3;
            private boolean field4;[729:"\n\n            "][729:"public FiveConstructorsMod() {\n            }"][730:"\n\n            "][730:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"][730:"\n\n            "][730:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][730:"\n\n            "][730:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][729:"\n\n            "][729:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]
        }

        class SingleConstructor {
            [756:"p"]rivate final int field1;
            private int field2;[756:"\n            "][756:"SingleConstructor(int field1) {\n                this.field1 = field1;\n            }"]
        }

        private class SingleDoubleConstructor {
            [762:"p"]rivate final int field1;
            [762:"p"]rivate final int field2;
            private int field3;[762:"\n\n            "][762:"private SingleDoubleConstructor(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"]
        }

        public class SingleConstructorNoMod {
            [767:"p"]rivate final int field1;
            private int field2;[767:"\n            "][767:"public SingleConstructorNoMod(int field1) {\n                this.field1 = field1;\n            }"]
        }

        public class SingleDoubleConstructorNoMod {
            [773:"p"]rivate final int field1;
            [773:"p"]rivate final int field2;
            private int field3;[773:"\n\n            "][773:"public SingleDoubleConstructorNoMod(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"]
        }

    }

    [778:"c"]lass Builders {

        class FirstBuilder {

        }

        class SecondBuilder {

        }

        class BuildersBuilder {

        }

    }

}
