package data;

public class LombokDirtyOffTestData {

    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {[0:"\n            "][0:"return"][0:" "]dirty2[0:";"][0:"\n        "]}

        public boolean isDirty2() {[1:"\n            "][1:"return"][1:" "]dirty[1:";"][1:"\n        "]}

        [2:"p"]ublic class DirtyData {
            boolean dirty;
            [3:"p"]rivate boolean ok;

            public boolean isDirty() {[4:"\n                "][4:"return"][4:" "]!dirty[4:";"][4:"\n            "]}[3:"\n\n            "][3:"public boolean isOk() {\n                return ok;\n            }"][2:"\n\n            "][6:"@Override"][6:"\n            "]public boolean equals(Object o) {
                if [7:"("]this == o[7:")"] return true;
                if [8:"("]!(o instanceof DirtyData)[8:")"] return false;

                [9:"DirtyData"] dirtyData = [10:"(DirtyData) "]o;

                if [11:"("]dirty != dirtyData.dirty[11:")"] return false;
                if [12:"("]ok != dirtyData.ok[12:")"] return false;

                return true;
            }[2:"\n\n            "][19:"@Override"][19:"\n            "]public int hashCode() {
                [20:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[21:" + ("]ok ? 1 : 0[21:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [24:"b"]oolean ok;[24:"\n\n            "][24:"public boolean isOk() {\n                return ok;\n            }"]
            public boolean isDirty() {[26:"\n                "][26:"return"][26:" "]dirty2[26:";"][26:"\n            "]}
        }
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {[27:"\n            "]this.dirty2 = dirty[27:";"][27:"\n        "]}

        public void setDirty2(boolean dirty2) {[28:"\n            "]this.dirty = dirty2[28:";"][28:"\n        "]}

        [29:"p"]ublic class DirtyData {
            boolean dirty;
            [30:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {[31:"\n                "]this.dirty = !dirty[31:";"][31:"\n            "]}[30:"\n\n            "][30:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][29:"\n\n            "][29:"public boolean isDirty() {\n                return dirty;\n            }"][29:"\n\n            "][29:"public boolean isOk() {\n                return ok;\n            }"][29:"\n\n            "][39:"@Override"][39:"\n            "]public boolean equals(Object o) {
                if [40:"("]this == o[40:")"] return true;
                if [41:"("]!(o instanceof DirtyData)[41:")"] return false;

                [42:"DirtyData"] dirtyData = [43:"(DirtyData) "]o;

                if [44:"("]dirty != dirtyData.dirty[44:")"] return false;
                if [45:"("]ok != dirtyData.ok[45:")"] return false;

                return true;
            }[29:"\n\n            "][52:"@Override"][52:"\n            "]public int hashCode() {
                [53:"int"] result = (dirty ? 1 : 0);
                result = 31 * result[54:" + ("]ok ? 1 : 0[54:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [57:"b"]oolean ok;[57:"\n\n            "][57:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]

            public void setDirty(boolean dirty) {[63:"\n                "]this.ok = dirty[63:";"][63:"\n            "]}
        }
    }
}
