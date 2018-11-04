package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.ProductosFacturas;
import uniandes.superAndes.negocio.SucursalesClientes;

class SQLProductosFacturas {

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
		public SQLProductosFacturas (PersistenciaSuperAndes pp)
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
		public long adicionarProductoFactura(PersistenceManager pm, long idProducto, long idFactura, int cantidad) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosFacturas() + "(idProduco, idFactura, cantidad) values (?, ?, ?)");
	        q.setParameters(idProducto, idFactura, cantidad);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductoFactura (PersistenceManager pm, long idProducto, long idFactura)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosFacturas() + " WHERE idProducto = ? AND idFactura = ?");
	        q.setParameters(idProducto, idFactura);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<ProductosFacturas> darProductosFacturas (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosFacturas());
			q.setResultClass(ProductosFacturas.class);
			List<ProductosFacturas> resp = (List<ProductosFacturas>) q.execute();
			return resp;
		}
}
