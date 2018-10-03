package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.ProductosProveedores;
import uniandes.superAndes.negocio.SucursalesClientes;

class SQLProductosProveedores {

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
		public SQLProductosProveedores (PersistenciaSuperAndes pp)
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
		public long adicionarProductoProveedor(PersistenceManager pm, long idProducto, long idProveedor) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosProveedores() + "(idProducto, idProveedor) values (?, ?)");
	        q.setParameters(idProducto, idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductoProveedor (PersistenceManager pm, long idProducto, long idProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursalesClientes() + " WHERE idProducto = ? AND idProveedor = ?");
	        q.setParameters(idProducto, idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<ProductosProveedores> darProductosProveedores (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosProveedores());
			q.setResultClass(ProductosProveedores.class);
			List<ProductosProveedores> resp = (List<ProductosProveedores>) q.execute();
			return resp;
		}
}
