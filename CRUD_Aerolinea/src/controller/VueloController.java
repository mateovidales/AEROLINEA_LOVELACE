package controller;

import entities.Avion;
import entities.Vuelo;
import model.VueloModel;
import utils.Utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class VueloController {
    public static void insert(){
        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo: ");
        Date fecha_salida = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha del vuelo: (AAA-MM-DD)"));
        Time hora_salida = Time.valueOf(JOptionPane.showInputDialog("Ingrese la hora del vuelo: (00:00:00)"));
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del avion: "));

        InstanceModel().create(new Vuelo(destino,fecha_salida,hora_salida,id_avion));
    }

    public static void getAll(){
        String list = getAll(InstanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

    //creamos una sobreescritura para guardar toda la lista en string y despues mostrarla en un JOPTION
    public static String getAll(List<Object> list){
        String listString = "Lista de Vuelos \n";
        for (Object i: list){
            Vuelo objVuelo = (Vuelo) i;
            listString+=objVuelo.toString()+"\n";
        }
        return listString;
    }

    public static void getbyPlace(){
        String destino = JOptionPane.showInputDialog("Ingrese el nombre del destino: ");
        Vuelo vueloDestino =InstanceModel().findByplace(destino);
        JOptionPane.showMessageDialog(null,vueloDestino.toString());

    }

    public static void update(){
        Object[] opcion = Utils.listToArray(InstanceModel().findAll());

        Vuelo objSelected = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo para actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
                objSelected.setDestino(JOptionPane.showInputDialog(null, "Ingrese el nuevo destino del vuelo: ",objSelected.getDestino()));
        objSelected.setFecha_salida(Date.valueOf(JOptionPane.showInputDialog(null, "Ingrese la nueva fecha del vuelo: ",objSelected.getFecha_salida())));
        objSelected.setHora_salida(Time.valueOf(JOptionPane.showInputDialog(null, "Ingrese la nueva hora del vuelo: ",objSelected.getHora_salida())));
        objSelected.setFk_id_avion(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo ID del avion para el vuelo",objSelected.getFk_id_avion())));

        InstanceModel().update(objSelected);
    }

    public static void delete(){
        Object[] opcion = Utils.listToArray(InstanceModel().findAll());

        Vuelo objSelected = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo para eliminar ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
                InstanceModel().delete(objSelected);
    }

    public static VueloModel InstanceModel(){
        return new VueloModel();
    }
}
