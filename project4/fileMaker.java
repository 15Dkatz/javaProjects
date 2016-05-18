import java.io.IOException;
import java.io.PrintWriter;

public class fileMaker {
	
	String fileName = "";
	PrintWriter outFile;
	
	
	public fileMaker(String fileName) throws IOException {
		super();
		
		this.fileName = fileName;
		
		outFile = new PrintWriter(fileName);
		
	}
	
//	method to write to the outFile
	public void writeToFile(String text) {
		outFile.print(text + " ");
		outFile.println();
	}
	
	public void writeToFile(int text) {
		outFile.print(text + " ");
		outFile.println();
	}
	
	public void println() {
		outFile.println();
	}
	
	public void closeFile() {
		outFile.close();
		
	}
	
	
//	default methods
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	@Override
	public String toString() {
		return "fileWrite [fileName=" + fileName + "]";
	}

	

	

	
	
}