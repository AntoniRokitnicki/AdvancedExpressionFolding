package data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PseudoAnnotationsCacheTestData {

    private int valueCache;
    private boolean valueCacheInitialized;

    public int value() {
        if (valueCacheInitialized) {
            return valueCache;
        }
        int __result = value$impl();
        valueCache = __result;
        valueCacheInitialized = true;
        return __result;
    }

    private int value$impl() {
        return 42;
    }

    private static final Map<List<Object>, String> formatCache = new HashMap<>();

    public static String format(String prefix, int value) {
        List<Object> __cacheKey = Arrays.asList(prefix, value);
        Map<List<Object>, String> __cache = formatCache;
        String __cachedValue = __cache.get(__cacheKey);
        if (__cachedValue != null || __cache.containsKey(__cacheKey)) {
            return __cachedValue;
        }
        String __result = format$impl(prefix, value);
        __cache.put(__cacheKey, __result);
        return __result;
    }

    private static String format$impl(String prefix, int value) {
        return prefix + value;
    }
}
