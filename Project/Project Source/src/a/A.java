/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import Game.*;
import Screens.Mani_Menu;
import file_templates.A_raund;
import file_templates.C_raund;
import file_templates.Raund;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Ertuğrul Demir
 */
public class A {

    public static p_a Player_A;
    public static p_b Player_B;
    public static p_c Player_C;
    public static p_d Player_D;
    public static Setttings default_settings;
    public static Raund All_Raund_saves;
    public static map Harita;
    public static Game_Data game_Data;
    public static JFrame ekran;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creating game attributes
        default_settings = new Setttings();

        All_Raund_saves = new Raund();
        
        System.out.print("Oyunun ismini giriniz: ");
        Scanner sc = new Scanner(System.in);
        String new_game_path = sc.nextLine();
        
        
        System.out.println("Vasyaılan Ayarlar ile devam etmek ister misiniz? E/H");
        String cevap = sc.nextLine();

        if (cevap.equalsIgnoreCase("H")) {
            Ayarlar_degis();
        }
        
        
        // features --> max_r, max_c, gold_Ratio, invgold_ratio, max_gold_value, divider_gold  
        Harita = new map(default_settings.getMax_row(), default_settings.getMax_colmn(), default_settings.getGold_ratio(), default_settings.getInvisible_ratio(), default_settings.getMax_Gold_value(), default_settings.getDivider_gold());
        game_Data = new Game_Data(Harita, All_Raund_saves, default_settings);

        int[][] map_matris = game_Data.getMap().getMap();
        int[][] map_value_matris = game_Data.getMap().getGold_value_map();

        Player_A = new p_a(game_Data);
        Player_B = new p_b(game_Data);
        Player_C = new p_c(game_Data);
        Player_D = new p_d(game_Data);

        Player_D.add_all_other_player(Player_A, Player_B, Player_C);

        Harita.gold_add_to_map();
        Harita.Gold_value_add();
        Harita.yazıdır();
        System.out.println("\n\n");
        Harita.yazdır_gold_value();

 


        ekran = new JFrame();
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mani_Menu v1 = new Mani_Menu();
        v1._Game_Screen_(game_Data, Player_A, Player_B, Player_C, Player_D);
        v1.Icon_add();

        
        ekran.add(v1);

        ekran.setSize(800, 600);

        ekran.setVisible(true);

        Game_Loop();
        Ozet_Tablosu();
        
