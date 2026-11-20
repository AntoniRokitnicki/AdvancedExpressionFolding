package data;

import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class MapEntryDestructuringTestData {

    public void destructured(Map<String, Integer> stock, Warehouse warehouse, Map<String, Integer> report) {
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        int total = 0;
        for (final Map.Entry<String, Integer> item : stock.entrySet()) {
            total += item.getValue();
            report.put(item.getKey(), item.getValue() * 2);
        }

        for (var e : warehouse.getStock().entrySet()) {
            String line = e.getKey() + "=" + e.getValue();
            report.remove(line);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            report.remove(e.getKey());
        }
    }

    public void notDestructured(Map<String, Integer> stock, List<Map.Entry<String, Integer>> entries, String key) {
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            e.setValue(e.getValue() + 1);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            audit(e);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            int value = e.getValue();
            System.out.println(e.getKey() + value);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(key + e.getKey());
        }

        for (Map.Entry<String, Integer> e : entries) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    void audit(Map.Entry<String, Integer> entry) {
    }

    static class Warehouse {
        Map<String, Integer> stock;

        public Map<String, Integer> getStock() {
            return stock;
        }
    }
}
