package com.company.appspringbootfirst.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Group {
    private Integer id;
    private String name;
    private Faculty faculty;
}
