package ua.org.jblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{

    User findByName(String name);
}
