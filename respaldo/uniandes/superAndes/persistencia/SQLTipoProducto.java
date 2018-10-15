package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.TipoProducto;

class SQLTipoProducto {
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
		public SQLTipoProducto (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		

		/**
		 * Adiciona una nueva tupla de TipoProducto a la base de datos
		 * @param pm manejador de persistencia
		 * @param idTipoProducto
		 * @param nombre
		 * @return
		 */
		public long adicionarTipoProducto (PersistenceManager pm, long idTipoProducto, String nombre) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoProducto() + "(id,nombre) values (?, ?)");
	        q.setParameters(idTipoProducto, nombre);
	        return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN TIPO_PRODUCTO de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idTipoProducto - El identificador del tipo de producto
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarTipoProductoPorId (PersistenceManager pm, long idTipoProducto)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoProducto() + " WHERE id = ?");
	        q.setParameters(idTipoProducto);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO_PRODUCTO de la 
		 * base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idTipoProducto - El identificador del tipo de producto
		 * @return El objeto BODEGA que tiene el identificador dado
		 */
		public TipoProducto darTipoProductoPorId (PersistenceManager pm, long idTipoProducto) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto()  + " WHERE id = ?");
			q.setResultClass(TipoProducto.class);
			q.setParameters(idTipoProducto);
			return (TipoProducto) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE PRODUCTO de la 
		 * base de datos de SuperAndes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos TipoProducto
		 */
		public List<TipoProducto> darTiposProducto (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto() );
			q.setResultClass(Producto.class);
			return (List<TipoProducto>) q.executeList();
		}
}
