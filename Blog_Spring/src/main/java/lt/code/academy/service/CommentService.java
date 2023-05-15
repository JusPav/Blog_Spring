package lt.code.academy.service;

import lt.code.academy.dto.Comment;

public interface CommentService {
    void createComment(String postUrl, Comment commentDto);
}
