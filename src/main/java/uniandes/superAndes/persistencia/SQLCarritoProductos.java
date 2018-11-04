package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.CarritoProductos;


public class SQLCarritoProductos {


	 /* ****************************************************************
		 * 			Constantes
		 *****************************************************************/
		/**
		 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
		 * Se renombra acá para facilitar la escritura de las sentencias
		 */
		private final static String SQL = PersistenciaSuperAndes.SQL;

		/* ****************************************************************
		 * 			Atributos
		 *****************************************************************/
		/**
		 * El manejador de persistencia general de la aplicación
		 */
		private PersistenciaSuperAndes pp;

		/* ****************************************************************
		 * 			Métodos
		 *****************************************************************/
		/**
		 * Constructor
		 * @param pp - El Manejador de persistencia de la aplicación
		 */
		public SQLCarritoProductos (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un PROMOCIONES_FACTURAS a la base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @param idFactura - El identificador de la factura
		 * @return EL número de tuplas insertadas
		 */
		public long adicionarCarritoProductos(PersistenceManager pm, Long idProducto, Long idCarrito, int cantidad, Long idEstante) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO CARRITO_PRODUCTOS (idProducto, idCarrito, cantidad, idEstante) values (?, ?, ?, ?)");
	        q.setParameters(idProducto, idCarrito, cantidad, idEstante);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN PROMOCION_FACTURA de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @param idFactura - El identificador de la factura
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarCarritoProductos (PersistenceManager pm, long idCarrito, long idProducto)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM CARRITO_PRODUCTOS WHERE idCarrito = ? AND idProducto = ?");
	        q.setParameters(idCarrito, idProducto);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<CarritoProductos> darCarritoProductos (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO_PRODUCTOS");
			q.setResultClass(CarritoProductos.class);
			List<CarritoProductos> resp = (List<CarritoProductos>) q.execute();
			return resp;
		}
		
		public List<CarritoProductos> darCarritoProductosPorCarrito (PersistenceManager pm, Long idCarrito)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO_PRODUCTOS WHERE idCarrito = ?");
			q.setParameters(idCarrito);
			q.setResultClass(CarritoProductos.class);
			List<CarritoProductos> resp = (List<CarritoProductos>) q.execute();
			return resp;
		}
}
