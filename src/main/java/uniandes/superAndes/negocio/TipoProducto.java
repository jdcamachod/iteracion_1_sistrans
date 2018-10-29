package uniandes.superAndes.negocio;

public class TipoProducto implements VOTipoProducto {
	
	private long id;
	private String nombre;
	private long  idCategoria;
	
	public TipoProducto(long id, String nombre, long idCategoria) {
		
		this.id = id;
		this.nombre = nombre;
		this.idCategoria = idCategoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
