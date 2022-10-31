package com.example.demo.controllers;

import com.example.demo.models.Kuzov;
import com.example.demo.models.Mashina;
import com.example.demo.models.Odejda;
import com.example.demo.repo.MashinaRepository;
import com.example.demo.repo.OdejdaRepository;
import com.example.demo.repo.KuzovRepository;
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
@RequestMapping("odejda")
@PreAuthorize("hasAnyAuthority('USER')")
public class OdejdaController {
    @Autowired
    private OdejdaRepository odejdaRepository;

    @Autowired
    private MashinaRepository mashinaRepository;

    @Autowired
    private KuzovRepository kuzovRepository;

    @GetMapping()
    public String odejdaMain(Model model) {
        Iterable<Odejda> odejdas = odejdaRepository.findAll();
        model.addAttribute("odejdas", odejdas);
        return "odejda/main";
    }

    @GetMapping("/add")
    public String odejdaAdd(Odejda odejda, Model model) {
        Iterable<Mashina> authors = mashinaRepository.findAll();
        Iterable<Kuzov> genders = kuzovRepository.findAll();
        Iterable<Kuzov> kuzovs = kuzovRepository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genders", genders);
        model.addAttribute("kuzovs", kuzovs);
        return "odejda/add";
    }

    @PostMapping("/add")
    public String odejdaPostAdd(
            @ModelAttribute("odejda") @Valid Odejda odejda,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Mashina> authors = mashinaRepository.findAll();
            Iterable<Kuzov> genders = kuzovRepository.findAll();
             Iterable<Kuzov> kuzovs = kuzovRepository.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("genders", genders);
            model.addAttribute("kuzovs", kuzovs);
            return "odejda/add";
        }
        odejdaRepository.save(odejda);
        return "redirect:";
    }

    @GetMapping("/edit/{odejda}")
    public String odejdaEdit(
            Odejda odejda,
            Model model) {
        Iterable<Mashina> authors = mashinaRepository.findAll();
            Iterable<Kuzov> genders = kuzovRepository.findAll();
            Iterable<Kuzov> kuzovs = kuzovRepository.findAll();
             model.addAttribute("kuzovs", kuzovs);
            model.addAttribute("authors", authors);
            model.addAttribute("genders", genders);
        return "odejda/edit";
    }

    @PostMapping("/edit/{odejda}")
    public String odejdaPostEdit(
            @ModelAttribute("odejda") @Valid Odejda odejda,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Mashina> authors = mashinaRepository.findAll();
            Iterable<Kuzov> genres = kuzovRepository.findAll();
            Iterable<Kuzov> kuzovs = kuzovRepository.findAll();
             model.addAttribute("kuzovs", kuzovs);
            model.addAttribute("authors", authors);
            model.addAttribute("genres", genres);

            return "odejda/edit";
        }
        odejdaRepository.save(odejda);
        return "redirect:../";
    }

    @GetMapping("/show/{odejda}")
    public String odejdaShow(
            Odejda odejda,
            Model model) {
        model.addAttribute("release_date", new SimpleDateFormat("yyyy-MM-dd").format(odejda.release_date));
        model.addAttribute("odejda", odejda);
        return "odejda/show";
    }

    @GetMapping("/del/{odejda}")
    public String odejdaDel(
            Odejda odejda) {
        odejdaRepository.delete(odejda);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String odejdaFilter(@RequestParam(defaultValue = "") String title,
                             @RequestParam(required = false) boolean accurate_search,
                             Model model) {
        if (!title.equals("")) {
            List<Odejda> result = accurate_search ? odejdaRepository.findByTitle(title) : odejdaRepository.findByTitleContains(title);
            model.addAttribute("result", result);
        }
        model.addAttribute("title", title);
        model.addAttribute("accurate_search", accurate_search);
        return "odejda/filter";
    }


}
