/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import file_templates.A_raund;
import file_templates.C_raund;

/**
 *
 * @author Ertuğrul Demir
 */
public class p_c extends p_b {
    public boolean is_open_Inv_gold=true;
    public int Opened_INV_gold_Number=0;
    public int Current_raound_Opened_INV_gold_Number=0;
    
    private int first_ınv_gold_row=0;
    private int first_ınv_gold_colmn=0;
    private int second_ınv_gold_row=0;
    private int second_ınv_gold_colmn=0;

    
    public p_c(Game_Data data) {
        super(data);
    }

    @Override
    public void play() {
       open_invisible_location_nearest_for_player_c(this.getMap());     
        C_raund Current_round = new C_raund();
        Current_Raund_save_to_class(Current_round);
        //433.satır raund'a true atıyor onları bir arda en altta kullan
        
        
        if (hedef_yerinde_mi()) {//hedef varsa
            Target_save_to_class(Current_round);
            move_A();
            if (_is_over_gold()) {
                get_Gold();
            }
        }else if (!this.have_a_target || !hedef_yerinde_mi()) {//hedef yoksa belirle ve hareket et.
            find_target(this.getMap());
            Target_save_to_class(Current_round);             
            move_A();
            if (_is_over_gold()) {
                get_Gold();
            }

        }
        Update_total_consume();

        Total_Number_of_Opened_INV_gold_save_to_class(Current_round);
        Current_Number_of_opened_INV_gold_save_to_class(Current_round);
        
        Current_Old_Location(Current_round);
        Current_new_Location(Current_round);

        Current_Captured_Gold_save_to_class(Current_round, this.getCurrent_captured_gold());
        Current_Consumed_Gold_save_to_class(Current_round, this.getCurrent_consumed_gold());
        Gold_states_save_to_class(Current_round);//alttaki toteller bunun içinde
        //total_captured_gold_save_to_class(Current_round);
        //total_consumed_gold_save_to_class(Current_round);

        states_save_to_class(Current_round);
        Olaylar_save_to_class(Current_round);

        //Hareketi_Dosyaya_Yaz(Current_round);//File write

        add_to_Data(Current_round);

        //raund bitti sonraki raund için
        //current  ACTION BOOLEAN dataları resetle
        this.is_moved_this_raund = false;
        this.is_target_found_cost_this_raund = false;
        this.is_captured = false;
        //current values set to zero
        this.setCurrent_captured_gold(0);
        this.setCurrent_consumed_gold(0);
        
        //Yeni konumlar  Eski oldu.(sonraki raund için)
        this.setLocation_old_colmn(this.getLocation_new_colmn());
        this.setLocation_old_row(this.getLocation_new_row());
       
       
       
       //raund için düzeltilmiş kodlar  {stop}
       
       
       this.Current_raound_Opened_INV_gold_Number=0; 
       this.is_open_Inv_gold=true;
       
    }

