package com.assignments.mydayname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.Calendar;

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
            return "redirect:/listdates";
        }
    }
    //List dates
    @RequestMapping("/listdates")
    public String listDates(Model model)
    {
        model.addAttribute("dateinput",dateInputRepository.findAll());
        return "listdates";
    }

    String zodiacSign="";
    @RequestMapping("/showdate/{id}")
    public String showDay(@PathVariable("id") long id,Model model,DateInput dateInput,SimpleDateFormat sdf)
    {
        dateInput=dateInputRepository.findById(id);
        sdf = new SimpleDateFormat("EEEE");
        String day=sdf.format(dateInput.getIndate());
        sdf=new SimpleDateFormat("MMMM");
        String month=sdf.format(dateInput.getIndate());
        String femalename="";
        String malename="";
        String characteristics="";

        switch(day){
            case "Monday":
                femalename=FemaleNames.Adjoa.toString();
                malename=MaleNames.Kojo.toString();
                characteristics="Calm,peaceful";
                break;
            case "Tuesday":
                femalename=FemaleNames.Abena.toString();
                malename=MaleNames.Kwabena.toString();
                characteristics="Warrior, fierce, compassionate";
                break;
            case "Wednesday":
                femalename=FemaleNames.Akua.toString();
                malename=MaleNames.Kweku.toString();
                characteristics=" Advocate, controlling";
                break;
            case "Thursday":
                femalename=FemaleNames.Yaa.toString();
                malename=MaleNames.Yaw.toString();
                characteristics="Confrontational, aggressive";
                break;
            case "Friday":
                femalename=FemaleNames.Afua.toString();
                malename=MaleNames.Kofi.toString();
                characteristics="Adventurous, creative, innovative";
                break;
            case "Saturday":
                femalename=FemaleNames.Ama.toString();
                malename=MaleNames.Kwame.toString();
                characteristics="The ancient wise one.";
                break;
            case "Sunday":
                femalename=FemaleNames.Akosua.toString();
                malename=MaleNames.Kwesi.toString();
                characteristics="Born leader, guide, protector";
                break;
            default:day="Invalid month";
                break;

        }

        model.addAttribute("day",day);
        model.addAttribute("month",month);
        model.addAttribute("femalename",femalename);
        model.addAttribute("malename",malename);
        model.addAttribute("characteristics",characteristics);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        String yearFormat=sdf1.format(dateInput.getIndate());
        model.addAttribute("year",yearFormat);
        int yr=Integer.parseInt(yearFormat);
        int fromYear=yr-4;
        int toYear=(fromYear/12)*12;
        int animalCode=fromYear-toYear;
        String chineseZod="";
        switch(animalCode){
            case 0: chineseZod=ChineseZodiac.Rat.toString();
                break;
            case 1:chineseZod=ChineseZodiac.Ox.toString();
                break;
            case 2:chineseZod=ChineseZodiac.Tiger.toString();
                break;
            case 3:chineseZod=ChineseZodiac.Rabbit.toString();
                break;
            case 4:chineseZod=ChineseZodiac.Dragon.toString();
                break;
            case 5:chineseZod=ChineseZodiac.Snake.toString();
                break;
            case 6:chineseZod=ChineseZodiac.Horse.toString();
                break;
            case 7:chineseZod=ChineseZodiac.Goat.toString();
                break;
            case 8:chineseZod=ChineseZodiac.Monkey.toString();
                break;
            case 9:chineseZod=ChineseZodiac.Rooster.toString();
                break;
            case 10:chineseZod=ChineseZodiac.Dog.toString();
                break;
            case 11:chineseZod=ChineseZodiac.Pig.toString();
                break;
        }
        model.addAttribute("chinesezodiac",chineseZod);

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        String dayFormat=sdf2.format(dateInput.getIndate());

        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        String monthFormat=sdf3.format(dateInput.getIndate());

        model.addAttribute("dayformat",dayFormat);
        model.addAttribute("monthformat",monthFormat);
        int theday=Integer.parseInt(dayFormat);

        switch (Integer.parseInt(monthFormat)) {
            case 1:
                if (theday < 20) {
                    zodiacSign=ZodiacSigns.Capricorn.toString();
                } else {
                    zodiacSign=ZodiacSigns.Capricorn.toString();
                }
                break;
            case 2:
                if (theday < 18) {
                    zodiacSign=ZodiacSigns.Aquarius.toString();
                } else {
                    zodiacSign=ZodiacSigns.Pisces.toString();
                }
                break;
            case 3:
                if (theday < 21) {
                    zodiacSign=ZodiacSigns.Pisces.toString();
                } else {
                    zodiacSign=ZodiacSigns.Aries.toString();
                }
                break;
            case 4:
                if (theday < 20) {
                    zodiacSign=ZodiacSigns.Aries.toString();
                } else {
                    zodiacSign=ZodiacSigns.Taurus.toString();
                }
                break;
            case 5:
                if (theday < 21) {
                    zodiacSign=ZodiacSigns.Taurus.toString();
                } else {
                    zodiacSign=ZodiacSigns.Gemini.toString();
                }
                break;
            case 6:
                if (theday < 21) {
                    zodiacSign=ZodiacSigns.Gemini.toString();
                } else {
                    zodiacSign=ZodiacSigns.Cancer.toString();
                }
                break;
            case 7:
                if (theday < 23) {
                    zodiacSign=ZodiacSigns.Cancer.toString();
                } else {
                    zodiacSign=ZodiacSigns.Leo.toString();
                }
                break;
            case 8:
                if (theday < 23) {
                    zodiacSign=ZodiacSigns.Leo.toString();
                } else {
                    zodiacSign=ZodiacSigns.Virgo.toString();
                }
                break;
            case 9:
                if (theday < 23) { ;
                    zodiacSign=ZodiacSigns.Virgo.toString();
                } else {
                    zodiacSign=ZodiacSigns.Libra.toString();
                }
                break;
            case 10:
                if (theday < 23) {
                    zodiacSign=ZodiacSigns.Libra.toString();
                } else {
                    zodiacSign=ZodiacSigns.Scorpio.toString();
                }
                break;
            case 11:
                if (theday < 22) {
                    zodiacSign=ZodiacSigns.Scorpio.toString();
                } else {
                    zodiacSign=ZodiacSigns.Sagittarius.toString();
                }
                break;
            case 12:
                if (theday < 22) {
                    zodiacSign=ZodiacSigns.Sagittarius.toString();
                } else {
                    zodiacSign=ZodiacSigns.Capricorn.toString();
                }
                break;
        }
        model.addAttribute("zodiacsign",zodiacSign);


        String horourl="http://horoscope-api.herokuapp.com/horoscope/today/"+zodiacSign;
        RestTemplate restTemplate=new RestTemplate();

       HoroscopeToday horoscopeToday=restTemplate.getForObject(horourl, HoroscopeToday.class);
        model.addAttribute("horoscopetoday",horoscopeToday.getHoroscope());
        System.out.println(horoscopeToday.getDate());
String s=horoscopeToday.getDate();
        //Testing horoscope for tomorrow
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c=Calendar.getInstance();
        try { c.setTime(sdf.parse(horoscopeToday.getDate()));
        }
        catch (ParseException e)
        {
            System.out.println("Parse error");
        }
        c.add(Calendar.DATE,1);
        s=sdf.format(c.getTime());
        horoscopeToday.setDate(s);
        System.out.println(s);
        horoscopeToday=restTemplate.getForObject(horourl, HoroscopeToday.class);
        model.addAttribute("horoscopetomorrow",horoscopeToday.getHoroscope());
      //  horoscopeToday.setBdate(horoscopeToday.getBdate());
//        System.out.println(horoscopeToday.getValue().toString());

//-------------------------------------------------------------------------------
       /*SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c=Calendar.getInstance();
        c.setTime(dateInput.getIndate());
        c.add(Calendar.DATE,1);
        dateInput.setIndate(c.getTime());
        //horoscopeToday.setBdate(c.getTime());
        //horoscopeToday=new HoroscopeToday();
        horoscopeToday.setBdate(c.getTime());
        horoscopeToday=restTemplate.getForObject(horourl, HoroscopeToday.class);
        horoscopeToday.setBdate(horoscopeToday.getBdate());*/

//        System.out.println(horoscopeToday.getValue().toString());

        return "showdate";
    }

    @RequestMapping("/showhoroscope")
    public @ResponseBody String showIndex(){
        String horourl="http://horoscope-api.herokuapp.com/horoscope/today/"+zodiacSign;
        RestTemplate restTemplate=new RestTemplate();
        HoroscopeToday horoscopeToday=restTemplate.getForObject(horourl, HoroscopeToday.class);
//        System.out.println(horoscopeToday.getValue().toString());
        //model.addAttribute("horoscopetoday",horoscopeToday.getHoroscope());
        return horoscopeToday.getHoroscope();
    }
}


