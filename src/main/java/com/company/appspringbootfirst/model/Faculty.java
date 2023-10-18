package com.company.appspringbootfirst.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Faculty {
    private Integer id;
    private String name;
    private University university;
}
