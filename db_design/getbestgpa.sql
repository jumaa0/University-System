CREATE OR REPLACE FUNCTION ITI.getBestGpa(v_year_level NUMBER)
RETURN VARCHAR2
IS
    v_st_fname VARCHAR2(100); -- Assuming st_fname is a VARCHAR2 column
BEGIN
    SELECT st_fname as st_fname
    INTO v_st_fname
    FROM (
        SELECT st_fname
        FROM students
        WHERE year_level = v_year_level
        ORDER BY gpa DESC
    )
    WHERE ROWNUM = 1;

    RETURN v_st_fname;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Handle the case where no rows are returned
        RETURN NULL;
END;
/
