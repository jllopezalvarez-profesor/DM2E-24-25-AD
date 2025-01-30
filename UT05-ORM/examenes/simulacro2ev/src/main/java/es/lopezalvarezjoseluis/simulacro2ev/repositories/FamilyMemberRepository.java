package es.lopezalvarezjoseluis.simulacro2ev.repositories;

import es.lopezalvarezjoseluis.simulacro2ev.entities.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Integer> {
}
