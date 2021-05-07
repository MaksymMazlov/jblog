package ua.org.jblog.Exception;

public class NullException extends RuntimeException
{
    public NullException(String message)
    {
        super(message);
    }

    public NullException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
