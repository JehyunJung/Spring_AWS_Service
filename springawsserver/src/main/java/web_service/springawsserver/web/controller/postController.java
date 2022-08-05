package web_service.springawsserver.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web_service.springawsserver.config.auth.LoginMember;
import web_service.springawsserver.config.auth.dto.SessionMember;
import web_service.springawsserver.domain.service.PostsService;
import web_service.springawsserver.web.dto.PostsListResponseDto;
import web_service.springawsserver.web.dto.PostsResponseDto;
import web_service.springawsserver.web.dto.PostsSaveRequestDto;
import web_service.springawsserver.web.dto.PostsUpdateRequestDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class postController {
    private final PostsService postsService;

    //처음 화면 출력
    @GetMapping("")
    public String allPosts(Model model, @LoginMember SessionMember member) {
        List<PostsListResponseDto> posts = postsService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("memberName", member.getName());
        return "posts/allPosts";
    }

    //상세 글 출력
    @GetMapping("/{postId}")
    public String singlePost(@PathVariable Long postId, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(postId);
        model.addAttribute("post", postsResponseDto);
        return "posts/post";
    }

    //게시글을 등록하는 폼을 반환
    @GetMapping("/save")
    public String saveForm(@ModelAttribute("posts") PostsSaveRequestDto posts, Model model) {
        return "posts/save";
    }

    //게시글을 실제로 등록하는 작업
    @PostMapping("/save")
    public String saveProcess(@ModelAttribute("posts") PostsSaveRequestDto posts, Model model) {
        log.info("posts data -> title:{},author:{},content:{}", posts.getTitle(), posts.getAuthor(), posts.getContent());
        postsService.save(posts);
        return "redirect:/posts";
    }

    //게시글 수정 폼 반환
    @GetMapping("/{postId}/edit")
    public String updateForm(@PathVariable Long postId, Model model){
        PostsResponseDto postsResponseDto = postsService.findById(postId);
        model.addAttribute("post", postsResponseDto);
        return "posts/edit";
    }

    //게시글 수정
    @PostMapping("/{postId}/edit")
    public String updateProcess(@PathVariable Long postId,@ModelAttribute("posts") PostsSaveRequestDto posts, Model model){
        PostsUpdateRequestDto postsUpdateRequestDto = new PostsUpdateRequestDto(posts.getTitle(), posts.getContent());
        PostsResponseDto post = postsService.update(postId, postsUpdateRequestDto);
        model.addAttribute("post", post);
        return "redirect:/posts/{postId}";
    }

    //게시글 삭제
    @GetMapping("/{postId}/delete")
    public String deleteProcess(@PathVariable Long postId){
        postsService.delete(postId);
        return "redirect:/posts";
    }
}
