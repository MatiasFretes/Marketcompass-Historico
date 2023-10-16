package model;

import java.util.List;

public class DB_Recomendacion {
	
	private String mercadoNombre;
    private List<String> peticionUsuario;
    private String criterioNombre;

    public DB_Recomendacion() {
    }

    public DB_Recomendacion(String mercadoNombre, List<String> peticionUsuario, String criterioNombre) {
        this.mercadoNombre = mercadoNombre;
        this.peticionUsuario = peticionUsuario;
        this.criterioNombre = criterioNombre;
    }

    public String getMercadoNombre() {
        return mercadoNombre;
    }

    public void setMercadoNombre(String mercadoNombre) {
        this.mercadoNombre = mercadoNombre;
    }

    public List<String> getPeticionUsuario() {
        return peticionUsuario;
    }

    public void setPeticionUsuario(List<String> peticionUsuario) {
        this.peticionUsuario = peticionUsuario;
    }

	public String getCriterioNombre() {
		return criterioNombre;
	}

	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre = criterioNombre;
	}

	@Override
	public String toString() {
		return "DB_Recomendacion [mercadoNombre=" + mercadoNombre + ", peticionUsuario=" + peticionUsuario
				+ ", criterioNombre=" + criterioNombre + "]";
	}
}
