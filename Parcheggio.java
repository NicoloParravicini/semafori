/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es_parcheggioauto;

/**
 *
 * @author Nicolo
 */
public class Parcheggio {
    
    private int [] buffer;
    private int conta;
    private int i;
    private int u;
    private int Dim = 10;

    public Parcheggio() {
        
        this.conta = 0;
        this.i = 0;
        this.u = 0;
        this.Dim = Dim;
    }
    
    synchronized void ingresso (int dato) throws InterruptedException{
        
        while(conta == Dim)
            this.wait();
        
        buffer[i] = dato;
        i = (i + 1) % Dim;
        
        conta++;
        this.notify();
    }
    
    synchronized void uscita () throws InterruptedException{
        
        while(conta == 0)
            this.wait();
        
        int dato;
        dato = buffer [u];
        u = (u - 1) % Dim;
      
        
        conta--;
        this.notify();
    }
    
}
