package ru.itis.springbootdemo.dto;

import lombok.Data;

import javax.xml.soap.SAAJResult;

@Data
public class ServiceForm {
    private String name;
    private String cost;
    private String date;
}