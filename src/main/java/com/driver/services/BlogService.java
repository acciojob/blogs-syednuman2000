package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    BlogRepository blogRepository1;
    UserRepository userRepository1;

    public Blog createAndReturnBlog(int userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);

        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        user.getBlogList().add(blog);

        userRepository1.save(user);

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        User user = blog.getUser();
        user.getBlogList().remove(blog);
        userRepository1.save(user);
        blogRepository1.delete(blog);
    }
}
