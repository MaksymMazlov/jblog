package ua.org.jblog.Exception;

public abstract class AbstractJBlogException extends RuntimeException {
    public AbstractJBlogException(String message) {
        super(message);
    }

    public AbstractJBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractJBlogException(Throwable cause) {
        super(cause);
    }
}
