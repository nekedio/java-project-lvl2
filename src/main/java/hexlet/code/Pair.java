package hexlet.code;

public final class Pair {
    private final Object value;
    private final Object oldValue;
    private final String status;

    public Pair(Object valuePair, Object oldValuePair, String statusPair) {
        this.value = valuePair;
        this.oldValue = oldValuePair;
        this.status = statusPair;

    }

    public String getStatus() {
        return status;
    }

    public String toStringValue() {
        return String.valueOf(value);
    }

    public String toStringValuePlainFormat() {

        if (value == null) {
            return null;
        }

//        System.out.println(value.toString() + "      \"" + value.getClass().toString() + "\"");

        if ("class java.util.ArrayList".equals((value.getClass().toString()))) {
            return "[complex value]";
        }

        if ("class java.util.LinkedHashMap".equals((value.getClass().toString()))) {
            return "[complex value]";
        }

        if ("class java.lang.Boolean".equals((value.getClass().toString()))) {
            return String.valueOf(value);
        }

        if ("class java.lang.Integer".equals((value.getClass().toString()))) {
            return String.valueOf(value);
        }

        return "'" + String.valueOf(value) + "'";
    }

    public String toStringOldValue() {
        return String.valueOf(oldValue);
    }

    public String toStringOldValuePlainFormat() {
//        return String.valueOf(oldValue);
        if (oldValue == null) {
            return null;
        }

        if ("class java.util.ArrayList".equals((oldValue.getClass().toString()))) {
            return "[complex value]";
        }

        if ("class java.util.LinkedHashMap".equals((oldValue.getClass().toString()))) {
            return "[complex value]";
        }

        if ("class java.lang.Boolean".equals((oldValue.getClass().toString()))) {
//            return "!!!";
            return String.valueOf(oldValue);
        }

        if ("class java.lang.Integer".equals((oldValue.getClass().toString()))) {
            return String.valueOf(oldValue);
        }

        return "'" + String.valueOf(oldValue) + "'";
    }
}
