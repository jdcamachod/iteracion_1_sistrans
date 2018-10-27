package uniandes.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;


import uniandes.superAndes.persistencia.PersistenciaSuperAndes;


public class SuperAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		pp = PersistenciaSuperAndes.getInstance ();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperAndes.getInstance (tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	/* ****************************************************************
	 * 			Métodos para manejar las SUCURSALES
	 *****************************************************************/	
	
	/**
	 * Adiciona de manera persistente una sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param nombre nombre de la saucursal
	 * @param direccion direccion asociada a la sucursal
	 * @param tamano tamaño de la sucursal
	 * @param ciudad ciudad en que se encuentra la sucursal
	 * @return El objeto Sucursal adicionado. null si ocurre alguna Excepción
	 */
	public Sucursal adicionarSucursal (String nombre, String direccion, double tamano, String ciudad)
	{
        log.info ("Adicionando Tipo de bebida: " + nombre);
        Sucursal sucursal = pp.adicionarSucursal(nombre, direccion, tamano, ciudad)	;	
        log.info ("Adicionando sucursal: " + sucursal);
        return sucursal;
	}
	
	
	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarSucursal (String nombre)
	{
		log.info ("EliminandoSucursal por nombre: " + nombre);
        long resp = pp.eliminarSucursalPorNombre (nombre);		
        log.info ("EliminandoSucursal por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Sucursal darSucursalPorNombre (String nombre) {
		
		log.info ("Buscando sucursal por nombre: " + nombre);
		Sucursal resp = pp.darSucursalPorNombre(nombre);		
        log.info ("\"Buscando sucursal por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public List<VOSucursal> darVOSucursales ()
	{
		log.info ("Generando los VO de Sucursales");        
        List<VOSucursal> voSucursales = new LinkedList<VOSucursal> ();
        for (Sucursal tb : pp.darSucursales())
        {
        	voSucursales.add(tb);
        }
        log.info ("Generando los VO de Sucursales: " + voSucursales.size() + " existentes");
        return voSucursales;
	}
	
	//--------------------------------------------------------------------------
	// Proveedores
	//--------------------------------------------------------------------------
	public Proveedor adicionarProveedor(String nombre, String nit, String calificacion)
	{
		log.info("Adicionando proveedor: "+nombre);
		Proveedor proveedor = pp.adicionarProveedor(nombre, nit, calificacion);
		log.info("/Adicionando proveedor: "+nombre);
		return proveedor;
	}
	
	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedor(String nombre)
	{
		log.info ("EliminandoProveedor por nombre: " + nombre);
        long resp = pp.eliminarProveedorPorNombre (nombre);		
        log.info ("EliminandoProveedor por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Proveedor darProveedorPorNombre (String nombre) {
		
		log.info ("Buscando Proveedor por nombre: " + nombre);
		Proveedor resp = pp.darProveedorPorNombre(nombre);		
        log.info ("\"Buscando Proveedor por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public List<VOProveedor> darVOProveedores ()
	{
		log.info ("Generando los VO de Proveedores");        
        List<VOProveedor> vOProveedor = new LinkedList<VOProveedor> ();
        for (Proveedor tb : pp.darProveedores())
        {
        	vOProveedor.add(tb);
        }
        log.info ("Generando los VO de Proveedor: " + vOProveedor.size() + " existentes");
        return vOProveedor;
	}
	

}
