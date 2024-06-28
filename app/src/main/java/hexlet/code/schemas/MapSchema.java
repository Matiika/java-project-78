package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    private Map<String, BaseSchema> rules;
    private Integer size;

    public <T> MapSchema() {
        super();
        this.rules = new HashMap<>();
        this.size = null;
    }

    /*public boolean isValid(Map map) {
        boolean valid = true;

        if (required) {
            if (map == null) {
                return false;
            }
        } else if (map == null) {
            return true;
        }

        if (size != null && map.size() != size) {
            return false;
        }

        for (var key : map.keySet()) {
            if (this.rules.containsKey(key)) {
                System.out.println(this.rules.get(key));
                BaseSchema rule = rules.get(key);
                if (!rule.isValid(map.get(key))) {
                    return false;
                }
            }
        }

        return valid;
    }*/

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        return this;
    }

    public <T> void shape(Map<String, BaseSchema<T>> map) {
        for (var key : map.keySet()) {
            this.rules.put(key, map.get(key));
        }
    }


    @Override
    public boolean isValid(Object value) {

        var map = (Map) value;

        if (required) {
            if (map == null) {
                return false;
            }
        } else if (map == null) {
            return true;
        }

        if (size != null && map.size() != size) {
            return false;
        }

        for (var key : map.keySet()) {
            if (this.rules.containsKey(key)) {
                BaseSchema rule = rules.get(key);
                if (!rule.isValid(map.get(key))) {
                    return false;
                }
            }
        }

        return true;
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }


}
