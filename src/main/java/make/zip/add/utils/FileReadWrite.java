package make.zip.add.utils;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class FileReadWrite {

	private static String input;
	private static String output;
	private static boolean help;

	public static ArrayList<String>getPaths(String[] args){
		ArrayList<String>savePaths = new ArrayList<String>();
		Options options = createOptions();
		
		if(parseOptions(options, args)) {
			if (help){
				printHelp(options);
				System.exit(0);
			}
			File dir = new File(input);
			File[] fileList = dir.listFiles();
			
			for(File subfile:fileList) {
				savePaths.add(subfile.getName());
			}
			
		}
		return savePaths;
	}
	
	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "JavaFinalProject";
		String footer ="";
		formatter.printHelp("JavaFinalProject", header, options, footer, true);
		
	}
	
	private static boolean parseOptions(Options options, String[] file) {
		CommandLineParser parser = new DefaultParser();
	
		
		try {
			CommandLine cmd = parser.parse(options, file);
			
			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			
		} catch (ParseException e) {
			printHelp(options);
			System.exit(0);			
		}
			
	
		return true;
	}
	
	private static Options createOptions() {
		Options options = new Options();
		
		//add options by using OptionBuilder
				options.addOption(Option.builder("i").longOpt("input")
						.desc("Set an input file path")
						.hasArg()
						.argName("Input path")
						.required()
						.build());
				
		// add options by using OptionBuilder
				options.addOption(Option.builder("o").longOpt("output")
						.desc("Set an output file path")
						.hasArg()
						.argName("Output path")
						.required()
						.build());

		// add options by using OptionBuilder
				options.addOption(Option.builder("h").longOpt("help")
						.desc("Show a Help page")
						//.hasArg()
						.argName("Help")
						//.required()
						.build());
		return options;
	}
	
	public static String getinput() {
		return input;
	}
	public static String getoutput() {
		return output;
	}
}
