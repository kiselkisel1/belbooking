package com.grsu.tourism.service.user;

import com.grsu.tourism.converter.CommentDtoConverter;
import com.grsu.tourism.dto.CommentDto;
import com.grsu.tourism.model.user.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CommentDtoService {
    private final CommentService commentService;
    private final CommentDtoConverter converter;

    public List<CommentDto> getAll(Pageable pageable) {
        List<Comment> comments = commentService.getAll(pageable);
        List<CommentDto> commentDtos = comments.stream()
                .map(converter::convert)
                .collect(toList());
        return commentDtos;
    }

    public Map<Integer, List<CommentDto>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Comment> comments = commentService.getByServiceIds(serviceIds);
        return comments.stream()
                .map(converter::convert)
                .collect(groupingBy(CommentDto::getServiceId, mapping(row -> row, toList())));
    }

    public CommentDto getById(Integer commentId) {
        Comment comment = commentService.getById(commentId);
        return converter.convert(comment);
    }

    public List<CommentDto> getByServiceId(Integer serviceId) {
        List<Comment> comments = commentService.getByServiceId(serviceId);
        List<CommentDto> commentDtos = comments.stream()
                .map(converter::convert)
                .collect(toList());
        return commentDtos;
    }
}
