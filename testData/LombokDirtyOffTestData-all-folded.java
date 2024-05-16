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

    @Setter public class DirtyLombokSetters {
        boolean dirty;
        private boolean dirty2;

        @Data public class DirtyData {
            boolean dirty;
            private boolean ok;
        }

        @Setter public class DirtySingle {
            boolean dirty;
            boolean ok;
        }
    }
}
