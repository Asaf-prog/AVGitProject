package com.maven.test.avgitproject.daoUser;

import com.maven.test.avgitproject.entity.Sh1Detail;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface Sh1Repository extends JpaRepository<Sh1Detail, Integer> {

}
