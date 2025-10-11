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
            [0:"p"]rivate boolean ok;

            public boolean isDirty() {
                return !dirty;
            }[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]
        }

        public class DirtySingle {
            boolean dirty;
            [0:"b"]oolean ok;[0:"

            "][0:"public boolean isOk() {
                return ok;
            }"]
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

        [0:"p"]ublic class DirtyData {
            boolean dirty;
            [0:"p"]rivate boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }[0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"][0:"

            "][0:"public boolean isDirty() {
                return dirty;
            }"][0:"

            "][0:"public boolean isOk() {
                return ok;
            }"][0:"

            "][0:"@Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }"][0:"

            "][0:"@Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }"]
        }

        public class DirtySingle {
            boolean dirty;
            [0:"b"]oolean ok;[0:"

            "][0:"public void setOk(boolean ok) {
                this.ok = ok;
            }"]

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
        }
    }
}
