package data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Getter @Setter @Serial public class LombokTestData {

    LombokTestData data;
    boolean ok;
    String string;
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
