package Util;

import Entity.Food;

/**
 * Created by fanmiaomiao on 2018/3/27.
 */

public class Constant {
    static public String[] Foods ={"type_staple","type_soup","type_seafood","type_fruit_vegetable","type_breakfast","type_western","type_dessert"};
    public enum Nutrtions{
        VEGETABLES("vegetables",1),EGG("egg",2),OIL("oil",3),MILK("milk",4),MEAT("meats",5),FISH("fish",6),GRAIN("grain",7);

        private String name;
        private int ID;
        Nutrtions(String na,int id){
            this.name=na;
            this.ID = id;
        }
        @Override
        public String toString() {
            return "type_"+name;
        }
        public int getID(){
            return ID;
        }
    }
}
