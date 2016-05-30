package tuto.david.prototype.database.entity;


import java.util.Date;

public class Message {
    private Member author;
    private Chat chat;
    private Date creationDate;
    private int importance;

    public Message(Member author, Chat chat, Date creationDate, int importance){
        this.author = author;
        this.chat = chat;
        this.creationDate = creationDate;
        this.importance = importance;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
