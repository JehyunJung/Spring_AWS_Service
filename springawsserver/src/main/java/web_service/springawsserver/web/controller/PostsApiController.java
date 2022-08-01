package web_service.springawsserver.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_service.springawsserver.domain.service.PostsService;
import web_service.springawsserver.web.dto.PostsResponseDto;
import web_service.springawsserver.web.dto.PostsSaveRequestDto;
import web_service.springawsserver.web.dto.PostsUpdateRequestDto;

@RestController
@RequiredArgsConstructor
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateDto) {
        return postsService.update(id, postsUpdateDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto update(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
