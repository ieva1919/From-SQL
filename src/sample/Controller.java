package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, String> surname;

    @FXML
    private TableColumn<Student, String> email;

    @FXML
    private TableColumn<Student, String> phone;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //uzkraunamas pirmas preis parodant vartotojui

        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
       surname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        phone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));


        Connection connection = MyConnectionHandler.getConnection();

        pullDateFromDb(connection);

        if(connection != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Prisijungeme sekmingai prie musu DB");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Prisijungti prie duomenu bazes nepavyko");
            alert.show();
        }

    }
    //metodas, kuris isrenka studentus is duomenu bazes
    private void pullDateFromDb(Connection connection) {
        if (connection != null) {
            try {
                Statement st = connection.createStatement(); //kad butu ivykdyta query
                ResultSet resultSet = st.executeQuery("SELECT * FROM students"); //paleidinejami query

                List<Student> students = new ArrayList<>();

                while (resultSet.next()) {
                    students.add(new Student(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("surname"), resultSet.getString("phone"), resultSet.getString("email")));
                }

                table.setItems(FXCollections.observableList(students));


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    /*private void createDummyDate() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ieva", "Sinkeviciute", "ieva1919@gmail.com","+370654678826"));
        students.add(new Student(1, "Agne", "Bajoraite", "1919@gmail.com","+37044566626"));
        students.add(new Student(1, "Vaiva", "Kaunaite", "5626878@gmail.com","+37222222228826"));
        students.add(new Student(1, "Egle", "Matuleciute", "ieva@gmail.com","+37111111826"));
        ObservableList<Student> moockStudents = FXCollections.observableList(students);
        table.setItems(moockStudents);
    }*/

}
