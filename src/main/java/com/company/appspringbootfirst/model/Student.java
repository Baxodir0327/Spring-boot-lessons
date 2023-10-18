package com.company.appspringbootfirst.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Student {

    private Integer id;
    private String name;
    private Group group;
}
