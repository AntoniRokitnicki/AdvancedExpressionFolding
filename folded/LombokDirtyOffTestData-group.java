package data;

public class LombokDirtyOffTestData {

    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {
            return dirty2;
        }

        public boolean isDirty2() {
            return dirty;
        }

        [0:"p"]ublic class DirtyData {
            boolean dirty;
            [1:"p"]rivate boolean ok;

            public boolean isDirty() {
                return !dirty;
            }[1:"\n\n            "][1:"public boolean isOk() {\n                return ok;\n            }"][0:"\n\n            "][0:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"][0:"\n\n            "][0:"@Override\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }

        public class DirtySingle {
            boolean dirty;
            [2:"b"]oolean ok;[2:"\n\n            "][2:"public boolean isOk() {\n                return ok;\n            }"]
            public boolean isDirty() {
                return dirty2;
            }
        }
    }

    public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {
            this.dirty2 = dirty;
        }

        public void setDirty2(boolean dirty2) {
            this.dirty = dirty2;
        }

        [3:"p"]ublic class DirtyData {
            boolean dirty;
            [4:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }[4:"\n\n            "][4:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"][3:"\n\n            "][3:"public boolean isDirty() {\n                return dirty;\n            }"][3:"\n\n            "][3:"public boolean isOk() {\n                return ok;\n            }"][3:"\n\n            "][3:"@Override\n            public boolean equals(Object o) {\n                if (this == o) return true;\n                if (!(o instanceof DirtyData)) return false;\n\n                DirtyData dirtyData = (DirtyData) o;\n\n                if (dirty != dirtyData.dirty) return false;\n                if (ok != dirtyData.ok) return false;\n\n                return true;\n            }"][3:"\n\n            "][3:"@Override\n            public int hashCode() {\n                int result = (dirty ? 1 : 0);\n                result = 31 * result + (ok ? 1 : 0);\n                return result;\n            }"]
        }

        public class DirtySingle {
            boolean dirty;
            [5:"b"]oolean ok;[5:"\n\n            "][5:"public void setOk(boolean ok) {\n                this.ok = ok;\n            }"]

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
        }
    }
}
