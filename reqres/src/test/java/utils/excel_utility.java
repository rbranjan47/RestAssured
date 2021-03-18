package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel_utility 
{
	public static XSSFWorkbook workbook;
	public static XSSFSheet Sheetbook;
	
	//constructor to get the Sheet name and class name
	public excel_utility(String sheetname, String excelpath)
	{
		try
		{	
		 workbook = new XSSFWorkbook(excelpath);
		Sheetbook =  workbook.getSheet(sheetname);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public static void get_cellData(int rowNum, int colNum)
	{
		
		String value_1 = Sheetbook.getRow(0).getCell(0).getStringCellValue();
		System.out.println(value_1);
		
		//to reduce this decimal value, we can use the Data formatter
		DataFormatter formatter = new DataFormatter();
		
	Object value_2 = 	formatter.formatCellValue(Sheetbook.getRow(rowNum).getCell(colNum));
		
		//double value_2 =  Sheetbook.getRow(3).getCell(4).getNumericCellValue();
		System.out.println(value_2);
		
	}
	
	
	public static void get_rowCount()
	{
		//number of rows
		int rowscount = Sheetbook.getPhysicalNumberOfRows();
		System.out.println(rowscount);
	}
}
