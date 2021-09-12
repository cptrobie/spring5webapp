package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.*;
import guru.springframework.spring5webapp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Publisher wrox = new Publisher("Wrox Press", "1102 Warwick Road", "Acock's Green", "Burmingham", "B27 6BH");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(wrox);
        wrox.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(wrox);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "987987");
        // use same publisher as ddd

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(wrox);
        wrox.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(wrox);
        //Publisher oreillly = new Publisher("O'Reilly & Associates", "101 Morris Street", "Sebastopol", "CA", "95472");
        //publisherRepository.save(oreillly);

        System.out.println("Number of Publishers" + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + wrox.getBooks().size());
    }
}
