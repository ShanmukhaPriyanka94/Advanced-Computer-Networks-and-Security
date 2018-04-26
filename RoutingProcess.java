/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routingprocess;

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
public class RoutingProcess {
    
    public static File routingtable = new File("/home/priyanka/Desktop/B00777449_Assignment 3/2nd question/routingtable.txt");
    public static File Destination = new File("/home/priyanka/Desktop/B00777449_Assignment 3/2nd question/destinationaddress.txt");
    public static ArrayList < String > rtlist;
    public static ArrayList < String > addrlist;
    
    public static void Output() throws FileNotFoundException, IOException{
        
        rtlist = new ArrayList < String > ();
        addrlist = new ArrayList < String > ();
        String rtrow;
        BufferedReader br = new BufferedReader(new FileReader(routingtable));
        
        while ((rtrow = br.readLine()) != null)
            {
                rtrow.trim();
                rtrow = rtrow.replaceAll("\\s+", " ");
                rtlist.add(rtrow);
            }
        String addrrow;
        BufferedReader br1 = new BufferedReader(new FileReader(Destination));
        
        while ((addrrow = br1.readLine()) != null)
            {
                addrrow.trim();
                addrrow = addrrow.replaceAll("\\s+", " ");
                addrlist.add(addrrow);
            }
         System.out.println("Routing Table: ");
        for(int i=0;i<rtlist.size();i++){
              
              System.out.println(rtlist.get(i));
        }
        System.out.println("Address: ");
          for(int i=0;i<addrlist.size();i++){
              
              System.out.println(addrlist.get(i));
        }
         System.out.println("Single Address:"); 
          System.out.println(rtlist.get(6).substring(16, 27));
          ArrayList<String> flags;
          flags = new ArrayList < String > ();
          ArrayList<String> Interface;
          Interface = new ArrayList < String > ();
          ArrayList<String> nexthop;
          nexthop = new ArrayList < String > ();
          ArrayList<String> netmask;
          netmask = new ArrayList < String > ();
          
          
          for(int i=0;i<rtlist.size();i++){
             String[]split = rtlist.get(i).split(" ");
             netmask.add(split[1]);
             nexthop.add(split[2]);
             flags.add(split[3]);
             Interface.add(split[4]);
          }
         
          for (int i=0;i<addrlist.size();i++){
              int hs = 0;
              String[] addr = addrlist.get(i).split("\\.");
              for(int j=0;j<rtlist.size();j++){
                  
                  
                 
                  if(rtlist.get(j).contains(addrlist.get(i))&& rtlist.get(j).contains("HostSpecific") ){
                       System.out.println("Packet with Destination Address "+ addrlist.get(i)+" will be forwarded to "+nexthop.get(j)+" out on interface "+ Interface.get(j) +":- "+flags.get(j) );   
                       hs++;
                  }
              }
              int ns=0;
            if(hs==0){
              for(int j=0;j<rtlist.size();j++){
                 
                         String[] nm = netmask.get(j).split("\\.");
                           
                  String result = "";
                  int count = 0; 
                  for(int k=0;k<nm.length;k++){
                      if(!netmask.get(j).equals("0.0.0.0")){
                      if(nm[k].equals("0")){
                          addr[k] = "0";
                      }
                      if(count ==3)
                          result = result+addr[k];
                          
                      
                      else{
                          result = result+addr[k]+".";
                           count++;
                      }
                          
                      
                      
                      
                  }  
                  
                  }
                      
                      if(rtlist.get(j).contains(result)&& (rtlist.get(j).contains("NetworkSpecific")||rtlist.get(j).contains("Direct")) ){
                          System.out.println("Packet with Destination Address "+ addrlist.get(i)+" will be forwarded to "+nexthop.get(j)+" out on interface "+ Interface.get(j)+":- "+flags.get(j) );    
                          ns++;
                      }
                      
              } 
              
              for(int j=0;j<rtlist.size();j++){
              if(ns==0){
                    String[] nm = netmask.get(j).split("\\.");
                          String result1 = "";
                          int count1 = 0; 
                          for(int k=0;k<nm.length;k++){
                                if(netmask.get(j).equals("0.0.0.0")){
                                    addr[k] = "0";
                      
                                 if(count1 ==3)
                                    result1 = result1+addr[k];
                      else{
                          result1 = result1+addr[k]+".";
                           count1++;
                      }
                          
                      
                      }
                      
                  } 
                          if(rtlist.get(j).contains(result1)&& (rtlist.get(j).contains("Default")))
                            System.out.println("Packet with Destination Address "+ addrlist.get(i)+" will be forwarded to "+nexthop.get(j)+" out on interface "+ Interface.get(j)+":- "+flags.get(j) );    
              
                      }
              }
                  
              }
                  
                  
                  
              
             
              
         
         
         
          }
    
    
    
    
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Output();
    }
    
}
