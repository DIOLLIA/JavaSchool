package schedule.exception;

import org.hibernate.HibernateException;

public class CustomDaoException extends HibernateException {
    public CustomDaoException(String message) {
        super(message);
    }

    public CustomDaoException(Throwable cause) {
        super(cause);
    }

    public CustomDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
