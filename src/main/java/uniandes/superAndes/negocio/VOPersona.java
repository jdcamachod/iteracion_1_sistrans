package uniandes.superAndes.negocio;

public interface VOPersona {

	/**
	 * @return el tipo de documento de la persona
	 */
	public String getTipoDocumento();
	
	/**
	 * @return EL numero de documento de la persona
	 */
	public int getNumeroDocumento();
	/**
	 * @return Una cadena de caracteres con la información de la persona
	 */
	@Override
	public String toString(); 
}
