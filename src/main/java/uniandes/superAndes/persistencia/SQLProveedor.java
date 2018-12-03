package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Consulta;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.Proveedor;

class SQLProveedor {

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
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor() + "(id, nombre, nit, calificacion) values (?, ?, ?, ?)");
	        q.setParameters(idProveedor, nombre, nit, calificacionCalidad);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar PROVEEDORES de la base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreProveedor - El nombre del proveedor
		 * @return EL número de tuplas eliminadas
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
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProveedorPorId (PersistenceManager pm, long idProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor() + " WHERE id = ?");
	        q.setParameters(idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PROVEEDOR de la 
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
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
		 * base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreProveedor - El nombre de proveedor buscado
		 * @return Una lista de objetos PROVEEDOR que tienen el nombre dado
		 */
		public Proveedor darProveedorPorNombre (PersistenceManager pm, String nombreProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor()  + " WHERE nombre = ?");
			q.setResultClass(Proveedor.class);
			q.setParameters(nombreProveedor);
			return (Proveedor) q.executeUnique();
		}
		
		public List<Consulta> darProveedoresSemana(PersistenceManager pm, Date fechaInicial, Date fechaFinal)
		{
			Query q = pm.newQuery(SQL, "SELECT count(*) AS CUENTA, p.nombre\r\n" + 
					"FROM PROVEEDOR P \r\n" + 
					"INNER JOIN ORDEN_PEDIDO OP ON p.id= op.proveedor\r\n" + 
					"WHERE op.fechaesperadadeentrega BETWEEN ? AND ? GROUP BY p.nombre\r\n" + 
					"HAVING count(p.nombre)=(SELECT MAX(COUNT(p.nombre)) FROM PROVEEDOR T\r\n" + 
					"INNER JOIN ORDEN_PEDIDO TP ON t.id= tp.proveedor\r\n" + 
					"WHERE tp.fechaesperadadeentrega BETWEEN ? AND ?\r\n" + 
					"GROUP BY t.nombre) OR count(p.nombre)=(SELECT MIN(COUNT(p.nombre)) FROM PROVEEDOR T\r\n" + 
					"INNER JOIN ORDEN_PEDIDO TP ON t.id= tp.proveedor\r\n" + 
					"WHERE tp.fechaesperadadeentrega BETWEEN ? AND ?\r\n" + 
					"GROUP BY t.nombre) ORDER BY CUENTA");
			q.setParameters(fechaInicial, fechaFinal, fechaInicial, fechaFinal, fechaInicial, fechaFinal);
			q.setResultClass(Consulta.class);
			return (List<Consulta>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos Proveedor
		 */
		public List<Proveedor> darProveedores (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor() );
			q.setResultClass(Proveedor.class);
			return (List<Proveedor>) q.executeList();
		}
}
