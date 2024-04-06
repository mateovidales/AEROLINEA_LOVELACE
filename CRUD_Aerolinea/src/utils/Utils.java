package utils;

import java.util.List;

public class Utils {

    public static <Valor> Valor[] listToArray(List<Valor> list){
        // crear un arreglo de Object del tamaño de la lista
        Valor[] array = (Valor[])new Object[list.size()];

        int i=0;
        for (Valor iterador: list){
            array[i++] = iterador;
        }
        return array;
    }
}
