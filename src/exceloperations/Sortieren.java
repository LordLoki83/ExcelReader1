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
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
    

    static Object ExcelSort() throws IOException{
        
        String excelFilePath = "/Users/lukas/Documents/ExcelReader2/ExcelReader2/Datafiles/AbstellungenKW44.xlsx"; //Zellverbund muss aufgehoben werden
        FileInputStream inputstream = new FileInputStream(excelFilePath);
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputstream)) {
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
                                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");   //Korrektur Format Datum
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
    }

    
    static Object DatumAbgleich() throws ParseException, IOException{
        Object loken1[][]= (Object[][]) ExcelSort();
        Object loken2[][] = new Object[loken1.length][9];
        int b = 0;
        
        //Die erste Stelle in Loken2 befüllen, wenn diese leer ist
        if (loken2[0][0]== null){
            String dateStrErst = (String) loken1[0][3];       //Das Datum der aktiven Datensätze einlesen
            String dateStrErst1 = (String) loken1[0][4];      //Das Datum der aktiven Datensätze 
            String [] datumNeu = dateStrErst.split(" ");
            String [] datumNeuZwei = dateStrErst1.split(" ");
            String datumEins = datumNeu[0];
            String datumZwei = datumNeuZwei[0];
            String zeitEins = datumNeu[1];
            String zeitZwei = datumNeuZwei[1];
            Date datumFinal = sdf.parse(datumEins);
            Date datumFinalZwei = sdf.parse(datumZwei);
            String lok1DateOne = sdf.format(datumFinal);
            String lok1DateTwo = sdf.format(datumFinalZwei);
            //String lok1TimeOne = sdf2.format(zeitEins);
            //String lok1TimeTwo = sdf2.format(zeitZwei);
            loken2[0][0] = loken1[0][0]; //Region
            loken2[0][1] = loken1[0][1]; //Standort
            loken2[0][2] = loken1[0][2]; //Triebfahrzeug
            loken2[0][3] = lok1DateOne; //Datum von
            loken2[0][4] = zeitEins;  //Zeit von
            loken2[0][5] = lok1DateTwo; //Datum bis
            loken2[0][6] = zeitZwei;  //Zeit bis
            loken2[0][7] = loken1[0][5];
            loken2[0][8] = loken1[0][7];
            h++;
            }
        //Prüfen, ob Loken1 gleich Loken2 ist    
        for (int i = 0; i < loken2.length; i++){
            String dateStr = (String) loken1[i][3];       //Das Datum der aktiven Datensätze einlesen
            String dateStr1 = (String) loken1[i][4];      //Das Datum der aktiven Datensätze einlesen
            String dateStr2 = (String) loken2[b][3];     //Das Datum der aktiven Datensätze einlesen
            String dateStr3 = (String) loken2[b][5];    //Das Datum der aktiven Datensätze einlesen
           
            if(dateStr !=null & dateStr1 != null){      //Prüfen, ob einer der Datums-Datensätze null auswirft
                
                //String lok1Time1 = sdf2.format(lok1Date1);
                //String lok1Time2 = sdf2.format(lok1Date2);
                Date lok1Date1 = sdf.parse(dateStr);
                Date lok1Date2 = sdf.parse(dateStr1);
                Date lok2Date1 = sdf.parse(dateStr2);       //Umwandeln der Datum-Strings in Date-Datensätze
                Date lok2Date2 = sdf.parse(dateStr3);

                if(loken1[i][2] == loken2[b][2] & !lok1Date1.equals(lok2Date1) & !lok1Date2.equals(lok2Date2) ) {           //Prüfen, ob die Loknummer in Array 2 ungleich zu Array 1 sind
                    b++;
                    String dateStrErst1 = (String) loken1[i][3];       //Das Datum der aktiven Datensätze einlesen
                    String dateStrErstOne1 = (String) loken1[i][4];      //Das Datum der aktiven Datensätze 
                    String [] datumNeu1 = dateStrErst1.split(" ");
                    String [] datumNeuZwei1 = dateStrErstOne1.split(" ");
                    String datumEins1 = datumNeu1[0];
                    String datumZwei1 = datumNeuZwei1[0];
                    String zeitEins1 = datumNeu1[1];
                    String zeitZwei1 = datumNeuZwei1[1];
                    Date datumFinal1 = sdf.parse(datumEins1);
                    Date datumFinalZwei1 = sdf.parse(datumZwei1);
                    String lok1DateOne1 = sdf.format(datumFinal1);
                    String lok1DateTwo1 = sdf.format(datumFinalZwei1);

                    loken2[b][0] = loken1[i][0];//Region
                    loken2[b][1] = loken1[i][1];//Standort
                    loken2[b][2] = loken1[i][2];//Triebfahrzeug
                    loken2[b][3] = lok1DateOne1;//Datum von
                    loken2[b][4] = zeitEins1;          //Zeit von
                    loken2[b][5] = lok1DateTwo1;//Datum bis
                    loken2[b][6] = zeitZwei1;          //Zeit bis
                    loken2[b][7] = loken1[i][5];
                    loken2[b][8] = loken1[i][7];
                    h++;

                //Wenn die Loknummer gleich ist, aber das Datum unterschiedlich
                }else if(loken1[i][2] != loken2[b][2]){
                    b++;
                    String dateStrErst2 = (String) loken1[i][3];       //Das Datum der aktiven Datensätze einlesen
                    String dateStrErstOne2 = (String) loken1[i][4];      //Das Datum der aktiven Datensätze 
                    String [] datumNeu2 = dateStrErst2.split(" ");
                    String [] datumNeuZwei2 = dateStrErstOne2.split(" ");
                    String datumEins2 = datumNeu2[0];
                    String datumZwei2 = datumNeuZwei2[0];
                    String zeitEins2 = datumNeu2[1];
                    String zeitZwei2 = datumNeuZwei2[1];
                    Date datumFinal2 = sdf.parse(datumEins2);
                    Date datumFinalZwei2 = sdf.parse(datumZwei2);
                    String lok1DateOne2 = sdf.format(datumFinal2);
                    String lok1DateTwo2 = sdf.format(datumFinalZwei2);
                    loken2[b][0] = loken1[i][0];//Region
                    loken2[b][1] = loken1[i][1];//Standort
                    loken2[b][2] = loken1[i][2];//Triebfahrzeug
                    loken2[b][3] = lok1DateOne2;//Datum von
                    loken2[b][4] = zeitEins2;          //Zeit von
                    loken2[b][5] = lok1DateTwo2;//Datum bis
                    loken2[b][6] = zeitZwei2;          //Zeit bis
                    loken2[b][7] = loken1[i][5];
                    loken2[b][8] = loken1[i][7];
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

   
}