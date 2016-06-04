package tuto.david.prototype.database.entity;


public class Subscription {
    private long id;
    private Member member;
    private Chat chat;

    public Subscription(long id, Member member, Chat chat){
        this.id = id;
        this.chat = chat;
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
