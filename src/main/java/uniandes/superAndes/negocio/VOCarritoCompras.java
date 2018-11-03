package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOCarritoCompras {

	/**
	 * @return El identificador del carrito
	 */
	public long getId();
	
	/**
	 * @return El identificador del cliente
	 */
	public Long getIdCliente();
	
	/**
	 * @return La fecha de solicitud del carro
	 */
	public  Date getFecha();
}
