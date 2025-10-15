package exceloperations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class Sortieren {
 
    static void DatumAbgleich(Object loken[][]) throws ParseException{
        Object loken2[][] = new Object[loken.length][loken[0].length];
        int f= 0;
        int b = 0;
        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){
             for (int a = 0; a < loken[0].length; a++){
                loken2[0][a] = loken[0][a];

             }   
            }
        //Prüfen, ob Loken1 gleich Loken2 ist    
        for (int i = 0; i < loken2.length; i++){
            String dateStr = (String) loken[i][3];
            String dateStr1 = (String) loken[i][4];
            String dateStr2 = (String) loken[i+1][3];
            String dateStr3 = (String) loken[i+1][4];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(dateStr2 !=null & dateStr3 != null){
                Date lok1Date1 = sdf.parse(dateStr);
                Date lok1Date2 = sdf.parse(dateStr1);
                Date lok2Date1 = sdf.parse(dateStr2);
                Date lok2Date2 = sdf.parse(dateStr3);

                if (loken[i][2] != loken2[b][2]){
                    b++;

                    for (int a = 0; a < loken[0].length; a++){
                    loken2[b][a] = loken[i][a];
                    System.out.print("" + loken2[b][a]+ " ");
                    }   

               /*  if (lok1Date1.before(lok2Date1)){
                    for (int c = 0; c < loken[0].length; c++){
                        loken2[f][c] = loken[i][c];
                        System.out.print("" + loken2[f][c]+ " ");
                    }
                    f++;
                }*/
                    f++;
                    System.out.println();
                }    
            }else{

                if (loken[i][2] != loken2[b][2]){
                    b++;

                    for (int a = 0; a < loken[0].length; a++){
                    loken2[b][a] = loken[i][a];
                    System.out.print("" + loken2[b][a]+ " ");
                    }   

                    f++;
                    System.out.println();
                break;
                }
            }
            
        }
    System.out.println(f);
    }
}
