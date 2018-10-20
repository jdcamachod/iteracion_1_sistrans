package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.SucursalesClientes;



class SQLSucursalesClientes {
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
		public SQLSucursalesClientes (PersistenciaSuperAndes pp)
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
		public long adicionarSucursalCliente(PersistenceManager pm, long idSucursal, long idCliente) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursalesClientes() + "(idSucursal, idCliente) values (?, ?)");
	        q.setParameters(idSucursal, idCliente);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SucursalesClientes de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del cliente
		 * @param idSucursal - El identificador de la sucursal
		 * @return EL n�mero de tuplas eliminadas
		 */
		public long eliminarSucursalCliente (PersistenceManager pm, long idCliente, long idSucursal)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursalesClientes() + " WHERE idCliente = ? AND idSucursal = ?");
	        q.setParameters(idCliente, idSucursal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<SucursalesClientes> darSucursalesClientes (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursalesClientes());
			q.setResultClass(SucursalesClientes.class);
			List<SucursalesClientes> resp = (List<SucursalesClientes>) q.execute();
			return resp;
		}
}
