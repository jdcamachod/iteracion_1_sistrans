package uniandes.superAndes.negocio;

public interface VOPagueNLleveM {

	/**
	 * @return La cantidad de productos que se pagan en la promocion
	 */
	public int getN();
	
	/**
	 * @return La cantidad de productos que se llevan en la promocion
	 */
	public int getM();
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de promocion
	 */
	@Override
	public String toString(); 
}
