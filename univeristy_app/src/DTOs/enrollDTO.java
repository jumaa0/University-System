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
public class enrollDTO {
    private String st_id;
    private String crs_id;
    private String year_level;
    private String grade;

    public enrollDTO(String st_id, String crs_id, String year_level) {
        this.st_id = st_id;
        this.crs_id = crs_id;
        this.year_level = year_level;
    }

    public enrollDTO(String st_id, String crs_id, String year_level, String grade) {
        this.st_id = st_id;
        this.crs_id = crs_id;
        this.year_level = year_level;
        this.grade = grade;
    }

    
    
    
}
