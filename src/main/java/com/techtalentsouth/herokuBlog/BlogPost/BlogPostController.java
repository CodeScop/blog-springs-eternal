package com.techtalentsouth.herokuBlog.BlogPost;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	// I didn't follow the slides here, but want the original code for reference
	// private static List<BlogPost> posts = new ArrayList<>();
//	private static ArrayList<BlogPost> posts = new ArrayList<>();
	
	/*
	@GetMapping("/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", blogPostRepository.findAll());
		return "blogpost/index";
	}
	
	@GetMapping(value = "/blog_posts/new")
    public String newBlog (BlogPost blogPost) {
		
        return "blogpost/new";
		
    }
	*/
	
	//Alternative
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("blogpost/index");
		mv.addObject("blogPosts", blogPostRepository.findAll());
		return mv;
	}
	
	
	@GetMapping(value = "/blog_posts/new")
    public ModelAndView newPostForm (BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("blogpost/new");
        return mv;
		
    }
	
//	@GetMapping(value = "/blog_posts/edit/{id}")
//    public String editBlog (@PathVariable Long id) {
//		Optional<BlogPost> blogPost = blogPostRepository.findById(id);
//        return "blogpost/edit";
//		
//    }
	
	@GetMapping("/blog_posts/edit/{id}")
	public ModelAndView updatePostForm(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("blogpost/edit");
		Optional<BlogPost> post = blogPostRepository.findById(id);
		mv.addObject("blogPost", post);
		return mv;
	}
	
	@PutMapping("/blog_posts/update")
	public ModelAndView updatePost(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("redirect:/");
		blogPostRepository.save(blogPost);
		return mv;
	}
	@DeleteMapping(value = "/blog_posts/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {

        blogPostRepository.deleteById(id);
        return "redirect:/";

    }
	
	

	// below bit is included in slides, but possibly unnecessary?
	// private BlogPost blogPost;
	
	// initial @PostMapping route overruled by Day 2 version
	/*
	@PostMapping(value = "/")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		// Shortcut (remains to be seen if works):
		blogPostRepository.save(blogPost);
		//Longer version:
		//blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/result";
	}
	*/
	
	/*
	@PostMapping(value = "/blog_posts/result")
	public String create(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
//		posts.add(blogPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/result";
		}
	*/
	
	@PostMapping(value = "/blog_posts/result")
	public ModelAndView createPost(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("blogpost/result");
		BlogPost post = blogPostRepository.save(blogPost);
		mv.addObject("post", post);
		return mv;
		}
	
//	@PutMapping(value = "/blog_posts/result")
//	public String update(BlogPost blogPost, Model model) {
//		blogPostRepository.save(blogPost);
//		model.addAttribute("title", blogPost.getTitle());
//		model.addAttribute("author", blogPost.getAuthor());
//		model.addAttribute("blogEntry", blogPost.getBlogEntry());
//		return "blogpost/result";
//		}
//	

}
