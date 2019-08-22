package com.step.salehome.controller;

import com.step.salehome.constants.PostConstants;
import com.step.salehome.constants.URLConstants;
import com.step.salehome.model.City;
import com.step.salehome.model.Post;
import com.step.salehome.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;
    @Autowired
    private List<City> cityList;

    @RequestMapping("/")
    public String index(Model model) {
        List<Post> postList = postService.getRecentlyPost();
        model.addAttribute("postList", postList);
        List<Post> randomPostList = postService.getRandomPost();
        Collections.shuffle(randomPostList);
        List<Post> randomSalePost = randomPostList.stream().filter(post -> post.getPostType().equals(PostConstants.POST_TYPE_SALE)).limit(7).collect(Collectors.toList());
        model.addAttribute("randomSalePost", randomSalePost);
        List<Post> randomRentPost = randomPostList.stream().filter(post -> post.getPostType().equals(PostConstants.POST_TYPE_RENT)).limit(7).collect(Collectors.toList());
        model.addAttribute("randomRentPost", randomRentPost).addAttribute("cities", cityList).addAttribute("postCount", postService.getPostCount());
        return URLConstants.HOME;
    }
}
