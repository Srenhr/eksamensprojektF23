package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
}
