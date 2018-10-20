package uniandes.superAndes.negocio;

public class TipoProducto {
	
	private long id;
	private String nombre;
	public TipoProducto(long id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
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
	
	
}
