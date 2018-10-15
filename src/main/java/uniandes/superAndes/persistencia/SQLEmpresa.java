package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.Empresa;

class SQLEmpresa {
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
	public SQLEmpresa (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}



	/**
	 * Adiciona una nueva tupla de Empresa  a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador de la Empresa 	
	 * @param nit nit de la empresa a crear 
	 * @param direccion direccion de la empresa
	 * @return
	 */
	public long adicionarEmpresa  (PersistenceManager pm, long idEmpresa, double nit,String direccion ) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaEmpresa() + "(id, nit, direccion ) values (?, ?, ?)");
		q.setParameters(idEmpresa, nit, direccion);
		return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA Empresa de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador de la Empresa
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEmpresaPorId (PersistenceManager pm, long idEmpresa)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa() + " WHERE id = ?");
		q.setParameters(idEmpresa);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Empresa de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpresa - El identificador de la Empresa
	 * @return El objeto Empresa que tiene el identificador dado
	 */
	public Empresa darEmpresaPorId (PersistenceManager pm, long idEmpresa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa()  + " WHERE id = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(idEmpresa);
		return (Empresa) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Empresas de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Empresa
	 */
	public List<Empresa> darEmpresa (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaEmpresa() );
		q.setResultClass(Empresa.class);
		return (List<Empresa>) q.executeList();
	}
}
