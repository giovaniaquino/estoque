ALTER TABLE lotes ADD quantidade INT;

ALTER TABLE produto ADD codigo VARCHAR(20) UNIQUE NOT NULL;
ALTER TABLE produto DROP COLUMN quantidade;