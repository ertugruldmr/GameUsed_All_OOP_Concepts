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
public class p_a extends Player {

    public boolean have_a_target;
    public boolean is_moved_this_raund;
    public boolean is_target_found_cost_this_raund;
    public boolean is_captured;
    public int Rank;

    public p_a(Game_Data data) {
        super(data);
        Player_add_costs();
        Player_add_Locations_starting_new_old();
        
        this.have_a_target = false;
        this.is_moved_this_raund = false;
        this.is_target_found_cost_this_raund = false;
        this.is_captured = false;
    }
        public  void Player_add_Locations_starting_new_old(){      
        //player'a özgü kısım A
        this.setStart_row(this.getData().getSet().getA_starting_row());
        this.setStart_colm(this.getData().getSet().getA_starting_colmn());
        
        
        //Ortak kısım
        this.setLocation_new_row(this.getStart_row());
        this.setLocation_new_colmn(this.getStart_colm());
        
        this.setLocation_old_row(this.getStart_row());
        this.setLocation_old_colmn(this.getStart_colm());
    }
        public void Player_add_costs(){
        this.setMove_cost(this.getData().getSet().getA_def_move_cost());
        this.setTarget_finding_cost(this.getData().getSet().getA_def_target_finding_cost());
        }
    public p_a(map map) {
        super(map);
        deneme_degistir(map);
    }

    public void PlayTurn() {

        //tur için kayır sınıfı açılıyor.
        A_raund Current_round = new A_raund();
        Current_Raund_save_to_class(Current_round);
        if (_is_survive()) {//yaşyorsa
            //Oyna
            if (this.have_a_target) {//hedef varsa
                move_A();
                _insan_gibi_bir_oynama_hızı(); //İçerisinde zaten getgold var.

            } else if (!this.have_a_target) {//hedef yoksa belirle
                find_target(this.getMap());
            }

        }

        Gold_states_save_to_class(Current_round);
        state_of_alive_save_to_class(Current_round);
        //Hareketi_Dosyaya_Yaz(Current_round);//File write
        add_to_Data(Current_round);

    }

    public void play() {
        A_raund Current_round = new A_raund();
        Current_Raund_save_to_class(Current_round);
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
    }

