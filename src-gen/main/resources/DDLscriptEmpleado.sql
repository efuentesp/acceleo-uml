
DROP TABLE IF EXISTS empleado;

CREATE TABLE empleado ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
						nombre VARCHAR(100)
,						apellido VARCHAR(100)
,						email VARCHAR(100)
,						telefono VARCHAR(100)
,						departamento_id VARCHAR(100)
,						salario VARCHAR(100)
);

