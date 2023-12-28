package models;

public class User {
    private int ID;
    private String login;

    public User(int ID, String login) {
        this.ID = ID;
        this.login = login;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
