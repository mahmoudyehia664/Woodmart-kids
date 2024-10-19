package data;

import annotation.DataProviderIndex;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExcelData {
    @DataProvider
    public Object[][] getData(Method method) throws IOException {
        DataProviderIndex indexAnnotation = method.getAnnotation(DataProviderIndex.class);
        int index = indexAnnotation.value();
        DataFormatter formatter=new DataFormatter();
        FileInputStream file=new FileInputStream("src/test/resources/data/Data.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(file);
        XSSFSheet loginSheet=wb.getSheetAt(index);
        int rowCount=loginSheet.getPhysicalNumberOfRows();
        XSSFRow row=loginSheet.getRow(0);
        int columnCount=row.getLastCellNum();
        Object[][] data=new Object[rowCount-1][columnCount];
        for (int i=0;i<rowCount-1;i++){
            row = loginSheet.getRow(i+1);
            for (int j=0;j<columnCount;j++){
                data[i][j]=formatter.formatCellValue(row.getCell(j));
            }
        }
        return data;
    }
}
