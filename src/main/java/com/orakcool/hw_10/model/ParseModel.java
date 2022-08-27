package com.orakcool.hw_10.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
public class ParseModel {
    private final String parameter;
    private final HashMap<String, ParseModel> subMap;

    public ParseModel(String parameter, HashMap<String, ParseModel> subMap){
        this.parameter = parameter;
        this.subMap = subMap;
    }

    public ParseModel(String parameter){
        this(parameter, new HashMap<>());
    }

    public ParseModel(HashMap<String, ParseModel> subMap){
        this("", subMap);
    }

    public boolean isParameterPresent(){
        return !parameter.equals("");
    }

    public boolean isSubListPresent(){
        return !subMap.isEmpty();
    }

    public double getDoubleParameter(){
        return Double.parseDouble(getParameter());
    }

    public int getIntParameter(){
        return Integer.parseInt(getParameter());
    }

    public LocalDateTime getDataTimeParameter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(getParameter(), formatter);
    }

    public Manufacturer getManufacturerParameter(){
        return Manufacturer.valueOf(getParameter());
    }

    public OperationSystem getOSParameter() {
        return new OperationSystem(subMap.get("designation").getParameter(), subMap.get("version").getIntParameter());
    }
}
