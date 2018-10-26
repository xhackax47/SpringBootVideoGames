package fr.xhackax47.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.xhackax47.application.Application;
import fr.xhackax47.dao.VideoGameRepository;
import fr.xhackax47.model.VideoGame;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = Application.class)
public class IntegrationTest {

	@Autowired
	private VideoGameRepository vg;

	@Test
	@Transactional
	public void getAllGames() {
		assertThat(vg.findAll());
	}

	@Test
	@Transactional
	public void createVideoGame() {
		VideoGame gameTest = new VideoGame(60, "testPc", "testGame", "testRPG");
		assertThat(vg.save(gameTest));
	}

	@Test
	@Transactional
	public void getVideoGameById() {
		Long gameTestId = 1L;
		assertThat(vg.findById(gameTestId));
	}

	@Test
	@Transactional
	public void updateVideoGame() {
		Long gameTestId = 1L;
		Optional<VideoGame> game = vg.findById(gameTestId);
		VideoGame gameTest = game.get();

		game.get().setName("TesterUPDATED");
		game.get().setGenre("TestUPDATED");
		game.get().setPrice(50);
		game.get().setPlatform("testUPDATED");
		assertThat(vg.save(gameTest));
	}

	@Test
	@Transactional
	public void deleteVideoGame() {
		Long gameTestId = 100L;
		VideoGame gameTest = new VideoGame(gameTestId, 60, "testPc", "testGame", "testRPG");
		assertThat(vg.save(gameTest));
		vg.delete(gameTest);
		assertThat(!vg.existsById(gameTestId));
	}

}
