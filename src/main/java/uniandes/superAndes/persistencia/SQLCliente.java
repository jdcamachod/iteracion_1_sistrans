package uniandes.superAndes.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Producto;

class SQLCliente {

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
	public SQLCliente (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Adiciona la tupla de un nuevo cliente en la base de datos
	 * @param pm manejador de persistencia
	 * @param idCliente
	 * @param nombre
	 * @param correoElectronico
	 * @param puntos
	 * @return
	 */
	public long adicionarCliente (PersistenceManager pm, long idCliente, String nombre, String correoElectronico, double puntos, Long idEmpresa, Long idPersona) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente () + "(id, nombre, correoElectronico, puntos, empresa, personanatural) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idCliente, nombre, correoElectronico, puntos, idEmpresa, idPersona);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CLIENTES de la base de datos de SuperAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCliente - El nombre del cliente
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorCorreo (PersistenceManager pm, String correoCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente()  + " WHERE correoelectronico = ?");
        q.setParameters(correoCliente);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN CLIENTE de la base de datos de SuperAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del cliente
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorId (PersistenceManager pm, long idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE id = ?");
        q.setParameters(idCliente);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN CLIENTE de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del cliente
	 * @return El objeto PRODUCTO que tiene el identificador dado
	 */
	public Cliente darClientePorId (PersistenceManager pm, long idCLiente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente()  + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idCLiente);
		return (Cliente) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES de la 
	 * base de datos de SuperAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCliente - El nombre de cliente buscado
	 * @return Una lista de objetos CLIENTES que tienen el nombre dado
	 */
	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombreCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente()  + " WHERE nombre = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombreCliente);
		return (List<Cliente>) q.executeList();
	}
	
	
	public Cliente darClientesPorCorreo (PersistenceManager pm, String correoCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente()  + " WHERE correoelectronico = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(correoCliente);
		return (Cliente) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CLIENTE
	 */
	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() );
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientesComsumoSuperAndes (PersistenceManager pm, Date fechaInicial, Date fechaFinal, String nombreProd, String param)
	{
		Query q = pm.newQuery(SQL, "SELECT distinct(c.id), c.correoelectronico, c.puntos, c.personanatural,c.nombre, c.empresa\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"WHERE p.nombre = ? AND  F.fecha BETWEEN ? AND ? ORDER BY ?");
		q.setParameters(nombreProd, fechaInicial, fechaFinal, param);
		q.setResultClass(Cliente.class);
		return (List<Cliente>)q.executeList();
		
	}
	
	public List<Cliente> darClientesComsumoSuperAndesv2 (PersistenceManager pm, Date fechaInicial, Date fechaFinal, String nombreProd, String param)
	{
		Query q = pm.newQuery(SQL, "SELECT distinct(c.id), c.correoelectronico, c.puntos,c.nombre, c.personanatural, c.empresa\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id <> f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"WHERE p.nombre = ? AND  F.fecha BETWEEN ? AND ? ORDER BY ?");
		q.setParameters(nombreProd, fechaInicial, fechaFinal, param);
		q.setResultClass(Cliente.class);
		return (List<Cliente>)q.executeList();
		
	}
	
	public List<Cliente> darClientesComsumoSuperAndesGerente (PersistenceManager pm, Date fechaInicial, Date fechaFinal, String nombreProd, String param, Long sucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT distinct(c.id),c.nombre , c.correoelectronico, c.puntos, c.personanatural, c.empresa\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"INNER JOIN SUCURSAL S ON s.id = f.sucursal"
				+ "WHERE p.nombre = ? AND  F.fecha BETWEEN ? AND ? AND s.id = ? "
				+ " ORDER BY ?");
		q.setParameters(nombreProd, fechaInicial, fechaFinal, sucursal, param);
		q.setResultClass(Cliente.class);
		return (List<Cliente>)q.executeList();
		
	}
	
	
	public List<Cliente> encontrarLosClientesFrecuentesDeLaSucursal (PersistenceManager pm,long idSucursal) {
		//select cliente.id FROM cliente, factura WHERE cliente.id = factura.cliente group by cliente.id having count(cliente.nombre) > 2
		Query q = pm.newQuery(SQL, "SELECT cliente.id FROM " + pp.darTablaCliente() +", " + pp.darTablaFactura() + " WHERE factura.sucursal = ? AND cliente.id = factura.cliente group by cliente.id having count(cliente.nombre) > 2");
		q.setResultClass(Long.class);
		q.setParameters(idSucursal);
		List<Long> clientes = (List<Long>)    q.executeList();
		List<Cliente> losClientes = new ArrayList<Cliente>();
		for (int i = 0; i < clientes.size(); i++) {
			
			losClientes.add(darClientePorId(pm, clientes.get(i).longValue()));
		}
		
		return losClientes;
				
	}
	
	public List<Cliente> darClientesConProductoMay(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT  distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"WHERE p.preciounitario > 100000");
		q.setResultClass(Cliente.class);
		return(List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientesHerrYTec(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT  distinct(c.id), c.correoelectronico, c.puntos, c.personanatural, c.empresa\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"WHERE p.categoria = 14 OR p.categoria = 1");
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientesCompraMes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT  Distinct(c.id),COUNT(*)\r\n" + 
				"FROM CLIENTE C INNER JOIN FACTURA F ON c.id = f.cliente \r\n" + 
				"INNER JOIN PRODUCTOS_FACTURA PF ON pf.idfactura = f.id\r\n" + 
				"INNER JOIN PRODUCTO P ON p.id = pf.idproducto\r\n" + 
				"WHERE f.fecha BETWEEN '01/01/2018'AND '31/12/2018'\r\n" + 
				"GROUP BY c.id\r\n" + 
				"HAVING COUNT(c.id)>12");
		q.setResultClass(Cliente.class);
		return (List<Cliente>)q.executeList();
	}
}
