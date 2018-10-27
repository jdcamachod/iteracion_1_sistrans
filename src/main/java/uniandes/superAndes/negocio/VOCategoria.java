package uniandes.superAndes.negocio;

public interface VOCategoria {

	/**
	 * Retorna el id de la categoria
	 * @return id de la categoria 
	 */
	public long getId();
	
	/**
	 * Retorna el nombre de la categoria
	 * @return nombre de la categoria
	 */
	public String getNombre();
	
	/**
	 * @return Una cadena de caracteres con la información de la categoria
	 */
	@Override
	public String toString(); 
}
