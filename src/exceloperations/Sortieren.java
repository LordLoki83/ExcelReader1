package exceloperations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class Sortieren {
 
    static void DatumAbgleich(Object loken[][]) throws ParseException{
        Object loken2[][] = new Object[loken.length][8];
        int f= 0;
        int b = 0;
        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){
            loken2[0][0] = loken[0][0];
            loken2[0][1] = loken[0][1];
            loken2[0][2] = loken[0][2];
            loken2[0][3] = loken[0][3];
            loken2[0][4] = loken[0][4];
            loken2[0][5] = loken[0][5];
            loken2[0][7] = loken[0][7];
            f++;
             //for (int a = 0; a < loken2[0].length; a++){
                //loken2[0][a] = loken[0][a];
               // System.out.print(""+ loken2[0][a] + " ");

             //}   
             //System.out.println();
            }
        //Prüfen, ob Loken1 gleich Loken2 ist    
        for (int i = 0; i < loken2.length; i++){
            String dateStr = (String) loken[i][3];       //Das Datum der aktiven Datensätze einlesen
            String dateStr1 = (String) loken[i][4];      //Das Datum der aktiven Datensätze einlesen
            String dateStr2 = (String) loken2[b][3];     //Das Datum der aktiven Datensätze einlesen
            String dateStr3 = (String) loken2[b][4];     //Das Datum der aktiven Datensätze einlesen
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(dateStr !=null & dateStr1 != null){      //Prüfen, ob einer der Datums-Datensätze null auswirft
                Date lok1Date1 = sdf.parse(dateStr);
                Date lok1Date2 = sdf.parse(dateStr1);
                Date lok2Date1 = sdf.parse(dateStr2);       //Umwandeln der Datum-Strings in Date-Datensätze
                Date lok2Date2 = sdf.parse(dateStr3);

                if (loken[i][2] != loken2[b][2]){           //Prüfen, ob die Loknummer in Array 2 ungleich zu Array 1 sind
                    b++;
                    loken2[b][0] = loken[i][0];
                    loken2[b][1] = loken[i][1];
                    loken2[b][2] = loken[i][2];
                    loken2[b][3] = loken[i][3];
                    loken2[b][4] = loken[i][4];
                    loken2[b][5] = loken[i][5];
                    loken2[b][7] = loken[i][7];
                    for (int a = 0; a < loken2[0].length; a++){
                    //loken2[b][a] = loken[i][a];
                    System.out.print("" + loken2[b][a]+ " ");
                    }   

                    f++;
                    System.out.println();
                //Wenn die Loknummer gleich ist, aber das Datum unterschiedlich
                }else if(loken[i][2] == loken2[b][2] & !lok1Date1.equals(lok2Date1) & !lok1Date2.equals(lok2Date2) ) {
                    b++;
                    loken2[b][0] = loken[i][0];
                    loken2[b][1] = loken[i][1];
                    loken2[b][2] = loken[i][2];
                    loken2[b][3] = loken[i][3];
                    loken2[b][4] = loken[i][4];
                    loken2[b][5] = loken[i][5];
                    loken2[b][7] = loken[i][7];
                    for (int a = 0; a < loken2[0].length; a++){
                    //loken2[b][a] = loken[i][a];
                    System.out.print("" + loken2[b][a]+ " ");
                    } 
                    f++;
                    System.out.println();  
                }
            }else{

            }
            
        }
    System.out.println(f);
    }
}