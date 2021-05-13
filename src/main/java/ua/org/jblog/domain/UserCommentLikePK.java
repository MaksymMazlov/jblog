package ua.org.jblog.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCommentLikePK implements Serializable
{
    private int userId;
    private int commentId;

    public UserCommentLikePK()
    {
    }

    public UserCommentLikePK(int userId, int commentId)
    {
        this.userId = userId;
        this.commentId = commentId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getCommentId()
    {
        return commentId;
    }

    public void setCommentId(int commentId)
    {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserCommentLikePK that = (UserCommentLikePK) o;
        return userId == that.userId && commentId == that.commentId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, commentId);
    }
}
