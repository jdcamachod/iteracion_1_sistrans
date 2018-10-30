package uniandes.superAndes.negocio;

import java.sql.Date;
import java.util.ArrayList;
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
		log.info ("\"Buscando sucursal por nombre: " + resp );
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
		log.info ("\"Buscando Proveedor por nombre: " + resp );
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


	//--------------------------------------------------------------------------
	// Cliente
	//--------------------------------------------------------------------------
	public Cliente adicionarCliente(String nombre, String correoElectronico,double puntos ,  boolean empresa, String tipoDocumento,int numeroDocumento
			, String direccion, String nit)
	{
		log.info("Adicionando Cliente: "+nombre);
		Cliente cliente = null;
		if (empresa) {
		 cliente = pp.adicionarClienteEmpresa(nombre, correoElectronico, puntos, direccion, nit);
		}
		else {
			 cliente = pp.adicionarClientePersona(nombre, correoElectronico, puntos, direccion, nit, tipoDocumento, numeroDocumento);
		}
		log.info("/Adicionando Cliente: "+nombre);
		return cliente;
	}

	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCliente(String correo)
	{
		log.info ("Eliminando Cliente por nombre: " + correo);
		long resp = pp.eliminarClientePorCorreo(correo);		
		log.info ("Eliminando Cliente por nombre: " + resp + " tuplas eliminadas");
		return resp;
	}
		
	public Cliente darClientePorCorreo (String correo) {

		log.info ("Buscando Cliente por correo: " + correo);
		Cliente resp = pp.darClientePorCorreo(correo);
		log.info ("\"Buscando Cliente por correo: " + resp );
		return resp;
	}

	public List<VOCliente> darVOClientes ()
	{
		log.info ("Generando los VO de Clientes");        
		List<VOCliente> vOProveedor = new LinkedList<VOCliente> ();
		for (Cliente tb : pp.darClientes())
		{
			vOProveedor.add(tb);
		}
		log.info ("Generando los VO de Clientes: " + vOProveedor.size() + " existentes");
		return vOProveedor;
	}

	public Empresa darEmpresaPorId(long id) {
		// TODO Auto-generated method stub
		

		log.info ("Buscando Empresa por id: " + id);
		Empresa resp = pp.darEmpresaPorId(id);
		log.info ("\"Buscando Empresa por id: " + id );
		return resp;
		
	}
	
	public Persona darPersonaPorId(long id) {
		// TODO Auto-generated method stub
		

		log.info ("Buscando Persona por id: " + id);
		Persona resp = pp.darPersonaPorId(id);
		log.info ("\"Buscando Persona por id: " + id );
		return resp;
		
	}
	//--------------------------------------------------------------------------
	// Metodos para manejar los productos
	//--------------------------------------------------------------------------
	public Producto adicionarProducto(int cantidad, double cantidadPresentacion, Date fechaVencimiento, String codigoBarras, String marca, int nivelReorden, String nombre, double peso, double precioUnidadMedida, double precioUnitario, String presentacion, double volumen, String unidadMedida, Long idCategoria, Long idProveedor )
	{
		log.info("Adicionando producto: "+nombre);
		Producto producto = pp.adicionarProducto(cantidad, cantidadPresentacion, fechaVencimiento, codigoBarras, marca, nivelReorden, nombre, peso, precioUnidadMedida, precioUnitario, presentacion, volumen, unidadMedida, idCategoria, idProveedor);
		log.info("/Adicionando producto: "+nombre);
		return producto;
	}
	
	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductos(String nombre)
	{
		log.info ("Eliminando productos por nombre: " + nombre);
        long resp = pp.eliminarProductosPorNombre (nombre);		
        log.info ("Eliminando Productos por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public List<Producto> darProductosPorNombre (String nombre) {
		
		log.info ("Buscando Productos por nombre: " + nombre);
		List<Producto> resp = new LinkedList<Producto>();
		resp = (List<Producto>) pp.darProductosPorNombre(nombre);		
        log.info ("\"Buscando Productos por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public List<VOProducto> darVOProductos ()
	{
		log.info ("Generando los VO de Productos");        
        List<VOProducto> voProducto = new LinkedList<VOProducto> ();
        for (Producto tb : pp.darProductos())
        {
        	voProducto.add(tb);
        }
        log.info ("Generando los VO de Productos: " + voProducto.size() + " existentes");
        return voProducto;
	}
	
	public Categoria adicionarCategoria(String nombre)
	{
		log.info("Adicionando categoria: "+nombre);
		Categoria categoria = pp.adicionarCategoria(nombre);
		log.info("/Adicionando categoria: "+nombre);
		return categoria;
	}
	
	public TipoProducto adicionarTipoProducto(String nombre, long idCategoria)
	{
		log.info("Adicionando tipoProducto: "+nombre);
		TipoProducto tipoProducto = pp.adicionarTipoProducto(nombre, idCategoria);
		log.info("/Adicionando tipoProducto: "+nombre);
		return tipoProducto;
	}
	public Categoria darCategoriaPorNombre (String nombre) {
		
		log.info ("Buscando categoria por nombre: " + nombre);
		Categoria resp = pp.darCategoriaPorNombre(nombre);		
        log.info ("\"Buscando categoria por nombre: " + resp + " tuplas eliminadas");
        return  resp;
	}
	
	public TipoProducto darTipoProductoPorNombre (String nombre) {
		
		log.info ("Buscando tipo producto por nombre: " + nombre);
		TipoProducto resp = pp.darTipoProductoPorNombre(nombre);		
        log.info ("\"Buscando tipoProducto por nombre: " + resp + " tuplas eliminadas");
        return  resp;
	}
	
	public Promocion adicionarDescuentoPorcentaje(double porcentaje, Date fechaInicial, Date fechaFinal, Long idProducto)
	{
		log.info("Adicionando promocion de tipo descuento porcentaje ");
		double prp= pp.darProductoPorId(idProducto).getPrecioUnitario();
		double precio = (prp*porcentaje)/100;
		DescuentoPorcentaje descuentoPorcentaje = pp.adicionarDescuentoPorcentaje(porcentaje);
		Promocion promocion =  pp.adicionarPromocion(fechaInicial, fechaFinal, null, descuentoPorcentaje, null, null, precio);
		return promocion;
		
	}
	
	
	
	
	

}
