package tn.esprit.spring.services;


 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.DepartementRepository;



@Service
public class DepartementServiceImpl implements IDepartementService {
	@Autowired
	DepartementRepository deptRepoistory;
	
	
	

}