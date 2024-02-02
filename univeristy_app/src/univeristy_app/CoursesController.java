/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univeristy_app;

import DTOs.crsDTO;
import DTOs.deptDTO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JUMAA
 */
public class CoursesController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TableColumn<crsDTO, String> tab_crsId;
    @FXML
    private TableColumn<crsDTO, String> tab_crsName;
    @FXML
    private TableColumn<crsDTO, String> tab_crsCredits;

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private ObservableList<crsDTO> crsList = FXCollections.observableArrayList();
    @FXML
    private TableView<crsDTO> tab_crs;

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
    }    
    
    
    public void loadCourses() throws SQLException {
        ArrayList<crsDTO> courses = DataAccessLayer.loadCourses();

        // Debugging print statement to check the size of the retrieved list
        //System.out.println("Number of departments retrieved: " + depts.size());

        // Clear existing items in the ObservableList
        crsList.clear();

        // Add the retrieved departments to the ObservableList
        crsList.addAll(courses);

        // Set the items in the TableView
        tab_crs.setItems(crsList);

        // Map TableView columns to deptDTO properties
        tab_crsId.setCellValueFactory(new PropertyValueFactory<>("Crs_id"));
        tab_crsName.setCellValueFactory(new PropertyValueFactory<>("Crs_name"));
        tab_crsCredits.setCellValueFactory(new PropertyValueFactory<>("Crs_grade"));
        
        
        // Debugging print statement to indicate successful loading

}
}
