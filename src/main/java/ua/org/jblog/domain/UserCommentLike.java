package ua.org.jblog.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class UserCommentLike
{
    @EmbeddedId
    private UserCommentLikePK id;
    private LocalDateTime created;

    public UserCommentLikePK getId()
    {
        return id;
    }

    public void setId(UserCommentLikePK id)
    {
        this.id = id;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }
}
