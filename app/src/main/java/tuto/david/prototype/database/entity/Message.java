package tuto.david.prototype.database.entity;


public class Message {
    private long id;
    private long author;    // author id
    private long chat;      // chat id
    private String text;
    //private Date creationDate;
    private int importance;
    private boolean left;

    public Message(){
        id = -1;
        author = -1;
        chat = -1;
        text = null;
        importance = 1;
        left = false;
    }

    public Message(long id, long author, long chat, String text, int importance){
        this.id = id;
        this.author = author;
        this.chat = chat;
        this.text = text;
        this.importance = importance;
        this.left = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getChat() {
        return chat;
    }

    public void setChat(long chat) {
        this.chat = chat;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public boolean getLeft() { return left; }

    public void setLeft(boolean left) { this.left = left; }

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
