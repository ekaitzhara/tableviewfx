package ehu.isad.model;

import javafx.scene.image.Image;

public class StudentsModel {

    private Integer studentId;
    private String firstName;
    private String lastName;
    private Image image;
    private String imagePath;

    public StudentsModel(Integer studentId, String firstName, String lastName, String imagePath) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = new Image(imagePath);
        this.imagePath = imagePath;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {this.studentId = studentId;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }
}