/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univeristy_app;

import DTOs.deptDTO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JUMAA
 */
public class DepartmentsController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TableView<deptDTO> tab_depts;
    @FXML
    private TableColumn<deptDTO, String> tab_deptId;
    @FXML
    private TableColumn<deptDTO, String> tab_deptName;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private ObservableList<deptDTO> deptsList = FXCollections.observableArrayList();

        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        loadDepts();
        
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
    public void loadDepts() {
    try {
        ArrayList<deptDTO> depts = DataAccessLayer.loadDepts();

        // Debugging print statement to check the size of the retrieved list
        System.out.println("Number of departments retrieved: " + depts.size());

        // Clear existing items in the ObservableList
        deptsList.clear();

        // Add the retrieved departments to the ObservableList
        deptsList.addAll(depts);

        // Set the items in the TableView
        tab_depts.setItems(deptsList);

        // Map TableView columns to deptDTO properties
        tab_deptId.setCellValueFactory(new PropertyValueFactory<>("Dept_id"));
        tab_deptName.setCellValueFactory(new PropertyValueFactory<>("Dept_name"));

        // Debugging print statement to indicate successful loading
        System.out.println("Departments loaded successfully.");
    } catch (SQLException ex) {
        // Print the exception details for debugging
        ex.printStackTrace();
        Logger.getLogger(DepartmentsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}