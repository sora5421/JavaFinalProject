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


public class MakeFirstThread implements Runnable {
	
	ArrayList<String>save = new ArrayList<String>();
	ArrayList<String>number = new ArrayList<String>();
	ArrayList<String>Path = new ArrayList<String>();
	ArrayList<String>csv = new ArrayList<String>();
	String input;
	String output;

	public MakeFirstThread(ArrayList<String> paths, String in, String out) {
		number = paths;
		input = in;
		output = out;
	}

	@Override
	public void run() {
		for(String n: number){
			Path.add(input + "\\" + n);
		}
		for(String p:Path) {
			readFileInZip(p);
		}
		Make1();
		
	}
	
	public void readFileInZip(String path) {
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

		    
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
	
	/*public ArrayList<String>Make(){
		ArrayList<String>m = new ArrayList<String>();
		int i = 7;
		int j = 0;
		for(int i = 0; i< 7; i++)
		m.add("이름"+","+save.get(0)+","+save.get(1)+","+save.get(2)+","+save.get(3)+","+save.get(4)+","+save.get(5)+","+save.get(6));
		while(!m.isEmpty()) {
			if(save.get(i) == "" && save.get(i+1) == "")break;
			if(save.get(i).equals(save.get(0))) {
				i = i + 7;
				j++;
			}
			else {
				m.add(number.get(j)+","+save.get(i)+","+save.get(i+1)+","+save.get(i+2)+","+save.get(i+3)+","+save.get(i+4)+","+save.get(i+5)+","+save.get(i+6));
				//System.out.println(save.get(i+6));
				i = i + 7;
			}
			
		}
		return m;
	}
	
	public void wirteAFile(ArrayList<String>csv, String output) {
		String result = output.replace("result", "result1");
		
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(result);
		}catch(FileNotFoundException e){
			File tmp = new File(result);
			tmp.getParentFile().mkdirs();
			try {
				tmp.createNewFile();
				outputStream = new PrintWriter(output);
			} catch (IOException e1) {
				
			}
		}
		for(String out:csv) {
			outputStream.println(out);
		}
		outputStream.close();
	}
	*/
	public void Make1() {
		try {
		String result = output.replace("results", "result1");
		HSSFWorkbook writebook = new HSSFWorkbook();
		String zone = "sheet1";
		HSSFSheet mySheet = writebook.createSheet(zone);
		 
		 int rowIndex = 0;
		 HSSFRow row;
		 int j = 7, k = 0;
		 row = mySheet.createRow(0);
		 HSSFCell cell1 = row.createCell(0);
		 cell1.setCellValue("이름");
		 
		 for(int i = 1; i < 7; i++) {
			 HSSFCell cell = row.createCell(i);
			 cell.setCellValue(save.get(i-1));
		 }
		 while(!save.isEmpty()) {
			 row = mySheet.createRow(++rowIndex);
			 HSSFCell cell2 = row.createCell(0);
			 cell2.setCellValue(number.get(k));
			 
			 for(int i = 1; i < 7; i++) {
				 if(save.get(i+j-1).equals(save.get(0))) {
					 rowIndex--;
					 k++;
					 break;
				 }
				 HSSFCell cell = row.createCell(i);
				 cell.setCellValue(save.get(i+j-1));
			 }
			 j = j +7;
			 if(save.get(j) == "")break;
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
