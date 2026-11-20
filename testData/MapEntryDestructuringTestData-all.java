package data;

import <fold text='...' expand='false'>java.util.List;
import java.util.Map;</fold>

@SuppressWarnings("ALL")
public class MapEntryDestructuringTestData {

    public void destructured(Map<String, Integer> stock, Warehouse warehouse, Map<String, Integer> report) <fold text='{...}' expand='true'>{
        for <fold text='' expand='false'>(</fold><fold text='(key, value) in ' expand='false'><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : </fold>stock<fold text='' expand='false'>.entrySet()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'> + "</fold>: <fold text='$' expand='false'>" + </fold><fold text='value' expand='false'>e.getValue()</fold><fold text='")' expand='false'>)</fold>;
        }</fold>

        <fold text='var' expand='false'>int</fold> total = 0;
        for <fold text='' expand='false'>(</fold><fold text='' expand='false'><fold text='(key, value) in ' expand='false'><fold text='val' expand='false'>final</fold> Map.Entry<String, Integer></fold> item : </fold>stock<fold text='' expand='false'>.entrySet()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            total += <fold text='value' expand='false'>item.getValue()</fold>;
            report<fold text='[' expand='false'>.put(</fold><fold text='key' expand='false'>item.getKey()</fold><fold text='] = ' expand='false'>, </fold><fold text='value' expand='false'>item.getValue()</fold> * 2<fold text='' expand='false'>)</fold>;
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='(key, value) in ' expand='false'><fold text='val' expand='false'>var</fold> e : </fold>warehouse.<fold text='stock' expand='false'>getStock()</fold><fold text='' expand='false'>.entrySet()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> line =<fold text=' "$' expand='false'> </fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'> + "</fold>=<fold text='$' expand='false'>" + </fold><fold text='value' expand='false'>e.getValue()</fold><fold text='";' expand='false'>;</fold>
            report<fold text=' -= ' expand='false'>.remove(</fold>line<fold text='' expand='false'>)</fold>;
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='(key, _) in ' expand='false'><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : </fold>stock<fold text='' expand='false'>.entrySet()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            report<fold text=' -= ' expand='false'>.remove(</fold><fold text='key' expand='false'>e.getKey()</fold><fold text='' expand='false'>)</fold>;
        }</fold>

        <fold text='var' expand='false'>int</fold> sum = 0;
        for <fold text='' expand='false'>(</fold><fold text='(_, value) in ' expand='false'><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : </fold>stock<fold text='' expand='false'>.entrySet()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            sum += <fold text='value' expand='false'>e.getValue()</fold>;
        }</fold>
    }</fold>

    public void notDestructured(Map<String, Integer> stock, List<Map.Entry<String, Integer>> entries, String key) <fold text='{...}' expand='true'>{
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : stock.entrySet()<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            e.<fold text='value = ' expand='false'>setValue(</fold>e.<fold text='value' expand='false'>getValue()</fold> + 1<fold text='' expand='false'>)</fold>;
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : stock.entrySet()<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            audit(e);
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : stock.entrySet()<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>int</fold> value = e.<fold text='value' expand='false'>getValue()</fold>;
            <fold text='' expand='false'>System.out.</fold>println(e.<fold text='key' expand='false'>getKey()</fold> + value);
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : stock.entrySet()<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(key + e.<fold text='key' expand='false'>getKey()</fold>);
        }</fold>

        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>Map.Entry<String, Integer></fold> e : entries<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>e.<fold text='key' expand='false'>getKey()</fold><fold text='}' expand='false'> + "</fold>: <fold text='${' expand='false'>" + </fold>e.<fold text='value' expand='false'>getValue()</fold><fold text='}")' expand='false'>)</fold>;
        }</fold>
    }</fold>

    void audit(Map.Entry<String, Integer> entry) <fold text='{}' expand='true'>{
    }</fold>

    <fold text='@Getter s' expand='false'>s</fold>tatic class Warehouse <fold text='{...}' expand='true'>{
        Map<String, Integer> stock;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Map<String, Integer> getStock()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>stock<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>
}
