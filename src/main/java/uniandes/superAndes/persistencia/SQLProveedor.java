package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.Proveedor;

class SQLProveedor {

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
		public SQLProveedor (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		
		/**
		 * Adiciona una nueva tupla de proveedor en la base de datos
		 * @param pm manejador de persistencia
		 * @param idProveedor
		 * @param nombre
		 * @param nit
		 * @param calificacionCalidad
		 * @return
		 */
		public long adicionarProveedor (PersistenceManager pm, long idProveedor, String nombre, String nit, String calificacionCalidad) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor() + "(id, nombre, nit, calificacionCalidad) values (?, ?, ?, ?)");
	        q.setParameters(idProveedor, nombre, nit, calificacionCalidad);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar PROVEEDORES de la base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreProveedor - El nombre del proveedor
		 * @return EL n�mero de tuplas eliminadas
		 */
		public long eliminarProveedoresPorNombre (PersistenceManager pm, String nombreProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor()  + " WHERE nombre = ?");
	        q.setParameters(nombreProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN PROVEEDOR de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idProveedor - El identificador del proveedor
		 * @return EL n�mero de tuplas eliminadas
		 */
		public long eliminarProveedorPorId (PersistenceManager pm, long idProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor() + " WHERE id = ?");
	        q.setParameters(idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de UN PROVEEDOR de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idProveedor - El identificador del proveedor
		 * @return El objeto PROVEEDOR que tiene el identificador dado
		 */
		public Proveedor darProveedorPorId (PersistenceManager pm, long idProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor()  + " WHERE id = ?");
			q.setResultClass(Proveedor.class);
			q.setParameters(idProveedor);
			return (Proveedor) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS PROVEEDORES de la 
		 * base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreProveedor - El nombre de proveedor buscado
		 * @return Una lista de objetos PROVEEDOR que tienen el nombre dado
		 */
		public List<Proveedor> darProveedoresPorNombre (PersistenceManager pm, String nombreProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor()  + " WHERE nombre = ?");
			q.setResultClass(Proveedor.class);
			q.setParameters(nombreProveedor);
			return (List<Proveedor>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de LOS PROVEEDORES de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos Proveedor
		 */
		public List<Proveedor> darProveedores (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor() );
			q.setResultClass(Producto.class);
			return (List<Proveedor>) q.executeList();
		}
}
