/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author JUMAA
 */
public class crsDTO {
    private String crs_id;
    private String crs_name;
    private String crs_grade;

    public crsDTO(String crs_id, String crs_name, String crs_grade) {
        this.crs_id = crs_id;
        this.crs_name = crs_name;
        this.crs_grade = crs_grade;
    }

    

    public String getCrs_id() {
        return crs_id;
    }

    public String getCrs_name() {
        return crs_name;
    }

    public String getCrs_grade() {
        return crs_grade;
    }

    public void setCrs_id(String crs_id) {
        this.crs_id = crs_id;
    }

    public void setCrs_name(String crs_name) {
        this.crs_name = crs_name;
    }

    public void setCrs_grade(String crs_grade) {
        this.crs_grade = crs_grade;
    }
    
    
    
}
