import java.io.*;
import test.TradeDcc;

public class InputFile {

	private final int fillerLength = 1;
	private int[] fieldFixedLength = {6,4,12,8,40,19,4,12,12,6,8,15,1,8};/*各字段长度*/
	private String[] fieldName = {};
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		File inputFile = new File(".");
//		System.out.println(inputFile.getCanonicalPath());
		BufferedReader br = new BufferedReader(new FileReader("tmp/BOCOM_PIF_20120524.txt"));
		String oneline = null;
		/*while(null!=(oneline=br.readLine())){
			System.out.println(oneline);
			new InputFile().fillerByFixedLength(oneline);
		}*/
		new InputFile().fillerByFixedLength(br.readLine());
	}

	
	public TradeDcc fillerByFixedLength(String oneline){
		int index = 0;
		for(int i=0; i< fieldFixedLength.length; i++){
			System.out.println(oneline);
			System.out.print("index:"+index+"value:");
			System.out.println(oneline.substring(index, index+fieldFixedLength[i]));
			index+=fieldFixedLength[i]+fillerLength;
		}
		
		return new TradeDcc();
	}
}
