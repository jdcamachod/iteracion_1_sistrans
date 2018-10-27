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

package uniandes.superAndes.interfazApp;

import java.awt.BorderLayout;
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
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.superAndes.negocio.Proveedor;
import uniandes.superAndes.negocio.Sucursal;
import uniandes.superAndes.negocio.SuperAndes;
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
	private static final String CONFIG_INTERFAZ = "./src/resources/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/resources/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
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

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

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
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
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
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
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

	/* ****************************************************************
	 * 			CRUD de Sucursal
	 *****************************************************************/
	/**
	 * Adiciona una sucursal con la informaci�n dada por el usuario
	 * Se crea una nueva tupla de sucursal en la base de datos, si una sucursal con ese nombre no exist�a
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
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);


				}
				else
				{
					panelDatos.actualizarInterfaz("Ningun campo puede ser vacio o nulo");
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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



	/* ****************************************************************
	 * 			CRUD de Proveedor
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la informaci�n dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no exist�a
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
							throw new Exception ("No se pudo crear un tipo de bebida con nombre: " + nombre);
						}
						String resultado = "En registrarProveedor\n\n";
						resultado += "Proveedor adicionado exitosamente: " + prov;
						resultado += "\n Operaci�n terminada";
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
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
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
	 * Consulta en la base de datos las sucursales existentes y los muestra en el panel de datos de la aplicaci�n
	 */
	public void listarProveedor( )
	{
		try 
		{
			List <VOProveedor> lista = superAndes.darVOProveedores();

			String resultado = "En Proveedor";
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
		mostrarArchivo ("parranderos.log");
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
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
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
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
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
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
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
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
