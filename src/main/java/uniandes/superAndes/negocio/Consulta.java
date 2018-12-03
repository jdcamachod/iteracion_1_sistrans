package uniandes.superAndes.negocio;

public class Consulta {

	private String nombre;
	
	private int cuenta;

	public Consulta()
	{
		this.nombre = "";
		this.cuenta = 0;
	}
	public Consulta(String nombre, int cuenta) {
		super();
		this.nombre = nombre;
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	
	
}
