package uniandes.superAndes.negocio;

public class Proveedor implements VOProveedor
{

	private String calificacionCalidad;
	private long id;
	private String nit;
	private String nombre;
	public Proveedor(String calificacionCalidad, long id, String nit, String nombre) {
		super();
		this.calificacionCalidad = calificacionCalidad;
		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
	}
	public String getCalificacionCalidad() {
		return calificacionCalidad;
	}
	public void setCalificacionCalidad(String calificacionCalidad) {
		this.calificacionCalidad = calificacionCalidad;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
