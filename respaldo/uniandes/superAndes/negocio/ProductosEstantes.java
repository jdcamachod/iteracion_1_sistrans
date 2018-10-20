package uniandes.superAndes.negocio;

public class ProductosEstantes {

	private long idEstante;
	private long idProducto;
	public ProductosEstantes(long idEstante, long idProducto) {
		
		this.idEstante = idEstante;
		this.idProducto = idProducto;
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
	
	
	
	
}
