INSERT INTO REGIONES (id, nombre) VALUES (1,'Sudamerica');
INSERT INTO REGIONES (id, nombre) VALUES (2,'Centroamerica');
INSERT INTO REGIONES (id, nombre) VALUES (3,'Norteamerica');
INSERT INTO REGIONES (id, nombre) VALUES (4,'Europa');
INSERT INTO REGIONES (id, nombre) VALUES (5,'Asia');
INSERT INTO REGIONES (id, nombre) VALUES (6,'Africa');
INSERT INTO REGIONES (id, nombre) VALUES (7,'Oceania');


INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(1, 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(2, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(3, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(4, 'Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(5, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(6, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(7, 'Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(8, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(9, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(10, 'James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(11, 'Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(12, 'Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(13, 'John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(14, 'Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(15, 'Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(16, 'Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(17, 'Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(18, 'Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(19, 'Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '',1);  
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(20, 'Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', '',1); 
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(21, 'Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(22, 'John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(23, 'Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(24, 'John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '',1);
INSERT INTO clientes (id, nombre, apellido, email, createat, foto,region_id) VALUES(25, 'Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '',1);
 
/* Populate tabla productos */
INSERT INTO productos (id, nombre, precio, create_at) VALUES(1, 'Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(2, 'Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(3, 'Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(4, 'Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(5, 'Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(6, 'Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(7, 'Mica Comoda 5 Cajones', 299990, NOW());
 
/* Creamos algunas facturas */
INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES(1, 'Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);
 
INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES(2, 'Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
 
/* Creamos algunos usuarios con sus roles */
INSERT INTO usuarios (id, username, password, enabled) VALUES (1, 'andres','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO usuarios (id, username, password, enabled) VALUES (2, 'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
 




INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO users_authorities (user_id, role_id) VALUES (1, 1);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 2);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 1);