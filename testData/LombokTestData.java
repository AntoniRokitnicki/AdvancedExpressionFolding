package data;

import <fold text='...' expand='false'>org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;</fold>

<fold text='@Getter @Setter @Serial @' expand='false'>@</fold>SuppressWarnings("ALL")
public class LombokTestData {<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private static final long serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='false'>
    </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = ok;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = string;<fold text=' }' expand='false'>
    }</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data);<fold text=' }' expand='false'>
    }</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public data.LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Getter* p' expand='false'>p</fold>ublic class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Setter* p' expand='false'>p</fold>ublic class LombokSettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@Setter p' expand='false'>p</fold>ublic class LombokSettersFinalField <fold text='{...}' expand='true'>{
            LombokTestData data;
            final boolean ok = true;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{"<fold text=' + ' expand='false'> +
                    </fold>"data=" + data<fold text=' + ' expand='false'> +
                    </fold>", ok=" + ok<fold text=' + ' expand='false'> +
                    </fold>'}';
        }</fold></fold>

        <fold text='@ToString* p' expand='false'>p</fold>ublic class ToStringPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold></fold>
        }</fold>

        <fold text='@ToString* p' expand='false'>p</fold>ublic class ToStringPartial2 <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold></fold>

        <fold text='@EqualsAndHashCode* p' expand='false'>p</fold>ublic class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@EqualsAndHashCode* p' expand='false'>p</fold>ublic class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold></fold>

        <fold text='@Equals* p' expand='false'>p</fold>ublic class EqualsPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold></fold>
        }</fold>

        <fold text='@Equals* p' expand='false'>p</fold>ublic class EqualsPartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@HashCode p' expand='false'>p</fold>ublic class HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold></fold>

        <fold text='@HashCode* p' expand='false'>p</fold>ublic class HashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@HashCode* p' expand='false'>p</fold>ublic class HashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Data p' expand='false'>p</fold>ublic class DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'></fold>
        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>@Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic class DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold>

        }</fold>

        <fold text='@Data* p' expand='false'>p</fold>ublic class DataWithPartialGetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>

        <fold text='@Data* p' expand='false'>p</fold>ublic class DataWithPartialSetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>
    }</fold>

}
