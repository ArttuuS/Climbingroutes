package hh.sof03.Climbingroutes.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimbingLogRepository extends JpaRepository<ClimbingLog, Long> {
    List<ClimbingLog> findByUser(User user);
}