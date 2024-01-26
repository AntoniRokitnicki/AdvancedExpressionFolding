import <fold text='...' expand='false'>java.util.List;
import java.util.Optional;
import java.util.stream.Stream;</fold>

public class MapstructTestData {

    private static final long serialVersionUID = 1234567L;

    MapstructTestData data;
    boolean ok;
    String string;
    public MapstructTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold>
    public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold>
    <fold text='@Mapping(target = "data", source = "data")' expand='true'>public</fold><fold text='void setData(MapstructTestData data);' expand='true'> </fold>void<fold text='' expand='true'> </fold>setData(MapstructTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = data;<fold text=' }' expand='false'>
    }</fold>
    public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = ok;<fold text=' }' expand='false'>
    }</fold>
    public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold>
    public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = string;<fold text=' }' expand='false'>
    }</fold>
    public MapstructTestData getDataMethod(MapstructTestData data)<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold>
    public Optional<MapstructTestData> getDataOpt()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data);<fold text=' }' expand='false'>
    }</fold>
    public Stream<MapstructTestData> getDataStream()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data).stream();<fold text=' }' expand='false'>
    }</fold>
    public List<MapstructTestData> getDataList()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data).stream().toList();<fold text=' }' expand='false'>
    }</fold>
}
