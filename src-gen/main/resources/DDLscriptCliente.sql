
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
						numero VARCHAR(100)
,						nombre VARCHAR(100)
,						rfc VARCHAR(100)
,						direccion VARCHAR(100)
);

