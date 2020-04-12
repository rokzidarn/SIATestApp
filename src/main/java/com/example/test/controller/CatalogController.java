package com.example.test.controller;

import com.example.test.model.Catalog;
import com.example.test.service.CatalogService;
import com.example.test.validator.CatalogValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogValidator validator;

    @RequestMapping(method = RequestMethod.GET, path = "/catalogs")
    public ModelAndView catalogsAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> all = catalogService.findAllCatalogs();
        modelAndView.addObject("all", all);
        modelAndView.setViewName("catalogs");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/catalogs/{id}")
    public ModelAndView catalogById(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Catalog catalog = catalogService.findCatalogById(id);
        modelAndView.addObject("catalog", catalog);
        modelAndView.setViewName("catalog");

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/catalogs/add")
    public ModelAndView addCatalog(){
        ModelAndView modelAndView = new ModelAndView();
        Catalog catalog = new Catalog();
        modelAndView.addObject("catalog", catalog);
        modelAndView.setViewName("catalog_add");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/catalogs")
    public String processAddCatalog(@Valid Catalog catalog, BindingResult bindingResult) {
        validator.validate(catalog, bindingResult);  // custom validator

        if (bindingResult.hasErrors()) {
            return "catalog_add";
        }

        catalogService.saveCatalog(catalog);
        return "redirect:/catalogs";
    }
}