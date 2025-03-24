CREATE TABLE IF NOT EXISTS books (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_date DATETIME(6) NOT NULL,
    genre VARCHAR(100),
    created_at DATETIME(6),
    updated_at DATETIME(6)
);