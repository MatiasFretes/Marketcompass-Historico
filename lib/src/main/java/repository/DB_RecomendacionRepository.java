package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.DB_Recomendacion;

public class DB_RecomendacionRepository {
	
	private Connection connection;

    public DB_RecomendacionRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(DB_Recomendacion recomendacion) {
        try {
            String sql = "INSERT INTO db_recomendacion (mercado_nombre, peticion_usuario, criterio_nombre) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, recomendacion.getMercadoNombre());
            String listaComoJSON = new Gson().toJson(recomendacion.getPeticionUsuario());
            statement.setString(2, listaComoJSON);
            statement.setString(3, recomendacion.getCriterioNombre());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DB_Recomendacion> selectAll() {
        List<DB_Recomendacion> recomendaciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM db_recomendacion";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String mercadoNombre = resultSet.getString("mercado_nombre");
                String peticionUsuarioJSON = resultSet.getString("peticion_usuario");
                String criterioNombre = resultSet.getString("criterio_nombre");
                List<String> peticionUsuario = new ArrayList<>();
                try {
                    JsonArray jsonArray = JsonParser.parseString(peticionUsuarioJSON).getAsJsonArray();
                    for (JsonElement jsonElement : jsonArray) {
                        peticionUsuario.add(jsonElement.getAsString());
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                DB_Recomendacion recomendacion = new DB_Recomendacion(mercadoNombre, peticionUsuario, criterioNombre);
                recomendaciones.add(recomendacion);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recomendaciones;
    }

}
