CREATE TABLE IF NOT EXISTS stations(id BIGINT NOT NULL AUTO_INCREMENT,
            code VARCHAR(255) UNIQUE,
            cost INT,
            name VARCHAR(255) UNIQUE);