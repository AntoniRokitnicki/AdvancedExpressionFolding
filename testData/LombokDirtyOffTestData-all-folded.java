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

        @EqualsAndHashCode public class DirtyData {
            boolean dirty;
            @Getter private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }
        }

        public class DirtySingle {
            boolean dirty;
            @Getter boolean ok;
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

        @Getter @EqualsAndHashCode public class DirtyData {
            boolean dirty;
            @Setter private boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }
        }

        public class DirtySingle {
            boolean dirty;
            @Setter boolean ok;

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
        }
    }
}
