/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesslist;

/**
 *
 * @author priyanka
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class AccessList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.println("Enter 1 for Standard and 2 for Extended");
        Scanner option = new Scanner(System.in);
        
        int c=option.nextInt();
        switch(c){
            case 1:{
     
        ArrayList<String> acl = new ArrayList<>();
        ArrayList<String> ip = new ArrayList<>();
        String[] acltemp = null;
        String[] iptemp = null;
        String[] decide = null;
        String[] source = null;
        String flag="d";
        int count=0;
        try {
            Scanner acclist = new Scanner(new File("/home/priyanka/Desktop/acclist.txt"));
             Scanner input = new Scanner(new File("/home/priyanka/Desktop/input.txt"));
           while (acclist.hasNextLine())
            {
                String temp = acclist.nextLine();
                if(temp.contains("access-list"))
                {
                    acl.add(temp);
                }
            }
           while(input.hasNextLine()){
               ip.add(input.nextLine());
           
           }
           acclist.close();
           input.close();
          for(int j=0;j<ip.size();j++){ 
                iptemp=ip.get(j).trim().split("\\.");   
                for(int i=0; i<acl.size();i++){
                     acltemp =  acl.get(i).trim().split("\\s");
                    
                    if(!acltemp[3].contains("any")){
                        decide = acltemp[4].trim().split("\\.");
                        source = acltemp[3].trim().split("\\.");
                        for(int k=0;k<decide.length;k++){
                            if(decide[k].contains("0")){
                                if(!iptemp[k].equals(source[k])){
                                flag="a";
                            }
                            
                        }
                        if(flag=="a")
                            break;
                   
                    }
                }
                    
                    if(flag=="d"&&acltemp[2].contains("deny")){
                        System.out.println(ip.get(j)+ " is denied");
                        count++;
                        break;
                    
                    }
                    else if(flag=="d"&&acltemp[2].contains("permit")){
                        System.out.println(ip.get(j)+ " is permitted");
                        count++;
                        break;
                    
                    }
                    
                   
                        
                   flag="d"; 
                    
                }
                
                if(count==0){
                    System.out.println(ip.get(j)+ " is denied");
                
                }
                count=0;
        
           }
       
        }
        catch (Exception e) {
            e.printStackTrace();
        
        }
        break;
            }
            case 2:{
                ArrayList<String> acl = new ArrayList<>();
                ArrayList<String> ip = new ArrayList<>();
                String[] acltemp = null;
                String[] iptemp = null;
                String[] ipsource = null;
                String[] ipdest = null;
                String[] source = null;
                String[] sm = null;
                String[] destination = null;
                String[] dm = null; 
                String flag="d";
                String protocol = null;
        int count=0;
        try {
            Scanner acclist = new Scanner(new File("/home/priyanka/Desktop/acclist2.txt"));
             Scanner input = new Scanner(new File("/home/priyanka/Desktop/input2.txt"));
           while (acclist.hasNextLine())
            {
                String temp = acclist.nextLine();
                if(temp.contains("access-list"))
                {
                    acl.add(temp);
                }
            }
           while(input.hasNextLine()){
               ip.add(input.nextLine());
           
           }
           acclist.close();
           input.close();
           
           for(int i=0;i<ip.size();i++){
                iptemp=ip.get(i).split("\\s"); 
               /* for(int h=0;h<iptemp.length;h++){
                    System.out.println(iptemp[h]);
                
                }*/
                ipsource=iptemp[0].split("\\.");
                ipdest=iptemp[1].split("\\.");
               
               
                if(iptemp[2].contains("ftp")){
                    protocol="20-21";
                }
                else if(iptemp[2].contains("http")){
                    protocol="80";
                
                }
                else if(iptemp[2].contains("ssh")){
                    protocol="22";
                
                }
                for(int j=0;j<acl.size();j++){
                    acltemp =  acl.get(j).split("\\s");
                    if(acltemp[9].contains(protocol)){
                        
                         sm = acltemp[5].split("\\.");
                         for(int k=0;k<sm.length;k++){
                             if(!acltemp[4].contains("any")){
                             source = acltemp[4].split("\\.");
                             
                            if(sm[k].contains("0")){
                                if(!ipsource[k].equals(source[k])){
                                flag="a";
                            }
                            
                        }
                         }
                        if(flag=="a"){
                            break;
                        }
                        else{
                            if(!acltemp[6].contains("any")){
                             destination = acltemp[6].split("\\.");
                              dm = acltemp[7].split("\\.");
                             if(dm[k].contains("0")){
                                if(!ipdest[k].equals(destination[k])){
                                flag="a";
                            }
                            
                        }
                        }
                            
                        
                        }
                        if(flag=="a"){
                            break;
                        }
                   
                    }
                     
                           if(flag=="d"&&acltemp[2].contains("deny")){
                        System.out.println("Source: "+iptemp[0]+" Destination:"+iptemp[1]+ " is denied");
                        count++;
                        
                    
                    }
                    else if(flag=="d"&&acltemp[2].contains("permit")){
                        System.out.println("Source: "+iptemp[0]+" Destination:"+iptemp[1]+" is permitted");
                        count++;
                       
                    
                    }
                    
                   
                        
                   flag="d";  
                        
                    
                    }
                    if(count!=0){
                        break;
                    
                    }
                    
                
                
                }
                if(count==0){
                    System.out.println("Source: "+iptemp[0]+" Destination:"+iptemp[1]+ " is denied");
                
                }
                count=0;
                
           
           }
          
       
        }
        catch (Exception e) {
            e.printStackTrace();
        
        } 
            
            
            break;
            }
   
}
    }
    
}
