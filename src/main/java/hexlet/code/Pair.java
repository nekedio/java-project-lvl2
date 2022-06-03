package hexlet.code;

public final class Pair {
    private final Object value;
    private final Object oldValue;
    private final String status;

//    private final String type;

    public Pair(Object valuePair, Object oldValuePair, String statusPair) {
        this.value = valuePair;
        this.oldValue = oldValuePair;
        this.status = statusPair;
//        this.type = type;

    }

    public String getStatus() {
        return status;
    }

    public String toStringValue() {
        return String.valueOf(value);
    }

    public String toStringOldValue() {
        return String.valueOf(oldValue);
    }
}
