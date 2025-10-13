package exceloperations;

import java.util.Arrays;
import java.util.List;

public class Sortieren {
 
    static void SortierenLoken(Object loken[][] ){
        Object loken2[][] = new Object[loken.length][loken[1].length];
        int i = 0;
        
        for(int f = 0; f <loken.length;f++ ){
        List<Object> list = Arrays.asList(loken[f]);    
        if(list.get(2).toString().contains("1016")||list.get(2).toString().contains("1116")|| list.get(2).toString().contains("1144")|| list.get(2).toString().contains("1216")|| list.get(2).toString().contains("1293")){
                    
           for(int j = 0; j < loken[f].length;j++ ){
                loken2[i][j]= list.get(j);
                    System.out.print("" + loken2[i][j]+ " ");
                    
                }
                i++;
            System.out.println();
            }
        }
    }

    static void DatumAbgleich(Object loken[][]){
        /*
        int f = 0;
        erster Datensatz von loken in loken2 kopieren
        for ( i = 0; i< loken.length; i++){
            for( j= 0; j< loken[].length; j++){
                if (loken2[0][0] == null){
                    loken2[f][i] = loken[i][j];
                    System.out.print("" + loken2[f][j]+ " ");
                }else
            
            }
        }
        WENN Datensatz 2 von loken  gleich loken2

        for-Schleife von 0 bis Zeilen-Ende (i)
            loken[0][i] in ein zweites Array loken2[0][i]übertragen


         */

    }
}