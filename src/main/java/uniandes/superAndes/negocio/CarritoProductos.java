package uniandes.superAndes.negocio;

public class CarritoProductos {

	/**
	 * El identificador del carrito
	 */
	private Long idCarrito;
	
	/**
	 * El identificador del producto
	 */
	private Long idProducto;
	
	/**
	 * La cantidad de unidades de este producto en el carrito
	 */
	private int unidades;
	
	/**
	 * Constructor vacío para crear la relacion
	 */
	public CarritoProductos()
	{
		idCarrito = null;
		idProducto=  null;
		unidades = 0;
	}

	/**
	 * Constructor que asigna los datos recibidos por parametro
	 * @param idCarrito El identificador del carrito
	 * @param idProducto El identificador del producto
	 * @param unidades Las unidades disponibles de ese producto
	 */
	public CarritoProductos(Long idCarrito, Long idProducto, int unidades) {
	
		this.idCarrito = idCarrito;
		this.idProducto = idProducto;
		this.unidades = unidades;
	}

	/**
	 * @return el id del carrito
	 */
	public Long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * Asigna el id al carrito
	 * @param idCarrito
	 */
	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	/**
	 * @return el id del producto
	 */
	public Long getIdProducto() {
		return idProducto;
	}

	/**
	 * Asigna el identiificador al producto
	 * @param idProducto
	 */
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return Las unidades en el carrito de este producto
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Asigna un numero de unidades recibida por parametro
	 * @param unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
	
	
}
