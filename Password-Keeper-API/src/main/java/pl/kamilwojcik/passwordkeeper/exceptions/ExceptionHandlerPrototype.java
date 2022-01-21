package pl.kamilwojcik.passwordkeeper.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExceptionHandlerPrototype {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ErrorBody handleException(Exception ex, ErrorCode errorCode) {
        logException(ex);

        return errorCode.toErrorBody();
    }

    protected void logException(Exception ex) {
        logger.warn(String.format("Exception %s with msg: %s has been thrown by %s and handled by %s.",
                ex.getClass(),
                ex.getMessage(),
                this.getThrowingClass(ex).getName(),
                this.getClass().getName()
        ));
    }

    protected void logError(Exception ex) {
        logger.error("The following exception has been thrown", ex);
    }

    protected void logError(String msg, Exception ex) {
        logger.error(msg, ex);
    }

    protected Class<?> getThrowingClass(Throwable ex) {
        var stackTrace = ex.getStackTrace();
        var size = stackTrace.length;

        return stackTrace[size - 1].getClass();
    }

}
