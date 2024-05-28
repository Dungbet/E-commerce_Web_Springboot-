package jmaster.io.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jmaster.io.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer> {

	// No
}
