CREATE TABLE IF NOT EXISTS Cliente (
  Cedula INT NOT NULL,
  Nombre VARCHAR(70) NOT NULL,
  Apellido VARCHAR(70) NOT NULL,
   Edad INT NOT NULL,
  Telefono INT NOT NULL,
  Correo VARCHAR(100) NOT NULL,
  Clave VARCHAR(100) NOT NULL,
  PRIMARY KEY (Cedula));