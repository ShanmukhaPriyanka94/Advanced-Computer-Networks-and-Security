/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgeprocessing;

/**
 *
 * @author priyanka
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BridgeProcessing {
    
    public static File fdb = new File("/home/priyanka/Desktop/B00777449_Assignment 3/1st question/fdb.txt");
    public static File frame = new File("/home/priyanka/Desktop/B00777449_Assignment 3/1st question/frames.txt");
    public static File newfdb = new File("/home/priyanka/Desktop/B00777449_Assignment 3/1st question/newfdb.txt");
    public static int totalports;
    public static ArrayList <String> outputlist;
    public static ArrayList <String> framelist;
    public static HashMap <Character , Integer> fdbmap;
    public static HashMap <Character , Integer> newfdbmap;
    
    public static void FDBOutput()throws FileNotFoundException, IOException{
        String fdbrow;
        BufferedReader br = new BufferedReader(new FileReader(fdb));
        int line1=0;
        fdbmap = new HashMap <Character , Integer> ();
         while ((fdbrow = br.readLine()) != null) 
		    {   
                        if(line1==0){
                            line1++;
                            totalports = (int) fdbrow.charAt(0);
                        
                        }
                        else{
                            fdbrow.trim();
		    	    fdbrow = fdbrow.replaceAll("\\s+","");
                            
                            fdbmap.put(fdbrow.charAt(0), Character.getNumericValue(fdbrow.charAt(1)) );
                        }
                        
                    }
         System.out.println("FDB:");
         fdbmap.forEach((key, value) -> System.out.println(key + " : " + value));
         
          String framerow;
          framelist = new ArrayList <String> ();
          BufferedReader br1 = new BufferedReader(new FileReader(frame));
          while ((framerow = br1.readLine()) != null) 
		    {
                        framerow.trim();
		    	framerow = framerow.replaceAll("\\s+","");
                        framelist.add(framerow);
                    
                    }
          
         /*System.out.println("Incoming Frames:");
         for(int i=0;i<framelist.size();i++){
              
              System.out.println(framelist.get(i));
          
          }*/
         outputlist = new ArrayList <String> ();
         for(String Frame : framelist)
		{
			//String Frame = FrameList.remove(0);
			if(Frame.charAt(2) <= totalports)
			{
				if(! fdbmap.containsKey(Frame.charAt(0)) || fdbmap.get(Frame.charAt(0)) != Character.getNumericValue(Frame.charAt(2)))
				{
					outputlist.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" FDB Updated ");
				}
				if(fdbmap.containsKey(Frame.charAt(0)) && fdbmap.containsKey(Frame.charAt(1)) && fdbmap.get(Frame.charAt(0)) == fdbmap.get(Frame.charAt(1)))
				{
					outputlist.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame Discarded ");
				}
				if(fdbmap.containsKey(Frame.charAt(1)) && fdbmap.get(Frame.charAt(0)) != fdbmap.get(Frame.charAt(1)))
				{
					outputlist.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame sent on port "+fdbmap.get(Frame.charAt(1)));
				}
				if(! fdbmap.containsKey(Frame.charAt(1)))
				{
					outputlist.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame Broadcast on all ports");
				}
			}
			else
			{
				System.out.println("Invalid Frame: "+Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2));
			}
		}
         System.out.println("Output : ");
		for (String Frame : outputlist)
		{
			System.out.println(Frame);
		}
                newfdbmap = new HashMap <Character , Integer> ();
                newfdbmap = fdbmap;
                for (String Frame : framelist)
		{
			newfdbmap.put(Frame.charAt(0), Character.getNumericValue(Frame.charAt(2)));
		}
                
                System.out.println("Updated FDB:");
                newfdbmap.forEach((key, value) -> System.out.println(key + " : " + value));
                
             
            FileWriter fw = new FileWriter(newfdb);
           // System.out.println("FDB:");
         newfdbmap.forEach((key, value) -> {
            try {
                fw.write(key + " " + ":" + " " + value + "\n");
                
            } catch (IOException ex) {
                Logger.getLogger(BridgeProcessing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                
            fw.close();
    
    
    }
    
    
    

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        FDBOutput();
    }
    
}
