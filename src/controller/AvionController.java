package controller;

import entities.Avion;
import model.AvionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AvionController {
    public static void insert(){
        //pedimos al usuario los datos
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del avion: ");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avion: "));

        InstanceModel().create(new Avion(modelo,capacidad));
    }

    public static void getAll(){
        String list = getAll(InstanceModel().findAll());

        JOptionPane.showMessageDialog(null,list);
    }

    //creamos una sobreescritura para guardar toda la lista en string y despues mostrarla en un JOPTION
    public static String getAll(List<Object> list){
        String listString = "Lista de Aviones \n";
        for (Object i: list){
            Avion objAvion = (Avion) i;
            listString+=objAvion.toString()+"\n";
        }
        return listString;
    }

    public static void delete(){
        Object[] opcion = Utils.listToArray(InstanceModel().findAll());

        Avion objSelected = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un avion",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
        InstanceModel().delete(objSelected);
    }

    public static void update(){
        Object[] opcion = Utils.listToArray(InstanceModel().findAll());

        Avion objSelected = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un avion para actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
        objSelected.setModelo(JOptionPane.showInputDialog(null, "Ingrese el nuevo modeloo del avion: ",objSelected.getModelo()));
        objSelected.setCapacidad(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva capacidad del avion: ",objSelected.getCapacidad())));

        InstanceModel().update(objSelected);
    }
    public static AvionModel InstanceModel(){
        return new AvionModel();
    }
}
