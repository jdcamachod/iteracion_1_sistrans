package uniandes.superAndes.negocio;

import java.util.ArrayList;
import java.util.Date;

public class Producto implements VOProducto {
	
	private long id;
	
	private String codigoBarras;
	private double peso;
	private double volumen;
	private String marca;
	private int nivelReorden;
	private String nombre;
	private double precioUnitario;
	private String presentacion;
	private double precioUnidadMedida;
	private Long categoria;
	private Date fechaVencimiento;
	private int cantidad;
	private double cantidadEnLaPresentacion;
	private Long idProveedor;
	private String unidadMedida;
	private String calidad;
	

	public Producto()
	{
		this.id = 0;
		this.cantidad = 0;
		this.codigoBarras = "";
		this.marca = "";
		this.nivelReorden = 0;
		this.nombre = "";
		this.precioUnitario = 0;
		this.presentacion = "";
		this.categoria = (long) 0;
		this.peso = 0;
		this.volumen = 0;
		this.fechaVencimiento = new Date();
		this.precioUnidadMedida =0;
		this.cantidadEnLaPresentacion=0;
		this.idProveedor = (long) 0;
		this.unidadMedida= "";
		this.calidad = "";
	}
	public Producto(long id, int cantidad, String codigoBarras, double peso, double volumen, String marca,
			int nivelReorden, String nombre, double precioUnitario, String presentacion, 
			Long idCategoria, Date fechaVencimiento, double precioUnidadMedida, double cantidadPresentacion, Long idProveedor, String unidadMedida) {
	
		this.id = id;
		this.cantidad = cantidad;
		this.codigoBarras = codigoBarras;
		this.marca = marca;
		this.nivelReorden = nivelReorden;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.presentacion = presentacion;
	
		this.categoria = idCategoria;
		this.peso = peso;
		this.volumen = volumen;
		this.fechaVencimiento =fechaVencimiento;
		this.precioUnidadMedida = precioUnidadMedida;
		this.cantidadEnLaPresentacion = cantidadPresentacion;
		this.idProveedor = idProveedor;
		this.unidadMedida=unidadMedida;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
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
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getNivelReorden() {
		return nivelReorden;
	}
	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(long idCategoria) {
		this.categoria = idCategoria;
	}
	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}
	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getCantidadEnLaPresentacion() {
		return cantidadEnLaPresentacion;
	}
	public void setCantidadEnLaPresentacion(double cantidadPresentacion) {
		this.cantidadEnLaPresentacion = cantidadPresentacion;
	}
	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	@Override
	public String toString() 
	{
		return "Producto [id=" + id + ", nombre=" + nombre + ", codigoBarras= " + codigoBarras + " , peso= " + peso + ", volumen= " + volumen + " , marca= " + marca +
				" , nivelReorden= " + nivelReorden + " , precioUnitario= " + precioUnitario + " , presentacion= " + presentacion + " , precioUnidadMedida= " + precioUnidadMedida +
				" , idCategoria= " + categoria + " , fechaVencimiento= " + fechaVencimiento + " , cantidad= " + cantidad + " , cantidadPresentacion= " + cantidadEnLaPresentacion +
				" , idProveedor= " + idProveedor + " , unidadMedida= " + unidadMedida +"]";
	}

	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Producto tb = (Producto) tipo;
		return id == tb.id && nombre.equalsIgnoreCase (tb.nombre);
	}
	
	
	
	

	
}
