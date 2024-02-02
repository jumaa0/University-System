/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univeristy_app;

import DTOs.crsDTO;
import DTOs.stDTO;
import controllers.StudentsController;
import db.DataAccessLayer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JUMAA
 */
public class AllStudentsController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_address;
    @FXML
    private Button btn_addStudent;
    @FXML
    private TableView<stDTO> tab_students;
    @FXML
    private TableColumn<stDTO, String> tab_stID;
    @FXML
    private TableColumn<stDTO, String> tab_stFname;
    @FXML
    private TableColumn<stDTO, String> tab_stLname;
    @FXML
    private TableColumn<stDTO, String> tab_stAddress;
    @FXML
    private TableColumn<stDTO, String> tab_stGpa;
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private ObservableList<stDTO> studentList = FXCollections.observableArrayList();
    @FXML
    private Button btn_updateGPA;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/univeristy_app/Report.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        
        btn_addStudent.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            stDTO student = new stDTO(txt_id.getText(), txt_fname.getText(), txt_lname.getText(), txt_address.getText());
            try {
                DataAccessLayer.addStudent(student);
            } catch (SQLException ex) {
                Logger.getLogger(AllStudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadstudentsFromServer();
            
        }
        });
        
        
        btn_updateGPA.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            stDTO student = new stDTO(txt_id.getText(), txt_fname.getText(), txt_lname.getText(), txt_address.getText());
            try {
                DataAccessLayer.updateGpa();
            } catch (SQLException ex) {
                Logger.getLogger(AllStudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadstudentsFromServer();
            
        }
        });
        
        
        loadstudentsFromServer();
        
        
        
    }
    
    public void loadstudentsFromServer() {
                    
        try {
            
            // Retrieve the list of courses for the given st_id
            ArrayList<stDTO> students = DataAccessLayer.loadStudents();

            // Clear existing items in the ObservableList
            studentList.clear();

            // Add the retrieved courses to the ObservableList
            studentList.addAll(students);

            // Set the items in the TableView
            tab_students.setItems(studentList);

            // Map TableView columns to crsDTO properties
            tab_stID.setCellValueFactory(new PropertyValueFactory<>("st_id"));
            tab_stFname.setCellValueFactory(new PropertyValueFactory<>("st_fname"));
            tab_stLname.setCellValueFactory(new PropertyValueFactory<>("st_lname"));
            tab_stAddress.setCellValueFactory(new PropertyValueFactory<>("st_address"));
            tab_stGpa.setCellValueFactory(new PropertyValueFactory<>("st_gpa"));

        } catch (SQLException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}
