package model;

import database.ConfigDB;
import entities.Avion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD{
    @Override
    public Object create(Object obj) {
        //Nos conectamos a la BD
        Connection objConnection = ConfigDB.openConnection();
        //Convertimos el Objeto a un objeto Avion
        Avion objAvion = (Avion) obj;
        try {
            //Creamos la sentencia SQL
            String sql = "INSERT INTO avion (id_avion,modelo,capacidad) VALUES (?,?,?);";
            //Creamos el preparedstatment, lo guardamos en una variable y nos devuelva la llaves generadas (primary key)
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //le damos el valor a los ??? de la sentencia
            objPrepare.setInt(1, objAvion.getId_avion());
            objPrepare.setString(2,objAvion.getModelo());
            objPrepare.setInt(3,objAvion.getCapacidad());
            //Ejecutamos el Query
            objPrepare.execute();
            //obtenemos las llaves generadas con el resultset y el getgener...
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                //obtenemos la id que genere la base de datos
                objAvion.setId_avion(objResult.getInt(1));
            }
            // y avisamos al usuario que fue agregado exitosamente
            JOptionPane.showMessageDialog(null,"El avion fue agregado correctamente");
        //en caso de que suceda un error creamos el catch
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //cerramos la conexion a la BD
        ConfigDB.closeConnection();
        //retornamos el objeto creado
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        //abrimos la conexion a la BD
        Connection objConnection = ConfigDB.openConnection();
        //Creamos la lista de objetos para guardar los registros de la base de datos
        List<Object> listAviones = new ArrayList<>();
        //Creamos el try catch por si algo puede fallar
        try{
            //creamos la sentencia sql
            String sql = "SELECT * FROM avion;";
            //creamos el prepared y le enviamos la sentencia sql
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            //recorremos con un while
            while (objResult.next()){
                //por cada iteracion del while vamos a crear una instancia de Avion
                Avion objAvion = new Avion();
                //lo llenamos con la informacion id, modelo, capacidad
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                listAviones.add(objAvion);
            }
            //creamos el catch por si pasa algun error
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        boolean update = false;
        try {
            String sql = "UPDATE avion SET modelo = ?, capacidad = ? WHERE id_avion = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2, objAvion.getCapacidad());
            objPrepare.setInt(3, objAvion.getId_avion());
            int arrowAffected = objPrepare.executeUpdate();
            if (arrowAffected>0){
                update = true;
                JOptionPane.showMessageDialog(null,"El registro fue actualizado correctamente.");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }

    @Override
    public Boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        boolean Delete = false;
        try {
            String sql = "DELETE FROM avion WHERE id_avion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objAvion.getId_avion());
            int affectedRows = objPrepare.executeUpdate();
            if (affectedRows > 0){
                Delete = true;
                JOptionPane.showMessageDialog(null,"Registro fie eliminado correctamente.");
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return Delete;
    }
}
