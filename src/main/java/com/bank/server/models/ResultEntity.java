package com.bank.server.models;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity <T> {
    private Boolean succes;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T content;

    
    public ResultEntity(Boolean succes) {
        this.succes = succes;
    }

}
