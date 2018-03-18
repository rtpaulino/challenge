package com.skip.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderCancellationVO {

    private Long id;

    @JsonProperty(value = "reason", required = true)
    @NotBlank
    @Length(min = 3, max = 3000)
    private String reason;

    public OrderCancellationVO() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }
}
