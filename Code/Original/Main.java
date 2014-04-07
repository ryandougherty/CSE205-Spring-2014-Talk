
import java.io.*;
import java.nio.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Usage: javac Main <file> <speed> (> 0 for fast, 0 for slow, < for no string)");
			return;
		}
		int speed = Integer.parseInt(args[1]); // 1 for fast
		byte[] byteArray = new byte[4];
		String s = "";	
		StringBuilder builder = new StringBuilder();
		long minMemory = Runtime.getRuntime().freeMemory();


		try {
			long start = System.nanoTime();
			File file = new File(args[0]);
			long file_size = file.length();
			FileInputStream file_input = new FileInputStream (file);
			DataInputStream data_in    = new DataInputStream (file_input );

			while (true) 
			{
				try 
				{
					for (int index = 0; index < 4; index++)
					{
						byteArray[index] = data_in.readByte();
					}         
				}
				catch (Exception eof) 
				{
					break;
				}
					
				float f = readFloatLittleEndian(byteArray);

				if (speed == 0) {
					// slow
					s += f + "\n";
				} else if (speed > 0) {
					// fast
					builder.append(f);
					builder.append("\n");
				}

				long mem = Runtime.getRuntime().freeMemory();
				if (mem < minMemory) {
					minMemory = mem;
				}
			}
			data_in.close();  

			if (speed > 0)
				s = builder.toString();
			if (speed != 0) {
				PrintWriter out = new PrintWriter("output.txt");
				out.write(s);
			}
			

			long end = System.nanoTime();
			long total = end - start;
			System.out.println("Time to complete: " + total/1e9);
			System.out.println("Minimum memory: " + minMemory/1e6 + "MB");
		} 
		catch  (Exception e) 
		{
			System.err.println(e.toString());
		}

			
	}

	// aka magic!
	public static float readFloatLittleEndian(byte[] byteArr)
	{
		int accum = 0;
		for (int index = 0; index < 4; index++)
		{
			accum |= (byteArr[index] & 0xff ) << index*8;
		}
		return Float.intBitsToFloat( accum );
	}
}