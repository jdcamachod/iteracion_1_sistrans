package uniandes.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;







public class PersistenciaSuperAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
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
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
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
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
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
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
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
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
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
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
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
	 * Cierra la conexión con la base de datos
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
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
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
	
	
	
}
