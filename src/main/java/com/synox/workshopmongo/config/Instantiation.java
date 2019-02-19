package com.synox.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.synox.workshopmongo.domain.Post;
import com.synox.workshopmongo.domain.User;
import com.synox.workshopmongo.dto.AuthorDTO;
import com.synox.workshopmongo.dto.CommentDTO;
import com.synox.workshopmongo.repositories.PostRepository;
import com.synox.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2019"), "Partiu Disney!!!", "Vou pra Disney DisNovo kkkk", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2019"), "Hoje tá f*da", "Hoje o dia foi de cão :((( ", new AuthorDTO(alex));
		Post post3 = new Post(null, sdf.parse("24/03/2019"), "Cheguei finalmente !!!", "Estou muito feliz :]", new AuthorDTO(maria));

		CommentDTO c0 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2019"), new AuthorDTO(bob));
		CommentDTO c1 = new CommentDTO("E eu aqui, sem nada a fazer", sdf.parse("21/03/2019"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Que isso ?!? Fica assim naum", sdf.parse("23/03/2019"), new AuthorDTO(maria));
		CommentDTO c3 = new CommentDTO("Como foi o voo?", sdf.parse("24/03/2019"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c0, c1));
		post2.getComments().addAll(Arrays.asList(c2));
		post3.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		maria.getPosts().addAll(Arrays.asList(post1, post3));
		userRepository.save(maria);
	}

}
