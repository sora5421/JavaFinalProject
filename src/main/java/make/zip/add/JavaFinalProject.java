package make.zip.add;

import java.util.ArrayList;

import make.zip.add.utils.FileOpenError;
import make.zip.add.utils.FileReadWrite;
import make.zip.add.utils.MakeFirstThread;
import make.zip.add.utils.MakeSecondThread;

public class JavaFinalProject{

	public void run(String[] args) {
		
		try {
			if(args.length<6)
				throw new FileOpenError();
		} catch (FileOpenError e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		ArrayList<String> Paths = FileReadWrite.getPaths(args);
		
		String in = FileReadWrite.getinput();
		String out = FileReadWrite.getoutput();
		
		Runnable obj1 = new MakeFirstThread(Paths,in,out);
		Runnable obj2 = new MakeSecondThread(Paths,in,out);
		
		Thread th1 = new Thread(obj1);
		Thread th2 = new Thread(obj2);
		
		th1.start();
		th2.start();
	}

}

