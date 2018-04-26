/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bridgeprocessingflowchart;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
 

/**
 *
 * @author priyanka
 */
public class BridgeProcessingFlowchart {


        public static HashMap <Character , Integer> FDBMap;
	public static ArrayList <String> FrameList;
	public static ArrayList <String> FrameOPList;
	public static File FDB;
	public static File Frame;
	public static int Ports;
	public static HashMap <Character , Integer> NewFDB;
	
	
	public static boolean Verification() throws FileNotFoundException, IOException
	{
		Boolean VerificationFDB = true;
		Boolean VerificationFrame = true;
		FDBMap = new HashMap <Character , Integer> ();
		FrameList = new ArrayList <String> ();
		try (BufferedReader br = new BufferedReader(new FileReader(FDB))) 
		{
		    String LineofFDB;
		    int LineNum = 1;
		    
		    while ((LineofFDB = br.readLine()) != null) 
		    {   
		    	if (LineNum == 1)
		        {
			    	LineNum ++;   
			    	//LineofFDB.trim();
			    	//LineofFDB = LineofFDB.replaceAll("\\s+","");
			    	   if (LineofFDB.length() != 1 || ! Character.isDigit(LineofFDB.charAt(0)))
			    	   {
			    		   VerificationFDB = VerificationFDB && false; 
			    	   }
			    	   else if(LineofFDB.length() == 1 && Character.isDigit(LineofFDB.charAt(0)))
			    	   {
			    		   Ports = (int) LineofFDB.charAt(0);
			    	   }
		        }
		       else
		       {
		    	   LineofFDB.trim();
		    	   LineofFDB = LineofFDB.replaceAll("\\s+","");
		    	   if (! ( Character.isAlphabetic(LineofFDB.charAt(0)) ) || ! Character.isDigit(LineofFDB.charAt(1)))
		    	   {
		    		   VerificationFDB = VerificationFDB && false;
		    	   }
		    	   else if(Character.isAlphabetic(LineofFDB.charAt(0)) && Character.isDigit(LineofFDB.charAt(1)))
		    	   {
		    		   FDBMap.put(LineofFDB.charAt(0), Character.getNumericValue(LineofFDB.charAt(1)) );
		    	   }
		       }
		    }
		}
		if (VerificationFDB)
		{
			System.out.println("Valid FDB");
		}
		else
		{
			System.out.println("Invalid FDB");
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(Frame))) 
		{
		    String LineofFrame;
		    while ((LineofFrame = br.readLine()) != null) 
		    {
		       LineofFrame.trim();
		       LineofFrame = LineofFrame.replaceAll("\\s+","");
	    	   if (! ( Character.isAlphabetic(LineofFrame.charAt(0)) ) || ! Character.isAlphabetic(LineofFrame.charAt(1)) || ! Character.isDigit(LineofFrame.charAt(2)))
	    	   {
	    		   VerificationFrame = VerificationFrame && false;
	    	   }
	    	   else if (Character.isAlphabetic(LineofFrame.charAt(0)) && Character.isAlphabetic(LineofFrame.charAt(1)) && Character.isDigit(LineofFrame.charAt(2)))
	    	   {
	    		   FrameList.add(LineofFrame);
	    	   }
		    }
		}
		
		if (VerificationFrame)
		{
			System.out.println("valid Frame\n");
		}
		else
		{
			System.out.println("Invalid Frame\n");
			//System.out.println("Please ensure the formatting of files as specified in the requirement.");
		}
		return VerificationFDB && VerificationFrame;
	}


	public static void ChooseRequiredFile()
	{
		JFileChooser FDBChooser = new JFileChooser();
		FDBChooser.setDialogTitle("Open");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
		FDBChooser.setFileFilter(filter);
		FDBChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int ValidatationOutput = FDBChooser.showOpenDialog(null);
		if (ValidatationOutput == JFileChooser.APPROVE_OPTION) 
		{
		    FDB = FDBChooser.getSelectedFile();
		    System.out.println("FDB File Chosen: " + FDB.getAbsolutePath());
		}
		
		JFileChooser FrameChooser = new JFileChooser();
		FrameChooser.setDialogTitle("Open Frame File");
		FileNameExtensionFilter frmfilter = new FileNameExtensionFilter("Text Files", "txt");
		FrameChooser.setFileFilter(frmfilter);
		FrameChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int frmValidatationOutput = FrameChooser.showOpenDialog(null);
		if (frmValidatationOutput == JFileChooser.APPROVE_OPTION) 
		{
		    Frame = FrameChooser.getSelectedFile();
		    System.out.println("Frame File Chosen:" + Frame.getAbsolutePath() + "\n");
		}
	}





	
	public static void Output() throws FileNotFoundException
	{
		FrameOPList = new ArrayList <String> ();
		NewFDB = new HashMap <Character , Integer> ();
		for(String Frame : FrameList)
		{
			//String Frame = FrameList.remove(0);
			if(Frame.charAt(2) <= Ports)
			{
				if(! FDBMap.containsKey(Frame.charAt(0)) || FDBMap.get(Frame.charAt(0)) != Character.getNumericValue(Frame.charAt(2)))
				{
					FrameOPList.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" FDB Updated ");
				}
				if(FDBMap.containsKey(Frame.charAt(0)) && FDBMap.containsKey(Frame.charAt(1)) && FDBMap.get(Frame.charAt(0)) == FDBMap.get(Frame.charAt(1)))
				{
					FrameOPList.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame Discarded ");
				}
				if(FDBMap.containsKey(Frame.charAt(1)) && FDBMap.get(Frame.charAt(0)) != FDBMap.get(Frame.charAt(1)))
				{
					FrameOPList.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame sent on port "+FDBMap.get(Frame.charAt(1)));
				}
				if(! FDBMap.containsKey(Frame.charAt(1)))
				{
					FrameOPList.add(Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2)+" Frame Broadcast on all ports");
				}
			}
			else
			{
				System.out.println("Invalid Frame: "+Frame.charAt(0)+" "+Frame.charAt(1)+" "+Frame.charAt(2));
			}
		}
		System.out.println("Output Generated: ");
		for (String Frame : FrameOPList)
		{
			System.out.println(Frame);
		}

		NewFDB = FDBMap;

		for (String Frame : FrameList)
		{
			NewFDB.put(Frame.charAt(0), Character.getNumericValue(Frame.charAt(2)));
		}
		SaveFileAs(NewFDB);
	}
	
	public static void SaveFileAs(HashMap <Character , Integer> NewFDB) throws FileNotFoundException
	{
		File SaveRequiredOutPutFile = null; 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify location to save updated FDB file");   
		 
		int userSelection = fileChooser.showSaveDialog(null);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) 
		{
		    SaveRequiredOutPutFile = fileChooser.getSelectedFile();
		    System.out.println("\nUpdated FDB File Path: " + SaveRequiredOutPutFile.getAbsolutePath());
		}
		
		PrintWriter writer = new PrintWriter(SaveRequiredOutPutFile+".txt");
		System.out.println("\nUpdated FDB: ");
		for (char node : NewFDB.keySet())
		{
			writer.println(node+" "+NewFDB.get(node));
			System.out.println(node+" "+NewFDB.get(node));
		}
		writer.close();
	}
    public static void main(String[] args) throws FileNotFoundException, IOException  {
       
        ChooseRequiredFile();
		if (Verification())
		{
			Output();
		}
        
    }
    
}
