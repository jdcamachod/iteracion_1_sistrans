package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.Estante;

class SQLEstante {
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
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}



	/**
	 * Adiciona una nueva tupla de Estante a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador de la Estante	
	 * @param nombre nombre que se desea adicionar a la categoria
	 * @return
	 */
	/**
	 Adiciona una nueva tupla de Estante a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador de la Estante	
	 * @param tipo tipo de almacenamiento del estante
	 * @param volumen volumen del estante
	 * @param peso peso del estante
	 * @param direccion direccion en el local del estante
	 * @param nivelAbastecimiento del estante
	 * @param idSucursal sucursal asociada al estante
	 * @return
	 */
	public long adicionarEstante (PersistenceManager pm, long idEstante, long tipo, double volumen, double peso, String direccion, int nivelAbastecimiento,long idSucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante() + "(id, tipo,direccion, peso, volumen, idSucursal) values (?, ?,?, ?, ?,?)");
        q.setParameters(idEstante, tipo,direccion, peso, volumen, idSucursal);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Estante de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador del Estante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEstantePorId (PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante() + " WHERE id = ?");
		q.setParameters(idEstante);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Estante de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El identificador de la Estante
	 * @return El objeto Estante que tiene el identificador dado
	 */
	public Estante darEstantePorId (PersistenceManager pm, long idEstante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante()  + " WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Estantes de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Estante
	 */
	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaEstante() );
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
}
