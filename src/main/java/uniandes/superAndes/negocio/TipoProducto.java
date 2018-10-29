package uniandes.superAndes.negocio;

public class TipoProducto implements VOTipoProducto {
	
	private long id;
	private String nombreTipo;
	private Long  categoria;
	
	
	public TipoProducto()
	{
		this.id = 0;
		this.nombreTipo = "";
		this.categoria = null;
	}
	public TipoProducto(long id, String nombre, long idCategoria) {
		
		this.id = id;
		this.nombreTipo = nombre;
		this.categoria = idCategoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreTipo() {
		return nombreTipo;
	}
	public void setNombreTipo(String nombre) {
		this.nombreTipo = nombre;
	}
	public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long idCategoria) {
		this.categoria = idCategoria;
	}
	
}
