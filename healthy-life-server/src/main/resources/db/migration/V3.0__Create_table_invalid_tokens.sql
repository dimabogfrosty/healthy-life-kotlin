CREATE TABLE invalid_tokens
(
  id         serial primary key,
  token      VARCHAR(255) NOT NULL,
  expiration TIMESTAMP  NOT NULL
);