/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author Ertuğrul Demir
 */
public class Setttings {
   private  int max_row;        //Tahtadaki satır sayısı
   private  int max_colmn;      //Tahtadaki stun sayısı
   
   
   private int def_starting_gold;//Başlangıç altını
   
   private int Max_Move_step;   //Bir raunddak en fazla kaç kare gidilebilir.
   
   private int A_def_move_cost; //A nın rand başına hareket tutarı.
   private int B_def_move_cost; //B nın rand başına hareket tutarı.
   private int C_def_move_cost; //C nın rand başına hareket tutarı.
   private int D_def_move_cost; //D nın rand başına hareket tutarı.
   
   private int A_def_target_finding_cost;   //A nın rand başına Hedef Belirleme tutarı.
   private int B_def_target_finding_cost;   //B nın rand başına Hedef Belirleme tutarı.
   private int C_def_target_finding_cost;   //C nın rand başına Hedef Belirleme tutarı.
   private int D_def_target_finding_cost;   //D nın rand başına Hedef Belirleme tutarı.
   
   private float Gold_ratio;        //Altın sayısı = haritadaki kare sayısını / gold ratio (0-100) arasında float bir sayı.
   private float Invisible_ratio;   //Görünmez altın= Tüm altın sayısı / Invisible gold ratio (0-100) arasında float bir sayı.
   
   private int Max_Gold_value;      //Haritadaki altınların sahip olabileceği max değer.
   private int Divider_gold;        //altının hangi katlarda değere sahip olacağı.

   //Oyuncuların Başlangıç konumları
   private int A_starting_row=0;
   private int A_starting_colmn=0;

   private int B_starting_row;
   private int B_starting_colmn;

   private int C_starting_row;
   private int C_starting_colmn;

   private int D_starting_row;
   private int D_starting_colmn;   
    public Setttings(int max_row, int max_colmn, int def_starting_gold, int Max_Move_step, int A_def_move_cost, int B_def_move_cost, int C_def_move_cost, int D_def_move_cost, int A_def_target_finding_cost, int B_def_target_finding_cost, int C_def_target_finding_cost, int D_def_target_finding_cost, float Gold_ratio, float Invisible_ratio, int Max_Gold_value, int Divider_gold) {
        this.max_row = max_row;
        this.max_colmn = max_colmn;
        this.def_starting_gold = def_starting_gold;
        this.Max_Move_step = Max_Move_step;
        this.A_def_move_cost = A_def_move_cost;
        this.B_def_move_cost = B_def_move_cost;
        this.C_def_move_cost = C_def_move_cost;
        this.D_def_move_cost = D_def_move_cost;
        this.A_def_target_finding_cost = A_def_target_finding_cost;
        this.B_def_target_finding_cost = B_def_target_finding_cost;
        this.C_def_target_finding_cost = C_def_target_finding_cost;
        this.D_def_target_finding_cost = D_def_target_finding_cost;
        this.Gold_ratio = Gold_ratio;
        this.Invisible_ratio = Invisible_ratio;
        this.Max_Gold_value = Max_Gold_value;
        this.Divider_gold = Divider_gold;
    }

    public Setttings() {//Default values
        this.max_row = 20;
        this.max_colmn = 20;
        this.def_starting_gold = 200;
        this.Max_Move_step = 3;
        this.A_def_move_cost = 5;
        this.B_def_move_cost = 5;
        this.C_def_move_cost = 5;
        this.D_def_move_cost = 5;
        this.A_def_target_finding_cost = 5;
        this.B_def_target_finding_cost = 10;
        this.C_def_target_finding_cost = 15;
        this.D_def_target_finding_cost = 20;
        this.Gold_ratio = 20.0f;
        this.Invisible_ratio = 10.0f;
        this.Max_Gold_value = 25;
        this.Divider_gold = 5;
        //Oyuncuların başlamgıç konumları
            //A
        this.A_starting_row=0;
        this.A_starting_colmn=0;
            //B
        this.B_starting_row=0;
        this.B_starting_colmn=(this.max_colmn-1);
            //C
        this.C_starting_row=(this.max_row-1);
        this.C_starting_colmn=0;
            //D
        this.D_starting_row=(this.max_row-1);
        this.D_starting_colmn=(this.max_colmn-1);
    }
   
    public void default_settings(){
        this.max_row = 20;
        this.max_colmn = 20;
        this.def_starting_gold = 200;
        this.Max_Move_step = 3;
        this.A_def_move_cost = 5;
        this.B_def_move_cost = 5;
        this.C_def_move_cost = 5;
        this.D_def_move_cost = 5;
        this.A_def_target_finding_cost = 5;
        this.B_def_target_finding_cost = 10;
        this.C_def_target_finding_cost = 15;
        this.D_def_target_finding_cost = 20;
        this.Gold_ratio = 20.0f;
        this.Invisible_ratio = 10.0f;
        this.Max_Gold_value = 25;
        this.Divider_gold = 5;
        //Oyuncuların başlamgıç konumları
            //A
        this.A_starting_row=0;
        this.A_starting_colmn=0;
            //B
        this.B_starting_row=0;
        this.B_starting_colmn=(this.max_colmn-1);
            //C
        this.C_starting_row=(this.max_row-1);
        this.C_starting_colmn=0;
            //D
        this.D_starting_row=(this.max_row-1);
        this.D_starting_colmn=(this.max_colmn-1);
    }
    
