package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.TotalSaleDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.TotalSaleDTO(seller.name, SUM(sale.amount)) " +
    "FROM Sale sale " +
    "JOIN sale.seller seller " +
    "WHERE sale.date BETWEEN :minDate AND :maxDate " +
    "GROUP BY seller.name")
    Page<TotalSaleDTO>search1(LocalDate minDate, LocalDate maxDate, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(sale.id, sale.date, sale.amount, seller.name) " +
    "FROM Sale sale " +
    "JOIN sale.seller seller " +
    "WHERE sale.date BETWEEN :minDate AND :maxDate " +
    "AND UPPER(seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<SaleMinDTO>search2(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
    
}
