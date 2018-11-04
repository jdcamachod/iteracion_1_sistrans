package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.CarritoCompras;


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
        Query q = pm.newQuery(SQL, "INSERT INTO CARRITO (id, idCliente, fecha) values ( ?, ?, ?)");
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
        Query q = pm.newQuery(SQL, "DELETE FROM CARRITO WHERE id = ?");
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
	public CarritoCompras darCarritoPorId (PersistenceManager pm, long idCarrito) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO WHERE id = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(idCarrito);
		return (CarritoCompras) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProveedor - El nombre de proveedor buscado
	 * @return Una lista de objetos PROVEEDOR que tienen el nombre dado
	 */
	public CarritoCompras darCarritoPorCliente (PersistenceManager pm, Long idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO WHERE idCliente = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(idCliente);
		return (CarritoCompras) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PROVEEDORES de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Proveedor
	 */
	public List<CarritoCompras> darCarritos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO" );
		q.setResultClass(CarritoCompras.class);
		return (List<CarritoCompras>) q.executeList();
	}
	
	/**
	 * Le asigna al carrito de compras un cliente recibido por parametro
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del cliente
	 * @param fecha - La fecha en la que se asigna el carrito
	 * @param id - El identificador del carrito
	 * @return Un carrito con la informacion modificada
	 */
	public void asignarCliente(PersistenceManager pm, Long idCliente, Date fecha, long id)
	{
		Query q = pm.newQuery(SQL, "UPDATE CARRITO SET idCliente = ?, fecha = ? WHERE id = ? ");
		q.setParameters(idCliente, fecha, id);
		q.executeUnique();
	}
	
	/**
	 * Retorna una lista de los carritos que estan sin cliente
	 * @param pm - El manejador de persistencia
	 * @return Lista de carritos sin cliente
	 */
	public List<CarritoCompras> darCarritosSinCliente(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO WHERE idCliente IS NULL");
		q.setResultClass(CarritoCompras.class);
		return (List<CarritoCompras>)q.executeList();
	}
}
