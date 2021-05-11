package ua.org.jblog.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserLikePK implements Serializable
{
    private int userId;
    private int postId;

    public UserLikePK()
    {
    }

    public UserLikePK(int userId, int postId)
    {
        this.userId = userId;
        this.postId = postId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
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
        UserLikePK that = (UserLikePK) o;
        return userId == that.userId && postId == that.postId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, postId);
    }
}
