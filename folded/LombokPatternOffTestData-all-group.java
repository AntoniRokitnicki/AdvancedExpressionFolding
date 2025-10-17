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

    public void setData(LombokPatternOffTestData data) {[2:"\n        "]this.data = [3:"data"][4:"data"][5:"data"][6:"data"][2:";"][2:"\n    "]}

    public boolean isOk() {[7:"\n        "][7:"return"][7:" "]ok[7:";"][7:"\n    "]}

    public void setOk(boolean ok) {[8:"\n        "]this.ok = [9:"ok"][10:"ok"][11:"ok"][12:"ok"][8:";"][8:"\n    "]}

    public String getString() {[13:"\n        "][13:"return"][13:" "]string[13:";"][13:"\n    "]}

    public void setString(String string) {[14:"\n        "]this.string = [15:"string"][16:"string"][17:"string"][18:"string"][14:";"][14:"\n    "]}

    public Optional<LombokPatternOffTestData> asOptional() {[19:"\n        "][19:"return"][19:" "][20:"Optional.ofNullable("][21:"Optional.ofNullable("]data[20:")"][21:")"][19:";"][19:"\n    "]}

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

        public void setData(LombokPatternOffTestData data) {[26:"\n            "]this.data = [27:"data"][28:"data"][29:"data"][30:"data"][26:";"][26:"\n        "]}

        public boolean isOk() {[31:"\n            "][31:"return"][31:" "]ok[31:";"][31:"\n        "]}

        public void setOk(boolean ok) {[32:"\n            "]this.ok = [33:"ok"][34:"ok"][35:"ok"][36:"ok"][32:";"][32:"\n        "]}

        public class LombokSettersPartial {
            LombokPatternOffTestData data;
            boolean ok;

            public void setData(LombokPatternOffTestData data) {[37:"\n                "]this.data = [38:"data"][39:"data"][40:"data"][41:"data"][37:";"][37:"\n            "]}
        }

        public class LombokSettersFinalField {
            LombokPatternOffTestData data;
            final boolean ok = true;

            public void setData(LombokPatternOffTestData data) {[42:"\n                "]this.data = [43:"data"][44:"data"][45:"data"][46:"data"][42:";"][42:"\n            "]}
        }
    }

    public class ToStringFull {
        LombokPatternOffTestData data;
        boolean ok;

        [47:"@Override"][47:"\n        "]public String toString() {
            return "ToStringFull{[48:"\" +\n                    \""][49:"\" +\n                    \""]data=[48:"\" + "][49:"\" + "]data[48:" +\n                    \""][49:" +\n                    \""], ok=[48:"\" + "][49:"\" + "]ok[48:" +\n                    "][48:" +\n                    "][49:" +\n                    "][49:" +\n                    "]'}'[48:";"][49:";"]
        }

        public class ToStringPartial {
            LombokPatternOffTestData data;
            boolean ok;

            [51:"@Override"][51:"\n            "]public String toString() {[50:"\n                "][50:"return"][50:" "]"ToStringPartial{[52:"\" +\n                        \""][53:"\" +\n                        \""]data=[52:"\" + "][53:"\" + "]data[52:" +\n                        "][52:" +\n                        "][53:" +\n                        "][53:" +\n                        "]'}'[50:";"][52:";"][53:";"][50:"\n            "]}
        }

        public class ToStringPartial2 {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [55:"@Override"][55:"\n            "]public String toString() {[54:"\n                "][54:"return"][54:" "]"ToStringPartial{[56:"\" +\n                        \""][57:"\" +\n                        \""]data=[56:"\" + "][57:"\" + "]data[56:" +\n                        "][56:" +\n                        "][57:" +\n                        "][57:" +\n                        "]'}'[54:";"][56:";"][57:";"][54:"\n            "]}
        }
    }

    public class EqualsAndHashCodeFull {
        LombokPatternOffTestData data;
        boolean ok;

        [58:"@Override"][58:"\n        "]public boolean equals(Object o) {
            if [59:"("][67:"("]this == o[59:")"][67:")"] return true;
            if [60:"("][68:"("]o == null || [69:"getClass()"] != o.[70:"getClass()"][60:")"][68:")"] return false;
            [61:"EqualsAndHashCodeFull"][71:"EqualsAndHashCodeFull"] that = [62:"(EqualsAndHashCodeFull) "][72:"(EqualsAndHashCodeFull) "]o;
            return ok == that.ok && ([63:"data != null ? "][65:"data != null ? "][73:"data != null ? "]data[63:"."][65:"."][73:"."]64:".equals("][66:".equals("][74:".equals("]that.data[63:")"][64:")"][65:")"][66:")"][73:")"][74:")"][63:" : "][65:" : "][73:" : "]that.data == null);
        }

        [75:"@Override"][75:"\n        "]public int hashCode() {
            [76:"int"][80:"int"] result = ([77:"data != null ? "][78:"data != null ? "][81:"data != null ? "]data[77:"."][78:"."][81:"."]hashCode()[77:" : "][78:" : "][81:" : "]0);
            result = 31 * result[79:" + ("][82:" + ("]ok ? 1 : 0[79:")"][82:")"];
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokPatternOffTestData data;
            boolean ok;

            [83:"@Override"][83:"\n            "]public boolean equals(Object o) {
                if [84:"("][90:"("]this == o[84:")"][90:")"] return true;
                if [85:"("][91:"("]o == null || [92:"getClass()"] != o.[93:"getClass()"][85:")"][91:")"] return false;
                [86:"EqualsAndHashCodePartial"][94:"EqualsAndHashCodePartial"] that = [87:"(EqualsAndHashCodePartial) "][95:"(EqualsAndHashCodePartial) "]o;
                return [88:"data != null ? "][96:"data != null ? "]data[88:"."][96:"."]89:".equals("][97:".equals("]that.data[88:")"][89:")"][96:")"][97:")"][88:" : "][96:" : "]that.data == null;
            }

            [99:"@Override"][99:"\n            "]public int hashCode() {[98:"\n                "][98:"return"][98:" "][100:"data != null ? "][101:"data != null ? "]data[100:"."][101:"."]hashCode()[100:" : "][101:" : "]0[98:";"][98:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [102:"@Override"][102:"\n            "]public boolean equals(Object o) {
                if [103:"("][111:"("]this == o[103:")"][111:")"] return true;
                if [104:"("][112:"("]o == null || [113:"getClass()"] != o.[114:"getClass()"][104:")"][112:")"] return false;
                [105:"EqualsAndHashCodePartialTwo"][115:"EqualsAndHashCodePartialTwo"] that = [106:"(EqualsAndHashCodePartialTwo) "][116:"(EqualsAndHashCodePartialTwo) "]o;
                return ok == that.ok && ([107:"data != null ? "][109:"data != null ? "][117:"data != null ? "]data[107:"."][109:"."][117:"."]108:".equals("][110:".equals("][118:".equals("]that.data[107:")"][108:")"][109:")"][110:")"][117:")"][118:")"][107:" : "][109:" : "][117:" : "]that.data == null);
            }

            [119:"@Override"][119:"\n            "]public int hashCode() {
                [120:"int"][124:"int"] result = ([121:"data != null ? "][122:"data != null ? "][125:"data != null ? "]data[121:"."][122:"."][125:"."]hashCode()[121:" : "][122:" : "][125:" : "]0);
                result = 31 * result[123:" + ("][126:" + ("]ok ? 1 : 0[123:")"][126:")"];
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokPatternOffTestData data;
        boolean ok;

        [127:"@Override"][127:"\n        "]public boolean equals(Object o) {
            if [128:"("][136:"("]this == o[128:")"][136:")"] return true;
            if [129:"("][137:"("]o == null || [138:"getClass()"] != o.[139:"getClass()"][129:")"][137:")"] return false;
            [130:"EqualsFull"][140:"EqualsFull"] that = [131:"(EqualsFull) "][141:"(EqualsFull) "]o;
            return ok == that.ok && ([132:"data != null ? "][134:"data != null ? "][142:"data != null ? "]data[132:"."][134:"."][142:"."]133:".equals("][135:".equals("][143:".equals("]that.data[132:")"][133:")"][134:")"][135:")"][142:")"][143:")"][132:" : "][134:" : "][142:" : "]that.data == null);
        }

        public class EqualsPartial {
            LombokPatternOffTestData data;
            boolean ok;

            [144:"@Override"][144:"\n            "]public boolean equals(Object o) {
                if [145:"("][151:"("]this == o[145:")"][151:")"] return true;
                if [146:"("][152:"("]o == null || [153:"getClass()"] != o.[154:"getClass()"][146:")"][152:")"] return false;
                [147:"EqualsPartial"][155:"EqualsPartial"] that = [148:"(EqualsPartial) "][156:"(EqualsPartial) "]o;
                return [149:"data != null ? "][157:"data != null ? "]data[149:"."][157:"."]150:".equals("][158:".equals("]that.data[149:")"][150:")"][157:")"][158:")"][149:" : "][157:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [159:"@Override"][159:"\n            "]public boolean equals(Object o) {
                if [160:"("][168:"("]this == o[160:")"][168:")"] return true;
                if [161:"("][169:"("]o == null || [170:"getClass()"] != o.[171:"getClass()"][161:")"][169:")"] return false;
                [162:"EqualsPartialTwo"][172:"EqualsPartialTwo"] that = [163:"(EqualsPartialTwo) "][173:"(EqualsPartialTwo) "]o;
                return ok == that.ok && ([164:"data != null ? "][166:"data != null ? "][174:"data != null ? "]data[164:"."][166:"."][174:"."]165:".equals("][167:".equals("][175:".equals("]that.data[164:")"][165:")"][166:")"][167:")"][174:")"][175:")"][164:" : "][166:" : "][174:" : "]that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokPatternOffTestData data;
        boolean ok;

        [176:"@Override"][176:"\n        "]public int hashCode() {
            [177:"int"][181:"int"] result = ([178:"data != null ? "][179:"data != null ? "][182:"data != null ? "]data[178:"."][179:"."][182:"."]hashCode()[178:" : "][179:" : "][182:" : "]0);
            result = 31 * result[180:" + ("][183:" + ("]ok ? 1 : 0[180:")"][183:")"];
            return result;
        }

        public class HashCodePartial {
            LombokPatternOffTestData data;
            boolean ok;

            [185:"@Override"][185:"\n            "]public int hashCode() {[184:"\n                "][184:"return"][184:" "][186:"data != null ? "][187:"data != null ? "]data[186:"."][187:"."]hashCode()[186:" : "][187:" : "]0[184:";"][184:"\n            "]}
        }

        public class HashCodePartialTwo {
            LombokPatternOffTestData data;
            boolean ok;
            String string;

            [188:"@Override"][188:"\n            "]public int hashCode() {
                [189:"int"][193:"int"] result = ([190:"data != null ? "][191:"data != null ? "][194:"data != null ? "]data[190:"."][191:"."][194:"."]hashCode()[190:" : "][191:" : "][194:" : "]0);
                result = 31 * result[192:" + ("][195:" + ("]ok ? 1 : 0[192:")"][195:")"];
                return result;
            }
        }
    }

    public class DataFull {
        LombokPatternOffTestData data;
        boolean ok;

        public LombokPatternOffTestData getData() {[196:"\n            "][196:"return"][196:" "]data[196:";"][196:"\n        "]}

        public void setData(LombokPatternOffTestData data) {[197:"\n            "]this.data = [198:"data"][199:"data"][200:"data"][201:"data"][197:";"][197:"\n        "]}

        public boolean isOk() {[202:"\n            "][202:"return"][202:" "]ok[202:";"][202:"\n        "]}

        public void setOk(boolean ok) {[203:"\n            "]this.ok = [204:"ok"][205:"ok"][206:"ok"][207:"ok"][203:";"][203:"\n        "]}

        [208:"@Override"][208:"\n        "]public boolean equals(Object o) {
            if [209:"("][214:"("]this == o[209:")"][214:")"] return true;
            if [210:"("][215:"("]!(o instanceof DataFull)[210:")"][215:")"] return false;
            [211:"DataFull"][216:"DataFull"] dataFull = [212:"(DataFull) "][217:"(DataFull) "]o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[213:"isEquals()"][218:"isEquals()"];
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

            public void setData(LombokPatternOffTestData data) {[223:"\n                "]this.data = [224:"data"][225:"data"][226:"data"][227:"data"][223:";"][223:"\n            "]}

            public boolean isOk() {[228:"\n                "][228:"return"][228:" "]ok[228:";"][228:"\n            "]}

            public void setOk(boolean ok) {[229:"\n                "]this.ok = [230:"ok"][231:"ok"][232:"ok"][233:"ok"][229:";"][229:"\n            "]}

            [234:"@Override"][234:"\n            "]public boolean equals(Object o) {
                if [235:"("][240:"("]this == o[235:")"][240:")"] return true;
                if [236:"("][241:"("]!(o instanceof LombokPatternOffTestData.DataFull)[236:")"][241:")"] return false;
                [237:"LombokPatternOffTestData.DataFull"][242:"LombokPatternOffTestData.DataFull"] dataFull = [238:"(LombokPatternOffTestData.DataFull) "][243:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[239:"isEquals()"][244:"isEquals()"];
            }

            [246:"@Override"][246:"\n            "]public int hashCode() {[245:"\n                "][245:"return"][245:" "]new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()[245:";"][245:"\n            "]}

        }

        public class DataWithPartialGetters {
            LombokPatternOffTestData data;
            boolean ok;

            public LombokPatternOffTestData getData() {[247:"\n                "][247:"return"][247:" "]data[247:";"][247:"\n            "]}

            public void setData(LombokPatternOffTestData data) {[248:"\n                "]this.data = [249:"data"][250:"data"][251:"data"][252:"data"][248:";"][248:"\n            "]}

            public void setOk(boolean ok) {[253:"\n                "]this.ok = [254:"ok"][255:"ok"][256:"ok"][257:"ok"][253:";"][253:"\n            "]}

            [258:"@Override"][258:"\n            "]public boolean equals(Object o) {
                if [259:"("][264:"("]this == o[259:")"][264:")"] return true;
                if [260:"("][265:"("]!(o instanceof LombokPatternOffTestData.DataFull)[260:")"][265:")"] return false;
                [261:"LombokPatternOffTestData.DataFull"][266:"LombokPatternOffTestData.DataFull"] dataFull = [262:"(LombokPatternOffTestData.DataFull) "][267:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[263:"isEquals()"][268:"isEquals()"];
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

            public void setData(LombokPatternOffTestData data) {[273:"\n                "]this.data = [274:"data"][275:"data"][276:"data"][277:"data"][273:";"][273:"\n            "]}

            public boolean isOk() {[278:"\n                "][278:"return"][278:" "]ok[278:";"][278:"\n            "]}

            [279:"@Override"][279:"\n            "]public boolean equals(Object o) {
                if [280:"("][285:"("]this == o[280:")"][285:")"] return true;
                if [281:"("][286:"("]!(o instanceof LombokPatternOffTestData.DataFull)[281:")"][286:")"] return false;
                [282:"LombokPatternOffTestData.DataFull"][287:"LombokPatternOffTestData.DataFull"] dataFull = [283:"(LombokPatternOffTestData.DataFull) "][288:"(LombokPatternOffTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[284:"isEquals()"][289:"isEquals()"];
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
                if [301:"("][307:"("]this == o[301:")"][307:")"] return true;
                if [302:"("][308:"("]!(o instanceof DirtyData)[302:")"][308:")"] return false;

                [303:"DirtyData"][309:"DirtyData"] dirtyData = [304:"(DirtyData) "][310:"(DirtyData) "]o;

                if [305:"("][311:"("]dirty != dirtyData.dirty[305:")"][311:")"] return false;
                if [306:"("][312:"("]ok != dirtyData.ok[306:")"][312:")"] return false;

                return true;
            }

            [313:"@Override"][313:"\n            "]public int hashCode() {
                [314:"int"][316:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[315:" + ("][317:" + ("]ok ? 1 : 0[315:")"][317:")"];
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

            public void setOk(boolean ok) {[323:"\n                "]this.ok = [324:"ok"][325:"ok"][326:"ok"][327:"ok"][323:";"][323:"\n            "]}

            public boolean isDirty() {[328:"\n                "][328:"return"][328:" "]dirty[328:";"][328:"\n            "]}

            public boolean isOk() {[329:"\n                "][329:"return"][329:" "]ok[329:";"][329:"\n            "]}

            [330:"@Override"][330:"\n            "]public boolean equals(Object o) {
                if [331:"("][337:"("]this == o[331:")"][337:")"] return true;
                if [332:"("][338:"("]!(o instanceof DirtyData)[332:")"][338:")"] return false;

                [333:"DirtyData"][339:"DirtyData"] dirtyData = [334:"(DirtyData) "][340:"(DirtyData) "]o;

                if [335:"("][341:"("]dirty != dirtyData.dirty[335:")"][341:")"] return false;
                if [336:"("][342:"("]ok != dirtyData.ok[336:")"][342:")"] return false;

                return true;
            }

            [343:"@Override"][343:"\n            "]public int hashCode() {
                [344:"int"][346:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[345:" + ("][347:" + ("]ok ? 1 : 0[345:")"][347:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {[348:"\n                "]this.ok = [349:"ok"][350:"ok"][351:"ok"][352:"ok"][348:";"][348:"\n            "]}

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
                this.field1 = [362:"field1"][365:"field1"];
                this.field2 = [363:"field2"][366:"field2"];
                this.field3 = [364:"field3"][367:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [368:"field1"][370:"field1"];
                this.field2 = field1;
                this.field3 = [369:"field3"][371:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [372:"field1"][374:"field1"];
                this.field1 = field2;
                this.field3 = [373:"field3"][375:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [376:"field1"][379:"field1"];
                this.field2 = [377:"field2"][380:"field2"];
                this.field3 = [378:"field3"][381:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [382:"field1"][385:"field1"];
                this.field2 = [383:"field2"][386:"field2"];
                this.field3 = [384:"field3"][387:"field3"];
                // comment
            }
        }

        public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [388:"field1"][391:"field1"];
                this.field2 = [389:"field2"][392:"field2"];
                this.field3 = [390:"field3"][393:"field3"];
            }
        }

        public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = [394:"field1"][397:"field1"];
                this.field2 = [395:"field2"][398:"field2"];
                this.field3 = [396:"field3"][399:"field3"];
            }
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[400:"\n                "][400:"return"][400:" "]new StaticNameArgs(field1, field2, field3)[400:";"][400:"\n            "]}
        }

        public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = [401:"field1"][404:"field1"];
                this.field2 = [402:"field2"][405:"field2"];
                this.field3 = [403:"field3"][406:"field3"];
            }
        }
    }


    public class RequiredArgsConstructorAnnotation {
        public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = [407:"field1"][410:"field1"];
                this.field2 = [408:"field2"][411:"field2"];
                this.field3 = [409:"field3"][412:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [413:"field1"][416:"field1"];
                this.field2 = [414:"field2"][417:"field2"];
                this.field3 = [415:"field3"][418:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [419:"field1"][422:"field1"];
                this.field2 = [420:"field2"][423:"field2"];
                this.field3 = [421:"field3"][424:"field3"];
                // comment
            }
        }

        public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [425:"field1"][428:"field1"];
                this.field2 = [426:"field2"][429:"field2"];
                this.field3 = [427:"field3"][430:"field3"];
            }
        }

        public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = [431:"field1"][434:"field1"];
                this.field2 = [432:"field2"][435:"field2"];
                this.field3 = [433:"field3"][436:"field3"];
            }

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[437:"\n                "][437:"return"][437:" "]new StaticNameArgs(field1, field2, field3)[437:";"][437:"\n            "]}
        }

        public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = [438:"field1"][441:"field1"];
                this.field2 = [439:"field2"][442:"field2"];
                this.field3 = [440:"field3"][443:"field3"];
            }
        }
    }

    public class ValueAnnotation {
        public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = [444:"field1"][447:"field1"];
                this.field2 = [445:"field2"][448:"field2"];
                this.field3 = [446:"field3"][449:"field3"];
            }
            public String getField1() {[450:"\n                "][450:"return"][450:" "]field1[450:";"][450:"\n            "]}
            public int getField2() {[451:"\n                "][451:"return"][451:" "]field2[451:";"][451:"\n            "]}
            public boolean isField3() {[452:"\n                "][452:"return"][452:" "]field3[452:";"][452:"\n            "]}
            [453:"@Override"][453:"\n            "]public boolean equals(Object o) {
                if [454:"("][462:"("]this == o[454:")"][462:")"] return true;
                if [455:"("][463:"("]o == null || [464:"getClass()"] != o.[465:"getClass()"][455:")"][463:")"] return false;
                [456:"ValueArgs"][466:"ValueArgs"] valueArgs = [457:"(ValueArgs) "][467:"(ValueArgs) "]o;
                if [458:"("][468:"("]field2 != valueArgs.field2[458:")"][468:")"] return false;
                if [459:"("][469:"("]field3 != valueArgs.field3[459:")"][469:")"] return false;
                return [460:"field1 != null ? "][470:"field1 != null ? "]field1[460:"."][470:"."]461:".equals("][471:".equals("]valueArgs.field1[460:")"][461:")"][470:")"][471:")"][460:" : "][470:" : "]valueArgs.field1 == null;
            }
            [472:"@Override"][472:"\n            "]public int hashCode() {
                [473:"int"][476:"int"] result = [474:"field1 != null ? "][477:"field1 != null ? "]field1[474:"."][477:"."]hashCode()[474:" : "][477:" : "]0;
                result = 31 * result + field2;
                result = 31 * result[475:" + ("][478:" + ("]field3 ? 1 : 0[475:")"][478:")"];
                return result;
            }

            [479:"@Override"][479:"\n            "]public String toString() {
                return "ValueArgs{[480:"\" +\n                        \""][481:"\" +\n                        \""]field1='[480:"\" + "][481:"\" + "]field1[480:" + "][481:" + "]'\''[480:" +\n                        \""][481:" +\n                        \""], field2=[480:"\" + "][481:"\" + "]field2[480:" +\n                        \""][481:" +\n                        \""], field3=[480:"\" + "][481:"\" + "]field3[480:" +\n                        "][480:" +\n                        "][481:" +\n                        "][481:" +\n                        "]'}'[480:";"][481:";"]
            }
        }
        public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = [482:"field1"][485:"field1"];
                this.field2 = [483:"field2"][486:"field2"];
                this.field3 = [484:"field3"][487:"field3"];
            }
            public String getField1() {[488:"\n                "][488:"return"][488:" "]field1[488:";"][488:"\n            "]}
            public int getField2() {[489:"\n                "][489:"return"][489:" "]field2[489:";"][489:"\n            "]}
            public boolean isField3() {[490:"\n                "][490:"return"][490:" "]field3[490:";"][490:"\n            "]}
            [491:"@Override"][491:"\n            "]public [492:"final"][497:"final"] boolean equals(Object o) {
                if [493:"("][498:"("]this == o[493:")"][498:")"] return true;
                if [494:"("][499:"("]!(o instanceof ValueArgsSuper)[494:")"][499:")"] return false;

                [495:"ValueArgsSuper"][500:"ValueArgsSuper"] that = [496:"(ValueArgsSuper) "][501:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }

            [502:"@Override"][502:"\n            "]public int hashCode() {
                [503:"int"][504:"int"] result = Objects.hashCode(field1);
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
                this.field1 = [505:"field1"][508:"field1"];
                this.field2 = [506:"field2"][509:"field2"];
                this.field3 = [507:"field3"][510:"field3"];
            }
            public String getField1() {[511:"\n                "][511:"return"][511:" "]field1[511:";"][511:"\n            "]}
            public int getField2() {[512:"\n                "][512:"return"][512:" "]field2[512:";"][512:"\n            "]}
            public boolean isField3() {[513:"\n                "][513:"return"][513:" "]field3[513:";"][513:"\n            "]}
        }
    }
    class SingleField {
        public static class AllArgs {
            private String field1;
            public AllArgs(String field1) {[514:"\n                "]this.field1 = [515:"field1"][516:"field1"][517:"field1"][518:"field1"][514:";"][514:"\n            "]}
        }
        public static class ReqArgs {
            private final String field1;
            public ReqArgs(String field1) {[519:"\n                "]this.field1 = [520:"field1"][521:"field1"][522:"field1"][523:"field1"][519:";"][519:"\n            "]}
        }
        public static class Value {
            private final String field1;
            public Value(String field1) {[524:"\n                "]this.field1 = [525:"field1"][526:"field1"][527:"field1"][528:"field1"][524:";"][524:"\n            "]}
            public String getField1() {[529:"\n                "][529:"return"][529:" "]field1[529:";"][529:"\n            "]}
            [530:"@Override"][530:"\n            "]public [531:"final"][536:"final"] boolean equals(Object o) {
                if [532:"("][537:"("]this == o[532:")"][537:")"] return true;
                if [533:"("][538:"("]!(o instanceof Value)[533:")"][538:")"] return false;

                [534:"Value"][539:"Value"] value = [535:"(Value) "][540:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }
            [542:"@Override"][542:"\n            "]public int hashCode() {[541:"\n                "][541:"return"][541:" "]Objects.hashCode(field1)[541:";"][541:"\n            "]}
        }
        public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1) {[543:"\n                "]this.field1 = [544:"field1"][545:"field1"][546:"field1"][547:"field1"][543:";"][543:"\n            "]}
            public String getField1() {[548:"\n                "][548:"return"][548:" "]field1[548:";"][548:"\n            "]}
        }
        class Modifers {
            static class AllArgsDefault {
                private String field1;
                AllArgsDefault(String field1) {[549:"\n                    "]this.field1 = [550:"field1"][551:"field1"][552:"field1"][553:"field1"][549:";"][549:"\n                "]}
            }
            static class AllArgsPrivate {
                private String field1;
                private AllArgsPrivate(String field1) {[554:"\n                    "]this.field1 = [555:"field1"][556:"field1"][557:"field1"][558:"field1"][554:";"][554:"\n                "]}
            }
            static class AllArgsProteced {
                private String field1;
                protected AllArgsProteced(String field1) {[559:"\n                    "]this.field1 = [560:"field1"][561:"field1"][562:"field1"][563:"field1"][559:";"][559:"\n                "]}
            }
        }
    }

    class FieldLevelData {
        private String name;
        private String ignored;

        public String getName() {[564:"\n            "][564:"return"][564:" "]name[564:";"][564:"\n        "]}

        public void setName(String name) {[565:"\n            "]this.name = [566:"name"][567:"name"][568:"name"][569:"name"][565:";"][565:"\n        "]}

        [570:"@Override"][570:"\n        "]public [571:"final"][576:"final"] boolean equals(Object o) {
            if [572:"("][577:"("]this == o[572:")"][577:")"] return true;
            if [573:"("][578:"("]!(o instanceof FieldLevelData)[573:")"][578:")"] return false;
            [574:"FieldLevelData"][579:"FieldLevelData"] that = [575:"(FieldLevelData) "][580:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [582:"@Override"][582:"\n        "]public int hashCode() {[581:"\n            "][581:"return"][581:" "]Objects.hashCode(name)[581:";"][581:"\n        "]}
    }

    class FieldLevelValue {
        private final String name = "1";
        private String ignored;

        public String getName() {[583:"\n            "][583:"return"][583:" "]name[583:";"][583:"\n        "]}

        [584:"@Override"][584:"\n        "]public [585:"final"][590:"final"] boolean equals(Object o) {
            if [586:"("][591:"("]this == o[586:")"][591:")"] return true;
            if [587:"("][592:"("]!(o instanceof FieldLevelData)[587:")"][592:")"] return false;
            [588:"FieldLevelData"][593:"FieldLevelData"] that = [589:"(FieldLevelData) "][594:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [596:"@Override"][596:"\n        "]public int hashCode() {[595:"\n            "][595:"return"][595:" "]Objects.hashCode(name)[595:";"][595:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        private String name;
        private String ignored;

        public String getName() {[597:"\n            "][597:"return"][597:" "]name[597:";"][597:"\n        "]}

        [598:"@Override"][598:"\n        "]public [599:"final"][604:"final"] boolean equals(Object o) {
            if [600:"("][605:"("]this == o[600:")"][605:")"] return true;
            if [601:"("][606:"("]!(o instanceof FieldLevelData)[601:")"][606:")"] return false;
            [602:"FieldLevelData"][607:"FieldLevelData"] that = [603:"(FieldLevelData) "][608:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }

        [610:"@Override"][610:"\n        "]public int hashCode() {[609:"\n            "][609:"return"][609:" "]Objects.hashCode(name)[609:";"][609:"\n        "]}
    }

    class ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [611:"name"][612:"name"];
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
            public FiveConstructors(int field1) {[614:"\n                "]this.field1 = [615:"field1"][616:"field1"][617:"field1"][618:"field1"][614:";"][614:"\n            "]}
            public FiveConstructors(int field1, String field2) {
                this.field1 = [619:"field1"][621:"field1"];
                this.field2 = [620:"field2"][622:"field2"];
            }
            public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = [623:"field1"][626:"field1"];
                this.field2 = [624:"field2"][627:"field2"];
                this.field3 = [625:"field3"][628:"field3"];
            }
            public FiveConstructors(int field1, String field2, double field3, boolean field4) {
                this.field1 = [629:"field1"][633:"field1"];
                this.field2 = [630:"field2"][634:"field2"];
                this.field3 = [631:"field3"][635:"field3"];
                this.field4 = [632:"field4"][636:"field4"];
            }
        }

        public class FiveConstructorsMod {
            private int field1;
            private String field2;
            private double field3;
            private boolean field4;
            public FiveConstructorsMod() {
            }
            private FiveConstructorsMod(int field1) {[637:"\n                "]this.field1 = [638:"field1"][639:"field1"][640:"field1"][641:"field1"][637:";"][637:"\n            "]}
            FiveConstructorsMod(int field1, String field2) {
                this.field1 = [642:"field1"][644:"field1"];
                this.field2 = [643:"field2"][645:"field2"];
            }
            protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = [646:"field1"][649:"field1"];
                this.field2 = [647:"field2"][650:"field2"];
                this.field3 = [648:"field3"][651:"field3"];
            }
            public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {
                this.field1 = [652:"field1"][656:"field1"];
                this.field2 = [653:"field2"][657:"field2"];
                this.field3 = [654:"field3"][658:"field3"];
                this.field4 = [655:"field4"][659:"field4"];
            }
        }
    }

}
