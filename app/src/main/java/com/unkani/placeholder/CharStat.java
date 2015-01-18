package com.unkani.placeholder;

public class CharStat {
    private String Name = "default";
    private int Lvl = 1;
    private int HP = 0;
    private int MP = 0;
    private int Str = 0;
    private int Int = 0;
    private int Dex = 0;
    private int Vit = 0;
    private String Gender = "None";
    private String Element = "None";
    private String Flavor = "None";


    @Override
    public String toString() {
        return "CharStat [Name=" + Name + ", Lvl=" + Lvl + ", HP=" + HP + ", MP=" + MP + ", Str=" + Str + ", Int=" + Int + ", Dex=" + Dex +
                ", Vit=" + Vit + ", Element =" + Element + ", Flavor=" + Flavor + "]";
    }

    public void populateStats(String N, int L, int Health, int Mana, int Strength, int Intelligence, int Dexterity, int Vitality, String Elemental, String FlavorText) {
        Name = N;
        Lvl = L;
        HP = Health;
        MP = Mana;
        Str = Strength;
        Int = Intelligence;
        Dex = Dexterity;
        Vit = Vitality;
        Element = Elemental;
        Flavor = FlavorText;
    }


    public String getName(){
        return Name;
    }
    public int getLvl(){
        return Lvl;
    }
    public int getHP(){
        return HP;
    }
    public int getMP(){
        return MP;
    }
    public int getStr(){
        return Str;
    }
    public int getInt(){
        return Int;
    }
    public int getDex(){
        return Dex;
    }
    public int getVit(){
        return Vit;
    }
    public String getGender(){
        return Gender;
    }
    public String getElement(){
        return Element;
    }
    public String getFlavor(){
        return Flavor;
    }


}