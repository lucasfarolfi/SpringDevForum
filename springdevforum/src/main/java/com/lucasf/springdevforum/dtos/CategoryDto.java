package com.lucasf.springdevforum.dtos;
import com.lucasf.springdevforum.domain.Category;
import com.lucasf.springdevforum.domain.Post;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDto {
    @NotNull private String id;
    @NotNull private String name;
    @NotNull private Date createdAt;
    @NotNull private Date updatedAt;

    public CategoryDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }
}
