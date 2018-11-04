package uniandes.superAndes.negocio;

public class ProductosFacturas implements VOProductosFacturas
{

	private long idFactura;
	private long idProducto;
	private int cantidad;
	
	public ProductosFacturas () {
		idFactura = 0;
		idProducto = 0;
		cantidad =0 ;
	}
	public ProductosFacturas(long idFactura, long idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
