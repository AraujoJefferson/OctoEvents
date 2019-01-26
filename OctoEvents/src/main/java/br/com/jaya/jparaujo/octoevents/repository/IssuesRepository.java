package br.com.jaya.jparaujo.octoevents.repository;

import br.com.jaya.jparaujo.octoevents.entity.Issues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssuesRepository extends CrudRepository<Issues,Long> {
}
