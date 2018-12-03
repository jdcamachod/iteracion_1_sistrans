/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogot谩	- Colombia)
 * Departamento	de	Ingenier铆a	de	Sistemas	y	Computaci贸n
 * Licenciado	bajo	el	esquema	Academic Free License versi贸n 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germ谩n Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jim茅nez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jdo.JDODataStoreException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.superAndes.negocio.Bodega;
import uniandes.superAndes.negocio.Categoria;
import uniandes.superAndes.negocio.Producto;
import uniandes.superAndes.negocio.ProductosEstantes;
import uniandes.superAndes.negocio.Promocion;
import uniandes.superAndes.negocio.Proveedor;
import uniandes.superAndes.negocio.Sucursal;
import uniandes.superAndes.negocio.SuperAndes;
import uniandes.superAndes.negocio.TipoProducto;
import uniandes.superAndes.negocio.VOBodega;
import uniandes.superAndes.negocio.VOCategoria;
import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Empresa;
import uniandes.superAndes.negocio.Estante;
import uniandes.superAndes.negocio.OrdenPedido;
import uniandes.superAndes.negocio.Persona;
import uniandes.superAndes.negocio.Proveedor;
import uniandes.superAndes.negocio.Sucursal;
import uniandes.superAndes.negocio.SuperAndes;
import uniandes.superAndes.negocio.VOCliente;
import uniandes.superAndes.negocio.VOEstante;
import uniandes.superAndes.negocio.VOProducto;
import uniandes.superAndes.negocio.VOPromocion;
import uniandes.superAndes.negocio.VOProveedor;
import uniandes.superAndes.negocio.VOSucursal;


/**
 * Clase principal de la interfaz
 * 
 */
@SuppressWarnings("serial")

