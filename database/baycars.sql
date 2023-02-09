CREATE DATABASE IF NOT EXISTS baycars;
USE baycars;

CREATE TABLE IF NOT EXISTS client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    adress TEXT,
    cpf VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS car (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    value_car FLOAT,
    year_car INT,
    path_img VARCHAR(150),
    description TEXT,
    mark VARCHAR(20),
    model VARCHAR(20),
    state VARCHAR(20),
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS older_car (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    value_car FLOAT,
    year_car INT,
    path_img VARCHAR(150),
    description TEXT,
    mark VARCHAR(20),
    model VARCHAR(20),
    mileage FLOAT,
    state VARCHAR(20),
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS sale (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_sale DATE,
    parcel INT,
    value_sale FLOAT,
    id_car INT,
    FOREIGN KEY (id_car) REFERENCES car (id) ON DELETE CASCADE,
    id_older_car INT,
    FOREIGN KEY (id_older_car) REFERENCES older_car (id) ON DELETE CASCADE,
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS rent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_rent DATE,
    date_devolution DATE,
    parcel INT,
    value_rent FLOAT,
    mileage FLOAT,
    id_older_car INT,
    FOREIGN KEY (id_older_car) REFERENCES older_car (id) ON DELETE CASCADE,
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS manager(
    name varchar(100),
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100),
    password VARCHAR(100)
    );

INSERT INTO manager (name, password, email) VALUES ('admin', 'admin', 'admin@gmail.com');