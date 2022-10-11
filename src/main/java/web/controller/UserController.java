package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.model.User;

@Controller
@RequestMapping("/")
public class UserController {

    private UserDAO userDAO = new UserDAOImpl();

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String printAllUsers(Model model) {
        model.addAttribute("userList", userDAO.allUsers());

        return "users.html";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userDAO.add(user);
        return "redirect:/";
    }

}
