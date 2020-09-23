package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFWorkbook wb;
	XSSFSheet sheet;
	DataFormatter formatter;

	public ExcelReader(String excelPath,int sheetNo) throws IOException {
		File file = new File(excelPath);
		FileInputStream fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(sheetNo);
		formatter = new DataFormatter();
		//System.out.println("ExcelReader Constructor exiting");
	}

	public String getCellValue(int row, int col) {
		//System.out.println("function getCellValue");
		Cell cell = sheet.getRow(row).getCell(col);
		String value = formatter.formatCellValue(cell);
		//System.out.println("value @ "+row+"  "+cell+"  =  "+value);
		//System.out.println("value = "+value);
		return value;
	}
	
	public Object[][] getDataFromExcel() {
		int rows = getRowsCount();
		int col = getColumnCount();
		String data[][] = new String[rows][col];
		for(int i = 1;i<=rows;i++) {
			//System.out.println("i = "+i);
			for(int j=0;j<col;j++) {
				//System.out.println("j = "+j);
				data[i-1][j] = getCellValue(i,j);
				//System.out.print("data = "+data[i-1][j]+"  ");
			}
			//System.out.println(" ");
		}
		return data;
	}

	public int getRowsCount() {
		int noOfRows = (sheet.getLastRowNum() ) ;
		//System.out.println("noOfRows = "+noOfRows);
		return noOfRows;
	}
	public int getColumnCount() {
		int noOfColumns = (sheet.getRow(0).getLastCellNum() );
		//System.out.println("noOfColumns = "+noOfColumns);
		return noOfColumns;
	}
}
