package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.model.Item;
import com.example.test.service.CategoryService;
import com.example.test.service.ItemService;
import com.example.test.validator.ItemValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired  // bean injection
    private CategoryService categoryService;

    @Autowired
    private ItemValidator validator;

    @RequestMapping(method = RequestMethod.GET, path = "/items")
    public ModelAndView itemsAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Item> all = itemService.findAllItems();
        modelAndView.addObject("all", all);
        modelAndView.setViewName("items");  // Thymeleaf template for frontend

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/items/{id}")
    public ModelAndView itemById(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Item item = itemService.findItemById(id);

        if(item == null){
            modelAndView.setViewName("error");
        }
        else {
            modelAndView.addObject("item", item);
            modelAndView.setViewName("item");
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/items/find")
    public ModelAndView itemByPriceIsLessThan(@RequestParam int price){
        ModelAndView modelAndView = new ModelAndView();
        List<Item> all = itemService.findItemByPriceIsLessThan(price);
        modelAndView.addObject("all", all);
        modelAndView.setViewName("items");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add/item")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        Item item = new Item();
        modelAndView.addObject("item", item);
        List<Category> categories = categoryService.findAllCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("item_add");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add/item")
    public String processAdd(@Valid Item item, BindingResult bindingResult, SessionStatus sessionStatus) {
        validator.validate(item, bindingResult);

        if (bindingResult.hasErrors()) {
            return "item_add";
        }

        itemService.saveItem(item);
        sessionStatus.setComplete();
        return "redirect:/items";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/item/{id}")
    public String processDelete(@PathVariable int id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }
}
