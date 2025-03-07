package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.TotalSaleDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<TotalSaleDTO>FindSummary(String firstDate, String lastDate, Pageable pageable){

		LocalDate maxDate = Optional.ofNullable(lastDate).map(LocalDate::parse).orElse(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())); 
		
		LocalDate minDate = Optional.ofNullable(firstDate).map(LocalDate::parse).orElse(maxDate.minusYears(1L)); 
		
		Page<TotalSaleDTO> list = repository.search1(minDate, maxDate, pageable);		
		
		return list;
	}

	public Page<SaleMinDTO>FindReport(String firstDate, String lastDate, String name, Pageable pageable){

		LocalDate maxDate = Optional.ofNullable(lastDate).map(LocalDate::parse).orElse(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())); 
		
		LocalDate minDate = Optional.ofNullable(firstDate).map(LocalDate::parse).orElse(maxDate.minusYears(1L));
		
		Page<SaleMinDTO> list = repository.search2(minDate, maxDate, name, pageable);		
		
		return list;
	}


}
