package lt.code.academy.service;

import lt.code.academy.dto.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> findAllPosts();
    void createPost(Post postDto);

    Post findPostById(UUID postId);

    void updatePost(Post postDto);

    void deletePost(UUID postId);

    Post findPostByUrl(String postUrl);

    List<Post> searchPosts(String query);
}
