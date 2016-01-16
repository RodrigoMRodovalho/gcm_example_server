package br.com.rrodovalho.gcm_example_server.model;

import br.com.rrodovalho.gcm_example_server.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rrodovalho on 15/01/16.
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

    public User findByName(String name);

}
