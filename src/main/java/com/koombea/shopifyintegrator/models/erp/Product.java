package com.koombea.shopifyintegrator.models.erp;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private String vendor;
    @Getter @Setter private String productType;
    @Getter @Setter private Integer quantity;
    @Getter @Setter private LocalDateTime createdAt;
    @Getter @Setter private LocalDateTime updatedAt;    
    
}
