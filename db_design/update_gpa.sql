CREATE OR REPLACE PROCEDURE update_gpa(v_st_id NUMBER)
IS
    --CURSOR students_cursor
    --IS
    --SELECT * FROM STUDENTS;
    
    V_AVG_GRADES NUMBER;
    V_NEW_GPA NUMBER(3,2);
BEGIN

    SELECT AVG(grade) INTO V_AVG_GRADES
    FROM grades
    WHERE st_id = v_st_id;
    
    V_NEW_GPA:= calculate_gpa(V_AVG_GRADES);
    
    UPDATE students
    SET gpa = V_NEW_GPA
    WHERE st_id=v_st_id;
    
    DBMS_OUTPUT.PUT_LINE('GPA Updated Successfully for Student ID ');

END;
SHOW ERRORS

SET SERVEROUTPUT ON
DECLARE
BEGIN

    UPDATE_GPA(102);

END;