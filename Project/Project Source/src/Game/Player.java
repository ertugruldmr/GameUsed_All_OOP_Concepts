/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.LinkedList;

/*
class Gold_Nodes{
public int row;
public int colmn;
public int Gold_value;

    public Gold_Nodes(int row, int colmn, int Gold_value) {
        this.row = row;
        this.colmn = colmn;
        this.Gold_value = Gold_value;
    }
}
 */

/**
 *
 * @author Ertuğrul Demir
 */
public class Player {
    private boolean survive_state;
    //totals
    private int gold;
    private int spent_gold;
    private int captured_gold;
    
    
    
    //current raund
    private int Current_consumed_gold;
    private int Current_captured_gold;
    
    private map map;
    private Game_Data data;
    
    private int move_cost;
    private int target_finding_cost;
    //locations
        //starting
    private int start_row;
    private int start_colm;
        //before move
    private int Location_old_row;
    private int Location_old_colmn;
        //after move
    private int Location_new_row;
    private int Location_new_colmn;
    
    //Target
        //Before finding
    private int bf_find_target_row;  //Bunlardan pek emin değlim gerek var mı ?
    private int bf_find_target_colmn;//Bunlardan pek emin değlim gerek var mı ?
        //After finding
    private int target_row;
    private int target_colmn;
        
    private int Max_Move_step;
    
    public long play_turn_speed= (long) 1;
            
            
    public Player(Game_Data data) {
        this.survive_state=true;

        this.gold = data.getSet().getDef_starting_gold();
        this.Max_Move_step = data.getSet().getMax_Move_step();
        //17.11 eklendi
        this.map=data.getMap();
        this.data=data;
        
        //İlk durumda(başlangıçta) yeki yeni ve baslangıç aynıdır.

    }
    public Player(map map){
    //denemeler için
    }


    //Getter & Setter methods
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSpent_gold() {
        return spent_gold;
    }

    public void setSpent_gold(int spent_gold) {
        this.spent_gold = spent_gold;
    }

    public int getCaptured_gold() {
        return captured_gold;
    }

    public void setCaptured_gold(int captured_gold) {
        this.captured_gold = captured_gold;
    }

    public int getMove_cost() {
        return move_cost;
    }

    public void setMove_cost(int move_cost) {
        this.move_cost = move_cost;
    }

    public int getTarget_finding_cost() {
        return target_finding_cost;
    }

    public void setTarget_finding_cost(int target_finding_cost) {
        this.target_finding_cost = target_finding_cost;
    }

    public int getStart_row() {
        return start_row;
    }

    public void setStart_row(int start_row) {
        this.start_row = start_row;
    }

    public int getStart_colm() {
        return start_colm;
    }

    public void setStart_colm(int start_colm) {
        this.start_colm = start_colm;
    }

    public int getTarget_row() {
        return target_row;
    }

    public void setTarget_row(int target_row) {
        this.target_row = target_row;
    }

    public int getTarget_colmn() {
        return target_colmn;
    }

    public void setTarget_colmn(int target_colmn) {
        this.target_colmn = target_colmn;
    }

    public int getMax_Move_step() {
        return Max_Move_step;
    }

    public void setMax_Move_step(int Max_Move_step) {
        this.Max_Move_step = Max_Move_step;
    }

    public map getMap() {
        return map;
    }

    public void setMap(map map) {
        this.map = map;
    }

    public boolean isSurvive_state() {
        return survive_state;
    }

    public void setSurvive_state(boolean survive_state) {
        this.survive_state = survive_state;
    }

    public Game_Data getData() {
        return data;
    }

    public void setData(Game_Data data) {
        this.data = data;
    }

    public int getCurrent_consumed_gold() {
        return Current_consumed_gold;
    }

    public void setCurrent_consumed_gold(int Current_consumed_gold) {
        this.Current_consumed_gold = Current_consumed_gold;
    }

    public int getCurrent_captured_gold() {
        return Current_captured_gold;
    }

    public void setCurrent_captured_gold(int Current_captured_gold) {
        this.Current_captured_gold = Current_captured_gold;
    }

    public int getLocation_old_row() {
        return Location_old_row;
    }

    public void setLocation_old_row(int Location_old_row) {
        this.Location_old_row = Location_old_row;
    }

    public int getLocation_old_colmn() {
        return Location_old_colmn;
    }

    public void setLocation_old_colmn(int Location_old_colmn) {
        this.Location_old_colmn = Location_old_colmn;
    }

    public int getLocation_new_row() {
        return Location_new_row;
    }

    public void setLocation_new_row(int Location_new_row) {
        this.Location_new_row = Location_new_row;
    }

    public int getLocation_new_colmn() {
        return Location_new_colmn;
    }

    public void setLocation_new_colmn(int Location_new_colmn) {
        this.Location_new_colmn = Location_new_colmn;
    }

    public int getBf_find_target_row() {
        return bf_find_target_row;
    }

    public void setBf_find_target_row(int bf_find_target_row) {
        this.bf_find_target_row = bf_find_target_row;
    }

    public int getBf_find_target_colmn() {
        return bf_find_target_colmn;
    }

    public void setBf_find_target_colmn(int bf_find_target_colmn) {
        this.bf_find_target_colmn = bf_find_target_colmn;
    }
    
    
    
    
}