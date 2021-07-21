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
public class _OneRaund_ {
    private int raund;
    private int rank;
    //locations   old, new, target

    private int Target_Row;
    private int Target_Colmn;
    
    private int new_Location_row;
    private int new_Location_colmn;
    
    private int Yer_degistirme_kare_sayısı;
    private int total_Yer_degistirme_kare_sayısı=0;
    
    private int Old_Location_row;
    private int Old_Location_colmn;
    
    
    
    //altın
    private int gold_in_case;
    private int captured_gold;
    private int current_captured_gold=0;
    private int consume_gold;
    private int Current_Consume_gold=0;
    
    private boolean _is_alive=true;
    private boolean is_target_found_this_raund=false;//Bu raund bir hedef belirlendi mi?
    private boolean _is_captured_gold_in_this_raound=false;
    private boolean _is_moved_this_raund=false;
    
    private String Action="";

    public _OneRaund_(int raund, int Target_Row, int Target_Colmn, int new_Location_row, int new_Location_colmn, int Yer_degistirme_kare_sayısı, int Old_Location_row, int Old_Location_colmn, boolean _is_alive, boolean _is_captured_gold_in_this_raound, int gold_in_case, int captured_gold, int consume_gold, String Action,int total_Yer_degistirme_kare_sayısı) {
        this.raund = raund;
        this.Target_Row = Target_Row;
        this.Target_Colmn = Target_Colmn;
        this.new_Location_row = new_Location_row;
        this.new_Location_colmn = new_Location_colmn;
        this.Yer_degistirme_kare_sayısı = Yer_degistirme_kare_sayısı;
        this.Old_Location_row = Old_Location_row;
        this.Old_Location_colmn = Old_Location_colmn;
        this._is_alive = _is_alive;
        this._is_captured_gold_in_this_raound = _is_captured_gold_in_this_raound;
        this.gold_in_case = gold_in_case;
        this.captured_gold = captured_gold;
        this.consume_gold = consume_gold;
        this.Action = Action;
        this.total_Yer_degistirme_kare_sayısı=total_Yer_degistirme_kare_sayısı;
    }

    public _OneRaund_() {
    }

    public void yazdır(){    
        String yazı1="Raund:"+raund+"\nTarget row="+ Target_Row+" Target colmn:="+ Target_Colmn;
        String yazı2="\nOld Location row="+Old_Location_row+" colmn="+Old_Location_colmn;
        String yazı3="\nAlınan kare sayısı: "+Yer_degistirme_kare_sayısı;
        String yazı4="\nNew Location row="+new_Location_row+" colmn="+new_Location_colmn;
        String yazı5="\nKasadaki altın= "+gold_in_case+" Toplamda Elegeçirilen altın="+captured_gold+" Toplamda Harcanan Altın= "+consume_gold;
        String yazı6=Action;
        
        System.out.println(yazı1+yazı2+yazı3+yazı4+yazı5+"\n"+yazı6);
    }
    
    
   //Getter & Better
    public int getRaund() {
        return raund;
    }

    public void setRaund(int raund) {
        this.raund = raund;
    }

    public int getTarget_Row() {
        return Target_Row;
    }

    public void setTarget_Row(int Target_Row) {
        this.Target_Row = Target_Row;
    }

    public int getTarget_Colmn() {
        return Target_Colmn;
    }

    public void setTarget_Colmn(int Target_Colmn) {
        this.Target_Colmn = Target_Colmn;
    }

    public int getNew_Location_row() {
        return new_Location_row;
    }

    public void setNew_Location_row(int new_Location_row) {
        this.new_Location_row = new_Location_row;
    }

    public int getNew_Location_colmn() {
        return new_Location_colmn;
    }

    public void setNew_Location_colmn(int new_Location_colmn) {
        this.new_Location_colmn = new_Location_colmn;
    }

    public int getYer_degistirme_kare_sayısı() {
        return Yer_degistirme_kare_sayısı;
    }

    public void setYer_degistirme_kare_sayısı(int Yer_degistirme_kare_sayısı) {
        this.Yer_degistirme_kare_sayısı = Yer_degistirme_kare_sayısı;
    }

    public int getOld_Location_row() {
        return Old_Location_row;
    }

    public void setOld_Location_row(int Old_Location_row) {
        this.Old_Location_row = Old_Location_row;
    }

    public int getOld_Location_colmn() {
        return Old_Location_colmn;
    }

    public void setOld_Location_colmn(int Old_Location_colmn) {
        this.Old_Location_colmn = Old_Location_colmn;
    }

    public boolean isIs_alive() {
        return _is_alive;
    }

    public void setIs_alive(boolean _is_alive) {
        this._is_alive = _is_alive;
    }

    public boolean isIs_captured_gold_in_this_raound() {
        return _is_captured_gold_in_this_raound;
    }

    public void setIs_captured_gold_in_this_raound(boolean _is_captured_gold_in_this_raound) {
        this._is_captured_gold_in_this_raound = _is_captured_gold_in_this_raound;
    }

    public int getGold_in_case() {
        return gold_in_case;
    }

    public void setGold_in_case(int gold_in_case) {
        this.gold_in_case = gold_in_case;
    }

    public int getCaptured_gold() {
        return captured_gold;
    }

    public void setCaptured_gold(int captured_gold) {
        this.captured_gold = captured_gold;
    }

    public int getConsume_gold() {
        return consume_gold;
    }

    public void setConsume_gold(int consume_gold) {
        this.consume_gold = consume_gold;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCurrent_captured_gold() {
        return current_captured_gold;
    }

    public void setCurrent_captured_gold(int current_captured_gold) {
        this.current_captured_gold = current_captured_gold;
    }

    public int getCurrent_Consume_gold() {
        return Current_Consume_gold;
    }

    public void setCurrent_Consume_gold(int Current_Consume_gold) {
        this.Current_Consume_gold = Current_Consume_gold;
    }

    public boolean isIs_target_found_this_raund() {
        return is_target_found_this_raund;
    }

    public void setIs_target_found_this_raund(boolean is_target_found_this_raund) {
        this.is_target_found_this_raund = is_target_found_this_raund;
    }

    public boolean isIs_moved_this_raund() {
        return _is_moved_this_raund;
    }

    public void setIs_moved_this_raund(boolean _is_moved_this_raund) {
        this._is_moved_this_raund = _is_moved_this_raund;
    }

    public int getTotal_Yer_degistirme_kare_sayısı() {
        return total_Yer_degistirme_kare_sayısı;
    }

    public void setTotal_Yer_degistirme_kare_sayısı(int total_Yer_degistirme_kare_sayısı) {
        this.total_Yer_degistirme_kare_sayısı = total_Yer_degistirme_kare_sayısı;
    }


    
    
    
}
