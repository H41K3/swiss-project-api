package com.globalwallet.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalwallet.api.model.Card;
import com.globalwallet.api.model.User;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    
    // Busca todos os cartões pertencentes a um usuário específico
    List<Card> findAllByUser(User user);
    
    // Busca um cartão específico pelo ID, garantindo que seja do usuário logado
    Optional<Card> findByIdAndUser(Long id, User user);
}