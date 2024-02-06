package data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LombokTestData {

    private static final long serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;
    public LombokTestData getData() {
        return data;
    }
    public boolean isOk() {
        return ok;
    }
    public void setData(LombokTestData data) {
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
    public LombokTestData getDataMethod(LombokTestData data) {
        return data;
    }
    public Optional<LombokTestData> getDataOpt() {
        return Optional.ofNullable(data);
    }
    public Stream<LombokTestData> getDataStream() {
        return Optional.ofNullable(data).stream();
    }
    public List<LombokTestData> getDataList() {
        return Optional.ofNullable(data).stream().toList();
    }
}
