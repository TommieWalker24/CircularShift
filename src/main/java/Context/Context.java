package Context;

import java.util.HashMap;
import java.util.Map;
//context will contain a map so it will be able to
public class Context {
    private Map<String, Object> parameters;

    public Context() {
        this.parameters = new HashMap<>();
    }
    public <T> T getParameter(final String key) {
        return (T) this.parameters.get(key);
    }
    public void putParameter(final String key, final Object value) {
        this.parameters.put(key, value);
    }
    public boolean findParameterByKey(final String key) {
        return parameters.containsKey(key);
    }
    public void clearParameterByKey(final String key) {
        parameters.remove(key);
    }

}
