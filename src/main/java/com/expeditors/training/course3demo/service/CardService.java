package com.expeditors.training.course3demo.service;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 


import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 




import com.expeditors.training.course3demo.dto.FindCardCriteria;
import com.expeditors.training.course3demo.model.Card;
//import com.expeditors.training.course3demo.model.PhoneNumber;
import com.expeditors.training.course3demo.model.PhoneNumber;
 
@Repository
@Transactional(readOnly=true)
public class CardService {
	
    @PersistenceContext
    private EntityManager entityManager;
 
    public Card find(Long id) {
        return entityManager.find(Card.class, id);
    }
 
    @Transactional
    public Long add(Card card) {
    	for(PhoneNumber n:card.getPhoneNumbers() ) {
    		n.setCard(card);
    	}
    	
        entityManager.persist(card);
        return card.getId();
    }

	public List<Card> findCards(FindCardCriteria criteria) {
		String qStr = "SELECT c FROM Card c JOIN FETCH c.address";
		if(criteria.getOwner() != null && !criteria.getOwner().trim().isEmpty())
			qStr += "Where c.owner = :owner";
		if(criteria.getAmount() != null && !criteria.getAmount().trim().isEmpty() )
			qStr += "WHERE c.amount= :amount";
		Query q = entityManager.createQuery(qStr);
		if(criteria.getOwner() != null && !criteria.getOwner().trim().isEmpty())
			q.setParameter("owner", criteria.getOwner() );
		if(criteria.getAmount() != null && !criteria.getAmount().trim().isEmpty() )
			q.setParameter("amount", Double.valueOf(criteria.getAmount() ));
		return q.getResultList();
	}
}