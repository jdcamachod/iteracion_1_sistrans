package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOProducto {

	/**
	 * @return El identificador del producto
	 */
	public long getId();
	
	/**
	 * @return el codigo de barras del producto
	 */
	public String getCodigoBarras();
	
	/**
	 * @return el peso del producto
	 */
	public double getPeso();
	
	/**
	 * @return el volumen del producto
	 */
	public double getVolumen();
	
	/**
	 * @return la marca del producto
	 */
	public String getMarca();
	
	/**
	 * @return el nivel de reorden del producto
	 */
	public int getNivelReorden();
	
	/**
	 * @return el nombre del producto
	 */
	public String getNombre();
	
	/**
	 * @return el precio por unidad del producto
	 */
	public double getPrecioUnitario();
	
	/**
	 * @return La presentacion del producto
	 */
	public String getPresentacion();
	
	/**
	 * @return el precio por unidad de medida
	 */
	public double getPrecioUnidadMedida();
	
	/**
	 * @return El identificador de la categoria a la que pertenece el producto
	 */
	public Long getCategoria();
	
	/**
	 * @return fecha de vencimiento del producto
	 */
	public Date getFechaVencimiento();
	public int getCantidad();
	public double getCantidadEnLaPresentacion();
	public long getIdProveedor();
	
	@Override
	public String toString();
	
	
}
