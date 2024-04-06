package controller;

import entities.Reserva;
import entities.Vuelo;
import model.ReservaModel;
import model.VueloModel;
import utils.Utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReservaController {
    public static void insert(){
        getAll();
        Date fecha_reserva = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de la reserva: (AAA-MM-DD)"));
        String asiento = JOptionPane.showInputDialog("Ingrese el asiento: ");
        int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pasajero: "));
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo: "));
        InstanceModel().create(new Reserva(fecha_reserva,asiento,id_pasajero,id_vuelo));

    }

    public static void getAll(){
        String list = getAll(InstanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

    //creamos una sobreescritura para guardar toda la lista en string y despues mostrarla en un JOPTION
    public static String getAll(List<Object> list){
        String listString = "Lista de Reservas \n";
        for (Object i: list){
            Reserva objReserva = (Reserva) i;
            listString+=objReserva.toString()+"\n";
        }
        return listString;
    }

    public static void getbyFlight(){
        int vuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del vuelo: "));
        List<Object> reservaVuelo = InstanceModel().findByFlight(vuelo);
        JOptionPane.showMessageDialog(null, reservaVuelo.toString()+"\n");
    }

    public static void updatebySeat(){
        Object[] opcion = Utils.listToArray(InstanceModel().findAll());

        Reserva objSelected = (Reserva) JOptionPane.showInputDialog(
                null,
                "Seleccione el asiento para actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
        objSelected.setAsiento(JOptionPane.showInputDialog(null, "Ingrese el nuevo asiento: ",objSelected.getAsiento()));

        InstanceModel().changeBySeat(objSelected);
    }

    public static void delete(){
    Object[] opcion = Utils.listToArray(InstanceModel().findAll());

    Reserva objSelected = (Reserva) JOptionPane.showInputDialog(
            null,
            "Seleccione la reserva para eliminar ",
            "",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcion,
            opcion[0]);
    InstanceModel().delete(objSelected);
}

    public static ReservaModel InstanceModel(){
        return new ReservaModel();
    }
}
