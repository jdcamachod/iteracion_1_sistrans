package uniandes.superAndes.negocio;

public interface VOProductosPromociones {

	/**
	 * @return El identificador del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return El identificador de la promocion
	 */
	public long getIdPromocion();
	
	/**
	 * @return Una cadena de caracteres con la información del producto promocion
	 */
	@Override
	public String toString(); 
}
