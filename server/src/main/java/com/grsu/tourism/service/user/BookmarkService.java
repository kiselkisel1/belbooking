package com.grsu.tourism.service.user;

import com.grsu.tourism.model.Bookmark;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import com.grsu.tourism.repository.BookmarkRepository;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ValidateUtil validateService;
    private final UserService userService;

    public Bookmark save(Integer serviceId) {
        validateService.isServiceExists(serviceId);
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userDto.getId());
        bookmark.setServiceId(serviceId);
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getByServiceId(Integer serviceId) {
        validateService.isServiceExists(serviceId);
        return bookmarkRepository.findByServiceId(serviceId);
    }

    public List<Bookmark> getForCurrentUser() {
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        return bookmarkRepository.findByUserId(userDto.getId());
    }

    public List<Bookmark> getAll(Pageable pageable) {
        return bookmarkRepository.findAll(pageable).getContent();
    }

    public void delete(Integer id) {
        bookmarkRepository.deleteById(id);
    }

    public void deleteForCurrentUser() {
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        bookmarkRepository.deleteByUserId(userDto.getId());
    }
}
