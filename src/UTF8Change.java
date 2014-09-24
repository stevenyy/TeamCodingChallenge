import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 


public class sample {
	public static List<String> ulist = new ArrayList<String>();
	public static void main(String[] args) throws IOException{
		String address = "/Users/Hadoop/Downloads/PracticeInput.txt";
		BufferedReader br;
		String line;
		Scanner scanner = new Scanner(System.in);
//		try {
//			br = new BufferedReader(new FileReader(address));
			while((line = scanner.nextLine())!=null){
//			while((line = br.readLine())!=null){
				processLine(line);
			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	private static void processLine(String line) {
		List<String> bigList = new ArrayList<String>();
		String newline = line.toLowerCase();
		
		String[] array1 = newline.split("@");
//		System.out.println("splitting" + array1[0]);
//		System.out.println("splitting" + array1[1]);
		bigList.add(array1[0]);
		String[] array2 = array1[1].split("\\.");
		bigList.add(array2[0]);
		bigList.add(array2[1]);
		
		String ret = ""; 
		String sub1 = subprocess(bigList.get(0));
		ret = ret + sub1 + "@";
		String sub2 = subprocess(bigList.get(1));
		ret = ret + sub2 + ".";
		String sub3 = subprocess(bigList.get(2));
		ret = ret + sub3;
		
//		System.out.println("the returing string is " + ret);
		
		StringBuilder string = new StringBuilder(ret);
		if (ulist.size() != 0){
			string.append("~");
			for (String a: ulist){
				string.append(a);
			}
		}
		System.out.println(string.toString());
	}
	
	private static String subprocess(String string) {
		// TODO Auto-generated method stub
		List<String> tempList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int ascii = 0;
		for (int i = 0; i< string.length(); i++){
			char now = string.charAt(i);
//			System.out.println("the char is " + now);
			//			ascii = Character.getNumericValue(newline.charAt(i));
			ascii = (int) now;
//			System.out.println("now's ascii value is " + ascii);
			if ((ascii > 33) && (ascii < 126)){
//				System.out.println("printing the index" + i);
				sb.append(now);
				continue;
			}
			else{
				ulist.add(String.valueOf(now));
				tempList.add(String.valueOf(now));
			}
			String comb = utf(String.valueOf(string.charAt(i)));
			String insert = "+" + i + "?" + comb;
			sb.append(insert);
		}
		return sb.toString();
	}
	
	public static String utf(String c){
		String ret = "";
		try {
			byte[] bytes = c.getBytes("UTF-8");
			ret = bytesToHex(bytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;		
	}
	
	//	static public String byteToHex(byte b) {
	//		// Returns hex String representation of byte b
	//		char hexDigit[] = {
	//				'0', '1', '2', '3', '4', '5', '6', '7',
	//				'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	//		};
	//		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
	//		return new String(array);
	//	}
	//
	//	static public String charToHex(char c) {
	//		// Returns hex String representation of char c
	//		byte hi = (byte) (c >>> 8);
	//		byte lo = (byte) (c & 0xff);
	//		return byteToHex(hi) + byteToHex(lo);
	//	}



	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}



}
