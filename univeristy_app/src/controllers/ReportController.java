/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTOs.stDTO;
import db.DataAccessLayer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JUMAA
 */
public class ReportController implements Initializable {

    @FXML
    private Button btn_st;
    
    
    private Pane mainPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_allStudents;
    @FXML
    private Button btn_depts;
    @FXML
    private Button btn_courses;
    @FXML
    private TextField txt_nStudents;
    @FXML
    private TextField txt_avgGpa;
    @FXML
    private TextField txt_nDepts;
    @FXML
    private TextField txt_nCrs;
    @FXML
    private PieChart pie_standyear;
    @FXML
    private TextField txt_y1;
    @FXML
    private TextField txt_y2;
    @FXML
    private TextField txt_y3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_st.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/univeristy_app/Students.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
    
    });
        btn_allStudents.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/univeristy_app/AllStudents.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
    
    });
        
    
        
        
    btn_depts.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/univeristy_app/Departments.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
    
    });
    btn_courses.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/univeristy_app/Courses.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
    
    });
    
     try {
        // Retrieve the student count when the controller is initialized
        int studentCount = DataAccessLayer.getStNumber();
        System.out.println("Initial student count: " + studentCount);

        // Update the TextField with the retrieved student count
        txt_nStudents.setText(String.valueOf(studentCount));
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }

     try {
        // Retrieve the student count when the controller is initialized
        float avgGpa = DataAccessLayer.getAvgGpa();
        String formattedAvg = String.format("%.2f", avgGpa);
        //System.out.println("Initial student count: " + avgGpa);

        // Update the TextField with the retrieved student count
        txt_avgGpa.setText(formattedAvg);
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     
     try {
        // Retrieve the student count when the controller is initialized
        int deptCount = DataAccessLayer.getdeptNumber();
        //System.out.println("Initial student count: " + deptCount);

        // Update the TextField with the retrieved student count
        txt_nDepts.setText(String.valueOf(deptCount));
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     try {
        // Retrieve the student count when the controller is initialized
        int crsCount = DataAccessLayer.getcrsNumber();
        //System.out.println("Initial student count: " + deptCount);

        // Update the TextField with the retrieved student count
        txt_nCrs.setText(String.valueOf(crsCount));
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
    try {
        // Retrieve the student count when the controller is initialized
        String y1 = DataAccessLayer.getStName(1);
        //System.out.println("Initial student count: " + deptCount);

        // Update the TextField with the retrieved student count
        txt_y1.setText(y1);
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
     try {
        // Retrieve the student count when the controller is initialized
        String y2 = DataAccessLayer.getStName(2);
        //System.out.println("Initial student count: " + deptCount);

        // Update the TextField with the retrieved student count
        txt_y2.setText(y2);
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
      try {
        // Retrieve the student count when the controller is initialized
        String y3 = DataAccessLayer.getStName(3);
        //System.out.println("Initial student count: " + deptCount);

        // Update the TextField with the retrieved student count
        txt_y3.setText(y3);
    } catch (SQLException ex) {
        Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try {
            // Retrieve the data for pie chart when the controller is initialized
            // You need to adapt this part based on your data model
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Year 1", DataAccessLayer.getYearCount(1)),
                    new PieChart.Data("Year 2", DataAccessLayer.getYearCount(2)),
                    new PieChart.Data("Year 3", DataAccessLayer.getYearCount(3))
                    // Add more as needed
            );

            // Set the data in the PieChart
            pie_standyear.setData(pieChartData);

        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
