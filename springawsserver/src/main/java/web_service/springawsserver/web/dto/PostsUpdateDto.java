package web_service.springawsserver.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import web_service.springawsserver.domain.entity.Posts;

@Getter
@NoArgsConstructor
public class PostsUpdateDto {
    private String title;
    private String content;

    public PostsUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
