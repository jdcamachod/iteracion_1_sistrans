package uniandes.superAndes.negocio;

public class ProductosBodegas implements VOProductosBodegas{

	private long idProducto;
	private long idBodega;
	public ProductosBodegas(long idProducto, long idBodega) {
		
		this.idProducto = idProducto;
		this.idBodega = idBodega;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public long getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
	}
	
	
	
}
