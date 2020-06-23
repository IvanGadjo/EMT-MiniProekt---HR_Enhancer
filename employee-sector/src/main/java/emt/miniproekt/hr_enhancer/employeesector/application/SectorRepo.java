package emt.miniproekt.hr_enhancer.employeesector.application;


import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import emt.miniproekt.hr_enhancer.employeesector.domain.model.Sector;
import emt.miniproekt.hr_enhancer.employeesector.domain.repository.SectorRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectorRepo {

    SectorRepoJPA sectorRepoJPA;

    public SectorRepo(SectorRepoJPA sectorRepoJPA){
        this.sectorRepoJPA = sectorRepoJPA;
    }

    public List<Sector> findAll(){
        return sectorRepoJPA.findAll();
    }

    public Sector findById(int id){
        return sectorRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public Sector saveNewSector(Sector sector){
        return sectorRepoJPA.save(sector);
    }

    public void deleteById(int id){
        sectorRepoJPA.deleteById(id);
    }
}
