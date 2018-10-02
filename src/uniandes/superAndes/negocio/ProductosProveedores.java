package uniandes.superAndes.negocio;

public class ProductosProveedores {

	private long idProveedor;
	private long idProducto;
	private int precioProveedor;
	public ProductosProveedores(long idProveedor, long idProducto, int precioProveedor) {
		
		this.idProveedor = idProveedor;
		this.idProducto = idProducto;
		this.precioProveedor = precioProveedor;
	}
	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public int getPrecioProveedor() {
		return precioProveedor;
	}
	public void setPrecioProveedor(int precioProveedor) {
		this.precioProveedor = precioProveedor;
	}
	
	
	
}
