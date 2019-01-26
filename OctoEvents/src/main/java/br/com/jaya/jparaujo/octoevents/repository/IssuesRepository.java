package br.com.jaya.jparaujo.octoevents.repository;

import br.com.jaya.jparaujo.octoevents.entity.Issues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IssuesRepository extends CrudRepository<Issues,Long> {

    Optional<List<Issues>> findByNumber(Long number);
}