    public void start_point_update(){
    
        this.A_starting_row=0;
        this.A_starting_colmn=0;
            //B
        this.B_starting_row=0;
        this.B_starting_colmn=(this.max_colmn-1);
            //C
        this.C_starting_row=(this.max_row-1);
        this.C_starting_colmn=0;
            //D
        this.D_starting_row=(this.max_row-1);
        this.D_starting_colmn=(this.max_colmn-1);
    };
    
    
   //Getters and Setters

    
    
    public int getMax_row() {
        return max_row;
    }

    public void setMax_row(int max_row) {
        this.max_row = max_row;
        start_point_update();
    }

    public int getMax_colmn() {
        return max_colmn;
    }

    public void setMax_colmn(int max_colmn) {
        this.max_colmn = max_colmn;
        start_point_update();
    }

    public int getDef_starting_gold() {
        return def_starting_gold;
    }

    public void setDef_starting_gold(int def_starting_gold) {
        this.def_starting_gold = def_starting_gold;
    }

    public int getMax_Move_step() {
        return Max_Move_step;
    }

    public void setMax_Move_step(int Max_Move_step) {
        this.Max_Move_step = Max_Move_step;
    }

    public int getA_def_move_cost() {
        return A_def_move_cost;
    }

    public void setA_def_move_cost(int A_def_move_cost) {
        this.A_def_move_cost = A_def_move_cost;
    }

    public int getB_def_move_cost() {
        return B_def_move_cost;
    }

    public void setB_def_move_cost(int B_def_move_cost) {
        this.B_def_move_cost = B_def_move_cost;
    }

    public int getC_def_move_cost() {
        return C_def_move_cost;
    }

    public void setC_def_move_cost(int C_def_move_cost) {
        this.C_def_move_cost = C_def_move_cost;
    }

    public int getD_def_move_cost() {
        return D_def_move_cost;
    }

    public void setD_def_move_cost(int D_def_move_cost) {
        this.D_def_move_cost = D_def_move_cost;
    }

    public int getA_def_target_finding_cost() {
        return A_def_target_finding_cost;
    }

    public void setA_def_target_finding_cost(int A_def_target_finding_cost) {
        this.A_def_target_finding_cost = A_def_target_finding_cost;
    }

    public int getB_def_target_finding_cost() {
        return B_def_target_finding_cost;
    }

    public void setB_def_target_finding_cost(int B_def_target_finding_cost) {
        this.B_def_target_finding_cost = B_def_target_finding_cost;
    }

    public int getC_def_target_finding_cost() {
        return C_def_target_finding_cost;
    }

    public void setC_def_target_finding_cost(int C_def_target_finding_cost) {
        this.C_def_target_finding_cost = C_def_target_finding_cost;
    }

    public int getD_def_target_finding_cost() {
        return D_def_target_finding_cost;
    }

    public void setD_def_target_finding_cost(int D_def_target_finding_cost) {
        this.D_def_target_finding_cost = D_def_target_finding_cost;
    }

    public float getGold_ratio() {
        return Gold_ratio;
    }

    public void setGold_ratio(float Gold_ratio) {
        this.Gold_ratio = Gold_ratio;
    }

    public float getInvisible_ratio() {
        return Invisible_ratio;
    }

    public void setInvisible_ratio(float Invisible_ratio) {
        this.Invisible_ratio = Invisible_ratio;
    }

    public int getMax_Gold_value() {
        return Max_Gold_value;
    }

    public void setMax_Gold_value(int Max_Gold_value) {
        this.Max_Gold_value = Max_Gold_value;
    }

    public int getDivider_gold() {
        return Divider_gold;
    }

    public void setDivider_gold(int Divider_gold) {
        this.Divider_gold = Divider_gold;
    }

    public int getA_starting_row() {
        return A_starting_row;
    }

    public void setA_starting_row(int A_starting_row) {
        this.A_starting_row = A_starting_row;
    }

    public int getA_starting_colmn() {
        return A_starting_colmn;
    }

    public void setA_starting_colmn(int A_starting_colmn) {
        this.A_starting_colmn = A_starting_colmn;
    }

    public int getB_starting_row() {
        return B_starting_row;
    }

    public void setB_starting_row(int B_starting_row) {
        this.B_starting_row = B_starting_row;
    }

    public int getB_starting_colmn() {
        return B_starting_colmn;
    }

    public void setB_starting_colmn(int B_starting_colmn) {
        this.B_starting_colmn = B_starting_colmn;
    }

    public int getC_starting_row() {
        return C_starting_row;
    }

    public void setC_starting_row(int C_starting_row) {
        this.C_starting_row = C_starting_row;
    }

    public int getC_starting_colmn() {
        return C_starting_colmn;
    }

    public void setC_starting_colmn(int C_starting_colmn) {
        this.C_starting_colmn = C_starting_colmn;
    }

    public int getD_starting_row() {
        return D_starting_row;
    }

    public void setD_starting_row(int D_starting_row) {
        this.D_starting_row = D_starting_row;
    }

    public int getD_starting_colmn() {
        return D_starting_colmn;
    }

    public void setD_starting_colmn(int D_starting_colmn) {
        this.D_starting_colmn = D_starting_colmn;
    }
   
   
   
   
   
}
