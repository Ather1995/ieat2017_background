package Entity;

import java.util.ArrayList;
import java.util.List;

import Util.Constant;

/**
 * Created by fanmiaomiao on 2018/3/28.
 */

public class Recipe {
    private String recipeName;
    private String imgName;
    private ArrayList<String> Ingredients;
    private ArrayList<Constant.Nutrtions> Nutrition;
    private int stars;
    private ArrayList<String> Steps;
    public Recipe(String name){
        recipeName = name;
        Ingredients = new ArrayList<>();
        Nutrition = new ArrayList<>();
        Steps = new ArrayList<>();
    }

    public void setImgName(String imgName){
        this.imgName = imgName;
    }

    public void setStars(int stars){
        this.stars = stars;
    }
    public void setIngredients(ArrayList<String> ingredients){
        this.Ingredients=ingredients;
    }

    public void setNutrition(ArrayList<Constant.Nutrtions> nutrition){
        this.Nutrition = nutrition;
    }

    public void setSteps(ArrayList<String> steps){
        this.Steps = steps;
    }

    public List<String> getIngredients(){
        return this.Ingredients;
    }

    public  List<Constant.Nutrtions> getNutrition(){
        return this.Nutrition;
    }

    public String getRecipeName(){
        return this.recipeName;
    }

    public int getStars(){
        return this.stars;
    }

    public String getStep(int pos){
        return Steps.get(pos);
    }

    public String getImgName(){
        return this.imgName;
    }
    @Override
    public String toString(){
        return this.recipeName;
    }
}
