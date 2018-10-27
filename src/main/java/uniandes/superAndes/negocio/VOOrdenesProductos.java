package uniandes.superAndes.negocio;

public interface VOOrdenesProductos {

	/**
	 * @return el identificador del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return El identificador de la orden
	 */
	public long getIdOrden();
	
	/**
	 * @return El precio del proveedor
	 */
	public int getPrecioProveedor();
	
	/**
	 * @return Una cadena de caracteres con la información del proveedor
	 */
	@Override
	public String toString(); 
}
