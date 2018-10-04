package uniandes.superAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.OrdenPedido;

class SQLOrdenPedido {
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
		public SQLOrdenPedido (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}



		/**
		 * Adiciona una nueva tupla de OrdenPedido a la base de datos
		 * @param pm - El manejador de persistencia
		 * @param idOrdenPedido - El identificador de la OrdenPedido	
		 * @param nombre nombre que se desea adicionar a la categoria
		 * @return
		 */
		public long adicionarOrdenPedido (PersistenceManager pm, long idOrdenPedido, String estado,Date fechaEsperadaDeEntrega, Date fechaEntrega, String calificacionPedido , long proveedor
  ) 
		{
			Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaOrdenPedido() + "(id, estado, fechaEsperadaDeEntrega,  fechaEntrega, calificacionPedido, proveedor) values (?, ?,?,?,?,?)");
			q.setParameters(idOrdenPedido, estado, fechaEsperadaDeEntrega, fechaEntrega, calificacionPedido, proveedor);
			return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UNA OrdenPedido de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCategoria - El identificador de la OrdenPedido
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarOrdenPedidoPorId (PersistenceManager pm, long idOrdenPedido)
		{
			Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido() + " WHERE id = ?");
			q.setParameters(idOrdenPedido);
			return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA OrdenPedido de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCategoria - El identificador de la OrdenPedido
		 * @return El objeto OrdenPedido que tiene el identificador dado
		 */
		public OrdenPedido darOrdenPedidoPorId (PersistenceManager pm, long idOrdenPedido) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido()  + " WHERE id = ?");
			q.setResultClass(OrdenPedido.class);
			q.setParameters(idOrdenPedido);
			return (OrdenPedido) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS OrdenPedido de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos OrdenPedido
		 */
		public List<OrdenPedido> darCategorias (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaOrdenPedido() );
			q.setResultClass(OrdenPedido.class);
			return (List<OrdenPedido>) q.executeList();
		}
}
