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
public class p_d extends p_b {

    public p_a player_A;
    public p_b player_b;
    public p_c player_c;
    

    public p_d(Game_Data data) {
        super(data);
    }

    @Override
    public void play() {
        super.play(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Player_add_Locations_starting_new_old() {
        //Player D
        this.setStart_row(this.getData().getSet().getD_starting_row());
        this.setStart_colm(this.getData().getSet().getD_starting_colmn());

        //Ortak kısım
        this.setLocation_new_row(this.getStart_row());
        this.setLocation_new_colmn(this.getStart_colm());

        this.setLocation_old_row(this.getStart_row());
        this.setLocation_old_colmn(this.getStart_colm());
    }

    @Override
    public void Player_add_costs() {
        this.setMove_cost(this.getData().getSet().getD_def_move_cost());
        this.setTarget_finding_cost(this.getData().getSet().getD_def_target_finding_cost());
    }

    //add to data methodunu ekle.

    @Override
    public void add_to_Data(A_raund raund) {
        this.getData().getRaunds().add_D(raund);
    }
    
    
    //Target Finding
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

                    
                    ////////////////////////////////////////////////////7
                    if (is_there_mark_this_target(player_A,player_b,player_c, i, j)) { 
                        if (is_get_Target_able_Control(player_A,player_b,player_c, i, j)) {
                            // eğer i,j hedefi daha önceden hedef olarak seçilmişse.
                                // eğer  i,j ye diğerlerinden önce gidebiliyorsa. i,j yi kendi hedefi belirleyebilir.
                            
                            if (max_gain < gain) {

                                max_gain = gain;
                                this.setTarget_colmn(j);
                                this.setTarget_row(i);
                            }
                        }
                    }else {
                         if (max_gain < gain) {

                                max_gain = gain;
                                this.setTarget_colmn(j);
                                this.setTarget_row(i);
                            }
                    }
                    MuhatapOlunanAltınSayısı_sayac++;

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

    }
    public boolean is_get_Target_able_Control(Player p, int i, int j) {
        //(p_a Player_a,p_b Player_b, p_c Player_c
        //int distance_A =calculate_path_distance_for_others(Player_a, i, j);
        //int distance_B =calculate_path_distance_for_others(Player_b, i, j);
        //int distance_C =calculate_path_distance_for_others(Player_c, i, j);
        int distance_other_player = calculate_path_distance_for_others(p, i, j);
        int distance_D = calculate_path_cost(i, j);
        if (distance_D < distance_other_player) {
            return true;
        } else {
            return false;
        }
    }
        public boolean is_get_Target_able_Control(p_a a,p_b b, p_c c, int i, int j) {
        //(p_a Player_a,p_b Player_b, p_c Player_c
        boolean a_target_captur_able=is_get_Target_able_Control(a, i, j);
        boolean b_target_captur_able=is_get_Target_able_Control(b, i, j);
        boolean c_target_captur_able=is_get_Target_able_Control(c, i, j);
        
        
        // Diğer oyuncunun hedefi i,j değilse ondan önce varılabilir. ozaman doğrudan true;
        if(!is_there_mark_this_target(a, i, j)){
        a_target_captur_able=true;
        }
        if(!is_there_mark_this_target(b, i, j)){
        b_target_captur_able=true;
        }
        if(!is_there_mark_this_target(c, i, j)){
        c_target_captur_able=true;
        }
        
        
        //int distance_A =calculate_path_distance_for_others(Player_a, i, j);
        //int distance_B =calculate_path_distance_for_others(Player_b, i, j);
        //int distance_C =calculate_path_distance_for_others(Player_c, i, j);
        //int distance_other_player = calculate_path_distance_for_others(p, i, j);

        if(a_target_captur_able && b_target_captur_able && c_target_captur_able){
        return true;
        }
        
        return false;
    }
    
    
    public int calculate_path_distance_for_others(Player p, int i, int j) {
        int cost_colmn = Math.abs(p.getLocation_new_colmn() - j);
        int cost_row = Math.abs(p.getLocation_new_row() - i);

        int Total_distance = cost_colmn + cost_row;

        return Total_distance;
    }

    public boolean is_there_mark_this_target(Player p, int i, int j) {
        boolean is_colmn_equal = p.getTarget_colmn() == j;
        boolean is_row_equal = p.getTarget_row() == i;

        if (is_colmn_equal && is_row_equal) {
            return true;
        }
        return false;
    }
    
    public boolean is_there_mark_this_target(p_a a,p_b b, p_c c, int i, int j) {
        boolean a_is_there_mark_this_target=is_there_mark_this_target(a, i, j);
        boolean b_is_there_mark_this_target=is_there_mark_this_target(b, i, j);
        boolean c_is_there_mark_this_target=is_there_mark_this_target(c, i, j);

        if (a_is_there_mark_this_target || b_is_there_mark_this_target || c_is_there_mark_this_target) {
            return true;
        }
        return false;
    }
    public void add_all_other_player(p_a Player_A, p_b Player_B, p_c Player_C) {
        this.setPlayer_A(Player_A);
        this.setPlayer_b(Player_B);
        this.setPlayer_c(Player_C);
    }

    public p_a getPlayer_A() {
        return player_A;
    }

    public void setPlayer_A(p_a player_A) {
        this.player_A = player_A;
    }

    public p_b getPlayer_b() {
        return player_b;
    }

    public void setPlayer_b(p_b player_b) {
        this.player_b = player_b;
    }

    public p_c getPlayer_c() {
        return player_c;
    }

    public void setPlayer_c(p_c player_c) {
        this.player_c = player_c;
    }

}
