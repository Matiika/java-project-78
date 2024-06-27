package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private Integer size;

    public MapSchema() {
        super();
        this.size = null;
    }

    public boolean isValid(Map map) {
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

        return valid;
    }

    public MapSchema sizeof(Integer newSize) {
        this.size = newSize;
        return this;
    }


    public MapSchema required() {
        this.required = true;
        return this;
    }


}
