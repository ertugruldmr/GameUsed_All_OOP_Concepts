/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Ertuğrul Demir
 */
public class map {
        //map
    private int[][] Map;
    private final int max_row;
    private final int max_colmn;
    private final int size;
    
    private int[][] Gold_value_map;
    private final int Max_Gold_value;
    private final int Divider_gold;
    private final int max_force;
    
    //gold  1-->visible  2-->invisible  3-->sonradan görünen
    private final float Gold_ratio;
    private final float Invisible_ratio;
    
    private final int number_of_gold;
    private final int visible_gold_number;
    private final int invisible_gold_number;

    public map(int max_row, int max_colmn,float Gold_ratio,  float Invisible_ratio, int Max_Gold_value,int Divider_gold) {
        this.max_row = max_row;
        this.max_colmn = max_colmn;
        this.size=max_row*max_colmn;
        
        this.Max_Gold_value=Max_Gold_value;
        this.Divider_gold=Divider_gold;
        this.max_force=(int)(this.Max_Gold_value/this.Divider_gold);
        //Arrays
        this.Map=new int[this.max_row][this.max_colmn];
        this.Gold_value_map=new int[this.max_row][this.max_colmn];
        
        //gold numbers
        this.Gold_ratio=Gold_ratio;
        this.Invisible_ratio=Invisible_ratio;
        this.number_of_gold=(int)(this.size * (this.Gold_ratio/100.0f));
        this.invisible_gold_number=(int)(this.number_of_gold*(this.Invisible_ratio/100.0f));
        this.visible_gold_number= this.number_of_gold - this.invisible_gold_number;
        
        //First map value
        _array_zero_();
        //default player location
        this.Map[0][0]=-1;                          //A--> -1
        this.Map[0][this.max_colmn-1]=-2;             //B-->-2
        this.Map[this.max_row-1][0]=-3;               //C-->-3
        this.Map[this.max_row-1][this.max_colmn-1]=-4;  //D-->-4

    }
    private void _array_zero_(){
        int i,j;
    for (i=0;i<this.max_row;i++){
        for(j=0;j<this.max_colmn;j++){
            this.Map[i][j]=0;
            this.Gold_value_map[i][j]=0;
        }
    }
   }
    public void gold_add_to_map(){
       Random ran_row=new Random();
       Random ran_clomn=new Random();
       Random ran_Gold=new Random();
        
        //visible gold
        for (int i = 0; i < this.visible_gold_number; i++) {
        int row= ran_row.nextInt(this.max_row);
        int colmn= ran_clomn.nextInt(this.max_colmn);
        int Gold_value=ran_Gold.nextInt(this.max_force);
        
            if(this.Map[row][colmn]==0){
                this.Map[row][colmn]=1;
            }else{
            --i;
            }     
        }
        
        //invisible gold
        for (int i=0;i<this.invisible_gold_number;i++){
        int row= ran_row.nextInt(this.max_row);
        int colmn= ran_clomn.nextInt(this.max_colmn);
        
        if(this.Map[row][colmn]==0){
                this.Map[row][colmn]=2;
            }else{
            --i;
            }     
        }
        
        
        }
    public void Gold_value_add(){
    for(int i = 0; i < this.visible_gold_number; i++){
        int _row,_colmn;
        for(_row=0;_row<this.max_row;_row++){
            
            for(_colmn=0;_colmn<this.max_colmn;_colmn++){
            Random Gold=new Random();
            int Gold_Value;
                Gold_Value = (1 + Gold.nextInt(this.max_force));
                Gold_Value*=this.Divider_gold;
                if(this.Map[_row][_colmn]==1 || this.Map[_row][_colmn]==2 ){               
                        this.Gold_value_map[_row][_colmn]=Gold_Value;
                }
            
            }
        }
    }
    
    
    }
    
        
    public void yazıdır(){
    for(int i=0; i<this.max_row;i++){
            System.out.print(i+".: ");
        for(int j=0;j<this.max_colmn;j++){
            System.out.print(this.Map[i][j]+" ");
        }
            System.out.println("");
    }
        
    }
    
    public void yazdır_gold_value(){
    for(int i=0; i<this.max_row;i++){
            System.out.print(i+".: ");
        for(int j=0;j<this.max_colmn;j++){
            System.out.print(this.Gold_value_map[i][j]+" ");
        }
            System.out.println("");
    }    
    
    }
     
    
    
    
  //Getter & Setter methods

    public int[][] getMap() {
        return Map;
    }

    public int getMax_row() {
        return max_row;
    }

    public int getMax_colmn() {
        return max_colmn;
    }

    public int getSize() {
        return size;
    }

    public int[][] getGold_value_map() {
        return Gold_value_map;
    }

    public int getMax_Gold_value() {
        return Max_Gold_value;
    }

    public int getDivider_gold() {
        return Divider_gold;
    }

    public int getMax_force() {
        return max_force;
    }

    public float getGold_ratio() {
        return Gold_ratio;
    }

    public float getInvisible_ratio() {
        return Invisible_ratio;
    }

    public int getNumber_of_gold() {
        return number_of_gold;
    }

    public int getVisible_gold_number() {
        return visible_gold_number;
    }

    public int getInvisible_gold_number() {
        return invisible_gold_number;
    }
    
}
