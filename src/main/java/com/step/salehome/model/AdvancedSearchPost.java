package com.step.salehome.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvancedSearchPost {


    private String idCity;

    private String address;

    private String keywords;

    private String postType;

    private String roomCount;

    private String maxPrice;

    private String miniPrice;

    private String homeType;

    private String maxArea;

    private String miniArea;


    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        if(idCity == null || idCity.trim().isEmpty()){
            this.idCity = null;
        }
        else {this.idCity = idCity;}
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            this.address = null;
        }

        else{
            this.address = address;
        }
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        if(keywords == null || keywords.trim().isEmpty()) {
            this.keywords = null;
        }
        else{
            this.keywords = keywords;
        }
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        if(postType == null || postType.trim().isEmpty()){
            this.postType = null;
        }else {
            this.postType = postType;
        }
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        if(roomCount == null || roomCount.trim().isEmpty()){
            this.roomCount = null;
        }else {
            this.roomCount = roomCount;
        }
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        if(maxPrice == null || maxPrice.trim().isEmpty()){
            this.roomCount = null;
        }else {
            this.maxPrice = maxPrice;
        }
    }

    public String getMiniPrice() {
        return miniPrice;
    }

    public void setMiniPrice(String miniPrice) {
        if(miniPrice == null || miniPrice.trim().isEmpty()){
            this.miniPrice = null;
        }else {
            this.miniPrice = miniPrice;
        }
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        if(homeType == null || homeType.trim().isEmpty()){
            this.homeType = null;
        }else{
            this.homeType = homeType;

        }
    }

    public String getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(String maxArea) {
        if (maxArea == null || maxArea.trim().isEmpty()){
            this.maxArea = null;
        }else{
            this.maxArea = maxArea;

        }
    }

    public String getMiniArea() {
        return miniArea;
    }

    public void setMiniArea(String miniArea) {
        if(miniArea == null || miniArea.trim().isEmpty()){
            this.miniArea = null;
        }else{
            this.miniArea = miniArea;

        }
    }

    public boolean isAllFieldsNull() {
        boolean condition = true;
        List<Object> postLists = Arrays.asList(idCity, address, keywords, postType, roomCount, maxPrice, miniPrice, homeType, maxArea, miniArea);
        condition = !postLists.stream().anyMatch(o -> o!=null );

        return condition;
    }



}
