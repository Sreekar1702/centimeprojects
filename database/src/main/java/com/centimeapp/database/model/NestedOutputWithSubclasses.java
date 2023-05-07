package com.centimeapp.database.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NestedOutputWithSubclasses {

    @JsonProperty("Name")
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("Sub Classes")
    private List<NestedOutputWithSubclasses> subclasses;

    public NestedOutputWithSubclasses(String name, List<NestedOutputWithSubclasses> subclasses) {
        this.name = name;
        this.subclasses = subclasses;
    }
}
