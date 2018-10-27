package uniandes.superAndes.negocio;

public class Proveedor implements VOProveedor
{

	private String calificacion;
	private long id;
	private String nit;
	private String nombre;
	
	public Proveedor () {
		
		this.calificacion = "DEFAULT";
		this.id = 0;
		this.nit = "DEFAULT";
		this.nombre = "DEFAULT";
	}
	
	public Proveedor(String calificacionCalidad, long id, String nit, String nombre) {
		super();
		this.calificacion = calificacionCalidad;
		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
	}
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacionCalidad) {
		this.calificacion = calificacionCalidad;
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
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Proveedor [id=" + id + ", nombre=" + nombre + ", nit=" + nit + " , calificacionCalidad=" +calificacion + "]";
	}

	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Proveedor tb = (Proveedor) tipo;
		return id == tb.id && nombre.equalsIgnoreCase (tb.nombre);
	}

	
}
