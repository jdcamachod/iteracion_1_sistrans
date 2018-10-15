package uniandes.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.Proveedor;

class SQLBodega {

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
	public SQLBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	

	/**
	 * Adiciona una nueva tupla de bodega a la base de datos
	 * @param pm
	 * @param idBodega
	 * @param direccion
	 * @param peso
	 * @param volumen
	 * @param idSucursal
	 * @return
	 */
	public long adicionarBodega (PersistenceManager pm, long idBodega, long tipo,String direccion, double peso, double volumen, long idSucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega() + "(id,tipo ,direccion, peso, volumen, idSucursal) values (?, ?,?, ?, ?,?)");
        q.setParameters(idBodega, tipo,direccion, peso, volumen, idSucursal);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA BODEGA de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador de la bodega
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarBodegaPorId (PersistenceManager pm, long idBodega)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE id = ?");
        q.setParameters(idBodega);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA BODEGA de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBodega - El identificador de la bodega
	 * @return El objeto BODEGA que tiene el identificador dado
	 */
	public Bodega darBodegaPorId (PersistenceManager pm, long idBodega) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega()  + " WHERE id = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idBodega);
		return (Bodega) q.executeUnique();
	}

	public Bodega darBodegaConTipoYEspacioParaUno (PersistenceManager pm, long idTipo ) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega()  + " WHERE tipo = ? ");
		q.setResultClass(Bodega.class);
		q.setParameters(idTipo);
		return (Bodega) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS BODEGAS de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Bodega
	 */
	public List<Bodega> darBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() );
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
}
