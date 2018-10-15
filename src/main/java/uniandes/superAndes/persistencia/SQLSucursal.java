package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.Sucursal;

class SQLSucursal {
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
		public SQLSucursal (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		
		
		/**
		 * Adiciona una nueva tupla de sucursales en la base de datos
		 * @param pm
		 * @param idSucursal
		 * @param nombre
		 * @param direccion
		 * @param ciudad
		 * @param tamanio
		 * @return
		 */
		public long adicionarSucursal (PersistenceManager pm, long idSucursal, String nombre, String direccion, String ciudad, int tamanio) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "(id, nombre, direccion, ciudad, tamanio) values (?, ?, ?, ?,?)");
	        q.setParameters(idSucursal, nombre, direccion, ciudad, tamanio);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar SUCURSALES de la base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreSucursal - El nombre de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarSucursalesPorNombre (PersistenceManager pm, String nombreSucursal)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal() + " WHERE nombre = ?");
	        q.setParameters(nombreSucursal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA SUCURSAL de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarSucursalesPorId (PersistenceManager pm, long idSucursal)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal() + " WHERE id = ?");
	        q.setParameters(idSucursal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA SUCURSAL de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idSucursal - El identificador de la sucursal
		 * @return El objeto SUCURSAL que tiene el identificador dado
		 */
		public Sucursal darSucursalesPorId (PersistenceManager pm, long idSucursal) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal()  + " WHERE id = ?");
			q.setResultClass(Sucursal.class);
			q.setParameters(idSucursal);
			return (Sucursal) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SUCURSALES de la 
		 * base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreSucursal - El nombre de la sucursal buscado
		 * @return Una lista de objetos Sucursales que tienen el nombre dado
		 */
		public List<Sucursal> darSucursalesPorNombre (PersistenceManager pm, String nombreSucursal) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal()  + " WHERE nombre = ?");
			q.setResultClass(Sucursal.class);
			q.setParameters(nombreSucursal);
			return (List<Sucursal>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SUCURSALES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SUCURSALES
		 */
		public List<Sucursal> darSucursales (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal() );
			q.setResultClass(Sucursal.class);
			return (List<Sucursal>) q.executeList();
		}
}
