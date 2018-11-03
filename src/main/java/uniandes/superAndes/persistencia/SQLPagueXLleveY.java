package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.PagueXLleveY;
import uniandes.superAndes.negocio.Producto;

class SQLPagueXLleveY {

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
		public SQLPagueXLleveY (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		

		/**
		 * Adiciona una nueva tupla de bodega a la base de datos
		 * @param pm
		 * @param idBodega
		 * @param direccion
		 * @param peso
		 * @param volumen
		 * @param idSucursal
		 * @return
		 */
		public long adicionarPagueXLleveY (PersistenceManager pm, Long idPagueXLleveY, int x, int y) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPagueXLleveY() + "(idPagueXLleveY, x, y) values (?, ?, ?)");
	        q.setParameters(idPagueXLleveY, x, y);
	        return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA BODEGA de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idBodega - El identificador de la bodega
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarPagueXLleveYPorId (PersistenceManager pm, Long idPagueXLleveY)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPagueXLleveY() + " WHERE id = ?");
	        q.setParameters(idPagueXLleveY);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PROVEEDOR de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idBodega - El identificador de la bodega
		 * @return El objeto BODEGA que tiene el identificador dado
		 */
		public PagueXLleveY darPagueXLleveY (PersistenceManager pm, Long idPagueXLleveY) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPagueXLleveY()  + " WHERE id = ?");
			q.setResultClass(PagueXLleveY.class);
			q.setParameters(idPagueXLleveY);
			return (PagueXLleveY) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS BODEGAS de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos Bodega
		 */
		public List<PagueXLleveY> darPromocionesPagueXLleveY (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPagueXLleveY() );
			q.setResultClass(PagueXLleveY.class);
			return (List<PagueXLleveY>) q.executeList();
		}
}
