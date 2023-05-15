package lt.code.academy.controller;

import lt.code.academy.dto.Post;
import lt.code.academy.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<Post> posts=postService.findAllPosts();
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model){
        Post postDto=new Post();
        model.addAttribute("post",postDto);
        return "/admin/create_post";
    }
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") Post postDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return "admin/create_post";
        }
            postDto.setUrl(getUrl(postDto.getTitle()));
            postService.createPost(postDto);
            return "redirect:/admin/posts";
    }
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") UUID postId,
                               Model model){
    Post postDto=postService.findPostById(postId);
    model.addAttribute("post",postDto);
    return "admin/edit_post";
    }
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") UUID postId,
                            @Valid @ModelAttribute("post") Post post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post",post);
            return "admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";


    }
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") UUID postId){
    postService.deletePost(postId);
    return "redirect:/admin/posts";
    }
    @GetMapping("admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
                           Model model){
        Post postDto = postService.findPostByUrl(postUrl);

        model.addAttribute("post", postDto);
        return "admin/view_post";

    }
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<Post> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }
    private static String getUrl(String postTitle){
        String title=postTitle.trim().toLowerCase();
        String url=title.replaceAll("\\s+","-");
        url=url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }
}
