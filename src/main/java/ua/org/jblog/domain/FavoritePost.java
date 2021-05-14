package ua.org.jblog.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class FavoritePost
{
    @EmbeddedId
    private FavoritePostPK id;
    private LocalDateTime created;

    public FavoritePostPK getId()
    {
        return id;
    }

    public void setId(FavoritePostPK favoritePostPK)
    {
        this.id = favoritePostPK;
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
