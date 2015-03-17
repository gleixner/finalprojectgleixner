package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.model.UserAccount;
import com.expeditors.training.course3demo.model.UserRole;

@Service
@Transactional(readOnly=true)
public interface SecurityService {

	public UserAccount getByName( String name );
	
	
	
	@Transactional
	public String add(UserAccount account);
	
}

//@Service
//@Transactional(readOnly=true)
//public class SecurityService {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public UserAccount find( Long id ) {
//		return entityManager.find(UserAccount.class, id);
//	}
//	
//	@Transactional
//	public String add(UserAccount account) {
//		List<UserRole> result = new ArrayList<>();
//		for(UserRole role : account.getUserRoles() ) {
//			String role_name = role.getRole();
//			if( role_name.equals("None") ) {
//				continue;
//			}
//			role.setUserAccount(account);
//			role.setRole("ROLE_" + role_name );
//			result.add(role);
//		}
//		account.setUserRoles(result);
//		entityManager.persist(account);
//		return account.getUsername();
//	}
//	
//}
