package it.aulab.progetto_blog;

//Assertj è una libreria per scrivere asserzioni nei test java
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.AuthorRepository;
import it.aulab.progetto_blog.repositories.CommentRepository;
import it.aulab.progetto_blog.repositories.PostRepository;

// @SpringBootTest --> annotazione dove carica tutto l'application Context
@DataJpaTest //annotazione dove diciamo che non vogliamo caricarci tutto l'application context ma solo testare le jpa
@AutoConfigureTestDatabase(replace = Replace.NONE) // annotazione usata per comunicare che il db utilizzato è quello attuale
class ProgettoBlogApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	@BeforeEach //annotazione fornita dal framework JUnit5: significa che il metodo che ha quest'annotazione viene eseguito prima di ciascun test(@Test)
	void load() {
		Author a1 = new Author();
		a1.setFirstname("Giuseppe");
		a1.setLastname("Verdi");
		a1.setEmail("verdig@test.it");
		authorRepository.save(a1);

		Post p1 = new Post();
		p1.setTitle("Test");
		p1.setBody("body");
		p1.setPublishDate("20250124");
		p1.setAuthor(a1);
		postRepository.save(p1);

		Comment c1 = new Comment();
		c1.setEmail("sium@libero.it");
		c1.setBody("Sium");
		c1.setDate("20250124");
		c1.setPost(p1);
		commentRepository.save(c1);
	}

	@Test
	void contextLoads() {}

	@Test
	void findByFirstname() {
		assertThat(authorRepository.findByFirstname("Giuseppe"))
		.extracting("firstname")
		.containsOnly("Giuseppe");
	}

	@Test
	void authorsWithSameFirstname() {
		assertThat(authorRepository.authorsWithSameFirstname())
		.extracting("firstname")
		.containsOnly("Giuseppe");
	}

	@Test
	void authorsWithSameFirstnameNonNative() {
		assertThat(authorRepository.authorsWithSameFirstnameNonNative())
		.extracting("firstname")
		.containsOnly("Giuseppe");
	}

	@Test
	void findByTitle() {
		assertThat(postRepository.findByTitle("Test"))
		.extracting("title")
		.containsOnly("Test");
	}

	@Test
	void postsWithSameTitle() {
		assertThat(postRepository.postsWithSameTitle())
		.extracting("title")
		.containsOnly("Test");
	}

	@Test
	void postsWithSameTitleNonNative() {
		assertThat(postRepository.postsWithSameTitleNonNative())
		.extracting("title")
		.containsOnly("Test");
	}

	@Test
	void findByEmail() {
		assertThat(commentRepository.findByEmail("sium@libero.it"))
		.extracting("email")
		.containsOnly("sium@libero.it");
	}

	@Test
	void commentsWithSameEmail() {
		assertThat(commentRepository.commentsWithSameEmail())
		.extracting("email")
		.containsOnly("sium@libero.it");
	}

	@Test
	void commentsWithSameEmailNonNative() {
		assertThat(commentRepository.commentsWithSameEmailNonNative())
		.extracting("email")
		.containsOnly("sium@libero.it");
	}

}
