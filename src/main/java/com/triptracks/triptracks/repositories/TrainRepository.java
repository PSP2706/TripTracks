package com.triptracks.triptracks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.triptracks.triptracks.models.Train;
import com.triptracks.triptracks.models.User;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long>{
	List<Train> findBySourceAndDestination(String source,String destination);
    List<Train> findByAddedBy(User user);
    Train findByName(String name);
}
