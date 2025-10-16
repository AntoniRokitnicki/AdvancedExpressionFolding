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
public class LombokPatternOffTestData {

    private [0:"static final "][0:"long"] serialVersionUID = 1234567L;

    LombokPatternOffTestData data;
    boolean ok;
    String string;

    public LombokPatternOffTestData getData() {[1:"\n        "][1:"return"][1:" "]data[1:";"][1:"\n    "]}

    public void setData(LombokPatternOffTestData data) {[2:"\n        "]this.data = [3:"data"][2:";"][2:"\n    "]}

    public boolean isOk() {[7:"\n        "][7:"return"][7:" "]ok[7:";"][7:"\n    "]}

    public void setOk(boolean ok) {[8:"\n        "]this.ok = [9:"ok"][8:";"][8:"\n    "]}

    public String getString() {[13:"\n        "][13:"return"][13:" "]string[13:";"][13:"\n    "]}

    public void setString(String string) {[14:"\n        "]this.string = [15:"string"][14:";"][14:"\n    "]}

    public Optional<LombokPatternOffTestData> asOptional() {[19:"\n        "][19:"return"][19:" "][20:"Optional.ofNullable("]data[20:")"][19:";"][19:"\n    "]}

    public class LombokGetters {
        LombokPatternOffTestData data;
        boolean ok;

        public LombokPatternOffTestData getData() {[22:"\n            "][22:"return"][22:" "]data[22:";"][22:"\n        "]}

        public boolean isOk() {[23:"\n            "][23:"return"][23:" "]ok[23:";"][23:"\n        "]}

        public class LombokGettersPartial {
            LombokPatternOffTestData data;
            boolean ok;

            public boolean isOk() {[24:"\n                "][24:"return"][24:" "]ok[24:";"][24:"\n            "]}
        }
    }

    public class LombokSetters {
        LombokPatternOffTestData data;
        boolean ok;

        public LombokPatternOffTestData getData() {[25:"\n            "][25:"return"][25:" "]data[25:";"][25:"\n        "]}

        public void setData(LombokPatternOffTestData data) {[26:"\n            "]this.data = [27:"data"][26:";"][26:"\n        "]}

        public boolean isOk() {[31:"\n            "][31:"return"][31:" "]ok[31:";"][31:"\n        "]}

        public void setOk(boolean ok) {[32:"\n            "]this.ok = [33:"ok"][32:";"][32:"\n        "]}

        public class LombokSettersPartial {
            LombokPatternOffTestData data;
            boolean ok;

            public void setData(LombokPatternOffTestData data) {[37:"\n                "]this.data = [38:"data"][37:";"][37:"\n            "]}
        }

        public class LombokSettersFinalField {
            LombokPatternOffTestData data;
            final boolean ok = true;

            public void setData(LombokPatternOffTestData data) {[42:"\n                "]this.data = [43:"data"][42:";"][42:"\n            "]}
        }
    }

    public class ToStringFull {
        LombokPatternOffTestData data;
        boolean ok;

        [47:"@Override"][47:"\n        "]public String toString() {
            return "ToStringFull{[48:"\" +\n                    \""]data=[48:"\" + "]data[48:" +\n                    \""], ok=[48:"\" + "]ok[48:" +\n                    "]'}'[48:";"]
        }

        public class ToStringPartial {
            LombokPatternOffTestData data;
            boolean ok;

