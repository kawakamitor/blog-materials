DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id    BIGINT       NOT NULL AUTO_INCREMENT,
  name  VARCHAR(255) NOT NULL,
  age   INT          NOT NULL,
  PRIMARY KEY (id)
);

BEGIN;

INSERT INTO user (name, age) values ('Noah', 23);
INSERT INTO user (name, age) values ('Emma', 30);
INSERT INTO user (name, age) values ('Liam', 15);
INSERT INTO user (name, age) values ('Olivia', 3);
INSERT INTO user (name, age) values ('Mason', 50);

COMMIT;