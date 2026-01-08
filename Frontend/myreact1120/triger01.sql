CREATE OR REPLACE TRIGGER trg_log_friend_request_status
AFTER UPDATE OF status ON friend_request
FOR EACH ROW
BEGIN
  IF (:OLD.status IS NULL AND :NEW.status IS NOT NULL)
     OR (:OLD.status IS NOT NULL AND :NEW.status IS NULL)
     OR (:OLD.status <> :NEW.status) THEN

    INSERT INTO friend_request_log (
      log_id,
      friend_request_id,
      status,
      action_time
    ) VALUES (
      friend_request_log_seq.NEXTVAL,
      :NEW.id,
      :NEW.status,
      SYSTIMESTAMP
    );

  END IF;
END;
/