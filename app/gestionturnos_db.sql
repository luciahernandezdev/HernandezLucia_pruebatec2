-- Creación de Base de datos
CREATE DATABASE gestionturnos_db;
USE gestionturnos_db;

-- Crear tabla de ciudadano
CREATE TABLE ciudadano (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
);

-- Crear la tabla turnos para la relación uno a muchos
CREATE TABLE turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado ENUM('ESPERA', 'ATENDIDO') NOT NULL,
    ciudadano_id BIGINT NOT NULL,
    FOREIGN KEY (ciudadano_id) REFERENCES ciudadano(id) ON DELETE CASCADE
);
--Crear la tabla usuarios para añadir usuarios
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(50) NOT NULL
);

-- Insertar registros en la tabla persona
INSERT INTO ciudadano (nombre, apellido) VALUES
('Pedro', 'Alvarez'),
('Lucia', 'Fernandez'),
('Mateo', 'Garcia'),
('Eva', 'Hernandez'),
('Miguel', 'Martinez'),
('Sofia', 'Lopez'),
('Pablo', 'Gonzalez'),
('Carla', 'Rodriguez'),
('Juan', 'Perez'),
('Maria', 'Sanchez');

-- Insertar registros en la tabla turno
INSERT INTO turno (fecha, descripcion, estado, ciudadano_id) VALUES
('2025-03-05', 'Reunión con el departamento de ventas', 'ESPERA', 1),
('2025-04-15', 'Revisión del plan de marketing', 'ATENDIDO', 2),
('2025-06-18', 'Presentación de nuevos productos', 'ESPERA', 3),
('2025-07-22', 'Evaluación de desempeño anual', 'ATENDIDO', 4),
('2025-08-30', 'Reunión con inversionistas', 'ESPERA', 5),
('2025-09-12', 'Revisión de contratos de proveedores', 'ATENDIDO', 6),
('2025-10-05', 'Capacitación de equipo', 'ESPERA', 7),
('2025-11-19', 'Auditoría interna', 'ESPERA', 8),
('2025-12-03', 'Consulta con el departamento legal', 'ATENDIDO', 9),
('2025-12-15', 'Revisión de estrategias de crecimiento', 'ESPERA', 10),
('2025-12-20', 'Evaluación de proyectos en curso', 'ATENDIDO', 1),
('2025-12-25', 'Planificación de recursos', 'ESPERA', 2),
('2026-01-05', 'Reunión de cierre de trimestre', 'ATENDIDO', 3),
('2026-01-15', 'Análisis de riesgos', 'ESPERA', 4),
('2026-01-25', 'Desarrollo de nuevas estrategias', 'ATENDIDO', 5),
('2026-02-10', 'Revisión de políticas de la empresa', 'ESPERA', 6),
('2026-02-20', 'Evaluación de oportunidades de mercado', 'ATENDIDO', 7);
