package com.TheRookies.kanteen.Model;

public class Dish {
    private String dishImageUrl, dishname, cuisinename, dishprice, description,DishId, ratings;

    public Dish() {
    }

    public Dish(String dishImageUrl, String dishname, String cuisinename, String dishprice, String description, String dishId, String ratings) {
        this.dishImageUrl = dishImageUrl;
        this.dishname = dishname;
        this.cuisinename = cuisinename;
        this.dishprice = dishprice;
        this.description = description;
        DishId = dishId;
        this.ratings = ratings;
    }

    public String getDishImageUrl() {
        return dishImageUrl;
    }

    public void setDishImageUrl(String dishImageUrl) {
        this.dishImageUrl = dishImageUrl;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getCuisinename() {
        return cuisinename;
    }

    public void setCuisinename(String cuisinename) {
        this.cuisinename = cuisinename;
    }

    public String getDishprice() {
        return dishprice;
    }

    public void setDishprice(String dishprice) {
        this.dishprice = dishprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDishId() {
        return DishId;
    }

    public void setDishId(String dishId) {
        DishId = dishId;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
