package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.IEmployeService;

@ContextConfiguration(classes = TimesheetSpringBootCoreDataJpaMvcRest1Application.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);
	private static final long DEFAULT_TIMEOUT = 10000;

	@Autowired
	IEmployeService empService;

	@Autowired
	EmployeRepository empRepo;

	@Autowired
	DepartementRepository depRepo;

	@Autowired
	EntrepriseRepository entRepo;

	@Autowired
	MissionRepository misRepo;

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testAddEmploye() {
		try {
			l.info("In addEmployeTest() : ");
			l.debug("Method Begin.");

			Employe e1 = new Employe("Mihoubi", "Oussema", "oussema@gmail.com", true, Role.ADMINISTRATEUR);
			Employe e2 = new Employe("Mansour", "Arij", "arij@gmail.com", true, Role.ADMINISTRATEUR);
			empService.ajouterEmploye(e1);
			empService.ajouterEmploye(e2);

			l.debug("Method End.");
			l.info("Out of addEmployeTest() without errors.");
		} catch (Exception e) {
			l.error("Error in addEmployeTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testAddEmployeeToDepartement() {

		try {
			l.info("In addEmployeeToDepartementTest() : ");
			l.debug("Method Begin.");

			Departement d = new Departement("DEP1");
			depRepo.save(d);
			empService.affecterEmployeADepartement(1, d.getId());

			l.debug("Method End.");
			l.info("Out of addEmployeeToDepartementTest() without errors.");
		} catch (Exception e) {
			l.error("Error in addEmployeeToDepartementTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testRemoveEmployeeFromDepartement() {
		try {
			l.info("In removeEmployeeFromDepartementTest() : ");
			l.debug("Method Begin.");

			empService.desaffecterEmployeDuDepartement(1, 1);

			l.debug("Method End.");
			l.info("Out of removeEmployeeFromDepartementTest() without errors.");
		} catch (Exception e) {
			l.error("Error in removeEmployeeFromDepartementTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testAddContract() {

		try {
			l.info("In addContractTest() : ");
			l.debug("Method Begin.");

			Contrat c1 = new Contrat(new Date(), "CDI", 1500);
			Contrat c2 = new Contrat(new Date(), "CDI", 2600);
			Contrat c3 = new Contrat(new Date(), "CDI", 1500);
			Contrat c4 = new Contrat(new Date(), "CDI", 1500);
			Contrat c5 = new Contrat(new Date(), "CDI", 2000);

			empService.ajouterContrat(c1);
			empService.ajouterContrat(c2);
			empService.ajouterContrat(c3);
			empService.ajouterContrat(c4);
			empService.ajouterContrat(c5);

			l.debug("Method End.");
			l.info("Out of addContractTest() without errors.");
		} catch (Exception e) {
			l.error("Error in addContractTest() : " + e);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testAddContractToEmployee() {
		try {
			l.info("In addContractToEmployeeTest() : ");
			l.debug("Method Begin.");

			empService.affecterContratAEmploye(1, 1);

			l.debug("Method End.");
			l.info("Out of addContractToEmployeeTest() without errors.");
		} catch (Exception e) {
			l.error("Error in addContractToEmployeeTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetEmployeeNameById() {
		try {
			l.info("In getEmployeeNameByIdTest() : ");
			l.debug("Method Begin.");

			String s = empService.getEmployePrenomById(1);
			System.out.println(s);

			l.debug("Method End.");
			l.info("Out of getEmployeeNameByIdTest() without errors.");
		} catch (Exception e) {
			l.error("Error in getEmployeeNameByIdTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testDeleteEmployeeById() {
		try {
			l.info("In deleteEmployeeByIdTest() : ");
			l.debug("Method Begin.");

			empService.deleteEmployeById(1);

			l.debug("Method End.");
			l.info("Out of deleteEmployeeByIdTest() without errors.");
		} catch (Exception e) {
			l.error("Error in deleteEmployeeByIdTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testDeleteContractById() {
		try {
			l.info("In deleteContractByIdTest() : ");
			l.debug("Method Begin.");

			empService.deleteContratById(1);

			l.debug("Method End.");
			l.info("Out of deleteContractByIdTest() without errors.");
		} catch (Exception e) {
			l.error("Error in deleteContractByIdTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetEmployeesNumber() {
		try {
			l.info("In getEmployeesNumberTest() : ");
			l.debug("Method Begin.");

			int n = empService.getNombreEmployeJPQL();
			System.out.println(n);
			assertNotNull(n);
			l.info("Employees Number is :" + n);
			l.debug("Method End.");
			l.info("Out of getEmployeesNumberTest() without errors.");
		} catch (Exception e) {
			l.error("Error in getEmployeesNumberTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetAllEmployeesNames() {
		try {
			l.info("In getAllEmployeesNamesTest() : ");
			l.debug("Method Begin.");

			List<String> s = empService.getAllEmployeNamesJPQL();
			System.out.println(s);

			l.debug("Method End.");
			l.info("Out of getAllEmployeesNamesTest() without errors.");
		} catch (Exception e) {
			l.error("Error in getAllEmployeesNamesTest() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetAllEmplyeesByEntreprise() {
		try {
			l.info("In getAllEmplyeesByEntreprise() : ");
			l.debug("Method Begin.");

			Entreprise ent = entRepo.findById(1).get();
			List<Employe> e = empService.getAllEmployeByEntreprise(ent);
			System.out.println(e);

			l.debug("Method End.");
			l.info("Out of getAllEmplyeesByEntreprise() without errors.");
		} catch (Exception e) {
			l.error("Error in getAllEmplyeesByEntreprise() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testUpdateEmailByEmpId() {
		try {
			l.info("In updateEmailByEmpId() : ");
			l.debug("Method Begin.");

			String newEmail = "email";
			empService.mettreAjourEmailByEmployeId(newEmail, 1);

			l.debug("Method End.");
			l.info("Out of updateEmailByEmpId() without errors.");
		} catch (Exception e) {
			l.error("Error in updateEmailByEmpId() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testDeleteAllContracts() {
		try {
			l.info("In deleteAllContracts() : ");
			l.debug("Method Begin.");

			empService.deleteAllContratJPQL();

			l.debug("Method End.");
			l.info("Out of deleteAllContracts() without errors.");
		} catch (Exception e) {
			l.error("Error in deleteAllContracts() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetSalaryByEmployeeID() {
		try {
			l.info("In getSalaryByEmployeeID() : ");
			l.debug("Method Begin.");

			float sl = empService.getSalaireByEmployeIdJPQL(1);
			System.out.println(sl);

			l.debug("Method End.");
			l.info("Out of getSalaryByEmployeeID() without errors.");
		} catch (Exception e) {
			l.error("Error in getSalaryByEmployeeID() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetAverageSalaryByDepartementId() {
		try {
			l.info("In getAverageSalaryByDepartementId() : ");
			l.debug("Method Begin.");

			Double as = empService.getSalaireMoyenByDepartementId(1);
			System.out.println(as);

			l.debug("Method End.");
			l.info("Out of getAverageSalaryByDepartementId() without errors.");
		} catch (Exception e) {
			l.error("Error in getAverageSalaryByDepartementId() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetTimesheetsByMissionAndDate() {
		try {
			l.info("In getTimesheetsByMissionAndDate() : ");
			l.debug("Method Begin.");

			Employe e = empRepo.findById(1).get();
			Mission m = misRepo.findById(1).get();

			Date dd = new Date();
			Date df = new Date();

			List<Timesheet> lt = empService.getTimesheetsByMissionAndDate(e, m, dd, df);
			System.out.println(lt);

			l.debug("Method End.");
			l.info("Out of getTimesheetsByMissionAndDate() without errors.");
		} catch (Exception e) {
			l.error("Error in getTimesheetsByMissionAndDate() : " + e);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetAllEmployees() {
		try {
			l.info("In getAllEmployees() : ");
			l.debug("Method Begin.");

			List<Employe> le = empService.getAllEmployes();
			System.out.println(le);
			assertThat(le).size().isGreaterThan(0);
			l.info(le.size() + "> 0");
			l.debug("Method End.");
			l.info("Out of getAllEmployees() without errors.");
		} catch (Exception e) {
			l.error("Error in getAllEmployees() : " + e);
		}
	}

}