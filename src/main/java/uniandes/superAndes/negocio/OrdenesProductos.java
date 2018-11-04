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
	
	private int cantidad;
	
	public OrdenesProductos () {
		this.idOrden = 0 ;
		this.idProducto= 0;
		this.cantidad =0;
		
	}
	
	/**
	 * Constructor con parametros de la creacion de una OrdenesProducto
	 * @param idProducto El producto asociado a la orden
	 * @param idOrden El identificador de la orden asociada
	 * @param precioProveedor El precio del proveedor por el producto
	 */
	public OrdenesProductos(long idProducto, long idOrden, int precioProveedor, int cantidad) {
		
		this.idProducto = idProducto;
		this.idOrden = idOrden;
		this.precioProveedor = precioProveedor;
		this.cantidad = cantidad;
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
	
	
	
	
	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Productos orden [idOrden=" + idOrden + ", idProducto=" + idProducto + ", Precio proveedor=" + precioProveedor + " , cantidad=" + cantidad +  "]";
	}

	
}
