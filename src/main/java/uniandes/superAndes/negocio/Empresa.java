package uniandes.superAndes.negocio;

public class Empresa extends Cliente implements VOEmpresa
{

	/**
	 * Identificador unico de la Empresa
	 */
	private long id;
	/**
	 * Direccion de la empresa
	 */
	private String direccion;
	/**
	 * nit de la empresa
	 */
	private String nit;
	
	
	
	/**
	 * Crea una nueva empresa, constructor vacio
	 */
	public Empresa() {
		super ("DEFAULT", 0, "DEFAULT", 0, null,null);
		this.direccion = "";
		this.nit = "";
	}

	/**
	 * Crea una empresa con la informacion recibida
	 * @param correoElectronico correo electronico de la empresa
	 * @param id - id de la empresa
	 * @param nombre - nombre de la empresa
	 * @param puntos - puntos de la empresa
	 * @param direccion - direccion de la empresa
	 * @param nit - nit de la empresa
	 */
	public Empresa(String correoElectronico, long idCliente, String nombre, double puntos, Long idEmpresa,  String direccion, String nit) {
		super(correoElectronico, idCliente, nombre, puntos, null, idEmpresa);
		this.direccion = direccion;
		this.nit = nit;
		this.id= idEmpresa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la direccion de la empresa
	 * @return la direccion de la empresa
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna la direccion a la empresa
	 * @param direccion - direccion a asignar
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna el nit de la empresa
	 * @return el nit de la empresa
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * Asigna un nuevo nit a la empresa
	 * @param nit
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public String toString2()
	{
		return "Empresa [id=" + id + ", nombre=" + getNombre() + ", correo=" + getCorreoElectronico() + " , puntos=" +getPuntos()  +", Empresa id:" + id +
				", Direccion= "+ direccion +", NIT = "+ nit +" ]";
	}
	
	
	
}
