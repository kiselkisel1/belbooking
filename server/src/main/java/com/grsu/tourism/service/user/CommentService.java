package com.grsu.tourism.service.user;

import com.grsu.tourism.model.user.Comment;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import com.grsu.tourism.repository.CommentRepository;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ValidateUtil validateService;
    private final UserService userService;

    public List<Comment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable).getContent();
    }

    public Comment getById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with id is not found " + commentId));
    }

    public List<Comment> getByServiceId(Integer serviceId) {
        validateService.isServiceExists(serviceId);
        return commentRepository.findByServiceId(serviceId);
    }

    public List<Comment> getForCurrentUser() {
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        return commentRepository.findByUserId(userDto.getId());
    }

    public Comment save(Comment comment) {
        validateService.isServiceExists(comment.getServiceId());
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        comment.setUserId(userDto.getId());
        comment.setCommentDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }
}