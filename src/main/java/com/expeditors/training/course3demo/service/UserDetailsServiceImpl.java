package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.model.UserAccount;
import com.expeditors.training.course3demo.model.UserRole;


@Service("securityService")
@Transactional(readOnly=true)
public class UserDetailsServiceImpl implements UserDetailsService, SecurityService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public UserAccount getByName(String name) {
		return entityManager.find(UserAccount.class, name);
	}
	
	@Transactional
	public String add(UserAccount account) {
		List<UserRole> result = new ArrayList<>();
		for(UserRole role : account.getUserRoles() ) {
			String role_name = role.getRole();
			if( role_name.equals("None") ) {
				continue;
			}
			role.setUserAccount(account);
			role.setRole("ROLE_" + role_name );
			result.add(role);
		}
		account.setUserRoles(result);
		entityManager.persist(account);
		return account.getUsername();
	}

//	public UserAccount getByName(String name) {
//		List<UserAccount> users;
//		UserAccount result = null;
//		Query q = entityManager.createQuery("SELECT a from UserAccount a where a.username= :name");
//
//		q.setParameter("name", name).setMaxResults(1);
//		users = q.getResultList();
//		
//		if(users.size() > 0 )
//			result = users.get(0);
//		
//		return result;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		UserAccount account = getByName( name );
		List<GrantedAuthority> authorities = buildUserAuthority(account.getUserRoles() );
		
		return buildUserforAuthentication(account, authorities);
	}

	private UserDetails buildUserforAuthentication(UserAccount account,
			List<GrantedAuthority> authorities) {
		return new User(account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<>();
		
		for( UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole() ));
		}
		
		List<GrantedAuthority> result = new ArrayList<>(setAuths);
		return result;
	}

}
