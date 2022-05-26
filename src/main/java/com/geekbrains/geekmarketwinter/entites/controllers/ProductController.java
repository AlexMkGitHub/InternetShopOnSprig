package com.geekbrains.geekmarketwinter.entites.controllers;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductDTO;
import com.geekbrains.geekmarketwinter.entites.ProductImage;
import com.geekbrains.geekmarketwinter.services.CategoryService;
import com.geekbrains.geekmarketwinter.services.ProductService;
import com.geekbrains.geekmarketwinter.services.ImageSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequestMapping("/products")
@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private ImageSaverService imageSaverService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setImageSaverService(ImageSaverService imageSaverService) {
        this.imageSaverService = imageSaverService;
    }

//    @PostMapping
//    public String save(@Valid @ModelAttribute("product") Product product, BindingResult binding) {
//        if (binding.hasErrors() || product.getTitle().isEmpty()) {
//            binding.rejectValue("title", "", "Enter all data");
//            return "product_form";
//        }
//        productService.saveProduct(product);
//        return "redirect:/shop-page";
//    }

    @GetMapping("/add")
    public String newForm(Model model) {
        Product addNewProduct = new Product();
        model.addAttribute("addNewProduct", addNewProduct);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product_form";
    }
}
