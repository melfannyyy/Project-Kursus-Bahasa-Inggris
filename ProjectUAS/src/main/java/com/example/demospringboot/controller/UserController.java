package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Member;
import com.example.demospringboot.service.MemberService;
import com.example.demospringboot.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/user")
    public String userPage(Model model){
        @SuppressWarnings("unused")
        List<Member> userList;
        model.addAttribute("userList", memberService.getAllMembers()); 
        return "user/user-list";
    }

    // Menampilkan halaman add, edit user
    @GetMapping("/user/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        //System.out.println("User id(form):"+id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("roles", authentication.getAuthorities().toString());
        model.addAttribute("user", userService.getUserById(id)); 
        model.addAttribute("userDetail", memberService.getMemberById(id));

       
        return "user/user-detail"; 
    }

    @PostMapping("/resetpass")
    public String resetPass(Member user)
    {   
        String hashedpass = user.getEncryptedPass("12345678");

        user.setPassword(hashedpass);
        Member user2 = memberService.getMemberById(user.getId());
        user2.setPassword(hashedpass);
       // System.out.println("member id:"+user2.getId());
        // userService.updateUser(user2.getId(), user2);
        memberService.updateMember(user2.getId(), (Member) user2);
        // user.setPassword(hashedpass);
        // User user2 = userService.getUserById(user.getId());
        // user2.setPassword(hashedpass);
      
        // userService.updateUser(user2.getId(), user2);
        return "user/user-reset-success";
    }
    

    @GetMapping("/updatepass")
    public String showUpdatePass(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
        return "user/user-updatepass";
    }

    @PostMapping("/updatepass")
    public String updatePass(Member user, @RequestParam String newPassword)
    {   
        String hashedpass = user.getEncryptedPass(newPassword);
        user.setPassword(hashedpass);
        Member user2 = memberService.getMemberById(user.getId());
        user2.setPassword(hashedpass);
       // System.out.println("member id:"+user2.getId());
        // userService.updateUser(user2.getId(), user2);
        memberService.updateMember(user2.getId(), (Member) user2);
        return "redirect:/dashboard";
    }
}
