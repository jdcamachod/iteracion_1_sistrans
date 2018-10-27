package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOPromocionPaquete {

	/**
	 * @return El identificador de uno de los productos a los que pertenece el paquete
	 */
	public long getIdProductoOrigen();
	
	/***
	 * @return El identificador del paquete que se formo como un nuevo producto
	 */
	public long getIdProductoPaquete();
	
	/**
	 * @return La fecha inicial de la promocion
	 */
	public Date getFechaInicial();
	
	/**
	 * @return la fecha final de la promocion
	 */
	public Date getFechaFinal();
}
