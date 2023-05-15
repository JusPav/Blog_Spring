package lt.code.academy.service.impl;

import lt.code.academy.dto.Comment;
import lt.code.academy.entity.CommentEntity;
import lt.code.academy.entity.PostEntity;
import lt.code.academy.mapper.CommentMapper;
import lt.code.academy.repository.CommentRepository;
import lt.code.academy.repository.PostRepository;
import lt.code.academy.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, Comment commentDto) {
        PostEntity post=postRepository.findByUrl(postUrl).get();
        CommentEntity comment= CommentMapper.maptoComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }
}
