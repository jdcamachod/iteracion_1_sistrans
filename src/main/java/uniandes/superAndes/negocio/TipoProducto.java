package uniandes.superAndes.negocio;

public class TipoProducto implements VOTipoProducto {
	
	private long id;
	private String nombre;
	private Long  idCategoria;
	
	
	public TipoProducto(long id, String nombre, Long idCategoria)
	{
		this.id = 0;
		this.nombre = "";
		this.idCategoria = null;
	}
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
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
