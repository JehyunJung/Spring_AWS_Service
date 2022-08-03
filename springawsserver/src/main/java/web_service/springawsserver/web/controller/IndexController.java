package web_service.springawsserver.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web_service.springawsserver.domain.entity.Posts;
import web_service.springawsserver.domain.service.PostsService;
import web_service.springawsserver.web.dto.PostsListResponseDto;
import web_service.springawsserver.web.dto.PostsSaveRequestDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j

public class IndexController {
    private final PostsService postsService;

    //처음 화면 출력
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
