DROP TABLE IF EXISTS user_action;

CREATE TABLE user_action (
  id            BIGINT       NOT NULL,
  name          VARCHAR(255) NOT NULL,
  createAt      TIMESTAMP    NOT NULL,
  user_action   CHAR(30)     NOT NULL,
  PRIMARY KEY (id,createAt)
);