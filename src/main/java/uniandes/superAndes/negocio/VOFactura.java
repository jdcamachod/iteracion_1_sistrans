package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOFactura {

	public long getId();
	public Date getFecha();
	public double getCostoTotal();
	public long getIdCliente();
}
