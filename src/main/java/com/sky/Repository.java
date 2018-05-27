package com.sky;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Repository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);

}
