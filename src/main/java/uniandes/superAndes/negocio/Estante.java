package uniandes.superAndes.negocio;

public class Estante implements VOEstante {
	
	private long id;
	private int nivelAbastecimiento;
	private int peso;
	private int volumen;
	private String direccion;
	private long idCategoria;
	
	
	public Estante()
	{
		id = 0;
		nivelAbastecimiento = 0;
		peso = 0;
		volumen = 0;
		direccion = "";
		idCategoria = 0;
		
			
	}
	public Estante(long id, int nivelAbastecimiento, int peso, int volumen, String direccion, long idCategoria) {
		
		this.id = id;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.peso = peso;
		this.volumen = volumen;
		this.direccion = direccion;
		this.idCategoria = idCategoria;
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNivelAbastecimiento() {
		return nivelAbastecimiento;
	}
	public void setNivelAbastecimiento(int nivelAbastecimiento) {
		this.nivelAbastecimiento = nivelAbastecimiento;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	
	
	
	
}
