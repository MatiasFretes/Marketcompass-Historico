package model;

import java.util.List;

public class DB_Recomendacion {
	
	private String mercadoNombre;
    private List<String> peticionUsuario;

    public DB_Recomendacion() {
    }

    public DB_Recomendacion(String mercadoNombre, List<String> peticionUsuario) {
        this.mercadoNombre = mercadoNombre;
        this.peticionUsuario = peticionUsuario;
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

}
