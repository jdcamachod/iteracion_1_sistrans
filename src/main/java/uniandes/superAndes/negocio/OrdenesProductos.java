package uniandes.superAndes.negocio;

/**
 * Clase que modela los productos que pertenecen a una orden por medio de su relacion y con el precio
 * que el proveedor asigna a tal producto
 * @author Admin
 *
 */
public class OrdenesProductos implements VOOrdenesProductos {

	/**
	 *  identificador unico del producto
	 */
	private long idProducto;
	
	/**
	 * Identificador unico de la orden
	 */
	private long idOrden;
	
	/**
	 * Precio que el proveedor le asigna al producto en la orden
	 */
	private int precioProveedor;
	
	/**
	 * Constructor con parametros de la creacion de una OrdenesProducto
	 * @param idProducto El producto asociado a la orden
	 * @param idOrden El identificador de la orden asociada
	 * @param precioProveedor El precio del proveedor por el producto
	 */
	public OrdenesProductos(long idProducto, long idOrden, int precioProveedor) {
		
		this.idProducto = idProducto;
		this.idOrden = idOrden;
		this.precioProveedor = precioProveedor;
	}
	
	/**
	 * Retorna el identificador del proucto
	 */
	public long getIdProducto() {
		return idProducto;
	}
	
	/**
	 * Asigna un nuevo identificador al producto
	 * @param idProducto identificador a asignar
	 */
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	/**
	 * Retorna el identificador de la orden asociada
	 */
	public long getIdOrden() {
		return idOrden;
	}
	
	/**
	 * Asigna un identificador a la orden asociada
	 * @param idOrden identificador a asignar
	 */
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}
	
	/**
	 * Retorna el precio del proveedor por el producto
	 */
	public int getPrecioProveedor() {
		return precioProveedor;
	}
	
	/**
	 * Asigna un nuevo precio de proveedor para el producto
	 * @param precioProveedor el precio a asignar
	 */
	public void setPrecioProveedor(int precioProveedor) {
		this.precioProveedor = precioProveedor;
	}
	
	
	
}
