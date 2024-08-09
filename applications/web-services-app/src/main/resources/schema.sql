-- Primero, eliminar la tabla que tiene la clave foránea
DROP TABLE IF EXISTS [posts];

-- Luego, eliminar la tabla referenciada
DROP TABLE IF EXISTS [users];

-- Ahora crear la tabla referenciada
CREATE TABLE [users]
(
    [id]         INT IDENTITY (1,1) PRIMARY KEY,
    [name]       NVARCHAR(50) NOT NULL,
    [last_name]  NVARCHAR(50) NOT NULL,
    [birth_date] DATE         NOT NULL
);

-- Insertar datos en la tabla referenciada
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Camilo', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Alejandro', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Gabriel Jaime', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Luz', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Juan', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Pedro', 'Morales sanchez', '2024-08-08');
INSERT INTO [users] (name, last_name, birth_date)
VALUES ('Pablo', 'Morales sanchez', '2024-08-08');

-- Ahora crear la tabla que tiene la clave foránea
CREATE TABLE [posts]
(
    [id]          INT IDENTITY (1,1) PRIMARY KEY,
    [description] NVARCHAR(MAX) NOT NULL,
    [user_id]     INT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES [users] (id)
);

-- Insertar datos en la tabla con la clave foránea
INSERT INTO [posts] (description, user_id)
VALUES ('Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido', 1);
INSERT INTO [posts] (description, user_id)
VALUES ('Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido', 2);
INSERT INTO [posts] (description, user_id)
VALUES ('Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido', 3);
