DROP TRIGGER ITI.GRADES_TRG;

CREATE OR REPLACE TRIGGER ITI.GRADES_TRG
BEFORE INSERT
ON ITI.GRADES REFERENCING NEW AS New OLD AS Old
FOR EACH ROW
BEGIN
-- For Toad:  Highlight column G_ID
  :new.G_ID := GRADES2_SEQ.nextval;
END GRADES_TRG;
/