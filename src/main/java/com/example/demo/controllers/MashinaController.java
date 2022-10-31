package com.example.demo.controllers;

import com.example.demo.models.Mashina;
import com.example.demo.repo.MashinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("mashina")
public class MashinaController {

    @Autowired
    private MashinaRepository mashinaRepository;

    @GetMapping()
    public String authorMain(Model model) {
        Iterable<Mashina> mashinas = mashinaRepository.findAll();
        model.addAttribute("mashinas", mashinas);
        return "mashina/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String mashinaAdd(Mashina mashina) {
        return "mashina/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String mashinasPostAdd(
            @ModelAttribute("mashina") @Valid Mashina mashina,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "mashina/add";
        }
        mashinaRepository.save(mashina);
        return "redirect:";
    }

    @GetMapping("/edit/{mashina}")
    @PreAuthorize("isAuthenticated()")
    public String mashinaEdit(
            Mashina mashina,
            Model model) {
        model.addAttribute("birthday", new SimpleDateFormat("yyyy-MM-dd").format(mashina.birthday));
        model.addAttribute("mashina", mashina);
        return "mashina/edit";
    }

    @PostMapping("/edit/{mashina}")
    @PreAuthorize("isAuthenticated()")
    public String mashinaPostEdit(
            @ModelAttribute("mashina") @Valid Mashina mashina,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "mashina/edit";
        }
        mashinaRepository.save(mashina);
        return "redirect:../";
    }

    @GetMapping("/show/{mashina}")
    public String mashinaShow(
            Mashina mashina,
            Model model) {
        model.addAttribute("birthday", new SimpleDateFormat("yyyy-MM-dd").format(mashina.birthday));
        model.addAttribute("mashina", mashina);
        return "mashina/show";
    }

    @GetMapping("/del/{mashina}")
    @PreAuthorize("isAuthenticated()")
    public String mashinaDel(
            Mashina mashina) {
        mashinaRepository.delete(mashina);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String mashinaFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<Mashina> result = accurate_search ? mashinaRepository.findByName(name) : mashinaRepository.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "mashina/filter";
    }


}
