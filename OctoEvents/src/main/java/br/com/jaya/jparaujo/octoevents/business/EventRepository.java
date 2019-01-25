package br.com.jaya.jparaujo.octoevents.business;

import br.com.jaya.jparaujo.octoevents.entity.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends CrudRepository<Issue,Integer> {
}