    @Override
    public boolean hedef_yerinde_mi() {
        return super.hedef_yerinde_mi(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //Open INV algorithm
    public void open_invisible_location_nearest_for_player_c(map map){
        int i, j ,MuhatapOlunanGizliAltınSayısı_sayac = 0,MuhatapOlunanGizliAltınSayısı_sayac_2 = 0;
        int [] g_a_l=new int [2];
        int [] g_a_l_2=new int [2];
        int[][] MP = map.getMap();
        int min_distance = map.getSize();//En küçüğü bulmak için varsayılan max değeri ilk değer olarak atıyoruz.
        // 1. açılacak gizli altın için döngü
        for (i = 0; i < map.getMax_row(); i++) {
            for (j = 0; j < map.getMax_colmn(); j++) {
                if (MP[i][j] == 2) {

                    int distance = calculate_path_cost(i, j);

                    if (min_distance > distance) {

                        min_distance = distance;
                        g_a_l[1]=j;
                        g_a_l[0]=i;
                    }
                    MuhatapOlunanGizliAltınSayısı_sayac++;

                }

            }

        }
        
        min_distance = map.getSize();
        // 2. açılacak gizli altın için döngü
         for (i = 0; i < map.getMax_row(); i++) {
            for (j = 0; j < map.getMax_colmn(); j++) {
                if (MP[i][j] == 2) {

                    int distance = calculate_path_cost(i, j);

                    if (min_distance > distance) {
                        boolean aynı_row_mu=g_a_l[0]==i;
                        boolean aynı_colmn_mu=g_a_l[1]==j;
                        
                        if(!(aynı_colmn_mu & aynı_colmn_mu)){  
                       min_distance = distance;
                        g_a_l_2[1]=j;
                        g_a_l_2[0]=i;
                        
                        }

                        
                        
                    }
                    MuhatapOlunanGizliAltınSayısı_sayac_2++;

                }

            }

        }
        
        
        if (MuhatapOlunanGizliAltınSayısı_sayac == 0 & MuhatapOlunanGizliAltınSayısı_sayac_2==0) {
            this.is_open_Inv_gold=false;
            System.out.println("Açmak için haritada gizli altın bulunamadı.");
        } else {
            if(MuhatapOlunanGizliAltınSayısı_sayac!=0){
                Open_invisible_gold_1(g_a_l);
            }
            if(MuhatapOlunanGizliAltınSayısı_sayac_2!=0){
                Open_invisible_gold_2(g_a_l_2);
            }
        }

    }
    public void Open_invisible_gold_1(int [] Invisible_Gold_Location){
        int [][] Map=this.getMap().getMap();
        int row=Invisible_Gold_Location[0];
        int colmn=Invisible_Gold_Location[1];
        if(row!=0 & colmn!=0){
        Map[row][colmn]=3;
        this.first_ınv_gold_row=row;
        this.first_ınv_gold_colmn=colmn;
        this.Opened_INV_gold_Number+=1;
        this.Current_raound_Opened_INV_gold_Number+=1;
        }else{
            System.out.println("Açılacak gizli altın kalmadı");
        }
        
        
    }
    public void Open_invisible_gold_2(int [] Invisible_Gold_Location){
        int [][] Map=this.getMap().getMap();
        int row=Invisible_Gold_Location[0];
        int colmn=Invisible_Gold_Location[1];
        if(row!=0 & colmn!=0){
        Map[row][colmn]=3;
        this.second_ınv_gold_row=row;
        this.second_ınv_gold_colmn=colmn;
        this.Opened_INV_gold_Number+=1;
        this.Current_raound_Opened_INV_gold_Number+=1;
        }else{
        }
    }
    @Override
    public void Player_add_Locations_starting_new_old() {
        //Player C
        this.setStart_row(this.getData().getSet().getC_starting_row());
        this.setStart_colm(this.getData().getSet().getC_starting_colmn());
        
        
        //Ortak kısım
        this.setLocation_new_row(this.getStart_row());
        this.setLocation_new_colmn(this.getStart_colm());
        
        this.setLocation_old_row(this.getStart_row());
        this.setLocation_old_colmn(this.getStart_colm()); 
    }
    @Override
    public void Player_add_costs() {
        this.setMove_cost(this.getData().getSet().getC_def_move_cost());
        this.setTarget_finding_cost(this.getData().getSet().getC_def_target_finding_cost());
    }
    
    //bu sınıfa özhü save to class {start}
    private void state_of_is_Open_INV_gold(C_raund raund){
       raund.setTotal_acılan_gizli_alltın_sayısı(this.Current_raound_Opened_INV_gold_Number);
    }//Bunu states save to clss içerisinde EKLENDİ.
    private void Total_Number_of_Opened_INV_gold_save_to_class(C_raund raund){
    raund.setTotal_acılan_gizli_alltın_sayısı(this.Opened_INV_gold_Number);
    }//play içerisinde EKLENDİ.
    private void Current_Number_of_opened_INV_gold_save_to_class(C_raund raund){
    raund.setBu_raound_Acılan_Gizli_Altın_Sayısı(this.Current_raound_Opened_INV_gold_Number);
    }//play içerisinde EKLENDİ.
    private void Olay_Opened_INV_gold_save_to_class(C_raund raund){
        
        if(this.is_open_Inv_gold){
            if(this.Current_raound_Opened_INV_gold_Number==2){
                String yazı="Bu raund içerisinde 2 adet gizli altın açılmıştır\n";
                String yazı1="Birinci Altın:"+this.getFirst_ınv_gold_row()+","+this.getFirst_ınv_gold_colmn()+"\n";
                String yazı2="İkinci Altın:"+this.getSecond_ınv_gold_row()+","+this.getSecond_ınv_gold_colmn()+"\n";
                string_action_save_to_class(raund, yazı+yazı1+yazı2);
            }else if(this.Current_raound_Opened_INV_gold_Number==1){
                String yazı="Bu raund içerisinde 1 adet gizli altın açılmıştır\n";
                String yazı1="Birinci Altın:"+this.getFirst_ınv_gold_row()+","+this.getFirst_ınv_gold_colmn()+"\n";
                string_action_save_to_class(raund, yazı+yazı1);
            }
        
        }else{
                String yazı="C oyuncusunun Açabileceği Gizli Altın Kalmamıştır.";
                string_action_save_to_class(raund, yazı);
            }
    
    }//Bunu olaylar_Save_to_class içerisinde Eklendi
    //bu sınıfa özhü save to class {stop}
    
    
    //save to class (C_raund)
 
    public void Current_Raund_save_to_class(C_raund raund) {
        raund.setRaund(this.getData().getRaunds().getNumber_of_Raund());
    }
    
    private void Current_Old_Location(C_raund raund) {
        //Eski konumu kayıt et.  
        raund.setOld_Location_colmn(this.getLocation_old_colmn());
        raund.setOld_Location_row(this.getLocation_old_row());
    }

    private void Current_new_Location(C_raund raund) {
        // new location, Yer degistirme, String
        raund.setNew_Location_colmn(this.getLocation_new_colmn());
        raund.setNew_Location_row(this.getLocation_new_row());

        int old_row = raund.getOld_Location_row();
        int old_colmn = raund.getOld_Location_colmn();

        int new_row = raund.getNew_Location_row();
        int new_colmn = raund.getNew_Location_colmn();

        int colmn_Step = Math.abs(old_colmn - new_colmn);
        int row_Step = Math.abs(old_row - new_row);
        raund.setYer_degistirme_kare_sayısı(row_Step + colmn_Step);
        raund.setTotal_Yer_degistirme_kare_sayısı(raund.getTotal_Yer_degistirme_kare_sayısı()+row_Step + colmn_Step);

        //action kısımları play methodunun sonlarında save to class methodlarıyla yazılıcak.
        //String yazı = old_row + "," + old_colmn + " Konumundan " + new_row + "," + new_colmn + " Konumuna hareket edildi";
        //string_action_save_to_class(raund, yazı);
    }

    private void string_action_save_to_class(C_raund raund, String yazı) { //Bunun işlevini yazdır sağlayacak
        raund.setAction(raund.getAction() + '\n' + yazı);
    }

    private void state_of_alive_save_to_class(C_raund raund) {//survive false olursa ve 2. defa gelirse bu sınırsız çalışır.
        raund.setIs_alive(this._is_survive());

        if (!this._is_survive()) {//Eğer yaşamıyorsa rank ata ve sıradaki rank ödülünü güncelle
            raund.setRank(this.getData().getCurrent_Rank_award());
            this.Rank = this.getData().getCurrent_Rank_award();
            //String yazı = "Oyuncu elendi Sıralaması:" + raund.getRank();
            this.getData().setCurrent_Rank_award(this.getData().getCurrent_Rank_award() - 1);
            //string_action_save_to_class(raund, yazı);
            kill();
        }

    }

    private void state_of_gold_captured_save_to_class(C_raund raund) {//altın alındığı zaman çağrılır
        raund.setIs_captured_gold_in_this_raound(this.is_captured);
    }

    private void state_of_Target_finding_save_to_class(C_raund raund) {
        raund.setIs_target_found_this_raund(this.is_target_found_cost_this_raund);
    }

    private void state_of_moved_this_raund_save_to_class(C_raund raund) {
        raund.setIs_moved_this_raund(this.is_moved_this_raund);
    }

    private void states_save_to_class(C_raund raund) {
        state_of_is_Open_INV_gold(raund);
        state_of_Target_finding_save_to_class(raund);
        state_of_moved_this_raund_save_to_class(raund);
        state_of_gold_captured_save_to_class(raund);
        state_of_alive_save_to_class(raund);
    }

    private void Current_Captured_Gold_save_to_class(C_raund raund, int Captured_Value) {
        raund.setCurrent_captured_gold(Captured_Value);
        //String yazı = Captured_Value + "Değerinde altın alındı";
        //string_action_save_to_class(raund, yazı);
    }

    private void total_captured_gold_save_to_class(C_raund raund) {
        raund.setCaptured_gold(this.getCaptured_gold());
    }

    private void Current_Consumed_Gold_save_to_class(C_raund raund, int Consume_Value) {
        //raund.setCurrent_Consume_gold(raund.getCurrent_Consume_gold() + Consume_Value);//Udate_Counsume saeinde buna gerekyok
        raund.setCurrent_Consume_gold(this.getCurrent_consumed_gold());
        //String yazı = Consume_Value + "Değerinde altın harcandı";
        //string_action_save_to_class(raund, yazı);
    }

    private void total_consumed_gold_save_to_class(C_raund raund) {
        raund.setConsume_gold(this.getSpent_gold());
    }

    private void Gold_states_save_to_class(C_raund raund) {
        raund.setGold_in_case(this.getGold());
        //raund.setCaptured_gold(this.getCaptured_gold());//Bunlar gerek kalmadı
        //raund.setConsume_gold(this.getSpent_gold());//Bunlar gerek kalmadı
        total_captured_gold_save_to_class(raund);
        total_consumed_gold_save_to_class(raund);
    }

    //Acion Boolean save_to_class  'olayları yaz' raund.setAction(str)
    //is_moved_this_raund
    //is_target_found_cost_this_raund
    //is_captured
    //total consume this raund
    //alive ölümünü yazdır
    private void Olay_moved_save_to_class(C_raund raund) {
        if (this.is_moved_this_raund) {
            String yazı1 = "Hareket başlangıç konumu: " + this.getLocation_old_row() + "," + this.getLocation_old_colmn();
            String yazı2 = " Hareket bitiş konumu: " + this.getLocation_new_row() + "," + this.getLocation_new_colmn();
            string_action_save_to_class(raund, yazı1 + yazı2);
        }

    }

    private void Olay_target_found_cost_save_to_class(C_raund raund) {
        if (this.is_target_found_cost_this_raund) {
            String yazı = "Bu raund içerisinde Hedef belirlendi, Hedefin konumu :" + this.getTarget_row() + "," + this.getTarget_colmn();
            string_action_save_to_class(raund, yazı);
        }

    }

    private void Olay_captured_gold_this_Raund_save_to_class(C_raund raund) {
        if (this.is_captured) {
            String yazı = "Bu raund içerisinde altın ele geçirildi miktarı: " + this.getCurrent_captured_gold();
            string_action_save_to_class(raund, yazı);
        }

    }

    private void Olay_total_consume_this_round_save_to_class(C_raund round) {
        if (this.is_moved_this_raund || this.is_target_found_cost_this_raund) {
            String yazı = "Bu raund boyunca harcanan altın miktarı: " + this.getCurrent_consumed_gold();
            string_action_save_to_class(round, yazı);
        }

    }

    private void Olay_Elendin_save_to_class(C_raund round) {
        if (!isSurvive_state()) {
            //Ölüyse
            String yazı = "C oyuncusu Elendiniz, Mevcut dereceniz:" + this.Rank;
            string_action_save_to_class(round, yazı);
        }

    }

    private void Olaylar_save_to_class(C_raund raund) {
        Olay_Opened_INV_gold_save_to_class(raund);
        Olay_target_found_cost_save_to_class(raund);
        Olay_moved_save_to_class(raund);
        Olay_total_consume_this_round_save_to_class(raund);
        Olay_captured_gold_this_Raund_save_to_class(raund);
        Olay_Elendin_save_to_class(raund);
    }

    private void Target_save_to_class(C_raund raund) {
        raund.setTarget_Row(this.getTarget_row());
        raund.setTarget_Colmn(this.getTarget_colmn());
        //Target found cos action play de yazılıcak
        //String yazı = "Mevcut Hedef: " + this.getTarget_row() + "," + this.getTarget_colmn();
        //string_action_save_to_class(raund, yazı);
    }

    private void add_to_Data(C_raund raund) {
        this.getData().getRaunds().add_C(raund);
    }

    private void Hareketi_Dosyaya_Yaz(C_raund raund) {
        //Verilen  (new A_raund_kayıtları) hareketi dosyaya yazdırır.
        String Yıldız = "*************************************************************************************************************************";
        String Cizgi = "-------------------------------------------------------------------------------------------------------------------------";
        System.out.println(Yıldız);
        System.out.println(Cizgi);
        System.out.println(Yıldız);

        //System.out.println(Cizgi);
        //action
        System.out.println("Gerçekleşen olaylar: ");
        System.out.println(raund.getAction());
        
        //Raund
        System.out.println("Raund:" + raund.getRaund());
        //Açılan Inv gold lar
        
        //Target
        System.out.println("Target row: " + raund.getTarget_Row() + " Target colmn: " + raund.getTarget_Colmn());
        //location
        System.out.println("Konum row:" + raund.getNew_Location_row() + " Konum colmn: " + raund.getNew_Location_colmn());
        //step  
        System.out.println("Atılan adım sayısı: " + raund.getYer_degistirme_kare_sayısı() + " Adet karedir");
        //Gold 
        //in case
        System.out.println("Kasadaki altın miktarı: " + raund.getGold_in_case());
        //Toplam ele geçirilen altın miktarı
        System.out.println("Toplamda ele geçirilen altın mitarı: " + raund.getCaptured_gold());
        //Toplamda harcanan altın miktarı
        System.out.println("Toplamda harcanan altın miktarı: " + raund.getConsume_gold());
        //Bu raund içerisinde kazanılan altın:
        System.out.println("Bu raund içerisinde kazanılan altın: " + raund.getCurrent_captured_gold());
        //Bu raund içerisinde harcanan altın:
        System.out.println("Bu raund içerisinde harcanan altın: " + raund.getCurrent_Consume_gold());

        //kapanış
        System.out.println(Yıldız);
        System.out.println(Cizgi);
        System.out.println(Yıldız);
    }
//Finished copy past
    

    //getter & setter
    public int getFirst_ınv_gold_row() {
        return first_ınv_gold_row;
    }

    public void setFirst_ınv_gold_row(int first_ınv_gold_row) {
        this.first_ınv_gold_row = first_ınv_gold_row;
    }

    public int getFirst_ınv_gold_colmn() {
        return first_ınv_gold_colmn;
    }

    public void setFirst_ınv_gold_colmn(int first_ınv_gold_colmn) {
        this.first_ınv_gold_colmn = first_ınv_gold_colmn;
    }

    public int getSecond_ınv_gold_row() {
        return second_ınv_gold_row;
    }

    public void setSecond_ınv_gold_row(int second_ınv_gold_row) {
        this.second_ınv_gold_row = second_ınv_gold_row;
    }

    public int getSecond_ınv_gold_colmn() {
        return second_ınv_gold_colmn;
    }

    public void setSecond_ınv_gold_colmn(int second_ınv_gold_colmn) {
        this.second_ınv_gold_colmn = second_ınv_gold_colmn;
    }
    
    
}
