package uniandes.superAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.net.aso.p;
import uniandes.superAndes.negocio.OrdenPedido;
import uniandes.superAndes.negocio.Pague1Lleve2Porcentaje;

class SQLPague1Lleve2Porcentaje {
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
		public SQLPague1Lleve2Porcentaje (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}



		/**
		 * Adiciona una nueva tupla de Pague1Lleve2Porcentaje a la base de datos
		 * @param pm - El manejador de persistencia
		 * @param idOrdenPedido - El identificador de la Pague1Lleve2Porcentaje	
		 * @param nombre nombre que se desea adicionar a la Pague1Lleve2Porcentaje
		 * @return
		 */
		public long adicionarPague1Lleve2Porcentaje(PersistenceManager pm, Long idPague1Lleve2Porcentaje, double porcentaje ) 
		{
			Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaPague1Lleve2Porcentaje() + "(id, porcentaje) values (?, ?)");
			q.setParameters(idPague1Lleve2Porcentaje, porcentaje);
			return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA Pague1Lleve2Porcentaje de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCategoria - El identificador de la Pague1Lleve2Porcentaje
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarPague1Lleve2PorcentajePorId (PersistenceManager pm, Long idPague1Lleve2Porcentaje)
		{
			Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPague1Lleve2Porcentaje() + " WHERE id = ?");
			q.setParameters(idPague1Lleve2Porcentaje);
			return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA OrdenPedido de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCategoria - El identificador de la OrdenPedido
		 * @return El objeto OrdenPedido que tiene el identificador dado
		 */
		public Pague1Lleve2Porcentaje darPague1Lleve2PorcentajePorId (PersistenceManager pm, Long idPague1Lleve2Porcentaje) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPague1Lleve2Porcentaje()  + " WHERE id = ?");
			q.setResultClass(Pague1Lleve2Porcentaje.class);
			q.setParameters(idPague1Lleve2Porcentaje);
			return (Pague1Lleve2Porcentaje) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Pague1Lleve2Porcentaje de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos Pague1Lleve2Porcentaje
		 */
		public List<Pague1Lleve2Porcentaje> darPromociones (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaPague1Lleve2Porcentaje() );
			q.setResultClass(Pague1Lleve2Porcentaje.class);
			return (List<Pague1Lleve2Porcentaje>) q.executeList();
		}
}
