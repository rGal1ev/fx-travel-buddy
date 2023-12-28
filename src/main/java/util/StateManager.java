package util;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private static final Map<String, Object> state = new HashMap<>();

    public static Object get(String key) {
        return state.get(key);
    }

    public static void update(String key, Object newValue) {
        state.put(key, newValue);
    }
}
