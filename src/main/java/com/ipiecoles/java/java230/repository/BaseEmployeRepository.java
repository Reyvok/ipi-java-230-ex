package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaseEmployeRepository<T extends Employe> extends PagingAndSortingRepository<T, Long> {

    T findByMatricule(String matricule);

    List<T> findByNomAndPrenom(String nom, String prenom);

    List<T> findByNomIgnoreCase(String nom);

    List<T> findByDateEmbaucheBefore(LocalDate dateEmbauche);

    List<T> findByDateEmbaucheAfter(LocalDate dateEmbauche);

    List<T> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);

    Page<T> findByNomIgnoreCase(String nom, Pageable pageable);

    @Query("select e from #{#entityName} e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)")
    List<T> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);

    @Query(value = "SELECT * FROM Employe WHERE salaire > (SELECT avg(e2.salaire) FROM Employe e2)", nativeQuery = true)
    List<T> findEmployePlusRiches();

}
