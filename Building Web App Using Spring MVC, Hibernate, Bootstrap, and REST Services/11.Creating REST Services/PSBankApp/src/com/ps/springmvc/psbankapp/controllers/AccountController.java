package com.ps.springmvc.psbankapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.springmvc.psbankapp.model.Account;
import com.ps.springmvc.psbankapp.services.AccountService;


@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	
	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String viewAccount(Model model)
	{
		return "findAccount";
	}
		
	@RequestMapping("/new")
	public String newAccount(Model model) {
		model.addAttribute("account", new Account());
		return "account-form";
	}
	
	@RequestMapping("/showAccount")
	public String showAccount() {
		return "showAccount";
	}
	
	@RequestMapping(value="/saveAccount",method=RequestMethod.POST)
	public String saveAccount( @Valid @ModelAttribute("account") Account account,
			BindingResult result,Model model
			) {
		/*String acNo = request.getParameter("accountNo");
		String customerName = request.getParameter("accountHolderName");
		String balance = request.getParameter("balance"); */
		
		/*model.addAttribute("accountNumber", acNo);
		model.addAttribute("accountHolderName", customerName);
		model.addAttribute("balance", balance); */
		
		/*model.addAttribute("account",account);
		return "showAccount";*/
		
		/*if( result.hasErrors())
			return "account-form";
		else {
			accountService.saveAccount(account);
			return "redirect:/list";
		}*/
		
		if (result.hasErrors())
			return "account-form";
		else {
			String message = "";
			boolean flag =true;
			try {
			flag= accountService.saveAccount(account);
			}
			catch(Exception ex) {
				message = ex.getMessage();
				flag=false;
			}
			if(!flag) {
				model.addAttribute("message", message);
				return "account-form";
			}
			model.addAttribute("account", account);
			return "redirect:/list";
		}
		
	}
	
	@GetMapping("/list")
	public String listAccounts(Model model) {
		List<Account> accounts = accountService.getAccounts();
		model.addAttribute("accounts", accounts);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in user name
		model.addAttribute("username", name);
		return "listAccounts";
	}
	
	
	@GetMapping("/edit")
	public String updateAccount(@RequestParam("accountNo") int accountNo, Model model) {
		Account account = accountService.getAccount(new Integer(accountNo));
		model.addAttribute("account", account);
		return "account-form";
	}
	
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam("accountNo") int accountNo, Model model) {
		accountService.deleteAccount(accountNo);
		return "redirect:/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Account getAccount(@PathVariable("id") Integer accountNo) {
		System.out.println("Requested Account Number :" + accountNo);
		Account account = accountService.getAccount(accountNo);
		return account;
	}
	
		
	
}
