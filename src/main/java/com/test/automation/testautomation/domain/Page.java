package com.test.automation.testautomation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Entity
@Data
@NoArgsConstructor
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The page name cannot be null.")
    @NotBlank(message = "The page name cannot be blank.")
    private String name;
    @NotNull(message = "The URL cannot be null.")
    private URL url;
}
