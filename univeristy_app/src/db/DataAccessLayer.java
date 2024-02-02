package db;


import DTOs.crsDTO;
import DTOs.deptDTO;
import DTOs.stDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JUMAA
 */
public class DataAccessLayer {
     public static String url = "jdbc:oracle:thin:@localhost:1521:XE";

    public static void connect() throws SQLException {
        // Register the OracleDriver
        DriverManager.registerDriver(new OracleDriver());

        // Connection
        try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
            // Perform database operations here
            System.out.println("Connected to Oracle database.");
        }
    }
    
    public static stDTO getStudent(String st_id) throws SQLException {
    DriverManager.registerDriver(new OracleDriver());

    try (Connection con = DriverManager.getConnection(url, "iti", "123");
         PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM students WHERE st_id = ?")) {
        preparedStatement.setString(1, st_id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            stDTO student = new stDTO();

            if (resultSet.next()) {
                student.setSt_id(resultSet.getString("st_id"));
                student.setSt_fname(resultSet.getString("st_fname"));
                student.setSt_lname(resultSet.getString("st_lname"));
                student.setSt_level(resultSet.getString("year_level"));
                student.setSt_gpa(resultSet.getString("gpa"));
            }

            return student;
        }
    }
    }
    
    public static ArrayList<crsDTO> getCourses(String st_id) throws SQLException {
        ArrayList<crsDTO> courses = new ArrayList<>();
        System.out.println("getCourses method called for Student ID: " + st_id);

        try (Connection con = DriverManager.getConnection(url, "iti", "123");
             PreparedStatement preparedStatement = con.prepareStatement("SELECT g.crs_id, c.crs_name, g.grade FROM grades g join courses c on g.crs_id = c.crs_id where g.st_id = ?")){
             preparedStatement.setString(1, st_id);
             try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String crsid = resultSet.getString("crs_id");
                String crsname = resultSet.getString("crs_name");
                String grade = resultSet.getString("grade");
                

                courses.add(new crsDTO(crsid, crsname, grade));
                System.out.println("Student ID in getCourses: " + st_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
    }
    
    public static void enrollCourse(String s_id, String crs_id, String year_level, String grade) {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {

        // First, execute the DELETE statement
        try (PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM grades WHERE st_id = ? AND crs_id = ?")) {
            int idValue = Integer.parseInt(s_id);
            int crsValue = Integer.parseInt(crs_id);

            deleteStatement.setInt(1, idValue);
            deleteStatement.setInt(2, crsValue);

            // Execute the DELETE statement
            deleteStatement.executeUpdate();
        }

        // Then, execute the INSERT statement
        try (PreparedStatement insertStatement = con.prepareStatement("INSERT INTO grades(st_id, crs_id, crs_year, grade) VALUES (?, ?, ?, ?)")) {
            int idValue = Integer.parseInt(s_id);
            int crsValue = Integer.parseInt(crs_id);
            int yearValue = Integer.parseInt(year_level);

            // Check if grade is not empty or null before parsing
            Integer gradeValue = null;
            if (grade != null && !grade.isEmpty()) {
                gradeValue = Integer.parseInt(grade);
            }

            insertStatement.setInt(1, idValue);
            insertStatement.setInt(2, crsValue);
            insertStatement.setInt(3, yearValue);

            // Set the parameter to null if gradeValue is null
            if (gradeValue != null) {
                insertStatement.setInt(4, gradeValue);
            } else {
                insertStatement.setNull(4, java.sql.Types.INTEGER);
            }

            // Execute the INSERT statement
            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Enrollment successful!");
            } else {
                System.out.println("Enrollment failed. Please check your input.");
            }
        }

    } catch (NumberFormatException e) {
        System.out.println("Error: Cannot parse the grade to an integer. Please provide a valid numeric value or leave it empty.");
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception according to your application's needs
    }
}


    public static void addStudent(stDTO student) throws SQLException{
        try (Connection con = DriverManager.getConnection(url, "iti", "123");
             PreparedStatement preparedStatement = con.prepareStatement("insert into students(st_id, st_fname, st_lname, address, year_level) values(?,?,?,?,1)")){
            
            int idValue = Integer.parseInt(student.getSt_id());

            
             preparedStatement.setInt(1,idValue );
             preparedStatement.setString(2, student.getSt_fname());
             preparedStatement.setString(3, student.getSt_lname());
             preparedStatement.setString(4, student.getSt_address());
             int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("failed. Please check your input.");
        }
        }
    }
    
    public static ArrayList<stDTO> loadStudents() throws SQLException{
        ArrayList<stDTO> students = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, "iti", "123");
             PreparedStatement preparedStatement = con.prepareStatement("SELECT st_id, st_fname, st_lname, address, gpa from students")){
             try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String st_id = resultSet.getString("st_id");
                String st_fname = resultSet.getString("st_fname");
                String st_lname = resultSet.getString("st_lname");
                String address = resultSet.getString("address");
                String gpa = resultSet.getString("gpa");
                

                students.add(new stDTO(st_id, st_fname, st_lname, address, gpa));
                //System.out.println("Student ID in getCourses: " + st_id);
            }

        

        return students;
    }}
    }
        
    public static void updateGpa() throws SQLException{
        Connection con = DriverManager.getConnection(url, "iti", "123");
             PreparedStatement stmt = con.prepareStatement(
                "BEGIN\n" +
                        "    update_all_gpa();\n" +
                        "END;");
             stmt.execute();
    }
    
    public static void rmvCourse(String st_id, String crs_id) throws SQLException{
        try (Connection con = DriverManager.getConnection(url, "iti", "123")) {

        // First, execute the DELETE statement
        try (PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM grades WHERE st_id = ? AND crs_id = ?")) {
            int idValue = Integer.parseInt(st_id);
            int crsValue = Integer.parseInt(crs_id);

            deleteStatement.setInt(1, idValue);
            deleteStatement.setInt(2, crsValue);

            // Execute the DELETE statement
            deleteStatement.executeUpdate();
        }
    }
    }
    public static ArrayList<deptDTO> loadDepts() throws SQLException{
         
        ArrayList<deptDTO> depts = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, "iti", "123");
             PreparedStatement preparedStatement = con.prepareStatement("SELECT dept_id, dept_name from departments")){
             try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String dept_id = resultSet.getString("dept_id");
                String dept_name = resultSet.getString("dept_name");


                depts.add(new deptDTO(dept_id, dept_name));
            }

        

        
        return depts;
             }
        }
        
    }
    
    public static ArrayList<crsDTO> loadCourses() throws SQLException {
    ArrayList<crsDTO> courses = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT * FROM courses";

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String crs_id = resultSet.getString("crs_id");
                String crs_name = resultSet.getString("crs_name");
                String crs_credits = resultSet.getString("credits");

                // Debugging statement to print each course retrieved from the database
                System.out.println("Retrieved Course: " + crs_id + ", " + crs_name + ", " + crs_credits);

                courses.add(new crsDTO(crs_id, crs_name, crs_credits));
               
            }
             return courses;
        }
    } catch (SQLException ex) {
        // Print or log the exception for debugging purposes
        ex.printStackTrace();
        throw ex; // Re-throw the exception to be handled by the calling code
    }

    
}
    public static int getStNumber() throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT count(*) as c FROM students";

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Move the cursor to the first row
            if (resultSet.next()) {
                int stnumber = resultSet.getInt("c");
                return stnumber;
            } else {
                // Handle the case where no rows are returned
                return 0; // or throw an exception, depending on your requirements
            }
        }
    }
}

      public static float getAvgGpa() throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT avg(gpa) as avg FROM students";

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Move the cursor to the first row
            if (resultSet.next()) {
                float stnumber = resultSet.getFloat("avg");
                return stnumber;
            } else {
                // Handle the case where no rows are returned
                return 0; // or throw an exception, depending on your requirements
            }
        }
    }
}  

      public static int getdeptNumber() throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT count(*) as c FROM departments";

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Move the cursor to the first row
            if (resultSet.next()) {
                int deptnumber = resultSet.getInt("c");
                return deptnumber;
            } else {
                // Handle the case where no rows are returned
                return 0; // or throw an exception, depending on your requirements
            }
        }
    }
} 
      
      public static int getcrsNumber() throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT count(*) as c FROM courses";

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Move the cursor to the first row
            if (resultSet.next()) {
                int crsnumber = resultSet.getInt("c");
                return crsnumber;
            } else {
                // Handle the case where no rows are returned
                return 0; // or throw an exception, depending on your requirements
            }
        }
    }
} 
     
      public static int getYearCount(int year) throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "SELECT count(*) as c FROM students where year_level = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, year);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("c");
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

      public static String getStName(int year) throws SQLException {
    try (Connection con = DriverManager.getConnection(url, "iti", "123")) {
        String query = "select getBestGpa(?) as st_fname \n" +
                "from dual";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, year);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("st_fname");
                    } 
                }
            }
        }
         return null;
    }
}
