package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CharacterRepository extends JpaRepository<Character,String> {

    Character findByIdAndDateOffIsNull(String id);

    Set<Character> findByDateOffIsNull();

    Set<Character> findByNameAndDateOffIsNull(String name);

    Set<Character> findByAgeAndDateOffIsNull(Integer age);

}
