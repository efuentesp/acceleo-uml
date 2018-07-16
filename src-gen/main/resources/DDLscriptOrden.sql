
DROP TABLE IF EXISTS orden;

CREATE TABLE orden ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
						fecha VARCHAR(100)
,						cliente_id VARCHAR(100)
,						numero VARCHAR(100)
,						estatus VARCHAR(100)
,						almacen VARCHAR(100)
);

