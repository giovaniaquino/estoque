ALTER TABLE lotes
    ADD quantidade INT;

ALTER TABLE produto
    ADD codigo VARCHAR(20),
    DROP COLUMN quantidade;
