-- Insertar categorías
INSERT INTO category (category_id, max_income, min_income, name)
VALUES (1, 50000, 30000, 'Junior'),
       (2, 80000, 50000, 'Mid-level'),
       (3, 120000, 80000, 'Senior'),
       (4, 150000, 120000, 'Lead'),
       (5, 200000, 150000, 'Principal');

-- Insertar programadores
INSERT INTO programmer (programmer_id, document_number, first_name, last_name, category_id)
VALUES (1, '11111111A', 'Juan', 'Perez', 1),
       (2, '22222222B', 'María', 'González', 2),
       (3, '33333333C', 'Pedro', 'Martínez', 3),
       (4, '44444444D', 'Laura', 'López', 4),
       (5, '55555555E', 'Carlos', 'Rodríguez', 5),
       (6, '66666666F', 'Ana', 'Sánchez', 1),
       (7, '77777777G', 'Miguel', 'Fernández', 2),
       (8, '88888888H', 'Sofía', 'Ruiz', 3),
       (9, '99999999I', 'Diego', 'Gómez', 4),
       (10, '10101010J', 'Elena', 'Díaz', 5);

-- Añadido al enunciado - Que haya programadores que no estén asociados a categoría
    update programmer set category_id = null where programmer_id >= 8;


-- Insertar proyectos
INSERT INTO project (project_id, client_name, expected_income, name, start_date)
VALUES (1, 'Deloitte', 259800, 'Sistema de gestión de la retribución variable y beneficios para empleados',
        '2023-07-01'),
       (2, 'Movistar', 74000, 'Mantenimiento de cableado CPD de control de streaming', '2024-01-01'),
       (3, 'Ministerio de Economía y Hacienda', 457000, 'Plan de accesibilidad integral', null),
       (4, 'Mercadona', 19800, 'Auditoría de accesibilidad tienda online', '2024-06-15'),
       (5, 'Fundación ONCE', 180000, 'Mantenimiento evolutivo plataformas Por Talento / Integra', '2024-03-01');

-- Insertar asignaciones de programadores a proyectos
INSERT INTO project_programmer (programmer_id, project_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 2),
       (4, 5),
       (4, 2),
       (5, 3),
       (6, 3),
       (6, 5),
       (7, 4),
       (8, 4),
       (9, 1),
       (9, 5),
       (10, 5);

-- Reiniciar los campos identity
alter table category
    alter column category_id restart with 100;
alter table programmer
    alter column programmer_id restart with 100;
alter table project
    alter column project_id restart with 100;