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
public class stDTO {
    private String st_id;
    private String st_fname;
    private String st_lname;
    private String st_bod;
    private String st_gpa;
    private String st_address;
    private String st_level;

    
    public stDTO() {
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public void setSt_fname(String st_fname) {
        this.st_fname = st_fname;
    }

    public void setSt_lname(String st_lname) {
        this.st_lname = st_lname;
    }

    public void setSt_bod(String st_bod) {
        this.st_bod = st_bod;
    }

    public void setSt_gpa(String st_gpa) {
        this.st_gpa = st_gpa;
    }

    public void setSt_address(String st_address) {
        this.st_address = st_address;
    }

    public void setSt_level(String st_level) {
        this.st_level = st_level;
    }

    public String getSt_id() {
        return st_id;
    }

    public String getSt_fname() {
        return st_fname;
    }

    public String getSt_lname() {
        return st_lname;
    }

    public String getSt_bod() {
        return st_bod;
    }

    public String getSt_gpa() {
        return st_gpa;
    }

    public String getSt_address() {
        return st_address;
    }

    public String getSt_level() {
        return st_level;
    }

    public stDTO(String st_id, String st_fname, String st_lname, String st_address) {
        this.st_id = st_id;
        this.st_fname = st_fname;
        this.st_lname = st_lname;
        this.st_address = st_address;
    }

    public stDTO(String st_id, String st_fname, String st_lname, String st_gpa, String st_address) {
        this.st_id = st_id;
        this.st_fname = st_fname;
        this.st_lname = st_lname;
        this.st_gpa = st_gpa;
        this.st_address = st_address;
    }
    
    
}
