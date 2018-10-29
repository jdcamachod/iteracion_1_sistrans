package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.superAndes.negocio.Persona;
import uniandes.superAndes.negocio.Producto;

class SQLPersona {
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
		public SQLPersona  (PersistenciaSuperAndes pp)
		{
			this.pp = pp;
		}
		
		
		/**
		 * Adiciona una tupla de un producto a la tabla
		 * @param pm manejador de persistencia
		 * @param idProducto
		 * @param cantidad
		 * @param cantidadPresentacion
		 * @param fechaVencimiento
		 * @param codigoBarras
		 * @param marca
		 * @param nivelDeReorden
		 * @param nombre
		 * @param peso
		 * @param precioUnidadMedida
		 * @param precioUnitario
		 * @param presentacion
		 * @param volumen
		 * @return
		 */
		public long adicionarPersona  (PersistenceManager pm, long idPersona, String tipoDocumento, int documentoIdentificacion ) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersona () + "(id, tipoDocumento, documentoIdentificacion) values (?, ?, ?)");
	        q.setParameters(idPersona, tipoDocumento, documentoIdentificacion);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombrePro - El nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarPersonaPorId (PersistenceManager pm, long idPersona)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersona()  + " WHERE id = ?");
	        q.setParameters(idPersona);
	        return (long) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PRODUCTO de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPro - El identificador del producto
		 * @return El objeto PRODUCTO que tiene el identificador dado
		 */
		public Persona darPersonaPorId (PersistenceManager pm, long idPersona) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersona()  + " WHERE id = ?");
			q.setResultClass(Persona.class);
			q.setParameters(idPersona);
			return (Persona) q.executeUnique();
		}


		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PRODUCTOS de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos PRODUCTO
		 */
		public List<Persona> darPersona (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersona() );
			q.setResultClass(Persona.class);
			return (List<Persona>) q.executeList();
		}


}
