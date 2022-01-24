//package com.congtam.customer.repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//@Repository
//@Transactional
//public interface StatsRepository {
//    @Query(value = "SELECT count(m.id), t.id, t.name FROM medicine m RIGHT JOIN medicine_type t" +
//            "            ON m.medicineType_id = t.id" +
//            "            GROUP BY t.id",
//            nativeQuery = true)
//    List<Object[]> medsByCateStats();
//    List<Object[]> productStats(String kw, Date fromDate, Date toDate);
//    List<Object[]> productMonthStats(String kw, Date fromDate, Date toDate);
//}
