package ua.org.jblog.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class UserLike
{
    @EmbeddedId
    private UserLikePK id;
    private LocalDateTime created;

    public UserLikePK getId()
    {
        return id;
    }

    public void setId(UserLikePK id)
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
