package com.grsu.tourism.controller.user;

import com.grsu.tourism.model.user.Comment;
import com.grsu.tourism.service.user.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/add")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @GetMapping("/getById")
    public Comment getComment(@RequestParam Integer id) {
        return commentService.getById(id);
    }

    @GetMapping("/getAll")
    public List<Comment> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "rating") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return commentService.getAll(paging);
    }

    @GetMapping("/getByServiceId/{serviceId}")
    public List<Comment> getByServiceId(@PathVariable Integer serviceId) {
        return commentService.getByServiceId(serviceId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getForCurrentUser")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Comment> getForCurrentUser() {
        return commentService.getForCurrentUser();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/delete")
    @ApiOperation(value = "delete", authorizations = {@Authorization(value = "jwtToken")})
    public void delete(@RequestParam Integer id) {
        commentService.delete(id);
    }
}