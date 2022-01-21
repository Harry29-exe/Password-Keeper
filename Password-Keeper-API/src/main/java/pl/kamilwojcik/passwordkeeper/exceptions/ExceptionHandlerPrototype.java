package pl.kamilwojcik.passwordkeeper.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorHandlerPriority.MODULE_HANDLER;

@Order(MODULE_HANDLER)
public abstract class ExceptionHandlerPrototype {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void logException(Exception ex) {
        logger.warn(String.format("Exception %s with msg: %s has been thrown by %s and handled by %s.",
                ex.getClass(),
                ex.getMessage(),
                this.getThrowingClass(ex).getName(),
                this.getClass().getName()
        ));
    }

    protected Class<?> getThrowingClass(Throwable ex) {
        var stackTrace = ex.getStackTrace();
        var size = stackTrace.length;

        return stackTrace[size - 1].getClass();
    }

}
