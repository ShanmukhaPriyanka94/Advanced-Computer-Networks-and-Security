/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataencoding;

/**
 *
 * @author priyanka
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataEncoding {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        // TODO code application logic here
        
        String input = "/home/priyanka/Desktop/InputFile.txt";
        ConvertBitstream(input);
    }

    private static void ConvertBitstream(String input) {
  
                String BinaryFile="";
		try
		{
                        
                    byte[] FData = Files.readAllBytes(Paths.get(input));
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
		    BinaryFile = binary.toString();
                   
		}
	    catch (Exception e) 
		{
                    e.printStackTrace();
                }
		
		try 
		{
            File newTextFile = new File("/home/priyanka/Desktop/Binary.txt");
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(BinaryFile);
            fw.close();
            GenerateWaveforms(newTextFile);
                }
                catch (IOException e) 
		{
            e.printStackTrace();
        }
    }

    private static void GenerateWaveforms(File BinaryFile)  {
     
        try {
      FileInputStream fis = new FileInputStream(BinaryFile);
      char current;
      int count = 0;
      int t=0; //transitions
      int one=0;
      int zero=0;
      String prev="";
      
      System.out.print("NRZ Waveform");
      while (fis.available() > 0) {
          
        current = (char) fis.read();
        
        
        String s=current+"";
        if(count==0){
            if(Integer.parseInt(s)==1)
            { 
            System.out.print("-");
            prev="-";
            count++;
            one+=1;
        
            }
            else if(Integer.parseInt(s)==0)
                {
                    System.out.print("_");
                    prev="_";
                    count++;
                    zero+=1;
                
                }
        }
        else
        {
        if(Integer.parseInt(s)==1)
        { 
            if("-".equals(prev) || "|-".equals(prev)){
            System.out.print("-");
            prev="-";
            }
            else{
                System.out.print("|-");
                prev="|-";
                t+=1;
            
            }
             one+=1;
        }
        
        else if(Integer.parseInt(s)==0)
                { 
                    if("_".equals(prev) ||"|_".equals(prev) ){
                        System.out.print("_");
                        prev = "_";
                    }
                    else{
                        System.out.print("|_");
                        prev="|_";
                        t+=1;
            
            }
                    zero+=1;
                }
        }
        
      }
      System.out.println("");
      System.out.println("Total no. of ones = "+ one);
      System.out.println("Total no. of zeros = "+ zero);
      System.out.println("Total no. of transitions in NRZ = "+ t);
      System.out.println("");
      FileInputStream fis2 = new FileInputStream(BinaryFile);
      String p="";
      int tm=0;
      
      char current1;
      System.out.print("Manchester Waveform:");
      while (fis2.available() > 0) {
        current1 = (char) fis2.read();
        //System.out.print(current);
        String s=current1+"";
         
        if(Integer.parseInt(s)==1)
        { 
            if("_|-".equals(p) || "|_|-".equals(p)){
                System.out.print("|_|-");
                p="|_|-";
                tm+=2;
            
            }
            else{
                System.out.print("_|-");
                p="_|-";
                tm+=1;
            }
            
            
        }
        else if(Integer.parseInt(s)==0)
                {
                    if("-|_".equals(p) || "|-|_".equals(p)){
                        System.out.print("|-|_");
                        p="|-|_";
                        tm+=2;
                    }
            
            
            else{
                System.out.print("-|_");
                p="-|_";
                tm+=1;
                    }
            
                } 
      }
      System.out.println();
      System.out.println("No.of transitions in manchester = "+tm);
      
    } 
        catch (IOException e) {
      e.printStackTrace();
    }
}
}
