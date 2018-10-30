package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.Promocion;

class SQLPromocion {

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
		public SQLPromocion (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		public Long adicionarPromocion (PersistenceManager pm, long idPromocion, Date fechaInicial, Date fechaFinal, double precio, Long idPague1Lleve2Porcentaje, Long idPagueNLleveM, Long idPagueXLleveY, Long idDescuentoPorcentaje) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocion() + "(id, fechaInicial, fechaFinal, precio, pague1LleveSegPorcentaje, pagueNLleveM, pagueXLleveY, descuentoPorcentaje) values (?, ?, ?, ?,?, ?, ?, ?)");
	        q.setParameters(idPromocion, fechaInicial, fechaFinal, precio, idPague1Lleve2Porcentaje, idPagueNLleveM, idPagueXLleveY, idDescuentoPorcentaje);
	        return (Long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA PROMOCION de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @return EL número de tuplas eliminadas
		 */
		public Long eliminarPromocionPorId (PersistenceManager pm, Long idPromocion)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion() + " WHERE id = ?");
	        q.setParameters(idPromocion);
	        return (Long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA PROMOCION de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPromocion - El identificador de la promocion
		 * @return El objeto Promocion que tiene el identificador dado
		 */
		public Promocion darPromocionPorId (PersistenceManager pm, Long idPromocion) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion()  + " WHERE id = ?");
			q.setResultClass(Promocion.class);
			q.setParameters(idPromocion);
			return (Promocion) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PROMOCIONES de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos Promocion
		 */
		public List<Promocion> darPromociones (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion());
			q.setResultClass(Producto.class);
			return (List<Promocion>) q.executeList();
		}
}
