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
[0:"p"]ublic class LombokPatternOffNegativeTestData {[0:"\n\n    "][0:"private static final long serialVersionUID = 1234567L;"] = 1234567L;

    LombokPatternOffNegativeTestData data;
    boolean ok;
    String string;[0:"\n\n    "][0:"public LombokPatternOffNegativeTestData getData() {\n        return data;\n    }"]][2:" "]data[2:";"][2:"\n    "]}[0:"\n\n    "][0:"public void setData(LombokPatternOffNegativeTestData data) {\n        this.data = data;\n    }"]data"][5:"data"][6:"data"][7:"data"][3:";"][3:"\n    "]}[0:"\n\n    "][0:"public boolean isOk() {\n        return ok;\n    }"]n"][8:" "]ok[8:";"][8:"\n    "]}[0:"\n\n    "][0:"public void setOk(boolean ok) {\n        this.ok = ok;\n    }"]0:"ok"][11:"ok"][12:"ok"][13:"ok"][9:";"][9:"\n    "]}[0:"\n\n    "][0:"public String getString() {\n        return string;\n    }"]][14:" "]string[14:";"][14:"\n    "]}[0:"\n\n    "][0:"public void setString(String string) {\n        this.string = string;\n    }"]string"][17:"string"][18:"string"][19:"string"][15:";"][15:"\n    "]}

    public Optional<LombokPatternOffNegativeTestData> asOptional() {[20:"\n        "][20:"return"][20:" "][21:"Optional.ofNullable("][22:"Optional.ofNullable("]data[21:")"][22:")"][20:";"][20:"\n    "]}

    [23:"p"]ublic class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[23:"\n\n        "][23:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"]24:" "]data[24:";"][24:"\n        "]}[23:"\n\n        "][23:"public boolean isOk() {\n            return ok;\n        }"]][25:" "]ok[25:";"][25:"\n        "]}

        public class LombokGettersPartial {
            LombokPatternOffNegativeTestData data;
            [26:"b"]oolean ok;[26:"\n\n            "][26:"public boolean isOk() {\n                return ok;\n            }"]:" "]ok[27:";"][27:"\n            "]}
        }
    }

    [28:"p"]ublic class LombokSetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;[28:"\n\n        "][28:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"]29:" "]data[29:";"][29:"\n        "]}[28:"\n\n        "][28:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"]ta"][32:"data"][33:"data"][34:"data"][30:";"][30:"\n        "]}[28:"\n\n        "][28:"public boolean isOk() {\n            return ok;\n        }"]][35:" "]ok[35:";"][35:"\n        "]}[28:"\n\n        "][28:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]ok"][38:"ok"][39:"ok"][40:"ok"][36:";"][36:"\n        "]}

        public class LombokSettersPartial {
            [41:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[41:"\n\n            "][41:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][44:"data"][45:"data"][46:"data"][42:";"][42:"\n            "]}
        }

        public class LombokSettersFinalField {
            [47:"L"]ombokPatternOffNegativeTestData data;
            final boolean ok = true;[47:"\n\n            "][47:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"][50:"data"][51:"data"][52:"data"][48:";"][48:"\n            "]}
        }
    }

    [53:"p"]ublic class ToStringFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[53:"\n\n        "][54:"@Override"]rride\n        public String toString() {\n            return \"ToStringFull{\" +\n                    \"data=\" + data +\n                    \", ok=\" + ok +\n                    '}';\n        }"]a[55:" +\n                    \""][56:" +\n                    \""], ok=[55:"\" + "][56:"\" + "]ok[55:" +\n                    "][55:" +\n                    "][56:" +\n                    "][56:" +\n                    "]'}'[55:";"][56:";"]
        }

        public class ToStringPartial {
            [57:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[57:"\n\n            "][59:"@Override"]rride\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]                 \""]data=[60:"\" + "][61:"\" + "]data[60:" +\n                        "][60:" +\n                        "][61:" +\n                        "][61:" +\n                        "]'}'[58:";"][60:";"][61:";"][58:"\n            "]}
        }

        public class ToStringPartial2 {
            [62:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;
            String string;[62:"\n\n            "][64:"@Override"]rride\n            public String toString() {\n                return \"ToStringPartial{\" +\n                        \"data=\" + data +\n                        '}';\n            }"]                 \""]data=[65:"\" + "][66:"\" + "]data[65:" +\n                        "][65:" +\n                        "][66:" +\n                        "][66:" +\n                        "]'}'[63:";"][65:";"][66:";"][63:"\n            "]}
        }
    }

    [67:"p"]ublic class EqualsAndHashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[67:"\n\n        "][68:"@Override"]rride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]ashCodeFull) "][82:"(EqualsAndHashCodeFull) "]o;
            return ok == that.ok && ([73:"data != null ? "][75:"data != null ? "][83:"data != null ? "]data[73:"."][75:"."][83:"."]74:".equals("][76:".equals("][84:".equals("]that.data[73:")"][74:")"][75:")"][76:")"][83:")"][84:")"][73:" : "][75:" : "][83:" : "]that.data == null);
        }[67:"\n\n        "][85:"@Override"]rride\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]hCode()[87:" : "][88:" : "][91:" : "]0);
            result = 31 * result[89:" + ("][92:" + ("]ok ? 1 : 0[89:")"][92:")"];
            return result;
        }

        public class EqualsAndHashCodePartial {
            [93:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[93:"\n\n            "][94:"@Override"]rride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"]= [98:"(EqualsAndHashCodePartial) "][106:"(EqualsAndHashCodePartial) "]o;
                return [99:"data != null ? "][107:"data != null ? "]data[99:"."][107:"."]100:".equals("][108:".equals("]that.data[99:")"][100:")"][107:")"][108:")"][99:" : "][107:" : "]that.data == null;
            }[93:"\n\n            "][110:"@Override"]rride\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]ull ? "][112:"data != null ? "]data[111:"."][112:"."]hashCode()[111:" : "][112:" : "]0[109:";"][109:"\n            "]}
        }

        public class EqualsAndHashCodePartialTwo {
            [113:"L"]ombokPatternOffNegativeTestData data;
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
        LombokPatternOffNegativeTestData data;
        boolean ok;[140:"\n\n        "][141:"@Override"]erride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (o == null || getClass() != o.getClass()) return false;\n            EqualsFull that = (EqualsFull) o;\n            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n        }"]5:"(EqualsFull) "][155:"(EqualsFull) "]o;
            return ok == that.ok && ([146:"data != null ? "][148:"data != null ? "][156:"data != null ? "]data[146:"."][148:"."][156:"."]147:".equals("][149:".equals("][157:".equals("]that.data[146:")"][147:")"][148:")"][149:")"][156:")"][157:")"][146:" : "][148:" : "][156:" : "]that.data == null);
        }

        public class EqualsPartial {
            [158:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[158:"\n\n            "][159:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartial that = (EqualsPartial) o;\n                return data != null ? data.equals(that.data) : that.data == null;\n            }"] that = [163:"(EqualsPartial) "][171:"(EqualsPartial) "]o;
                return [164:"data != null ? "][172:"data != null ? "]data[164:"."][172:"."]165:".equals("][173:".equals("]that.data[164:")"][165:")"][172:")"][173:")"][164:" : "][172:" : "]that.data == null;
            }
        }

        public class EqualsPartialTwo {
            [174:"L"]ombokPatternOffNegativeTestData data;
            [175:"b"]oolean ok;
            String string;[174:"\n\n            "][175:"\n\n            "][176:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"][175:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                EqualsPartialTwo that = (EqualsPartialTwo) o;\n                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);\n            }"]lsPartialTwo) "][190:"(EqualsPartialTwo) "]o;
                return ok == that.ok && ([181:"data != null ? "][183:"data != null ? "][191:"data != null ? "]data[181:"."][183:"."][191:"."]182:".equals("][184:".equals("][192:".equals("]that.data[181:")"][182:")"][183:")"][184:")"][191:")"][192:")"][181:" : "][183:" : "][191:" : "]that.data == null);
            }
        }
    }

    [193:"p"]ublic class HashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[193:"\n\n        "][194:"@Override"]erride\n        public int hashCode() {\n            int result = (data != null ? data.hashCode() : 0);\n            result = 31 * result + (ok ? 1 : 0);\n            return result;\n        }"]0:"."]hashCode()[196:" : "][197:" : "][200:" : "]0);
            result = 31 * result[198:" + ("][201:" + ("]ok ? 1 : 0[198:")"][201:")"];
            return result;
        }

        public class HashCodePartial {
            [202:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[202:"\n\n            "][204:"@Override"]erride\n            public int hashCode() {\n                return data != null ? data.hashCode() : 0;\n            }"]ull ? "][206:"data != null ? "]data[205:"."][206:"."]hashCode()[205:" : "][206:" : "]0[203:";"][203:"\n            "]}
        }

        public class HashCodePartialTwo {
            [207:"L"]ombokPatternOffNegativeTestData data;
            [208:"b"]oolean ok;
            String string;[207:"\n\n            "][208:"\n\n            "][209:"@Override"]erride\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"][208:"@Override\n            public int hashCode() {\n                int result = (data != null ? data.hashCode() : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]de()[211:" : "][212:" : "][215:" : "]0);
                result = 31 * result[213:" + ("][216:" + ("]ok ? 1 : 0[213:")"][216:")"];
                return result;
            }
        }
    }

    [217:"p"]ublic class DataFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;[217:"\n\n        "][217:"public LombokPatternOffNegativeTestData getData() {\n            return data;\n        }"]][218:" "]data[218:";"][218:"\n        "]}[217:"\n\n        "][217:"public void setData(LombokPatternOffNegativeTestData data) {\n            this.data = data;\n        }"]data"][221:"data"][222:"data"][223:"data"][219:";"][219:"\n        "]}[217:"\n\n        "][217:"public boolean isOk() {\n            return ok;\n        }"]n"][224:" "]ok[224:";"][224:"\n        "]}[217:"\n\n        "][217:"public void setOk(boolean ok) {\n            this.ok = ok;\n        }"]:"ok"][227:"ok"][228:"ok"][229:"ok"][225:";"][225:"\n        "]}[217:"\n\n        "][230:"@Override"]erride\n        public boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof DataFull)) return false;\n            DataFull dataFull = (DataFull) o;\n            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n        }"](DataFull) "]o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[235:"isEquals()"][240:"isEquals()"];
        }[217:"\n\n        "][242:"@Override"]erride\n        public int hashCode() {\n            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n        }"]append(ok).toHashCode()[241:";"][241:"\n        "]}[217:"\n\n        "][243:"@Override"]erride\n        public String toString() {\n            return new ToStringBuilder(this)\n                    .append(\"data\", data)\n                    .append(\"ok\", ok)\n                    .toString();\n        }"]        }

        [244:"p"]ublic class DataWithoutToString {
            LombokPatternOffNegativeTestData data;
            boolean ok;[244:"\n\n            "][244:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"]5:" "]data[245:";"][245:"\n            "]}[244:"\n\n            "][244:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]"][248:"data"][249:"data"][250:"data"][246:";"][246:"\n            "]}[244:"\n\n            "][244:"public boolean isOk() {\n                return ok;\n            }"]251:" "]ok[251:";"][251:"\n            "]}[244:"\n\n            "][244:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][254:"ok"][255:"ok"][256:"ok"][252:";"][252:"\n            "]}[244:"\n\n            "][257:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]stData.DataFull) "][266:"(LombokPatternOffNegativeTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[262:"isEquals()"][267:"isEquals()"];
            }[244:"\n\n            "][269:"@Override"]erride\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]nd(ok).toHashCode()[268:";"][268:"\n            "]}

        }

        [270:"p"]ublic class DataWithPartialGetters {
            [271:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[271:"\n\n            "][271:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"]2:" "]data[272:";"][272:"\n            "]}[270:"\n\n            "][270:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]"][275:"data"][276:"data"][277:"data"][273:";"][273:"\n            "]}[270:"\n\n            "][270:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][280:"ok"][281:"ok"][282:"ok"][278:";"][278:"\n            "]}[270:"\n\n            "][283:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]stData.DataFull) "][292:"(LombokPatternOffNegativeTestData.DataFull) "]o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).[288:"isEquals()"][293:"isEquals()"];
            }[270:"\n\n            "][295:"@Override"]erride\n            public int hashCode() {\n                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();\n            }"]nd(ok).toHashCode()[294:";"][294:"\n            "]}[270:"\n\n            "][296:"@Override"]erride\n            public String toString() {\n                return new ToStringBuilder(this)\n                        .append(\"data\", data)\n                        .append(\"ok\", ok)\n                        .toString();\n            }"]        }
        }

        [297:"p"]ublic class DataWithPartialSetters {
            [298:"L"]ombokPatternOffNegativeTestData data;
            boolean ok;[297:"\n\n            "][297:"public LombokPatternOffNegativeTestData getData() {\n                return data;\n            }"]9:" "]data[299:";"][299:"\n            "]}[298:"\n\n            "][298:"public void setData(LombokPatternOffNegativeTestData data) {\n                this.data = data;\n            }"]"][302:"data"][303:"data"][304:"data"][300:";"][300:"\n            "]}[297:"\n\n            "][297:"public boolean isOk() {\n                return ok;\n            }"]305:" "]ok[305:";"][305:"\n            "]}[297:"\n\n            "][306:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;\n                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;\n                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();\n            }"]stData.DataFull) "][315:"(LombokPatternOffNegativeTestData.DataFull) "]o;
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

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {[353:"\n            "]this.dirty2 = dirty[353:";"][353:"\n        "]}

        public void setDirty2(boolean dirty2) {[354:"\n            "]this.dirty = dirty2[354:";"][354:"\n        "]}

        [355:"p"]ublic class DirtyData {
            boolean dirty;
            [356:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {[357:"\n                "]this.dirty = !dirty[357:";"][357:"\n            "]}[356:"\n\n            "][356:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][360:"ok"][361:"ok"][362:"ok"][358:";"][358:"\n            "]}[355:"\n\n            "][355:"public boolean isDirty() {\n                return dirty;\n            }"]:" "]dirty[363:";"][363:"\n            "]}[355:"\n\n            "][355:"public boolean isOk() {\n                return ok;\n            }"]364:" "]ok[364:";"][364:"\n            "]}[355:"\n\n            "][365:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"]][376:"("]dirty != dirtyData.dirty[370:")"][376:")"] return false;
                if [371:"("][377:"("]ok != dirtyData.ok[371:")"][377:")"] return false;

                return true;
            }[355:"\n\n            "][378:"@Override"]erride\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]0[380:")"][382:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [383:"b"]oolean ok;[383:"\n\n            "][383:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]"][386:"ok"][387:"ok"][388:"ok"][384:";"][384:"\n            "]}

            public void setDirty(boolean dirty) {[389:"\n                "]this.ok = dirty[389:";"][389:"\n            "]}
        }
    }

    public class LogAnnotation {
        [390:"p"]ublic class LogJava {[390:"\n            "][390:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [391:"p"]ublic class LogJava2 {[391:"\n            "][391:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"]
        }

        [392:"p"]ublic class LogDiffrentFieldName {[392:"\n            "][393:"public static final "]final Logger logger = Logger.getLogger(\"LogAnnotation.class\");"]class");
        }

        [394:"p"]ublic class LogCustomNameDeprecated {[394:"\n            "][394:"@Deprecated\n            static final Logger xlogger = Logger.getLogger(\"LogAnnotation.class\");"]otation.class");
        }

        [396:"p"]ublic class TripleLogJava {[396:"\n            "][396:"Logger log = Logger.getLogger(\"LogAnnotation.class\");"][396:"\n            "][397:"public static final "]final Logger log2 = Logger.getLogger(\"LogAnnotation.class\");"]class");[396:"\n            "][396:"static Logger log3 = Logger.getLogger(\"LogAnnotation.class\");"]
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
        [400:"p"]ublic class NoArgsConstructorSuperBefore {[400:"\n            "][400:"public NoArgsConstructorSuperBefore() {\n                // comment\n                super();\n            }"]r()[401:";"][401:"\n            "]}
        }
        [402:"p"]ublic class NoArgsConstructorSuperAfter {[402:"\n            "][402:"public NoArgsConstructorSuperAfter() {\n                super();\n                // comment\n            }"]"]// comment[403:"\n            "]}
        }

        [404:"p"]ublic class ProtectedNoArgsConstructorSuperAfter {[404:"\n            "][404:"protected ProtectedNoArgsConstructorSuperAfter() {\n                // comment\n            }"]
        }
        [405:"p"]ublic class NoArgsConstructorSuper {
            private String field1;[405:"\n            "][405:"public NoArgsConstructorSuper() {\n                super();\n            }"];"][406:"\n            "]}
        }
        public class NoArgsConstructorSuperParent extends Paren[407:"t"] {
            public NoArgsConstructorSuperParent() {[408:"\n                "]super(null)[408:";"][408:"\n            "]}
        }

    }

    public class AllArgsConstructorAnnotation {
        [409:"p"]ublic static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;[409:"\n            "][409:"public AllArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [412:"field3"][415:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentLeft {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) {
                this.field1 = [416:"field1"][418:"field1"];
                this.field2 = field1;
                this.field3 = [417:"field3"][419:"field3"];
            }
        }
        public static class AllArgsBrokenFieldAssigmentRight {
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) {
                this.field1 = [420:"field1"][422:"field1"];
                this.field1 = field2;
                this.field3 = [421:"field3"][423:"field3"];
            }
        }

        public static class AllArgsNoArgsConstructorSuperBefore {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [424:"field1"][427:"field1"];
                this.field2 = [425:"field2"][428:"field2"];
                this.field3 = [426:"field3"][429:"field3"];
            }
        }
        public static class AllArgsNoArgsConstructorSuperAfter {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [430:"field1"][433:"field1"];
                this.field2 = [431:"field2"][434:"field2"];
                this.field3 = [432:"field3"][435:"field3"];
                // comment
            }
        }

        [436:"p"]ublic static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;[436:"\n            "][436:"public AllArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [439:"field3"][442:"field3"];
            }
        }

        [443:"p"]ublic static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;[443:"\n            "][443:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [446:"field3"][449:"field3"];
            }
            public static StaticNameArgs of(String field1, int field2, boolean field3) {[450:"\n                "][450:"return"][450:" "]new StaticNameArgs(field1, field2, field3)[450:";"][450:"\n            "]}
        }

        [451:"p"]ublic static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;[451:"\n            "][451:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [454:"field3"][457:"field3"];
            }
        }
    }


    public class RequiredArgsConstructorAnnotation {
        [458:"p"]ublic static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[458:"\n\n            "][458:"public RequiredArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [461:"field3"][464:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperBefore {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) {
                // comment
                super();
                this.field1 = [465:"field1"][468:"field1"];
                this.field2 = [466:"field2"][469:"field2"];
                this.field3 = [467:"field3"][470:"field3"];
            }
        }

        public static class RequiredArgsNoArgsConstructorSuperAfter {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) {
                super();
                this.field1 = [471:"field1"][474:"field1"];
                this.field2 = [472:"field2"][475:"field2"];
                this.field3 = [473:"field3"][476:"field3"];
                // comment
            }
        }

        [477:"p"]ublic static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[477:"\n\n            "][477:"public RequiredArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [480:"field3"][483:"field3"];
            }
        }

        [484:"p"]ublic static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[484:"\n\n            "][484:"private StaticNameArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [487:"field3"][490:"field3"];
            }

            public static StaticNameArgs of(String field1, int field2, boolean field3) {[491:"\n                "][491:"return"][491:" "]new StaticNameArgs(field1, field2, field3)[491:";"][491:"\n            "]}
        }

        [492:"p"]ublic static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[492:"\n\n            "][492:"protected ProtectedArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [495:"field3"][498:"field3"];
            }
        }
    }

    public class ValueAnnotation {
        [499:"p"]ublic static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;[499:"\n            "][499:"public ValueArgs(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [502:"field3"][505:"field3"];
            }[499:"\n            "][499:"public String getField1() {\n                return field1;\n            }"]" "]field1[506:";"][506:"\n            "]}[499:"\n            "][499:"public int getField2() {\n                return field2;\n            }"]" "]field2[507:";"][507:"\n            "]}[499:"\n            "][499:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[508:";"][508:"\n            "]}[499:"\n            "][509:"@Override"]erride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (o == null || getClass() != o.getClass()) return false;\n                ValueArgs valueArgs = (ValueArgs) o;\n                if (field2 != valueArgs.field2) return false;\n                if (field3 != valueArgs.field3) return false;\n                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;\n            }"]lse;
                if [515:"("][525:"("]field3 != valueArgs.field3[515:")"][525:")"] return false;
                return [516:"field1 != null ? "][526:"field1 != null ? "]field1[516:"."][526:"."]517:".equals("][527:".equals("]valueArgs.field1[516:")"][517:")"][526:")"][527:")"][516:" : "][526:" : "]valueArgs.field1 == null;
            }[499:"\n            "][528:"@Override"]erride\n            public int hashCode() {\n                int result = field1 != null ? field1.hashCode() : 0;\n                result = 31 * result + field2;\n                result = 31 * result + (field3 ? 1 : 0);\n                return result;\n            }"]            result = 31 * result[531:" + ("][534:" + ("]field3 ? 1 : 0[531:")"][534:")"];
                return result;
            }[499:"\n\n            "][535:"@Override"]erride\n            public String toString() {\n                return \"ValueArgs{\" +\n                        \"field1='\" + field1 + '\\'' +\n                        \", field2=\" + field2 +\n                        \", field3=\" + field3 +\n                        '}';\n            }"][537:" +\n                        \""], field2=[536:"\" + "][537:"\" + "]field2[536:" +\n                        \""][537:" +\n                        \""], field3=[536:"\" + "][537:"\" + "]field3[536:" +\n                        "][536:" +\n                        "][537:" +\n                        "][537:" +\n                        "]'}'[536:";"][537:";"]
            }
        }
        [538:"p"]ublic static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;[538:"\n            "][538:"public ValueArgsSuper(String field1, int field2, boolean field3) {\n                super();\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [541:"field3"][544:"field3"];
            }[538:"\n            "][538:"public String getField1() {\n                return field1;\n            }"]" "]field1[545:";"][545:"\n            "]}[538:"\n            "][538:"public int getField2() {\n                return field2;\n            }"]" "]field2[546:";"][546:"\n            "]}[538:"\n            "][538:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[547:";"][547:"\n            "]}[538:"\n            "][548:"@Override"]erride\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof ValueArgsSuper)) return false;\n\n                ValueArgsSuper that = (ValueArgsSuper) o;\n                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);\n            }"]sSuper) "][558:"(ValueArgsSuper) "]o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }[538:"\n\n            "][559:"@Override"]erride\n            public int hashCode() {\n                int result = Objects.hashCode(field1);\n                result = 31 * result + field2;\n                result = 31 * result + Boolean.hashCode(field3);\n                return result;\n            }"]return result;
            }
        }
        [562:"p"]ublic static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;[562:"\n            "][562:"public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [565:"field3"][568:"field3"];
            }[562:"\n            "][562:"public String getField1() {\n                return field1;\n            }"]" "]field1[569:";"][569:"\n            "]}[562:"\n            "][562:"public int getField2() {\n                return field2;\n            }"]" "]field2[570:";"][570:"\n            "]}[562:"\n            "][562:"public boolean isField3() {\n                return field3;\n            }"]" "]field3[571:";"][571:"\n            "]}
        }
    }
    class SingleField {
        [572:"p"]ublic static class AllArgs {
            private String field1;[572:"\n            "][572:"public AllArgs(String field1) {\n                this.field1 = field1;\n            }"]"][575:"field1"][576:"field1"][577:"field1"][573:";"][573:"\n            "]}
        }
        [578:"p"]ublic static class ReqArgs {
            private final String field1;[578:"\n            "][578:"public ReqArgs(String field1) {\n                this.field1 = field1;\n            }"]"][581:"field1"][582:"field1"][583:"field1"][579:";"][579:"\n            "]}
        }
        [584:"p"]ublic static class Value {
            private final String field1;[584:"\n            "][584:"public Value(String field1) {\n                this.field1 = field1;\n            }"]"][587:"field1"][588:"field1"][589:"field1"][585:";"][585:"\n            "]}[584:"\n            "][584:"public String getField1() {\n                return field1;\n            }"]" "]field1[590:";"][590:"\n            "]}[584:"\n            "][591:"@Override"]erride\n            public final boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof Value)) return false;\n\n                Value value = (Value) o;\n                return Objects.equals(field1, value.field1);\n            }"]595:"Value"][600:"Value"] value = [596:"(Value) "][601:"(Value) "]o;
                return Objects.equals(field1, value.field1);
            }[584:"\n            "][603:"@Override"]erride\n            public int hashCode() {\n                return Objects.hashCode(field1);\n            }"]ts.hashCode(field1)[602:";"][602:"\n            "]}
        }
        [604:"p"]ublic static class ValueWithoutEqualsAndHashCode {
            private final String field1;[604:"\n            "][604:"public ValueWithoutEqualsAndHashCode(String field1) {\n                this.field1 = field1;\n            }"]"][607:"field1"][608:"field1"][609:"field1"][605:";"][605:"\n            "]}[604:"\n            "][604:"public String getField1() {\n                return field1;\n            }"]" "]field1[610:";"][610:"\n            "]}
        }
        class Modifers {
            [611:"s"]tatic class AllArgsDefault {
                private String field1;[611:"\n                "][611:"AllArgsDefault(String field1) {\n                    this.field1 = field1;\n                }"]14:"field1"][615:"field1"][616:"field1"][612:";"][612:"\n                "]}
            }
            [617:"s"]tatic class AllArgsPrivate {
                private String field1;[617:"\n                "][617:"private AllArgsPrivate(String field1) {\n                    this.field1 = field1;\n                }"]20:"field1"][621:"field1"][622:"field1"][618:";"][618:"\n                "]}
            }
            [623:"s"]tatic class AllArgsProteced {
                private String field1;[623:"\n                "][623:"protected AllArgsProteced(String field1) {\n                    this.field1 = field1;\n                }"]26:"field1"][627:"field1"][628:"field1"][624:";"][624:"\n                "]}
            }
        }
    }

    class FieldLevelData {
        [629:"p"]rivate String name;
        private String ignored;[629:"\n\n        "][629:"public String getName() {\n            return name;\n        }"]][630:" "]name[630:";"][630:"\n        "]}[629:"\n\n        "][629:"public void setName(String name) {\n            this.name = name;\n        }"]name"][633:"name"][634:"name"][635:"name"][631:";"][631:"\n        "]}[629:"\n\n        "][636:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][645:"FieldLevelData"] that = [641:"(FieldLevelData) "][646:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[629:"\n\n        "][648:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[647:";"][647:"\n        "]}
    }

    class FieldLevelValue {
        [649:"p"]rivate final String name = "1";
        private String ignored;[649:"\n\n        "][649:"public String getName() {\n            return name;\n        }"]][650:" "]name[650:";"][650:"\n        "]}[649:"\n\n        "][651:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][660:"FieldLevelData"] that = [656:"(FieldLevelData) "][661:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[649:"\n\n        "][663:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[662:";"][662:"\n        "]}
    }
    class FieldLevelNotFinalNotValue {
        [664:"p"]rivate String name;
        private String ignored;[664:"\n\n        "][664:"public String getName() {\n            return name;\n        }"]][665:" "]name[665:";"][665:"\n        "]}[664:"\n\n        "][666:"@Override"]erride\n        public final boolean equals(Object o) {\n            if (this == o) return true;\n            if (!(o instanceof FieldLevelData)) return false;\n            FieldLevelData that = (FieldLevelData) o;\n            return Objects.equals(name, that.name);\n        }"]"FieldLevelData"][675:"FieldLevelData"] that = [671:"(FieldLevelData) "][676:"(FieldLevelData) "]o;
            return Objects.equals(name, that.name);
        }[664:"\n\n        "][678:"@Override"]erride\n        public int hashCode() {\n            return Objects.hashCode(name);\n        }"]]Objects.hashCode(name)[677:";"][677:"\n        "]}
    }

    [679:"c"]lass ClassWithBuilder {
        private String name;
        class ClassWithBuilderBuilder {
            private String name;
            public ClassWithBuilderBuilder name(String name) {
                this.name = [680:"name"][681:"name"];
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
            private boolean field4;[683:"\n            "][683:"public FiveConstructors() {\n            }"][684:"\n            "][684:"public FiveConstructors(int field1) {\n                this.field1 = field1;\n            }"]"][689:"field1"][690:"field1"][691:"field1"][687:";"][687:"\n            "]}[684:"\n            "][685:"\n            "][684:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][685:"public FiveConstructors(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [693:"field2"][695:"field2"];
            }[684:"\n            "][685:"\n            "][686:"\n            "][684:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][685:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][686:"public FiveConstructors(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [698:"field3"][701:"field3"];
            }[683:"\n            "][683:"public FiveConstructors(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]708:"field3"];
                this.field4 = [705:"field4"][709:"field4"];
            }
        }

        [710:"p"]ublic class FiveConstructorsMod {
            [711:"p"]rivate int field1;
            [712:"p"]rivate String field2;
            [713:"p"]rivate double field3;
            private boolean field4;[710:"\n            "][710:"public FiveConstructorsMod() {\n            }"][711:"\n            "][711:"private FiveConstructorsMod(int field1) {\n                this.field1 = field1;\n            }"]"][716:"field1"][717:"field1"][718:"field1"][714:";"][714:"\n            "]}[711:"\n            "][712:"\n            "][711:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"][712:"FiveConstructorsMod(int field1, String field2) {\n                this.field1 = field1;\n                this.field2 = field2;\n            }"] [720:"field2"][722:"field2"];
            }[711:"\n            "][712:"\n            "][713:"\n            "][711:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][712:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"][713:"protected FiveConstructorsMod(int field1, String field2, double field3) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n            }"]         this.field3 = [725:"field3"][728:"field3"];
            }[710:"\n            "][710:"public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {\n                this.field1 = field1;\n                this.field2 = field2;\n                this.field3 = field3;\n                this.field4 = field4;\n            }"]735:"field3"];
                this.field4 = [732:"field4"][736:"field4"];
            }
        }
    }

}
