package data;

import <fold text='...' expand='false'>org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;</fold>

<fold text='@Getter @Setter @Serial @' expand='false'>@</fold>SuppressWarnings("ALL")
public class LombokTestData {<fold text='' expand='true'>

    </fold><fold text='' expand='true'>private static final long serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='true'>
    </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='true'></fold>
    </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='true'></fold>
    </fold><fold text='' expand='true'>public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = <fold text='<<' expand='false'>string</fold>;<fold text=' }' expand='false'>
    }</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {
        </fold>return <fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
    }</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>
        </fold><fold text='' expand='true'>public data.LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='true'></fold>
        </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Getter* p' expand='false'>p</fold>ublic class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>
            </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>
        </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>
        </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>
        </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='true'></fold>
        </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Setter* p' expand='false'>p</fold>ublic class LombokSettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@Setter p' expand='false'>p</fold>ublic class LombokSettersFinalField <fold text='{...}' expand='true'>{
            LombokTestData data;
            final boolean ok = true;<fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>

        </fold><fold text='' expand='true'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{<fold text='' expand='false'>" +
                    "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='' expand='false'> +
                    "</fold>, ok=<fold text='$' expand='false'>" + </fold>ok<fold text='${' expand='false'> +
                    </fold>'}'<fold text='}";' expand='false'>;</fold>
        }</fold></fold>

        <fold text='@ToString* p' expand='false'>p</fold>ublic class ToStringPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='}";' expand='false'>;</fold>
            }</fold></fold>
        }</fold>

        <fold text='@ToString* p' expand='false'>p</fold>ublic class ToStringPartial2 <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='}";' expand='false'>;</fold>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>

        <fold text='' expand='true'></fold>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsAndHashCodeFull</fold> that = <fold text='' expand='false'>(EqualsAndHashCodeFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold><fold text='' expand='true'></fold>

        </fold><fold text='' expand='true'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        <fold text='@EqualsAndHashCode* p' expand='false'>p</fold>ublic class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartial</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null;
            }</fold><fold text='' expand='true'></fold>

            </fold><fold text='' expand='true'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@EqualsAndHashCode* p' expand='false'>p</fold>ublic class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='true'>

            <fold text='' expand='true'></fold>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartialTwo</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartialTwo) </fold>o;
                return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
            }</fold><fold text='' expand='true'></fold>

            </fold><fold text='' expand='true'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>

        </fold><fold text='' expand='true'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsFull</fold> that = <fold text='' expand='false'>(EqualsFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold></fold>

        <fold text='@Equals* p' expand='false'>p</fold>ublic class EqualsPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()<fold text='' expand='false'></fold>)</fold> return false;
                <fold text='val' expand='false'>EqualsPartial</fold> that = <fold text='' expand='false'>(EqualsPartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null;
            }</fold></fold>
        }</fold>

        <fold text='@Equals* p' expand='false'>p</fold>ublic class EqualsPartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsPartialTwo</fold> that = <fold text='' expand='false'>(EqualsPartialTwo) </fold>o;
                return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@HashCode p' expand='false'>p</fold>ublic class HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>

        </fold><fold text='' expand='true'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        <fold text='@HashCode* p' expand='false'>p</fold>ublic class HashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@HashCode* p' expand='false'>p</fold>ublic class HashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Data p' expand='false'>p</fold>ublic class DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='true'>

        </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>
        </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='true'></fold>
        </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>
        </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>

        <fold text='' expand='true'></fold>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof DataFull)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>DataFull</fold> dataFull = <fold text='' expand='false'>(DataFull) </fold>o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
        }</fold></fold><fold text='' expand='true'>

        </fold><fold text='' expand='true'>@Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>

        </fold><fold text='' expand='true'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic class DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='true'></fold>
            </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='true'></fold>

            </fold><fold text='' expand='true'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold>

        }</fold>

        <fold text='@Data* p' expand='false'>p</fold>ublic class DataWithPartialGetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='true'></fold>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold></fold><fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold><fold text='' expand='true'></fold>

            </fold><fold text='' expand='true'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>

        <fold text='@Data* p' expand='false'>p</fold>ublic class DataWithPartialSetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='true'>

            </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='true'></fold>
            </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }<fold text='' expand='true'></fold></fold>

            </fold><fold text='' expand='true'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='true'>

            </fold><fold text='' expand='true'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>
    }</fold>

}
