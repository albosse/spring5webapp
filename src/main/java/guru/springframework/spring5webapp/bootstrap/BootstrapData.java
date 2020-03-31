package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("O'Reilly", "42 Wallabe Road", "Sydney", "NSW", "42123");
        publisherRepository.save(publisher);

        Author sebastian = new Author("Sebastian", "Fitzek");
        Book abgeschnitten = new Book("Abgeschnitten", "123456");
        sebastian.getBooks().add(abgeschnitten);
        abgeschnitten.getAuthors().add(sebastian);
        abgeschnitten.setPublisher(publisher);
        publisher.getBooks().add(abgeschnitten);

        authorRepository.save(sebastian);
        bookRepository.save(abgeschnitten);
        publisherRepository.save(publisher);

        Author stephen = new Author("Stephen", "King");
        Book institut = new Book("Das Institut", "78945612");
        stephen.getBooks().add(institut);
        institut.getAuthors().add(stephen);
        institut.setPublisher(publisher);
        publisher.getBooks().add(institut);

        authorRepository.save(stephen);
        bookRepository.save(institut);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());

    }
}
