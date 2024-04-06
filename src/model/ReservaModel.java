package model;

import database.ConfigDB;
import entities.Reserva;
import entities.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaModel implements CRUD{
    @Override
    public Object create(Object obj) {
        // Nos conectamos a la BD
        Connection objConnection = ConfigDB.openConnection();
        // Convertimos el Objeto a un objeto Reserva
        Reserva objReserva = (Reserva) obj;
        try {
            // Verificar si el asiento está ocupado
            String VerificarSql = "SELECT COUNT(*) FROM reservacion WHERE asiento = ? AND id_vuelo = ?";
            PreparedStatement PrepareVerificacion = objConnection.prepareStatement(VerificarSql);
            PrepareVerificacion.setString(1, objReserva.getAsiento());
            PrepareVerificacion.setInt(2, objReserva.getFk_id_vuelo());
            ResultSet ResultVerificacion = PrepareVerificacion.executeQuery();
            ResultVerificacion.next();
            int count = ResultVerificacion.getInt(1);

            if (count > 0) {
                // si el asiento está ocupado, mostrar mensaje de error
                JOptionPane.showMessageDialog(null, "El asiento ya está reservado, elija otro asiento.");
                return null;  // No se crea la reserva
            }

            // Verificar capacidad del avión
            // Obtener la capacidad del avión
            String capacidadSql = "SELECT capacidad FROM avion WHERE id_avion = ?";
            PreparedStatement PrepareCapacidad = objConnection.prepareStatement(capacidadSql);
            PrepareCapacidad.setInt(1, objReserva.getFk_id_vuelo());
            ResultSet ResultCapacidad = PrepareCapacidad.executeQuery();
            if (ResultCapacidad.next()) {
                int capacidadAvion = ResultCapacidad.getInt("capacidad");

                // Contar la cantidad de asientos reservados
                String asientosSql = "SELECT COUNT(*) FROM reservacion WHERE id_vuelo = ?";
                PreparedStatement PrepareAsientos = objConnection.prepareStatement(asientosSql);
                PrepareAsientos.setInt(1, objReserva.getFk_id_vuelo());
                ResultSet ResultAsientos = PrepareAsientos.executeQuery();
                ResultAsientos.next();
                int asientosReservados = ResultAsientos.getInt(1);

                // Obtener la cantidad de asientos a reservar en esta reserva
                int asientosAReservar = 1; // Puedes ajustar si la reserva incluye más de un asiento

                // Verificar si se excede la capacidad del avión
                if (asientosReservados + asientosAReservar > capacidadAvion) {
                    // La capacidad del avión se excede, mostrar mensaje de error
                    JOptionPane.showMessageDialog(null, "No se puede realizar la reserva, ya que el avion se encuentra lleno");
                    return null;  // No se crea la reserva
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el avión especificado.");
                return null;  // No se crea la reserva
            }

            // El asiento está disponible y la capacidad del avión esta disponible, crear reserva
            String sql = "INSERT INTO reservacion (id_reservacion, fecha_reserva, asiento, id_pasajero, id_vuelo) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //le damos el valor a los ??? de la sentencia
            objPrepare.setInt(1, objReserva.getId_reserva());
            objPrepare.setDate(2, objReserva.getFecha_reserva());
            objPrepare.setString(3, objReserva.getAsiento());
            objPrepare.setInt(4, objReserva.getFk_id_pasajero());
            objPrepare.setInt(5, objReserva.getFk_id_vuelo());
            //Ejecutamos el Query
            objPrepare.execute();
            //obtenemos las llaves generadas con el resultset y el getgener...
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                //obtenemos la id que genere la base de datos
                objReserva.setId_reserva(objResult.getInt(1));
            }
            // y avisamos al usuario que fue agregado exitosamente
            JOptionPane.showMessageDialog(null,"La reserva fue agregada correctamente");
            //en caso de que suceda un error creamos el catch
        } catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objReserva;
    }


    @Override
    public List<Object> findAll() {
        //abrimos la conexion a la BD
        Connection objConnection = ConfigDB.openConnection();
        //Creamos la lista de objetos para guardar los registros de la base de datos
        List<Object> listReservas = new ArrayList<>();
        //Creamos el try catch por si algo puede fallar
        try{
            String sql = "SELECT * FROM reservacion INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero \n" +
                    "INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Reserva objReserva = new Reserva();
                objReserva.setId_reserva(objResult.getInt("reservacion.id_reservacion"));
                objReserva.setFecha_reserva(objResult.getDate("reservacion.fecha_reserva"));
                objReserva.setAsiento(objResult.getString("reservacion.asiento"));
                objReserva.setFk_id_pasajero(objResult.getInt("id_pasajero"));
                objReserva.setFk_id_vuelo(objResult.getInt("id_vuelo"));
                objReserva.setPasajero(objResult.getString("pasajero.nombre"));
                objReserva.setVuelo(objResult.getString("vuelo.destino"));
                listReservas.add(objReserva);
            }
            //creamos el catch por si pasa algun error
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservas;
    }

    public List<Object> findByFlight(int vuelo){
        Connection objConnection = ConfigDB.openConnection();
        List<Object>ListReserva = new ArrayList<>();
        Reserva objReserva = new Reserva();
        try {
            String sql = "SELECT * FROM reservacion INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo WHERE reservacion.id_vuelo = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, vuelo);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objReserva = new Reserva();
                objReserva.setId_reserva(objResult.getInt("reservacion.id_reservacion"));
                objReserva.setFecha_reserva(objResult.getDate("reservacion.fecha_reserva"));
                objReserva.setAsiento(objResult.getString("reservacion.asiento"));
                objReserva.setFk_id_pasajero(objResult.getInt("id_pasajero"));
                objReserva.setFk_id_vuelo(objResult.getInt("id_vuelo"));
                objReserva.setPasajero(objResult.getString("pasajero.nombre"));
                objReserva.setVuelo(objResult.getString("vuelo.destino"));
                ListReserva.add(objReserva);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return ListReserva;
    }

    public boolean changeBySeat(Object asiento){
        Connection objConnection = ConfigDB.openConnection();
        Reserva objReserva = (Reserva) asiento;
        boolean updateSeat = false;
        try {
            String sql = "UPDATE reservacion SET asiento = ? WHERE id_reservacion = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objReserva.getAsiento());
            objPrepare.setInt(2, objReserva.getId_reserva());
            int arrowAffected = objPrepare.executeUpdate();
            if (arrowAffected>0){
                updateSeat = true;
                JOptionPane.showMessageDialog(null,"El asiento fue actualizado correctamente.");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return updateSeat;
    }
    @Override
    public Boolean update(Object obj) {
        return null;
    }

    @Override
        public Boolean delete(Object obj) {
            Connection objConnection = ConfigDB.openConnection();
            Reserva objReserva = (Reserva) obj;
            boolean Delete = false;
            try {
                String sql = "DELETE FROM reservacion WHERE id_reservacion = ?;";
                PreparedStatement objPrepare = objConnection.prepareStatement(sql);
                objPrepare.setInt(1, objReserva.getId_reserva());
                int affectedRows = objPrepare.executeUpdate();
                if (affectedRows > 0){
                    Delete = true;
                    JOptionPane.showMessageDialog(null,"La reserva fue eliminado correctamente.");
                }
            }catch (SQLException error){
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
            ConfigDB.closeConnection();
            return Delete;
        }
    }

