package uniandes.superAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.Factura;
import uniandes.superAndes.negocio.FechasDemanda;
import uniandes.superAndes.negocio.FechasIngresos;

class SQLFactura {
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
	public SQLFactura (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	/**
	 * 
	 * Adiciona una nueva tupla de Factura a la base de datos
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la Factura	
	 * @param fecha fecha de la factura
	 * @param costoTotal costo de la factura
	 * @param idCliente cliente asociado a la factura
	 * @return
	 */
	public long adicionarFactura (PersistenceManager pm, long idFactura, Date fecha, double costoTotal, Long idCliente, Long sucursal) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " +  pp.darTablaFactura() + "(id, fecha, costototal, cliente, sucursal) values (?, ?, ?, ?, ?)");
		q.setParameters(idFactura, fecha, costoTotal, idCliente);
		return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA Factura de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la Factura
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarFacturaPorId (PersistenceManager pm, long idFactura)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura() + " WHERE id = ?");
		q.setParameters(idFactura);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Factura de la 
	 * base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la Factura
	 * @return El objeto Factura que tiene el identificador dado
	 */
	public Factura darFacturaPorId (PersistenceManager pm, long idFactura) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFactura()  + " WHERE id = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idFactura);
		return (Factura) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Facturas de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Factura
	 */
	public List<Factura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaFactura() );
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}


	public List<Factura> darFacturasEntreFechasYSucursal(PersistenceManager pm , Date fechaInicial, Date fechaFinal, long idSucursal) {
		// TODO Auto-generated method stub
		
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaFactura() + " WHERE sucursal = ? AND fecha BETWEEN ? AND ?" );
		q.setResultClass(Factura.class);
		q.setParameters(idSucursal,fechaInicial, fechaFinal);
		return (List<Factura>) q.executeList();
	}
	
	public List<Factura> darFacturasEntreFechas(PersistenceManager pm , Date fechaInicial, Date fechaFinal) {
		// TODO Auto-generated method stub
		
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaFactura() + " WHERE fecha BETWEEN ? AND ?" );
		q.setResultClass(Factura.class);
		q.setParameters(fechaInicial, fechaFinal);
		return (List<Factura>) q.executeList();
	}
	
	public List <FechasDemanda> darFechasConDemandaDeSucursal (PersistenceManager pm,long categoria,long idSucursal, Date fechaInicial, Date fechaFinal) {
		Query q = pm.newQuery(SQL, "SELECT factura.fecha,factura.sucursal, sum(productos_factura.cantidad)as sumacantidades  FROM factura, productos_factura, producto WHERE producto.id = productos_factura.idproducto and producto.categoria = ? and factura.sucursal = ? AND factura.id = productos_factura.idfactura AND fecha BETWEEN ? AND ? group by factura.fecha, factura.sucursal order by sumacantidades desc" );
		q.setResultClass(FechasDemanda.class);
		q.setParameters(categoria,idSucursal, fechaInicial, fechaFinal);
		return (List<FechasDemanda>) q.executeList();
	}
	
	public List <FechasDemanda> darFechasConMenorDemandaDeSucursal (PersistenceManager pm,long categoria,long idSucursal, Date fechaInicial, Date fechaFinal) {
		Query q = pm.newQuery(SQL, "SELECT factura.fecha,factura.sucursal, sum(productos_factura.cantidad)as sumacantidades  FROM factura, productos_factura, producto WHERE producto.id = productos_factura.idproducto and producto.categoria = ? and factura.sucursal = ? AND factura.id = productos_factura.idfactura AND fecha BETWEEN ? AND ? group by factura.fecha, factura.sucursal order by sumacantidades asc" );
		q.setResultClass(FechasDemanda.class);
		q.setParameters(categoria,idSucursal, fechaInicial, fechaFinal);
		return (List<FechasDemanda>) q.executeList();
	}
	
	public List <FechasIngresos> darFechasConMayoresIngresos (PersistenceManager pm,long categoria,long idSucursal, Date fechaInicial, Date fechaFinal) {
		Query q = pm.newQuery(SQL, "SELECT factura.fecha,factura.sucursal, sum(producto.preciounitario)as sumaprecio  FROM factura, productos_factura, producto WHERE producto.categoria = ? and factura.sucursal = ? AND factura.id = productos_factura.idfactura AND producto.id = productos_factura.idproducto"
				+ " AND fecha BETWEEN ? AND ? group by factura.fecha, factura.sucursal order by sumaprecio desc" );
		q.setResultClass(FechasDemanda.class);
		q.setParameters(categoria ,idSucursal, fechaInicial, fechaFinal);
		return (List<FechasIngresos>) q.executeList();
	}
	
	public List<Factura> darFacturasPorCliente(PersistenceManager pm, Long idCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " +  pp.darTablaFactura() +" WHERE cliente = ?");
		q.setParameters(idCliente);
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}
}
