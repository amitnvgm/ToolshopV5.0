package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
  @DataProvider(name ="InvalidLoginDataExcel")
  public String [][] getData() throws IOException 
  {
	  String excelfilepath = ".\\testdata\\invaliddata.xlsx";
	  ExcelUtilities excel = new ExcelUtilities(excelfilepath);
	  int rows=excel.getRowCount("Sheet1");
	  int cols = excel.getCellCount("Sheet1", 1);
	  
	  System.out.println(rows);
	  System.out.println(cols);
	  
	  String logindata[][] = new String [rows][cols];
	  
	  for(int r=1;r<=rows;r++)
	  {
		  for(int c=0;c<cols;c++)
		  {
			  logindata[r-1][c]=excel.getCellData("Sheet1", r, c);
			  System.out.println(logindata[r-1][c]);
		  }
	  }
	  return logindata;
	  
  }
  

  @DataProvider(name ="InvalidLoginData")
  public Object [][] getdata()
  {
	return new Object[][]
			{
				{"abc@g.com" , "wel"},
				{"xyz","wel01"},
				{"pqrst", ""},
			};
  }


}
