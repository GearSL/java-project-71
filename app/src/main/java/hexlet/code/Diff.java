package hexlet.code;

public class Diff {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String state;

    public Diff(String key, Object oldValue, Object newValue, String state) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("key: %s, OldValue: %s, NewValue: %s, State: %s", key, oldValue, newValue, state);
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getState() {
        return state;
    }
}
