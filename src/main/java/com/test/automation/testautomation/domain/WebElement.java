package com.test.automation.testautomation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class WebElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull(message = "The webElement name cannot be null.")
    @NotBlank(message = "The webElement name cannot be blank.")
    private String name;
    @NotNull(message = "The webElement type cannot be null.")
    @NotBlank(message = "The webElement type cannot be blank.")
    private String selectBy;
    @NotNull(message = "The webElement path cannot be null.")
    @NotBlank(message = "The webElement path cannot be blank.")
    private String selector;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_page_id")
    private Page page;
}
