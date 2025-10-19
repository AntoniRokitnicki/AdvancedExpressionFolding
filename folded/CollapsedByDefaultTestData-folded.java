package data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollapsedByDefaultTestData {
    public List<String> upperCase(String[] args) {
        return args*.toUpperCase()
            .toList();
    }
}
