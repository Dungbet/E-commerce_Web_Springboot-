package jmaster.io.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmaster.io.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	// tim theo username
	
		// Select user where username = ?
		User findByUsername(String username);
		
		
		// where name = :s (s truyền vào)
		// List<User> findByName (String s);
		Page<User> findByName (String s, Pageable pageable); //Pageable trả về trang hiện tại và số bản ghi trên 1 trang
															// Nó không trả về list nữa
		
		@Query("SELECT u FROM User u WHERE u.name LIKE :name") // câu lệnh của jbql
		Page<User> searchByName (@Param("name") String s,Pageable pageable);
		
		@Query("SELECT u FROM User u WHERE u.name LIKE :name OR u.username LIKE :name") // câu lệnh của jbql
		List<User> searchByNameOrUsername (@Param("name") String s);
		
		// Xoa tuy bien
		@Modifying
		@Query("DELETE FROM User u WHERE u.username = :x")
		int deleteUser (@Param("x") String username);
		
		// Xoa 1 thuoc tinh
		void deleteByUsername( String username);
		
		@Query("SELECT u FROM User u WHERE MONTH(u.birthdate) = :month AND DATE(u.birthdate) = :date")
		List<User> searchByBirthday(@Param("date") int date, @Param("month") int month);
}
