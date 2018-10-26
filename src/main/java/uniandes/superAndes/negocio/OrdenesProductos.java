package uniandes.superAndes.negocio;

public class OrdenesProductos implements VOOrdenesProductos {

	private long idProducto;
	private long idOrden;
	private int precioProveedor;
	public OrdenesProductos(long idProducto, long idOrden, int precioProveedor) {
		
		this.idProducto = idProducto;
		this.idOrden = idOrden;
		this.precioProveedor = precioProveedor;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}
	public int getPrecioProveedor() {
		return precioProveedor;
	}
	public void setPrecioProveedor(int precioProveedor) {
		this.precioProveedor = precioProveedor;
	}
	
	
	
}
