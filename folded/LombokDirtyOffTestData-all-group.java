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

            public boolean isDirty() {[4:"\n                "][4:"return"][4:" "]!dirty[4:";"][4:"\n            "]}[3:"\n\n            "][3:"public boolean isOk() {\n                return ok;\n            }"]"]ok[5:";"][5:"\n            "]}[2:"\n\n            "][6:"@Override"]ride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"]rtyData.dirty[11:")"][17:")"] return false;
                if [12:"("][18:"("]ok != dirtyData.ok[12:")"][18:")"] return false;

                return true;
            }[2:"\n\n            "][19:"@Override"]ride\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]")"][23:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [24:"b"]oolean ok;[24:"\n\n            "][24:"public boolean isOk() {\n                return ok;\n            }"]:" "]ok[25:";"][25:"\n            "]}
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

            public void setDirty(boolean dirty) {[31:"\n                "]this.dirty = !dirty[31:";"][31:"\n            "]}[30:"\n\n            "][30:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][34:"ok"][35:"ok"][36:"ok"][32:";"][32:"\n            "]}[29:"\n\n            "][29:"public boolean isDirty() {\n                return dirty;\n            }"]"]dirty[37:";"][37:"\n            "]}[29:"\n\n            "][29:"public boolean isOk() {\n                return ok;\n            }"]:" "]ok[38:";"][38:"\n            "]}[29:"\n\n            "][39:"@Override"]rride\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"] != dirtyData.dirty[44:")"][50:")"] return false;
                if [45:"("][51:"("]ok != dirtyData.ok[45:")"][51:")"] return false;

                return true;
            }[29:"\n\n            "][52:"@Override"]rride\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]")"][56:")"];
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            [57:"b"]oolean ok;[57:"\n\n            "][57:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][60:"ok"][61:"ok"][62:"ok"][58:";"][58:"\n            "]}

            public void setDirty(boolean dirty) {[63:"\n                "]this.ok = dirty[63:";"][63:"\n            "]}
        }
    }
}
