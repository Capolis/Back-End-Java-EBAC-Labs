package br.com.nameList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nameList.model.PersonName;

@Repository
public interface NameRepository extends JpaRepository<PersonName, Long> {

    // Atualiza todos os registros que possuem o nome antigo para o novo nome
    @Modifying
    @Transactional
    @Query("UPDATE PersonName p SET p.name = :newName WHERE p.name = :oldName")
    int updateAllNames(@Param("oldName") String oldName, @Param("newName") String newName);

    // Deleta todos os registros que dão match com o nome passado
    @Modifying
    @Transactional
    @Query("DELETE FROM PersonName p WHERE p.name = :name")
    int deleteAllByName(@Param("name") String name);
}