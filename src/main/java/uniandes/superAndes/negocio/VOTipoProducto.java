package uniandes.superAndes.negocio;

public interface VOTipoProducto  {

	/**
	 * @return El identificador del tipo de producto
	 */
	public long getId();
	
	/**
	 * @return El nombre del tipo de producto
	 */
	public String getNombreTipo();
	
	public Long getCategoria();
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de producto
	 */
	@Override
	public String toString(); 
}
