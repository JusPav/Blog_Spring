package lt.code.academy.service.impl;

import lt.code.academy.controller.PostController;
import lt.code.academy.dto.Post;
import lt.code.academy.entity.PostEntity;
import lt.code.academy.mapper.PostMapper;
import lt.code.academy.repository.PostRepository;
import lt.code.academy.service.PostService;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;

    private PostController postController;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllPosts() {

        List<PostEntity> posts=postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(Post postDto) {
        PostEntity post=PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public Post findPostById(UUID postId) {
       PostEntity post=postRepository.findById(postId).get();
       return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(Post postDto) {
        postDto.setUrl(getUrl(postDto.getTitle()));
        PostEntity post=PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    private static String getUrl(String postTitle){
        String title=postTitle.trim().toLowerCase();
        String url=title.replaceAll("\\s+","-");
        url=url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }
    @Override
    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post findPostByUrl(String postUrl) {
        PostEntity post=postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<Post> searchPosts(String query) {
        List<PostEntity> posts = postRepository.searchPosts(query);
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }
}
