package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.OrdenesProductos;

class SQLOrdenesProductos {
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
		public SQLOrdenesProductos (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}



		/**
		 * Adiciona una nueva tupla de OrdenesProductos a la base de datos
		 * @param pm - El manejador de persistencia
		 * @param idOrdenesProductos - El identificador de la OrdenesProductos	
		 * @return
		 */
		public long adicionarOrdenesProductos (PersistenceManager pm, long idOrdenesProductos, long idProducto, long idOrden, double precioProveedor) 
		{
			Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaOrdenesProductos() + "(id, idProducto, idOrden, precioProveedor) values (?, ?, ?, ?)");
			q.setParameters(idOrdenesProductos, idProducto, idOrden, precioProveedor);
			return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA OrdenesProductos de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idOrdenesProductos - El identificador de la OrdenesProductos
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarOrdenesProductosPorId (PersistenceManager pm, long idOrdenesProductos)
		{
			Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenesProductos() + " WHERE id = ?");
			q.setParameters(idOrdenesProductos);
			return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA OrdenesProductos de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idOrdenesProductos - El identificador de la OrdenesProductos
		 * @return El objeto OrdenesProductos que tiene el identificador dado
		 */
		public OrdenesProductos darOrdenesProductosPorId (PersistenceManager pm, long idOrdenesProductos) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenesProductos()  + " WHERE id = ?");
			q.setResultClass(OrdenesProductos.class);
			q.setParameters(idOrdenesProductos);
			return (OrdenesProductos) q.executeUnique();
		}
		
		public List<Object []> darProductosPorOrden (PersistenceManager pm, long idOrden)
		{
	        String sql = "SELECT idProducto, COUNT(idProducto) ";
	        sql += " FROM " + pp.darTablaOrdenesProductos ();
	        sql += " WHERE idOrden = " + idOrden ;
	       	sql	+= " GROUP BY idProducto";
			Query q = pm.newQuery(SQL, sql);
			return q.executeList();
		}

}
