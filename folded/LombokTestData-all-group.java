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
[0:"p"]ublic class LombokTestData {[0:"\n\n    "][0:"private static final long serialVersionUID = 1234567L;"] = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;[0:"\n\n    "][0:"public LombokTestData getData() {\n        return data;\n    }"]][2:" "]data[2:";"][2:"\n    "]}[0:"\n\n    "][0:"public void setData(LombokTestData data) {\n        this.data = data;\n    }"]data"][5:"data"][6:"data"][7:"data"][3:";"][3:"\n    "]}[0:"\n\n    "][0:"public boolean isOk() {\n        return ok;\n    }"]n"][8:" "]ok[8:";"][8:"\n    "]}[0:"\n\n    "][0:"public void setOk(boolean ok) {\n        this.ok = ok;\n    }"]0:"ok"][11:"ok"][12:"ok"][13:"ok"][9:";"][9:"\n    "]}[0:"\n\n    "][0:"public String getString() {\n        return string;\n    }"]][14:" "]string[14:";"][14:"\n    "]}[0:"\n\n    "][0:"public void setString(String string) {\n        this.string = string;\n    }"]string"][17:"string"][18:"string"][19:"string"][15:";"][15:"\n    "]}

    public Optional<LombokTestData> asOptional() {[20:"\n        "][20:"return"][20:" "][21:"Optional.ofNullable("][22:"Optional.ofNullable("]data[21:")"][22:")"][20:";"][20:"\n    "]}

    [23:"p"]ublic class LombokGetters {
        LombokTestData data;
        boolean ok;[23:"\n\n        "][23:"public LombokTestData getData() {\n            return data;\n        }"]24:" "]data[24:";"][24:"\n        "]}[23:"\n\n        "][23:"public boolean isOk() {\n            return ok;\n        }"]][25:" "]ok[25:";"][25:"\n        "]}

        public class LombokGettersPartial {
            LombokTestData data;
            [26:"b"]oolean ok;[26:"\n\n            "][26:"public boolean isOk() {\n                return ok;\n            }"]:" "]ok[27:";"][27:"\n            "]}
        }
    }

    [28:"p"]ublic class LombokSetters {
        LombokTestData data;
        boolean ok;[28:"\n\n        "][28:"public LombokTestData getData() {\n            return data;\n        }"]29:" "]data[29:";"][29:"\n        "]}[28:"\n\n        "][28:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"]ta"][32:"data"][33:"data"][34:"data"][30:";"][30:"\n        "]}[28:"\n\n        "][28:"public boolean isOk() {\n            return ok;\n        }"]][35:" "]ok[35:";"][35:"\n        "]}[28:"\n\n        "][28:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]ok"][38:"ok"][39:"ok"][40:"ok"][36:";"][36:"\n        "]}

        public class LombokSettersPartial {
            [41:"L"]ombokTestData data;
            boolean ok;[41:"\n\n            "][41:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][44:"data"][45:"data"][46:"data"][42:";"][42:"\n            "]}
        }

        public class LombokSettersFinalField {
            [47:"L"]ombokTestData data;
            final boolean ok = true;[47:"\n\n            "][47:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"][50:"data"][51:"data"][52:"data"][48:";"][48:"\n            "]}
        }
    }

    [53:"p"]ublic class ToStringFull {
        LombokTestData data;
        boolean ok;[53:"\n\n        "][54:"@Override"]rride\n        public String toString() {\n            return \"ToStringFull{\" +\n                    \"data=\" + data +\n                    \", ok=\" + ok +\n                    '}';\n        }"]a[55:" +\n                    \""][56:" +\n                    \""], ok=[55:"\" + "][56:"\" + "]ok[55:" +\n                    "][55:" +\n                    "][56:" +\n                    "][56:" +\n                    "]'}'[55:";"][56:";"]
        }

        public class ToStringPartial {
            [57:"L"]ombokTestData data;
            boolean ok;[57:"\n\n            "][59:"@Override"]rride\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]                 \""]data=[60:"\" + "][61:"\" + "]data[60:" +\n                        "][60:" +\n                        "][61:" +\n                        "][61:" +\n                        "]'}'[58:";"][60:";"][61:";"][58:"\n            "]}
        }

        public class ToStringPartial2 {
            [62:"L"]ombokTestData data;
            boolean ok;
            String string;[62:"\n\n            "][64:"@Override"]rride\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]                 \""]data=[65:"\" + "][66:"\" + "]data[65:" +\n                        "][65:" +\n                        "][66:" +\n                        "][66:" +\n                        "]'}'[63:";"][65:";"][66:";"][63:"\n            "]}
        }
    }

    [67:"p"]ublic class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;[67:"\n\n        "][68:"@Override"]rride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]ashCodeFull) "][82:"(EqualsAndHashCodeFull) "]o;
            return ok == that.ok && ([73:"data != null ? "][75:"data != null ? "][83:"data != null ? "]data[73:"."][75:"."][83:"."]74:".equals("][76:".equals("][84:".equals("]that.data[73:")"][74:")"][75:")"][76:")"][83:")"][84:")"][73:" : "][75:" : "][83:" : "]that.data == null);
        }[67:"\n\n        "][85:"@Override"]rride\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]hCode()[87:" : "][88:" : "][91:" : "]0);
            result = 31 * result[89:" + ("][92:" + ("]ok ? 1 : 0[89:")"][92:")"];
            return result;
        }

        public class EqualsAndHashCodePartial {
            [93:"L"]ombokTestData data;
            boolean ok;[93:"\n\n            "][94:"@Override"]rride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"]= [98:"(EqualsAndHashCodePartial) "][106:"(EqualsAndHashCodePartial) "]o;
                return [99:"data != null ? "][107:"data != null ? "]data[99:"."][107:"."]100:".equals("][108:".equals("]that.data[99:")"][100:")"][107:")"][108:")"][99:" : "][107:" : "]that.data == null;
            }[93:"\n\n            "][110:"@Override"]rride\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]ull ? "][112:"data != null ? "]data[111:"."][112:"."]hashCode()[111:" : "][112:" : "]0[109:";"][109:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            [113:"L"]ombokTestData data;
            [114:"b"]oolean ok;
            String string;[113:"\n\n            "][114:"\n\n            "][115:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][114:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"]lsAndHashCodePartialTwo) "][129:"(EqualsAndHashCodePartialTwo) "]o;
                return ok == that.ok && ([120:"data != null ? "][122:"data != null ? "][130:"data != null ? "]data[120:"."][122:"."][130:"."]121:".equals("][123:".equals("][131:".equals("]that.data[120:")"][121:")"][122:")"][123:")"][130:")"][131:")"][120:" : "][122:" : "][130:" : "]that.data == null);
            }[113:"\n\n            "][114:"\n\n            "][132:"@Override"]erride\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][114:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]de()[134:" : "][135:" : "][138:" : "]0);
                result = 31 * result[136:" + ("][139:" + ("]ok ? 1 : 0[136:")"][139:")"];
                return result;
            }
        }
    }

    [140:"p"]ublic class EqualsFull {
        LombokTestData data;
        boolean ok;[140:"\n\n        "][141:"@Override"]erride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsFull that = (EqualsFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]5:"(EqualsFull) "][155:"(EqualsFull) "]o;
            return ok == that.ok && ([146:"data != null ? "][148:"data != null ? "][156:"data != null ? "]data[146:"."][148:"."][156:"."]147:".equals("][149:".equals("][157:".equals("]that.data[146:")"][147:")"][148:")"][149:")"][156:")"][157:")"][146:" : "][148:" : "][156:" : "]that.data == null);
        }

        public class EqualsPartial {
            [158:"L"]ombokTestData data;
            boolean ok;[158:"\n\n            "][159:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartial that = (EqualsPartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"] that = [163:"(EqualsPartial) "][171:"(EqualsPartial) "]o;
                return [164:"data != null ? "][172:"data != null ? "]data[164:"."][172:"."]165:".equals("][173:".equals("]that.data[164:")"][165:")"][172:")"][173:")"][164:" : "][172:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            [174:"L"]ombokTestData data;
            [175:"b"]oolean ok;
            String string;[174:"\n\n            "][175:"\n\n            "][176:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][175:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"]lsPartialTwo) "][190:"(EqualsPartialTwo) "]o;
                return ok == that.ok && ([181:"data != null ? "][183:"data != null ? "][191:"data != null ? "]data[181:"."][183:"."][191:"."]182:".equals("][184:".equals("][192:".equals("]that.data[181:")"][182:")"][183:")"][184:")"][191:")"][192:")"][181:" : "][183:" : "][191:" : "]that.data == null);
            }
        }
    }

    [193:"p"]ublic class HashCodeFull {
        LombokTestData data;
        boolean ok;[193:"\n\n        "][194:"@Override"]erride\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]0:"."]hashCode()[196:" : "][197:" : "][200:" : "]0);
            result = 31 * result[198:" + ("][201:" + ("]ok ? 1 : 0[198:")"][201:")"];
            return result;
        }

        public class HashCodePartial {
            [202:"L"]ombokTestData data;
            boolean ok;[202:"\n\n            "][204:"@Override"]erride\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]ull ? "][206:"data != null ? "]data[205:"."][206:"."]hashCode()[205:" : "][206:" : "]0[203:";"][203:"\n            "]}
        }

        public class HashCodePartialTwo {
            [207:"L"]ombokTestData data;
            [208:"b"]oolean ok;
            String string;[207:"\n\n            "][208:"\n\n            "][209:"@Override"]erride\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][208:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]de()[211:" : "][212:" : "][215:" : "]0);
                result = 31 * result[213:" + ("][216:" + ("]ok ? 1 : 0[213:")"][216:")"];
                return result;
            }
        }
    }

    [217:"p"]ublic class DataFull {
        LombokTestData data;
        boolean ok;[217:"\n\n        "][217:"public LombokTestData getData() {\n            return data;\n        }"]][218:" "]data[218:";"][218:"\n        "]}[217:"\n\n        "][217:"public void setData(LombokTestData data) {\n            this.data = data;\n        }"]data"][221:"data"][222:"data"][223:"data"][219:";"][219:"\n        "]}[217:"\n\n        "][217:"public boolean isOk() {\n            return ok;\n        }"]n"][224:" "]ok[224:";"][224:"\n        "]}[217:"\n\n        "][217:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]:"ok"][227:"ok"][228:"ok"][229:"ok"][225:";"][225:"\n        "]}[217:"\n\n        "][230:"@Override"]erride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof DataFull)) return false;\n            DataFull dataFull = (DataFull) o;\n            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n        }"](DataFull) "]o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[235:"isEquals()"][240:"isEquals()"];
        }[217:"\n\n        "][242:"@Override"]erride\n        public int hashCode() {\n            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n        }"]append(ok).toHashCode()[241:";"][241:"\n        "]}[217:"\n\n        "][243:"@Override"]erride\n        public String toString() {\n            return new ToStringBuilder(this)\n                    .append(\"data\", data)\n                    .append(\"ok\", ok)\n                    .toString();\n        }"]        }

        [244:"p"]ublic class DataWithoutToString {
            LombokTestData data;
            boolean ok;[244:"\n\n            "][244:"public LombokTestData getData() {\n                return data;\n            }"]5:" "]data[245:";"][245:"\n            "]}[244:"\n\n            "][244:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]"][248:"data"][249:"data"][250:"data"][246:";"][246:"\n            "]}[244:"\n\n            "][244:"public boolean isOk() {\n                return ok;\n            }"]251:" "]ok[251:";"][251:"\n            "]}[244:"\n\n            "][244:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][254:"ok"][255:"ok"][256:"ok"][252:";"][252:"\n            "]}[244:"\n\n            "][257:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]][266:"(LombokTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[262:"isEquals()"][267:"isEquals()"];
            }[244:"\n\n            "][269:"@Override"]erride\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]nd(ok).toHashCode()[268:";"][268:"\n            "]}

        }

        [270:"p"]ublic class DataWithPartialGetters {
            [271:"L"]ombokTestData data;
            boolean ok;[271:"\n\n            "][271:"public LombokTestData getData() {\n                return data;\n            }"]2:" "]data[272:";"][272:"\n            "]}[270:"\n\n            "][270:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]"][275:"data"][276:"data"][277:"data"][273:";"][273:"\n            "]}[270:"\n\n            "][270:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][280:"ok"][281:"ok"][282:"ok"][278:";"][278:"\n            "]}[270:"\n\n            "][283:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]][292:"(LombokTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[288:"isEquals()"][293:"isEquals()"];
            }[270:"\n\n            "][295:"@Override"]erride\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]nd(ok).toHashCode()[294:";"][294:"\n            "]}[270:"\n\n            "][296:"@Override"]erride\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]        }
        }

        [297:"p"]ublic class DataWithPartialSetters {
            [298:"L"]ombokTestData data;
            boolean ok;[297:"\n\n            "][297:"public LombokTestData getData() {\n                return data;\n            }"]9:" "]data[299:";"][299:"\n            "]}[298:"\n\n            "][298:"public void setData(LombokTestData data) {\n                this.data = data;\n            }"]"][302:"data"][303:"data"][304:"data"][300:";"][300:"\n            "]}[297:"\n\n            "][297:"public boolean isOk() {\n                return ok;\n            }"]305:" "]ok[305:";"][305:"\n            "]}[297:"\n\n            "][306:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokTestData.DataFull)) return false;\n                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]][315:"(LombokTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[311:"isEquals()"][316:"isEquals()"];
            }[297:"\n\n            "][318:"@Override"]erride\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]nd(ok).toHashCode()[317:";"][317:"\n            "]}[297:"\n\n            "][319:"@Override"]erride\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]        }
        }
    }

    public class FoldOn {
        [320:"p"]ublic class FoldOnPublic {
            boolean ok;[320:"\n\n            "][320:"public boolean isOk() {\n                return ok;\n            }"]321:" "]ok[321:";"][321:"\n            "]}
        }

        [322:"c"]lass FoldOnClass {
            boolean ok;[322:"\n\n            "][322:"public boolean isOk() {\n                return ok;\n            }"]323:" "]ok[323:";"][323:"\n            "]}
        }

        @SuppressWarnings("ALL")
        [324:"c"]lass FoldOnWithAnnotation {
            boolean ok;[324:"\n\n            "][324:"public boolean isOk() {\n                return ok;\n            }"]325:" "]ok[325:";"][325:"\n            "]}
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

            public boolean isDirty() {[330:"\n                "][330:"return"][330:" "]!dirty[330:";"][330:"\n            "]}[329:"\n\n            "][329:"public boolean isOk() {\n                return ok;\n            }"]331:" "]ok[331:";"][331:"\n            "]}[328:"\n\n            "][332:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"]][343:"("]dirty != dirtyData.dirty[337:")"][343:")"] return false;
                if [338:"("][344:"("]ok != dirtyData.ok[338:")"][344:")"] return false;

                return true;
            }[328:"\n\n            "][345:"@Override"]erride\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]0[347:")"][349:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [350:"b"]oolean ok;[350:"\n\n            "][350:"public boolean isOk() {\n                return ok;\n            }"]351:" "]ok[351:";"][351:"\n            "]}

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
            if [354:"("][355:"("]wrapper != null[354:")"][355:")"] {
                [356:"try"][356:" "][356:"{"]
                [356:"    "]return wrapperDeeplyHiddenRef;[356:"\n                "][356:"\n                "][356:"}"][356:" "][356:"catch (Exception e) {\n                    throw new RuntimeException(e);\n                }"]               }
            }
            return null;
        }

        public List<String> getWrapperWrongRef() {[358:"\n            "][358:"return"][358:" "]Collections.unmodifiableList(wrapper)[358:";"][358:"\n        "]}

        public List<String> getLocalMethodWrappedList() {[359:"\n            "][359:"return"][359:" "]localWrap(localMethodWrappedList)[359:";"][359:"\n        "]}

        public List<String> getThisLocalMethodWrappedList() {[360:"\n            "][360:"return"][360:" "]this.localWrap(thisLocalMethodWrappedList)[360:";"][360:"\n        "]}

        public List<String> getLazyLoadedList() {
            if [361:"("][362:"("]lazyLoadedList == null[361:")"][362:")"] {
                lazyLoadedList = [363:"new ArrayList<>()"];
            }
            return lazyLoadedList;
        }

        public List<String> getDefensiveCopyList() {[364:"\n            "][364:"return"][364:" "]new ArrayList<>(defensiveCopyList)[364:";"][364:"\n        "]}

        public List<String> getOneLineLazyLoadedList() {
            if [365:"("][366:"("]oneLineLazyLoadedList == null[365:")"][366:")"] oneLineLazyLoadedList = [367:"new ArrayList<>()"];
            return oneLineLazyLoadedList;
        }

        private List<String> localWrap(List<String> list) {[368:"\n            "][368:"return"][368:" "]null[368:";"][368:"\n        "]}
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;
        [369:"b"]oolean withoutThis;

        public void setDirty(boolean dirty) {[370:"\n            "]this.dirty2 = dirty[370:";"][370:"\n        "]}

        public void setDirty2(boolean dirty2) {[371:"\n            "]this.dirty = dirty2[371:";"][371:"\n        "]}[369:"\n\n        "][369:"public void setWithoutThis(boolean withoutThiss) {\n            withoutThis = withoutThiss;\n        }"]72:";"][372:"\n        "]}

        [373:"p"]ublic class DirtyData {
            boolean dirty;
            [374:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {[375:"\n                "]this.dirty = !dirty[375:";"][375:"\n            "]}[374:"\n\n            "][374:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][378:"ok"][379:"ok"][380:"ok"][376:";"][376:"\n            "]}[373:"\n\n            "][373:"public boolean isDirty() {\n                return dirty;\n            }"]:" "]dirty[381:";"][381:"\n            "]}[373:"\n\n            "][373:"public boolean isOk() {\n                return ok;\n            }"]382:" "]ok[382:";"][382:"\n            "]}[373:"\n\n            "][383:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"]][394:"("]dirty != dirtyData.dirty[388:")"][394:")"] return false;
                if [389:"("][395:"("]ok != dirtyData.ok[389:")"][395:")"] return false;

                return true;
            }[373:"\n\n            "][396:"@Override"]erride\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]0[398:")"][400:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [401:"b"]oolean ok;[401:"\n\n            "][401:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][404:"ok"][405:"ok"][406:"ok"][402:";"][402:"\n            "]}

            public void setDirty(boolean dirty) {[407:"\n                "]this.ok = dirty[407:";"][407:"\n            "]}
        }
    }

    public class LogAnnotation {
        [408:"p"]ublic class LogJava {[408:"\n            "][408:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [409:"p"]ublic class LogJava2 {[409:"\n            "][409:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [410:"p"]ublic class LogDiffrentFieldName {[410:"\n            "][411:"public static final "]final Logger logger = Logger.getLogger(\"LogAnnotation.class\");"]class");
        }

        [412:"p"]ublic class LogCustomNameDeprecated {[412:"\n            "][412:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]otation.class");
        }

        [414:"p"]ublic class TripleLogJava {[414:"\n            "][414:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][414:"\n            "][415:"public static final "]final Logger log2 = Logger.getLogger(\"LogAnnotation.class\");"]class");[414:"\n            "][414:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
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
        [418:"p"]ublic class NoArgsConstructorCommentBeforeSuper {[418:"\n            "][418:"public NoArgsConstructorCommentBeforeSuper() {\n                // comment\n                super();\n            }"]r()[419:";"][419:"\n            "]}
        }
        [420:"p"]ublic class NoArgsConstructorCommentAfterSuper {[420:"\n            "][420:"public NoArgsConstructorCommentAfterSuper() {\n                super();\n                // comment\n            }"]"]// comment[421:"\n            "]}
        }
        [422:"p"]ublic class ProtectedNoArgsConstructorComment {[422:"\n            "][422:"protected ProtectedNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        // comment hidden as it is common and adds no meaningful value
        [423:"p"]ublic class PrivateNoArgsConstructorComment {[423:"\n            "][423:"private PrivateNoArgsConstructorComment() {\n                // comment\n            }"]
        }
        [424:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[424:"\n            "][424:"public NoArgsConstructorSuper() {\n                super();\n            }"];"][425:"\n            "]}
        }
        public class NoArgsConstructorSuperParent extends Paren[426:"t"] {
            public NoArgsConstructorSuperParent() {[427:"\n                "]super(null)[427:";"][427:"\n            "]}
        }

    }

    public class AllArgsConstructorAnnotation {
        [428:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[428:"\n            "][428:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [431:"field3"][434:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [435:"field1"][437:"field1"];
                this.field2 = field1;
                this.field3 = [436:"field3"][438:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [439:"field1"][441:"field1"];
                this.field1 = field2;
                this.field3 = [440:"field3"][442:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [443:"field1"][446:"field1"];
                this.field2 = [444:"field2"][447:"field2"];
                this.field3 = [445:"field3"][448:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [449:"field1"][452:"field1"];
                this.field2 = [450:"field2"][453:"field2"];
                this.field3 = [451:"field3"][454:"field3"];
                // comment
            }
        }

        [455:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[455:"\n            "][455:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [458:"field3"][461:"field3"];
            }
        }

        [462:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[462:"\n            "][462:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [465:"field3"][468:"field3"];
            }
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[469:"\n                "][469:"return"][469:" "]new StaticNameArgs(field1, field2, field3)[469:";"][469:"\n            "]}
        }

        [470:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[470:"\n            "][470:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [473:"field3"][476:"field3"];
            }
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [477:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[477:"\n\n            "][477:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [480:"field3"][483:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [484:"field1"][487:"field1"];
                this.field2 = [485:"field2"][488:"field2"];
                this.field3 = [486:"field3"][489:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [490:"field1"][493:"field1"];
                this.field2 = [491:"field2"][494:"field2"];
                this.field3 = [492:"field3"][495:"field3"];
                // comment
            }
        }

        [496:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[496:"\n\n            "][496:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [499:"field3"][502:"field3"];
            }
        }

        [503:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[503:"\n\n            "][503:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [506:"field3"][509:"field3"];
            }

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[510:"\n                "][510:"return"][510:" "]new StaticNameArgs(field1, field2, field3)[510:";"][510:"\n            "]}
        }

        [511:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[511:"\n\n            "][511:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [514:"field3"][517:"field3"];
            }
        }
    }

    public class ValueAnnotation {
        [518:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[518:"\n            "][518:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [521:"field3"][524:"field3"];
            }[518:"\n            "][518:"public String getField1() {\n                return field1;\n            }"]" "]field1[525:";"][525:"\n            "]}[518:"\n            "][518:"public int getField2() {\n                return field2;\n            }"]" "]field2[526:";"][526:"\n            "]}[518:"\n            "][518:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[527:";"][527:"\n            "]}[518:"\n            "][528:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                ValueArgs valueArgs = (ValueArgs) o;\n                if (field2 != valueArgs.field2) return false;\n                if (field3 != valueArgs.field3) return false;\n                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;\n            }"]lse;
                if [534:"("][544:"("]field3 != valueArgs.field3[534:")"][544:")"] return false;
                return [535:"field1 != null ? "][545:"field1 != null ? "]field1[535:"."][545:"."]536:".equals("][546:".equals("]valueArgs.field1[535:")"][536:")"][545:")"][546:")"][535:" : "][545:" : "]valueArgs.field1 == null;
            }[518:"\n            "][547:"@Override"]erride\n            public int hashCode() {\n                int result = field1 != null ? field1.hashCode() : 0;\n                result = 31 * result + field2;\n                result = 31 * result + (field3 ? 1 : 0);\n                return result;\n            }"]            result = 31 * result[550:" + ("][553:" + ("]field3 ? 1 : 0[550:")"][553:")"];
                return result;
            }[518:"\n\n            "][554:"@Override"]erride\n            public String toString() {\n                return \"ValueArgs{\" +\n                        \"field1='\" + field1 + '\\'' +\n                        \", field2=\" + field2 +\n                        \", field3=\" + field3 +\n                        '}';\n            }"][556:" +\n                        \""], field2=[555:"\" + "][556:"\" + "]field2[555:" +\n                        \""][556:" +\n                        \""], field3=[555:"\" + "][556:"\" + "]field3[555:" +\n                        "][555:" +\n                        "][556:" +\n                        "][556:" +\n                        "]'}'[555:";"][556:";"]
            }
        }
        [557:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[557:"\n            "][557:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [560:"field3"][563:"field3"];
            }[557:"\n            "][557:"public String getField1() {\n                return field1;\n            }"]" "]field1[564:";"][564:"\n            "]}[557:"\n            "][557:"public int getField2() {\n                return field2;\n            }"]" "]field2[565:";"][565:"\n            "]}[557:"\n            "][557:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[566:";"][566:"\n            "]}[557:"\n            "][567:"@Override"]erride\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof ValueArgsSuper)) return false;\n\n                ValueArgsSuper that = (ValueArgsSuper) o;\n                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);\n            }"]sSuper) "][577:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }[557:"\n\n            "][578:"@Override"]erride\n            public int hashCode() {\n                int result = Objects.hashCode(field1);\n                result = 31 * result + field2;\n                result = 31 * result + Boolean.hashCode(field3);\n                return result;\n            }"]return result;
            }
        }
        [581:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[581:"\n            "][581:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [584:"field3"][587:"field3"];
            }[581:"\n            "][581:"public String getField1() {\n                return field1;\n            }"]" "]field1[588:";"][588:"\n            "]}[581:"\n            "][581:"public int getField2() {\n                return field2;\n            }"]" "]field2[589:";"][589:"\n            "]}[581:"\n            "][581:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[590:";"][590:"\n            "]}
        }
    }
    class SingleField {
        [591:"p"]ublic static class AllArgs {
            private String field1;[591:"\n            "][591:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]"][594:"field1"][595:"field1"][596:"field1"][592:";"][592:"\n            "]}
        }
        [597:"p"]ublic static class ReqArgs {
            private final String field1;[597:"\n            "][597:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]"][600:"field1"][601:"field1"][602:"field1"][598:";"][598:"\n            "]}
        }
        [603:"p"]ublic static class Value {
            private final String field1;[603:"\n            "][603:"public Value(String field1) {\n                this.field1 = field1;\n            }"]"][606:"field1"][607:"field1"][608:"field1"][604:";"][604:"\n            "]}[603:"\n            "][603:"public String getField1() {\n                return field1;\n            }"]" "]field1[609:";"][609:"\n            "]}[603:"\n            "][610:"@Override"]erride\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof Value)) return false;\n\n                Value value = (Value) o;\n                return Objects.equals(field1, value.field1);\n            }"]614:"Value"][619:"Value"] value = [615:"(Value) "][620:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }[603:"\n            "][622:"@Override"]erride\n            public int hashCode() {\n                return Objects.hashCode(field1);\n            }"]ts.hashCode(field1)[621:";"][621:"\n            "]}
        }
        [623:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[623:"\n            "][623:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"]"][626:"field1"][627:"field1"][628:"field1"][624:";"][624:"\n            "]}[623:"\n            "][623:"public String getField1() {\n                return field1;\n            }"]" "]field1[629:";"][629:"\n            "]}
        }
        class Modifers {
            [630:"s"]tatic class AllArgsDefault {
                private String field1;[630:"\n                "][630:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]33:"field1"][634:"field1"][635:"field1"][631:";"][631:"\n                "]}
            }
            [636:"s"]tatic class AllArgsPrivate {
                private String field1;[636:"\n                "][636:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]39:"field1"][640:"field1"][641:"field1"][637:";"][637:"\n                "]}
            }
            [642:"s"]tatic class AllArgsProteced {
                private String field1;[642:"\n                "][642:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]45:"field1"][646:"field1"][647:"field1"][643:";"][643:"\n                "]}
            }
        }
    }

    class FieldLevelData {
        [648:"p"]rivate String name;
        private String ignored;[648:"\n\n        "][648:"public String getName() {\n            return name;\n        }"]][649:" "]name[649:";"][649:"\n        "]}[648:"\n\n        "][648:"public void setName(String name) {\n            this.name = name;\n        }"]name"][652:"name"][653:"name"][654:"name"][650:";"][650:"\n        "]}[648:"\n\n        "][655:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][664:"FieldLevelData"] that = [660:"(FieldLevelData) "][665:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[648:"\n\n        "][667:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[666:";"][666:"\n        "]}
    }

    class FieldLevelValue {
        [668:"p"]rivate final String name = "1";
        private String ignored;[668:"\n\n        "][668:"public String getName() {\n            return name;\n        }"]][669:" "]name[669:";"][669:"\n        "]}[668:"\n\n        "][670:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][679:"FieldLevelData"] that = [675:"(FieldLevelData) "][680:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[668:"\n\n        "][682:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[681:";"][681:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        [683:"p"]rivate String name;
        private String ignored;[683:"\n\n        "][683:"public String getName() {\n            return name;\n        }"]][684:" "]name[684:";"][684:"\n        "]}[683:"\n\n        "][685:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][694:"FieldLevelData"] that = [690:"(FieldLevelData) "][695:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[683:"\n\n        "][697:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[696:";"][696:"\n        "]}
    }

    [698:"c"]lass ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [699:"name"][700:"name"];
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
            private boolean field4;[702:"\n            "][702:"public FiveConstructors() {\n            }"][703:"\n            "][703:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"]"][708:"field1"][709:"field1"][710:"field1"][706:";"][706:"\n            "]}[703:"\n            "][704:"\n            "][703:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][704:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [712:"field2"][714:"field2"];
            }[703:"\n            "][704:"\n            "][705:"\n            "][703:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][704:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][705:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [717:"field3"][720:"field3"];
            }[702:"\n            "][702:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]727:"field3"];
                this.field4 = [724:"field4"][728:"field4"];
            }
        }

        [729:"p"]ublic class FiveConstructorsMod {
            [730:"p"]rivate int field1;
            [731:"p"]rivate String field2;
            [732:"p"]rivate double field3;
            private boolean field4;[729:"\n\n            "][729:"public FiveConstructorsMod() {\n            }"][730:"\n\n            "][730:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"]"][735:"field1"][736:"field1"][737:"field1"][733:";"][733:"\n            "]}[730:"\n\n            "][731:"\n\n            "][730:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][731:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [739:"field2"][741:"field2"];
            }[730:"\n\n            "][731:"\n\n            "][732:"\n\n            "][730:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][731:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][732:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [744:"field3"][747:"field3"];
            }[729:"\n\n            "][729:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]754:"field3"];
                this.field4 = [751:"field4"][755:"field4"];
            }
        }

        class SingleConstructor {
            [756:"p"]rivate final int field1;
            private int field2;[756:"\n            "][756:"SingleConstructor(int field1) {\n                this.field1 = field1;\n            }"]"][759:"field1"][760:"field1"][761:"field1"][757:";"][757:"\n            "]}
        }

        private class SingleDoubleConstructor {
            [762:"p"]rivate final int field1;
            [762:"p"]rivate final int field2;
            private int field3;[762:"\n\n            "][762:"private SingleDoubleConstructor(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [764:"field2"][766:"field2"];
            }
        }

        public class SingleConstructorNoMod {
            [767:"p"]rivate final int field1;
            private int field2;[767:"\n            "][767:"public SingleConstructorNoMod(int field1) {\n                this.field1 = field1;\n            }"]"][770:"field1"][771:"field1"][772:"field1"][768:";"][768:"\n            "]}
        }

        public class SingleDoubleConstructorNoMod {
            [773:"p"]rivate final int field1;
            [773:"p"]rivate final int field2;
            private int field3;[773:"\n\n            "][773:"public SingleDoubleConstructorNoMod(int field1, int field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [775:"field2"][777:"field2"];
            }
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
