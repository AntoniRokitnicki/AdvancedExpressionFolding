package data;

import java.io.IOException;
import java.util.Optional;

public class PseudoAnnotationsOptionalTestData {

    public String findName() {
        return "";
    }

    public Optional<String> optionalFindName() {
        return Optional.ofNullable(findName());
    }

    public int findAge() {
        return 0;
    }

    public Optional<Integer> optionalFindAge() {
        return Optional.ofNullable(findAge());
    }

    public <T> T find(Class<T> type) throws IOException {
        return null;
    }

    public <T> Optional<T> optionalFind(Class<T> type) throws IOException {
        return Optional.ofNullable(find(type));
    }
}
