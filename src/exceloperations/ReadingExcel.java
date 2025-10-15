package exceloperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class ReadingExcel {
    public static void main(String[] args) throws IOException, ParseException {
        
        String excelFilePath = "/Users/lukas/Documents/ExcelReader2/ExcelReader2/Datafiles/AbstellungenKW42.xlsx";
        FileInputStream inputstream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(inputstream); 
        XSSFSheet sheet =  workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(5).getLastCellNum();

        Object loken[][] = new Object[rows-5][cols];
        int i = 0;

        for( int r = 6 ; r <= rows; r++){
            XSSFRow row = sheet.getRow(r);
            XSSFCell cellLok = row.getCell(2);
            XSSFCell cellArt = row.getCell(5);

            if(!cellArt.getStringCellValue().contains("aREP")){

                if(!cellLok.getStringCellValue().contains("1142")& !cellLok.getStringCellValue().contains("1163")& !cellLok.getStringCellValue().contains("1064")& !cellLok.getStringCellValue().contains("1063")){

                    for(int c = 0; c < cols; c++){
                        XSSFCell cell1 = row.getCell(c); 

                        if (cell1.getCellType()== CellType.STRING){
                            String text = cell1.getStringCellValue();
                            loken[i][c] = text;

                        } else if (cell1.getCellType()== CellType.NUMERIC){
                            Date text = cell1.getDateCellValue();
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");   //Korrektur Format Datum
                            String datum = format.format(text);
                            loken[i][c] = datum;
                        }  

                    }
                    i++;
                }  
            } 
        }
        Object loken3[][]= (Object[][]) Sortieren.DatumAbgleich(loken);
       
        for (int a = 0; a < Sortieren.Ruckgabe(); a++ ){
            for (int b = 0; b < loken3[0].length; b++ ){
                System.out.print("" + loken3[a][b] + " ");
        }
         System.out.println(); 
        }
        System.out.print(Sortieren.Ruckgabe());
    }
}