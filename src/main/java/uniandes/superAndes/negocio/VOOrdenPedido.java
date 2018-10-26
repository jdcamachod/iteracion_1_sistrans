package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOOrdenPedido {

	public long getId();
	public String getCalificacionPedido();
	public int getEstado();
	public Date getFechaEsperadaEntrega();
	public Date getFechaEntrega();
	public long getIdProveedor();
	
	
}
