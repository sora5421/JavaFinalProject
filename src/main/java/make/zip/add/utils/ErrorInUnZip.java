package make.zip.add.utils;

public class ErrorInUnZip extends Exception{

	public ErrorInUnZip() {
		super("UnZip is wrong. Check the zip file.");
	}
	
	public ErrorInUnZip(String message) {
		super(message);
	}
}
