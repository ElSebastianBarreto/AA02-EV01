CREATE TABLE IF NOT EXISTS Cliente (
  Cedula INT NOT NULL,
  Nombre VARCHAR(70) NOT NULL,
  Apellido VARCHAR(70) NOT NULL,
  Edad INT NOT NULL,
  Correo VARCHAR(100) NOT NULL,
  Clave VARCHAR(100) NOT NULL,
  Telefono VARCHAR(13) NOT NULL,
  PRIMARY KEY (Cedula));

a
  insert into cliente VALUES (123, 'Sebastian', 'Barreto', 24, 'sebastianqgmail.com', 'clave', '3214186414');