package exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DaoException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(DaoException.class);

    public DaoException(String message) {
        super(message);
        log.error(message);

    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
        log.error(message + cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
        log.error(cause.toString());
    }
}
