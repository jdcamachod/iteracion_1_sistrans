package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.DescuentoPorcentaje;

class SQLDescuentoPorcentaje {
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
	public SQLDescuentoPorcentaje (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}



	/**
	 * Adiciona una nueva tupla de categoria a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idDescuentoPorcentaje - El identificador del descuento	
	 * @param porcentaje porcentaje para el Descuento Porcentaje
	 * @return
	 */
	public long adicionarDescuentoPorcentaje (PersistenceManager pm, long idDescuentoPorcentaje, double porcentaje) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaDescuentoPorcentaje() + "(id, procentaje) values (?, ?)");
		q.setParameters(idDescuentoPorcentaje, porcentaje);
		return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Descuento Porcentaje de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idDescuentoPorcentaje - El identificador de la DescuentoPorcentaje
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCDescuentoPorcentajePorId (PersistenceManager pm, long idDescuentoPorcentaje)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDescuentoPorcentaje() + " WHERE id = ?");
		q.setParameters(idDescuentoPorcentaje);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Descuento Porcentaje de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idDescuentoPorcentaje - El identificador de la Descuento Porcentaje
	 * @return El objeto CATEGORIA que tiene el identificador dado
	 */
	public DescuentoPorcentaje darDescuentoPorcentajePorId (PersistenceManager pm, long idDescuentoPorcentaje) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDescuentoPorcentaje()  + " WHERE id = ?");
		q.setResultClass(DescuentoPorcentaje.class);
		q.setParameters(idDescuentoPorcentaje);
		return (DescuentoPorcentaje) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Descuento Porcentaje de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Descuento Porcentaje
	 */
	public List<DescuentoPorcentaje> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaDescuentoPorcentaje());
		q.setResultClass(DescuentoPorcentaje.class);
		return (List<DescuentoPorcentaje>) q.executeList();
	}
}
