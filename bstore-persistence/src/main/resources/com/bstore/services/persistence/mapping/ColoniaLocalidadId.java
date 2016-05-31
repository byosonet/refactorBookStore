// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

/**
 * ColoniaLocalidadId generated by hbm2java
 */
public class ColoniaLocalidadId implements java.io.Serializable {

	private short idEstado;
	private int idDelegacionMunicipio;
	private int id;

	public ColoniaLocalidadId() {
	}

	public ColoniaLocalidadId(short idEstado, int idDelegacionMunicipio, int id) {
		this.idEstado = idEstado;
		this.idDelegacionMunicipio = idDelegacionMunicipio;
		this.id = id;
	}

	public short getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(short idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdDelegacionMunicipio() {
		return this.idDelegacionMunicipio;
	}

	public void setIdDelegacionMunicipio(int idDelegacionMunicipio) {
		this.idDelegacionMunicipio = idDelegacionMunicipio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ColoniaLocalidadId))
			return false;
		ColoniaLocalidadId castOther = (ColoniaLocalidadId) other;

		return (this.getIdEstado() == castOther.getIdEstado())
				&& (this.getIdDelegacionMunicipio() == castOther.getIdDelegacionMunicipio())
				&& (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdEstado();
		result = 37 * result + this.getIdDelegacionMunicipio();
		result = 37 * result + this.getId();
		return result;
	}

}
