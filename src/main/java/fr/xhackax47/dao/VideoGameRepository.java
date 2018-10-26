package fr.xhackax47.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.xhackax47.model.VideoGame;

@Repository
@Transactional
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {}
