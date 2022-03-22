package com.koombea.shopifyintegrator.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "parameters")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Parameter {
    @Getter @Setter @Id private Long id;
    @Getter @Setter private LocalDateTime productsRefreshedAt;
}
