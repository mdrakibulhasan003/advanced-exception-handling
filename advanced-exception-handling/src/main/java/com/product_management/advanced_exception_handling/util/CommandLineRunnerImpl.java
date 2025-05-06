package com.product_management.advanced_exception_handling.util;

import com.product_management.advanced_exception_handling.model.Product;
import com.product_management.advanced_exception_handling.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.validation.Validator;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private ProductService productService;
    private Validator validator;

    public CommandLineRunnerImpl(ProductService productService, Validator validator) {
        this.productService = productService;
        this.validator = validator;
    }


    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Spring boot Exception Handling CLI===");
        System.out.println("1. Get Product By ID");
        System.out.println("2. Create Product");
        System.out.println("3. Exit");

        while (true) {
            System.out.println("Entery your choice (1-3): ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        //Test getting product by id
                        System.out.println("Enter product id: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product product = productService.getProductById(id);
                        System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice());
                        break;

                    case "2":
                        //Test creating product
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        Product newProduct = new Product();
                        newProduct.setName(name);
                        newProduct.setPrice(price);

                        // Validate input

                        var violations = validator.validate(newProduct);
                        if (!violations.isEmpty()) {
                            violations.forEach(v -> System.out.println("Error: " + v.getMessage()));
                            break;
                        }

                        Product createProduct = productService.createProduct(newProduct);
                        System.out.println("Created Product: " + createProduct.getName() + ", Price: " + createProduct.getPrice());
                        break;

                    case "3":
                        //Exit the CLI
                        System.out.println("Exiting... ");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter 1,2 or 3 ");
                }

            }
            catch (NumberFormatException e) {
                System.out.println("Error: Please, enter a valid number.");
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
