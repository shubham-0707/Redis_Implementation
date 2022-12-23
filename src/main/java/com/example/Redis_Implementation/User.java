package com.example.Redis_Implementation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    // This is the main User class file in which the attributes of the user are defined...
    private int id;
    private String name;
    private String country;
    private int age;

}
