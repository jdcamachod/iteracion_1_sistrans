package uniandes.superAndes.persistencia;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.DescuentoPorcentaje;
import uniandes.superAndes.negocio.OrdenPedido;
import uniandes.superAndes.negocio.OrdenesProductos;
import uniandes.superAndes.negocio.Pague1Lleve2Porcentaje;
import uniandes.superAndes.negocio.PagueNLleveM;
import uniandes.superAndes.negocio.PagueXLleveY;
import uniandes.superAndes.negocio.Promocion;







public class PersistenciaSuperAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON
	 */
	private static PersistenciaSuperAndes instance;

	/**
	 * F�brica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	/**
	 * Atributo para el acceso a la tabla BODEGA de la base de datos
	 */
	private SQLBodega sqlBodega;

	/**
	 * Atributo para el acceso a la tabla CATEGORIA de la base de datos
	 */
	private SQLCategoria sqlCategoria;

	/**
	 * Atributo para el acceso a la tabla CLIENTE de la base de datos
	 */
	private SQLCliente sqlCliente;

	/**
	 * Atributo para el acceso a la tabla DESCUENTO_PORCENTAJE de la base de datos
	 */
	private SQLDescuentoPorcentaje sqlDescuentoPorcentaje;

	/**
	 * Atributo para el acceso a la tabla EMPRESA de la base de datos
	 */
	private SQLEmpresa sqlEmpresa;

	/**
	 * Atributo para el acceso a la tabla ESTANTE de la base de datos
	 */
	private SQLEstante sqlEstante;

	/**
	 * Atributo para el acceso a la tabla FACTURA de la base de datos
	 */
	private SQLFactura sqlFactura;

	/**
	 * Atributo para el acceso a la tabla ORDENES_PRODUCTO de la base de datos
	 */
	private SQLOrdenesProductos sqlOrdenesProducto;

	/**
	 * Atributo para el acceso a la tabla ORDEN_PEDIDO de la base de datos
	 */
	private SQLOrdenPedido sqlOrdenPedido;

	/**
	 * Atributo para el acceso a la tabla PAGUE_1_LLEVE_2_PORCENTAJE de la base de datos
	 */
	private SQLPague1Lleve2Porcentaje sqlPague1Lleve2Porcentaje;

	/**
	 * Atributo para el acceso a la tabla PAGUE_N_LLEVE_M de la base de datos
	 */
	private SQLPagueNLleveM sqlPagueNLleveM;

	/**
	 * Atributo para el acceso a la tabla PAGUE_X_LLEVE_Y de la base de datos
	 */
	private SQLPagueXLleveY sqlPagueXLleveY;

	/**
	 * Atributo para el acceso a la tabla PERSONA de la base de datos
	 */
	private SQLPersona sqlPersona;

	/**
	 * Atributo para el acceso a la tabla PRODUCTO de la base de datos
	 */
	private SQLProducto sqlProducto;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOS_BODEGAS de la base de datos
	 */
	private SQLProductosBodegas sqlProductosBodegas;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOS_ESTANTES de la base de datos
	 */
	private SQLProductosEstantes sqlProductosEstantes;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOS_ESTANTES de la base de datos
	 */
	private SQLProductosFacturas sqlProductosFacturas;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOS_PROMOCIONES de la base de datos
	 */
	private SQLProductosPromociones sqlProductosPromociones;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOS_PROVEEDORES de la base de datos
	 */
	private SQLProductosProveedores sqlProductosProveedores;

	/**
	 * Atributo para el acceso a la tabla PROMOCION de la base de datos
	 */
	private SQLPromocion sqlPromocion;

	/**
	 * Atributo para el acceso a la tabla PROMOCIONES_FACTURAS de la base de datos
	 */
	private SQLPromocionesFacturas sqlPromocionesFacturas;

	/**
	 * Atributo para el acceso a la tabla PROVEEDOR de la base de datos
	 */
	private SQLProveedor sqlProveedor;

	/**
	 * Atributo para el acceso a la tabla SUCURSAL de la base de datos
	 */
	private SQLSucursal sqlSucursal;

	/**
	 * Atributo para el acceso a la tabla SUCURSALES_CLIENTES de la base de datos
	 */
	private SQLSucursalesClientes sqlSucursalesClientes;

	/**
	 * Atributo para el acceso a la tabla TIPO_PRODUCTO de la base de datos
	 */
	private SQLTipoProducto sqlTipoProducto;

	/**
	 * Atributo para el acceso a la tabla PROMOCION_PAQUETE de la base de datos
	 */
	private SQLPromocionPaquete sqlPromocionPaquete;


	/* ****************************************************************
	 * 			M�todos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patr�n SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("superAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();

		tablas.add ("SuperAndes_sequence");
		tablas.add ("SUCURSAL");
		tablas.add ("PROVEEDOR");
		tablas.add ("EMPRESA");
		tablas.add ("PERSONA_NATURAL");
		tablas.add ("CLIENTE");
		tablas.add ("BODEGA");
		tablas.add ("CATEGORIA");
		tablas.add ("TIPO_PRODUCTO");
		tablas.add ("DESCUENTO_PORCENTAJE");
		tablas.add ("PAGUE_1_LLEVE_SEG_PORCENTAJE");
		tablas.add ("PAGUE_N_LLEVE_M");
		tablas.add ("PAGUE_X_LLEVE_Y");
		tablas.add ("PROMOCION");
		tablas.add ("ESTANTE");
		tablas.add ("FACTURA");
		tablas.add ("PRODUCTO");
		tablas.add ("ORDEN_PEDIDO");
		tablas.add ("PRODUCTOS_ORDEN");
		tablas.add ("PRODUCTOS_BODEGA");
		tablas.add ("PRODUCTOS_ESTANTE");
		tablas.add ("PRODUCTOS_FACTURA");
		tablas.add ("PROMOCIONES_PRODUCTOS");
		tablas.add ("PRODUCTOS_PROVEEDOR");
		tablas.add ("PROMOCIONES_FACTURAS");
		tablas.add ("CLIENTES_SUCURSALES");
		tablas.add("PROMOCION_PAQUETE");
	}
	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr�n SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexi�n con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlSucursal = new SQLSucursal(this);
		sqlProveedor = new SQLProveedor(this);
		sqlEmpresa = new SQLEmpresa(this);
		sqlPersona = new SQLPersona(this);
		sqlCliente = new SQLCliente(this);
		sqlBodega = new SQLBodega(this);
		sqlCategoria = new SQLCategoria(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlPague1Lleve2Porcentaje = new SQLPague1Lleve2Porcentaje(this);
		sqlPagueNLleveM = new SQLPagueNLleveM(this);
		sqlPagueXLleveY = new SQLPagueXLleveY(this);
		sqlDescuentoPorcentaje = new SQLDescuentoPorcentaje(this);
		sqlEstante = new SQLEstante (this);
		sqlFactura = new SQLFactura(this);		
		sqlProducto = new SQLProducto(this);
		sqlPromocion = new SQLPromocion(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlOrdenesProducto = new SQLOrdenesProductos(this);
		sqlProductosBodegas = new SQLProductosBodegas(this);
		sqlProductosEstantes = new SQLProductosEstantes(this);
		sqlProductosFacturas = new SQLProductosFacturas(this);
		sqlProductosPromociones = new SQLProductosPromociones(this);
		sqlProductosProveedores = new SQLProductosProveedores(this);
		sqlProductosProveedores = new SQLProductosProveedores(this);
		sqlPromocionesFacturas = new SQLPromocionesFacturas(this);
		sqlPromocionPaquete= new SQLPromocionPaquete(this);
		sqlSucursalesClientes= new SQLSucursalesClientes(this);
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de superAndes
	 */
	public String darSeqSuperAndes ()
	{
		return tablas.get(0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sucursal
	 */
	public String darTablaSucursal ()
	{
		return tablas.get (1);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Proveedor
	 */
	public String darTablaProveedor ()
	{
		return tablas.get (2);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Empresa
	 */
	public String darTablaEmpresa ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Persona
	 */
	public String darTablaPersona ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Cliente
	 */
	public String darTablaCliente ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bodega
	 */
	public String darTablaBodega ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Categoria
	 */
	public String darTablaCategoria ()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoProducto
	 */
	public String darTablaTipoProducto ()
	{
		return tablas.get (8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de DescuentoPorcentaje
	 */
	public String darTablaDescuentoPorcentaje ()
	{
		return tablas.get (9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Pague1Lleve2Porcentaje
	 */
	public String darTablaPague1Lleve2Porcentaje ()
	{
		return tablas.get (10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PagueNLleveM
	 */
	public String darTablaPagueNLleveM ()
	{
		return tablas.get (11);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PagueXLleveY
	 */
	public String darTablaPagueXLleveY ()
	{
		return tablas.get (12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Promocion
	 */
	public String darTablaPromocion ()
	{
		return tablas.get (13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Estante
	 */
	public String darTablaEstante ()
	{
		return tablas.get (14);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Factura
	 */
	public String darTablaFactura ()
	{
		return tablas.get (15);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Producto
	 */
	public String darTablaProducto ()
	{
		return tablas.get (16);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OrdenPedido
	 */
	public String darTablaOrdenPedido ()
	{
		return tablas.get (17);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OrdenesProductos
	 */
	public String darTablaOrdenesProductos ()
	{
		return tablas.get (18);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosBodegas
	 */
	public String darTablaProductosBodegas ()
	{
		return tablas.get (19);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosEstantes
	 */
	public String darTablaProductosEstantes ()
	{
		return tablas.get (20);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosFacturas
	 */
	public String darTablaProductosFacturas ()
	{
		return tablas.get (21);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosPromociones
	 */
	public String darTablaProductosPromociones ()
	{
		return tablas.get (22);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosProveedores
	 */
	public String darTablaProductosProveedores ()
	{
		return tablas.get (23);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PomocionesFacturas
	 */
	public String darTablaPromocionesFacturas ()
	{
		return tablas.get (24);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de SucursalesClientes
	 */
	public String darTablaSucursalesClientes ()
	{
		return tablas.get (25);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromocionPaquete
	 */
	public String darTablaPromocionPaquete ()
	{
		return tablas.get (26);
	}

	/**
	 * Transacci�n para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de SuperAndes
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	/**
	 * MEtodo que agrega una promocion junto con su requerimiento el tipo de la promocion 
	 * @param fechaInicial fecha donde inicia la promocion
	 * @param fechaFinal fecha final de la promocion
	 * @param pagueNLleveM tipo de promocion
	 * @param descuentoPorcentaje tipo de promocion
	 * @param pagueXLleveY tipo de promocion
	 * @param pague1Lleve2Porcentaje tipo de promocion
	 * @return la promocion junto con su tipo
	 */
	public Promocion adicionarPromocion (Date fechaInicial, Date fechaFinal,PagueNLleveM pagueNLleveM, DescuentoPorcentaje descuentoPorcentaje, PagueXLleveY pagueXLleveY , Pague1Lleve2Porcentaje pague1Lleve2Porcentaje  ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			Long idPague1Lleve2Porcentaje= null;
			Long idPagueNLleveM = null;
			Long idPagueXLleveY = null;
			Long idDescuentoPorcentaje = null;
			long id = nextval ();
			long tipoPromocion;
			int precio = 0;
			String elTipoEs = "";

			Promocion laPromocion = null ;
			if(pagueNLleveM != null) {
				elTipoEs = "pagueNLleveM";
				idPagueNLleveM = pagueNLleveM.getId();
				tipoPromocion = sqlPagueNLleveM.adicionarPagueNLleveM(pm, idPagueNLleveM, pagueNLleveM.getM(), pagueNLleveM.getN());
				laPromocion = new PagueNLleveM(id, fechaInicial, fechaFinal, precio, idPagueNLleveM, pagueNLleveM.getM(), pagueNLleveM.getN());
			}else if (pague1Lleve2Porcentaje!= null ) {
				elTipoEs = "pague1Lleve2Porcentaje";
				idPague1Lleve2Porcentaje = pague1Lleve2Porcentaje.getId();
				tipoPromocion = sqlPague1Lleve2Porcentaje.adicionarPague1Lleve2Porcentaje(pm, idPague1Lleve2Porcentaje, pague1Lleve2Porcentaje.getPorcentaje2());
				laPromocion = new Pague1Lleve2Porcentaje(id, fechaInicial, fechaFinal, precio, idPague1Lleve2Porcentaje, pague1Lleve2Porcentaje.getPorcentaje2());
			}
			else if (pagueXLleveY!= null ) {
				elTipoEs = "pagueXLleveY";
				idPagueXLleveY = pagueXLleveY.getId();
				tipoPromocion = sqlPagueXLleveY.adicionarPagueXLleveY(pm, idPagueXLleveY, pagueXLleveY.getX(), pagueXLleveY.getY());
				laPromocion = new PagueXLleveY(id, fechaInicial, fechaFinal, precio, idPagueXLleveY, pagueXLleveY.getX(), pagueXLleveY.getY());
			}else if(descuentoPorcentaje != null) {
				elTipoEs = "descuentoPorcentaje";
				idDescuentoPorcentaje = descuentoPorcentaje.getId();
				tipoPromocion =sqlDescuentoPorcentaje.adicionarDescuentoPorcentaje(pm, idDescuentoPorcentaje, descuentoPorcentaje.getPorcentaje());
				laPromocion = new DescuentoPorcentaje(id, fechaInicial, fechaFinal, precio, descuentoPorcentaje.getPorcentaje(), idDescuentoPorcentaje);
			}
			else {
				tipoPromocion = 0;
				laPromocion = null;	
			}
			long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, id, fechaInicial, fechaFinal, precio, idPague1Lleve2Porcentaje, idPagueNLleveM, idPagueXLleveY, idDescuentoPorcentaje);
			tx.commit();

			log.trace ("Inserci�n de promocion : " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			log.trace ("Inserci�n de Tipo promocion : " + elTipoEs  + ": " + tipoPromocion + " tuplas insertadas");

			return laPromocion;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}


	}
/**
 * Eliminar una promocion 
 * @param idPromocion promocion a eliminar
 * @return resultado de la eliminacion
 */
	public long eliminarPromocion(long idPromocion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPromocion.eliminarPromocionPorId(pm, idPromocion);           
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
/**
 * Proceso para solicitar a un proveedor un producto
 * @param idProveedor el proveedor que se le va a solicitar el producto
 * @param idProducto el producto a solicitar
 * @param fechaEsperadaDeEntrega la feche en la que se cree que sera entregado el producto
 * @param precioProveedor el precio que el proveedor acordo para la compra
 * @return
 */
	public OrdenPedido solicitarProductoProveedor (long idProveedor, long idProducto, Date fechaEsperadaDeEntrega, double precioProveedor) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idOrdenPedido = nextval();
			String calificacionPedido= "En proceso";
			long creacionOrden = sqlOrdenPedido.adicionarOrdenPedido(pm, idOrdenPedido,calificacionPedido , fechaEsperadaDeEntrega, 0, idProveedor);

			long idOrdenesProductos = nextval();
			long creacionProductosOrden = sqlOrdenesProducto.adicionarOrdenesProductos(pm, idOrdenesProductos , idProducto, idOrdenPedido,precioProveedor);

			tx.commit();

			log.trace ("Inserci�n de Orden Pedido: " + idOrdenesProductos + ". " + creacionOrden + " tuplas insertadas");
			log.trace ("Inserci�n de Productos Orden: " + idOrdenesProductos + ". " + creacionProductosOrden + " tuplas insertadas");


			return new OrdenPedido(idOrdenPedido, calificacionPedido, 0 , fechaEsperadaDeEntrega, null, idProveedor);
		}
		catch (Exception e)
		{
			//	        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}
	/**
	 * Registra la llega de una orden y supone que le llegan los productos y las bodegas relacionadas
	 *  para su almacenamiento
	 * 
	 * @param orden la orden de compra que se hizo al vendedor
	 * @param calificacionPedido la calificacion del pedido
	 * @param estado actualizacion del estado de la compra
	 * @param fechaEntrega la fecha en que llego el producto
	 * @param productosEnLaOrden los productos relacionados a la orden
	 * @param bodegasDisponibles las bodegas relacionadas para el almacenamiento
	 * @return
	 */
	public long llegadaOrdenPedido (OrdenPedido orden,String calificacionPedido, int estado, Date fechaEntrega, List <OrdenesProductos> productosEnLaOrden, List<Bodega> bodegasDisponibles) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{ 
			tx.begin();
			long idOrdenPedido = orden.getId();
			long actualizarOrden = sqlOrdenPedido.registrarLlegadaOrdenPedido(pm, idOrdenPedido , calificacionPedido, estado, fechaEntrega);
			
			for (int i = 0; i < productosEnLaOrden.size(); i++) {
				
				long actualizarBodega = sqlProductosBodegas.adicionarProductoBodega(pm, productosEnLaOrden.get(i).getIdOrden(), bodegasDisponibles.get(i).getId());
				log.trace ("Actualizar bodega  : " +  bodegasDisponibles.get(i).getId() + ". " + actualizarBodega + " tuplas insertadas");
			}
			tx.commit();
				
			log.trace ("Actualizar Orden  : " + idOrdenPedido + ". " + actualizarOrden + " tuplas insertadas");
			
			return actualizarOrden;
		}
		catch (Exception e)
		{
			//	        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

		
		
	}
	
<<<<<<< HEAD:src/uniandes/superAndes/persistencia/PersistenciaSuperAndes.java
	
	
=======

>>>>>>> 172bb72fe16aa85096bf376218e7859a3175ccf2:respaldo/uniandes/superAndes/persistencia/PersistenciaSuperAndes.java
}
