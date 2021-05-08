package ua.org.jblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment
{
    @Id
    @GeneratedValue
    private int id;
    private String comment;
    private int postId;
    private String authorComment;
    private String created;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getAuthorComment()
    {
        return authorComment;
    }

    public void setAuthorComment(String authorComment)
    {
        this.authorComment = authorComment;
    }
}
