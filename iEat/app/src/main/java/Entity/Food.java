package Entity;

/**
 * Created by lenovo on 2018/3/20.
 */

public class Food {

    private int imageId;
    private String name;
    private int itemId;

    public Food(String name, int imageId, int itemId){
        this.imageId = imageId;
        this.itemId = itemId;
        this.name = name;
    }

    public int getImageId(){
        return imageId;
    }
    public int getItemId(){
        return itemId;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name;
    }
}
