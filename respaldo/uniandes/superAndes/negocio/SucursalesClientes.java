package uniandes.superAndes.negocio;

public class SucursalesClientes {

	private long idSucursal;
	private long idCliente;
	public SucursalesClientes(long idSucursal, long idCliente) {
		super();
		this.idSucursal = idSucursal;
		this.idCliente = idCliente;
	}
	public long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
