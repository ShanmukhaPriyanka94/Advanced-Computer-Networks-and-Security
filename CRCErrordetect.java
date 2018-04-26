/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcerrordetect;

/**
 *
 * @author priyanka
 */
import java.util.Scanner;

public class CRCErrordetect {

    /**
     * @param args the command line arguments
     */
   private static Scanner Scanline;
	
	public static void main(String[] args) 
	{
		
		ChangeToValidatePhase();
					
	}

	private static void ChangeToValidatePhase() 
	{
		//System.out.println();
		//System.out.print("Enter Message To Transmit (Value of M(x)): ");
		//Scanline = new Scanner(System.in);  
		//String InitialM = Scanline.nextLine();
                String InitialM = "1101011";
                String GlobalID;
                GlobalID = "1101";
		
		GenerateDataToTransmit(InitialM , GlobalID);
		System.out.println();
                String q="y";
                do{
                    
		System.out.print("Enter the message received ");
		Scanline = new Scanner(System.in); 
		String ReceivedMessage = Scanline.nextLine();
                
		ValidateCRC(ReceivedMessage , GlobalID);
                
                    System.out.print("Do you want to send more messages(y/n) ");
		Scanline = new Scanner(System.in); 
                q =  Scanline.nextLine();
                }while("y".equals(q));
		
	}
        
        private static void GenerateDataToTransmit(String MStr, String GlobalDStr) 
	{
		int MBin[] = new int [MStr.length() + GlobalDStr.length() - 1];
		int GlobalDBinary[] = new int [GlobalDStr.length()];
		for(int i = 0 ; i < MStr.length() ; i++)
			MBin[i] = Integer.parseInt(String.valueOf(MStr.charAt(i)));
		for(int i = 0 ; i < GlobalDStr.length() ; i++)
			GlobalDBinary[i] = Integer.parseInt(String.valueOf(GlobalDStr.charAt(i)));
		
		for (int i = 0; i < MStr.length(); i++)
        {
            if (MBin[i] == 1)
                for (int j = 0; j < GlobalDBinary.length; j++)
                	MBin[i + j] ^= GlobalDBinary[j];
        }
		
		System.out.print("Message that is to be transmitted is P(x): ");
        for (int i = 0; i < MStr.length(); i++)
        	MBin[i] = Integer.parseInt(MStr.charAt(i) + "");
        for (int i = 0; i < MBin.length; i++) 
        	System.out.print(MBin[i]);
        System.out.println();
		
        
	}

	


	

	private static void ValidateCRC(String RecvMsgStr, String GlobalDStr) 
	{
		
		Boolean IsValid = true;
		int RecvMsgBinary[] = new int [RecvMsgStr.length() + GlobalDStr.length() - 1];
		int GlobalDBinary[] = new int [GlobalDStr.length()];
		for(int i = 0 ; i < RecvMsgStr.length() ; i++)
			RecvMsgBinary[i] = Integer.parseInt(String.valueOf(RecvMsgStr.charAt(i)));
		for(int i = 0 ; i < GlobalDStr.length() ; i++)
			GlobalDBinary[i] = Integer.parseInt(String.valueOf(GlobalDStr.charAt(i)));
		
		for (int i = 0; i < RecvMsgStr.length(); i++)
        {
            if (RecvMsgBinary[i] == 1)
                for (int j = 0; j < GlobalDBinary.length; j++)
                	RecvMsgBinary[i + j] ^= GlobalDBinary[j];
        }
		
		for (int i = 0; i < RecvMsgBinary.length; i++)
        {
			if (RecvMsgBinary[i] == 1)
			{
				IsValid = false;
				break;
			}
        }
		System.out.println();
		if (IsValid)
		{
			System.out.println("No error detected");
		}
		else
		{
			System.out.println("There is an error");
		}
	}

	

}

