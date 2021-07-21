/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import file_templates.A_raund;

/**
 *
 * @author Ertuğrul Demir
 */
public class p_b extends p_a {

    public p_b(Game_Data data) {
        super(data);
        
    }

    @Override
    public void Player_add_costs() {
        this.setMove_cost(this.getData().getSet().getB_def_move_cost());
        this.setTarget_finding_cost(this.getData().getSet().getB_def_target_finding_cost());
    }

    @Override
    public void Player_add_Locations_starting_new_old() {
        //Player B
        this.setStart_row(this.getData().getSet().getB_starting_row());
        this.setStart_colm(this.getData().getSet().getB_starting_colmn());
        
        
        //Ortak kısım
        this.setLocation_new_row(this.getStart_row());
        this.setLocation_new_colmn(this.getStart_colm());
        
        this.setLocation_old_row(this.getStart_row());
        this.setLocation_old_colmn(this.getStart_colm());    
    }

    @Override
    public int calculate_path_cost(int i, int j) {
        return super.calculate_path_cost(i, j); //To change body of generated methods, choose Tools | Templates.
    }
    public int calculate_total_move_step(int distance){
        int full_step=(int)(distance / this.getMax_Move_step());
        int moded= distance % this.getMax_Move_step();
        if(moded>0){//tam katı değilse (tam olmayan fazladan adım atıcak)
            full_step+=1;
        }//tam katıdır 
        return full_step;
    }
    public int gold_value_in_location(int i ,int j){
        int [][] values_map=this.getMap().getGold_value_map();
        return values_map[i][j];
    }
    public int calculate_target_cost_for_Player_B(int i,int j){
        int distance=calculate_path_cost(i, j);
        int step_of_distance=calculate_total_move_step(distance);
        int Target_gold_value=gold_value_in_location(i, j);
        // sonuç = (gelen altın)  - (ulaşmak için harcanan altın)
        int result_of_target_gold= Target_gold_value - (step_of_distance*this.getMove_cost());
        
    return result_of_target_gold;
    }
    public int the_Worst_result_for_Get_target(){
    return this.getMap().getMax_Gold_value()-(this.getMap().getSize()*this.getMove_cost()) ;
    }
    
    @Override
    public void find_target(Game.map map) {
        //super.find_target(map); //To change body of generated methods, choose Tools | Templates.
        int i, j, MuhatapOlunanAltınSayısı_sayac = 0;
        int[][] MP = map.getMap();
        //LinkedList<Gold_Nodes> goldNodes= new LinkedList<>();
        int[][] gold_value_map = map.getGold_value_map();
        int max_gain = the_Worst_result_for_Get_target();//En küçüğü bulmak için varsayılan max değeri ilk değer olarak atıyoruz.

        for (i = 0; i < map.getMax_row(); i++) {
            for (j = 0; j < map.getMax_colmn(); j++) {
                if (MP[i][j] == 1 || MP[i][j] == 3) {// 1 görünür altın  3--> sonradan göürünür olmuş altın

                    int gain = calculate_target_cost_for_Player_B(i, j);

                    //Haritadaki en kazançlı (altın - ulaşıma harcanan altın) altına git. (B'nİn hedef bulma stratejisi)
                    if (max_gain < gain) {

                        max_gain = gain;
                        this.setTarget_colmn(j);
                        this.setTarget_row(i);

                    }
                    MuhatapOlunanAltınSayısı_sayac++;
                    //goldNodes.add(new Gold_Nodes(i, j,goldValue));
                }

            }

        }
        if (MuhatapOlunanAltınSayısı_sayac == 0) {
            System.out.println("Hedef Tayini için haritada altın bulunamadı.");
        } else {
            target_Finding_cost_add_to_current_consume();//Hedef bulmak için gerekli alın alınır
            //Buraya eylem kaydını ekle (eylem tarket atandı {this.setTarget...}) //target ... bulundu
            this.have_a_target = true;
            is_target_found_cost_this_raund = true;
        }

        //this.setTarget_colmn();
        //this.setTarget_row();
    }

    @Override
    public void play() {
        super.play(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Olay_Elendin_save_to_class(A_raund round) {
                if (!isSurvive_state()) {
            //Ölüyse
            String yazı = "B oyuncusu Elendiniz, Mevcut dereceniz:" + this.Rank;
            string_action_save_to_class(round, yazı);
        }
    }

    @Override
    public void add_to_Data(A_raund raund) {
        this.getData().getRaunds().add_B(raund);
    }
    
    
    
}
