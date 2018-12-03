package uniandes.superAndes.negocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;


import uniandes.superAndes.persistencia.PersistenciaSuperAndes;


public class SuperAndes implements Runnable {

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

	public List<VOPromocion> darVOPromociones ()
	{
		log.info ("Generando los VO de Promociones");        
		List<VOPromocion> voPromociones = new LinkedList<VOPromocion> ();
		List<Promocion> promos = pp.darPromociones();
		log.info("Verificando las promociones finalizadas");
		pp.verificarPromociones(promos);
		log.info("/Verificando las promociones finalizadas");
		for (Promocion tb : promos)
		{
			voPromociones.add(tb);
		}
		log.info ("Generando los VO de Promociones: " + voPromociones.size() + " existentes");
		return voPromociones;
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

	public Producto darProductoPorNombreYProveedor (String nombre, long idProveedor) {

		log.info ("Buscando Producto por nombre y idPorveedor: " + nombre + " proveedor: " + idProveedor);

		Producto resp = pp.darProductoPorNombreYProveedor(nombre, idProveedor);	
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
		log.info("Buscando producto por id");
		double prp= pp.darProductoPorId(idProducto).getPrecioUnitario();
		log.info("/Buscando producto por id");
		double precio = prp -(prp*porcentaje)/100;
		DescuentoPorcentaje descuentoPorcentaje = pp.adicionarDescuentoPorcentaje(porcentaje);
		Promocion promocion =  pp.adicionarPromocion(fechaInicial, fechaFinal, null, descuentoPorcentaje, null, null, precio, idProducto);
		log.info("/Adicionando promocion de tipo descuento porcentaje");
		return promocion;

	}

	public Promocion adicionarPague1Lleve2Porcentaje(double porcentaje, Date fechaInicial, Date fechaFinal, Long idProducto)
	{
		log.info("Adicionando promocion de tipo descuento porcentaje en segundo articulo llevando 1 ");
		log.info("Buscando producto por id");
		double prp= pp.darProductoPorId(idProducto).getPrecioUnitario();
		log.info("/Buscando producto por id");
		double precio = prp +(prp*porcentaje)/100;
		Pague1Lleve2Porcentaje pague1Lleve2 = pp.adicionarPague1Lleve2Porcentaje(porcentaje);
		Promocion promocion =  pp.adicionarPromocion(fechaInicial, fechaFinal, null, null, null, pague1Lleve2, precio, idProducto);
		log.info("/Adicionando promocion de tipo descuento porcentaje en segundo articulo llevando 1");
		return promocion;

	}

	public Promocion adicionarPagueNLleveM(int n,int m, Date fechaInicial, Date fechaFinal, Long idProducto)
	{
		log.info("Adicionando promocion de tipo pague n lleve m ");
		log.info("Buscando producto por id");
		double prp= pp.darProductoPorId(idProducto).getPrecioUnitario();
		log.info("/Buscando producto por id");
		double precio = n*prp;
		PagueNLleveM pagueNLleveM = pp.adicionarPagueNLleveM(n, m);
		Promocion promocion =  pp.adicionarPromocion(fechaInicial, fechaFinal, pagueNLleveM, null, null, null, precio, idProducto);
		log.info("/Adicionando promocion de tipo pague n lleve m");
		return promocion;

	}
	public Promocion adicionarPagueXLleveY(int x,int y, Date fechaInicial, Date fechaFinal, Long idProducto)
	{
		log.info("Adicionando promocion de tipo pague x lleve y ");
		log.info("Buscando producto por id");
		double prp= pp.darProductoPorId(idProducto).getPrecioUnidadMedida();
		log.info("/Buscando producto por id");
		double precio = x*prp;
		PagueXLleveY paguexLlevey = pp.adicionarPagueXLleveY(x,y);
		Promocion promocion =  pp.adicionarPromocion(fechaInicial, fechaFinal, null, null, paguexLlevey, null, precio, idProducto);
		log.info("/Adicionando promocion de tipo pague x lleve y");
		return promocion;

	}

	public Producto darProductoPorId (long id) {

		log.info ("Buscando producto por id: " + id);
		Producto resp = pp.darProductoPorId(id);		
		log.info ("\"Buscando producto por id: " + resp );
		return  resp;
	}


	//--------------------------------------------------------------------------
	// Estante
	//--------------------------------------------------------------------------
	public Estante adicionarEstante( String nombreCategoria, String direccion, double peso, double volumen,String nombreSucursal, int nivelAbastecimiento) 
	{
		log.info("Adicionando bodega con direccion : "+direccion);

		
		long idSucursal = darSucursalPorNombre(nombreSucursal).getId();
		Categoria cat = darCategoriaPorNombre(nombreCategoria);
		if(cat==null)
		{
			cat =adicionarCategoria(nombreCategoria);
		}


		Estante estante = pp.adicionarEstante(cat.getId(), direccion, peso, volumen, idSucursal, nivelAbastecimiento);
		log.info("/Adicionando bodega con direccion: "+direccion);
		return estante;
	}

	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param direccion - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEstante(String direccion)
	{
		log.info ("Eliminando Bodega por direccion: " + direccion);
		long resp = pp.eliminarEstantePorDireccion(direccion);
		log.info ("Eliminando Bodega por direccion: " + resp + " tuplas eliminadas");
		return resp;
	}

	public Estante darEstantePorDireccion (String direccion) {

		log.info ("Buscando Estante por direccion: " + direccion);
		Estante resp = pp.darEstantePorDireccion(direccion);
		log.info ("\"Buscando Estante por direccion: " + resp );
		return resp;
	}

	public List<VOEstante> darVOEstantes ()
	{
		log.info ("Generando los VO de Estante");        
		List<VOEstante> vOProveedor = new LinkedList<VOEstante> ();
		for (Estante tb : pp.darEstantes())
		{
			vOProveedor.add(tb);
		}
		log.info ("Generando los VO de Estante: " + vOProveedor.size() + " existentes");
		return vOProveedor;
	}
	
	public List<VOCategoria> darVOCategoria ()
	{
		log.info ("Generando los VO de categoria");        
		List<VOCategoria> vOCategoria = new LinkedList<VOCategoria> ();
		for (Categoria tb : pp.darCategorias())
		{
			vOCategoria.add(tb);
		}
		log.info ("Generando los VO de categoria: " + vOCategoria.size() + " existentes");
		return vOCategoria;
	}

	public ProductosEstantes asociarEstanteProducto(Producto producto, Estante estante, int cantidad)
	{
		log.info("Adicionando un nuevo ProductosEstantes");
		ProductosEstantes relacion = pp.asociarEstanteProducto(estante.getId(), producto.getId(), cantidad);
		log.info("/Adicionando un nuevo ProductosEstantes");
		return relacion;
	}
	public List<VOEstante> darVOEstantesPorSucursal (Long sucursal)
	{
		log.info ("Generando los VO de Estante");        
		List<VOEstante> vOProveedor = new LinkedList<VOEstante> ();
		for (Estante tb : pp.darEstantesPorSucursal(sucursal))
		{
			vOProveedor.add(tb);
		}
		log.info ("Generando los VO de Estante: " + vOProveedor.size() + " existentes");
		return vOProveedor;
	}
	
	public List<Producto> darProductosPorEstante (Long estante)
	{
		log.info ("Generando los VO de Producto");        
		List<Producto> productos = new LinkedList<Producto> ();
		for (ProductosEstantes tb : pp.darProductosEstantesPorEstante(estante))
		{
			productos.add(pp.darProductoPorId(tb.getIdProducto()));
		}
		log.info ("Generando los VO de Producto: " + productos.size() + " existentes");
		return productos;
	}





	//--------------------------------------------------------------------------
	// Bodega
	//--------------------------------------------------------------------------
	public Bodega adicionarBodega( String nombreTipo, String direccion, double peso, double volumen,String nombreSucursal)
	{
		log.info("Adicionando bodega con direccion : "+direccion);


		long idSucursal = darSucursalPorNombre(nombreSucursal).getId();
		Categoria cat = darCategoriaPorNombre("PRUEBA");
		if(cat==null)
		{
			cat =adicionarCategoria("PRUEBA");
		}


		Bodega bodega = pp.adicionarBodega(cat.getId() , direccion, peso, volumen, idSucursal);
		log.info("/Adicionando bodega con direccion: "+direccion);
		return bodega;
	}

	/**
	 * Elimina un Sucursal por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param direccion - El nombre del Sucursal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarBodega(String direccion)
	{
		log.info ("Eliminando Bodega por direccion: " + direccion);
		long resp = pp.eliminarBodegaPorDireccion(direccion);
		log.info ("Eliminando Bodega por direccion: " + resp + " tuplas eliminadas");
		return resp;
	}

	public Bodega darBodegaPorDireccion (String direccion) {

		log.info ("Buscando Bodega por direccion: " + direccion);
		Bodega resp = pp.darBodegaPorDireccion(direccion);
		log.info ("\"Buscando Bodega por direccion: " + resp );
		return resp;
	}

	public List<VOBodega> darVOBodegas ()
	{
		log.info ("Generando los VO de Bodega");        
		List<VOBodega> vOProveedor = new LinkedList<VOBodega> ();
		for (Bodega tb : pp.darBodegas())
		{
			vOProveedor.add(tb);
		}
		log.info ("Generando los VO de Proveedor: " + vOProveedor.size() + " existentes");
		return vOProveedor;
	}



	public OrdenPedido solicitarProductoProveedor (String nombreProveedor, String nombreProducto, Date fechaEsperadaDeEntrega, double precioProveedor, int cantidad) {
		log.info ("Creando solicitud de orden a proveedor");  
		long idProveedor = darProveedorPorNombre(nombreProveedor).getId();
		long idProducto=  darProductoPorNombreYProveedor(nombreProducto, idProveedor).getId();
		OrdenPedido orden = pp.solicitarProductoProveedor(idProveedor, idProducto, fechaEsperadaDeEntrega, precioProveedor, cantidad);
		log.info("solicitud de orden a proveedor creada");
		return orden;

	}

	public long llegadaOrdenPedido (long idOrden ,double calificacionPedido, int estado, Date fechaEntrega) throws Exception 
	{

		OrdenPedido orden = pp.darOrdenPedidoProveedor(idOrden);
		if (orden == null) {
			throw new Exception("La orden no es valida o no existe");
		}
		List <Producto>  productos = pp.darProductosOrdenPedido(idOrden);
		return 		pp.llegadaOrdenPedido(orden, calificacionPedido, estado, fechaEntrega, productos);

	}

	public CarritoCompras solicitarCarrito(Long idCliente)
	{
	
		
			log.info("Solicitando carrito para el cliente: "+pp.darClientePorId(idCliente).getNombre());
			List<CarritoCompras> carritos = pp.darCarritosSinClientes();
			CarritoCompras carrito = null;
			java.util.Date fecha = new java.util.Date();
			System.out.println(carritos.isEmpty());
			if(carritos==null || carritos.isEmpty())
			{
				carrito = pp.adicionarCarrito(idCliente, fecha);
			}
			else {
				carrito = carritos.get(0);
				
				long id = carrito.getId(); 
				pp.solicitarCarrito(fecha, idCliente, id);
				carrito = pp.darCarritoPorId(id);
				
			}
			log.info("/Solicitando carrito para el cliente: "+pp.darClientePorId(idCliente).getNombre());
			return carrito;
		
		
	}
	public CarritoCompras eliminarClienteCarrito(CarritoCompras carrito)
	{
		carrito = pp.solicitarCarrito(null, null, carrito.getId());
		return carrito;
	}

	public CarritoCompras darCarritoPorCliente (Long idCliente) {

		log.info ("Buscando carrito por idCliente: " + idCliente);
		CarritoCompras resp = pp.darCarritoPorCliente(idCliente);		
		log.info ("\"Buscando carrito por idCliente: " + resp );
		return  resp;
	}

	public String consultarFuncionamiento(int anio)
	{
		java.sql.Date date;
		java.sql.Date datea;
		Calendar c = Calendar.getInstance();
		c.set(anio, 12, 31);
		Calendar d =  Calendar.getInstance();
		d.set(anio, 1,1);
		String respuesta ="";
		int semana = 1;
		while(d.compareTo(c)<=0)
		{
			respuesta+="Semana "+semana+"\n";
			datea=new java.sql.Date(d.getTime().getTime());
			d.add(Calendar.WEEK_OF_YEAR, 1);
			date = new java.sql.Date(d.getTime().getTime());
			List<Consulta> consultas=pp.darIdentificadoresExtremosProductos(datea, date);
			List<Consulta> proveedores =pp.darProveedoresSemana(datea, date);
			respuesta+="Productos menos vendidos= \n";
			int cuenta=-1;
			int cuenta2 = -1;
			if(!consultas.isEmpty())
				{
					cuenta=consultas.get(0).getCuenta();
				}
			if(!proveedores.isEmpty())
			{
				cuenta2=proveedores.get(0).getCuenta();
			}
			for(Consulta con: consultas)
			{
				if(con.getCuenta()!=cuenta)
				{
					respuesta+="Productos mas vendidos= \n";
					cuenta = con.getCuenta();
				}
				 respuesta +="numero vendido "+con.getCuenta()+", nombre producto "+con.getNombre()+"\n";
				
			}
			respuesta+="Proveedores menos solicitados: \n";
			for(Consulta con: proveedores)
			{
				if(con.getCuenta()!=cuenta)
				{
					respuesta+="Proveedores mas solicitados= \n";
					cuenta = con.getCuenta();
				}
				 respuesta +="numero de solicitaciones "+con.getCuenta()+", nombre proveedor "+con.getNombre()+"\n";
				
			}
			semana++;
			
		}
		return respuesta;
		
		
		
	}
	public String analizarLaOperacionDeSuperAndes(String categoria, Date fechaInicial, Date fechaFinal) {

		List<Sucursal> sucursales = pp.darSucursales();
		List<FechasDemanda> fechasDemanda = null;
		List<FechasDemanda> fechasMenorDemanda = null;
		List<FechasIngresos> fechasIngresos = null;
		String resp = "";
		Categoria laCategoria = pp.darCategoriaPorNombre(categoria);

		for (int i = 0; i < sucursales.size(); i++) {
			 fechasDemanda = pp.darFechasConDemandaDeSucursal(sucursales.get(i).getId(), fechaInicial, fechaFinal, laCategoria.getId());
			 for (int j = 0; j < fechasDemanda.size(); j++) {
				resp += fechasDemanda.get(j).toString();
			}
		}
		
		for (int i = 0; i < sucursales.size(); i++) {
			  fechasMenorDemanda = pp.darFechasConMenorDemandaDeSucursal(sucursales.get(i).getId(), fechaInicial, fechaFinal, laCategoria.getId());
				 for (int j = 0; j < fechasMenorDemanda.size(); j++) {
						resp += fechasMenorDemanda.get(j).toString();
					}
		}
		
		for (int i = 0; i < sucursales.size(); i++) {
			 fechasIngresos = pp.darFechasConIngresosDeSucursal(sucursales.get(i).getId(), fechaInicial, fechaFinal, laCategoria.getId());
			 for (int j = 0; j < fechasIngresos.size(); j++) {
					resp += fechasIngresos.get(j).toString();
				}
		}
		
		
		return resp;
	}
	
	public List<Cliente> encontrarLosClientesFrecuentes (String sucursal){
	
		return pp.encontrarLosClientesFrecuentesDeLaSucursal(pp.darSucursalPorNombre(sucursal).getId());
		
	}
	public List<Cliente> darClientesConsumoSuperAndes(Date fechaInicial, Date fechaFinal,String nombreProd, String param)
	{
		return pp.darClientesConsumoSuperAndes(fechaInicial, fechaFinal, nombreProd, param);
	}
	
	public List<Cliente> darClientesConsumoSuperAndesv2(Date fechaInicial, Date fechaFinal,String nombreProd, String param)
	{
		return pp.darClientesConsumoSuperAndesv2(fechaInicial, fechaFinal, nombreProd, param);
	}
	
	
	
	
	public void vaciarCarritosAbandonados()
	{
		System.out.println("revisando carritos abandonados");
		for(CarritoCompras carrito: pp.darCarritosSinClientes())
		{
		
			System.out.println(darProductosPorCarrito(carrito.getId()).isEmpty());
			for(Producto producto: darProductosPorCarrito(carrito.getId()))
			{
				CarritoProductos relacion = darCarritoProducto(producto.getId(), carrito.getId());
				boolean eliminado= eliminarProductoCarrito(carrito.getId(), producto.getId());
				
				if(eliminado)
				{
					
					
					devolverCantidadEstante(relacion.getCantidad(), producto.getId(), relacion.getIdEstante());
					System.out.println("Se elimino el producto "+producto.getNombre()+ " del carrito "+carrito.getId());
				}
				else {
					System.out.println("ocurrio un error eliminando los productos de un carrito");
				}
			}
		}
		
	}
	
	public CarritoProductos adicionarProductoACarrito(Producto producto, Estante estante, int cantidad, CarritoCompras carrito)
	{
		log.info("Adicionando un nuevo CarritoProductos");
		CarritoProductos relacion = pp.adicionarProductoACarrito(carrito.getId(), producto.getId(), cantidad, estante.getId());
		log.info("/Adicionando un nuevo CarritoProductos");
		return relacion;
	}
	
	public ProductosEstantes darProductoEstante(Long producto, Long estante)
	{
		log.info("Buscando una relacion de tipo ProductoEstante");
		ProductosEstantes relacion = pp.darProductoEstante(producto, estante);
		log.info("/Buscando una relacion de tipo ProductoEstante");
		return relacion;
	}
	
	public void restarCantidadEstante(int cantidad, Long idProducto, Long idEstante)
	{
		log.info("Restando "+cantidad+" cantidad de productos "+idProducto+" del estante +"+idEstante);
		pp.restarCantidadEstante(cantidad, idProducto, idEstante);
		log.info("/Restando "+cantidad+" cantidad de productos "+idProducto+" del estante +"+idEstante);
	}
	
	public void restarCantidadProductos(int cantidad, Long idProducto)
	{
		log.info("Restando "+cantidad+" cantidad de productos "+idProducto );
		pp.restarCantidadProductos(cantidad, idProducto);
		log.info("/Restando "+cantidad+" cantidad de productos "+idProducto);
	}
	
	public void devolverCantidadEstante(int cantidad, Long idProducto, Long idEstante)
	{
		log.info("Sumando "+cantidad+" cantidad de productos "+idProducto+" del estante +"+idEstante);
		pp.devolverCantidadEstante(cantidad, idProducto, idEstante);
		log.info("/Sumando "+cantidad+" cantidad de productos "+idProducto+" del estante +"+idEstante);
	}
	
	public List<Producto> darProductosPorCarrito(Long idCarrito)
	{
		log.info("Consultando los productos en el carrito " +idCarrito );
		List<Producto> productos = pp.darProductosPorCarrito(idCarrito);
		log.info("/Consultando los productos en el carrito "+idCarrito);
		return productos;
	}
	
	public boolean eliminarProductoCarrito(Long idCarrito, Long idProducto)
	{
		log.info("Eliminando el producto "+ idProducto+" del carrito "+idCarrito);
		boolean eliminado = pp.eliminarProductoCarrito(idProducto, idCarrito);
		log.info("/Eliminando el producto "+ idProducto +" del carrito "+idCarrito);
		return eliminado;
	}
	public void eliminarProductoEstantes(Long idEstante, Long idProducto)
	{
		log.info("Eliminando el producto "+ idProducto+" del estante "+idEstante);
		 pp.eliminarProductoEstante(idProducto, idEstante);
		log.info("/Eliminando el producto "+ idProducto +" del estante "+idEstante);
		
	}
	public void quitarUnidadesProducto(Long idProducto, Long idCarrito, int cantidad)
	{
		log.info("Quitando "+ cantidad+" unidades del producto "+idProducto+" del carrito "+idCarrito);
		pp.quitarUnidadesCarrito(idProducto, idCarrito, cantidad);
		log.info("/Quitando "+ cantidad+" unidades del producto "+idProducto+" del carrito "+idCarrito);
	}
	public CarritoProductos darCarritoProducto(Long idProducto, Long idCarrito)
	{
		return pp.darCarritoProducto(idCarrito, idProducto);
	}
	
	public List<Factura> darFacturasPorCliente(Long idCliente)
	{
		log.info("Consultando las facturas por cliente "+idCliente );
		List<Factura> facturas =pp.darFacturasPorCliente(idCliente);
		log.info("/Consultando las facturas por cliente "+idCliente );
		return facturas;
	}
	
	
	public Factura adicionarFactura( java.util.Date fecha, double costoTotal, Long cliente, Long sucursal) 
	{
		log.info("Adicionando factura con cliente : "+cliente+ " y fecha: "+fecha);


		Factura factura = pp.adicionarFactura(fecha, costoTotal, cliente, sucursal);
		log.info("/Adicionando factura con cliente : "+cliente+ " y fecha: "+fecha);
		return factura;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pp.verificarPromociones(pp.darPromociones());
		vaciarCarritosAbandonados();
	}





}
