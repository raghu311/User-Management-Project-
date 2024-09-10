package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.LoginFormDTO;
import com.example.demo.dto.QuoteApiResponseDTO;
import com.example.demo.dto.RegisterFormDTO;
import com.example.demo.dto.ResetPwdFormDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.DashBoardService;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DashBoardService dashBoardService;

	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
    Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);

		RegisterFormDTO registerFormDTO = new RegisterFormDTO();
		model.addAttribute("registerForm", registerFormDTO);
		return "register";
	}

	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable Integer countryId) {

		Map<Integer, String> statesMap = userService.getStates(countryId);
		System.out.println(statesMap);
		return statesMap;

	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCitiess(@PathVariable Integer stateId) {

		Map<Integer, String> citiesMap = userService.getCities(stateId);
		return citiesMap;

	}

	@PostMapping("/register")
	public String handleRegisterationPage(RegisterFormDTO registerFormDTO, Model model){
		boolean status = userService.duplicateEmailCheck(registerFormDTO.getEmail());
		if (status) {
			model.addAttribute("emsg", "Duplicate Email Found");
			//model.addAttribute("registerForm", registerFormDTO);

		} else {
			boolean saveUser = userService.saveUser(registerFormDTO);
			if (saveUser) {
				model.addAttribute("smsg", "Registeration sucess , please check your email account::::");
				//model.addAttribute("registerForm",  registerFormDTO);

			} else {
				model.addAttribute("emsg", "Registeration Failed:::");
				
			}
		}
		model.addAttribute("registerForm", new RegisterFormDTO());
		model.addAttribute("countries", userService.getCountries());
		return "register";
	}

	@GetMapping("/")
	public String index(Model model) {
		LoginFormDTO loginFormDTO = new LoginFormDTO();
		model.addAttribute("loginForm", loginFormDTO);
		return "login";

	}

	@PostMapping("/login")
	public String handleUserLogin(LoginFormDTO loginFormDTO, Model model) {

		UserDTO userDTO = userService.login(loginFormDTO);
		if (userDTO == null) {
			model.addAttribute("emsg", "Invailed Credinationals");
			model.addAttribute("loginForm", loginFormDTO);

		} else {
			String pwdUpdated = userDTO.getPwdUpdated();
			if ("Yes".equals(pwdUpdated)) {
				return "redirect:dashboard";
			} else {
				return "redirect:reset-pwd-page?email=" + userDTO.getEmail();
			}
		}
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		QuoteApiResponseDTO quoteApiResponseDTO = dashBoardService.getQuote();
		model.addAttribute("quote", quoteApiResponseDTO);

		return "dashboard";

	}

	@GetMapping("/reset-pwd-page")
	public String loadResetPwdPage(@RequestParam String email, Model model) {
		ResetPwdFormDTO resetPwdFormDTO = new ResetPwdFormDTO();
		resetPwdFormDTO.setEmail(email);
		model.addAttribute("resetPwd", resetPwdFormDTO);
		return "resetPwd";

	}

	@PostMapping("/resetPwd")
	public String handlePwdReset(ResetPwdFormDTO resetPwdFormDTO, Model model) {
		boolean resetPwd = userService.resetPwd(resetPwdFormDTO);
		if (resetPwd = true) {
			return "redirect:dashboard";
		} 
			return "resetPwd";
		
	}
}
