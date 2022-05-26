package hexlet.code;

public class Pair {
    private String key;
    private Object value;
    private Object oldValue;

    public String getStatus() {
        return status;
    }

    private String status;

    public Pair(String key, Object value, Object oldValue, String status) {
        this.key = key;
        this.value = value;
        this.oldValue = oldValue;
        this.status = status;
    }

    public String toStringValue() {
        return String.valueOf(value);
    }

    public String toStringOldValue() {
        return String.valueOf(oldValue);
    }
}
