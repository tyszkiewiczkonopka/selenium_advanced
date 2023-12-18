package models.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
    private String name;
    private BigDecimal price;

}
