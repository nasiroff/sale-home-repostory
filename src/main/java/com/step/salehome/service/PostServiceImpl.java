package com.step.salehome.service;

import com.step.salehome.model.AdvancedSearchPost;
import com.step.salehome.model.City;
import com.step.salehome.model.Post;
import com.step.salehome.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> searchPost(AdvancedSearchPost advancedSearchPost, int offset) {
        return postRepository.searchPost(advancedSearchPost, offset);
    }

    @Override
    public List<Post> getRecentlyPost() {
        return postRepository.getRecentlyPost();
    }

    @Override
    public void addPost(Post post) {
        postRepository.addPost(post);
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }

    @Override
    public List<Post> getMyPosts(int id, int offset) {
        return postRepository.getMyPosts(id, offset);
    }

    @Override
    public List<Post> getFavoritePosts(int id) {
        return postRepository.getFavoritePosts(id);
    }

    @Override
    public void deleteFromFavoritePost(int userId, int postId) {
        postRepository.deleteFromFavoritePost(userId, postId);
    }

    @Override
    public List<City> getAllCity() {
        return postRepository.getAllCity();
    }

    @Override
    public void deletePost(int id) {
        postRepository.deletePost(id);
    }

    @Override
    public void addToFavorite(int postId, int userId) {
        postRepository.addToFavorite(postId, userId);
    }

    @Override
    public List<Post> getRandomPost() {
        return postRepository.getRandomPost();
    }

    @Override
    public int getPostCount() {
        return postRepository.getPostCount();
    }

    @Override
    public int getSearchedPostCount(AdvancedSearchPost advancedSearchPost) {
        return postRepository.getSearchedPostCount(advancedSearchPost);
    }

    @Override
    public int getMyPostCount(int id) {
        return postRepository.getMyPostCount(id);
    }
}
