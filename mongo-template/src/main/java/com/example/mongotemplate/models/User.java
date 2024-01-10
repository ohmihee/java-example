package com.example.mongotemplate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    public String id;
    public String firstName;
    public String lastName;

    @Override
    public String toString() {
        return String.format(
                "User{id: %s, firstName: %s, lastName: %s",
                id, firstName, lastName
        );
    }


}
