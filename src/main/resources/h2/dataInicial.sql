INSERT INTO TCLIENTE (APELLIDOS, CEDULA, NOMBRE, TELEFONO, PAIS) VALUES ('PEREZ', '1', 'ROBERTO', '093939393','USA');
INSERT INTO TCLIENTE (APELLIDOS, CEDULA, NOMBRE, TELEFONO, PAIS) VALUES ('SANCHEZ', '2', 'RAUL', '093223333', 'CRC');
INSERT INTO TCLIENTE (APELLIDOS, CEDULA, NOMBRE, TELEFONO, PAIS) VALUES ('FERNANDEZ', '3', 'JORGE', '0102220333', 'CRC');
INSERT INTO CUENTA (NUMERO, TIPO, CLIENTE_ID, ESTADO) VALUES ('22222', 'AHORRO', 1, true);
INSERT INTO CUENTA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('33333', 'CORRIENTE', 2,true);
INSERT INTO CUENTA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('44444', 'AHORRO', 2, true);
INSERT INTO CUENTA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('55555', 'META', 2, true);
INSERT INTO CUENTA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('888888888', 'META', 3, true);
INSERT INTO DIRECCION (DIRECCION, NOMENCLATURA, CLIENTE_ID) VALUES ('10 de agosto', 'n31', 1);
INSERT INTO DIRECCION (DIRECCION, NOMENCLATURA, CLIENTE_ID) VALUES ('Direccion 2', 'n31', 1);
INSERT INTO DIRECCION (DIRECCION, NOMENCLATURA, CLIENTE_ID) VALUES ('Av. Amazonas', 'n100', 1);
INSERT INTO DIRECCION (DIRECCION, NOMENCLATURA, CLIENTE_ID) VALUES ('Av. Prensa', 'n1', 2);
INSERT INTO INVERSION (NUMERO, TIPO, CLIENTE_ID) VALUES ('11110', 'FONDO_FIJO', 1);
INSERT INTO INVERSION (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('22220', 'FONDO_VARIABLE', 2, true);
INSERT INTO TARJETA (NUMERO, TIPO, CLIENTE_ID, ESTADO) VALUES ('44549292929', 'VISA', 1, false);
INSERT INTO TARJETA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('44542323232', 'VISA', 2, false);
INSERT INTO TARJETA (NUMERO, TIPO, CLIENTE_ID,ESTADO) VALUES ('44212221212', 'MASTERCARD', 1,false);
