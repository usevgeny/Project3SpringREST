package io.evgeny.project3restConsumer.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, max = 30, message = "Should be min 2 symbols max 3O symbols")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
