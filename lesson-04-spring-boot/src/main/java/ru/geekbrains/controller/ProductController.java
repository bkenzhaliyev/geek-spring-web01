package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("product", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

//    @GetMapping("/{id}")
//    public String delete(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("product", productRepository.delete(id));
//        return "product";
//    }
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(Product product, BindingResult binding) {
        if (!(product.getCost() >= 0 && product.getCost() <= 100000)) {
            binding.rejectValue("cost", "", "Cost not corrected");
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @DeleteMapping( "/{id}")
    public String delete(@PathVariable Long id){
       productRepository.delete(id);
        return "redirect:/product";
    }
}
