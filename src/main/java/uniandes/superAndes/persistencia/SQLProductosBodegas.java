package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.ProductosBodegas;


class SQLProductosBodegas {

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
		public SQLProductosBodegas (PersistenciaSuperAndes pp)
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
		public long adicionarProductoBodega(PersistenceManager pm, long idProducto, long idBodega) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosBodegas() + "(idProducto, idBodega) values (?, ?)");
	        q.setParameters(idProducto, idBodega);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductoBodega (PersistenceManager pm, long idProducto, long idBodega)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosBodegas() + " WHERE idProducto = ? AND idBodega = ?");
	        q.setParameters(idProducto, idBodega);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<ProductosBodegas> darProductosBodegas (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosBodegas());
			q.setResultClass(ProductosBodegas.class);
			List<ProductosBodegas> resp = (List<ProductosBodegas>) q.execute();
			return resp;
		}
		public List<Long> darProductosDeBodega (PersistenceManager pm, long idBodega)
		{
			Query q = pm.newQuery(SQL, "SELECT idproducto FROM " + pp.darTablaProductosBodegas()+ " WHERE idBodega = ? ");
			q.setResultClass(ProductosBodegas.class);
			q.setParameters(idBodega);
			List<Long> resp = (List<Long>) q.executeList();
			return resp;
		}
}
