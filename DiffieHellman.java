/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diffiehellman;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author priyanka
 */
public class DiffieHellman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Value of P: ");
        Scanner pin = new Scanner(System.in);
        int p = pin.nextInt();
        System.out.println("Value of g: ");
        Scanner gin = new Scanner(System.in);
        int g = gin.nextInt();
        // create instance of Random class
        Random rand = new Random();
 
        // Generate random integers 
        int SA = rand.nextInt(20);
       
        int SB = rand.nextInt(20);
        
        System.out.println("Alice's SA :  " + SA);
        System.out.println("Bob's SB :  " + SB);
        int TA = (int)((Math.pow(g, SA))% p);
        int TB = (int)((Math.pow(g, SB))% p);
        System.out.println("Alice's TA :  " + TA);
        System.out.println("Bob's TB :  " + TB);
        
        int as = (int)((Math.pow(TB, SA))% p);
        int bs = (int)((Math.pow(TA, SB))% p);
        System.out.println("Alice's secret key :  " + as);
        System.out.println("Bob's secret key :  " + bs);
        
      
        
    }
    
}
