package uniandes.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import uniandes.superAndes.negocio.Producto;

class SQLProducto {
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
		public SQLProducto (PersistenciaSuperAndes pp)
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
		public long adicionarProducto (PersistenceManager pm, long idProducto, int cantidad, double cantidadPresentacion, Date fechaVencimiento, String codigoBarras, String marca, int nivelDeReorden, String nombre, double peso, double precioUnidadMedida, double precioUnitario, String presentacion, double volumen, String unidadMedida, Long idCategoria, Long idProveedor) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + "(id, cantidad, cantidadEnLaPresentacion, fechaVencimiento, codigoBarras, marca, nivelReorden, nombre, peso, precioUnidadMedida, precioUnitario, presentacion,volumen, unidadMedida, categoria, idProveedor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
	        q.setParameters(idProducto, cantidad, cantidadPresentacion, fechaVencimiento, codigoBarras, marca, nivelDeReorden,nombre, peso, precioUnidadMedida, precioUnitario, presentacion, volumen, unidadMedida, idCategoria, idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombrePro - El nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductosPorNombre (PersistenceManager pm, String nombrePro)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto()  + " WHERE nombre = ?");
	        q.setParameters(nombrePro);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN PRODUCTO de la base de datos de SuperAndes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPro - El identificador del producto
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProductoPorId (PersistenceManager pm, long idPro)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto() + " WHERE id = ?");
	        q.setParameters(idPro);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PRODUCTO de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idPro - El identificador del producto
		 * @return El objeto PRODUCTO que tiene el identificador dado
		 */
		public Producto darProductoPorId (PersistenceManager pm, long idPro) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto()  + " WHERE id = ?");
			q.setResultClass(Producto.class);
			q.setParameters(idPro);
			return (Producto) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PRODUCTOS de la 
		 * base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombrePro - El nombre de producto buscado
		 * @return Una lista de objetos PRODUCTO que tienen el nombre dado
		 */
		public List<Producto> darProductosPorNombre (PersistenceManager pm, String nombrePro) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto()  + " WHERE nombre = ?");
			q.setResultClass(Producto.class);
			q.setParameters(nombrePro);
			return (List<Producto>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PRODUCTOS de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos PRODUCTO
		 */
		public List<Producto> darProductos (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto() );
			q.setResultClass(Producto.class);
			return (List<Producto>) q.executeList();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PRODUCTOS de la 
		 * base de datos de SuperAndes, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombrePro - El nombre de producto buscado
		 * @return Una lista de objetos PRODUCTO que tienen el nombre dado
		 */
		public Producto darProductoPorNombreYProveedor (PersistenceManager pm, String nombrePro, long idProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto()  + " WHERE nombre = ? AND idproveedor = ?");
			q.setResultClass(Producto.class);
			q.setParameters(nombrePro, idProveedor);
			return (Producto) q.executeUnique();
		}
		
		public void restarCantidad(PersistenceManager pm, int cantidad, Long idProducto)
		{
			Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaProducto()+" SET cantidad = cantidad - ?  WHERE id = ? ");
			q.setParameters(cantidad, idProducto);
			q.executeUnique();
		}


}
