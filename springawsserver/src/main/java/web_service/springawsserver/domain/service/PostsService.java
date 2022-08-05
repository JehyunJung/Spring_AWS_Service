package web_service.springawsserver.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web_service.springawsserver.domain.entity.Posts;
import web_service.springawsserver.domain.repository.PostsRepository;
import web_service.springawsserver.web.dto.PostsListResponseDto;
import web_service.springawsserver.web.dto.PostsResponseDto;
import web_service.springawsserver.web.dto.PostsSaveRequestDto;
import web_service.springawsserver.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public PostsResponseDto update(Long id, PostsUpdateRequestDto updateRequestDto) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        post.update(updateRequestDto.getTitle(), updateRequestDto.getContent());
        return new PostsResponseDto(post);
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        return new PostsResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAll(){
        return postsRepository.findAll()
                .stream()
                .map((post)->{
                    return new PostsListResponseDto(post.getId(),post.getTitle(),post.getAuthor(),post.getContent(),post.getModifiedDate());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        postsRepository.deleteById(id);
    }
}
