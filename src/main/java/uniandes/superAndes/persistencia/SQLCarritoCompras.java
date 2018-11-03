package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.CarritoCompras;
import uniandes.superAndes.negocio.Proveedor;

public class SQLCarritoCompras {

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
	public SQLCarritoCompras (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Adiciona una nueva tupla de proveedor en la base de datos
	 * @param pm manejador de persistencia
	 * @param idProveedor
	 * @param nombre
	 * @param nit
	 * @param calificacionCalidad
	 * @return
	 */
	public long adicionarCarritoCompras (PersistenceManager pm, long idCarrito, Long idCliente, Date fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito() + "(id, idCliente, fecha) values ( ?, ?, ?)");
        q.setParameters(idCarrito, idCliente, fecha);
        return (long) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PROVEEDOR de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProveedor - El identificador del proveedor
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCarrito (PersistenceManager pm, long idCarrito)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE id = ?");
        q.setParameters(idCarrito);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PROVEEDOR de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProveedor - El identificador del proveedor
	 * @return El objeto PROVEEDOR que tiene el identificador dado
	 */
	public Proveedor darCarritoPorId (PersistenceManager pm, long idCarrito) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito()  + " WHERE id = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(idCarrito);
		return (Proveedor) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProveedor - El nombre de proveedor buscado
	 * @return Una lista de objetos PROVEEDOR que tienen el nombre dado
	 */
	public Proveedor darCarritoPorCliente (PersistenceManager pm, Long idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito()  + " WHERE idCliente = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(idCliente);
		return (Proveedor) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Proveedor
	 */
	public List<CarritoCompras> darCarritos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() );
		q.setResultClass(CarritoCompras.class);
		return (List<CarritoCompras>) q.executeList();
	}
}
