package com.TheRookies.kanteen.Model;

public class Food {
    private String  cuisineimageurl,cuisinename,food;

    public Food() {
    }

    public Food(String cuisineimageurl, String cuisinename, String food) {
        this.cuisineimageurl = cuisineimageurl;
        this.cuisinename = cuisinename;
        this.food = food;
    }

    public String getCuisineimageurl() {
        return cuisineimageurl;
    }

    public void setCuisineimageurl(String cuisineimageurl) {
        this.cuisineimageurl = cuisineimageurl;
    }

    public String getCuisinename() {
        return cuisinename;
    }

    public void setCuisinename(String cuisinename) {
        this.cuisinename = cuisinename;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
