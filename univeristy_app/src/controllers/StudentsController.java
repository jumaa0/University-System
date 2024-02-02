/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTOs.crsDTO;
import DTOs.stDTO;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JUMAA
 */
public class StudentsController implements Initializable {

    @FXML
    private TextField txt_id;
    @FXML
    private Button btn_search;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_level;
    @FXML
    private TextField txt_gpa;
    @FXML
    private TextField txt_crsid;
    @FXML
    private TextField txt_grade;
    @FXML
    private Button btn_enroll;
    @FXML
    private TableView<crsDTO> tab_crs;
    @FXML
    private TableColumn<crsDTO, String> tab_crsId;
    @FXML
    private TableColumn<crsDTO, String> tab_crsName;
    @FXML
    private TableColumn<crsDTO, String> tab_crsGrade;
    
    private ObservableList<crsDTO> coursesList = FXCollections.observableArrayList();
    @FXML
    private Button btn_back;
    
    private Pane mainPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_remove;

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
        
        btn_search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // Get the student ID from the txt_id TextField
            String studentId = txt_id.getText();

            try {
                // Call the DataAccessLayer.getStudent() method
                stDTO student = DataAccessLayer.getStudent(studentId);

                // Print debug information
                System.out.println("Retrieved student: " + student);

                // Update the other text fields with the retrieved data
                txt_name.setText(student.getSt_fname());
                txt_level.setText(student.getSt_level());
                txt_gpa.setText(student.getSt_gpa());
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
                // Print more detailed error information
                ex.printStackTrace();
            }
            loadCoursesFromServer();
        }
    });
        
        btn_enroll.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
            DataAccessLayer.enrollCourse(txt_id.getText(),txt_crsid.getText(), txt_level.getText(), txt_grade.getText());
            loadCoursesFromServer();
        }
        
        });
        
         btn_remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the selected item from the TableView
                crsDTO selectedCourse = tab_crs.getSelectionModel().getSelectedItem();

                // Check if the selected item is not null
                if (selectedCourse != null) {
                    // Check if txt_grade has no value
                    if (selectedCourse.getCrs_grade() == null || selectedCourse.getCrs_grade().trim().isEmpty()) {
                        try {
                            // Call the DataAccessLayer.rmvCourse() method
                            DataAccessLayer.rmvCourse(txt_id.getText(), selectedCourse.getCrs_id());
                            System.out.println("Grade is either null or empty. Removing course...");
                            // Reload courses from server after removing
                            loadCoursesFromServer();
                        } catch (SQLException ex) {
                            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        // txt_grade has a value, do nothing or show a message as needed
                        System.out.println("Grade is not empty, no removal done.");
                    }
                } else {
                    // No item selected, show a message or handle as needed
                    System.out.println("No course selected for removal.");
                }
            }
        });
         
         
    }
              
            public void loadCoursesFromServer() {
                    
                String studentId = txt_id.getText();
        try {
            System.out.println("Loading courses...");
            
            // Retrieve the list of courses for the given st_id
            ArrayList<crsDTO> courses = DataAccessLayer.getCourses(studentId);

            // Clear existing items in the ObservableList
            coursesList.clear();

            // Add the retrieved courses to the ObservableList
            coursesList.addAll(courses);

            // Set the items in the TableView
            tab_crs.setItems(coursesList);

            // Map TableView columns to crsDTO properties
            tab_crsId.setCellValueFactory(new PropertyValueFactory<>("crs_id"));
            tab_crsName.setCellValueFactory(new PropertyValueFactory<>("crs_name"));
            tab_crsGrade.setCellValueFactory(new PropertyValueFactory<>("crs_grade"));

        } catch (SQLException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}
