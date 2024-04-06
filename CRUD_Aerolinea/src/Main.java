import controller.AvionController;
import controller.PasajeroController;
import controller.ReservaController;
import controller.VueloController;
import entities.Avion;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            String opcion = "";

            do {
                opcion = JOptionPane.showInputDialog(null,
                        "BIENVENIDO A LA AEROLINEA\n" +
                                "Elija su opcion:\n" +
                                "1. Avion\n" +
                                "2. Vuelo\n" +
                                "3. Pasajero\n" +
                                "4. Reserva\n" +
                                "5. Salir");

                switch (opcion) {
                    case "1":
                        String opcionAvion = JOptionPane.showInputDialog(null,
                                "ESPECIALIDAD\n" +
                                        "1. Listar Avion\n" +
                                        "2. Crear Avion\n" +
                                        "3. Actualizar Avion\n" +
                                        "4. Eliminar Avion\n" +
                                        "5. Salir\n");

                        switch (opcionAvion) {
                            case "1":
                                AvionController.getAll();
                                break;
                            case "2":
                                AvionController.insert();
                                break;
                            case "3":
                                AvionController.update();
                                break;
                            case "4":
                                AvionController.delete();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                break;
                        }
                        break;
                    case "2":
                        switch (opcion) {
                            case "2":
                                String opcionVuelo = JOptionPane.showInputDialog(null,
                                        "VUELO\n" +
                                                "1. Listar Vuelos\n" +
                                                "2. Crear Vuelo\n" +
                                                "3. Buscar Vuelo por destino\n" +
                                                "4. Actualizar Vuelo\n" +
                                                "5. Eliminar Vuelo\n" +
                                                "6. Salir");

                                switch (opcionVuelo) {
                                    case "1":
                                        VueloController.getAll();
                                        break;
                                    case "2":
                                        VueloController.insert();
                                        break;
                                    case "3":
                                        VueloController.getbyPlace();
                                        break;
                                    case "4":
                                        VueloController.update();
                                        break;
                                    case "5":
                                        VueloController.delete();
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                        break;
                                }
                        }
                    case "3":
                        switch (opcion) {
                            case "3":
                                String opcionPasajero = JOptionPane.showInputDialog(null,
                                        "PASAJERO\n" +
                                                "1. Listar Pasajeros\n" +
                                                "2. Crear Pasajero\n" +
                                                "3. Buscar Pasajero por nombre\n" +
                                                "4. Actualizar Pasajero\n" +
                                                "5. Eliminar Pasajero\n" +
                                                "6. Salir");

                                switch (opcionPasajero) {
                                    case "1":
                                        PasajeroController.getAll();
                                        break;
                                    case "2":
                                        PasajeroController.insert();
                                        break;
                                    case "3":
                                        PasajeroController.getbyName();

                                        break;
                                    case "4":
                                        PasajeroController.update();
                                        break;
                                    case "5":
                                        PasajeroController.delete();
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                        break;
                                }
                        }
                        break;
                    case "4":
                        switch (opcion) {
                            case "4":
                                String opcionReserva = JOptionPane.showInputDialog(null,
                                        "RESERVA\n" +
                                                "1. Listar Reservas\n" +
                                                "2. Crear Reserva\n" +
                                                "3. Buscar Reservas por Vuelo\n" +
                                                "4. Actualizar asiento de una reserva\n" +
                                                "5. Eliminar Reserva\n" +
                                                "6. Salir");

                                switch (opcionReserva) {
                                    case "1":
                                        ReservaController.getAll();
                                        break;
                                    case "2":
                                        ReservaController.insert();
                                        break;
                                    case "3":
                                        ReservaController.getbyFlight();
                                        break;
                                    case "4":
                                        ReservaController.updatebySeat();
                                        break;
                                    case "5":
                                        ReservaController.delete();
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                        break;
                                }
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción inválida.");
                                break;
                        }
                }
            } while (!opcion.equals("5"));

        }
    }
