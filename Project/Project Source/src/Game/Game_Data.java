/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import file_templates.Raund;
import java.util.Arrays;
import java.util.Random;





    
    

/**
 *
 * @author Ertuğrul Demir
 */
public class Game_Data {
   map  map;
   int tur=0;
   private int Current_Rank_award=4;
   private Raund Raunds; //linked list <class Hareketler>; içeren bir sınıf. Tüm kayıtlar var
   Setttings set;//Default settings 
   /*   Buradakiler yukarıdaki sınıfın içeriği
   private  int max_row;
   private  int max_colmn;
   
   
   private int def_starting_gold;
   
   private int Max_Move_step;
   
   private int A_def_move_cost;
   private int B_def_move_cost;
   private int C_def_move_cost;
   private int D_def_move_cost;
   
   private int A_def_target_finding_cost;
   private int B_def_target_finding_cost;
   private int C_def_target_finding_cost;
   private int D_def_target_finding_cost;
   
   private float Gold_ratio;
   private float Invisible_ratio;
   
   private int Max_Gold_value;
   private int Divider_gold;
*/ 
    public Game_Data(map map, Raund Raunds, Setttings set) {
        this.map = map;
        this.Raunds = Raunds;
        this.set=set;   
    }
    //default values
    
   
   
   
   
   
   
   
   
   
   
   //setter & getter methods

    public map getMap() {
        return map;
    }

    public void setMap(map map) {
        this.map = map;
    }

    public int getTur() {
        return tur;
    }

    public void setTur(int tur) {
        this.tur = tur;
    }

    public Raund getRaunds() {
        return Raunds;
    }

    public void setRaunds(Raund Raunds) {
        this.Raunds = Raunds;
    }

    public int getCurrent_Rank_award() {
        return Current_Rank_award;
    }

    public void setCurrent_Rank_award(int Current_Rank_award) {
        this.Current_Rank_award = Current_Rank_award;
    }

    public Setttings getSet() {
        return set;
    }

    public void setSet(Setttings set) {
        this.set = set;
    }
   
   
   
   
}
