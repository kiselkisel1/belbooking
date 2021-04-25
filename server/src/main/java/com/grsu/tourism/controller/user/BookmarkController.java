package com.grsu.tourism.controller.user;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.Bookmark;
import com.grsu.tourism.service.user.BookmarkService;
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
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/add/{serviceId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Bookmark addBookmark(@PathVariable Integer serviceId, @RequestParam String type) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(type);

        return bookmarkService.save(serviceId, type);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getByServiceId/{serviceId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Bookmark> getBookmark(@PathVariable Integer serviceId) {
        return bookmarkService.getByServiceId(serviceId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getForCurrentUser")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Bookmark> getBookmarkForCurrentUser() {
        return bookmarkService.getForCurrentUser();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Bookmark> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam(defaultValue = "serviceId") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return bookmarkService.getAll(paging);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/delete")
    @ApiOperation(value = "delete", authorizations = {@Authorization(value = "jwtToken")})
    public void delete(@RequestParam Integer id) {
        bookmarkService.delete(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/deleteForCurrentUser")
    @ApiOperation(value = "delete", authorizations = {@Authorization(value = "jwtToken")})
    public void deleteForCurrentUser() {
        bookmarkService.deleteForCurrentUser();
    }
}