    public void _insan_gibi_bir_oynama_hızı() {
        
        try {
            Thread.sleep(1000 / this.play_turn_speed);
        } catch (InterruptedException e) {
        }

    }
    public boolean hedef_yerinde_mi(){
     int [][] value_map=this.getData().getMap().getGold_value_map();
     if(this.have_a_target){
         int colmn=this.getTarget_colmn();
         int row =this.getTarget_row();
         if(value_map[row][colmn]>0){
         return true;
         }
     }
    return false;
    }
    public void find_target(map map) {
        int i, j, MuhatapOlunanAltınSayısı_sayac = 0;
        int[][] MP = map.getMap();
        //LinkedList<Gold_Nodes> goldNodes= new LinkedList<>();
        int[][] gold_value_map = map.getGold_value_map();
        int min_distance = map.getSize();//En küçüğü bulmak için varsayılan max değeri ilk değer olarak atıyoruz.

        for (i = 0; i < map.getMax_row(); i++) {
            for (j = 0; j < map.getMax_colmn(); j++) {
                if (MP[i][j] == 1 || MP[i][j] == 3) {
                    //int goldValue=gold_value_map[i][j]; // A gold value'yi ignorlar.
                    int distance = calculate_path_cost(i, j);

                    //haritadaki en yakın altına git. (A'nın hedef bulma stratejisi)
                    if (min_distance > distance) {

                        min_distance = distance;
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

    public int calculate_path_cost(int i, int j) {

        int cost_colmn = Math.abs(this.getLocation_new_colmn() - j);
        int cost_row = Math.abs(this.getLocation_new_row() - i);

        int Total_distance = cost_colmn + cost_row;

        return Total_distance;
    }

    public void move_A() {
        int[] steps = new int[2];
        //colmn--> left --> - (stundan çıkarma)
        //colmn --> right --> +  (stuna ekleme)
        //row --> up --> -  (satırdan çıkarma            
        //row --> down --> + (satıra ekleme)
        
        //satır yada stun target ile denk olduğunda problem çıkıyor.
        
        if (this.getTarget_row() > this.getLocation_new_row()) {
            //altın (ın satırı)  aşağıdaysa

            if (this.getTarget_colmn() > this.getLocation_new_colmn()) {
                //altın sağ taraftaysa
                //altın --> aşağıda ve sağda    
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Down(steps[0]);//row
                Move_to_Right(steps[1]);//column
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);
            } else if (this.getTarget_colmn() < this.getLocation_new_colmn()) {
                //altın sol taraftaysa
                //altın --> aşağıda ve solda
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Down(steps[0]);//row
                Move_to_Left(steps[1]);//colmn
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);
            }else{//stunlar eşitse sadece satırı oynatıcak zaten
                //sadece aşşağı in    
                Calculate_step(steps);
                Move_to_Down(steps[0]);//row
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
            
            }

        } else if (this.getTarget_row() < this.getLocation_new_row()) {
            //altın (ın satırı)  yukarıda
            if (this.getTarget_colmn() > this.getLocation_new_colmn()) {
                //altın sağ taraftaysa
                //altın --> yukarıda ve sağda    
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Up(steps[0]);//row
                Move_to_Right(steps[1]);//colmn
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);

            } else if (this.getTarget_colmn() < this.getLocation_new_colmn()) {
                //altın sol taraftaysa
                //altın --> yukarıda ve solda
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Up(steps[0]);//row
                Move_to_Left(steps[1]);//colmn
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);
            }else{//stunlar eşitse sadece satırı oynatıcak zaten
                //sadece yukarı in    
                Calculate_step(steps);
                Move_to_Up(steps[0]);//row
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
            
            }

        } else {//satırlar eşitse sadece stunlarla (colmn lar ile hareket et)
            // sadece stunlarda hareket et.
            if (this.getTarget_colmn() > this.getLocation_new_colmn()) {
                //altın sağ taraftaysa  
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Right(steps[1]);//column
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);
            } else if (this.getTarget_colmn() < this.getLocation_new_colmn()) {
                //altın sol taraftaysa
                Calculate_step(steps);
                //Current_Old_Location(raund);
                //Hareket et
                Move_to_Left(steps[1]);//colmn
                move_cost_add_to_current_consume(); //Hareket etmenin bedeli kesildi
                this.is_moved_this_raund = true;
                //Buraya hareket kaydını ekle
                //Current_new_Location(raund);
            }else{//stunlar eşitse sadece satırı oynatıcak zaten
                //Hem stular hem de satırlar eşitse altının üzerinde olmalısın.
                
                //eğer altının üzerindeysen onu al.
                if(_is_over_gold()){
                    //get_Gold();
                }
                
            
            }
            
        }

    }

    private void Calculate_step(int[] steps) {

        int raw_colmn_Step = Math.abs(this.getLocation_new_colmn() - this.getTarget_colmn());
        int raw_row_Step = Math.abs(this.getLocation_new_row() - this.getTarget_row());
        int r_step = -10, c_step = -10, max_adım = this.getMax_Move_step();
        int yarım_adım = (int) (max_adım / 2);

        //Max adımdan büyükse adımı max adım kadar olsun 3+3=6 6>3 ? çöz kardeş
        if (raw_colmn_Step > max_adım & raw_row_Step > max_adım) {//2 side max adımdan büyükse
            c_step = yarım_adım;
            r_step = max_adım - yarım_adım;
        } else if (raw_colmn_Step > max_adım || raw_row_Step > max_adım) {// birisi büyükse
            if (raw_colmn_Step <= max_adım) {
                c_step = raw_colmn_Step;
                r_step = max_adım - raw_colmn_Step;

            } else if (raw_row_Step <= max_adım) {
                r_step = raw_row_Step;
                c_step = max_adım - raw_row_Step;
            }
        } else {//2 side küçük veya eşitse 

            if (raw_colmn_Step + raw_row_Step > max_adım) {//Hiçbiri max adımdan büyük değilken toplamları max adımdan büyükse.
                c_step = raw_colmn_Step;
                r_step = max_adım - raw_colmn_Step;

            } else {//Hiçbiri max adımdan büyük değilken toplamları da  max adımdan üçükse.
                c_step = raw_colmn_Step;
                r_step = raw_row_Step;
            }
        }

        steps[0] = r_step;
        steps[1] = c_step;

    }
//Bu stepler  move methodunda  ayarlamı olmalı.  distance % üst_hareket_sınır= step

    private void Move_to_Left(int step) {
        //colmn --> left --> -  (stundan Çıkartma)
        this.setLocation_new_colmn(this.getLocation_new_colmn() - step);
    }

    private void Move_to_Right(int step) {
        //colmn --> right --> +  (stuna ekleme)
        this.setLocation_new_colmn(this.getLocation_new_colmn() + step);
    }

