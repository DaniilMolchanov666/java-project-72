DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
    );

CREATE TABLE url_checks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url_id BIGINT REFERENCES urls(id),
    status_code integer NOT NULL,
    title TEXT,
    h1 TEXT,
    description TEXT,
    created_at TIMESTAMP
    );