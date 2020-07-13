package com.brainacad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Nuser {
    private String name;
    private String job;
    private String id;
    private String updatedAt;
   // private List<Book> books;
}
