--Solicitar un carrito
--Se buscaban los carritos sin cliente
SELECT * FROM CARRITO WHERE idCliente IS NULL;
--Se actualizaba el primero entre los encontrados
UPDATE CARRITO SET idCliente = ? WHERE idCarrito = ?;
-- En caso de que la primera consulta no diera ningun resultado
INSERT INTO CARRITO(idCliente, id, fecha) values(?,?,?)


--Adicionar producto al carrito de compras
--Se tenia el carrito, el producto y el estante de donde se tomaba ademas de la cantidad de productos
INSERT INTO CARRITO_PRODUCTOS(idCarrito, idProducto, idEstante, cantidad) values (?, ?, ?, ?);
-- Se resta la cantidad de productos tomados del estante
UPDATE PRODUCTOS_ESTANTES SET cantidad = cantidad - ? WHERE idProducto =? AND idEstante = ?;



--Devolver productos
--Se tiene la cantidad que se quiere devolver, El producto y el estante
UPDATE CARRITO_PRODUCTOS SET cantidad = cantidad -? WHERE idProducto = ? AND idEstante = ?;
--En caso de que la cantidad fuera la total que habia en el carrito
DELETE FROM CARRITO_PRODUCTOS WHERE idCarrito = ? AND idProducto = ?;
--Se devuelven los productos al estante
UPDATE PRODUCTOS_ESTANTES SET cantidad = cantidad + ? WHERE idProducto =? AND idEstante = ?;

--Pagar la compra
-- Se borran los productos del carrito, con todos
DELETE FROM CARRITO_PRODUCTOS WHERE idCarrito = ? AND idProducto = ?;
--Se restan los productos en general
UPDATE PRODUCTO SET cantidad = cantidad - ? WHERE id= ?;
--Se crea la factura
INSERT INTO FACTURA(fecha, id, sucursal, cliente) values (?,?,?,?);



--Abandonar carrito
UPDATE CARRITO SET idCliente = null AND fecha = null WHERE id = ?;


--Devolver productos de carritos abandonados
--Buscar todos los carritos sin cliente
SELECT * FROM CARRITO WHERE idCliente IS NULL;
DELETE FROM CARRITO_PRODUCTOS WHERE idCarrito = ? AND idProducto = ?;
