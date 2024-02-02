CREATE OR REPLACE PROCEDURE ITI.update_all_gpa
IS
    CURSOR students_cursor IS
        SELECT * FROM STUDENTS;
    
    V_AVG_GRADES NUMBER;
    V_NEW_GPA NUMBER(3,2);
BEGIN
    FOR student_rec IN students_cursor LOOP
        SELECT AVG(grade) INTO V_AVG_GRADES
        FROM grades
        WHERE st_id = student_rec.st_id
        AND grade IS NOT NULL;
    
        V_NEW_GPA := calculate_gpa(V_AVG_GRADES);
    
        UPDATE students
        SET gpa = V_NEW_GPA
        WHERE st_id = student_rec.st_id;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('GPA Updated Successfully for all Students');
END;
/
