package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.Locker;

public interface LockerRepository
        extends JpaRepository<Locker, Long> {

}