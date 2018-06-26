package schedule.exception;

public class CustomServiceException extends Exception{

    public CustomServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
