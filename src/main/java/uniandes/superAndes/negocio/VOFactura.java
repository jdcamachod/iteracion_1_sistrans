package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOFactura {

	/**
	 * @return el id de la factura
	 */
	public long getId();
	
	/**
	 * @return la fecha de la factura
	 */
	public Date getFecha();
	
	/**
	 * @return el costo total de la factura
	 */
	public double getCostoTotal();
	
	/**
	 * @return el identificador del cliente
	 */
	public Long getCliente();
	
	public Long getSucursal();
	
	/**
	 * @return Una cadena de caracteres con la información de la factura
	 */
	@Override
	public String toString(); 
}
