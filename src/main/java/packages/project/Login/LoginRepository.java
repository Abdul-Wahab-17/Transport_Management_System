package packages.project.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    @Query("SELECT l FROM Login l WHERE l.loginId = :loginId AND l.pin = :pin")
    Login findByLoginIdAndPin(@Param("loginId") int loginId, @Param("pin") int pin);

    @Query("select L.role from Login L where L.loginId = :loginId")
    String findRoleByLoginId(Integer loginId);

    @Query(" select L from Login L where L.loginId = :loginId")
    Login findByLoginId(Integer loginId);
}
