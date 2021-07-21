/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_templates;

/**
 *
 * @author Ertuğrul Demir
 */
public class C_raund extends _OneRaund_{
    private int Bu_raound_Acılan_Gizli_Altın_Sayısı;
    private int total_acılan_gizli_alltın_sayısı;
    
    private int First_Gold_row;
    private int First_Gold_colmn;
    
    private int Second_Gold_row;
    private int Second_Gold_colmn;

    public C_raund(int Bu_raound_Acılan_Gizli_Altın_Sayısı, int total_acılan_gizli_alltın_sayısı, int First_Gold_row, int First_Gold_colmn, int Second_Gold_row, int Second_Gold_colmn, int raund, int Target_Row, int Target_Colmn, int new_Location_row, int new_Location_colmn, int Yer_degistirme_kare_sayısı, int Old_Location_row, int Old_Location_colmn, boolean _is_alive, boolean _is_captured_gold_in_this_raound, int gold_in_case, int captured_gold, int consume_gold, String Action, int total_Yer_degistirme_kare_sayısı) {
        super(raund, Target_Row, Target_Colmn, new_Location_row, new_Location_colmn, Yer_degistirme_kare_sayısı, Old_Location_row, Old_Location_colmn, _is_alive, _is_captured_gold_in_this_raound, gold_in_case, captured_gold, consume_gold, Action, total_Yer_degistirme_kare_sayısı);
        this.Bu_raound_Acılan_Gizli_Altın_Sayısı = Bu_raound_Acılan_Gizli_Altın_Sayısı;
        this.total_acılan_gizli_alltın_sayısı = total_acılan_gizli_alltın_sayısı;
        this.First_Gold_row = First_Gold_row;
        this.First_Gold_colmn = First_Gold_colmn;
        this.Second_Gold_row = Second_Gold_row;
        this.Second_Gold_colmn = Second_Gold_colmn;
    }
    
    public C_raund() {
    }

    public int getBu_raound_Acılan_Gizli_Altın_Sayısı() {
        return Bu_raound_Acılan_Gizli_Altın_Sayısı;
    }

    public void setBu_raound_Acılan_Gizli_Altın_Sayısı(int Bu_raound_Acılan_Gizli_Altın_Sayısı) {
        this.Bu_raound_Acılan_Gizli_Altın_Sayısı = Bu_raound_Acılan_Gizli_Altın_Sayısı;
    }

    public int getTotal_acılan_gizli_alltın_sayısı() {
        return total_acılan_gizli_alltın_sayısı;
    }

    public void setTotal_acılan_gizli_alltın_sayısı(int total_acılan_gizli_alltın_sayısı) {
        this.total_acılan_gizli_alltın_sayısı = total_acılan_gizli_alltın_sayısı;
    }


    
    public int getFirst_Gold_row() {
        return First_Gold_row;
    }

    public void setFirst_Gold_row(int First_Gold_row) {
        this.First_Gold_row = First_Gold_row;
    }

    public int getFirst_Gold_colmn() {
        return First_Gold_colmn;
    }

    public void setFirst_Gold_colmn(int First_Gold_colmn) {
        this.First_Gold_colmn = First_Gold_colmn;
    }

    public int getSecond_Gold_row() {
        return Second_Gold_row;
    }

    public void setSecond_Gold_row(int Second_Gold_row) {
        this.Second_Gold_row = Second_Gold_row;
    }

    public int getSecond_Gold_colmn() {
        return Second_Gold_colmn;
    }

    public void setSecond_Gold_colmn(int Second_Gold_colmn) {
        this.Second_Gold_colmn = Second_Gold_colmn;
    }
    
    
    
    
    
}
