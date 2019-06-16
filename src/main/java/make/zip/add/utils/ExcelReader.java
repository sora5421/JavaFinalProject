package make.zip.add.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelReader {
	
	public ArrayList<String> getData(InputStream is) {
		ArrayList<String> values = new ArrayList<String>();
		int i = 0,j = 0;
		try (InputStream inp = is) {
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        while(true) {
		        	Row row = sheet.getRow(i);//
		        	if(row == null) break;
		        	
		        	Cell cell = row.getCell(j);// 
		        	j++;
		        	if (cell == null) {
			            j = 0;
			            i++;
			        }
			        else {
			        	if(isStringDouble(cell)) {
			        		double k;
			        		k = cell.getNumericCellValue();
			        		String s = String.valueOf((int)k);
			        		values.add(s);
			        	}
			        	else {
			        		values.add(cell.getStringCellValue()); 
			        	}
			        }
		        }
		        
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return values;
	}
	
	public static boolean isStringDouble(Cell cell){
		try {
			cell.getStringCellValue();
			return false;
		}catch(IllegalStateException e) {
			return true;
		}
	  }
}
