package com.wenting.springcloud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable {

    private Long id;

    private String serial;

    public Payment(long l, String serial001) {
        this.id = l;
        this.serial = serial001;
    }
}
