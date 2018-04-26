/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitstream;

/**
 *
 * @author priyanka
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class BitStream {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException 
	{	
		String InputText = "/home/priyanka/Desktop/input.txt";	
		String GenOutput = "/home/priyanka/Desktop/StuffedFile.txt";
		FileToSend(InputText);
		FileToRecieve(GenOutput);	
	}
	
	public static void FileToSend(String Path)
	{
		String BinFile = "";
		String Stuffed = "";
		String NoStuffing = "";
                String GeneratedFile = "";
		try
		{
			byte[] FData = Files.readAllBytes(Paths.get(Path));
			StringBuilder binary = new StringBuilder();
		    for (byte b : FData)
		    {
		       int val = b;
		       for (int i = 0; i < 8; i++)
		       {
		          binary.append((val & 128) == 0 ? 0 : 1);
		          val <<= 1;
		       }
		    }
		    BinFile = binary.toString();
		    Stuffed = BinFile.replaceAll("11111", "111110");
		   
		}
	    catch (Exception e) 
		{
           //e.printStackTrace();
        }
		
		try 
		{
            File newTextFile = new File("/home/priyanka/Desktop/BinFile.txt");
                    try (FileWriter fw = new FileWriter(newTextFile)) {
                        fw.write(BinFile);
                    }
            
            File newTextFile2 = new File("/home/priyanka/Desktop/StuffedFile.txt");
                    try (FileWriter fw2 = new FileWriter(newTextFile2)) {
                        fw2.write(Stuffed);
                    }
        } 
		catch (IOException e) 
		{
        }
	}
	
	public static void FileToRecieve(String Path) throws IOException
	{
		String output = "";
		String line = "";
		String text = "";
	    try 
	    (BufferedReader br = new BufferedReader(new FileReader(Path))) {
	        line = br.readLine();
	       
	    }
	    File Extract = new File("/home/priyanka/Desktop/EStuffed.txt");
        try (FileWriter StuffedFileWriter = new FileWriter(Extract)) {
            StuffedFileWriter.write(line);
        }
		text = line.replaceAll("111110", "11111");
		for(int i = 0; i <= text.length() - 8; i+=8)
		{
		    int k = Integer.parseInt(text.substring(i, i+8), 2);
		    output += (char) k;
		} 
		File UnstuffedFile = new File("/home/priyanka/Desktop/NoStuffing.txt");
        try (FileWriter UnstuffedFileWriter = new FileWriter(UnstuffedFile)) {
            UnstuffedFileWriter.write(text);
        }
        
        File OutputFile = new File("/home/priyanka/Desktop/output.txt");
        try (FileWriter OutputFileWriter = new FileWriter(OutputFile)) {
            OutputFileWriter.write(output);
        }
	}

    
}
