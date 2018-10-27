package uniandes.superAndes.negocio;

public interface VOProductosFacturas {

	/**
	 * @return El identificador de la factura
	 */
	public long getIdFactura();
	
	/**
	 * @return El identificador del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return Una cadena de caracteres con la informaci�n del producto factura
	 */
	@Override
	public String toString(); 
}
