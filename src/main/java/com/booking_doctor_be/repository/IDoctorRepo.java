package com.booking_doctor_be.repository;

import com.booking_doctor_be.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IDoctorRepo extends JpaRepository<Doctor, Integer> {


    @Query("SELECT h.id AS id, h.name AS name, h.thumbnail AS thumbnail, h.price AS price, h.address AS address, h.province AS province, h.status AS status, " +
            "SUM(CASE WHEN b.status = 'Đã trả phòng' THEN b.total ELSE 0 END) AS revenue " +
            "FROM Doctor h " +
            "LEFT JOIN Booking b ON h.id = b.doctor.id " +
            "WHERE h.owner.id = :ownerId " +
            "AND h.name LIKE CONCAT('%', :nameSearch, '%') " +
            "AND h.status LIKE CONCAT('%', :status, '%') " +
            "GROUP BY h.id, h.name, h.thumbnail, h.price, h.address, h.status")
    Page<IDoctorRepo.DoctorInfo> findByOwnerIdAndNameAndStatus(@Param("ownerId") int ownerId, @Param("nameSearch") String nameSearch, @Param("status") String status, Pageable pageable);

    @Query("SELECT h.id AS id, h.name AS name, h.thumbnail AS thumbnail, h.price AS price, h.address AS address, h.province AS province, h.status AS status, " +
            "SUM(CASE WHEN b.status = 'Đã trả phòng' THEN b.total ELSE 0 END) AS revenue " +
            "FROM Doctor h " +
            "LEFT JOIN Booking b ON h.id = b.doctor.id " +
            "WHERE h.owner.id = :ownerId " +
            "GROUP BY h.id")
    List<IDoctorRepo.DoctorInfo> findByOwnerId(@Param("ownerId") int ownerId);
    @Query("SELECT h.id AS id, h.name AS name, h.thumbnail AS thumbnail, h.price AS price, h.address AS address, h.province AS province, h.status AS status, " +
            "SUM(CASE WHEN b.status = 'Đã trả phòng' THEN b.total ELSE 0 END) AS revenue " +
            "FROM Doctor h " +
            "LEFT JOIN Booking b ON h.id = b.doctor.id " +
            "WHERE h.owner.id = :ownerId " +
            "GROUP BY h.id")
    Page<IDoctorRepo.DoctorInfo> findByOwnerId(@Param("ownerId") int ownerId , Pageable pageable);

    interface DoctorInfo {
        int getId();

        String getName();

        String getThumbnail();

        double getPrice();

        String getAddress();

        String getProvince();

        double getRevenue();

        String getStatus();
    }


    @Query("SELECT h FROM Doctor h WHERE h.province LIKE concat('%', :province, '%') AND h.name LIKE concat('%', :nameSearch, '%') AND (h.price - h.price * h.sale / 100) BETWEEN :minPrice AND :maxPrice")
    Page<Doctor> findDoctorsByNameAndPriceRangeAndLocal(Pageable pageable, @Param("nameSearch") String nameSearch, @Param("province") String province, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT h FROM Doctor h WHERE (h.price - h.price * h.sale / 100)  BETWEEN :minPrice AND :maxPrice")
    Page<Doctor> findAllByPriceRange(Pageable pageable, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT h FROM Doctor h WHERE h.name LIKE concat('%', :nameSearch, '%') AND (h.price - h.price * h.sale / 100) BETWEEN :minPrice AND :maxPrice")
    Page<Doctor> findDoctorsByNameAndPriceRange(Pageable pageable, @Param("nameSearch") String nameSearch, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);


    Doctor findByIdAndOwnerId(int houseId, int ownerId);

    @Query("SELECT b.doctor.id, COUNT(*) FROM Booking b GROUP BY b.doctor.id ORDER BY COUNT(*) DESC")
    List<Integer> getTopBookingDoctorId();
}


