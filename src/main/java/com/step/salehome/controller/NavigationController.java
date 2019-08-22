package com.step.salehome.controller;

import com.step.salehome.constants.URLConstants;
import com.step.salehome.model.AdvancedSearchPost;
import com.step.salehome.model.City;
import com.step.salehome.model.Post;
import com.step.salehome.model.User;
import com.step.salehome.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NavigationController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PostService postService;
    @Autowired
    private List<City> cityList;

    @RequestMapping("/post/{idPost}")
    public String openPostPage(Model model, @PathVariable("idPost") int idPost) {
        Post post = postService.getPostById(idPost);
        model.addAttribute("post", post);
        return URLConstants.PRE;
    }

    @GetMapping("/register")
    public String openRegisterPage(Model model) {
        model.addAttribute("user", new User()).addAttribute("message", model.asMap().get("message"));
        return URLConstants.REGISTER;
    }

    @RequestMapping("/search")
    public String openSearchPage(Model model,
                                 @RequestParam(name = "keywords", required = false) String keywords,
                                 @RequestParam(name = "postType", required = false) String postType,
                                 @RequestParam(required = false, name = "miniPrice") String miniPrice,
                                 @RequestParam(required = false, name = "maxPrice") String maxprice,
                                 @RequestParam(required = false, name = "roomCount") String roomCount,
                                 @RequestParam(required = false, name = "cityName") String cityname,
                                 @RequestParam(required = false, name = "homeType") String homeType,
                                 @RequestParam(required = false, name = "miniArea") String miniArea,
                                 @RequestParam(required = false, name = "maxArea") String maxArea,
                                 @RequestParam(name = "page", required = false) Integer page) {
        AdvancedSearchPost advancedSearchPost = new AdvancedSearchPost();
        advancedSearchPost.setIdCity(cityname);
        advancedSearchPost.setHomeType(homeType);
        advancedSearchPost.setKeywords(keywords);
        advancedSearchPost.setMaxArea(maxArea);
        advancedSearchPost.setMiniArea(miniArea);
        advancedSearchPost.setMaxPrice(maxprice);
        advancedSearchPost.setMiniPrice(miniPrice);
        advancedSearchPost.setRoomCount(roomCount);
        advancedSearchPost.setPostType(postType);
        int postCount = postService.getSearchedPostCount(advancedSearchPost);
        int totalPage = (int) Math.ceil((double) postCount / 15);
        int offset = 0;
        if (page != null && page >= totalPage) offset = postCount - 15;
        else if (page != null && page > 1) offset = (page - 1) * 15;
        model.addAttribute("totalPage", totalPage);
        List<Post> searchPostList = postService.searchPost(advancedSearchPost, offset);
        model.addAttribute("searchPostList", searchPostList).addAttribute("cities", cityList).addAttribute("advancedSearchPost", advancedSearchPost);
        return URLConstants.SEARCH;
    }

    @RequestMapping("/login")
    public String openLoginPage() {
        return URLConstants.LOGIN;
    }
}
