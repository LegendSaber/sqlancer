package sqlancer.common.query;

import java.util.Objects;

public class SQLQueryError implements Comparable<SQLQueryError> {

    public enum ErrorLevel {
        WARNING, ERROR
    }

    private ErrorLevel level;
    private int code;
    private String message;

    public void setLevel(ErrorLevel level) {
        this.level = level;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorLevel getLevel() {
        return level;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasSameLevel(SQLQueryError that) {
        if (level == null) {
            return that.getLevel() == null;
        } else {
            return level.equals(that.getLevel());
        }
    }

    public boolean hasSameCodeAndMessage(SQLQueryError that) {
        if (code != that.getCode()) {
            return false;
        }
        if (message == null) {
            return that.getMessage() == null;
        } else {
            return message.equals(that.getMessage());
        }
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that instanceof SQLQueryError) {
            SQLQueryError thatError = (SQLQueryError) that;
            return hasSameLevel(thatError) && hasSameCodeAndMessage(thatError);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, code, message);
    }

    @Override
    public String toString() {
        return String.format("Level: %s; Code: %d; Message: %s.", level, code, message);
    }

    @Override
    public int compareTo(SQLQueryError that) {
        if (code < that.getCode()) {
            return -1;
        } else if (code > that.getCode()) {
            return 1;
        }

        if (level == null && that.getLevel() != null) {
            return -1;
        } else {
            if (that.getLevel() == null) {
                return 1;
            } else {
                int res = level.compareTo(that.getLevel());
                if (res != 0) {
                    return res;
                }
            }
        }

        if (message == null && that.getMessage() != null) {
            return -1;
        } else {
            if (that.getMessage() == null) {
                return 1;
            } else {
                int res = message.compareTo(that.getMessage());
                if (res != 0) {
                    return res;
                }
            }
        }

        return 0;
    }
}
