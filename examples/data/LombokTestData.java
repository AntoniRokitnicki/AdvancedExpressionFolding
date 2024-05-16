package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.extension.PsiClassExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */
@SuppressWarnings("ALL")
public class LombokTestData {

    private static final long serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;
    public LombokTestData getData() {
        return data;
    }
    public void setData(LombokTestData data) {
        this.data = data;
    }
    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    public class LombokGetters {
        LombokTestData data;
        boolean ok;
        public LombokTestData getData() {
            return data;
        }
        public boolean isOk() {
            return ok;
        }

        public class LombokGettersPartial {
            LombokTestData data;
            boolean ok;
            public boolean isOk() {
                return ok;
            }
        }
    }

    public class LombokSetters {
        LombokTestData data;
        boolean ok;
        public LombokTestData getData() {
            return data;
        }
        public void setData(LombokTestData data) {
            this.data = data;
        }
        public boolean isOk() {
            return ok;
        }
        public void setOk(boolean ok) {
            this.ok = ok;
        }

        public class LombokSettersPartial {
            LombokTestData data;
            boolean ok;
            public void setData(LombokTestData data) {
                this.data = data;
            }
        }

        public class LombokSettersFinalField {
            LombokTestData data;
            final boolean ok = true;
            public void setData(LombokTestData data) {
                this.data = data;
            }
        }
    }

    public class ToStringFull {
        LombokTestData data;
        boolean ok;

        @Override
        public String toString() {
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
        }

        public class ToStringPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }

        public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }
    }

    public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        public class EqualsPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class HashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class HashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class DataFull {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }
        public void setData(LombokTestData data) {
            this.data = data;
        }
        public boolean isOk() {
            return ok;
        }
        public void setOk(boolean ok) {
            this.ok = ok;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        public class DataWithoutToString {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }
            public void setData(LombokTestData data) {
                this.data = data;
            }
            public boolean isOk() {
                return ok;
            }
            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

        }

        public class DataWithPartialGetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }
            public void setData(LombokTestData data) {
                this.data = data;
            }
            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }
            public void setData(LombokTestData data) {
                this.data = data;
            }
            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }
    }

    public class FoldOn {
        public class FoldOnPublic{
            boolean ok;
            public boolean isOk() {
                return ok;
            }
        }

        class FoldOnClass{
            boolean ok;
            public boolean isOk() {
                return ok;
            }
        }

        @SuppressWarnings("ALL")
        class FoldOnWithAnnotation {
            boolean ok;
            public boolean isOk() {
                return ok;
            }
        }
    }
    
    public class DirtyLombokGetters {
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {
            return dirty2;
        }

        public boolean isDirty2() {
            return dirty;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public boolean isOk() {
                return ok;
            }
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

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public boolean isDirty() {
                return dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }

        public class DirtySingle {
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
        }
    }

}
