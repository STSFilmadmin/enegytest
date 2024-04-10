package com.sebn.pfm.Energy.Controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class GreetingController {


	@GetMapping("/")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Map<String, Object> model) {
		model.put("name", name);
		return "index";
	}

	

	@GetMapping("/index.html")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@GetMapping("/profile.html")
	public String profile(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "profile";
	}
	@GetMapping("/pages-sign-in.html")
	public String pages_sign_in(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "login";
	}
	@GetMapping("/pages-sign-up.html")
	public String pages_sign_up(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "registration";
	}
	@GetMapping("/ui-buttons.html")
	public String ui_buttons(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "ui-buttons";
	}
	@GetMapping("/ui-forms.html")
	public String ui_forms(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "ui-forms";
	}
	@GetMapping("/ui-cards.html")
	public String ui_cards(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "ui-cards";
	}
	@GetMapping("/ui-typography.html")
	public String ui_typography(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "ui-typography";
	}

	@GetMapping("/icons-feather.html")
	public String icons_feather(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "icons-feather";
	}

	@GetMapping("/registration.html")
	public String registration(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "registration";
	}
	@GetMapping("/charts-chartjs.html")
	public String charts_chartjs(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "charts-chartjs";
	}
	@GetMapping("/pages-blank.html")
	public String pages_blank(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "blank";
	}




}