        String Path = create_Folders(new_game_path);
        Write_to_text(Path, game_Data);
    }

    public static void Ayarlar_degis() {
        
        
        
        try {
        Scanner sc = new Scanner(System.in);

        System.out.print("Haritanın satır sayısı:");
        String max_row_s = sc.nextLine();
        if(!max_row_s.equalsIgnoreCase("")){
            int max_row = Integer.parseInt(max_row_s);
            default_settings.setMax_row(max_row);
        }  
        System.out.print("Haritanın stun sayısı:");
        String max_colmn_s = sc.nextLine();
        if(!max_colmn_s.equalsIgnoreCase("")){
        int max_colmn = Integer.parseInt(max_colmn_s);
        default_settings.setMax_colmn(max_colmn);
        }
        System.out.print("Başlangıç Altını:");
        String tarting_gold_s = sc.nextLine();
        if(! tarting_gold_s.equalsIgnoreCase("")){
        int Starting_gold = Integer.parseInt(tarting_gold_s);
        default_settings.setDef_starting_gold(Starting_gold);
        
        }
        System.out.print("Bir raundda gidilebilecek en fazla kare sayısı:");
        String Max_Move_step_s = sc.nextLine();
           if(! Max_Move_step_s.equalsIgnoreCase("")){
        int Max_Move_step = Integer.parseInt(Max_Move_step_s);
        default_settings.setMax_Move_step(Max_Move_step);
        }
        System.out.print("Altın Oranı: ");
        String GoldRatio_s = sc.nextLine();
         if(! GoldRatio_s.equalsIgnoreCase("")){
        int GoldRatio = Integer.parseInt(GoldRatio_s);
        default_settings.setGold_ratio(GoldRatio);
        }
        System.out.print("Görünmez Altın Oranı:");
        String INV_GoldRatio_s = sc.nextLine();
        if(! INV_GoldRatio_s.equalsIgnoreCase("")){
        int INV_GoldRatio = Integer.parseInt(INV_GoldRatio_s);
        default_settings.setInvisible_ratio(INV_GoldRatio);
        }
        System.out.print("Altın değerlerinin katını girin:");
        String Divider_S = sc.nextLine();
        if(! Divider_S.equalsIgnoreCase("")){
        int Divider = Integer.parseInt(Divider_S);
        default_settings.setDivider_gold(Divider);
        }
        System.out.print("Bir altının sahip olabileceği en yüksek değer: ");
        String Max_gold_value_s = sc.nextLine();
        if(! Max_gold_value_s.equalsIgnoreCase("")){
        int Max_gold_value = Integer.parseInt(Max_gold_value_s);
        default_settings.setMax_Gold_value(Max_gold_value);
        }
        System.out.print("A oyuncusunun hareket bedeli:");
        String move_cost_s = sc.nextLine();
        if(! move_cost_s.equalsIgnoreCase("")){
        int A_move_cost = Integer.parseInt(move_cost_s);
        default_settings.setA_def_move_cost(A_move_cost);
        }
        System.out.print("A oyuncusunun hedef belirleme bedeli:");
        String A_def_target_finding_cost = sc.nextLine();
        if(! A_def_target_finding_cost.equalsIgnoreCase("")){
        int A_target_finding_cost = Integer.parseInt(A_def_target_finding_cost);
        default_settings.setA_def_target_finding_cost(A_target_finding_cost);
        }
        System.out.print("B oyuncusunun hareket bedeli:");
        String B_move_cost_s = sc.nextLine();
        if(! B_move_cost_s.equalsIgnoreCase("")){
        int B_move_cost = Integer.parseInt(B_move_cost_s);
        default_settings.setB_def_move_cost(B_move_cost);
        }
        System.out.print("B oyuncusunun hedef belirleme bedeli:");
        String B_def_target_finding_cost = sc.nextLine();
        if(! B_def_target_finding_cost.equalsIgnoreCase("")){
        int B_target_finding_cost = Integer.parseInt(B_def_target_finding_cost);
        default_settings.setB_def_target_finding_cost(B_target_finding_cost);
        }
        System.out.print("C oyuncusunun hareket bedeli:");
        String C_move_cost_s = sc.nextLine();
        if(! C_move_cost_s.equalsIgnoreCase("")){
        int C_move_cost = Integer.parseInt(C_move_cost_s);
        default_settings.setC_def_move_cost(C_move_cost);
        }
        System.out.print("C oyuncusunun hedef belirleme bedeli:");
        String C_def_target_finding_cost = sc.nextLine();
        if(! C_def_target_finding_cost.equalsIgnoreCase("")){
        int C_target_finding_cost = Integer.parseInt(C_def_target_finding_cost);
        default_settings.setC_def_target_finding_cost(C_target_finding_cost);
        }
        System.out.print("D oyuncusunun hareket bedeli:");
        String D_move_cost_s = sc.nextLine();
         if(! D_move_cost_s.equalsIgnoreCase("")){
         int D_move_cost = Integer.parseInt(D_move_cost_s);
         default_settings.setD_def_move_cost(D_move_cost);
        }
        System.out.print("D oyuncusunun hedef belirleme bedeli:");
        String D_def_target_finding_cost = sc.nextLine();
        if(! D_def_target_finding_cost.equalsIgnoreCase("")){
        int D_target_finding_cost = Integer.parseInt(D_def_target_finding_cost);
            default_settings.setD_def_target_finding_cost(D_target_finding_cost);
        }
        
        
        
        
        } catch (Exception e) {
            System.out.println("Ayarları değiştirirken hata");
        }
        
        
        
    }

    public static void Ozet_Tablosu(){
     A_raund A =game_Data.getRaunds().getKayit_A().getLast();
     A_raund B =game_Data.getRaunds().getKayit_B().getLast();
     C_raund C =game_Data.getRaunds().getKayit_C().getLast();
     A_raund D =game_Data.getRaunds().getKayit_D().getLast();
     
     
        System.out.println("*******************************************************");
        System.out.println("\t\tÖZET Tablosu");
        System.out.println("*******************************************************");
        
        System.out.println("-------------------------------------------------------");
        
        System.out.println("\nA oyuncusu");
        System.out.println("Yapılan Hamle Sayısı: "+A.getRaund());
        System.out.println("Kasadaki Altın: "+A.getGold_in_case());
        System.out.println("Toplamda Harcanan Altın: "+A.getConsume_gold());
        System.out.println("Toplamda kazanılan altınd: "+A.getCaptured_gold());
        
        
        System.out.println("-------------------------------------------------------");
        
        System.out.println("\nB oyuncusu");
        System.out.println("Yapılan Hamle Sayısı: "+B.getRaund());
        System.out.println("Kasadaki Altın: "+B.getGold_in_case());
        System.out.println("Toplamda Harcanan Altın: "+B.getConsume_gold());
        System.out.println("Toplamda kazanılan altınd: "+B.getCaptured_gold());
        
        System.out.println("-------------------------------------------------------");
        
        System.out.println("\nC oyuncusu");
        System.out.println("Yapılan Hamle Sayısı: "+C.getRaund());
        System.out.println("Kasadaki Altın: "+C.getGold_in_case());
        System.out.println("Toplamda Harcanan Altın: "+C.getConsume_gold());
        System.out.println("Toplamda kazanılan altınd: "+C.getCaptured_gold());
        
        System.out.println("-------------------------------------------------------");
        
        System.out.println("\nD oyuncusu");
        System.out.println("Yapılan Hamle Sayısı: "+D.getRaund());
        System.out.println("Kasadaki Altın: "+D.getGold_in_case());
        System.out.println("Toplamda Harcanan Altın: "+D.getConsume_gold());
        System.out.println("Toplamda kazanılan altınd: "+D.getCaptured_gold());
        
        System.out.println("-------------------------------------------------------");

        System.out.println("*******************************************************");
        System.out.println("\t\tOYUN BITTI");
        System.out.println("*******************************************************");
        
    
    }
    public static String create_Folders(String new_game_path) {

        //Proje dosyası üzerine klasör açmak istiyorum
        String project_path = System.getProperty("user.dir");
        //Games klasorü
        File Games_file = new File(project_path + "\\" + "Game_Saves");
        String Games_Path = Games_file.getPath();
        //Oynanan oyunun ismine ile açılıcak klasör
        new_game_path = Games_Path + "\\" + new_game_path;
        File new_game = new File(new_game_path);

        try {
            Games_file.mkdir();
            new_game.mkdir();
        } catch (Exception e) {
        }

        return new_game_path;
    }

    public static void Write_to_text(String path, Game_Data data) {
        //create_txt_files(path);

        LinkedList<A_raund> raunds_A = data.getRaunds().getKayit_A();
        LinkedList<A_raund> raunds_B = data.getRaunds().getKayit_B();
        LinkedList<C_raund> raunds_C = data.getRaunds().getKayit_C();
        LinkedList<A_raund> raunds_D = data.getRaunds().getKayit_D();
        String Yıldız = "*************************************************************************************************************************";
        String Cizgi = "-------------------------------------------------------------------------------------------------------------------------";

        try (FileWriter Save_A = new FileWriter(path + "\\" + "Player_A.txt");
                FileWriter Save_B = new FileWriter(path + "\\" + "Player_B.txt");
                FileWriter Save_C = new FileWriter(path + "\\" + "Player_C.txt");
                FileWriter Save_D = new FileWriter(path + "\\" + "Player_D.txt");) {

            //A için ön hazırlık
            Save_A.write(Yıldız);
            Save_A.write("\n\nPlayer A'nın kayırları aşşağıdaki gibidir:\n\n");
            Save_A.write(Yıldız);
            //B için ön hazırlık
            Save_B.write(Yıldız);
            Save_B.write("\n\nPlayer B'nin kayırları aşşağıdaki gibidir:\n\n");
            Save_B.write(Yıldız);
            //C için ön hazırlık
            Save_C.write(Yıldız);
            Save_C.write("\n\nPlayer C'nin kayırları aşşağıdaki gibidir:\n\n");
            Save_C.write(Yıldız);
            //D için ön hazırlık
            Save_D.write(Yıldız);
            Save_D.write("\n\nPlayer D'nin kayırları aşşağıdaki gibidir:\n\n");
            Save_D.write(Yıldız);

            for (A_raund raund : raunds_A) {
                Save_A.write("\n" + Cizgi + "\n");
                Save_A.write("Raund:" + raund.getRaund() + "\n");

                Save_A.write("\nGerçekleşen olaylar:\n");
                Save_A.write(raund.getAction() + "\n");

                Save_A.write("\nPlayer A'nın son durumu:\n\n");

                Save_A.write("Oyuncunun konumu:" + raund.getNew_Location_row() + "," + raund.getNew_Location_colmn() + "\n");
                Save_A.write("Hedefin konumu: " + raund.getTarget_Row() + "," + raund.getTarget_Colmn() + "\n");

                Save_A.write("Kasadaki altın miktarı: " + raund.getGold_in_case() + "\n");
                Save_A.write("Toplamda ele geçirilen altın mitarı: " + raund.getCaptured_gold() + "\n");
                Save_A.write("Toplamda harcanan altın miktarı: " + raund.getConsume_gold() + "\n");
                Save_A.write("Bu raund içerisinde kazanılan altın: " + raund.getCurrent_captured_gold() + "\n");
                Save_A.write("Bu raund içerisinde harcanan altın: " + raund.getCurrent_Consume_gold() + "\n");

                Save_A.write("\n" + Cizgi + "\n");
            }

            for (A_raund raund : raunds_B) {
                Save_B.write("\n" + Cizgi + "\n");
                Save_B.write("Raund:" + raund.getRaund() + "\n");

                Save_B.write("\nGerçekleşen olaylar:\n");
                Save_B.write(raund.getAction() + "\n");

                Save_B.write("\nPlayer B'nin son durumu:\n\n");

                Save_B.write("Oyuncunun konumu:" + raund.getNew_Location_row() + "," + raund.getNew_Location_colmn() + "\n");
                Save_B.write("Hedefin konumu: " + raund.getTarget_Row() + "," + raund.getTarget_Colmn() + "\n");

                Save_B.write("Kasadaki altın miktarı: " + raund.getGold_in_case() + "\n");
                Save_B.write("Toplamda ele geçirilen altın mitarı: " + raund.getCaptured_gold() + "\n");
                Save_B.write("Toplamda harcanan altın miktarı: " + raund.getConsume_gold() + "\n");
                Save_B.write("Bu raund içerisinde kazanılan altın: " + raund.getCurrent_captured_gold() + "\n");
                Save_B.write("Bu raund içerisinde harcanan altın: " + raund.getCurrent_Consume_gold() + "\n");

                Save_B.write("\n" + Cizgi + "\n");
            }

            for (C_raund raund : raunds_C) {
                Save_C.write("\n" + Cizgi + "\n");
                Save_C.write("Raund:" + raund.getRaund() + "\n");

                Save_C.write("\nGerçekleşen olaylar:\n");
                Save_C.write(raund.getAction() + "\n");

                Save_C.write("\nPlayer C'nin son durumu:\n\n");

                Save_C.write("Oyuncunun konumu:" + raund.getNew_Location_row() + "," + raund.getNew_Location_colmn() + "\n");
                Save_C.write("Hedefin konumu: " + raund.getTarget_Row() + "," + raund.getTarget_Colmn() + "\n");

                Save_C.write("Kasadaki altın miktarı: " + raund.getGold_in_case() + "\n");
                Save_C.write("Toplamda ele geçirilen altın mitarı: " + raund.getCaptured_gold() + "\n");
                Save_C.write("Toplamda harcanan altın miktarı: " + raund.getConsume_gold() + "\n");
                Save_C.write("Bu raund içerisinde kazanılan altın: " + raund.getCurrent_captured_gold() + "\n");
                Save_C.write("Bu raund içerisinde harcanan altın: " + raund.getCurrent_Consume_gold() + "\n");

                Save_C.write("\n" + Cizgi + "\n");
            }

            for (A_raund raund : raunds_D) {
                Save_D.write("\n" + Cizgi + "\n");
                Save_D.write("Raund:" + raund.getRaund() + "\n");

                Save_D.write("\nGerçekleşen olaylar:\n");
                Save_D.write(raund.getAction() + "\n");

                Save_D.write("\nPlayer D'nin son durumu:\n\n");

                Save_D.write("Oyuncunun konumu:" + raund.getNew_Location_row() + "," + raund.getNew_Location_colmn() + "\n");
                Save_D.write("Hedefin konumu: " + raund.getTarget_Row() + "," + raund.getTarget_Colmn() + "\n");

                Save_D.write("Kasadaki altın miktarı: " + raund.getGold_in_case() + "\n");
                Save_D.write("Toplamda ele geçirilen altın mitarı: " + raund.getCaptured_gold() + "\n");
                Save_D.write("Toplamda harcanan altın miktarı: " + raund.getConsume_gold() + "\n");
                Save_D.write("Bu raund içerisinde kazanılan altın: " + raund.getCurrent_captured_gold() + "\n");
                Save_D.write("Bu raund içerisinde harcanan altın: " + raund.getCurrent_Consume_gold() + "\n");

                Save_D.write("\n" + Cizgi + "\n");
            }

            Save_A.write("\n\n" + Yıldız);
            Save_A.write("\nOYUN BİTMIŞTİR.\n");
            Save_A.write(Yıldız);

            Save_B.write("\n\n" + Yıldız);
            Save_B.write("\nOYUN BİTMIŞTİR.\n");
            Save_B.write(Yıldız);

            Save_C.write("\n\n" + Yıldız);
            Save_C.write("\nOYUN BİTMIŞTİR.\n");
            Save_C.write(Yıldız);

            Save_D.write("\n\n" + Yıldız);
            Save_D.write("\nOYUN BİTMIŞTİR.\n");
            Save_D.write(Yıldız);

        } catch (Exception e) {
            System.out.println("Dosyalara yazı yazılamadı...");
        }

    }

    public static void Game_Loop() {
        int Number_of_player = 4;
        
        int Number_of_Gold = game_Data.getMap().getNumber_of_gold();;
        boolean Oyun_devam_ediyor_mu = true;
        int tur = 0;

        int[][] map_matris = game_Data.getMap().getMap();
        int[][] map_value_matris = game_Data.getMap().getGold_value_map();

        while (Oyun_devam_ediyor_mu) {
            

            Number_of_player = 0;
            tur++;
            
            System.out.println("************************************************");
            System.out.println("Raund:"+tur);
            System.out.println("************************************************");
            
            All_Raund_saves.setNumber_of_Raund(tur);
            //if(Player_A.getsurvive_state)
            Scanner a = new Scanner(System.in);
            
            if (Player_A.isSurvive_state()) {
            System.out.println("A'nun ilerlemesi için entera basın:");
            String av = a.nextLine();    
                Player_A.play();
                ekran.repaint();
                Number_of_player++;
            }
            //label.setText("TUR:"+tur);

            
            if (Player_B.isSurvive_state()) {
                System.out.println("B'nin ilerlemesi için entera basın:");
                String aa = a.nextLine();
                Player_B.play();
                ekran.repaint();
                Number_of_player++;
            }

            
            if (Player_C.isSurvive_state()) {
                System.out.println("C'nin ilerlemesi için entera basın:");
                String aaa = a.nextLine();
                Player_C.play();
                ekran.repaint();
                Number_of_player++;
            }

            
            if (Player_D.isSurvive_state()) {
                System.out.println("D'nin ilerlemesi için entera basın:");
                String aaaa = a.nextLine();
                Player_D.play();
                ekran.repaint();
                Number_of_player++;
            }
            int a_a = 0;
            int b_b = 0;
            Number_of_Gold = 0;
            for (a_a = 0; a_a < game_Data.getMap().getMax_row(); a_a++) {
                for (b_b = 0; b_b < game_Data.getMap().getMax_colmn(); b_b++) {
                    if (map_matris[a_a][b_b] == 1 || map_matris[a_a][b_b] == 3) {
                        Number_of_Gold++;
                    }
                }
            }

            Oyun_devam_ediyor_mu = (Number_of_player > 0 && Number_of_Gold > 0);
            System.out.println("------------------------------------------------");
            System.out.println("Kalan Altın Sayısı:" + Number_of_Gold);
            System.out.println("------------------------------------------------");
        }
        System.out.println("Oyun durumu :" + Oyun_devam_ediyor_mu);

    }

}
