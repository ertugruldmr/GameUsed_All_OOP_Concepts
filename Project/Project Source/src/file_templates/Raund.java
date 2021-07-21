/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_templates;

import java.util.LinkedList;

/**
 *
 * @author Ertuğrul Demir
 */
public class Raund {
    LinkedList<A_raund> Kayit_A;
    LinkedList<A_raund> Kayit_B;// bir farkı yok diye A_raund ile yaptım
    LinkedList<C_raund> Kayit_C;
    LinkedList<A_raund> Kayit_D;
    private int Number_of_Raund=0;
    
    public Raund() {
        this.Kayit_A = new LinkedList<A_raund>();
        this.Kayit_B = new LinkedList<A_raund>();
        this.Kayit_C = new LinkedList<C_raund>();
        this.Kayit_D = new LinkedList<A_raund>();
        this.Number_of_Raund++;
    }
    
    //add
    public void add_A(A_raund raund){
    this.Kayit_A.add(raund);
    }
    public void add_B(A_raund raund){
    this.Kayit_B.add(raund);
    }
    public void add_C(C_raund raund){
    this.Kayit_C.add(raund);
    }
    public void add_D(A_raund raund){
    this.Kayit_D.add(raund);
    }
    
    public void raund_passed(){
     this.setNumber_of_Raund(this.getNumber_of_Raund()+1);
    }
    
    
    
//Getter and Setter
    public LinkedList<A_raund> getKayit_A() {
        return Kayit_A;
    }

    public void setKayit_A(LinkedList<A_raund> Kayit_A) {
        this.Kayit_A = Kayit_A;
    }

    public LinkedList<A_raund> getKayit_B() {
        return Kayit_B;
    }

    public void setKayit_B(LinkedList<A_raund> Kayit_B) {
        this.Kayit_B = Kayit_B;
    }

    public LinkedList<C_raund> getKayit_C() {
        return Kayit_C;
    }

    public void setKayit_C(LinkedList<C_raund> Kayit_C) {
        this.Kayit_C = Kayit_C;
    }

    public LinkedList<A_raund> getKayit_D() {
        return Kayit_D;
    }

    public void setKayit_D(LinkedList<A_raund> Kayit_D) {
        this.Kayit_D = Kayit_D;
    }

    public int getNumber_of_Raund() {
        return Number_of_Raund;
    }

    public void setNumber_of_Raund(int Number_of_Raund) {
        this.Number_of_Raund = Number_of_Raund;
    }
    
}
