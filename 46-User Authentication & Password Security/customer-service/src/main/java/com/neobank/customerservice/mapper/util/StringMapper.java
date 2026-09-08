package com.neobank.customerservice.mapper.util;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class StringMapper {

    @Named("capitalize")
    public String capitalize(String value){
        if(value == null || value.isBlank()){
            return value;
        }
        return value.substring(0,1).toUpperCase() +
                value.substring(1).toLowerCase();
    }
}
