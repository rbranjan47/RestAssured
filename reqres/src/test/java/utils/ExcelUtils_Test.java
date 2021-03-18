package utils;

public class ExcelUtils_Test 
{
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		String sheetname = "Sheet1";
		
		String datapath = System.getProperty("user.dir");
		String excelpath = datapath+"/Excel_Data/JSON_DATADRIVEN.xlsx";
		excel_utility excelutils =  new excel_utility(sheetname, excelpath);
		
		excelutils.get_rowCount();
		excelutils.get_cellData(2,3);
		excelutils.get_cellData(0,3);
		excelutils.get_cellData(1,3);
		excelutils.get_cellData(4,3);
		excelutils.get_cellData(3,3);
	}
}
