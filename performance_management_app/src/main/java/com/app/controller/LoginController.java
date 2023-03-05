package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.domain.MST_User;
import com.app.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService service;

    @GetMapping
    //以下の引数が邪魔している気もしたが、削除しても特に何も変わらず
    public String loginGet(@ModelAttribute("user") MST_User user) {
        return "login";
    }

    @PostMapping
    public String loginPost(@ModelAttribute("user") MST_User user, Model model) {

        MST_User loginUser = service.findOne(user.getUser_address());
        // モデルのパスワードと、loginUserのパスワードが一致した場合
        if (loginUser.getPassword().equals(user.getPassword())) {
        } else {
            model.addAttribute("msg", "メールアドレスまたはパスワードが不正です。");
            return "login";
        }
        // ログイン直後のURLがloginのままになってしまうので、redirectにしたほうがよいかも？
        return "redirect:/dashboad";
    }

}
