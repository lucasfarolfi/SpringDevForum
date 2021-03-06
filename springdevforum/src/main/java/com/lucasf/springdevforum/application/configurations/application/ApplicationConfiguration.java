package com.lucasf.springdevforum.application.configurations.application;

import com.lucasf.springdevforum.application.dtos.AuthorDto;
import com.lucasf.springdevforum.domain.builders.UserFactory;
import com.lucasf.springdevforum.domain.entities.Category;
import com.lucasf.springdevforum.domain.entities.Comment;
import com.lucasf.springdevforum.domain.entities.Post;
import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.domain.enums.PostStatus;
import com.lucasf.springdevforum.domain.valueObjects.Email;
import com.lucasf.springdevforum.infraestructure.repositories.CategoryRepository;
import com.lucasf.springdevforum.infraestructure.repositories.PostRepository;
import com.lucasf.springdevforum.infraestructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile(value = {"prod", "dev"})
public class ApplicationConfiguration implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();
        categoryRepository.deleteAll();

        // Saving Users
        User u1 = new UserFactory().createUser("Lucas", "Farolfi","lucas123@email.com",
                "123.456.789-10", 21, "12345-6789","12345678").get();
        User u2 = new UserFactory().createUser("João", "Gabriel","joao123@email.com",
                "123.456.789-10", 21, "12345-6789","12345678").get();

        userRepository.saveAll(Arrays.asList(u1, u2));

        // Saving Categories
        Category csharp = new Category("C#");
        Category java = new Category("Java");
        Category security = new Category("Security");
        Category web = new Category("Web");

        categoryRepository.saveAll(Arrays.asList(csharp, java, security, web));

        // Saving Posts
        AuthorDto author1 = new AuthorDto(u1);
        AuthorDto author2 = new AuthorDto(u2);

        Post p1 = new Post("Cors do Dotnet", "Estou com problemas para ativar o CORS.", PostStatus.SOLVED, author1);
        p1.getCategories().addAll(Arrays.asList(csharp, security));
        Post p2 = new Post("Dockerfile Spring", "Estou com problemas com o Dockerfile.", PostStatus.UNRESOLVED, author2);
        p2.getCategories().addAll(Arrays.asList(java, web));

        postRepository.saveAll(Arrays.asList(p1, p2));

        // Saving Posts comments
        Comment c1p1 = new Comment("Já tentou debugar o código ?", author2);
        Comment c2p1 = new Comment("Ja sei, execute esse comando no arquivo Setup.cs", author2);
        Comment c3p1 = new Comment("Funcionou agora, obrigado!", author1);
        p1.getComments().addAll(Arrays.asList(c1p1, c2p1, c3p1));

        Comment c1p2 = new Comment("Você ja executou o Jar?", author1);
        Comment c2p2 = new Comment("Executei, mas agora está dando um erro.", author2);
        Comment c3p2 = new Comment("Devemos resolver esse erro antes.", author1);
        p2.getComments().addAll(Arrays.asList(c1p2, c2p2, c3p2));

        postRepository.saveAll(Arrays.asList(p1, p2));
    }
}
