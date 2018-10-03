package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.Producto;

class SQLCategoria {
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
	public SQLCategoria (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}



	/**
	 * Adiciona una nueva tupla de categoria a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la bodega	
	 * @param nombre nombre que se desea adicionar a la categoria
	 * @return
	 */
	public long adicionarCategoria (PersistenceManager pm, long idCategoria, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaCategoria() + "(id, nombre) values (?, ?)");
		q.setParameters(idCategoria, nombre);
		return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA BODEGA de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la bodega
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCategoriaPorId (PersistenceManager pm, long idCategoria)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria() + " WHERE id = ?");
		q.setParameters(idCategoria);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA CATEGORIA de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCategoria - El identificador de la bodega
	 * @return El objeto CATEGORIA que tiene el identificador dado
	 */
	public Categoria darCategoriaPorId (PersistenceManager pm, long idCategoria) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria()  + " WHERE id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(idCategoria);
		return (Categoria) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS BODEGAS de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CATEGORIA
	 */
	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaCategoria() );
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}
}
