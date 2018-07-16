
DROP TABLE IF EXISTS examen;

CREATE TABLE examen ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
						nombre VARCHAR(100)
,						calificacion VARCHAR(100)
,						empleado_id VARCHAR(100)
);

