package model;

public class Contact {
    private int id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private String createdAt;

    public Contact(int id, String name, String email, String subject, String message, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public String getCreatedAt() { return createdAt; }
}