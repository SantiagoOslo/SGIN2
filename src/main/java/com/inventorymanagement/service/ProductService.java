package com.inventorymanagement.service;

import com.inventorymanagement.model.Product;
import com.inventorymanagement.repository.ProductRepository;
import com.inventory.validation.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        validateProduct(product);  // Validar antes de guardar
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        validateProduct(product);  // Validar antes de actualizar
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    // Método privado para validar los datos del producto
    private void validateProduct(Product product) {
        // Validar nombre
        if (!InputValidator.validateRequiredField(product.getNombre())) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (!InputValidator.validateMaxLength(product.getNombre(), 255)) {
            throw new IllegalArgumentException("La longitud máxima del nombre es de 255 caracteres.");
        }
        if (!InputValidator.validateSpecialCharacters(product.getNombre())) {
            throw new IllegalArgumentException("El nombre contiene caracteres no permitidos.");
        }

        // Validar descripción
        if (product.getDescripcion() != null && !InputValidator.validateMaxLength(product.getDescripcion(), 500)) {
            throw new IllegalArgumentException("La longitud máxima de la descripción es de 500 caracteres.");
        }

        // Validar cantidad
        if (!InputValidator.validateNonNegativeNumber(product.getCantidad())) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        // Validar precio
        if (!InputValidator.validatePriceRange(product.getPrecio(), 1000000)) {
            throw new IllegalArgumentException("El precio debe estar entre 0 y 1,000,000.");
        }
    }
}
