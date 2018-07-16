
DROP TABLE IF EXISTS pedido;

CREATE TABLE pedido ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
						almacen VARCHAR(100)
,						fecha VARCHAR(100)
,						estatus VARCHAR(100)
,						cliente_id VARCHAR(100)
,,						numero VARCHAR(100)
);

