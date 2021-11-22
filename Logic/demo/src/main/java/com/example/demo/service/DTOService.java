package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.contract.DTOable;
import com.example.demo.contract.IDTO;

@Service
public class DTOService {

	public List<IDTO> generate(List<? extends DTOable> categories) {
		
		List<IDTO> dtos = new ArrayList<>();
		for (DTOable item : categories) {
			dtos.add(item.dtofy());
		}
		
		return dtos;
	}
}