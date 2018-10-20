package uniandes.superAndes.negocio;

public class ProductosPromociones {

	private long idProducto;
	private long idPromocion;
	public ProductosPromociones(long idProducto, long idPromocion) {
		
		this.idProducto = idProducto;
		this.idPromocion = idPromocion;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public long getIdPromocion() {
		return idPromocion;
	}
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}
	
	
}

