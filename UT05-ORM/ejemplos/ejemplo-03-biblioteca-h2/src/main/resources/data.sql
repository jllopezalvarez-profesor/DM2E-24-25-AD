insert into books(title)
values ('El juego de Ender'),
       ('La mujer del viajero en el tiempo'),
       ('La voz de los muertos'),
       ('Ender el xenocida'),
       ('Hijos de la mente');

insert into categories(name)
values ('Ciencia ficción'),
       ('Romántica');

insert into books_categories(book_id, category_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (2, 2);

INSERT INTO users (id, dni, first_name, last_name, email)
VALUES ('123e4567-e89b-12d3-a456-426614174001', '12345678A', 'Juan', 'Gómez', 'juan.gomez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174002', '87654321B', 'Ana', 'Martínez', 'ana.martinez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174003', '23456789C', 'Pedro', 'López', 'pedro.lopez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174004', '34567890D', 'María', 'Fernández', 'maria.fernandez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174005', '45678901E', 'Luis', 'Sánchez', 'luis.sanchez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174006', '56789012F', 'Laura', 'Pérez', 'laura.perez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174007', '67890123G', 'David', 'Ramírez', 'david.ramirez@example.com'),
       ('123e4567-e89b-12d3-a456-426614174008', '78901234H', 'Sara', 'García', 'sara.garcia@example.com'),
       ('123e4567-e89b-12d3-a456-426614174009', '89012345I', 'Carlos', 'Torres', 'carlos.torres@example.com'),
       ('123e4567-e89b-12d3-a456-426614174010', '90123456J', 'Elena', 'Ruiz', 'elena.ruiz@example.com');
