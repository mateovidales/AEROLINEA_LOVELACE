package model;

import database.ConfigDB;
import entities.Avion;
import entities.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD{

    public Object create(Object obj) {
        //Nos conectamos a la BD
        Connection objConnection = ConfigDB.openConnection();
        //Convertimos el Objeto a un objeto Avion
        Vuelo objVuelo = (Vuelo) obj;
        try {
            //Creamos la sentencia SQL
            String sql = "INSERT INTO vuelo (id_vuelo,destino,fecha_salida,hora_salida,id_avion) VALUES (?,?,?,?,?);";
            //Creamos el preparedstatment, lo guardamos en una variable y nos devuelva la llaves generadas (primary key)
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //le damos el valor a los ??? de la sentencia
            objPrepare.setInt(1, objVuelo.getId_vuelo());
            objPrepare.setString(2,objVuelo.getDestino());
            objPrepare.setDate(3,objVuelo.getFecha_salida());
            objPrepare.setTime(4,objVuelo.getHora_salida());
            objPrepare.setInt(5,objVuelo.getFk_id_avion());
            //Ejecutamos el Query
            objPrepare.execute();
            //obtenemos las llaves generadas con el resultset y el getgener...
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                //obtenemos la id que genere la base de datos
                objVuelo.setId_vuelo(objResult.getInt(1));
            }
            // y avisamos al usuario que fue agregado exitosamente
            JOptionPane.showMessageDialog(null,"El vuelo fue agregado correctamente");
            //en caso de que suceda un error creamos el catch
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //cerramos la conexion a la BD
        ConfigDB.closeConnection();
        //retornamos el objeto creado
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        //abrimos la conexion a la BD
        Connection objConnection = ConfigDB.openConnection();
        //Creamos la lista de objetos para guardar los registros de la base de datos
        List<Object> listVuelos = new ArrayList<>();
        //Creamos el try catch por si algo puede fallar
        try{
            //creamos la sentencia sql
            String sql = "SELECT * FROM vuelo;";
            //creamos el prepared y le enviamos la sentencia sql
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            //recorremos con un while
            while (objResult.next()){
                //por cada iteracion del while vamos a crear una instancia de Avion
                Vuelo objVuelo = new Vuelo();
                //lo llenamos con la informacion id, modelo, capacidad
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getDate("fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("hora_salida"));
                objVuelo.setFk_id_avion(objResult.getInt("id_avion"));
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                listVuelos.add(objVuelo);
            }
            //creamos el catch por si pasa algun error
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelos;
    }

    public Vuelo findByplace(String destino){
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try{
            String sql = "SELECT * FROM vuelo WHERE destino = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, destino);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objVuelo = new Vuelo();
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getDate("fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("hora_salida"));
                objVuelo.setFk_id_avion(objResult.getInt("id_avion"));
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
            }
            //creamos el catch por si pasa algun error
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        boolean update = false;
        try {
            String sql = "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, id_avion = ? WHERE id_vuelo = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setDate(2, objVuelo.getFecha_salida());
            objPrepare.setTime(3, objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getFk_id_avion());
            objPrepare.setInt(5, objVuelo.getId_vuelo());
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
        Vuelo objVuelo = (Vuelo) obj;
        boolean Delete = false;
        try {
            String sql = "DELETE FROM vuelo WHERE id_vuelo = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objVuelo.getId_vuelo());
            int affectedRows = objPrepare.executeUpdate();
            if (affectedRows > 0){
                Delete = true;
                JOptionPane.showMessageDialog(null,"El vuelo fue eliminado correctamente.");
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return Delete;
    }
}
