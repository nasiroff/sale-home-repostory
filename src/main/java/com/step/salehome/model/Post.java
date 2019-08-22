package com.step.salehome.model;

import com.step.salehome.util.PostUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Post {
    private int idPost;
    private User user;

    private City city;


//    @NotNull(message = "Adress must not be null")
//    @NotBlank(message = "Adress must not be blank")
    private String address;


    @NotNull(message = "Title must not be null")
    @NotBlank(message = "Title must not be blank")
    @Size(min = 15, max = 150, message = "Title length must be between 30 and 150")
    private String title;


    @NotNull(message = "Description must not be null")
    @NotBlank(message = "Description must not be blank")
    @Size(min = 50, max = 250, message = "Description length must be between 100 and 250")
    private String desc;


    @NotNull(message = "Post must not be null")
    @NotBlank(message = "Post type must not be blank")
    @Pattern(regexp = "rent|sale")
    private String postType;

    @NotNull(message = "Room count must not be null")
    private Integer roomCount;

    @NotNull(message = "Price must not be null")
    private Double price;


    @NotNull(message = "Home type must not be null")
    @NotBlank(message = "Home type must not be blank")
    @Pattern(regexp = "apartment|flat|studio", message = "Home type don't match with current select")
    private String homeType;

    @NotNull(message = "Area must not be null")
    private Double area;

    private String phoneNumber;



    private LocalDateTime shareDate;
    private String status;

    private List<PostImage> postImages;

    private boolean emailAllowed;

    public Post() {
        this.postImages = new ArrayList<>();
    }

    public void addImage(PostImage postImage){
        postImages.add(postImage);
    }

    public String postAge(){
        return PostUtil.ageOf(shareDate);
    }

}
