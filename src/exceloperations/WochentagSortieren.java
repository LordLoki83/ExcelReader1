package exceloperations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WochentagSortieren {
 static void MontagSortieren() throws ParseException, IOException{
        Object loken1[][]= (Object[][]) Sortieren.DatumAbgleich();
        Object loken2[][] = new Object[loken1.length][9];
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int b = 0;
        int l = 0;

        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){

            loken2[0][0] = loken1[0][0];//Region
            loken2[0][1] = loken1[0][1];//Standort
            loken2[0][2] = loken1[0][2];//Triebfahrzeug
            loken2[0][3] = loken1[0][3];//Datum von
            loken2[0][4] = loken1[0][4];         //Zeit von
            loken2[0][5] = loken1[0][5];//Datum bis
            loken2[0][6] = loken1[0][6];         //Zeit bis
            loken2[0][7] = loken1[0][7];
            loken2[0][8] = loken1[0][8];
            l++;
            }
        String dateStr3 = "21-10-2025";     //Das Datum der aktiven Datensätze einlesen
        Date monday = sdf.parse(dateStr3);
        int a = 0;
        
        for (int i = 0; i < loken2.length; i++ ){

            String start = (String) loken1[i][3];;
            String end = (String) loken1[i][5];

            if(start !=null & end != null){  

                Date startZeit = sdf.parse(start);
                Date endZeit = sdf.parse(end);

                if(!monday.before(startZeit) && !monday.after(endZeit)){

                    if(loken1[i][2] != loken2[b][2]) {           //Prüfen, ob die Loknummer in Array 2 ungleich zu Array 1 sind
                        b++;
                        loken2[b][0] = loken1[i][0];//Region
                        loken2[b][1] = loken1[i][1];//Standort
                        loken2[b][2] = loken1[i][2];//Triebfahrzeug
                        loken2[b][3] = loken1[i][3];//Datum von
                        loken2[b][4] = loken1[i][4];        //Zeit von
                        loken2[b][5] = loken1[i][5];//Datum bis
                        loken2[b][6] = loken1[i][6];        //Zeit bis
                        loken2[b][7] = loken1[i][7];
                        loken2[b][8] = loken1[i][8];
                        l++;

                        for (int e = 0; e < loken1[0].length; e++ ){
                            System.out.print("" + loken2[a][e] + " ");
                            
                        }

                        a++;
                         System.out.println(); 
                    }
                }
            }
        }
        System.out.print(l);
    }
}
