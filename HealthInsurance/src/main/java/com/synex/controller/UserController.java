package com.synex.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.Address;
import com.synex.domain.Gender;
import com.synex.domain.Role;
import com.synex.domain.User;
import com.synex.repository.RoleRepository;
import com.synex.repository.UserRepository;
import com.synex.services.UserService;
import com.synex.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@SessionAttributes("user")
public class UserController {
	
	@Autowired UserServiceImpl userService;
	
	@Autowired RoleRepository rRepo;
	
	@Autowired UserRepository uRepo;
	
	@GetMapping("/fetchUser")
	public String fetchUserPage(Principal principal) {
		return "fetchUser";
	}

	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		String message = "";
		if (error != null) {
			message = "Invalid Credentials";
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		model.addAttribute("Message", message);
		return "login";

	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorised access";
		model.addAttribute("Message", message);
		return "accessDeniedPage";

	}

	@RequestMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String firstname,
                         @RequestParam String lastname,
                         @RequestParam String mobile,
                         @RequestParam String address,
                         @RequestParam String address2,
                         @RequestParam LocalDate dob,
                         @RequestParam String gender) {

        // Encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        // Set default role
        Role userRole = rRepo.findByRoleName("USER").get(0);
        
        System.out.println(dob);
        System.out.println(gender);

        // Create user entity
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(hashedPassword);
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setMobileNo(mobile);
        user.setDateBirth(dob);
        
        user.setCreatedDate(LocalDate.now());
        
        if (gender.equals("MALE")) {
        	user.setGender(Gender.MALE);
        } else if (gender.equals("FEMALE")) {
        	user.setGender(Gender.FEMALE);
        } else {
        	user.setGender(Gender.OTHERS);
        }
        	
        
        Address addressObj = new Address();
        addressObj.setAddressLine1(address);
        addressObj.setAddressLine2(address2);
        user.setAddress(addressObj);
        
        // Set roles
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Save user
        userService.save(user);

        // Redirect to login page after successful signup
        return "redirect:/login";
    }
	
	@GetMapping("/register")
	public String register() {
		return "signup";
	}
	
	@GetMapping(value = "/user/{username}")
	@ResponseBody
	public String getUserByUsername(@PathVariable String username) {
		return userService.findByUserName(username).getEmail();
	}
	
	
	


}
