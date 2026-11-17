CREATE DATABASE IF NOT EXISTS proyectos_inversion_publica
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE proyectos_inversion_publica;

CREATE TABLE IF NOT EXISTS proyectos (
    id INT NOT NULL AUTO_INCREMENT,
    nombre_proyecto VARCHAR(60) NOT NULL,
    descripcion_tecnica TEXT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_culminacion DATE NOT NULL,
    asesor_tecnico VARCHAR(150) NOT NULL,
    presupuesto_asignado DECIMAL(15, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE USER IF NOT EXISTS 'proyectos_app'@'localhost'
    IDENTIFIED BY 'DemoProyectos2026!';

ALTER USER 'proyectos_app'@'localhost'
    IDENTIFIED BY 'DemoProyectos2026!';

REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'proyectos_app'@'localhost';

GRANT SELECT, INSERT ON proyectos_inversion_publica.proyectos
    TO 'proyectos_app'@'localhost';
