package uniandes.superAndes.negocio;

public class ProductosFacturas {

	private long idFactura;
	private long idProducto;
	public ProductosFacturas(long idFactura, long idProducto) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
	}
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	
}
