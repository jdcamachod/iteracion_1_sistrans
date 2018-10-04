package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Pague1Lleve2Porcentaje;
import uniandes.superAndes.negocio.PagueNLleveM;

class SQLPagueNLleveM {
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
		public SQLPagueNLleveM (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}



		/**
		 * Adiciona una nueva tupla de PagueNLleveM a la base de datos
		 * @param pm - El manejador de persistencia
		 * @param idOrdenPedido - El identificador de la PagueNLleveM	
		 * @param m nombre que se desea adicionar a la PagueNLleveM
		 * @return
		 */
		public long adicionarPagueNLleveM (PersistenceManager pm, long idPagueNLleveM, double m , double n) 
		{
			Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaPagueNLleveM() + "(id, m,n) values (?, ?. ?)");
			q.setParameters(idPagueNLleveM, m,n);
			return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA PagueNLleveM de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPagueNLleveM - El identificador de la PagueNLleveM
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarPague1Lleve2PorcentajePorId (PersistenceManager pm, long idPagueNLleveM)
		{
			Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPagueNLleveM() + " WHERE id = ?");
			q.setParameters(idPagueNLleveM);
			return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA OrdenPedido de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCategoria - El identificador de la OrdenPedido
		 * @return El objeto OrdenPedido que tiene el identificador dado
		 */
		public PagueNLleveM darPagueNLleveMPorId (PersistenceManager pm, long idPagueNLleveM) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPagueNLleveM()  + " WHERE id = ?");
			q.setResultClass(PagueNLleveM.class);
			q.setParameters(idPagueNLleveM);
			return (PagueNLleveM) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS PagueNLleveM de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos PagueNLleveM
		 */
		public List<PagueNLleveM> darCategorias (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaPagueNLleveM() );
			q.setResultClass(PagueNLleveM.class);
			return (List<PagueNLleveM>) q.executeList();
		}
}