public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci贸n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuraci贸n de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/resources/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuraci贸n de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/resources/TablasBD_A.json";

	private static final String EMPRESARB = "empresaRB";

	private static final String CLIENTERB = "clienteRB"; 

	private static final String TIPO1 = "Aplica descuento con un porcentaje dado al producto escogido";
	private static final String TIPO2 = "Paga 1 y lleva el segundo producto por un porcentaje menor";
	private static final String TIPO3 = "Paga N numero de productos y lleva M (N<M)";
	private static final String TIPO4 = "Paga X cantidad del producto escogido y lleva Y cantidad (Son unidades de medida)";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociaci贸n a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuraci贸n de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacci贸n para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Men煤 de la aplicaci贸n
	 */
	private JMenuBar menuBar;

	private JTextField tipoDocField;

	private JTextField direccionField;

	private JTextField numDocumento;

	private JRadioButton empresaRButton;

	private JRadioButton clienteRButton;

	private JTextField nitField;

	private JLabel labelTipoDoc;

	private JLabel labelnumDoc;

	private JLabel labelDireccionEmpresa;

	private JLabel labelNit;

	private JComboBox productos;

	/* ****************************************************************
	 * 			M茅todos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicaci贸n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp( )
	{
		// Carga la configuraci贸n de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gr谩fica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD_A", CONFIG_TABLAS);

		superAndes = new SuperAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			M茅todos de configuraci贸n de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuraci贸n para la aplicaci贸, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci贸n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci贸n
	 * @return Un objeto JSON con la configuraci贸n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontr贸 un archivo de configuraci贸n v谩lido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontr贸 un archivo de configuraci贸n v谩lido");			
			JOptionPane.showMessageDialog(null, "No se encontr贸 un archivo de configuraci贸n de interfaz v谩lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * M茅todo para configurar el frame principal de la aplicaci贸n
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuraci贸n por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuracion indicada en el archivo de configuracion" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * M茅todo para crear el men煤 de la aplicaci贸n con base em el objeto JSON le铆do
	 * Genera una barra de men煤 y los men煤s con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los men霉s deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creaci贸n de la barra de men煤s
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creaci贸n de cada uno de los men煤s
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creaci贸n de cada una de las opciones del men煤
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de Sucursal
	 *****************************************************************/
	/**
	 * Adiciona una sucursal con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de sucursal en la base de datos, si una sucursal con ese nombre no exist韆
	 */
	public void registrarSucursal( )
	{
		try 
		{

			JTextField nombreField = new JTextField(10);
			JTextField direccionField = new JTextField(10);
			JTextField ciudadField = new JTextField(10);
			JTextField tamanoField = new JTextField(10);

			JPanel myPanel = new JPanel(new GridLayout(4,2));
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombreField);
			// myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Direccion:"));
			myPanel.add(direccionField);
			myPanel.add(new JLabel("Ciudad:"));
			myPanel.add(ciudadField);
			myPanel.add(new JLabel("Tamano:"));
			myPanel.add(tamanoField);



			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombre = nombreField.getText();
				String direccion = direccionField.getText();
				String ciudad = ciudadField.getText();
				double tamano = Double.parseDouble(tamanoField.getText());



				if (!nombre.isEmpty() && !direccion.isEmpty() && !ciudad.isEmpty() && tamano > 0)
				{

					Sucursal prov = superAndes.adicionarSucursal(nombre, direccion, tamano, ciudad);
					if (prov == null)
					{
						throw new Exception ("No se pudo crear una Sucursal con nombre: " + nombre);
					}
					String resultado = "En registrarProveedor\n\n";
					resultado += "Proveedor adicionado exitosamente: " + prov;
					resultado += "\n Operaci髇 terminada";
					panelDatos.actualizarInterfaz(resultado);


				}
				else
				{
					panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarPromocion()
	{
		try{
			JPanel myPanel = new JPanel(new GridLayout(2,1));
			myPanel.add(new JLabel("Seleccione el tipo de promocion"));
			JComboBox tipo = new JComboBox();
			tipo.addItem(TIPO1);
			tipo.addItem(TIPO2);
			tipo.addItem(TIPO3);
			tipo.addItem(TIPO4);
			myPanel.add(tipo);
			int res = JOptionPane.showConfirmDialog(null, myPanel, 
					"Escoja la promocion", JOptionPane.OK_CANCEL_OPTION);
			if (res == JOptionPane.OK_OPTION) 
			{
				String tipoProm = (String) tipo.getSelectedItem();
				JTextField dateInicialField = new JTextField(10);
				JTextField dateFinalField = new JTextField(10);
				productos = new JComboBox<String>();
				for(VOProducto p: superAndes.darVOProductos())
				{
					productos.addItem(p.getId()+": "+p.getNombre()+", $"+p.getPrecioUnitario());
				}
				if(tipoProm.equals(TIPO1)) 
				{
					myPanel = new JPanel(new GridLayout(4,2));
					JTextField porcentajeField = new JTextField(10);
					myPanel.add(new JLabel("porcentaje"));
					myPanel.add(porcentajeField);
					myPanel.add(new JLabel("Fecha inicio Promocion:"));
					myPanel.add(dateInicialField);
					myPanel.add(new JLabel("Fecha de finalizaci髇 de la promocion:"));
					myPanel.add(dateFinalField);
					myPanel.add(new JLabel("Seleccione el producto"));
					myPanel.add(productos);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Ingrese los datos de la promocion", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String fechaIn = dateInicialField.getText();
						String fechaFin = dateFinalField.getText();
						double porcentaje  = Double.parseDouble(porcentajeField.getText());
						String ac= (String) productos.getSelectedItem();
						long id = Long.parseLong(ac.split(":")[0]);
						Producto producto = superAndes.darProductoPorId(id);
						if (!fechaIn.isEmpty() && !fechaFin.isEmpty())
						{
							Date utilDateIn = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIn);
							java.sql.Date sqlDateIn = new java.sql.Date(utilDateIn.getTime());
							Date utilDateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
							java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());
							Promocion prom = superAndes.adicionarDescuentoPorcentaje(porcentaje, sqlDateIn, sqlDateFin, producto.getId());
							if (prom == null)
							{
								throw new Exception ("No se pudo crear la Promocion ");
							}
							String resultado = "En registrar promocion\n\n";
							resultado += "Promocion adicionado exitosamente: " + prom;
							resultado+="\n Quedan "+superAndes.darProductoPorId(prom.getIdProducto()).getCantidad()+" productos disponibles para esta promocion";
							resultado += "\n Operaci髇 terminada";
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
						}

					}
					else
					{
						panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
					} 
				}
				else if(tipoProm.equals(TIPO2))
				{
					myPanel = new JPanel(new GridLayout(4,2));
					JTextField porcentajeField = new JTextField(10);
					myPanel.add(new JLabel("porcentaje de descuento para el segundo producto"));
					myPanel.add(porcentajeField);
					myPanel.add(new JLabel("Fecha inicio Promocion:"));
					myPanel.add(dateInicialField);
					myPanel.add(new JLabel("Fecha de finalizaci髇 de la promocion:"));
					myPanel.add(dateFinalField);
					myPanel.add(new JLabel("Seleccione el producto"));
					myPanel.add(productos);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Ingrese los datos de la promocion", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String fechaIn = dateInicialField.getText();
						String fechaFin = dateFinalField.getText();
						double porcentaje  = Double.parseDouble(porcentajeField.getText());
						String ac= (String) productos.getSelectedItem();
						long id = Long.parseLong(ac.split(":")[0]);
						Producto producto = superAndes.darProductoPorId(id);
						if (!fechaIn.isEmpty() && !fechaFin.isEmpty())
						{
							Date utilDateIn = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIn);
							java.sql.Date sqlDateIn = new java.sql.Date(utilDateIn.getTime());
							Date utilDateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
							java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());
							Promocion prom = superAndes.adicionarPague1Lleve2Porcentaje(porcentaje, sqlDateIn, sqlDateFin, producto.getId());
							if (prom == null)
							{
								throw new Exception ("No se pudo crear la Promocion ");
							}
							String resultado = "En registrar promocion\n\n";
							resultado += "Promocion adicionado exitosamente: " + prom;
							resultado+="\n Quedan "+superAndes.darProductoPorId(prom.getIdProducto()).getCantidad()+" productos disponibles para esta promocion";
							resultado += "\n Operaci髇 terminada";
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
						}

					}
					else
					{
						panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
					} 
				}
				else if(tipoProm.equals(TIPO3))
				{
					myPanel = new JPanel(new GridLayout(5,2));
					JTextField nP = new JTextField(10);
					myPanel.add(new JLabel("Cantidad n de productos (Paga)"));
					myPanel.add(nP);
					JTextField mP = new JTextField(10);
					myPanel.add(new JLabel("Cantidad m de productos (Lleva)"));
					myPanel.add(mP);
					myPanel.add(new JLabel("Fecha inicio Promocion:"));
					myPanel.add(dateInicialField);
					myPanel.add(new JLabel("Fecha de finalizaci髇 de la promocion:"));
					myPanel.add(dateFinalField);
					myPanel.add(new JLabel("Seleccione el producto"));
					myPanel.add(productos);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Ingrese los datos de la promocion", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String fechaIn = dateInicialField.getText();
						String fechaFin = dateFinalField.getText();
						int n = Integer.parseInt(nP.getText());
						int m = Integer.parseInt(mP.getText());
						String ac= (String) productos.getSelectedItem();
						long id = Long.parseLong(ac.split(":")[0]);
						Producto producto = superAndes.darProductoPorId(id);
						if (!fechaIn.isEmpty() && !fechaFin.isEmpty())
						{
							Date utilDateIn = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIn);
							java.sql.Date sqlDateIn = new java.sql.Date(utilDateIn.getTime());
							Date utilDateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
							java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());
							Promocion prom = superAndes.adicionarPagueNLleveM(n,m, sqlDateIn, sqlDateFin, producto.getId());
							if (prom == null)
							{
								throw new Exception ("No se pudo crear la Promocion ");
							}
							String resultado = "En registrar promocion\n\n";
							resultado += "Promocion adicionado exitosamente: " + prom;
							resultado+="\n Quedan "+superAndes.darProductoPorId(prom.getIdProducto()).getCantidad()+" productos disponibles para esta promocion";
							resultado += "\n Operaci髇 terminada";
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
						}

					}
					else
					{
						panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
					} 
				}
				else if(tipoProm.equals(TIPO4))
				{
					myPanel = new JPanel(new GridLayout(5,2));
					JTextField nP = new JTextField(10);
					myPanel.add(new JLabel("Cantidad x del producto (Paga)"));
					myPanel.add(nP);
					JTextField mP = new JTextField(10);
					myPanel.add(new JLabel("Cantidad y del producto (Lleva)"));
					myPanel.add(mP);
					myPanel.add(new JLabel("Fecha inicio Promocion:"));
					myPanel.add(dateInicialField);
					myPanel.add(new JLabel("Fecha de finalizaci髇 de la promocion:"));
					myPanel.add(dateFinalField);
					myPanel.add(new JLabel("Seleccione el producto"));
					myPanel.add(productos);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Ingrese los datos de la promocion", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String fechaIn = dateInicialField.getText();
						String fechaFin = dateFinalField.getText();
						int n = Integer.parseInt(nP.getText());
						int m = Integer.parseInt(mP.getText());
						String ac= (String) productos.getSelectedItem();
						long id = Long.parseLong(ac.split(":")[0]);
						Producto producto = superAndes.darProductoPorId(id);
						if (!fechaIn.isEmpty() && !fechaFin.isEmpty())
						{
							Date utilDateIn = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIn);
							java.sql.Date sqlDateIn = new java.sql.Date(utilDateIn.getTime());
							Date utilDateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
							java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());
							Promocion prom = superAndes.adicionarPagueXLleveY(n,m, sqlDateIn, sqlDateFin, producto.getId());
							if (prom == null)
							{
								throw new Exception ("No se pudo crear la Promocion ");
							}
							String resultado = "En registrar promocion\n\n";
							resultado += "Promocion adicionado exitosamente: " + prom;
							resultado+="\n Quedan "+superAndes.darProductoPorId(prom.getIdProducto()).getCantidad()+" productos disponibles para esta promocion";
							resultado += "\n Operaci髇 terminada";
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
						}

					}
					else
					{
						panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
					} 
				}



			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			} 


		}
		catch(Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}


	public void listarPromocion( )
	{
		try 
		{
			List <VOPromocion> lista = superAndes.darVOPromociones();

			String resultado = "En Promociones";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */
	public void listarSucursal( )
	{
		try 
		{
			List <VOSucursal> lista = superAndes.darVOSucursales();

			String resultado = "En Sucursales";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarSucursalPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Id de la sucursal?", "Borrar Sucursal por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				long tbEliminados = superAndes.eliminarSucursal(nombre);

				String resultado = "En eliminar Sucursal\n\n";
				resultado += tbEliminados + " Sucursales eliminados\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarSucursalPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre de la Sucursal?", "Buscar Sucursal por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				VOSucursal sucursal = superAndes.darSucursalPorNombre(nombre);
				String resultado = "En buscar Sucursal por nombre\n\n";
				if (sucursal != null)
				{
					resultado += "La Sucursal es: " + sucursal;
				}
				else
				{
					resultado += "Una Sucursal con nombre: " + nombre + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/* ****************************************************************
	 * 			CRUD de Productos
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist韆
	 */
	public void registrarProveedor( )
	{
		try 
		{

			JTextField nombreField = new JTextField(10);
			JTextField nitField = new JTextField(10);
			JPanel myPanel = new JPanel(new GridLayout(2,2));
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombreField);
			myPanel.add(new JLabel("Nit:"));
			myPanel.add(nitField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombre = nombreField.getText();
				String nit = nitField.getText();

				if (!nombre.isEmpty())
				{
					if(!nit.isEmpty())
					{
						Proveedor prov = superAndes.adicionarProveedor (nombre,nit, "");
						if (prov == null)
						{
							throw new Exception ("No se pudo crear un Proveedor con nombre: " + nombre);
						}
						String resultado = "En registrarProveedor\n\n";
						resultado += "Proveedor adicionado exitosamente: " + prov;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					else
					{
						panelDatos.actualizarInterfaz("Nit no puede ser vacio");
					}

				}
				else
				{
					panelDatos.actualizarInterfaz("Nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */
	public void listarProveedor( )
	{
		try 
		{
			List <VOProveedor> lista = superAndes.darVOProveedores();

			String resultado = "En Proveedor";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarProveedorPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Id de la Proveedor?", "Borrar Proveedor por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				long tbEliminados = superAndes.eliminarProveedor(nombre);

				String resultado = "En eliminar Proveedor\n\n";
				resultado += tbEliminados + " Proveedor eliminados\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarProveedorPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre de la Proveedor?", "Buscar Proveedor por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				VOProveedor sucursal = superAndes.darProveedorPorNombre(nombre);
				String resultado = "En buscar Proveedor por nombre\n\n";
				if (sucursal != null)
				{
					resultado += "La Proveedor es: " + sucursal;
				}
				else
				{
					resultado += "Una Proveedor con nombre: " + nombre + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}




	/* ****************************************************************
	 * 			CRUD de Producto
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist韆
	 */
	public void registrarProducto( )
	{
		try 
		{

			JTextField nombreField = new JTextField(10);
			JTextField marcaField = new JTextField(10);
			JTextField precioUnField = new JTextField(10);
			JTextField presentacionField = new JTextField(10);
			JTextField precioUnMedField = new JTextField(10);
			JTextField cantidadPresenField = new JTextField(10);
			JTextField unMedField = new JTextField(10);
			JTextField volumenField = new JTextField(10);
			JTextField pesoField = new JTextField(10);
			JTextField codigoBarrasField = new JTextField(10);
			JTextField fechaVenField = new JTextField(10);
			JTextField cantidadField = new JTextField(10);
			JTextField nombreProvField = new JTextField(10);

			JTextField categoriaField = new JTextField(10);
			JTextField tipoProductoField = new JTextField(10);

			JPanel myPanel = new JPanel(new GridLayout(15,2));


			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombreField);
			myPanel.add(new JLabel("Marca:"));
			myPanel.add(marcaField);
			myPanel.add(new JLabel("Precio unitario:"));
			myPanel.add(precioUnField);
			myPanel.add(new JLabel("Presentacion:"));
			myPanel.add(presentacionField);
			myPanel.add(new JLabel("Precio por unidad de medida:"));
			myPanel.add(precioUnMedField);
			myPanel.add(new JLabel("Cantidad en la presentacion:"));
			myPanel.add(cantidadPresenField);
			myPanel.add(new JLabel("Unidad de medida (gr o ml):"));
			myPanel.add(unMedField);
			myPanel.add(new JLabel("volumen en centimetros cubicos:"));
			myPanel.add(volumenField);
			myPanel.add(new JLabel("peso en gramos:"));
			myPanel.add(pesoField);
			myPanel.add(new JLabel("codigo de barras en hexadecimal:"));
			myPanel.add(codigoBarrasField);
			myPanel.add(new JLabel("Fecha de vencimiento (dd/mm/aaaa):"));
			myPanel.add(fechaVenField);
			myPanel.add(new JLabel("cantidad:"));
			myPanel.add(cantidadField);
			myPanel.add(new JLabel("nombre del proveedor (no escriba nada si no tiene proveedor):"));
			myPanel.add(nombreProvField);
			myPanel.add(new JLabel("Categoria del producto:"));
			myPanel.add(categoriaField);
			myPanel.add(new JLabel("Tipo de producto:"));
			myPanel.add(tipoProductoField);



			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese los datos del producto", JOptionPane.OK_CANCEL_OPTION);


			if (result == JOptionPane.OK_OPTION) {

				String nombre = nombreField.getText();
				String marca = marcaField.getText();
				double precioUnitario = Double.parseDouble(precioUnField.getText());
				String presentacion = presentacionField.getText();
				double precioUnidadMedida = Double.parseDouble(precioUnMedField.getText());
				int cantidadPresentacion = Integer.parseInt(cantidadPresenField.getText());
				String unidadMedida = unMedField.getText();
				double volumen = Double.parseDouble(volumenField.getText());
				double peso = Double.parseDouble(pesoField.getText());
				String codigoBarras = codigoBarrasField.getText();
				String fechaVencimiento = fechaVenField.getText();
				int cantidad = Integer.parseInt(cantidadField.getText());
				String nombreProv = nombreProvField.getText();
				String categoria = categoriaField.getText();
				String tipoProducto = tipoProductoField.getText();

				if(superAndes.darProveedorPorNombre(nombreProv)==null)
				{
					panelDatos.actualizarInterfaz("El proveedor no existe");
				}
				else {
					Categoria cat = superAndes.darCategoriaPorNombre(categoria);
					if(cat==null)
					{
						cat = superAndes.adicionarCategoria(categoria);
					}
					TipoProducto tp = superAndes.darTipoProductoPorNombre(tipoProducto);
					if(tp == null)
					{
						tp = superAndes.adicionarTipoProducto(tipoProducto, cat.getId());
					}
					Long idProv = superAndes.darProveedorPorNombre(nombreProv).getId();
					Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVencimiento);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					Long cate = cat.getId();
					Producto prod =superAndes.adicionarProducto(cantidad, cantidadPresentacion,
							sqlDate, codigoBarras, marca, 0, nombre, peso, precioUnidadMedida,
							precioUnitario, presentacion, volumen,unidadMedida, cate, idProv);

					if(prod == null)
					{
						throw new Exception ("No se pudo crear un producto con nombre "+nombre);
					}
					String resultado= "En registrarProducto \n\n";
					resultado += "Producto adicionado exitosamente:  "+prod;
					resultado += "\n Operacion terminada ";
					panelDatos.actualizarInterfaz(resultado);

				}
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */

	public void listarProductos( )
	{
		try 
		{
			List <VOProducto> lista = superAndes.darVOProductos();
			String resultado = "En Productos";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarProductoPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del producto?", "Borrar Producto por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				long tbEliminados = superAndes.eliminarProductos(nombre);
				String resultado = "En eliminar Producto\n\n";
				resultado += tbEliminados + " Proveedor eliminados\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarProductoPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del producto?", "Buscar Producto por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				List<Producto> producto = superAndes.darProductosPorNombre(nombre);
				String resultado = "En buscar Producto por nombre\n\n";
				if (producto != null)
				{
					resultado += "La Proveedor es: \n" ;
					for(VOProducto pr: producto)
					{
						resultado +=pr+"\n";
					}
				}
				else
				{
					resultado += "Un Producto con nombre: " + nombre + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/* ****************************************************************
	 * 			CRUD de Cliente
	 * ****************************************************************
	 */

	public void registrarCliente( ) {

		try {

			JTextField nombreField = new JTextField(10);
			JTextField correoField = new JTextField(10);
			JTextField puntosField = new JTextField(10);
			tipoDocField = new JTextField(10);
			numDocumento = new JTextField(10);

			direccionField = new JTextField(10);
			direccionField.setVisible(false);
			nitField = new JTextField(10);
			nitField.setVisible(false);




			ButtonGroup empresaPersonaGroup = new ButtonGroup();
			empresaRButton = new JRadioButton("Empresa");
			empresaRButton.addActionListener(this);
			empresaRButton.setActionCommand(EMPRESARB);
			clienteRButton = new JRadioButton("Cliente");
			clienteRButton.addActionListener(this);
			clienteRButton.setActionCommand(CLIENTERB);
			clienteRButton.setSelected(true);
			empresaPersonaGroup.add(empresaRButton);
			empresaPersonaGroup.add(clienteRButton);

			JPanel myPanel = new JPanel(new GridLayout(11,2));
			myPanel.add(new JLabel("Nombre: "));
			myPanel.add(nombreField);
			myPanel.add(new JLabel("Correo Electronico: "));
			myPanel.add(correoField);
			myPanel.add(new JLabel("Puntos: "));
			myPanel.add(puntosField);
			myPanel.add(empresaRButton);
			myPanel.add(clienteRButton);

			labelTipoDoc = new JLabel("Tipo de Documento: ");
			myPanel.add(labelTipoDoc);
			myPanel.add(tipoDocField);

			labelnumDoc = new JLabel("Numero de documento: ");
			myPanel.add(labelnumDoc);
			myPanel.add(numDocumento);

			labelDireccionEmpresa = new JLabel("Direccion de la empresa: ");
			labelDireccionEmpresa.setVisible(false);
			myPanel.add(labelDireccionEmpresa);
			myPanel.add(direccionField);

			labelNit = new JLabel("Nit de la emprea: ");
			labelNit.setVisible(false);
			myPanel.add(labelNit);
			myPanel.add(nitField);
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombre = nombreField.getText();
				String correoElectronico = correoField.getText();
				double puntos = 0;
				if (!puntosField.getText().isEmpty()) {
					puntos = Double.parseDouble(puntosField.getText());
				}
				boolean empresa = empresaRButton.isSelected();
				String tipoDocumento = tipoDocField.getText();
				int numeroDocumento = 0 ;
				if ( !numDocumento.getText().isEmpty()) {
					numeroDocumento =Integer.parseInt( numDocumento.getText());
				}
				String direccion = direccionField.getText();
				String nit = nitField.getText();

				if (!nombre.isEmpty())
				{
					if(!correoElectronico.isEmpty())
					{
						Cliente cliente = null;
						if (!empresa) {
							if (!tipoDocumento.isEmpty() && !( numeroDocumento == 0)) {

								cliente = superAndes.adicionarCliente(nombre, correoElectronico, puntos, empresa, tipoDocumento,
										numeroDocumento, direccion, nit);

							}else {
								panelDatos.actualizarInterfaz("Llene todos los datos");

							}
						}else {
							if (!direccion.isEmpty() && !nit.isEmpty()) {

								cliente = superAndes.adicionarCliente(nombre, correoElectronico, puntos, empresa, tipoDocumento,
										numeroDocumento, direccion, nit);

							}else {
								panelDatos.actualizarInterfaz("Llene todos los datos");

							}

						} 

						if (cliente == null)
						{
							throw new Exception ("No se pudo crear un Cliente con nombre: " + nombre + "Correo: " + correoElectronico);
						}
						String resultado = "En registrar Cliente\n\n";
						resultado += "Cliente adicionado exitosamente: " + cliente;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					else
					{
						panelDatos.actualizarInterfaz("correoElectronico no puede ser vacio");
					}

				}
				else
				{
					panelDatos.actualizarInterfaz("nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}

		} 
		catch (Exception e) 
		{
			//	e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}






	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */
	public void listarClientes( )
	{
		try 
		{
			List <VOCliente> lista = superAndes.darVOClientes();

			String resultado = "En Clientes";
			resultado +=  "\n" + listarVOCliente(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarClientePorCorreo( )
	{
		try 
		{
			String correo = JOptionPane.showInputDialog (this, "Correo del Cliente que desea borrar?", "Borrar Cliente"
					+ " por correo", JOptionPane.QUESTION_MESSAGE);
			if (correo != null)
			{
				long tbEliminados = superAndes.eliminarCliente(correo);

				String resultado = "En eliminar Cliente\n\n";
				resultado += tbEliminados + " Cliente eliminados\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarClientePorCorreo( )
	{
		try 
		{
			String correo = JOptionPane.showInputDialog (this, "Correo del Cliente?", "Buscar Cliente por correo", JOptionPane.QUESTION_MESSAGE);
			if (correo != null)
			{
				VOCliente sucursal = superAndes.darClientePorCorreo(correo);
				String resultado = "En buscar Cliente por correo\n\n";
				if (sucursal != null)
				{
					resultado += "El Cliente es: " + sucursal;
				}
				else
				{
					resultado += "Una Cliente con correo: " + correo + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}




	/* ****************************************************************
	 * 			CRUD de Estante
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist韆
	 */
	public void registrarEstante( )
	{
		try 
		{

			JTextField direccionField = new JTextField(10);
			JComboBox categorias = new JComboBox();
			JTextField volumenField = new JTextField(10);
			JTextField pesoField = new JTextField(10);
			JTextField nivelAbastecimientoField = new JTextField(10);
			JComboBox sucursales = new JComboBox();	

			for(VOCategoria cat: superAndes.darVOCategoria())
			{
				categorias.addItem(cat.getNombre());
			}
			for(VOSucursal suc: superAndes.darVOSucursales())
			{
				sucursales.addItem(suc.getNombre());
			}

			JPanel myPanel = new JPanel(new GridLayout(6,2));
			myPanel.add(new JLabel("Sucursal:"));
			myPanel.add(sucursales);
			myPanel.add(new JLabel("Direccion:"));
			myPanel.add(direccionField);
			myPanel.add(new JLabel("Categoria:"));
			myPanel.add(categorias);
			myPanel.add(new JLabel("Volumen:"));
			myPanel.add(volumenField);
			myPanel.add(new JLabel("Peso:"));
			myPanel.add(pesoField);
			myPanel.add(new JLabel("Nivel de reorden:"));
			myPanel.add(nivelAbastecimientoField);			

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese los datos del estante", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombreSucursal = (String) sucursales.getSelectedItem();
				String direccion = direccionField.getText();
				String nombreCategoria = (String) categorias.getSelectedItem();
				String volumen = volumenField.getText();
				String peso = pesoField.getText();
				String nivelAbastecimiento = nivelAbastecimientoField.getText();


				if (!nombreSucursal.isEmpty() && !direccion.isEmpty() && !nombreCategoria.isEmpty() && !volumen.isEmpty() && !peso.isEmpty())
				{
					try {
						double pesoNumero = Double.parseDouble(peso);
						double volumenNumero = Double.parseDouble(volumen);
						int nivelAbastecimientoNumero = Integer.parseInt(nivelAbastecimiento);


						Estante estante = superAndes.adicionarEstante(nombreCategoria, direccion, pesoNumero, volumenNumero, nombreSucursal, nivelAbastecimientoNumero);
						if (estante == null)
						{
							throw new Exception ("No se pudo crear un estante con direccion: " + direccion);
						}
						String resultado = "En registrar estante\n\n";
						resultado += "estante adicionado exitosamente: " + estante;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					catch (NumberFormatException e) {
						panelDatos.actualizarInterfaz("Ingrese campos validos ");
					}
				}


				else
				{
					panelDatos.actualizarInterfaz("Los campos no pueden ser vacios ");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */
	public void listarEstantes( )
	{
		try 
		{
			List <VOEstante> lista = superAndes.darVOEstantes();

			String resultado = "En Estantes";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void registrarProductoAEstante()
	{
		try {
			productos = new JComboBox<String>();
			for(VOProducto p: superAndes.darVOProductos())
			{
				productos.addItem(p.getId()+": "+p.getNombre()+", $"+p.getPrecioUnitario());
			}
			JComboBox estantes = new JComboBox<String>();
			for(VOEstante p: superAndes.darVOEstantes())
			{
				estantes.addItem(p.getDireccion());
			}
			JPanel myPanel = new JPanel(new GridLayout(3,2));
			myPanel.add(new JLabel("Seleccione el prooducto"));
			myPanel.add(productos);
			myPanel.add(new JLabel("Seleccione el estante"));
			myPanel.add(estantes);
			myPanel.add(new JLabel("Digite la cantidad de productos destinados a este estante"));
			JTextField cant = new JTextField(10);
			myPanel.add(cant);
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese los datos del estante y el producto", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION)
			{
				String prod = (String) productos.getSelectedItem();
				Long idProducto =Long.parseLong( prod.split(":")[0]);
				String direccion = (String) estantes.getSelectedItem();
				int cantidad = Integer.parseInt(cant.getText());
				Estante estante = superAndes.darEstantePorDireccion(direccion);
				Producto producto = superAndes.darProductoPorId(idProducto);
				if(cantidad > producto.getCantidad())
				{
					panelDatos.actualizarInterfaz("La cantidad de productos disponibles");
				}
				else {
					ProductosEstantes relacion = superAndes.asociarEstanteProducto(producto, estante, cantidad);
					panelDatos.actualizarInterfaz("Se asocio el producto "+producto.getNombre()+" con el estante "+estante.getDireccion());
				}
				
			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
				
			}
			
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarEstantePorDireccion( )
	{
		try 
		{
			String direccion = JOptionPane.showInputDialog (this, "Direccion del Estante?", "Borrar Estante por direccion", JOptionPane.QUESTION_MESSAGE);
			if (direccion != null)
			{
				long tbEliminados = superAndes.eliminarEstante(direccion);

				String resultado = "En eliminar Estante\n\n";
				resultado += tbEliminados + " Estante eliminada\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarEstantePorDireccion( )
	{
		try 
		{
			String direccion = JOptionPane.showInputDialog (this, "Direccion del Estante?", "Buscar Estante por direccion", JOptionPane.QUESTION_MESSAGE);
			if (direccion != null)
			{
				VOEstante sucursal = superAndes.darEstantePorDireccion(direccion);
				String resultado = "En buscar Bodega por direccion\n\n";
				if (sucursal != null)
				{
					resultado += "El Estante es: " + sucursal;
				}
				else
				{
					resultado += "Un Estante con direccion: " + direccion + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/* ****************************************************************
	 * 			CRUD de Bodega
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist韆
	 */
	public void registrarBodega( )
	{
		try 
		{

			JTextField direccionField = new JTextField(10);
			JTextField categoriaField = new JTextField(10);
			JTextField volumenField = new JTextField(10);
			JTextField pesoField = new JTextField(10);
			JTextField sucursalField = new JTextField(10);	


			JPanel myPanel = new JPanel(new GridLayout(6,2));
			myPanel.add(new JLabel("Sucursal:"));
			myPanel.add(sucursalField);
			myPanel.add(new JLabel("Direccion:"));
			myPanel.add(direccionField);
			myPanel.add(new JLabel("Categoria:"));
			myPanel.add(categoriaField);
			myPanel.add(new JLabel("Volumen:"));
			myPanel.add(volumenField);
			myPanel.add(new JLabel("Peso:"));
			myPanel.add(pesoField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombreSucursal = sucursalField.getText();
				String direccion = direccionField.getText();
				String nombreCategoria = categoriaField.getText();
				String volumen = volumenField.getText();
				String peso = pesoField.getText();


				if (!nombreSucursal.isEmpty() && !direccion.isEmpty() && !nombreCategoria.isEmpty() && !volumen.isEmpty() && !peso.isEmpty())
				{
					try {
						double pesoNumero = Double.parseDouble(peso);
						double volumenNumero = Double.parseDouble(volumen);


						Bodega bodega = superAndes.adicionarBodega(nombreCategoria, direccion, pesoNumero, volumenNumero, nombreSucursal);
						if (bodega == null)
						{
							throw new Exception ("No se pudo crear una Bodega con direccion: " + direccion);
						}
						String resultado = "En registrarBodega\n\n";
						resultado += "bodega adicionado exitosamente: " + bodega;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					catch (NumberFormatException e) {
						panelDatos.actualizarInterfaz("Ingrese campos validos ");
					}
				}


				else
				{
					panelDatos.actualizarInterfaz("Los campos no pueden ser vacios ");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci髇
	 */
	public void listarBodegas( )
	{
		try 
		{
			List <VOBodega> lista = superAndes.darVOBodegas();

			String resultado = "En Bodegas";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci髇 terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos Sucursal con el identificador dado po el usuario
	 * Cuando dicho Sucursal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarBodegaPorDireccion( )
	{
		try 
		{
			String direccion = JOptionPane.showInputDialog (this, "Direccion del bodega?", "Borrar bodega por direccion", JOptionPane.QUESTION_MESSAGE);
			if (direccion != null)
			{
				long tbEliminados = superAndes.eliminarBodega(direccion);

				String resultado = "En eliminar Bodega\n\n";
				resultado += tbEliminados + " Bodega eliminada\n";
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca la Sucursal con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarBodegaPorDireccion( )
	{
		try 
		{
			String direccion = JOptionPane.showInputDialog (this, "Direccion de la Bodega?", "Buscar Bodega por direccion", JOptionPane.QUESTION_MESSAGE);
			if (direccion != null)
			{
				VOBodega sucursal = superAndes.darBodegaPorDireccion(direccion);
				String resultado = "En buscar Bodega por direccion\n\n";
				if (sucursal != null)
				{
					resultado += "La Bodega es: " + sucursal;
				}
				else
				{
					resultado += "Una Bodega con direccion: " + direccion + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de Orden de pedido proveedor
	 *****************************************************************/

	/**
	 * Adiciona un tipo de bebida con la informaci髇 dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist韆
	 */
	public void solicitarProductoProveedor( )
	{
		try 
		{

			JTextField nombreProveedorField = new JTextField(10);
			JTextField nombreProductoField = new JTextField(10);
			JTextField fechaEsperadaDeEntregaField = new JTextField(10);
			JTextField precioProveedorField = new JTextField(10);
			JTextField cantidadField = new JTextField(10);	


			JPanel myPanel = new JPanel(new GridLayout(6,2));
			myPanel.add(new JLabel("Nombre proveedor:"));
			myPanel.add(nombreProveedorField);
			myPanel.add(new JLabel("Nombre Producto:"));
			myPanel.add(nombreProductoField);
			myPanel.add(new JLabel("Fecha de espera de entrega:"));
			myPanel.add(fechaEsperadaDeEntregaField);
			myPanel.add(new JLabel("Precio Proveedor:"));
			myPanel.add(precioProveedorField);
			myPanel.add(new JLabel("Cantidad:"));
			myPanel.add(cantidadField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String nombreProveedor = nombreProveedorField.getText();
				String nombreProducto = nombreProductoField.getText();
				String  fechaEsperada = fechaEsperadaDeEntregaField.getText();
				String precioProveedor = precioProveedorField.getText();
				String cantidad = cantidadField.getText();

				if (!nombreProveedor.isEmpty() && !nombreProducto.isEmpty() && !fechaEsperada.isEmpty() && !precioProveedor.isEmpty() && !cantidad.isEmpty())
				{
					try {
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						Date date = simpleDateFormat.parse(fechaEsperada);
						java.sql.Date fechaEsperadaDeEntrega = new java.sql.Date(date.getTime());
						double precioProveedorNumero = Double.parseDouble(precioProveedor);
						int cantidadNumero = Integer.parseInt(cantidad);

						OrdenPedido orden = superAndes.solicitarProductoProveedor(nombreProveedor, nombreProducto, fechaEsperadaDeEntrega, precioProveedorNumero, cantidadNumero);
						if (orden == null)
						{
							throw new Exception ("No se pudo crear una Orden al proveedor " + nombreProveedor);
						}
						String resultado = "En registra Orden\n\n";
						resultado += "Orden adicionado exitosamente: " + orden;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					catch (NumberFormatException e) {
						panelDatos.actualizarInterfaz("Ingrese campos validos ");
					}
				}


				else
				{
					panelDatos.actualizarInterfaz("Los campos no pueden ser vacios ");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void llegadaOrdenPedido () {
		try 
		{

			JTextField idOrdenField = new JTextField(10);
			JTextField calificacionField = new JTextField(10);
			JTextField fechaDeEntregaField = new JTextField(10);	


			JPanel myPanel = new JPanel(new GridLayout(6,2));
			myPanel.add(new JLabel("id orden:"));
			myPanel.add(idOrdenField);
			myPanel.add(new JLabel("Calificacion de 1 a 10:"));
			myPanel.add(calificacionField);
			myPanel.add(new JLabel("Fecha de entrega:"));
			myPanel.add(fechaDeEntregaField);


			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String idOrden = idOrdenField.getText();
				String  fechaEsperada = fechaDeEntregaField.getText();
				String calificacion = calificacionField.getText();

				if (!idOrden.isEmpty() && !fechaEsperada.isEmpty() && !calificacion.isEmpty() )
				{
					try {
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						Date date = simpleDateFormat.parse(fechaEsperada);
						java.sql.Date fechaEsperadaDeEntrega = new java.sql.Date(date.getTime());
						double calificacionNumero = Double.parseDouble(calificacion);
						long idOrdenNumero = Long.parseLong(idOrden);


						long algo = superAndes.llegadaOrdenPedido(idOrdenNumero, calificacionNumero, 1, fechaEsperadaDeEntrega);

						if (algo == 0)
						{
							throw new Exception ("No se pudo Llegar la orden " + idOrden);
						}
						String resultado = "En Llegar Orden\n\n";
						resultado += "Llegada adicionada exitosamente: " + algo;
						resultado += "\n Operaci髇 terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					catch (NumberFormatException e) {
						panelDatos.actualizarInterfaz("Ingrese campos validos ");
					}
				}


				else
				{
					panelDatos.actualizarInterfaz("Los campos no pueden ser vacios ");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}


	}
	
	public void consultarFuncionamiento( )
	{
		try 
		{

			JTextField anioField = new JTextField(10);
			

			JPanel myPanel = new JPanel(new GridLayout(1,2));
			myPanel.add(new JLabel("A駉 de la consulta:"));
			myPanel.add(anioField);
			

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese el a駉 para consultar", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String anio = anioField.getText();
				


				if (!anio.isEmpty() )
				{

					Integer ano = Integer.parseInt(anio);

					String resultado = "El analisis fue\n\n";
					
					panelDatos.actualizarInterfaz(resultado);
					
					
					java.sql.Date date;
					java.sql.Date datea;
					Calendar c = Calendar.getInstance();
					c.set(ano, 12, 31);
					Calendar d =  Calendar.getInstance();
					d.set(ano, 1,1);
					int semana = 1;
					while(d.compareTo(c)<=0)
					{
						datea=new java.sql.Date(d.getTime().getTime());
						d.add(Calendar.WEEK_OF_YEAR, 1);
						date = new java.sql.Date(d.getTime().getTime());
						String respuesta = superAndes.consultarFuncionamiento(semana, datea, date);
						semana++;
						panelDatos.agregarAInterfaz(respuesta);
					}
					
					
					panelDatos.actualizarInterfaz("Termino el proceso");



				}
				else
				{
					panelDatos.actualizarInterfaz("Nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void analizarLaOperacionDeSuperAndes( )
	{
		try 
		{

			JTextField fechaInicialField = new JTextField(10);
			JTextField fechaFinalField = new JTextField(10);
			JTextField categoriaField = new JTextField(10);

			JPanel myPanel = new JPanel(new GridLayout(3,2));
			myPanel.add(new JLabel("Categoria:"));
			myPanel.add(categoriaField);
			myPanel.add(new JLabel("Fecha Inicial:"));
			myPanel.add(fechaInicialField);
			myPanel.add(new JLabel("Fecha Final:"));
			myPanel.add(fechaFinalField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String fechaInicial = fechaInicialField.getText();
				String fechaFinal = fechaFinalField.getText();
				String categoria = categoriaField.getText();


				if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty() && !categoria.isEmpty())
				{

					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					Date date1 = simpleDateFormat.parse(fechaInicial);
					java.sql.Date fechaInicialEs = new java.sql.Date(date1.getTime());

					pattern = "yyyy-MM-dd";
					simpleDateFormat = new SimpleDateFormat(pattern);
					Date date2 = simpleDateFormat.parse(fechaFinal);
					java.sql.Date fechaFinalEs = new java.sql.Date(date2.getTime());


					String prov = superAndes.analizarLaOperacionDeSuperAndes(categoria, fechaInicialEs, fechaFinalEs);
					if (prov == null)
					{
						throw new Exception ("No se pudo crear el analisis con nombre: " + fechaInicial);
					}
					String resultado = "En analisis\n\n";
					resultado += "analisis  exitosamente: " + prov;
					resultado += "\n Operaci髇 terminada";
					panelDatos.actualizarInterfaz(resultado);


				}
				else
				{
					panelDatos.actualizarInterfaz("Nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void consumoSuperAndes( )
	{
		try 
		{

			JTextField fechaInicialField = new JTextField(10);
			JTextField fechaFinalField = new JTextField(10);
			JTextField nombreProdField = new JTextField(10);
			JTextField paramField = new JTextField(10);
			

			JPanel myPanel = new JPanel(new GridLayout(4,2));
			myPanel.add(new JLabel("Nombre del producto: "));
			myPanel.add(nombreProdField);
			myPanel.add(new JLabel("Fecha Inicial (yyyy-MM-dd):"));
			myPanel.add(fechaInicialField);
			myPanel.add(new JLabel("Fecha Final (yyyy-MM-dd):"));
			myPanel.add(fechaFinalField);
			myPanel.add(new JLabel("Parametro de ordenamiento (puntos, correo, nombre, id): "));
			myPanel.add(paramField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese los datos del producto y la fecha", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String fechaInicial = fechaInicialField.getText();
				String fechaFinal = fechaFinalField.getText();
				String nombreProd = nombreProdField.getText();
				String param = paramField.getText();


				if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty() && !nombreProd.isEmpty() && !param.isEmpty())
				{

					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					Date date1 = simpleDateFormat.parse(fechaInicial);
					java.sql.Date fechaInicialEs = new java.sql.Date(date1.getTime());

					pattern = "yyyy-MM-dd";
					simpleDateFormat = new SimpleDateFormat(pattern);
					Date date2 = simpleDateFormat.parse(fechaFinal);
					java.sql.Date fechaFinalEs = new java.sql.Date(date2.getTime());

					
					List<Cliente> lista = superAndes.darClientesConsumoSuperAndes(fechaInicialEs, fechaFinalEs, nombreProd, param);
					String resultado = "En Clientes";
					resultado +=  "\n" + listarVO(lista);
					panelDatos.actualizarInterfaz(resultado);

				}
				else
				{
					panelDatos.actualizarInterfaz("Nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		
		
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void consumoSuperAndesv2( )
	{
		try 
		{

			JTextField fechaInicialField = new JTextField(10);
			JTextField fechaFinalField = new JTextField(10);
			JTextField nombreProdField = new JTextField(10);
			JTextField paramField = new JTextField(10);
			

			JPanel myPanel = new JPanel(new GridLayout(4,2));
			myPanel.add(new JLabel("Nombre del producto: "));
			myPanel.add(nombreProdField);
			myPanel.add(new JLabel("Fecha Inicial (yyyy-MM-dd):"));
			myPanel.add(fechaInicialField);
			myPanel.add(new JLabel("Fecha Final (yyyy-MM-dd):"));
			myPanel.add(fechaFinalField);
			myPanel.add(new JLabel("Parametro de ordenamiento (puntos, correo, nombre, id): "));
			myPanel.add(paramField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Ingrese los datos del producto y la fecha", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {

				String fechaInicial = fechaInicialField.getText();
				String fechaFinal = fechaFinalField.getText();
				String nombreProd = nombreProdField.getText();
				String param = paramField.getText();


				if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty() && !nombreProd.isEmpty() && !param.isEmpty())
				{

					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					Date date1 = simpleDateFormat.parse(fechaInicial);
					java.sql.Date fechaInicialEs = new java.sql.Date(date1.getTime());

					pattern = "yyyy-MM-dd";
					simpleDateFormat = new SimpleDateFormat(pattern);
					Date date2 = simpleDateFormat.parse(fechaFinal);
					java.sql.Date fechaFinalEs = new java.sql.Date(date2.getTime());

					
					List<Cliente> lista = superAndes.darClientesConsumoSuperAndesv2(fechaInicialEs, fechaFinalEs, nombreProd, param);
					String resultado = "En Clientes";
					resultado +=  "\n" + listarVO(lista);
					panelDatos.actualizarInterfaz(resultado);

				}
				else
				{
					panelDatos.actualizarInterfaz("Nombre no se permite vacio");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		
		
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	

	public void clientesFrecuente()
	{
		try 
		{
			String direccion = JOptionPane.showInputDialog (this, "Sucursal?", "cliente frecuente de sucursal ", JOptionPane.QUESTION_MESSAGE);
			if (direccion != null)
			{
				 List<Cliente> clientes = superAndes.encontrarLosClientesFrecuentes(direccion);
				String resultado = "En buscar Bodega por direccion\n\n";
				if (clientes != null)
				{
					for (int i = 0; i < clientes.size(); i++) {
						resultado += "Los clientes son : " + clientes.get(i).toString();

					}
				}
				else
				{
					resultado += "No existen clientes: " + direccion + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci髇 terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci髇 cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}





	/* ****************************************************************
	 * 			M茅todos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("superAndes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecuci贸n
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecuci贸n de la operaci贸n y recolecci贸n de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generaci贸n de la cadena de caracteres con la traza de la ejecuci贸n de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci贸n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci贸n de la operaci贸n y recolecci贸n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci贸n de la cadena de caracteres con la traza de la ejecuci贸n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}



	/**
	 * Muestra la presentaci贸n general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creaci贸n de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentaci贸n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la informaci贸n acerca del desarrollo de esta apicaci贸n
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogota	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier铆a	de	Sistemas	y	Computacion\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License version 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Super Andes\n";
		resultado += " * @version 2.0\n";
		resultado += " * @author Juan Diego Camacho y Kevyn Steve Blanco\n";
		resultado += " * Noviembre de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			M茅todos privados para la presentaci贸n de resultados y otras operaciones
	 *****************************************************************/

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l韓ea por cada tipo de bebida
	 * @param <E>
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l韊a para cada tipo de bebida recibido
	 */
	private <E> String listarVO(List<E> lista) 
	{
		String resp = "Los Elementos  son:\n";
		int i = 1;
		for (Object tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}


	private String listarVOCliente(List<VOCliente> lista) 
	{
		String resp = "Los Elementos  son:\n";
		int i = 1;
		long id = 0;
		for (VOCliente tb : lista)
		{
			Long idEmpresa = tb.getEmpresa();
			Long idPersona= tb.getPersonaNatural();

			if (idEmpresa == null) {
				Persona persona = superAndes.darPersonaPorId(idPersona.longValue());
				resp += i++ + ". " + "Persona Natural: " + persona.toString() + "\n" + "\n";

			}else {
				Empresa empresa = superAndes.darEmpresaPorId(idEmpresa.longValue());
				resp += i++ + ". " + tb.toString() + "\n" + "Empresa: " + empresa.toString() + "\n" + "\n";
			}

		}
		return resp;
	}



	/**
	 * Genera una cadena de caracteres con la descripci贸n de la excepcion e, haciendo 茅nfasis en las excepcionsde JDO
	 * @param e - La excepci贸n recibida
	 * @return La descripci贸n de la excepci贸n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci贸n
	 * @param e - La excepci贸n generada
	 * @return La cadena con la informaci贸n de la excepci贸n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci贸n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para m谩s detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como par谩metro con la aplicaci贸n por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			M茅todos de la Interacci贸n
	 *****************************************************************/
	/**
	 * M茅todo para la ejecuci贸n de los eventos que enlazan el men煤 con los m茅todos de negocio
	 * Invoca al m茅todo correspondiente seg煤n el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			if (evento.equals(EMPRESARB)) {



				tipoDocField.setVisible(false);
				numDocumento.setVisible(false);
				labelnumDoc.setVisible(false);
				labelTipoDoc.setVisible(false);

				direccionField.setVisible(true);
				nitField.setVisible(true);
				labelDireccionEmpresa.setVisible(true);
				labelNit.setVisible(true);



			}else if(evento.equals(CLIENTERB)) {
				tipoDocField.setVisible(true);
				numDocumento.setVisible(true);
				labelnumDoc.setVisible(true);
				labelTipoDoc.setVisible(true);

				direccionField.setVisible(false);
				nitField.setVisible(false);
				labelDireccionEmpresa.setVisible(false);
				labelNit.setVisible(false);

			}else {
				Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
				req.invoke ( this );
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este m茅todo ejecuta la aplicaci贸n, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por l铆nea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );

			ScheduledExecutorService scheduler
			= Executors.newSingleThreadScheduledExecutor();

			Runnable task = new SuperAndes();
			int initialDelay = 0;
			int periodicDelay = 1;
			scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,
					TimeUnit.DAYS
					);
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
