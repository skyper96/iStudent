package ro.ubb.istudent.exception;

public class SqlConnectException extends RuntimeException {

    public SqlConnectException(String message) {
        super (message);
    }
}