    private void Move_to_Up(int step) {
        //row --> up --> -  (satırdan çıkarma)
        this.setLocation_new_row(this.getLocation_new_row() - step);
    }

    private void Move_to_Down(int step) {
        //row --> down --> + (satıra ekleme)
        this.setLocation_new_row(this.getLocation_new_row() + step);
    }

    public boolean _is_over_gold() {
        boolean is_colmn_equalse = this.getTarget_colmn() == this.getLocation_new_colmn();
        boolean is_row_equalse = this.getTarget_row() == this.getLocation_new_row();
        return (is_colmn_equalse && is_row_equalse);
    }

    public void control_the_over_gold_and_execute() {
        if (_is_over_gold()) {
            get_Gold();
        }

    }

    public void get_Gold() {
        int i = this.getLocation_new_row();
        int j = this.getLocation_new_colmn();
        int[][] MP = this.getMap().getMap();
        int[][] MP_value = this.getMap().getGold_value_map();

        if (MP[i][j] == 1 || MP[i][j] == 3) {
            if (MP_value[i][j] > 0) {
                this.setCurrent_captured_gold(MP_value[i][j]);//Bu el ele geçirilen altın.
                this.setCaptured_gold(this.getCaptured_gold() + MP_value[i][j]);//totalde ele geçirilen altın.
                this.setGold(this.getGold() + MP_value[i][j]);
                this.is_captured = true;
                //Current_Captured_Gold_save_to_class(raund, MP_value[i][j]);
                //Kayıt al
                //Hamle Düğümüne Eylemi(A i j adresindeki altını alındı) yaz 
                //Kayıt al
                MP[i][j] = 0;         //altın adresi siliniyor.
                MP_value[i][j] = 0; //adresteki miktar tüketiliyor.
                this.have_a_target = false;
                //gold_captured_save_to_class(raund);
            } else {
                System.out.println("Verilen adreste Altın yok");
            }

        }

    }

    //save to class (raund)
    public void Current_Raund_save_to_class(A_raund raund) {
        raund.setRaund(this.getData().getRaunds().getNumber_of_Raund());
    }

    private void Current_Old_Location(A_raund raund) {
        //Eski konumu kayıt et.  
        raund.setOld_Location_colmn(this.getLocation_old_colmn());
        raund.setOld_Location_row(this.getLocation_old_row());
    }

