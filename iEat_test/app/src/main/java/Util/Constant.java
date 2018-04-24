package Util;

public class Constant {
    static public String[] Foods ={"food1","food2","food3","food4","food5","food6","food7","food8","food9","food10","food11","food12","food13"};

    private String name;
    private int ID;
    @Override
    public String toString() {
        return "type_"+name;
    }
    public int getID(){
        return ID;
    }
}
