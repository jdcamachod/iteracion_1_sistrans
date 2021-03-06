package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.ProductosEstantes;



class SQLProductosEstantes {

	 /* ****************************************************************
		 * 			Constantes
		 *****************************************************************/
		/**
		 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
		 * Se renombra ac� para facilitar la escritura de las sentencias
		 */
		private final static String SQL = PersistenciaSuperAndes.SQL;

		/* ****************************************************************
		 * 			Atributos
		 *****************************************************************/
		/**
		 * El manejador de persistencia general de la aplicaci�n
		 */
		private PersistenciaSuperAndes pp;

		/* ****************************************************************
		 * 			M�todos
		 *****************************************************************/
		/**
		 * Constructor
		 * @param pp - El Manejador de persistencia de la aplicaci�n
		 */
		public SQLProductosEstantes (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un SUCURSALES_CLIENTES a la base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @param idSucursal - El identificador de la sucursal
		 * @param idCliente - El identificador del cliente
		 * @return EL n�mero de tuplas insertadas
		 */
		public long adicionarProductosEstantes(PersistenceManager pm, long idProducto, long idEstante, int cantidad) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosEstantes() + "(idProducto, idEstante, cantidad) values (?, ?,?)");
	        q.setParameters(idProducto, idEstante, cantidad);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificad
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL n�mero de tuplas eliminadas
		 */
		public long eliminarProductosEstante (PersistenceManager pm, long idProducto, long idEstante)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosEstantes() + " WHERE idProducto = ? AND idEstante = ?");
	        q.setParameters(idProducto, idEstante);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<ProductosEstantes> darProductosEstantes (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosEstantes());
			q.setResultClass(ProductosEstantes.class);
			List<ProductosEstantes> resp = (List<ProductosEstantes>) q.executeList();
			return resp;
		}
		
		public ProductosEstantes darProductoEstante(PersistenceManager pm, Long idProducto, Long idEstante)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosEstantes()+" WHERE idProducto = ? AND idEstante = ? ");
			q.setParameters(idProducto, idEstante);
			q.setResultClass(ProductosEstantes.class);
			return (ProductosEstantes)q.executeUnique();
		}
		
		public void restarCantidad(PersistenceManager pm, int cantidad, Long idProducto, Long idEstante)
		{
			Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaProductosEstantes()+" SET cantidad = cantidad - ?  WHERE idProducto = ? AND idEstante = ?");
			q.setParameters(cantidad, idProducto, idEstante);
			q.executeUnique();
		}
		
		public void devolverCantidad(PersistenceManager pm, int cantidad, Long idProducto, Long idEstante)
		{
			Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaProductosEstantes()+" SET cantidad = cantidad + ?  WHERE idProducto = ? AND idEstante = ?");
			q.setParameters(cantidad, idProducto, idEstante);
			q.executeUnique();
		}
		public List<ProductosEstantes> darProductosEstantesPorEstante (PersistenceManager pm, Long idEstante)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosEstantes()+" WHERE idEstante = ?");
			q.setParameters(idEstante);
			q.setResultClass(ProductosEstantes.class);
			List<ProductosEstantes> resp = (List<ProductosEstantes>) q.executeList();
			return resp;
		}
}
