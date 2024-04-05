package controller;

import entities.Avion;
import entities.Pasajero;
import jdk.jshell.execution.Util;
import model.PasajeroModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PasajeroController {
    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero: ");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del pasajero: ");
        String documento = JOptionPane.showInputDialog("Ingrese el documento de identidad del pasajero: ");

        InstaceModel().create(new Pasajero(nombre,apellido,documento));
    }

    public static void getAll(){
        String list = getAll(InstaceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public static String getAll(List<Object>list){
        String listString = "Lista de pasajeros \n";
        for (Object i:list){
            Pasajero objPasajero = (Pasajero) i;
            listString+=objPasajero.toString()+"\n";
        }
        return listString;
    }

    public static void getbyName(){
    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero: ");
    Pasajero PasajeroDocumento = InstaceModel().findbyName(nombre);
    JOptionPane.showMessageDialog(null, PasajeroDocumento.toString());
    }
    public static void update(){
        Object[] opcion = Utils.listToArray(InstaceModel().findAll());

        Pasajero objSelected = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione un pasajero para actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
        objSelected.setNombre(JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre del pasajero: ",objSelected.getNombre()));
        objSelected.setApellido(JOptionPane.showInputDialog(null,"Ingrese el nuevo apellido del pasajero: ",objSelected.getApellido()));
        objSelected.setDocumento_identidad(JOptionPane.showInputDialog(null,"Ingrese el nuevo documento de identidad del pasajero: ",objSelected.getDocumento_identidad()));

        InstaceModel().update(objSelected);
    }

    public static void delete(){
        Object[] opcion = Utils.listToArray(InstaceModel().findAll());

        Pasajero objSelected = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione el pasajero para eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcion,
                opcion[0]);
        InstaceModel().delete(objSelected);
    }
    public static PasajeroModel InstaceModel(){
        return new PasajeroModel();
    }
}


