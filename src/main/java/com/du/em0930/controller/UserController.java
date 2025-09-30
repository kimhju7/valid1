package com.du.em0930.controller;

import com.du.em0930.dto.UserRequest;
import com.du.em0930.entity.MyUser;
import com.du.em0930.repository.MyUserRepository;
import com.du.em0930.util.PasswordUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final MyUserRepository myUserRepository;


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("userRequest") UserRequest userRequest,
                                BindingResult bindingResult,
                                Model model) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        // 실제 저장 로직(예 : DB 저장)
//        MyUser myUser = userRequest.toEntity();
//        myUserRepository.save(myUser);

        // 비밀번호 암호화
        String hashedPassword = PasswordUtil.hashPassword(userRequest.getPassword());
        //Entity로 변환 후 저장
        MyUser myUser = userRequest.toEntity(hashedPassword);
        myUserRepository.save(myUser);


        model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
        return "signup";
    }



}
