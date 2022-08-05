package web_service.springawsserver.web.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime modifiedTime;

    public PostsListResponseDto(Long id, String title, String author, String content, LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.modifiedTime = modifiedTime;
    }
}
