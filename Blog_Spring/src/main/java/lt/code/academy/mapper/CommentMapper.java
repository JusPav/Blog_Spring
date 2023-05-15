package lt.code.academy.mapper;

import lt.code.academy.dto.Comment;
import lt.code.academy.entity.CommentEntity;

public class CommentMapper {
    public static Comment mapToCommentDto(CommentEntity comment){
        return Comment.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }
    public static CommentEntity maptoComment(Comment comment){
        return CommentEntity.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }
}
