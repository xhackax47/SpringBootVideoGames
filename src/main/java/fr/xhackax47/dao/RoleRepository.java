package fr.xhackax47.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.xhackax47.model.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {}