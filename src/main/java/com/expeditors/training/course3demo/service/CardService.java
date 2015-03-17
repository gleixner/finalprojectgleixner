package com.expeditors.training.course3demo.service;
 
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 


import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 








import com.expeditors.training.course3demo.dto.FindCardCriteria;
import com.expeditors.training.course3demo.model.Card;
import com.expeditors.training.course3demo.model.CardProductBuys;
//import com.expeditors.training.course3demo.model.PhoneNumber;
import com.expeditors.training.course3demo.model.PhoneNumber;
import com.expeditors.training.course3demo.model.Product;
 
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
		String qStr = "SELECT c FROM Card c JOIN FETCH c.address ";
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
	
//	@Transactional
//	public Card buy(Long cardId, Long productId) {
//		Card c = entityManager.find(Card.class,  cardId);
//		Product p = entityManager.find(Product.class, productId);
//		c.getProducts().add(p);
//		c.setAmount(c.getAmount() - p.getPrice());
//		return c;
//	}
	
	@Transactional
	public Card buy(Long cardId, Long productId) {
		Card c = entityManager.find(Card.class,  cardId);
		Product p = entityManager.find(Product.class, productId);
		CardProductBuys cp = new CardProductBuys(c, p, new Date() );
		c.getBoughtProducts().add(cp);
		c.setAmount(c.getAmount() - p.getPrice());
		return c;
	}
}