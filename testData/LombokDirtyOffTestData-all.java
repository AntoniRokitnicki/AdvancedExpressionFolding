<fold text='📦' expand='false'>package</fold> data;

public <fold text='🏛️' expand='false'>class</fold> LombokDirtyOffTestData {

    public <fold text='🏛️' expand='false'>class</fold> DirtyLombokGetters <fold text='{...}' expand='true'>{
        <fold text='🔘' expand='false'>boolean</fold> dirty;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> dirty2;

        public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='🔘' expand='false'>boolean</fold> isDirty2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DirtyData <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Getter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> <fold text='🔘' expand='false'>boolean</fold> ok;

            public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>!dirty<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> DirtyData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> DirtySingle <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Getter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
            public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DirtyLombokSetters <fold text='{...}' expand='true'>{
        <fold text='🔘' expand='false'>boolean</fold> dirty;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> dirty2;

        public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.dirty2 = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> setDirty2(<fold text='🔘' expand='false'>boolean</fold> dirty2)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.dirty = dirty2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DirtyData <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Setter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> <fold text='🔘' expand='false'>boolean</fold> ok;

            public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.dirty = !dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> DirtyData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            }<fold text='' expand='false'></fold></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> DirtySingle <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Setter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>
}
