package make.zip.add.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;


public class MakeFirstThread implements Runnable {
	
	ArrayList<String>save = new ArrayList<String>();
	ArrayList<String>number = new ArrayList<String>();
	ArrayList<String>Path = new ArrayList<String>();
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
			Path.add(input + "'\'" + n);
		}
		for(String p:Path) {
			readFileInZip(p);
		}
		
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
		        
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
