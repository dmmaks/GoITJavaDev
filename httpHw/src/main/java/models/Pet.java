package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.PetStatus;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    private Category category;
    private String name;
    Collection<String> photoUrls;
    Collection<Tag> tags;
    PetStatus status;
}
