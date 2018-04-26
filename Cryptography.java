/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author priyanka
 */
public class Cryptography {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
       
       
       String text =null;
        StringBuilder finaltext=null;
        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int key=0;
         int index=0,ki=0;
         int count=0;
       while(count==0){
            System.out.println("Type 1 for Encryption and 2 for Decryption");
        Scanner op = new Scanner(System.in);
        int option = op.nextInt();
             if(option<1||option>2){
            System.out.println("Wrong option is chosen ");
            
             
        }
        else{
         count=1; 
         //-------------Ceaser--------------------
        
        if(option==1)
            System.out.println("Enter PlainText");
        else if(option==2)
            System.out.println("Enter Cipher Text");
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
      
        //switch(switchin){
         System.out.println("----------------------Ceaser--------------------------");
        System.out.println("Enter Key for ceaser");
        Scanner ke = new Scanner(System.in);
        key = ke.nextInt();
         int[] cap = new int[text.length()]; 
        int cps=0;
        for(int cp=0;cp<text.length();cp++){
        char character = text.charAt(cp);
            if(Character.isUpperCase(character)){
                cap[cps]=cp;
                cps++;
            }
        }
        if(cps==0&&!(Character.isUpperCase(text.charAt(0)))){
            
        }
        
       // String spacetext = text.replaceAll("\\s+","");
        String captext=text.toLowerCase();

       finaltext = new StringBuilder(captext);
      
        for(int i=0;i<finaltext.length();i++){
            char temp = finaltext.charAt(i);
           
            for(int j=0;j<alphabets.length;j++){
                if(temp==alphabets[j]){
                    index=j;
                    if(option==1)
                        ki=(index+key)%26;
                    if(option==2)
                        ki=(index-key)%26;
                    if(ki<0)
                        ki=ki+26;
                    finaltext.setCharAt(i, alphabets[ki]);
                }
            }
            
        }
        for(int df=0;df<cap.length;df++){
            if(cps==0&&!(Character.isUpperCase(text.charAt(0)))){
            
        }
            else{
           finaltext.setCharAt(cap[df], Character.toUpperCase(finaltext.charAt(cap[df])));
            }
       }
        if(option==1)
            System.out.println("Encrypted/Cipher Text is: "+" "+ finaltext);
        if(option==2)
            System.out.println("Decrypted/Plain Text is: "+" "+ finaltext);        
    
        
        
       System.out.println("-----------vignere------------");
       //Vignere
        
        HashMap < String, String[] > vhmap;
        vhmap = new HashMap<String, String[]>();
        vhmap.put("a", new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"});
        
        vhmap.put("b", new String[] {"b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a"});
        
        vhmap.put("c", new String[] {"c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b"});
       
        vhmap.put("d", new String[] {"d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c"});
        
        vhmap.put("e", new String[] {"e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d"});
        
        vhmap.put("f", new String[] {"f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e"});
       
        vhmap.put("g", new String[] {"g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f"});
        
        vhmap.put("h", new String[] {"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g"});
       
        vhmap.put("i", new String[] {"i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h"});
        
        vhmap.put("j", new String[] {"j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i"});
       
        vhmap.put("k", new String[] {"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j"});
        
        vhmap.put("l", new String[] {"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k"});
       
        vhmap.put("m", new String[] {"m","n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l"});
        
        vhmap.put("n", new String[] {"n","o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m"});
        
        vhmap.put("o", new String[] {"o","p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n"});
        
        vhmap.put("p", new String[] {"p","q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o"});
        
        vhmap.put("q", new String[] {"q","r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"});
        
        vhmap.put("r", new String[] {"r","s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"});
       
        vhmap.put("s", new String[] {"s","t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r"});
        
        vhmap.put("t", new String[] {"t","u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s"});
        
        vhmap.put("u", new String[] {"u","v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"});
        
        vhmap.put("v", new String[] {"v","w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u"});
        
        vhmap.put("w", new String[] {"w","x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v"});
       
        vhmap.put("x", new String[] {"x","y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w"});
        
        vhmap.put("y", new String[] {"y","z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x"});
        
        vhmap.put("z", new String[] {"z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"});
        
        StringBuilder vignerefulltext=null;
        
        int[] spa = new int[text.length()]; 
        int ct=0;
        for(int sp=0;sp<text.length();sp++){
            if(text.charAt(sp)==' '){
                spa[ct]=sp;
                ct++;
            }
        }
        
         
        
        
        String spacetext = text.replaceAll("\\s+","");
         int[] capv = new int[spacetext.length()]; 
         int cpsv=0;
        for(int i=0;i<spacetext.length();i++){
            
            char character = spacetext.charAt(i);
            if(Character.isUpperCase(character)){
                capv[cpsv]=i;
                cpsv++;
            }
        }
        String ctext=spacetext.toLowerCase();
        
            
            vignerefulltext = new StringBuilder(ctext);
        int vignerefulltextlength = vignerefulltext.length();
        String vkey;
        System.out.println("Enter Key for vigenere");
        Scanner vin = new Scanner(System.in);
        vkey = vin.nextLine();
        vkey=vkey.toLowerCase();
        StringBuilder vignerekey=null;
        vignerekey = new StringBuilder(vkey);
        int vklength = vignerekey.length();
        int ext=0;
        while(vignerekey.length()<vignerefulltext.length()){
             vignerekey.append(vignerekey.charAt(ext));
             ext++;
            
        }
         //System.out.println("Key: "+" "+ vignerekey);
         
         if(option==1){
        
       for(int lo=0;lo<vignerefulltextlength;lo++){
        String str = Character.toString(vignerefulltext.charAt(lo));
          
           
               String st=null;
               for(int kk=0;kk<(vhmap.get(str)).length;kk++){
                   st=vhmap.get("a")[kk];
                   if(st.equals(Character.toString(vignerekey.charAt(lo)))){
                       // System.out.println(st);
                       String stt=vhmap.get(str)[kk];
                       vignerefulltext.setCharAt(lo,stt.charAt(0) );
                    
               }
           }
       }
       for(int i=0;i<capv.length;i++){
           if(cpsv==0&&!(Character.isUpperCase(text.charAt(0)))){
            
        }
           else{
           vignerefulltext.setCharAt(capv[i], Character.toUpperCase(vignerefulltext.charAt(capv[i])));
           }
       }
       for(int df=0;df<spa.length;df++){
           vignerefulltext.insert(spa[df], ' ');
       }
       
        
        
       System.out.println("Encrypted Text is (Vignere): "+" "+ vignerefulltext);
         }
         
         if(option==2){
             for(int lo=0;lo<vignerekey.length();lo++){
                String str = Character.toString(vignerekey.charAt(lo));
          
           
               String st=null;
                st = Character.toString(vignerefulltext.charAt(lo));
               for(int kk=0;kk<26;kk++){
                  
                   if(st.equals(vhmap.get(str)[kk])){
                       // System.out.println(st);
                       String stt=vhmap.get("a")[kk];
                       vignerefulltext.setCharAt(lo,stt.charAt(0) );
                    
               }
           }
       }
              for(int i=0;i<capv.length;i++){
           if(cpsv==0&&!(Character.isUpperCase(text.charAt(0)))){
            
        }
           else{
           vignerefulltext.setCharAt(capv[i], Character.toUpperCase(vignerefulltext.charAt(capv[i])));
           }
       }
       for(int df=0;df<spa.length;df++){
           vignerefulltext.insert(spa[df], ' ');
       }
      
      
       System.out.println("Decrypted Text is (Vignere): "+" "+ vignerefulltext);
         
         
         }
         
         
         
         //Matrix Transposition
         System.out.println("----------------MatrixTransposition---------------");
         String[][] transm = new String[50][50];
         String transtext = text.replaceAll("\\s+","%");
         StringBuilder transposition=null;
         transposition = new StringBuilder(transtext);
         System.out.println("Enter total no.of keys");
         Scanner k = new Scanner(System.in);
         Scanner ik = new Scanner(System.in);
         int max = k.nextInt();
         int[] keys = new int[max];
         System.out.println("Enter keys for MatrixTransposition one by one");
         for(int i=0;i<max;i++){
             keys[i] = ik.nextInt();
         }
             int l=0;
             int maxrows;
             if(transposition.length()%max==0){
                 maxrows=transposition.length()/max;
             }
             else{
                 maxrows=(transposition.length()/max)+1;
             }
             
             if(option==1){
             for(int m=0;m<maxrows;m++){
                 for(int n=0;n<max;n++){
                     if(l<transposition.length()){
                     transm[m][n] = Character.toString(transposition.charAt(l));
                     l++;
                     }
                     else{
                         transm[m][n] = "%";
                     
                     }
                         }
                 
             }
             for(int m=0;m<maxrows;m++){
                 for(int n=0;n<max;n++){
                     
                     System.out.print(transm[m][n] + " ");
                 }
                 System.out.println();
             }
             System.out.println("Encrypted Text is: ");
             for(int out=0;out<keys.length;out++){
                 for(int tr=0;tr<maxrows;tr++){
                     System.out.print(transm[tr][keys[out]-1] + " ");
                 
                 }
             
             
             }
                 }
                 
                if(option==2){
                     int lk=0;
                     for(int out=0;out<keys.length;out++){
                           for(int tr=0;tr<maxrows;tr++){
                                if(lk<transposition.length()){
                               transm[tr][keys[out]-1]=  Character.toString(transposition.charAt(lk));
                                  lk++;
                                }
                                else{
                                    transm[tr][keys[out]-1] = "%";
                                }
                     
                            }
                
                       }
                        System.out.println("Dcrypted Text is: ");
                     for(int m=0;m<maxrows;m++){
                         for(int n=0;n<max;n++){
                             if(transm[m][n].equals("%")){
                                 transm[m][n]=" ";
                             }
                     
                              System.out.print(transm[m][n]);
                            }
                 
                     }
                }
                 
             
                }
       
       }
        System.out.println(); 
       //-----------------LFSR-------------------------------
       System.out.println("----------------LFSR---------------------");
       System.out.println("Enter length of the shift register");
       int ls;
       Scanner in = new Scanner(System.in);
       ls = in.nextInt();
       Scanner is = new Scanner(System.in);
         int[] seed = new int[ls];
         System.out.println("Enter bits in seed one by one");
         for(int i=ls-1;i>=0;i--){
             seed[i] = is.nextInt();
         }
         for(int i=0;i<ls;i++){
             System.out.print(seed[i]);
         }
         System.out.println();
          System.out.println("Enter total no.of tap positions");
          int mt;
          Scanner m = new Scanner(System.in);
          mt = m.nextInt();
          Scanner it = new Scanner(System.in);
         int[] tap = new int[mt];
         System.out.println("Enter tap positions one by one");
         for(int i=0;i<mt;i++){
             tap[i] = it.nextInt();
         }
         System.out.println("Enter total n0.of cycles");
          int c;
          Scanner cy = new Scanner(System.in);
          c = cy.nextInt();
          int[] out = new int[c];
          int t,t1=0,r=0;
          
          for(int i=c-1;i>=0;i--){
              out[i]=seed[ls-1];
               t=seed[tap[mt-1]];
                  for(int k=mt-1;k>0;k--){
                      t1=seed[tap[k-1]];
                      
                      r=t1^t;
                       t=r;
                      // System.out.println("xor value: "+ r);
                     
                  }
              for(int j=ls-1;j>0;j--){
                 
                  seed[j]=seed[j-1];
           
              }
              seed[0]=r;
              for(int l=0;l<ls;l++){
                  System.out.print(seed[l]);
              }
              System.out.println();
              
               //System.out.print("out after iteration: "+out[i]);
          
          }
          System.out.println("Pseudo Outoput onn LFSR is : ");
          for(int i=c-1;i>=0;i--){
              System.out.print(out[i]);
          
          }
          System.out.println();
         
       

        
        
    }
         
    
}
