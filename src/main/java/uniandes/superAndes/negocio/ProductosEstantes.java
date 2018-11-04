package uniandes.superAndes.negocio;

public class ProductosEstantes implements VOProductosEstantes {

	private Long idEstante;
	private Long idProducto;
	private int cantidad;
	
	public ProductosEstantes()
	{
		this.idEstante= null;
		this.idProducto=null;
		this.cantidad=0;
	}
	public ProductosEstantes(Long idEstante, Long idProducto, int cantidad) {
		
		this.idEstante = idEstante;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public long getIdEstante() {
		return idEstante;
	}
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
