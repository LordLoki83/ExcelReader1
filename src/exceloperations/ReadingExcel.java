package exceloperations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadingExcel {
    public static void main(String[] args) throws IOException, ParseException {
        int c= 0;
        Object loken2[][]= (Object[][]) Sortieren.DatumAbgleich();
        String dateStr3 = "22-10-2025";     //Das Datum der aktiven Datensätze einlesen
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date monday = sdf.parse(dateStr3);


        for (int a = 0; a < Sortieren.Ruckgabe(); a++ ){
            String start = (String) loken2[a][3];
            String end = (String) loken2[a][4];
            
            Date startZeit = sdf.parse(start);
            Date endZeit = sdf.parse(end);
            if(!monday.before(startZeit) && !monday.after(endZeit)){
                for (int b = 0; b < loken2[0].length; b++ ){
                    System.out.print("" + loken2[a][b] + " ");
                }
                c++;
                System.out.println(); 
            }
           
         
        }
        System.out.print(c);
    }
}