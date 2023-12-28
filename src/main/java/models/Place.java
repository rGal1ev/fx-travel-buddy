package models;

public class Place {
    private int ID;
    private String title;
    private String description;
    private String city;
    private String image;

    public Place(int ID, String title, String description, String city, String image) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.city = city;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
