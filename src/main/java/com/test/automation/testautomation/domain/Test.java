package com.test.automation.testautomation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The test description cannot be null.")
    @NotBlank(message = "The test description cannot be blank.")
    private String description;
    @NotNull(message = "The test action cannot be null.")
    @NotBlank(message = "The test action cannot be blank.")
    private String action;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_page_id")
    private Page page;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_webElement_id")
    private WebElement webElement;
}
