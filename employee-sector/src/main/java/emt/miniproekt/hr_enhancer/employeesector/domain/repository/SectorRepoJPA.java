package emt.miniproekt.hr_enhancer.employeesector.domain.repository;

import emt.miniproekt.hr_enhancer.employeesector.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepoJPA extends JpaRepository<Sector, Integer> {
}
