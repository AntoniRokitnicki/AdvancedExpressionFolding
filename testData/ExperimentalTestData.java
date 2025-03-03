package data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
public class ExperimentalTestData {

    <fold text='@Getter @Setter c' expand='false'>c</fold>lass NamelessProperty <fold text='{...}' expand='true'>{
        private NamelessProperty prop;

        public NamelessProperty get()<fold text=' { ' expand='false'> {
            </fold>return prop;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public data.ExperimentalTestData.NamelessProperty getProp()<fold text=' { ' expand='false'> {
            </fold>return prop;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setProp(NamelessProperty prop)<fold text=' { ' expand='false'> {
            </fold>this.prop = prop;<fold text=' }' expand='false'>
        }</fold></fold>

        public void set(NamelessProperty s)<fold text=' { ' expand='false'> {
            </fold>this.prop = s;<fold text=' }' expand='false'>
        }</fold>

        NamelessProperty main(NamelessProperty s, NamelessProperty namelessProperty) <fold text='{...}' expand='true'>{
            s.set(namelessProperty.getProp().get());
            s.set(namelessProperty.getProp());
            s.setProp(namelessProperty.getProp().get());
            s.setProp(namelessProperty.getProp());


            s.set(namelessProperty.get());
            s.set(namelessProperty.get().get()); //TODO:

            System.out.println(s.get());
            s.get();
            s.set(namelessProperty);
            s.set(s.get());
            s.set(namelessProperty.get().get().get());
            s.set(main(s.get(), namelessProperty.get()));
            return namelessProperty.get();
        }</fold>
    }</fold>


}
