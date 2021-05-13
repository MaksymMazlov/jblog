package ua.org.jblog.Exception;

public class NotFoundException extends AbstractJBlogException
{
    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
