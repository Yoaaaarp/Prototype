package tuto.david.prototype.database.entity;


import java.util.ArrayList;
import java.util.List;

public class Chat {
    private long id;
    private String title;
    private List<Member> members;
    private List<Message> messages;

    // constructeur pour nouveau fil de discussion
    public Chat(long id, String title, List<Member> members){
        this.id = id;
        this.title = title;
        this.members = members;
        messages = new ArrayList<>();
    }

    // constructeur pour fil de discussion existant (après recupépration des données depuis le serveur
    public Chat(long id, String title, List<Member> members, List<Message> messages){
        this.id = id;
        this.title = title;
        this.members = members;
        this.messages = messages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
