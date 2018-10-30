package uniandes.superAndes.negocio;

public interface VOPagueXLleveY {

	/**
	 * @return La cantidad que se paga del determinado producto
	 */
	public int getX();
	
	/**
	 * @return La cantidad del producto que se lleva por la promocion
	 */
	public int getY();
	
	public long getIdPagueXLleveY();
	/**
	 * @return Una cadena de caracteres con la información del tipo de promocion
	 */
	@Override
	public String toString(); 
}