    private void Current_new_Location(A_raund raund) {
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

    public void string_action_save_to_class(A_raund raund, String yazı) { //Bunun işlevini yazdır sağlayacak
        raund.setAction(raund.getAction() + '\n' + yazı);
    }

    private void state_of_alive_save_to_class(A_raund raund) {//survive false olursa ve 2. defa gelirse bu sınırsız çalışır.
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

    private void state_of_gold_captured_save_to_class(A_raund raund) {//altın alındığı zaman çağrılır
        raund.setIs_captured_gold_in_this_raound(this.is_captured);
    }

    private void state_of_Target_finding_save_to_class(A_raund raund) {
        raund.setIs_target_found_this_raund(this.is_target_found_cost_this_raund);
    }

    private void state_of_moved_this_raund_save_to_class(A_raund raund) {
        raund.setIs_moved_this_raund(this.is_moved_this_raund);
    }

    private void states_save_to_class(A_raund raund) {
        state_of_Target_finding_save_to_class(raund);
        state_of_moved_this_raund_save_to_class(raund);
        state_of_gold_captured_save_to_class(raund);
        state_of_alive_save_to_class(raund);
    }

    private void Current_Captured_Gold_save_to_class(A_raund raund, int Captured_Value) {
        raund.setCurrent_captured_gold(Captured_Value);
        //String yazı = Captured_Value + "Değerinde altın alındı";
        //string_action_save_to_class(raund, yazı);
    }

    private void total_captured_gold_save_to_class(A_raund raund) {
        raund.setCaptured_gold(this.getCaptured_gold());
    }

    private void Current_Consumed_Gold_save_to_class(A_raund raund, int Consume_Value) {
        //raund.setCurrent_Consume_gold(raund.getCurrent_Consume_gold() + Consume_Value);//Udate_Counsume saeinde buna gerekyok
        raund.setCurrent_Consume_gold(this.getCurrent_consumed_gold());
        //String yazı = Consume_Value + "Değerinde altın harcandı";
        //string_action_save_to_class(raund, yazı);
    }

    private void total_consumed_gold_save_to_class(A_raund raund) {
        raund.setConsume_gold(this.getSpent_gold());
    }

    private void Gold_states_save_to_class(A_raund raund) {
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
    private void Olay_moved_save_to_class(A_raund raund) {
        if (this.is_moved_this_raund) {
            String yazı1 = "Hareket başlangıç konumu: " + this.getLocation_old_row() + "," + this.getLocation_old_colmn();
            String yazı2 = " Hareket bitiş konumu: " + this.getLocation_new_row() + "," + this.getLocation_new_colmn();
            string_action_save_to_class(raund, yazı1 + yazı2);
        }

    }

    private void Olay_target_found_cost_save_to_class(A_raund raund) {
        if (this.is_target_found_cost_this_raund) {
            String yazı = "Bu raund içerisinde Hedef belirlendi, Hedefin konumu :" + this.getTarget_row() + "," + this.getTarget_colmn();
            string_action_save_to_class(raund, yazı);
        }

    }

    private void Olay_captured_gold_this_Raund_save_to_class(A_raund raund) {
        if (this.is_captured) {
            String yazı = "Bu raund içerisinde altın ele geçirildi miktarı: " + this.getCurrent_captured_gold();
            string_action_save_to_class(raund, yazı);
        }

    }

    private void Olay_total_consume_this_round_save_to_class(A_raund round) {
        if (this.is_moved_this_raund || this.is_target_found_cost_this_raund) {
            String yazı = "Bu raund boyunca harcanan altın miktarı: " + this.getCurrent_consumed_gold();
            string_action_save_to_class(round, yazı);
        }

    }

    public void Olay_Elendin_save_to_class(A_raund round) {
        if (!isSurvive_state()) {
            //Ölüyse
            String yazı = "A oyuncusu Elendiniz, Mevcut dereceniz:" + this.Rank;
            string_action_save_to_class(round, yazı);
        }

    }

    private void Olaylar_save_to_class(A_raund raund) {
        Olay_target_found_cost_save_to_class(raund);
        Olay_moved_save_to_class(raund);
        Olay_total_consume_this_round_save_to_class(raund);
        Olay_captured_gold_this_Raund_save_to_class(raund);
        Olay_Elendin_save_to_class(raund);
    }

    private void Target_save_to_class(A_raund raund) {
        raund.setTarget_Row(this.getTarget_row());
        raund.setTarget_Colmn(this.getTarget_colmn());
        //Target found cos action play de yazılıcak
        //String yazı = "Mevcut Hedef: " + this.getTarget_row() + "," + this.getTarget_colmn();
        //string_action_save_to_class(raund, yazı);
    }

    public void add_to_Data(A_raund raund) {
        this.getData().getRaunds().add_A(raund);
    }

    private void Hareketi_Dosyaya_Yaz(A_raund raund) {
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

    public boolean _is_survive() {
        return this.getGold() > 0;
    }

    public void survive_state_execute(A_raund raund) {
        if (!_is_survive()) {
            kill();
        }

    }  //Sanırsam kullanmadım dahaca

    public void kill() {
        System.out.println("Yetersiz Altın Ölüm Sebebidir!!!");
        //raund.setIs_alive(false);
        this.setSurvive_state(false);
    }

    public void target_Finding_cost_add_to_current_consume() {
        if (this.getGold() > this.getTarget_finding_cost()) {
            this.setGold(this.getGold() - this.getTarget_finding_cost());
            this.setCurrent_consumed_gold(this.getCurrent_consumed_gold() + this.getTarget_finding_cost());
            //Current_Consumed_Gold_save_to_class(raund, this.getTarget_finding_cost());
        } else {//Yetersiz altın ölüm sebebidir.
            System.out.println("Hedef Tahsili için altın yetersiz");
            kill();
        }

    }

    private void move_cost_add_to_current_consume() {
        if (this.getGold() > this.getMove_cost()) {
            this.setGold(this.getGold() - this.getMove_cost());
            this.setCurrent_consumed_gold(this.getCurrent_consumed_gold() + this.getMove_cost());
            //Current_Consumed_Gold_save_to_class(raund, this.getMove_cost());Bunun erine _is_moved tanımla
        } else {//Yetersiz altın ölüm sebebidir.
            System.out.println("Hareket emtek için altın yetersiz");
            kill();
        }
    }

    public void Update_total_consume() {
        this.setSpent_gold(this.getSpent_gold() + this.getCurrent_consumed_gold());
    }

    public void deneme_degistir(map map) {
        int[][] a = map.getMap();
        int i, j;
        for (i = 0; i < map.getMax_row(); i++) {
            for (j = 0; j < map.getMax_colmn(); j++) {
                a[i][j] = 0;
            }
        }

    }
}
