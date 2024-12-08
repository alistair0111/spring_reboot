package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank
    @Size(min = 3, max = 50,message = "Product name must be atleast 3 characters")
    private String productName;
    @NotBlank
    @Size(min = 6, max = 50,message = "Product description must be atleast 6 characters")
    private String description;
    private Integer quantity;
    private String image;
    private double price;
    private double specialPrice;
    private double discount;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
