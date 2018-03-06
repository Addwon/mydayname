package com.assignments.mydayname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

@Controller
public class MainController {
    @Autowired
    DateInputRepository dateInputRepository;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("dateinput",dateInputRepository.findAll());
        return "index";
    }

    //Date input
    @GetMapping("/dateform")
    public String showDateForm(Model model){
        model.addAttribute("dateinput",new DateInput());
        return "dateform";
    }
    @PostMapping("/dateform")
    public String postDateForm(@Valid @ModelAttribute("dateinput") DateInput dateInput, BindingResult result){
        if(result.hasErrors()){
            return "dateform";
        }
        else{
            dateInputRepository.save(dateInput);

            return "redirect:/";
        }
    }

    @RequestMapping("/showdate/{id}")
    public String showDay(@PathVariable("id") long id,Model model,DateInput dateInput)
    {
        dateInput=dateInputRepository.findById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String day=sdf.format(dateInput.getIndate());

        switch(day){
            case "Monday":day+=": "+FemaleNames.Adjoa+" "+MaleNames.Kojo;
                break;
            case "Tuesday":day+=": "+FemaleNames.Abena+" "+MaleNames.Kwabena;
                break;
            case "Wednesday":day+=": "+FemaleNames.Akua+" "+MaleNames.Kweku;
                break;
            case "Thursday":day+=": "+FemaleNames.Yaa+" "+MaleNames.Yaw;
                break;
            case "Friday":day+=": "+FemaleNames.Afua+" "+MaleNames.Kofi;
                break;
            case "Saturday":day+=": "+FemaleNames.Ama+" "+MaleNames.Kwame;
                break;
            case "Sunday":day+=": "+FemaleNames.Akosua+" "+MaleNames.Kwesi;
                break;
            default:day="Invalid month";
                break;

        }

        model.addAttribute("day",day);
        //model.addAttribute("dateinput",dateInputRepository.findAll());
        return "showdate";
    }
}


