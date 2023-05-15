package lt.code.academy.mapper;

import lt.code.academy.dto.Post;
import lt.code.academy.entity.PostEntity;

import java.util.stream.Collectors;

public class PostMapper {
    public static Post mapToPostDto(PostEntity post){
        return Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream().map((comment)->CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
                .build();

    }
    public static PostEntity mapToPost(Post post){
        return PostEntity.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }
}
