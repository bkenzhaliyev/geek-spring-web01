package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository1;

import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductRepository1 productRepository;

    @Autowired
    public ProductController(ProductRepository1 productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public String listPage(@RequestParam("productFilter") Optional<String> productFilter,
                           @RequestParam("costMinFilter") Optional<Long> costMinFilter,
                           @RequestParam("costMaxFilter") Optional<Long> costMaxFilter,
                           Model model) {
//        if (productFilter.isEmpty() || productFilter.get().isBlank()) {
//            model.addAttribute("product", productRepository.findAll());
//        } else {
            model.addAttribute("product",
                    productRepository.findWithFilter("%" + productFilter.filter(s -> !s.isBlank()).orElse(null) + "%" ,
                                                     costMinFilter.orElse(null),
                                                     costMaxFilter.orElse(null))
            );
//        }
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

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

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }
}
