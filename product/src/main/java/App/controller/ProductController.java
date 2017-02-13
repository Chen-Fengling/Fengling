package App.controller;

import App.domain.Product;
import App.form.ProductForm;
import App.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Log logger = LogFactory
            .getLog(ProductController.class);
    @RequestMapping("/product_input")
    public String input(){
        logger.info("InputProductController called");
        return "ProductForm";
    }

    @RequestMapping("/product_save")
    public String save(ProductForm productForm, RedirectAttributes redirectAttributes){
        logger.info("SaveProductController called");
        //no need to create and instantiate a productFrom
        //create a product
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(Float.parseFloat(productForm.getPrice()));

        //add product
        Product savaProduct = productService.add(product);
        redirectAttributes.addFlashAttribute("message","The product is successfully added!");
        return "redirect:/product_view/"+savaProduct.getId();
    }

    @RequestMapping("/product_view/{id}")
    public String viewPrduct(@PathVariable long id, Model model){
        Product product = productService.getId(id);
        model.addAttribute("product",product);
        return "ProductDetails";
    }

}
