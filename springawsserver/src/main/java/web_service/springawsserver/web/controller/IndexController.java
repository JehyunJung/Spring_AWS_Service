package web_service.springawsserver.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    //게시글을 등록하는 폼을 반환
    @GetMapping("/posts/save")
    public String saveForm(@ModelAttribute("posts") PostsSaveRequestDto posts, Model model) {
        return "posts/save";
    }

    //게시글을 실제로 등록하는 작업
    @PostMapping("/posts/save")
    public String saveProcess(@ModelAttribute("posts") PostsSaveRequestDto posts, Model model) {
        log.info("posts data -> title:{},author:{},content:{}", posts.getTitle(), posts.getAuthor(), posts.getContent());
        postsService.save(posts);
        return "redirect:/posts/allPosts";
    }

    //모든 게시글을 출력하는 작업
    @GetMapping("/posts/allPosts")
    public String allPosts(Model model){
        List<PostsListResponseDto> posts = postsService.findAll();
        model.addAttribute("posts", posts);
        return "posts/allPosts";
    }
}
