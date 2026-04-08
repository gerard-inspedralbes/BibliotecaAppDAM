-- Crear base de dades
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- ------------------------
-- TAULA USUARIS
-- ------------------------
CREATE TABLE usuaris (
                         id INT PRIMARY KEY,
                         tipus ENUM('ALUMNE', 'PROFESSOR') NOT NULL,
                         nom VARCHAR(100) NOT NULL
);

-- ------------------------
-- TAULA LLIBRES
-- ------------------------
CREATE TABLE llibres (
                         id INT PRIMARY KEY,
                         titol VARCHAR(255) NOT NULL,
                         autor VARCHAR(255) NOT NULL,
                         disponible BOOLEAN NOT NULL
);

-- ------------------------
-- TAULA PRESTECS
-- ------------------------
CREATE TABLE prestecs (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          id_usuari INT NOT NULL,
                          id_llibre INT NOT NULL,

                          FOREIGN KEY (id_usuari) REFERENCES usuaris(id)
                              ON DELETE CASCADE,

                          FOREIGN KEY (id_llibre) REFERENCES llibres(id)
                              ON DELETE CASCADE,

                          UNIQUE (id_llibre) -- evita prestar el mateix llibre dues vegades
);

INSERT INTO usuaris (id, tipus, nom) VALUES
                                         (1, 'ALUMNE', 'Anna'),
                                         (2, 'ALUMNE', 'Marc'),
                                         (3, 'ALUMNE', 'Laia'),
                                         (4, 'ALUMNE', 'Joan'),
                                         (5, 'ALUMNE', 'Clara'),
                                         (6, 'PROFESSOR', 'Carlos'),
                                         (7, 'PROFESSOR', 'Angela'),
                                         (8, 'PROFESSOR', 'Gerard');

INSERT INTO llibres (id, titol, autor, disponible) VALUES
                                                       (1, '1984', 'George Orwell', true),
                                                       (2, 'El Quijote', 'Miguel de Cervantes', false),
                                                       (3, 'Clean Code', 'Robert C. Martin', true),
                                                       (4, 'Harry Potter', 'J.K. Rowling', true),
                                                       (5, 'El Hobbit', 'J.R.R. Tolkien', false);