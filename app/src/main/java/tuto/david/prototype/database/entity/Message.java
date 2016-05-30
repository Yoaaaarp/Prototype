package tuto.david.prototype.database.entity;


import java.util.Date;

public class Message {
    private Member author;
    private Chat chat;
    private String text;
    //private Date creationDate;
    private int importance;

    public Message(Member author, Chat chat, String text, int importance){
        this.author = author;
        this.chat = chat;
        this.text = text;
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

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

   /* public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }*/

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
