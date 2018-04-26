/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetrouting;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;

public class PacketRouting {
 public static HashMap < String, String > DestinationHop;
    public static HashMap < String, String > DestinationMask;
    public static HashMap < String, String > DestinationInterface;
    public static Boolean isError;
    public static File RT;
    public static File PF;
    public static ArrayList < String > RTArray;
    public static ArrayList < String > PFArray;
    
    
    public static boolean Verification() throws FileNotFoundException, IOException
    {
        Boolean IsRTValid = true;
        Boolean IsPFileValid = true;
        int Count = 1;
        int CntPacket = 1;
        RTArray = new ArrayList < String > ();
        PFArray = new ArrayList < String > ();

        System.out.println("Routing Table is getting Validated");
        
        try (BufferedReader br = new BufferedReader(new FileReader(RT)))
        {
            String RTRow;
            while ((RTRow = br.readLine()) != null)
            {
                RTRow.trim();
                RTRow = RTRow.replaceAll("\\s+", " ");
                RTArray.add(RTRow);
                String[] RowElements = RTRow.split(" ");
                if (RowElements.length != 5)
                {
                    IsRTValid = IsRTValid && false;
                    System.out.println("Invalid number of entries at row " + Count);
                }
                if (RowElements[0].split("\\.").length != 4 || RowElements[1].split("\\.").length != 4 || (!RowElements[2].equals("null") && RowElements[2].split("\\.").length != 4))
                {
                    IsRTValid = IsRTValid && false;
                    System.out.println("Wrong IP at Row" + Count);
                }
                Count++;
            }
        }
        catch (Exception e)
        {
        	isError = true;
        	System.out.println("Error\n");
        }

        if (IsRTValid && !isError)
            System.out.println("Correct Routing Table File\n");
        else if (!IsRTValid)
            System.out.println("\nIncorrect Routing File\n");

        System.out.println("Packet File Validation");
       

        try (BufferedReader br = new BufferedReader(new FileReader(PF)))
        {
            String PacR;
            while ((PacR = br.readLine()) != null)
            {
                PacR.trim();
                PacR = PacR.replaceAll("\\s+", "");
                PFArray.add(PacR);
                String[] PacketElements = PacR.split("\\.");
                if (PacketElements.length != 4 || Integer.parseInt(PacketElements[0]) > 255 || Integer.parseInt(PacketElements[1]) > 255 || Integer.parseInt(PacketElements[2]) > 255 || Integer.parseInt(PacketElements[3]) > 255)
                {
                    IsPFileValid = IsPFileValid && false;
                    System.out.println("Invalid IP Address at row " + CntPacket);
                }
                CntPacket++;
            }
        }
        catch (Exception e)
        {
        	isError = true;
        	System.out.println("Error in Packet file parsing\n");
        }

        if (IsPFileValid && !isError)
            System.out.println("Packet File is Valid\n");
        else if(!IsPFileValid)
            System.out.println("\nPacket File is Invalid\n");

        return IsPFileValid && IsRTValid && !isError;
    }
    


public static void ChooseRequiredFiles()
    {
    	isError = false;
    	JFileChooser FDBChooser = new JFileChooser();
        FDBChooser.setDialogTitle("Routing Table");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        FDBChooser.setFileFilter(filter);
        FDBChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = FDBChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            RT = FDBChooser.getSelectedFile();
            System.out.println("Selected FDB File: " + RT.getAbsolutePath());
        }
        else
        {
        	isError = true;
        	System.out.println("\nFile not selected");
        }

        JFileChooser FrameFileChooser = new JFileChooser();
        FrameFileChooser.setDialogTitle("Input destination packet");
        FileNameExtensionFilter frmfilter = new FileNameExtensionFilter("Text Files", "txt");
        FrameFileChooser.setFileFilter(frmfilter);
        FrameFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int frmresult = FrameFileChooser.showOpenDialog(null);
        if (frmresult == JFileChooser.APPROVE_OPTION)
        {
            PF = FrameFileChooser.getSelectedFile();
            System.out.println("Frame Chosen: " + PF.getAbsolutePath() + "\n");
        }
        else
        {
        	isError = true;
        	System.out.println("No Packet File\n");
        }
    }
    





    
    public static void Output() throws FileNotFoundException, IOException
    {
        DestinationHop = new HashMap < String, String > ();
        DestinationMask = new HashMap < String, String > ();
        DestinationInterface = new HashMap < String, String > ();
        System.out.println("Output");
       
        for (String RTRow: RTArray)
        {
            String[] RowElements = RTRow.split(" ");
            DestinationHop.put(RowElements[1], RowElements[2]);
            DestinationInterface.put(RowElements[1], RowElements[4]);
        }
        for (String DestAddr: PFArray)
            DestinationMask.put(DestAddr, MaskAddress(DestAddr));

        for (String DestAddr: PFArray)
        {
            if (DestinationMask.get(DestAddr) != null && DestinationHop.get(DestinationMask.get(DestAddr)) != null && DestinationHop.get(DestinationMask.get(DestAddr)).equals("null"))
            {
                System.out.println("Packet with destination address " + DestAddr + " is directly connected on interface " + DestinationInterface.get(MaskAddress(DestAddr)));
            }
            else
            {
                System.out.println("Packet with destination address " + DestAddr + " will be forwarded to " + DestinationHop.get(DestinationMask.get(DestAddr)) + " out on interface " + DestinationInterface.get(MaskAddress(DestAddr)));
            }
        }

    }

    
    public static String MaskAddress(String DestAddr)
    {
        String NetAddr = "";
        int position = 3;
        String[] IPElements = DestAddr.split("\\.");
        while (!DestinationHop.containsKey(NetAddr) && position > -1)
        {
            NetAddr = "";
            IPElements[position] = "0";
            for (String Element: IPElements)
            {
                NetAddr += Element + ".";
            }
            NetAddr = NetAddr.substring(0, NetAddr.length() - 1);
            position--;
        }
        return NetAddr;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        ChooseRequiredFiles();
        if (Verification())
        {
            Output();
        }
    }
    
}
