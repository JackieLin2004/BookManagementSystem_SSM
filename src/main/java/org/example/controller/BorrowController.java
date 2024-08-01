package org.example.controller;

import jakarta.annotation.Resource;
import org.example.service.BookService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

    @Resource
    BookService service;

    @Resource
    UserService userService;

    @GetMapping({"/borrow", "/"})
    public String borrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("borrow_list", service.getBorrowList());
        model.addAttribute("book_count", service.getBookList().size());
        model.addAttribute("student_count", userService.getStudentList().size());
        return "borrow";
    }
}
