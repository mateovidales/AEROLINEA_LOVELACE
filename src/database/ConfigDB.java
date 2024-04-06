package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //este atributo tendra el estado de la conexion
    public static Connection objConnection = null;

    //metodo para conectar la bd
    public static Connection openConnection() {
        try {
            //llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // creamos las variables de conexion
            String url = "jdbc:mysql://localhost:3306/aerolinea";
            String user = "root";
            String password = "";
            //establecer conexion
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecte perfectamente!!");

        } catch (ClassNotFoundException error) {
            System.out.println("Error >> DRIVER NO INSTALADO" + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >> error al conectar con la base de datos" + error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) objConnection.close();
            System.out.println("Se finalizo la conexion con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
