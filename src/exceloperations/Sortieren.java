package exceloperations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
public class Sortieren {
    static int h = 0;
    

    static Object ExcelSort() throws IOException{
        
        String excelFilePath = "/Users/lukas/Documents/ExcelReader2/ExcelReader2/Datafiles/AbstellungenKW44.xlsx"; //Zellverbund muss aufgehoben werden
        FileInputStream inputstream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputstream); 
        XSSFSheet sheet =  workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(5).getLastCellNum();

        Object loken[][] = new Object[rows-5][cols];
        int j = 0;
        for( int r = 6 ; r <= rows; r++){
            XSSFRow row = sheet.getRow(r);
            XSSFCell cellReg = row.getCell(0);
            XSSFCell cellLok = row.getCell(2);
            XSSFCell cellArt = row.getCell(5);
            if(cellReg != null & cellLok != null & cellArt != null){
                if(!cellReg.getStringCellValue().contains("GBL")){
                    if(!cellArt.getStringCellValue().contains("aREP")& !cellArt.getStringCellValue().contains("SFPR")&!cellArt.getStringCellValue().contains("SFTS")){

                        if(!cellLok.getStringCellValue().contains("1142")& !cellLok.getStringCellValue().contains("1163")& !cellLok.getStringCellValue().contains("1064")& !cellLok.getStringCellValue().contains("1063")){

                            for(int c = 0; c < cols; c++){
                                XSSFCell cell1 = row.getCell(c); 

                                if (cell1.getCellType()== CellType.STRING){
                                    String text = cell1.getStringCellValue();
                                    loken[j][c] = text;

                                } else if (cell1.getCellType()== CellType.NUMERIC){
                                    Date text = cell1.getDateCellValue();
                                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");   //Korrektur Format Datum
                                    String datum = format.format(text);
                                    loken[j][c] = datum;
                                }  

                            }
                        }
                        j++;
                    }  
                }
            } 
        }
        return loken;
    }

    
    static Object DatumAbgleich() throws ParseException, IOException{
        Object loken1[][]= (Object[][]) ExcelSort();
        Object loken2[][] = new Object[loken1.length][8];
        int b = 0;
        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){
            loken2[0][0] = loken1[0][0];
            loken2[0][1] = loken1[0][1];
            loken2[0][2] = loken1[0][2];
            loken2[0][3] = loken1[0][3];
            loken2[0][4] = loken1[0][4];
            loken2[0][5] = loken1[0][5];
            loken2[0][7] = loken1[0][7];
            h++;
            }
        //Prüfen, ob Loken1 gleich Loken2 ist    
        for (int i = 0; i < loken2.length; i++){
            String dateStr = (String) loken1[i][3];       //Das Datum der aktiven Datensätze einlesen
            String dateStr1 = (String) loken1[i][4];      //Das Datum der aktiven Datensätze einlesen
            String dateStr2 = (String) loken2[b][3];     //Das Datum der aktiven Datensätze einlesen
            String dateStr3 = (String) loken2[b][4];     //Das Datum der aktiven Datensätze einlesen
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(dateStr !=null & dateStr1 != null){      //Prüfen, ob einer der Datums-Datensätze null auswirft
                Date lok1Date1 = sdf.parse(dateStr);
                Date lok1Date2 = sdf.parse(dateStr1);
                Date lok2Date1 = sdf.parse(dateStr2);       //Umwandeln der Datum-Strings in Date-Datensätze
                Date lok2Date2 = sdf.parse(dateStr3);

                if(loken1[i][2] == loken2[b][2] & !lok1Date1.equals(lok2Date1) & !lok1Date2.equals(lok2Date2) ) {           //Prüfen, ob die Loknummer in Array 2 ungleich zu Array 1 sind
                    b++;
                    loken2[b][0] = loken1[i][0];
                    loken2[b][1] = loken1[i][1];
                    loken2[b][2] = loken1[i][2];
                    loken2[b][3] = loken1[i][3];
                    loken2[b][4] = loken1[i][4];
                    loken2[b][5] = loken1[i][5];
                    loken2[b][7] = loken1[i][7];
                    h++;
                //Wenn die Loknummer gleich ist, aber das Datum unterschiedlich
                }else if(loken1[i][2] != loken2[b][2]){
                    b++;
                    loken2[b][0] = loken1[i][0];
                    loken2[b][1] = loken1[i][1];
                    loken2[b][2] = loken1[i][2];
                    loken2[b][3] = loken1[i][3];
                    loken2[b][4] = loken1[i][4];
                    loken2[b][5] = loken1[i][5];
                    loken2[b][7] = loken1[i][7];
                    h++;
                }
            }else{
            }
        }
    return loken2 ;
    }

    static int Ruckgabe(){

        return h;
    }

    static void MontagSortieren() throws ParseException, IOException{
        Object loken[][]= (Object[][]) DatumAbgleich();
        Object loken2[][] = new Object[loken.length][8];
        int b = 0;
        int l = 0;
        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){

            loken2[0][0] = loken[0][0];
            loken2[0][1] = loken[0][1];
            loken2[0][2] = loken[0][2];
            loken2[0][3] = loken[0][3];
            loken2[0][4] = loken[0][4];
            loken2[0][5] = loken[0][5];
            loken2[0][7] = loken[0][7];
            l++;
            }
        String dateStr3 = "21-10-2025";     //Das Datum der aktiven Datensätze einlesen
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date monday = sdf.parse(dateStr3);

        for (int a = 0; a < loken2.length; a++ ){

            String start = (String) loken[a][3];
            String end = (String) loken[a][4];

            if(start !=null & end != null){  

                Date startZeit = sdf.parse(start);
                Date endZeit = sdf.parse(end);

                if(!monday.before(startZeit) && !monday.after(endZeit)){

                    if(loken[a][2] != loken2[b][2]) {           //Prüfen, ob die Loknummer in Array 2 ungleich zu Array 1 sind
                        b++;
                        loken2[b][0] = loken[a][0];
                        loken2[b][1] = loken[a][1];
                        loken2[b][2] = loken[a][2];
                        loken2[b][3] = loken[a][3];
                        loken2[b][4] = loken[a][4];
                        loken2[b][5] = loken[a][5];
                        loken2[b][7] = loken[a][7];
                        l++;

                        for (int e = 0; e < loken[0].length; e++ ){
                            System.out.print("" + loken2[b][e] + " ");
                        }
                         System.out.println(); 
                    }
                }
            }
        }
        System.out.print(l);
    }
}