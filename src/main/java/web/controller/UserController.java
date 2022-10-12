package web.controller;

import org.jetbrains.annotations.NotNull;
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

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String printAllUsers(Model model) {
        model.addAttribute("userList", userDAO.allUsers());
        return "users";
    }
    @GetMapping("/{id}")
    public String printOneUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.getById(id));
        return "user";
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
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.edit(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deletePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getById(id));
        return "delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(userDAO.getById(id));
        return "redirect:/";
    }



}
