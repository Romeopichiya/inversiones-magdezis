DROP DATABASE IF EXISTS inversiones_magdezis_in4bv;
CREATE DATABASE inversiones_magdezis_in4bv;
USE inversiones_magdezis_in4bv;

-- Tabla roles
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Roles
INSERT INTO roles (nombre) VALUES ('ADMIN'), ('USER');

-- Tabla usuarios 
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Tabla inversiones
CREATE TABLE inversiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(150) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha DATE NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Usuario con ID 1 
INSERT INTO usuarios (nombre, apellido, correo, contrasena, rol_id) 
VALUES ('Alfred', 'Pichiyá', 'alfred@magdezis.com', '12345', 1);

INSERT INTO inversiones (descripcion, monto, fecha, usuario_id) 
VALUES ('Bienes Raíces - Lote A', 15000.00, '2026-10-09', 1);

INSERT INTO inversiones (descripcion, monto, fecha, usuario_id) VALUES 
('Fondo Mutual de Crecimiento', 8500.50, '2026-10-10', 1),
('Acciones de Tecnología - Paquete B', 23000.00, '2026-10-11', 1),
('Bono Corporativo a Plazo Fijo', 5000.00, '2026-10-12', 1);
