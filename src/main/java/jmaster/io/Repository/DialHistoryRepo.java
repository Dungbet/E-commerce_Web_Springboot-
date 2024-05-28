package jmaster.io.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmaster.io.entity.DialHistory;

public interface DialHistoryRepo extends JpaRepository<DialHistory, Integer>{
	@Query("SELECT d FROM DialHistory d WHERE d.user.id = :x")
	DialHistory findRewardById (@Param("x") int idUser);
}
