package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }
    
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String loadUser(@PathVariable String userId, Model model){
        model.addAttribute("user", signupRepository.getOne(Long.parseLong(userId)));
        return "user";
    }
    
    @RequestMapping(value = "user/{userId}", method = RequestMethod.POST)
    public String updateUser(@RequestParam String name, @RequestParam String address, @RequestParam String creditCard,
            Model model, @PathVariable String userId){
        Signup user = signupRepository.getOne(Long.parseLong(userId));
        user.setAddress(address);
        user.setName(name);
        user.setCreditCard(creditCard);
        signupRepository.save(user);
        model.addAttribute("user", user);
        return "user";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchUser(@RequestParam String search, Model model){
        model.addAttribute("users", signupRepository.findByName(search));
        return "users";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam String creditCard,
            Model model) {
        Signup user = signupRepository.save(new Signup(name, address, creditCard));
        model.addAttribute("user", user);
        return "done";
    }

}
