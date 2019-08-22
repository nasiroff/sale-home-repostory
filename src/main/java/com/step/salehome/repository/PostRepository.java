package com.step.salehome.repository;

import com.step.salehome.model.AdvancedSearchPost;
import com.step.salehome.model.City;
import com.step.salehome.model.Post;
import com.step.salehome.model.User;

import java.util.List;

public interface PostRepository {
    List<Post> searchPost(AdvancedSearchPost advancedSearchPost, int offset);

    void addPost(Post post);

    Post getPostById(int id);

    List<Post> getRecentlyPost();

    List<Post> getRandomPost();

    List<Post> getMyPosts(int id, int offset);

    List<Post> getFavoritePosts(int id);

    void deleteFromFavoritePost(int userId, int postId);

    List<City> getAllCity();

    void deletePost(int id);

    void addToFavorite(int postId, int userId);

    int getPostCount();

    int getSearchedPostCount(AdvancedSearchPost advancedSearchPost);

    int getMyPostCount(int id);
}
