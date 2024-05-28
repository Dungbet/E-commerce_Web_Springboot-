package jmaster.io.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmaster.io.entity.Reward;

public interface RewardRepo extends JpaRepository<Reward, Integer> {

	@Query("SELECT r.id FROM Reward r WHERE r.tenPhanThuong = :x")
	int findByName(@Param("x") String name);
}
