package uniandes.superAndes.negocio;

public interface VOProveedor {
	
	
	/**
	 * @return la calificacion de calidad del proveedor
	 */
	public String getCalificacion();
	
	/**
	 * @return el identificador del proveedor
	 */
	public long getId();
	
	/**
	 * @return el nit del proveedor
	 */
	public String getNit();
	
	/**
	 * @return el nombre del proveedor
	 */
	public String getNombre();
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Tipos de bebida
	 * @param tb - El tipo de bebida a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object tb); 
	



}
