package com.example.demo.controllers;

import com.example.demo.models.Kuzov;
import com.example.demo.models.Mashina;
import com.example.demo.repo.MashinaRepository;
import com.example.demo.repo.KuzovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("kuzov")
public class KuzovController {
    @Autowired
    private KuzovRepository kuzovRepository;

    @Autowired
    private MashinaRepository mashinaRepository;

    @GetMapping()
    public String bookMain(Model model) {
        Iterable<Kuzov> kuzovs = kuzovRepository.findAll();
        model.addAttribute("kuzovs", kuzovs);
        return "kuzov/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String bookAdd(Kuzov kuzov, Model model) {
        Iterable<Mashina> authors = mashinaRepository.findAll();
        model.addAttribute("authors", authors);
        return "kuzov/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String bookPostAdd(
            @ModelAttribute("kuzov") @Valid Kuzov kuzov,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Mashina> authors = mashinaRepository.findAll();
            model.addAttribute("authors", authors);
            return "kuzov/add";
        }
        kuzovRepository.save(kuzov);
        return "redirect:";
    }

    @GetMapping("/edit/{kuzov}")
    @PreAuthorize("isAuthenticated()")
    public String bookEdit(Kuzov kuzov, Model model) {
        Iterable<Mashina> authors = mashinaRepository.findAll();
        model.addAttribute("authors", authors);
        return "kuzov/edit";
    }

    @PostMapping("/edit/{kuzov}")
    @PreAuthorize("isAuthenticated()")
    public String bookPostEdit(
            @ModelAttribute("kuzov") @Valid Kuzov kuzov,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Mashina> authors = mashinaRepository.findAll();
            model.addAttribute("authors", authors);
            return "kuzov/edit";
        }
        kuzovRepository.save(kuzov);
        return "redirect:../";
    }

    @GetMapping("/show/{kuzov}")
    public String bookShow(
            Kuzov kuzov) {
        return "kuzov/show";
    }

    @GetMapping("/del/{kuzov}")
    @PreAuthorize("isAuthenticated()")
    public String bookDel(
            Kuzov kuzov) {
        kuzovRepository.delete(kuzov);
        return "redirect:../";
    }
}
