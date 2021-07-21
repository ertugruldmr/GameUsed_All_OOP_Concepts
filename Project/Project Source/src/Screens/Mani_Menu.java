/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import Game.Game_Data;
import Game.p_a;
import Game.p_b;
import Game.p_c;
import Game.p_d;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Ertuğrul Demir
 */
public class Mani_Menu extends JPanel {

    BufferedImage Player_A, Player_B, Player_C, Player_D;
    BufferedImage Gold, INV_Gold;
    BufferedImage Target_A, Target_B, Target_C, Target_D;
    Game_Data data;

    p_a P_A;
    p_b P_B;
    p_c P_C;
    p_d P_D;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        int[][] map = this.data.getMap().getMap();
        int[][] map_value = this.data.getMap().getGold_value_map();
        int max_colmn = this.data.getMap().getMax_colmn();
        int max_row = this.data.getMap().getMax_row();
        int i = 0, j = 0;

        int map_start_x=50;
        int map_Start_y=50;
        
        
        int rect_x_size=40;
        int rect_y_size=40;
        int rect_x_size_half=(int)(rect_x_size*0.5);
        int rect_y_size_half=(int)(rect_y_size*0.5);
        
        int taşma=4;
        
        for (i = 0; i < max_row; i++) {
            int start_y=map_Start_y + i * rect_y_size;
            for (j = 0; j < max_colmn; j++) {
                g.setColor(new Color(150, 190, 190));
                int start_x=map_start_x + j * rect_x_size;
                
                
                g.fillRect(start_x, start_y, rect_x_size, rect_y_size);
                g.setColor(Color.BLACK);
                g.drawRect(start_x, start_y, rect_x_size, rect_y_size);

                g.drawString(j + "j", start_x + rect_x_size_half , map_Start_y-rect_y_size_half);

            }
            g.drawString(i + "i", map_start_x-rect_x_size_half, start_y+rect_y_size_half);
        }

        
          if (this.P_A.have_a_target) {
            g.setColor(Color.RED);
            int start_x=map_start_x-taşma + this.P_A.getTarget_colmn() * rect_x_size ;
            int start_y= map_Start_y-taşma + this.P_A.getTarget_row() * rect_y_size;
            int size_x=rect_x_size+2*taşma;
            int size_y=rect_y_size+2*taşma;
            
            g.fillRect(start_x,start_y, size_x, size_y);
        }
        //pb
        if (this.P_B.have_a_target) {
            g.setColor(Color.GREEN);
            int start_x=map_start_x-taşma + this.P_B.getTarget_colmn() * rect_x_size ;
            int start_y= map_Start_y-taşma + this.P_B.getTarget_row() * rect_y_size;
            int size_x=rect_x_size+2*taşma;
            int size_y=rect_y_size+2*taşma;
            
            g.fillRect(start_x,start_y, size_x, size_y);
        }
        //pc
        if (this.P_C.have_a_target) {
            g.setColor(Color.BLUE);
            int start_x=map_start_x-taşma + this.P_C.getTarget_colmn() * rect_x_size ;
            int start_y= map_Start_y-taşma + this.P_C.getTarget_row() * rect_y_size;
            int size_x=rect_x_size+2*taşma;
            int size_y=rect_y_size+2*taşma;
            
            g.fillRect(start_x,start_y, size_x, size_y);
        }
        //pd
        if (this.P_D.have_a_target) {
            g.setColor(Color.orange);
            int start_x=map_start_x-taşma + this.P_D.getTarget_colmn() * rect_x_size ;
            int start_y= map_Start_y-taşma + this.P_D.getTarget_row() * rect_y_size;
            int size_x=rect_x_size+2*taşma;
            int size_y=rect_y_size+2*taşma;
            
            g.fillRect(start_x,start_y, size_x, size_y);
        }
        
        
        for (i = 0; i < max_row; i++) {
            int start_y=map_Start_y + i * rect_y_size;
            for (j = 0; j < max_colmn; j++) {
                int start_x=map_start_x + j * rect_x_size;
                //g.setColor(new Color(150, 190, 190));
                //g.fillRect(130 + j * 40, 100 + i * 40, 40, 40);
                if (map[i][j] == 1) {//Görünür altını bas
                    g.drawImage(Gold, start_x, start_y, null);
                    g.setColor(Color.RED);
                    g.drawString("" + map_value[i][j], start_x+rect_x_size_half, start_y+rect_y_size_half);
                }
                if (map[i][j] == 3) {
                    g.drawImage(INV_Gold, start_x, start_y, null);
                    g.setColor(Color.red);
                    g.drawString("" + map_value[i][j], start_x+rect_x_size_half, start_y+rect_y_size_half);
                }
            }
        }

        if(P_A.isSurvive_state()){
        g.drawImage(Player_A, map_start_x + P_A.getLocation_new_colmn() * rect_x_size, map_Start_y + P_A.getLocation_new_row() * rect_y_size, null);
        }    
        if(P_B.isSurvive_state()){
        g.drawImage(Player_B, map_start_x + P_B.getLocation_new_colmn() * rect_x_size, map_Start_y + P_B.getLocation_new_row() * rect_y_size, null);
        }    
        if(P_C.isSurvive_state()){
        g.drawImage(Player_C, map_start_x + P_C.getLocation_new_colmn() * rect_x_size, map_Start_y + P_C.getLocation_new_row() * rect_y_size, null);
        }    
        if(P_D.isSurvive_state()){
        g.drawImage(Player_D, map_start_x + P_D.getLocation_new_colmn() * rect_x_size, map_Start_y + P_D.getLocation_new_row() * rect_y_size, null);
        }    
        
        
        
        
        
        


        /*
        g.setColor(Color.black);
        for (i = 0; i < max_row; i++) {
            for (j = 0; j < max_colmn; j++) {
                g.drawLine(130 + j * 40, 100, 130 + j * 40, 540);
                g.drawLine(130, 100 + i * 40, 690, 100 + i * 40);
            }
        }
         */
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
        super.repaint(tm, x, y, width, height); //To change body of generated methods, choose Tools | Templates.
    }

    public void _Game_Screen_(Game_Data data, p_a P_A, p_b P_B, p_c P_C, p_d P_D) {
        this.data = data;
        this.P_A = P_A;
        this.P_B = P_B;
        this.P_C = P_C;
        this.P_D = P_D;
        Icon_add();
        repaint();
        setVisible(true);
    }

    public void Icon_add() {
        try {
            Player_A = ImageIO.read(new File("images\\A.png"));
            Player_B = ImageIO.read(new File("images\\B.png"));
            Player_C = ImageIO.read(new File("images\\C.png"));
            Player_D = ImageIO.read(new File("images\\D.png"));

            Gold = ImageIO.read(new File("images\\Gold.png"));
            INV_Gold = ImageIO.read(new File("images\\INV_Gold.png"));
        } catch (Exception e) {
            System.out.println("Iconlar yüklenemedi");
        }

    }
}
