package com.orakcool.hw_10.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationSystem {
        String designation;
        int version;

    public OperationSystem(String designation, int version) {
        this.designation = designation;
        this.version = version;
    }

    @Override
    public String toString() {
        return "OperationSystem{" +
                "designation='" + designation + '\'' +
                ", version=" + version +
                '}';
    }
}
