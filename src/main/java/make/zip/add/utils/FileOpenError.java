package make.zip.add.utils;

public class FileOpenError extends Exception{

	public FileOpenError() {
		super("File path is too much!");
	}
	
	public FileOpenError(String message) {
		super(message);
	}
}
