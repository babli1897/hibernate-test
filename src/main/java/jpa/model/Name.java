package jpa.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
public class Name {

    @NotNull // doesn't set not null while creating employee table via code
    private String firstName;

    @NotNull
    private String middleName;

    private String lastName;
}
