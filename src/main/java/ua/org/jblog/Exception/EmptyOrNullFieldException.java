package ua.org.jblog.Exception;

public class EmptyOrNullFieldException extends NullPointerException
{
    public EmptyOrNullFieldException(String s)
    {
        super(s);
    }
}
