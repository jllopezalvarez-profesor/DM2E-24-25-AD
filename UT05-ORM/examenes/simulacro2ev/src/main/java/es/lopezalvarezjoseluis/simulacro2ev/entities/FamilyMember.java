package es.lopezalvarezjoseluis.simulacro2ev.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer familyMemberId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private int age;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMember that = (FamilyMember) o;
        return Objects.equals(familyMemberId, that.familyMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(familyMemberId);
    }

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
