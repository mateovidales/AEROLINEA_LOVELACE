package model;

import controller.PasajeroController;
import database.ConfigDB;
import entities.Pasajero;

import javax.swing.*;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD{
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        try {
            String sql = "INSERT INTO pasajero (id_pasajero, nombre,apellido,documento_identidad) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objPasajero.getId_pasajero());
            objPrepare.setString(2,objPasajero.getNombre());
            objPrepare.setString(3,objPasajero.getApellido());
            objPrepare.setString(4,objPasajero.getDocumento_identidad());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPasajero.setId_pasajero(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listPasajeros = new ArrayList<>();

        try {
            String sql = "SELECT * FROM pasajero;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                listPasajeros.add(objPasajero);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    public Pasajero findbyName(String nombre){
        Connection objConnectionn = ConfigDB.openConnection();
        Pasajero objPasajero = null;

        try {
            String sql = "SELECT * FROM pasajero WHERE nombre = ?;";
            PreparedStatement objPrepare = objConnectionn.prepareStatement(sql);
            objPrepare.setString(1,nombre);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public Boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        boolean update = false;
        try {
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());
            objPrepare.setInt(4, objPasajero.getId_pasajero());
            int affectedRows = objPrepare.executeUpdate();
            if (affectedRows > 0){
                update=true;
                JOptionPane.showMessageDialog(null,"El pasajero fue actualizado correctamente");
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }

    @Override
    public Boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        boolean delete = false;
        try {
            String sql = "DELETE FROM pasajero WHERE id_pasajero = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objPasajero.getId_pasajero());
            int affectedRows = objPrepare.executeUpdate();
            if (affectedRows > 0){
                delete = true;
                JOptionPane.showMessageDialog(null,"El pasajero fue eliminado correctamente");
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return delete;
    }
}
