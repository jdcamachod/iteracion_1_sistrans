package uniandes.superAndes.negocio;

/**
 * Representa un cliente
 *
 */
public class Cliente implements VOCliente
{

	/**
	 * El correo electronico del cliente
	 */
	private String correoElectronico;
	
	/**
	 * Identificador unico del cliente
	 */
	private long id;
	
	/**
	 * nombre del cliente
	 */
	private String nombre;
	
	/**
	 * Los puntos de fidelidad del cliente
	 */
	private double puntos;
	
	/**
	 * Crea un nuevo cliente, constructor vacio
	 */
	
	private Long empresa;
	
	private Long personaNatural;
	
	
	
	public Cliente()
	{
		this.correoElectronico = "";
		this.id =0;
		this.nombre = "";
		this.puntos = 0.0;
		this.empresa =  null;
		this.personaNatural = null;
		
	}
	
	/**
	 * Crea un nuevo cliente con la informacion recibida por parametro
	 * @param correoElectronico - correo electronico del cliente
	 * @param id - identificador del cliente
	 * @param nombre - nombre del cliente
	 * @param puntos - puntos del cliente
	 */
	public Cliente(String correoElectronico, long id, String nombre, double puntos, Long idPersona,Long idEmpresa) {
	
		this.correoElectronico = correoElectronico;
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		empresa = idEmpresa;
		personaNatural = idPersona;
	}
	/**
	 * Retorna el correo electronico del cliente
	 * @return correo electronico del cliente
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Asigna un nuevo correo electronico al cliente
	 * @param correoElectronico correo electronico a asignar
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Retorna el identificador del cliente
	 * @return el identificador del cliente
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Asigna un nuevo id al cliente
	 * @param id - id a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Retorna el nombre del cliente
	 * @return nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Asigna un nuevo nombre al cliente
	 * @param nombre nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna los puntos de fidelidad del cliente
	 * @return puntos de fidelidad del cliente
	 */
	public double getPuntos() {
		return puntos;
	}
	
	/**
	 * Asigna un nuevo valor de puntos al cliente
	 * @param puntos puntos a asignar
	 */
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	public Long getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(Long personanatural) {
		this.personaNatural = personanatural;
	}
	
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Cliente [id=" + id + ", nombre=" + nombre + ", correo=" + correoElectronico + " , puntos=" +puntos + ", empresa= "+ empresa +", persona natural:" + personaNatural + " ]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Cliente tb = (Cliente) tipo;
		return id == tb.id && correoElectronico.equalsIgnoreCase (tb.correoElectronico);
	}

	
	
}
