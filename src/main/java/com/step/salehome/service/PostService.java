package com.step.salehome.service;

import com.step.salehome.model.AdvancedSearchPost;
import com.step.salehome.model.City;
import com.step.salehome.model.Post;

import java.util.List;

public interface PostService {
    List<Post> searchPost(AdvancedSearchPost advancedSearchPost, int offset);

    List<Post> getRecentlyPost();

    void addPost(Post post);

    Post getPostById(int id);

    List<Post> getMyPosts(int id, int offset);

    List<Post> getFavoritePosts(int id);

    void deleteFromFavoritePost(int userId, int postId);

    List<City> getAllCity();

    void deletePost(int id);

    void addToFavorite(int postId, int userId);

    List<Post> getRandomPost();

    int getPostCount();

    int getSearchedPostCount(AdvancedSearchPost advancedSearchPost);

    int getMyPostCount(int id);
}