package data;

public class LombokDirtyOffTestData {

    public class DirtyLombokGetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty()<fold text=' { ' expand='false'> {
            </fold>return dirty2;<fold text=' }' expand='false'>
        }</fold>

        public boolean isDirty2()<fold text=' { ' expand='false'> {
            </fold>return dirty;<fold text=' }' expand='false'>
        }</fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter p' expand='false'>p</fold>rivate boolean ok;

            public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return !dirty;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
            public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty2;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    <fold text='@Setter p' expand='false'>p</fold>ublic class DirtyLombokSetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
            </fold>this.dirty2 = dirty;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setDirty2(boolean dirty2)<fold text=' { ' expand='false'> {
            </fold>this.dirty = dirty2;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            private boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.dirty = !dirty;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>

        <fold text='@Setter p' expand='false'>p</fold>ublic class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.ok = dirty;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>
}