            [51:"@Override"][51:"\n            "]public String toString() {[50:"\n                "][50:"return"][50:" "]"ToStringPartial{[52:"\" +\n                        \""]data=[52:"\" + "]data[52:" +\n                        "]'}'[50:";"][50:"\n            "]}
        }

        public class ToStringPartial2 {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [55:"@Override"][55:"\n            "]public String toString() {[54:"\n                "][54:"return"][54:" "]"ToStringPartial{[56:"\" +\n                        \""]data=[56:"\" + "]data[56:" +\n                        "]'}'[54:";"][54:"\n            "]}
        }
    }

    public class EqualsAndHashCodeFull {
        LombokPatternOffTestData data;
        boolean ok;

        [58:"@Override"][58:"\n        "]public boolean equals(Object o) {
            if [59:"("]this == o[59:")"] return true;
            if [60:"("]o == null || [69:"getClass()"] != o.[70:"getClass()"][60:")"] return false;
            [61:"EqualsAndHashCodeFull"] that = [62:"(EqualsAndHashCodeFull) "]o;
            return ok == that.ok && ([63:"data != null ? "]data[63:"."]equals(that.data[63:")"][63:" : "]that.data == null);
        }

        [75:"@Override"][75:"\n        "]public int hashCode() {
            [76:"int"] result = ([77:"data != null ? "]data[77:"."]hashCode()[77:" : "]0);
            result = 31 * result[79:" + ("]ok ? 1 : 0[79:")"];
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokPatternOffTestData data;
            boolean ok;

            [83:"@Override"][83:"\n            "]public boolean equals(Object o) {
                if [84:"("]this == o[84:")"] return true;
                if [85:"("]o == null || [92:"getClass()"] != o.[93:"getClass()"][85:")"] return false;
                [86:"EqualsAndHashCodePartial"] that = [87:"(EqualsAndHashCodePartial) "]o;
                return [88:"data != null ? "]data[88:"."]equals(that.data[88:")"][88:" : "]that.data == null;
            }

            [99:"@Override"][99:"\n            "]public int hashCode() {[98:"\n                "][98:"return"][98:" "][100:"data != null ? "]data[100:"."]hashCode()[100:" : "]0[98:";"][98:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [102:"@Override"][102:"\n            "]public boolean equals(Object o) {
                if [103:"("]this == o[103:")"] return true;
                if [104:"("]o == null || [113:"getClass()"] != o.[114:"getClass()"][104:")"] return false;
                [105:"EqualsAndHashCodePartialTwo"] that = [106:"(EqualsAndHashCodePartialTwo) "]o;
                return ok == that.ok && ([107:"data != null ? "]data[107:"."]equals(that.data[107:")"][107:" : "]that.data == null);
            }

            [119:"@Override"][119:"\n            "]public int hashCode() {
                [120:"int"] result = ([121:"data != null ? "]data[121:"."]hashCode()[121:" : "]0);
                result = 31 * result[123:" + ("]ok ? 1 : 0[123:")"];
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokPatternOffTestData data;
        boolean ok;

        [127:"@Override"][127:"\n        "]public boolean equals(Object o) {
            if [128:"("]this == o[128:")"] return true;
            if [129:"("]o == null || [138:"getClass()"] != o.[139:"getClass()"][129:")"] return false;
            [130:"EqualsFull"] that = [131:"(EqualsFull) "]o;
            return ok == that.ok && ([132:"data != null ? "]data[132:"."]equals(that.data[132:")"][132:" : "]that.data == null);
        }

        public class EqualsPartial {
            LombokPatternOffTestData data;
            boolean ok;

            [144:"@Override"][144:"\n            "]public boolean equals(Object o) {
                if [145:"("]this == o[145:")"] return true;
                if [146:"("]o == null || [153:"getClass()"] != o.[154:"getClass()"][146:")"] return false;
                [147:"EqualsPartial"] that = [148:"(EqualsPartial) "]o;
                return [149:"data != null ? "]data[149:"."]equals(that.data[149:")"][149:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [159:"@Override"][159:"\n            "]public boolean equals(Object o) {
                if [160:"("]this == o[160:")"] return true;
                if [161:"("]o == null || [170:"getClass()"] != o.[171:"getClass()"][161:")"] return false;
                [162:"EqualsPartialTwo"] that = [163:"(EqualsPartialTwo) "]o;
                return ok == that.ok && ([164:"data != null ? "]data[164:"."]equals(that.data[164:")"][164:" : "]that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokPatternOffTestData data;
        boolean ok;

        [176:"@Override"][176:"\n        "]public int hashCode() {
            [177:"int"] result = ([178:"data != null ? "]data[178:"."]hashCode()[178:" : "]0);
            result = 31 * result[180:" + ("]ok ? 1 : 0[180:")"];
            return result;
        }

        public class HashCodePartial {
            LombokPatternOffTestData data;
            boolean ok;

            [185:"@Override"][185:"\n            "]public int hashCode() {[184:"\n                "][184:"return"][184:" "][186:"data != null ? "]data[186:"."]hashCode()[186:" : "]0[184:";"][184:"\n            "]}
        }

        public class HashCodePartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [188:"@Override"][188:"\n            "]public int hashCode() {
                [189:"int"] result = ([190:"data != null ? "]data[190:"."]hashCode()[190:" : "]0);
                result = 31 * result[192:" + ("]ok ? 1 : 0[192:")"];
                return result;
            }
        }
    }

    public class DataFull {
        LombokPatternOffTestData data;
        boolean ok;

        public LombokPatternOffTestData getData() {[196:"\n            "][196:"return"][196:" "]data[196:";"][196:"\n        "]}

        public void setData(LombokPatternOffTestData data) {[197:"\n            "]this.data = [198:"data"][197:";"][197:"\n        "]}

        public boolean isOk() {[202:"\n            "][202:"return"][202:" "]ok[202:";"][202:"\n        "]}

        public void setOk(boolean ok) {[203:"\n            "]this.ok = [204:"ok"][203:";"][203:"\n        "]}

        [208:"@Override"][208:"\n        "]public boolean equals(Object o) {
            if [209:"("]this == o[209:")"] return true;
            if [210:"("]!(o instanceof DataFull)[210:")"] return false;
            [211:"DataFull"] dataFull = [212:"(DataFull) "]o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[213:"isEquals()"];
        }

        [220:"@Override"][220:"\n        "]public int hashCode() {[219:"\n            "][219:"return"][219:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[219:";"][219:"\n        "]}

        [221:"@Override"][221:"\n        "]public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        public class DataWithoutToString {
            LombokPatternOffTestData data;
            boolean ok;

            public LombokPatternOffTestData getData() {[222:"\n                "][222:"return"][222:" "]data[222:";"][222:"\n            "]}

            public void setData(LombokPatternOffTestData data) {[223:"\n                "]this.data = [224:"data"][223:";"][223:"\n            "]}

            public boolean isOk() {[228:"\n                "][228:"return"][228:" "]ok[228:";"][228:"\n            "]}

            public void setOk(boolean ok) {[229:"\n                "]this.ok = [230:"ok"][229:";"][229:"\n            "]}

            [234:"@Override"][234:"\n            "]public boolean equals(Object o) {
                if [235:"("]this == o[235:")"] return true;
                if [236:"("]!(o instanceof LombokPatternOffTestData.DataFull)[236:")"] return false;
                [237:"LombokPatternOffTestData.DataFull"] dataFull = [238:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[239:"isEquals()"];
            }

            [246:"@Override"][246:"\n            "]public int hashCode() {[245:"\n                "][245:"return"][245:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[245:";"][245:"\n            "]}

        }

        public class DataWithPartialGetters {
            LombokPatternOffTestData data;
            boolean ok;

            public LombokPatternOffTestData getData() {[247:"\n                "][247:"return"][247:" "]data[247:";"][247:"\n            "]}

            public void setData(LombokPatternOffTestData data) {[248:"\n                "]this.data = [249:"data"][248:";"][248:"\n            "]}

            public void setOk(boolean ok) {[253:"\n                "]this.ok = [254:"ok"][253:";"][253:"\n            "]}

            [258:"@Override"][258:"\n            "]public boolean equals(Object o) {
                if [259:"("]this == o[259:")"] return true;
                if [260:"("]!(o instanceof LombokPatternOffTestData.DataFull)[260:")"] return false;
                [261:"LombokPatternOffTestData.DataFull"] dataFull = [262:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[263:"isEquals()"];
            }

            [270:"@Override"][270:"\n            "]public int hashCode() {[269:"\n                "][269:"return"][269:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[269:";"][269:"\n            "]}

            [271:"@Override"][271:"\n            "]public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        public class DataWithPartialSetters {
            LombokPatternOffTestData data;
            boolean ok;

            public LombokPatternOffTestData getData() {[272:"\n                "][272:"return"][272:" "]data[272:";"][272:"\n            "]}

            public void setData(LombokPatternOffTestData data) {[273:"\n                "]this.data = [274:"data"][273:";"][273:"\n            "]}

            public boolean isOk() {[278:"\n                "][278:"return"][278:" "]ok[278:";"][278:"\n            "]}

            [279:"@Override"][279:"\n            "]public boolean equals(Object o) {
                if [280:"("]this == o[280:")"] return true;
                if [281:"("]!(o instanceof LombokPatternOffTestData.DataFull)[281:")"] return false;
                [282:"LombokPatternOffTestData.DataFull"] dataFull = [283:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[284:"isEquals()"];
            }

            [291:"@Override"][291:"\n            "]public int hashCode() {[290:"\n                "][290:"return"][290:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[290:";"][290:"\n            "]}

            [292:"@Override"][292:"\n            "]public String toString() {
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

            public boolean isOk() {[293:"\n                "][293:"return"][293:" "]ok[293:";"][293:"\n            "]}
        }

        class FoldOnClass {
            boolean ok;

            public boolean isOk() {[294:"\n                "][294:"return"][294:" "]ok[294:";"][294:"\n            "]}
        }

        @SuppressWarnings("ALL")
        class FoldOnWithAnnotation {
            boolean ok;

            public boolean isOk() {[295:"\n                "][295:"return"][295:" "]ok[295:";"][295:"\n            "]}
        }
    }

    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {[296:"\n            "][296:"return"][296:" "]dirty2[296:";"][296:"\n        "]}

        public boolean isDirty2() {[297:"\n            "][297:"return"][297:" "]dirty[297:";"][297:"\n        "]}

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public boolean isDirty() {[298:"\n                "][298:"return"][298:" "]!dirty[298:";"][298:"\n            "]}

            public boolean isOk() {[299:"\n                "][299:"return"][299:" "]ok[299:";"][299:"\n            "]}

            [300:"@Override"][300:"\n            "]public boolean equals(Object o) {
                if [301:"("]this == o[301:")"] return true;
                if [302:"("]!(o instanceof DirtyData)[302:")"] return false;

                [303:"DirtyData"] dirtyData = [304:"(DirtyData) "]o;

                if [305:"("]dirty != dirtyData.dirty[305:")"] return false;
                if [306:"("]ok != dirtyData.ok[306:")"] return false;

                return true;
            }

            [313:"@Override"][313:"\n            "]public int hashCode() {
                [314:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[315:" + ("]ok ? 1 : 0[315:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public boolean isOk() {[318:"\n                "][318:"return"][318:" "]ok[318:";"][318:"\n            "]}

            public boolean isDirty() {[319:"\n                "][319:"return"][319:" "]dirty2[319:";"][319:"\n            "]}
        }
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {[320:"\n            "]this.dirty2 = dirty[320:";"][320:"\n        "]}

        public void setDirty2(boolean dirty2) {[321:"\n            "]this.dirty = dirty2[321:";"][321:"\n        "]}

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty) {[322:"\n                "]this.dirty = !dirty[322:";"][322:"\n            "]}

            public void setOk(boolean ok) {[323:"\n                "]this.ok = [324:"ok"][323:";"][323:"\n            "]}

            public boolean isDirty() {[328:"\n                "][328:"return"][328:" "]dirty[328:";"][328:"\n            "]}

            public boolean isOk() {[329:"\n                "][329:"return"][329:" "]ok[329:";"][329:"\n            "]}

            [330:"@Override"][330:"\n            "]public boolean equals(Object o) {
                if [331:"("]this == o[331:")"] return true;
                if [332:"("]!(o instanceof DirtyData)[332:")"] return false;

                [333:"DirtyData"] dirtyData = [334:"(DirtyData) "]o;

                if [335:"("]dirty != dirtyData.dirty[335:")"] return false;
                if [336:"("]ok != dirtyData.ok[336:")"] return false;

                return true;
            }

            [343:"@Override"][343:"\n            "]public int hashCode() {
                [344:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[345:" + ("]ok ? 1 : 0[345:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {[348:"\n                "]this.ok = [349:"ok"][348:";"][348:"\n            "]}

            public void setDirty(boolean dirty) {[353:"\n                "]this.ok = dirty[353:";"][353:"\n            "]}
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
            [354:"public static final "][354:"Logger"] logger = Logger.getLogger("LogAnnotation.class");
        }

        public class LogCustomNameDeprecated {
            @Deprecated
            [355:"static final "][355:"Logger"] xlogger = Logger.getLogger("LogAnnotation.class");
        }

        public class TripleLogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
            [356:"public static final "][356:"Logger"] log2 = Logger.getLogger("LogAnnotation.class");
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
        class NoArgsConstructorPrivate {
            private NoArgsConstructorPrivate() {
            }
        }
        public class NoArgsConstructorSuperBefore {
            public NoArgsConstructorSuperBefore() {[357:"\n                "]// comment[357:"\n                "]super()[357:";"][357:"\n            "]}
        }
        public class NoArgsConstructorSuperAfter {
            public NoArgsConstructorSuperAfter() {[358:"\n                "]super()[358:";"][358:"\n                "]// comment[358:"\n            "]}
        }

        public class ProtectedNoArgsConstructorSuperAfter {
            protected ProtectedNoArgsConstructorSuperAfter() {
                // comment
            }
        }
        public class NoArgsConstructorSuper {
            private String field1;
            public NoArgsConstructorSuper() {[359:"\n                "]super()[359:";"][359:"\n            "]}
        }
        public class NoArgsConstructorSuperParent extends Paren[360:"t"] {
            public NoArgsConstructorSuperParent() {[361:"\n                "]super(null)[361:";"][361:"\n            "]}
        }

    }

    public class AllArgsConstructorAnnotation {
        public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgs(String field1, int field2, boolean field3) {
                this.field1 = [362:"field1"];
                this.field2 = [363:"field2"];
                this.field3 = [364:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [368:"field1"];
                this.field2 = field1;
                this.field3 = [369:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [372:"field1"];
                this.field1 = field2;
                this.field3 = [373:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [376:"field1"];
                this.field2 = [377:"field2"];
                this.field3 = [378:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [382:"field1"];
                this.field2 = [383:"field2"];
                this.field3 = [384:"field3"];
                // comment
            }
        }

        public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [388:"field1"];
                this.field2 = [389:"field2"];
                this.field3 = [390:"field3"];
            }
        }

        public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = [394:"field1"];
                this.field2 = [395:"field2"];
                this.field3 = [396:"field3"];
            }
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[400:"\n                "][400:"return"][400:" "]new StaticNameArgs(field1, field2, field3)[400:";"][400:"\n            "]}
        }

        public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = [401:"field1"];
                this.field2 = [402:"field2"];
                this.field3 = [403:"field3"];
            }
        }
    }


    public class RequiredArgsConstructorAnnotation {
        public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = [407:"field1"];
                this.field2 = [408:"field2"];
                this.field3 = [409:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [413:"field1"];
                this.field2 = [414:"field2"];
                this.field3 = [415:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [419:"field1"];
                this.field2 = [420:"field2"];
                this.field3 = [421:"field3"];
                // comment
            }
        }

        public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [425:"field1"];
                this.field2 = [426:"field2"];
                this.field3 = [427:"field3"];
            }
        }

        public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = [431:"field1"];
                this.field2 = [432:"field2"];
                this.field3 = [433:"field3"];
            }

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[437:"\n                "][437:"return"][437:" "]new StaticNameArgs(field1, field2, field3)[437:";"][437:"\n            "]}
        }

        public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = [438:"field1"];
                this.field2 = [439:"field2"];
                this.field3 = [440:"field3"];
            }
        }
    }

    public class ValueAnnotation {
        public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = [444:"field1"];
                this.field2 = [445:"field2"];
                this.field3 = [446:"field3"];
            }
            public String getField1() {[450:"\n                "][450:"return"][450:" "]field1[450:";"][450:"\n            "]}
            public int getField2() {[451:"\n                "][451:"return"][451:" "]field2[451:";"][451:"\n            "]}
            public boolean isField3() {[452:"\n                "][452:"return"][452:" "]field3[452:";"][452:"\n            "]}
            [453:"@Override"][453:"\n            "]public boolean equals(Object o) {
                if [454:"("]this == o[454:")"] return true;
                if [455:"("]o == null || [464:"getClass()"] != o.[465:"getClass()"][455:")"] return false;
                [456:"ValueArgs"] valueArgs = [457:"(ValueArgs) "]o;
                if [458:"("]field2 != valueArgs.field2[458:")"] return false;
                if [459:"("]field3 != valueArgs.field3[459:")"] return false;
                return [460:"field1 != null ? "]field1[460:"."]equals(valueArgs.field1[460:")"][460:" : "]valueArgs.field1 == null;
            }
            [472:"@Override"][472:"\n            "]public int hashCode() {
                [473:"int"] result = [474:"field1 != null ? "]field1[474:"."]hashCode()[474:" : "]0;
                result = 31 * result + field2;
                result = 31 * result[475:" + ("]field3 ? 1 : 0[475:")"];
                return result;
            }

            [479:"@Override"][479:"\n            "]public String toString() {
                return "ValueArgs{[480:"\" +\n                        \""]field1='[480:"\" + "]field1[480:" + "]'\''[480:" +\n                        \""], field2=[480:"\" + "]field2[480:" +\n                        \""], field3=[480:"\" + "]field3[480:" +\n                        "]'}'[480:";"]
            }
        }
        public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [482:"field1"];
                this.field2 = [483:"field2"];
                this.field3 = [484:"field3"];
            }
            public String getField1() {[488:"\n                "][488:"return"][488:" "]field1[488:";"][488:"\n            "]}
            public int getField2() {[489:"\n                "][489:"return"][489:" "]field2[489:";"][489:"\n            "]}
            public boolean isField3() {[490:"\n                "][490:"return"][490:" "]field3[490:";"][490:"\n            "]}
            [491:"@Override"][491:"\n            "]public [492:"final"] boolean equals(Object o) {
                if [493:"("]this == o[493:")"] return true;
                if [494:"("]!(o instanceof ValueArgsSuper)[494:")"] return false;

                [495:"ValueArgsSuper"] that = [496:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }

            [502:"@Override"][502:"\n            "]public int hashCode() {
                [503:"int"] result = Objects.hashCode(field1);
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
                this.field1 = [505:"field1"];
                this.field2 = [506:"field2"];
                this.field3 = [507:"field3"];
            }
            public String getField1() {[511:"\n                "][511:"return"][511:" "]field1[511:";"][511:"\n            "]}
            public int getField2() {[512:"\n                "][512:"return"][512:" "]field2[512:";"][512:"\n            "]}
            public boolean isField3() {[513:"\n                "][513:"return"][513:" "]field3[513:";"][513:"\n            "]}
        }
    }
    class SingleField {
        public static class AllArgs {
            private String field1;
            public AllArgs(String field1) {[514:"\n                "]this.field1 = [515:"field1"][514:";"][514:"\n            "]}
        }
        public static class ReqArgs {
            private final String field1;
            public ReqArgs(String field1) {[519:"\n                "]this.field1 = [520:"field1"][519:";"][519:"\n            "]}
        }
        public static class Value {
            private final String field1;
            public Value(String field1) {[524:"\n                "]this.field1 = [525:"field1"][524:";"][524:"\n            "]}
            public String getField1() {[529:"\n                "][529:"return"][529:" "]field1[529:";"][529:"\n            "]}
            [530:"@Override"][530:"\n            "]public [531:"final"] boolean equals(Object o) {
                if [532:"("]this == o[532:")"] return true;
                if [533:"("]!(o instanceof Value)[533:")"] return false;

                [534:"Value"] value = [535:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }
            [542:"@Override"][542:"\n            "]public int hashCode() {[541:"\n                "][541:"return"][541:" "]Objects.hashCode(field1)[541:";"][541:"\n            "]}
        }
        public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1) {[543:"\n                "]this.field1 = [544:"field1"][543:";"][543:"\n            "]}
            public String getField1() {[548:"\n                "][548:"return"][548:" "]field1[548:";"][548:"\n            "]}
        }
        class Modifers {
            static class AllArgsDefault {
                private String field1;
                AllArgsDefault(String field1) {[549:"\n                    "]this.field1 = [550:"field1"][549:";"][549:"\n                "]}
            }
            static class AllArgsPrivate {
                private String field1;
                private AllArgsPrivate(String field1) {[554:"\n                    "]this.field1 = [555:"field1"][554:";"][554:"\n                "]}
            }
            static class AllArgsProteced {
                private String field1;
                protected AllArgsProteced(String field1) {[559:"\n                    "]this.field1 = [560:"field1"][559:";"][559:"\n                "]}
            }
        }
    }

    class FieldLevelData {
        private String name;
        private String ignored;

        public String getName() {[564:"\n            "][564:"return"][564:" "]name[564:";"][564:"\n        "]}

        public void setName(String name) {[565:"\n            "]this.name = [566:"name"][565:";"][565:"\n        "]}

        [570:"@Override"][570:"\n        "]public [571:"final"] boolean equals(Object o) {
            if [572:"("]this == o[572:")"] return true;
            if [573:"("]!(o instanceof FieldLevelData)[573:")"] return false;
            [574:"FieldLevelData"] that = [575:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [582:"@Override"][582:"\n        "]public int hashCode() {[581:"\n            "][581:"return"][581:" "]Objects.hashCode(name)[581:";"][581:"\n        "]}
    }

    class FieldLevelValue {
        private final String name = "1";
        private String ignored;

        public String getName() {[583:"\n            "][583:"return"][583:" "]name[583:";"][583:"\n        "]}

        [584:"@Override"][584:"\n        "]public [585:"final"] boolean equals(Object o) {
            if [586:"("]this == o[586:")"] return true;
            if [587:"("]!(o instanceof FieldLevelData)[587:")"] return false;
            [588:"FieldLevelData"] that = [589:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [596:"@Override"][596:"\n        "]public int hashCode() {[595:"\n            "][595:"return"][595:" "]Objects.hashCode(name)[595:";"][595:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        private String name;
        private String ignored;

        public String getName() {[597:"\n            "][597:"return"][597:" "]name[597:";"][597:"\n        "]}

        [598:"@Override"][598:"\n        "]public [599:"final"] boolean equals(Object o) {
            if [600:"("]this == o[600:")"] return true;
            if [601:"("]!(o instanceof FieldLevelData)[601:")"] return false;
            [602:"FieldLevelData"] that = [603:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [610:"@Override"][610:"\n        "]public int hashCode() {[609:"\n            "][609:"return"][609:" "]Objects.hashCode(name)[609:";"][609:"\n        "]}
    }

    class ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [611:"name"];
                return this;
            }
            public ClassWithBuilder build() {[613:"\n                "][613:"return"][613:" "]new ClassWithBuilder()[613:";"][613:"\n            "]}
        }
    }

    class Constructors {
        public class FiveConstructors {
            private int field1;
            private String field2;
            private double field3;
            private boolean field4;
            public FiveConstructors() {
            }
            public FiveConstructors(int field1) {[614:"\n                "]this.field1 = [615:"field1"][614:";"][614:"\n            "]}
            public FiveConstructors(int field1, String field2) {
                this.field1 = [619:"field1"];
                this.field2 = [620:"field2"];
            }
            public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = [623:"field1"];
                this.field2 = [624:"field2"];
                this.field3 = [625:"field3"];
            }
            public FiveConstructors(int field1, String field2, double field3, boolean field4) {
                this.field1 = [629:"field1"];
                this.field2 = [630:"field2"];
                this.field3 = [631:"field3"];
                this.field4 = [632:"field4"];
            }
        }

        public class FiveConstructorsMod {
            private int field1;
            private String field2;
            private double field3;
            private boolean field4;
            public FiveConstructorsMod() {
            }
            private FiveConstructorsMod(int field1) {[637:"\n                "]this.field1 = [638:"field1"][637:";"][637:"\n            "]}
            FiveConstructorsMod(int field1, String field2) {
                this.field1 = [642:"field1"];
                this.field2 = [643:"field2"];
            }
            protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = [646:"field1"];
                this.field2 = [647:"field2"];
                this.field3 = [648:"field3"];
            }
            public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {
                this.field1 = [652:"field1"];
                this.field2 = [653:"field2"];
                this.field3 = [654:"field3"];
                this.field4 = [655:"field4"];
            }
        }
    }

}
