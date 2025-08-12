package com.example.demospringboot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Jadwal;
import com.example.demospringboot.repository.JadwalRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class JadwalService implements BaseService<Jadwal, Integer> {
    @Autowired
    private JadwalRepository jadwalRepository;

    // Implementasi metode dari BaseService
    @Override
    public List<Jadwal> findAll() {
        return jadwalRepository.findAll();
    }

    @Override
    public Optional<Jadwal> findById(Integer id) {
        return jadwalRepository.findById(id);
    }

    @Override
    public Jadwal save(Jadwal instructor) {
        return jadwalRepository.save(instructor);
    }

    @Override
    public void deleteById(Integer id) {
        jadwalRepository.deleteById(id);
    }


    public List<Jadwal> getAllJad() {
        return jadwalRepository.findAll();
    }
    public Jadwal getJadById(int id) {
        return jadwalRepository.findById(id).orElse(null);
    }
    public Jadwal addJad(Jadwal jad) {
        return jadwalRepository.save(jad);
    }
    public Jadwal updateJad(int id, Jadwal jad) {
        jad.setId(id);
        return jadwalRepository.save(jad);
    }
    public void deleteJad(int Id) {
        jadwalRepository.deleteById(Id);
    }

    

    public List<Jadwal> getAllActiveJad()
    {
        List<Jadwal> jadwals = jadwalRepository.findAllActive();

        //urutkan dulu dengan sorted() kemudian ditampung ke collector

        return jadwals.stream()
        .sorted((e1, e2) -> e2.getGrade().compareTo(e1.getGrade())) // Urutkan Descending Pemula, Menengah, Lanjutan (p,m,l)
        .collect(Collectors.toList());
    }
    public List<Jadwal> findJadwalByGrade(String grade) {
        return jadwalRepository.findBygrade(grade);
    }

    public void updateInactiveById(int jadwal_id){
        jadwalRepository.updateInactiveById(jadwal_id);
    }
    
}
