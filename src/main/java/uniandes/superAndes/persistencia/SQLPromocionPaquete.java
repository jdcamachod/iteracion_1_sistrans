package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.PromocionPaquete;


class SQLPromocionPaquete {

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
		public SQLPromocionPaquete (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un PROMOCION_PAQUETE a la base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @param idProductoOrigen - El identificador del producto de origen
		 * @param idProductoPaquete - El identificador del producto paquete
		 * @return EL número de tuplas insertadas
		 */
		public long adicionarPromocionPaquete(PersistenceManager pm, long idProductoOrigen, long idProductoPaquete,Date fechaInicial, Date fechaFinal) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionPaquete() + "(idProductoOrigen,idProductoPaquete, fechaInicial, fechaFinal) values (?, ?, ?, ?)");
	        q.setParameters(idProductoOrigen, idProductoPaquete, fechaInicial, fechaFinal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN PROMOCION_PAQUETE de la base de datos de SuperAndes, por sus identificador
		 * @param pm - El manejador de persistencia
		 * @param idProductoOrigen - El identificador del cliente
		 * @param idProductoPaquete - El identificador de la sucursal
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarPromocionPaquete (PersistenceManager pm, long idProductoOrigen, long idProductoPaquete)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionPaquete() + " WHERE IdProductoOrigen = ? AND idProductoPaquete = ?");
	        q.setParameters(idProductoOrigen, idProductoPaquete);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SucursalesClientes de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos SucursalesClientes
		 */
		public List<PromocionPaquete> darPromocionesPaquetes (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionPaquete());
			q.setResultClass(PromocionPaquete.class);
			List<PromocionPaquete> resp = (List<PromocionPaquete>) q.execute();
			return resp;
		}
}
