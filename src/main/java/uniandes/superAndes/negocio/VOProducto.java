package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOProducto {

	public long getId();
	public String getCodigoBarras();
	public double getPeso();
	public double getVolumen();
	public String getMarca();
	public int getNivelReorden();
	public String getNombre();
	public double getPrecioUnitario();
	public String getPresentacion();
	public double getPrecioUnidadMedida();
	public long getIdCategoria();
	public Date getFechaVencimiento();
	public int getCantidad();
	public double getCantidadPresentacion();
	public long getIdProveedor();
	
}
