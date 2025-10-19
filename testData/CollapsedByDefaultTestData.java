package data;

import <fold text='...' expand='false'>java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;</fold>

public class CollapsedByDefaultTestData {
    public List<String> upperCase(String[] args) <fold text='{...}' expand='true'>{
        return <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
            .map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold>
            <fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
    }</fold>
}
