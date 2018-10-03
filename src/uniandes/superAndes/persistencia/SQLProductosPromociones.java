package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.ProductosPromociones;
import uniandes.superAndes.negocio.SucursalesClientes;

class SQLProductosPromociones {

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
		public SQLProductosPromociones (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un SUCURSALES_CLIENTES a la base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @param idSucursal - El identificador de la sucursal
		 * @param idCliente - El identificador del cliente
		 * @return EL número de tuplas insertadas
		 */
		public long adicionarProductoPromocion(PersistenceManager pm, long idProducto, long idPromocion) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosPromociones() + "(idProducto, idPromocion) values (?, ?)");
	        q.setParameters(idProducto, idPromocion);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductoPromocion (PersistenceManager pm, long idProducto, long idPromocion)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosPromociones() + " WHERE idProducto = ? AND idPromocion = ?");
	        q.setParameters(idProducto, idPromocion);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<ProductosPromociones> darProductosPromociones (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosPromociones());
			q.setResultClass(ProductosPromociones.class);
			List<ProductosPromociones> resp = (List<ProductosPromociones>) q.execute();
			return resp;
		}
}
