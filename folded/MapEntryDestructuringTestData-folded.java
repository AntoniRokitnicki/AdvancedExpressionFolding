package data;

import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class MapEntryDestructuringTestData {

    public void destructured(Map<String, Integer> stock, Warehouse warehouse, Map<String, Integer> report) {
        for ((key, value) in stock) {
            System.out.println("$key: $value");
        }

        int total = 0;
        for ((key, value) in stock) {
            total += value;
            report.put(key, value * 2);
        }

        for ((key, value) in warehouse.stock) {
            String line = "$key=$value";
            report -= line;
        }

        for ((key, _) in stock) {
            report -= key;
        }

        int sum = 0;
        for ((_, value) in stock) {
            sum += value;
        }
    }

    public void notDestructured(Map<String, Integer> stock, List<Map.Entry<String, Integer>> entries, String key) {
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            e.value = e.value + 1;
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            audit(e);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            int value = e.value;
            System.out.println(e.key + value);
        }

        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(key + e.key);
        }

        for (Map.Entry<String, Integer> e : entries) {
            System.out.println("${e.key}: ${e.value}");
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
