import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MapstructTestData {

    private static final long serialVersionUID = 1234567L;

    MapstructTestData data;
    boolean ok;
    String string;
    public MapstructTestData getData() {
        return data;
    }
    public boolean isOk() {
        return ok;
    }
    public void setData(MapstructTestData data) {
        this.data = data;
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
    public MapstructTestData getDataMethod(MapstructTestData data) {
        return data;
    }
    public Optional<MapstructTestData> getDataOpt() {
        return Optional.ofNullable(data);
    }
    public Stream<MapstructTestData> getDataStream() {
        return Optional.ofNullable(data).stream();
    }
    public List<MapstructTestData> getDataList() {
        return Optional.ofNullable(data).stream().toList();
    }
}
