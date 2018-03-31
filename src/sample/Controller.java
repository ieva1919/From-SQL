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


    @FXML
    private TableView<StudentMark> marksTable;
    @FXML
    private TableColumn<StudentMark, String> marksTable_name;
    @FXML
    private TableColumn<StudentMark, String> marksTable_surname;
    @FXML
    private TableColumn<StudentMark, Integer> marksTable_mark;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //uzkraunamas pirmas preis parodant vartotojui

        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        phone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        marksTable_name.setCellValueFactory(new PropertyValueFactory<StudentMark, String>("name"));
        marksTable_surname.setCellValueFactory(new PropertyValueFactory<StudentMark, String>("surname"));
        marksTable_mark.setCellValueFactory(new PropertyValueFactory<StudentMark, Integer>("mark"));

        Connection connection = MyConnectionHandler.getConnection();

        if(connection != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Prisijungeme sekmingai prie musu DB");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Prisijungti prie duomenu bazes nepavyko");
            alert.show();
        }

        createTables(connection);


        try {
            Statement statemant  = connection.createStatement();
            statemant.executeUpdate("UPDATE students set name ='Jolita' where id=1");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        pullDateFromDb(connection);
        pullStudnetMarks(connection);

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

    private void pullStudnetMarks(Connection connection) {
        if (connection != null) {
            try {
                Statement st = connection.createStatement(); //kad butu ivykdyta query
                ResultSet resultSet = st.executeQuery("SELECT name, surname, mark FROM students JOIN student_marks_laukai ON students.ID = student_marks_laukai.Students_ID where mark < 5"); //paleidinejami query

                List<StudentMark> studentMarks = new ArrayList<>();

                while (resultSet.next()) {
                    studentMarks.add(new StudentMark(
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("mark")
                    ));
                }

                marksTable.setItems(FXCollections.observableList(studentMarks));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTables (Connection connection) {
        if(QueryUtils.isTableExist(connection, "students")) {
            System.out.println("Lentele students egzistuoja");
        } else {
            System.out.println("Lentele students neegzistuoja");
        }
        if(QueryUtils.isTableExist(connection, "studentsMarks")) {
            System.out.println("Lentele studentsMarks egzistuoja");
        } else {
            System.out.println("Lentele studentsMarks neegzistuoja");

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
