package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productRepository) {
        this.productService = productRepository;
    }


    @GetMapping
    public String listPage(@RequestParam("productFilter") Optional<String> productFilter,
                           @RequestParam("costMinFilter") Optional<Long> costMinFilter,
                           @RequestParam("costMaxFilter") Optional<Long> costMaxFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField,
                           Model model) {

        String productFilterValue = productFilter
                .filter(s -> !s.isBlank())
                .orElse(null);

        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(5);

        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
//        System.out.println("ProductController  - sort value for field: " + sortFieldValue );
        model.addAttribute("product", productService.findProductByFilter(
                productFilterValue,
                costMinFilter.orElse(null),
                costMaxFilter.orElse(null),
                pageValue,
                sizeValue,
                sortFieldValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @PostMapping
    public String save(@ModelAttribute("product") ProductDto product, BindingResult binding) {
        if (!(product.getCost() >= 0 && product.getCost() <= 10000000)) {
            binding.rejectValue("cost", "", "Cost not corrected");
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}
