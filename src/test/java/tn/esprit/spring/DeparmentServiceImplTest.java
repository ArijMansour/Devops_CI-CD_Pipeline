package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DeparmentServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(DeparmentServiceImplTest.class);
	private static final long DEFAULT_TIMEOUT = 10000;
	
	@Autowired
	private DepartementRepository depRepo;
	
	
	
	@Mock
	EmployeRepository employesRepository;
	
	@Mock
	DepartementRepository deptRepoistory;
	@Mock
	EmployeServiceImpl emserv;

		@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testaffectempDp() {
		Departement depManagedEntity= new Departement();	
		Mockito.when(deptRepoistory.findById(depManagedEntity.getId())).thenReturn(Optional.of(depManagedEntity));
		deptRepoistory.save(depManagedEntity);
		Employe employeManagedEntity= new Employe();
		Mockito.when(employesRepository.findById(employeManagedEntity.getId())).thenReturn(Optional.of(employeManagedEntity));
		employesRepository.save(employeManagedEntity);
		emserv.affecterEmployeADepartement(employeManagedEntity.getId(), depManagedEntity.getId());
		verify(emserv).affecterEmployeADepartement(employeManagedEntity.getId(),depManagedEntity.getId());

	}
		
	
	@Test(timeout = DEFAULT_TIMEOUT)
		public void testaddUpdateep() {
		
		Departement d = new Departement("DEV");
		String nom= "DEV";
		try {
			l.info("In ajouterDepartementTest() : ");
			l.debug("Method Begin.");
			assertEquals("Erreur sur le nom de departement",d.getName(),nom);
			depRepo.save(d);
		    d.setName("reseau");
		    depRepo.save(d);
		    Departement updatedep = depRepo.findByName("reseau");   
		    assertThat(updatedep.getName()).isEqualTo("reseau");
			l.debug("Method End.");
			l.info("Out of ajouterDepartementTest() without errors.");
		} catch (Exception e) {
			l.error("Error in ajouterDepartementTest() : " + e);
		}

	}
	
	
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testDeletDep() {
		Departement dp = depRepo.findByName("reseau");
		try {
			l.info("In deleteDepartementTest() : ");
			l.debug("Method Begin.");
			depRepo.deleteById(dp.getId());
			Departement  deletedProduct = depRepo.findByName("reseau");  
		    assertThat(deletedProduct).isNull(); 
			l.debug("Method End.");
			l.info("Out of deleteDepartementTest() without errors.");
		} catch (Exception e) {
			l.error("Error in DeleteDepartementTest() : " + e);
		}
	}
		
		
		@Test(timeout = DEFAULT_TIMEOUT)
		public void testFindalldep() {
			List<Departement> departements = (List<Departement>) depRepo.findAll();

			try {
				l.info("In deleteDepartementTest() : ");
				l.debug("Method Begin.");
			//	assertEquals(2,departements.size()); 
				l.debug("Method End.");
				l.info("Out of testFindalldep() without errors.");
			} catch (Exception e) {
				l.error("Error in testFindAllDep() : " + e);
			}		
			
			
	}
		
		@Test(timeout = DEFAULT_TIMEOUT)
		public void getdepartementbyid() {
			//Departement d = depRepo.findById(2).get();

			
			try {

				l.info("In  getDepartementbyID()Test : " );
			//	l.info(d);
				l.info("Out  getdepartementbyid without errors.");

			}

			catch (Exception e1) {
				l.error("Erreur dans  getDepartementbyIDTest(): " + e1);
			}

		}
	
		
		
		
		
		
	
	
	
	
	

}
