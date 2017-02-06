/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

/**
 *
 * @author DreadJak
 */
@Controller
public class AdminController {
    
    @Autowired
    private SignupRepository signupRepository;
    
    @RequestMapping(value = "/admin/{userId}", method = RequestMethod.GET)
    public String loadAdmin(@PathVariable String userId){
        Signup user = signupRepository.findOne(Long.parseLong(userId));
        if (user.getIsAdmin()){
            return "admin";
        }
        return "index";
    }
    
    @RequestMapping(value = "/admin/add/{userId}", method = RequestMethod.GET)
    public String addAdmin(@PathVariable String userId, Model model){
        try {
            Signup user = signupRepository.findOne(Long.parseLong(userId));
            user.setIsAdmin(true);
            signupRepository.save(user);
            model.addAttribute("user", user);
            return "redirect:/admin/users";
        }
        catch (Exception ex){
            return "redirect:/form";
        }
    }
    
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String loadUsers(Model model) {
        model.addAttribute("users", signupRepository.findAll());
        return "users";
    }
}
