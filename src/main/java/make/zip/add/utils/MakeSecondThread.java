package make.zip.add.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class MakeSecondThread implements Runnable {
	
	ArrayList<String>save = new ArrayList<String>();
	ArrayList<String>number = new ArrayList<String>();
	ArrayList<String>Path = new ArrayList<String>();
	ArrayList<String>csv = new ArrayList<String>();
	String input;
	String output;

	public MakeSecondThread(ArrayList<String> paths, String in, String out) {
		number = paths;
		input = in;
		output = out;
	}

	@Override
	public void run() {
		int i = 0;
		for(String n: number){
			Path.add(input + "\\" + n);
		}
		for(String p:Path) {
			readFileInZip(p);
		}
		Make2();
		
	}
	
	public void readFileInZip(String path) {
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

		    
		    	ZipArchiveEntry entry1 = entries.nextElement();
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		    
		        ExcelReader myReader = new ExcelReader();
		        
		        for(String value:myReader.getData(stream)) {
		        	save.add(value);
		        }
		        
		    zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Make2() {
		try {
		String result = output.replace("results", "result2");
		HSSFWorkbook writebook = new HSSFWorkbook();
		String zone = "sheet1";
		HSSFSheet mySheet = writebook.createSheet(zone);
		 
		 int rowIndex = 1;
		 HSSFRow row;
		 int j = 5, k = 0;
		 
		 row = mySheet.createRow(0);
		 for(int i = 0; i < 4; i++) {
			 HSSFCell cell = row.createCell(i);
			 cell.setCellValue(save.get(i));
		 }
		 
		 row = mySheet.createRow(1);
		 HSSFCell cell1 = row.createCell(0);
		 cell1.setCellValue("ÀÌ¸§");
		 
		 for(int i = 4; i < 9; i++) {
			 HSSFCell cell = row.createCell(i-3);
			 cell.setCellValue(save.get(i));
		 }
		 
		 while(!save.isEmpty()) {
			 row = mySheet.createRow(++rowIndex);
			 HSSFCell cell2 = row.createCell(0);
			 cell2.setCellValue(number.get(k));
			 
			 try {
				 for(int i = 4; i < 9; i++) {
					 if(save.get(i+j).equals(save.get(0))) {
						 rowIndex--;
						 k++;
						 j--;
						 break;
					 }if(save.get(i+j).equals(save.get(4))) {
						 rowIndex--;
						 break;
					 }
					 HSSFCell cell = row.createCell(i-3);
					 cell.setCellValue(save.get(i+j));
				 }
				 j = j +5;
			 }catch(IndexOutOfBoundsException e) {
				 cell2.setCellValue("");
				 break;
			 }
			 
		 }
		
		 
			 File file = new File(result);
			 FileOutputStream output = new FileOutputStream(file);
			writebook.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

}
