package vasyurin.work.bank_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vasyurin.work.bank_rest.entities.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByOwnerId(Long ownerId);

    List<Card> findByStatus(String status);

}
