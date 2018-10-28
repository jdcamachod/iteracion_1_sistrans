package uniandes.superAndes.negocio;

public interface VOEmpresa {

	/**
	 * @return la direccion de la empresa
	 */
	public String getDireccion();
	
	/**
	 * @return el nit de la empresa
	 */
	public String getNit();
	
	
	public long getId();
	/**
	 * @return Una cadena de caracteres con la información de la empresa
	 */
	@Override
	public String toString(); 
}
