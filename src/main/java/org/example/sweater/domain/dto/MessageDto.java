package org.example.sweater.domain.dto;


import org.example.sweater.domain.Message;
import org.example.sweater.domain.User;
import org.example.sweater.domain.util.MessageHelper;

public class MessageDto {
    private Integer id;
    private String tag;
    private String text;
    private User author;
    private String filename;
    private Integer likes;
    private Boolean isLiked;

    public MessageDto(Message message, Integer likes, Boolean isLiked) {
        this.id = message.getId();
        this.tag = message.getTag();
        this.text = message.getText();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.isLiked = isLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getLikes() {
        return likes;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", isLiked=" + isLiked +
                '}';
    }
}
