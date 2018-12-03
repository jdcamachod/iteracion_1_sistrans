--PUNTO 1
SELECT distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa
FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente 
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
WHERE p.nombre = 'Apron' AND  F.fecha BETWEEN '8/9/18' AND '14/11/18' ORDER BY puntos;

--Consultando con sucursal
SELECT distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa
FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
INNER JOIN SUCURSAL S ON s.id = f.sucursal
WHERE p.nombre = 'Apron' AND  F.fecha BETWEEN '8/9/18' AND '14/11/18' AND s.id = 62;

--PUNTO 2
SELECT distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa
FROM CLIENTE C INNER JOIN FACTURA F ON c.id <> f.cliente 
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
WHERE p.nombre = 'Apron' AND  F.fecha BETWEEN '8/9/18' AND '14/11/18' ORDER BY puntos;

-- punto 3

SELECT count(*) AS cuenta , p.id
FROM PRODUCTO P 
INNER JOIN PRODUCTOS_FACTURA PF ON p.id= pf.idproducto
INNER JOIN FACTURA F ON pf.idfactura = f.id
WHERE F.fecha BETWEEN '8/9/18' AND '14/12/19' GROUP BY p.id
HAVING count(p.id)=(SELECT MAX(COUNT(p.id)) FROM PRODUCTO T
INNER JOIN PRODUCTOS_FACTURA TF ON t.id= tf.idproducto
INNER JOIN FACTURA E ON tf.idfactura = e.id
WHERE E.fecha BETWEEN '8/9/18' AND '14/12/19'
GROUP BY t.id) OR count(p.id)=(SELECT MIN(COUNT(p.id)) FROM PRODUCTO T
INNER JOIN PRODUCTOS_FACTURA TF ON t.id= tf.idproducto
INNER JOIN FACTURA E ON tf.idfactura = e.id
WHERE E.fecha BETWEEN '8/9/18' AND '14/12/19'
GROUP BY t.id);

SELECT p.*
FROM PRODUCTO P 
INNER JOIN PRODUCTOS_FACTURA PF ON p.id<> pf.idproducto
INNER JOIN FACTURA F ON pf.idfactura = f.id
WHERE F.fecha BETWEEN '8/9/18' AND '14/12/19' ;

SELECT count(*) AS CUENTA, p.nombre
FROM PROVEEDOR P 
INNER JOIN ORDEN_PEDIDO OP ON p.id= op.proveedor
WHERE op.fechaesperadadeentrega BETWEEN '7/12/19' AND '14/12/19' GROUP BY p.nombre
HAVING count(p.nombre)=(SELECT MAX(COUNT(p.nombre)) FROM PROVEEDOR T
INNER JOIN ORDEN_PEDIDO TP ON t.id= tp.proveedor
WHERE tp.fechaesperadadeentrega BETWEEN '7/12/19' AND '14/12/19'
GROUP BY t.nombre) OR count(p.nombre)=(SELECT MIN(COUNT(p.nombre)) FROM PROVEEDOR T
INNER JOIN ORDEN_PEDIDO TP ON t.id= tp.proveedor
WHERE tp.fechaesperadadeentrega BETWEEN '7/12/19' AND '14/12/19'
GROUP BY t.nombre) ORDER BY CUENTA;

--PUNTO 4
--Clientes que compran una vez al mes
SELECT  Distinct(c.id),COUNT(*)
FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente 
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
WHERE f.fecha BETWEEN '01/01/2018'AND '31/12/2018'
GROUP BY c.id
HAVING COUNT(c.id)>12;

--Clientes que compran productos costosos
SELECT  distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa
FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente 
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
WHERE p.preciounitario > 100000;

--Clientes que compran herramientas o tecnologia
SELECT  distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa
FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente 
INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id
INNER JOIN PRODUCTO P ON p.id = pf.idproducto
WHERE p.categoria = 14 OR p.categoria = 1;



