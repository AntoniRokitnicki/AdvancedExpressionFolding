<fold text='📦' expand='false'>package</fold> data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
public <fold text='🏛️' expand='false'>class</fold> ExperimentalTestData {

    <fold text='@Getter @Setter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> NamelessProperty <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> NamelessProperty prop;

        public NamelessProperty get()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prop<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public data.ExperimentalTestData.NamelessProperty getProp()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prop<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setProp(NamelessProperty prop)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.prop = <fold text='<<' expand='false'>prop</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='💀' expand='false'>void</fold> set(NamelessProperty s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.prop = s<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        NamelessProperty main(NamelessProperty s, NamelessProperty namelessProperty) <fold text='{...}' expand='true'>{
            s.set(namelessProperty.<fold text='prop' expand='false'>getProp()</fold>.get());
            s.set(namelessProperty.<fold text='prop' expand='false'>getProp()</fold>);
            s.<fold text='prop = ' expand='false'>setProp(</fold>namelessProperty.<fold text='prop' expand='false'>getProp()</fold>.get()<fold text='' expand='false'>)</fold>;
            s.<fold text='prop = ' expand='false'>setProp(</fold>namelessProperty<fold text='<<' expand='false'>.getProp()</fold><fold text='' expand='false'>)</fold>;


            s.set(namelessProperty.get());
            s.set(namelessProperty.get().get()); //TODO:

            <fold text='' expand='false'>System.out.</fold>println(s.get());
            s.get();
            s.set(namelessProperty);
            s.set(s.get());
            s.set(namelessProperty.get().get().get());
            s.set(main(s.get(), namelessProperty.get()));
            <fold text='🔙' expand='false'>return</fold> namelessProperty.get();
        }</fold>
    }</fold>


}
