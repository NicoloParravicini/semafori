/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semafori;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 *
 * @author Utente
 */
public class ProdConsSem {
    
    protected static Semaphore semVuoto = new Semaphore(1);
    protected static Semaphore semPieno = new Semaphore(0);
    protected static int buffer = 0;
    

    public static class Produttore extends Thread{
    
        public void run(){
    
            while(true){

                try{

                    semVuoto.acquire();
                    buffer++;
                    System.out.println("Produce: " + buffer);
                    semPieno.release();
                }catch (InterruptedException e){
                }
                try{

                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                }
            }
        }
    }
    
    public static class Consumatore extends Thread {
    
        int j = 0;
        
         public void run(){
    
            while(true){

                try{

                    semPieno.acquire();
                     j = buffer;
                    System.out.println("Consuma: " + j);
                    semVuoto.release();
                }catch (InterruptedException e){
                }
            }
        }
    }
    
    public static void main(String[] args){
    
        new Produttore().start();
        new Consumatore().start();
    }
}