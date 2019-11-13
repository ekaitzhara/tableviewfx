package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.db.DBKudeatzaile;
import ehu.isad.db.StudentsKud;
import ehu.isad.model.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    private static int studentid = 3;
    @FXML
    private TableView<StudentsModel> tbData;
    @FXML
    private TableColumn<StudentsModel, Integer> studentId;

    @FXML
    private TableColumn<StudentsModel, String> firstName;

    @FXML
    private TableColumn<StudentsModel, String> lastName;

    @FXML
    private TableColumn<StudentsModel, Image> image;

    @FXML
    private Button sartubotoia;

    @FXML
    private Button gordebotoia;


    private Main main;

    // add your data here from any source
    private ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList(
            StudentsKud.getInstantzia().lortuIkasleak()
    );



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbData.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        lastName.setCellFactory(TextFieldTableCell.forTableColumn());

        image.setCellValueFactory(new PropertyValueFactory<StudentsModel, Image>("image"));

        image.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);

                    // INPORTANTEA
                    tbData.refresh();
            }
        };
    });


        //add your data to the table here.
        tbData.setItems(studentsModels);
    }


    public void setMainApp(Main main) {
        this.main = main;
    }

    public void sartu(ActionEvent actionEvent) {

//        try(InputStream in = new URL("https://www.eldiario.es/fotos/mejores-memes-debate-electoral_EDIIMA20191102_0433_26.jpg").openStream()){
//            Files.copy(in, Paths.get("build/resources/main/memes.jpg"));
//            Files.copy(in, Paths.get("src/main/resources/memes.jpg"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        studentsModels.add(new StudentsModel(studentid++,"meme","last", "memes.jpg"));

    }

    public void gorde(ActionEvent actionEvent) {
        System.out.println("gorde");
        DBKudeatzaile dbKud = DBKudeatzaile.getInstantzia();
        for (StudentsModel s: studentsModels) {
            dbKud.execSQL("INSERT INTO studentsModel(firstName, lastName, imagePath) VALUES ('" + s.getFirstName() + "', '" + s.getLastName() + "', '" + s.getImagePath() + "');");
        }

    }
}