package models;

public class User {
    private int ID;
    private String login;
    private String FIO;

    public User(int ID, String login, String FIO) {
        this.ID = ID;
        this.login = login;
        this.FIO = FIO;
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

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
