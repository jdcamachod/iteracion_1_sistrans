package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.PromocionesFacturas;
import uniandes.superAndes.negocio.SucursalesClientes;

class SQLPromocionesFacturas {

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
		public SQLPromocionesFacturas (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un PROMOCIONES_FACTURAS a la base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @param idFactura - El identificador de la factura
		 * @return EL número de tuplas insertadas
		 */
		public long adicionarPromocionFactura(PersistenceManager pm, long idPromocion, long idFactura) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionesFacturas() + "(idPromocion, idFactura) values (?, ?)");
	        q.setParameters(idPromocion, idFactura);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN PROMOCION_FACTURA de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @param idFactura - El identificador de la factura
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarSucursalCliente (PersistenceManager pm, long idPromocion, long idFactura)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionesFacturas() + " WHERE idPromocion = ? AND idFactura = ?");
	        q.setParameters(idPromocion, idFactura);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<PromocionesFacturas> darPromocionesFacturas (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionesFacturas());
			q.setResultClass(SucursalesClientes.class);
			List<PromocionesFacturas> resp = (List<PromocionesFacturas>) q.execute();
			return resp;
		}
}
