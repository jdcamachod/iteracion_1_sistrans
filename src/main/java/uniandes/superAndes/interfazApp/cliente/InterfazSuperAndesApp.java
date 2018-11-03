/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.superAndes.interfazApp.cliente;

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
import java.util.Date;
import java.util.GregorianCalendar;
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
import uniandes.superAndes.negocio.Promocion;
import uniandes.superAndes.negocio.Proveedor;
import uniandes.superAndes.negocio.Sucursal;
import uniandes.superAndes.negocio.SuperAndes;
import uniandes.superAndes.negocio.TipoProducto;
import uniandes.superAndes.negocio.VOBodega;
import uniandes.superAndes.negocio.Cliente;
import uniandes.superAndes.negocio.Empresa;
import uniandes.superAndes.negocio.Estante;
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
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/resources/interfaceConfigAppCliente.json"; 
	private static final String CONFIG_INTERFAZNI = "./src/resources/interfaceConfigAppClienteNoIniciado.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/resources/TablasBD_A.json";
	

	private static final String EMPRESARB = "empresaRB";

	private static final String CLIENTERB = "clienteRB"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * Atributo que representa la interfaz
	 */
	private static InterfazSuperAndesApp interfaz;
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
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
	
	private Cliente cliente;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp(String inter )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", inter);

		// Configura la apariencia del frame que contiene la interfaz gráfica
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
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
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
			log.info ("Se encontro un archivo de configuracion valido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuracion por defecto" );			
			titulo = "SuperAndes APP Default";
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
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
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
	
	public void actualizarInterfaz()
	{
		interfaz.setVisible(false);
		interfaz = new InterfazSuperAndesApp(CONFIG_INTERFAZ);
		interfaz.setVisible(true);
		
	}

	
	/**
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci�n
	 */
	public void listarSucursal( )
	{
		try 
		{
			List <VOSucursal> lista = superAndes.darVOSucursales();

			String resultado = "En Sucursales";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci�n terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
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
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci�n
	 */

	public void listarProductos( )
	{
		try 
		{
			List <VOProducto> lista = superAndes.darVOProductos();
			String resultado = "En Productos";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci�n terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
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
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
						cliente = null;
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
						actualizarInterfaz();
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
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * Inicia sesion con el correo del cliente
	 */
	public void iniciarSesion()
	{
		try 
		{
			cliente = null;
			String correo = JOptionPane.showInputDialog (this, "Correo del Cliente?", "Iniciar sesion", JOptionPane.QUESTION_MESSAGE);
			if (correo != null)
			{
				cliente = superAndes.darClientePorCorreo(correo);
				String resultado = "En buscar Cliente por correo\n\n";
				if (cliente != null)
				{
					resultado += "El Cliente es: " + cliente;
					actualizarInterfaz();
				}
				else
				{
					resultado += "Un Cliente con correo: " + correo + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
					resultado += "Un Cliente con correo: " + correo + " NO EXISTE\n";    				
				}
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci�n
	 */
	public void listarEstantes( )
	{
		try 
		{
			List <VOEstante> lista = superAndes.darVOEstantes();

			String resultado = "En Estantes";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci�n terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
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
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci�n
	 */
	public void listarBodegas( )
	{
		try 
		{
			List <VOBodega> lista = superAndes.darVOBodegas();

			String resultado = "En Bodegas";
			resultado +=  "\n" + listarVO(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operaci�n terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
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
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * 			Métodos administrativos
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
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}



	/**
	 * Muestra la presentación general del proyecto
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
	 * Muestra el script de creación de la base de datos
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
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogota	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computacion\n";
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
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param <E>
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
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
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
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
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
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
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
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
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			interfaz = new InterfazSuperAndesApp(CONFIG_INTERFAZNI );
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
