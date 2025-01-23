package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateDegreeAndModulesDto {
    private String name;
    private Integer totalHours;

    private List<CreateModuleDto> modules;

    public CreateDegreeAndModulesDto() {
        this.modules = new ArrayList<>();
    }

    public CreateDegreeAndModulesDto(String name, Integer totalHours, List<CreateModuleDto> modules) {
        this.name = name;
        this.totalHours = totalHours;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public List<CreateModuleDto> getModules() {
        return modules;
    }

    public void setModules(List<CreateModuleDto> modules) {
        this.modules = modules;
    }
}
