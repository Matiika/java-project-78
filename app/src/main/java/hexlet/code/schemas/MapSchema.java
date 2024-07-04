package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    private Map<String, BaseSchema> rules;
    private Integer size;

    public <T> MapSchema() {
        super();
        this.rules = new HashMap<>();
        this.size = null;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> map) {
        for (var key : map.keySet()) {
            this.rules.put(key, map.get(key));
        }
        Predicate<Object> shapePredicate = value -> {
            Map mapValue = (Map) value;
            for (var key : mapValue.keySet()) {
                if (this.rules.containsKey(key)) {
                    BaseSchema rule = rules.get(key);
                    var valueToCheck = mapValue.get(key);
                    if (!rule.isValid(valueToCheck)) {
                        return false;
                    }
                }
            }
            return true;
        };
        addCheck("shape", shapePredicate);
        return this;
    }

    public MapSchema required() {
        this.required = true;
        Predicate<Object> reqPredicate = value -> {
            Map mapValue = (Map) value;
            return mapValue != null;
        };
        addCheck("required", reqPredicate);
        return this;
    }

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        Predicate<Object> reqPredicate = value -> {
            Map mapValue = (Map) value;
            return (size == null) || (mapValue.size() == size);
        };
        addCheck("required", reqPredicate);
        return this;
    }

}
