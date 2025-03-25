package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtilities(String path)
	{
		this.path = path;
	}
	
	
	public int getRowCount(String XlSheet) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;	
	}
	
	public int getCellCount(String Xlsheet,int rownum) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(Xlsheet);
		row=ws.getRow(rownum);
		int colCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return colCount;
	}
	
	public String getCellData(String XlSheet,int rownum,int colnum) throws IOException {
		
		fi=new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
			
		String data;
		
		try {
			
			//data=cell.toString();  // both  methods use to read data from cell	
			DataFormatter formatter = new DataFormatter(); 
			data=formatter.formatCellValue(cell);
			
		}catch(Exception e) {
			
			data ="";
		}
		
		wb.close();
		fi.close();
		return data;
		
	}
	
	public  void setCellData(String XlSheet,int rownum,int colnum,String data) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		row=ws.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}

}
