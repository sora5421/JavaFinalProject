package make.zip.add;

import java.util.ArrayList;
import make.zip.add.utils.FileReadWrite;
import make.zip.add.utils.MakeFirstThread;
import make.zip.add.utils.MakeSecondThread;

public class JavaFinalProject{

	public void run(String[] args) {
		
		ArrayList<String> Paths = FileReadWrite.getPaths(args);
		
		String in = FileReadWrite.getinput();
		String out = FileReadWrite.getoutput();
		
		Runnable obj1 = new MakeFirstThread(Paths,in,out);
		Runnable obj2 = new MakeSecondThread();
		
		Thread th1 = new Thread(obj1);
		Thread th2 = new Thread(obj2);
		
		th1.start();
		th2.start();
	}

}

