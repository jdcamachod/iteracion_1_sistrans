package uniandes.superAndes.negocio;

public interface VOEstante {

	/**
	 * @return el identificador del estante
	 */
	public long getId();
	
	/**
	 * @return el nivel de abastecimiento del estante
	 */
	public int getNivelAbastecimiento();
	
	/**
	 * @return el peso capacidad del estante
	 */
	public int getPeso();
	
	/**
	 * @return el volumen capacidad del  estante
	 */
	public int getVolumen();
	
	/**
	 * @return la direccion del estante
	 */
	public String getDireccion();
	
	/**
	 * @return el identificador de la categoria
	 */
	public long getIdCategoria();
	
	/**
	 * @return Una cadena de caracteres con la informaci�n del estante
	 */
	@Override
	public String toString(); 
}
