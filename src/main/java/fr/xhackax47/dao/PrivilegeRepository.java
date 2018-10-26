package fr.xhackax47.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.xhackax47.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {}
