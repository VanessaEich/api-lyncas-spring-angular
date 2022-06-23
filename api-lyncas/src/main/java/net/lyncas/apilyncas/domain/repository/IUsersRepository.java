package net.lyncas.apilyncas.domain.repository;

import net.lyncas.apilyncas.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUsersRepository extends JpaRepository<User, Long> {

    @Query("SELECT obj FROM User obj JOIN FETCH obj.authentication WHERE obj IN :users")
    List<User> findUsersAuthentication(List<User> users);

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    @Query(nativeQuery = true, value= "select user_id, name, last_name, login, birth_date, " +
            "telephone from user where name like (:name)")
     List<User> findByName(@Param("name") String name);
}


