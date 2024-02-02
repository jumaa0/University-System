CREATE OR REPLACE FUNCTION calculate_gpa(grade IN NUMBER)
RETURN NUMBER
IS
  BEGIN
    CASE
      WHEN grade >= 97 THEN RETURN 4.0;
      WHEN grade >= 93 THEN RETURN 4.0;
      WHEN grade >= 90 THEN RETURN 3.7;
      WHEN grade >= 87 THEN RETURN 3.3;
      WHEN grade >= 83 THEN RETURN 3.0;
      WHEN grade >= 80 THEN RETURN 2.7;
      WHEN grade >= 77 THEN RETURN 2.3;
      WHEN grade >= 73 THEN RETURN 2.0;
      WHEN grade >= 70 THEN RETURN 1.7;
      WHEN grade >= 67 THEN RETURN 1.3;
      WHEN grade >= 65 THEN RETURN 1.0;
      ELSE RETURN 0.0;
    END CASE;
  END calculate_gpa;