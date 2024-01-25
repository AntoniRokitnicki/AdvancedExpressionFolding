import <fold text='...' expand='false'>java.util.List;
import java.util.Optional;
import java.util.stream.Stream;</fold>

<fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokTestData {

    private static final long serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='true'>
    </fold><fold text='' expand='true'>public LombokTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='true'></fold>
    </fold><fold text='' expand='true'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='true'></fold>
    </fold><fold text='' expand='true'>public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = string;<fold text=' }' expand='false'>
    }</fold></fold>
    public LombokTestData getDataMethod(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold>
    public Optional<LombokTestData> getDataOpt()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data);<fold text=' }' expand='false'>
    }</fold>
    public Stream<LombokTestData> getDataStream()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data).stream();<fold text=' }' expand='false'>
    }</fold>
    public List<LombokTestData> getDataList()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data).stream().toList();<fold text=' }' expand='false'>
    }</fold>
}
