CREATE SEQUENCE block_seq;

CREATE TABLE block_table(
    id INTEGER  NOT NULL DEFAULT nextval('block_seq') PRIMARY KEY ,
    block_number INTEGER NOT NULL ,
    prev_hash VARCHAR(255) ,
    hash VARCHAR(255)
)