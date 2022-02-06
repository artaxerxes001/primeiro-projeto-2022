package com.artaxerxesprojecdio.user_dept.repositories;

import com.artaxerxesprojecdio.user_dept.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

    //  devemos informar o tipo, e o tipo do ID. <TIPO, TIPO_ID>
public interface UserRepository extends JpaRepository<User, Long> {
}
