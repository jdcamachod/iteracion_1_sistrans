package uniandes.superAndes.negocio;

public class Estante implements VOEstante {
	
	private long id;
	private int nivelAbastecimiento;
	private double peso;
	private double volumen;
	private String direccion;
	private long categoria;
	private long sucursal;
	
	
	public Estante()
	{
		id = 0;
		nivelAbastecimiento = 0;
		peso = 0;
		volumen = 0;
		direccion = "";
		categoria = 0;
		sucursal = 0;
		
			
	}
	public Estante(long id, int nivelAbastecimiento, double peso, double volumen, String direccion, long idCategoria, long sucursal) {
		
		this.id = id;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.peso = peso;
		this.volumen = volumen;
		this.direccion = direccion;
		this.categoria = idCategoria;
		this.sucursal = sucursal;
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getVolumen() {
		return volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getCategoria() {
		return categoria;
	}
	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}
	public long getSucursal() {
		return sucursal;
	}
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}
	@Override
	public String toString() 
	{
		return "Estante [id=" + id + ", sucursal=" + sucursal + ", tipo=" + categoria + " , direccion=" + direccion + ", volumen=" + volumen + ", peso=" + peso + ", Nivel Abastecimiento: "+ nivelAbastecimiento +"]";
	}
	
	
}
