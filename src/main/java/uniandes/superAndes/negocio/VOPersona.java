package uniandes.superAndes.negocio;

public interface VOPersona {

	/**
	 * @return el tipo de documento de la persona
	 */
	public String getTipoDocumento();

	/**
	 * @return EL numero de documento de la persona
	 */
	public int getDocumentoIdentificacion();
	/**
	 * @return Una cadena de caracteres con la información de la persona
	 */

	public long getId () ;
	@Override
	public String toString(); 
}
