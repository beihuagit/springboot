package com.xiaole.dowel.controller;

import com.xiaole.dowel.common.ServerResponse;
import com.xiaole.dowel.pojo.User;
import com.xiaole.dowel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String getAll() {
        List<User> users = userService.getAll();
        return users.toString();
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (null != user) {
            return user.toString();
        } else {
            return "not exists";
        }
    }

    @PostMapping
    public String add(User user) {
        userService.add(user);
        return "nice";
    }

    @PutMapping
    public String update(User user) {
        userService.update(user);
        return "nice";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "nice";
    }

    @PostMapping("login")
    public ServerResponse login(String username, String password) {
        return userService.login(username, password);
    }

}
