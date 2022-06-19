package com.lucasf.springdevforum.configurations.application;

import com.lucasf.springdevforum.domain.*;
import com.lucasf.springdevforum.dtos.AuthorDto;
import com.lucasf.springdevforum.repositories.CategoryRepository;
import com.lucasf.springdevforum.repositories.PostRepository;
import com.lucasf.springdevforum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
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
        User u1 = new User("Lucas", "Farolfi","lucas123@email.com", "12345678");
        User u2 = new User("Joao", "Gabriel","joao123@email.com", "12345678");

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
        Post p2 = new Post("Dockerfile Spring", "Estou com problemas com o Dockerfile.", PostStatus.UNRESOLVED, author2);
        p1.getCategories().addAll(Arrays.asList(csharp, security));
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
