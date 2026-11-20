package data;

import <fold text='...' expand='false'>java.util.List;
import java.util.Map;</fold>

@SuppressWarnings("ALL")
public class MapEntryDestructuringTestData {

    public void destructured(Map<String, Integer> stock, Warehouse warehouse, Map<String, Integer> report) <fold text='{...}' expand='true'>{
        for (<fold text='(key, value) in ' expand='false'>Map.Entry<String, Integer> e : </fold>stock<fold text='' expand='false'>.entrySet()</fold>) <fold text='{...}' expand='true'>{
            System.out.println<fold text='("$' expand='false'>(</fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'> + "</fold>: <fold text='$' expand='false'>" + </fold><fold text='value' expand='false'>e.getValue()</fold><fold text='")' expand='false'>)</fold>;
        }</fold>

        int total = 0;
        for (<fold text='(key, value) in ' expand='false'>final Map.Entry<String, Integer> item : </fold>stock<fold text='' expand='false'>.entrySet()</fold>) <fold text='{...}' expand='true'>{
            total += <fold text='value' expand='false'>item.getValue()</fold>;
            report.put(<fold text='key' expand='false'>item.getKey()</fold>, <fold text='value' expand='false'>item.getValue()</fold> * 2);
        }</fold>

        for (<fold text='(key, value) in ' expand='false'>var e : </fold>warehouse.<fold text='stock' expand='false'>getStock()</fold><fold text='' expand='false'>.entrySet()</fold>) <fold text='{...}' expand='true'>{
            String line =<fold text=' "$' expand='false'> </fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'> + "</fold>=<fold text='$' expand='false'>" + </fold><fold text='value' expand='false'>e.getValue()</fold><fold text='";' expand='false'>;</fold>
            report<fold text=' -= ' expand='false'>.remove(</fold>line<fold text='' expand='false'>)</fold>;
        }</fold>

        for (<fold text='(key, value) in ' expand='false'>Map.Entry<String, Integer> e : </fold>stock<fold text='' expand='false'>.entrySet()</fold>) <fold text='{...}' expand='true'>{
            report<fold text=' -= ' expand='false'>.remove(</fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    public void notDestructured(Map<String, Integer> stock, List<Map.Entry<String, Integer>> entries, String key) <fold text='{...}' expand='true'>{
        for (Map.Entry<String, Integer> e : stock.entrySet()) <fold text='{...}' expand='true'>{
            e.<fold text='value = ' expand='false'>setValue(</fold>e.<fold text='value' expand='false'>getValue()</fold> + 1<fold text='' expand='false'>)</fold>;
        }</fold>

        for (Map.Entry<String, Integer> e : stock.entrySet()) <fold text='{...}' expand='true'>{
            audit(e);
        }</fold>

        for (Map.Entry<String, Integer> e : stock.entrySet()) <fold text='{...}' expand='true'>{
            int value = e.<fold text='value' expand='false'>getValue()</fold>;
            System.out.println(e.<fold text='key' expand='false'>getKey()</fold> + value);
        }</fold>

        for (Map.Entry<String, Integer> e : stock.entrySet()) <fold text='{...}' expand='true'>{
            System.out.println(key + e.<fold text='key' expand='false'>getKey()</fold>);
        }</fold>

        for (Map.Entry<String, Integer> e : entries) <fold text='{...}' expand='true'>{
            System.out.println<fold text='("${' expand='false'>(</fold>e.<fold text='key' expand='false'>getKey()</fold><fold text='}' expand='false'> + "</fold>: <fold text='${' expand='false'>" + </fold>e.<fold text='value' expand='false'>getValue()</fold><fold text='}")' expand='false'>)</fold>;
        }</fold>
    }</fold>

    void audit(Map.Entry<String, Integer> entry) <fold text='{}' expand='true'>{
    }</fold>

    static class Warehouse <fold text='{...}' expand='true'>{
        Map<String, Integer> stock;

        public Map<String, Integer> getStock()<fold text=' { ' expand='false'> {
            </fold>return stock;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
