DROP DATABASE IF EXISTS scrap_demo;
CREATE DATABASE scrap_demo;
USE scrap_demo;

-- scrap, user 테이블 삭제
DROP TABLE IF EXISTS scrap;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) UNIQUE NOT NULL,
    password_hashed VARCHAR(64) NOT NULL,
    password_salt VARCHAR(32) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role ENUM('user', 'admin') NOT NULL DEFAULT 'user',
    signup_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE scrap (
    scrap_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(128),
    url VARCHAR(255) NOT NULL,
    scrap_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);