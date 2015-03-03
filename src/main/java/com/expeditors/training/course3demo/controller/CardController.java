package com.expeditors.training.course3demo.controller;
 
import javax.validation.Valid;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
 

import com.expeditors.training.course3demo.dto.FindCardCriteria;
import com.expeditors.training.course3demo.model.Card;
import com.expeditors.training.course3demo.service.CardService;
 
@Controller
@RequestMapping("/card/")
public class CardController {
    @Autowired
    CardService cardService;
 
    @RequestMapping(value="find.html", method=RequestMethod.GET)
    public String showFindCard(Model m) {
    	m.addAttribute("criteria", new FindCardCriteria() );
    	return "findCard";
    }
    
    @RequestMapping(value="find.html", method=RequestMethod.POST)
    public String findCard(
    		@ModelAttribute("criteria") @Valid FindCardCriteria finder,
    		BindingResult result,
    		Model m) {
    	m.addAttribute("cards", cardService.findCards(finder));
    	return "findCardResults";
    }
    
    @RequestMapping(method=RequestMethod.GET,value="confirm.html")
    public ModelAndView confirmCard(@RequestParam(value="id",required=true) Long id) {
        ModelAndView mav = new ModelAndView();
         mav.setViewName("confirmCard");
         mav.addObject("card", cardService.find(id));
        return mav;
    }
    @RequestMapping(method=RequestMethod.GET,value="register.html")
    public ModelAndView showRegisterCard(@RequestParam(value="id",required=false) Long id) {
        ModelAndView mav = new ModelAndView();
        Card c= new Card();
        mav.addObject("card", c);
        mav.setViewName("registerCard");
        return mav;
    }
 
    @RequestMapping(method=RequestMethod.POST,value="register.html")
    public ModelAndView registerCard(@ModelAttribute("card") @Valid Card card, BindingResult result, Model m) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()) {
            mav.addObject("card", card);
            mav.setViewName("registerCard");
        } else {
            Long id = cardService.add(card);
            mav.setView(new RedirectView("confirm.html"));
            mav.addObject("id", id);
        }
        return mav;
    }
 
}