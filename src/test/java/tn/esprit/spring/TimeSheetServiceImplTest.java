package tn.esprit.spring;


import static org.junit.Assert.assertNotNull;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetServiceImplTest {
	
	
	private static final Logger l = LogManager.getLogger(TimeSheetServiceImplTest.class);
	
	@Autowired
	ITimesheetService timeSheetService;
	@Autowired
	DepartementRepository depRepo;
	@Autowired
	EmployeRepository empRepo;
	@Autowired
	IEmployeService employerService;
	@Autowired
	MissionRepository missRepo;
	
	
	@Test
	public void testAjouterMission() {
		Mission mission = new Mission("validation", "je valide");
//		  assertNull(mission.getName());  // JUnit assertion
//		  assertThat(mission.getName()).isNull();  
		try {
			l.info("In AjouterMission() : ");
			l.debug("lancer ajout");
			timeSheetService.ajouterMission(mission);
			missRepo.delete(mission);
			l.info("Out ajouterMission() without errors.");
		} catch (Exception e) {
			l.error("Erreur dans AjouterMission() :" + e);
		}
	}
	
	@Test
	public void testAffecterMissionAdepartement() {
		Departement dep = new Departement("informatique");
		Mission mission = new Mission("validation", "je valide");
		depRepo.save(dep);
		try {
			l.info("In affecterMissionADepartement() : ");
			l.debug("lancer affectation");
			timeSheetService.affecterMissionADepartement(1, 1);
			timeSheetService.affecterMissionADepartement(0,0);
			l.info("Out affecterMissionADepartement() without errors.");
		} catch (Exception e) {
			l.error("Erreur dans affecterMissionADepartement() :" + e);
		}
	}
	@Test
	public void testAjouterTimeSheet() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2021-03-10");
		Date dateFin = dateFormat.parse("2021-03-19");
		Employe emp = new Employe("Elakermi", "Tarek", "tarek@gmail.com", true, Role.ADMINISTRATEUR);
		assertNotNull(empRepo.save(emp).getId());
		try {
			l.info("In AjouterTimeSheet() : ");
			l.debug("lancer ajoutTimeSheet");
			timeSheetService.ajouterTimesheet(1, 1, new Date(), new Date());
			employerService.deleteEmployeById(1);
		} catch (Exception e) {
			l.error("Erreur dans AjouterTimeSheet() :" + e);
		}

	}
	
	@Test
	public void testfindAllMissionByEmployeJPQL() {
		try {
			l.info("In findAllMissionByEmployeJPQL() : ");
			l.debug("lancer methode");
			timeSheetService.findAllMissionByEmployeJPQL(1);
		} catch (Exception e) {
			l.error("Erreur dans findAllMissionByEmployeJPQL() :" + e);
		}

	}
	
	public void testgetAllEmployeByMission() {
		Mission mission = new Mission("validation", "je valide");
		assertNotNull(missRepo.save(mission).getId());
		try {
			l.info("In getAllEmployeByMission() : ");
			l.debug("lancer methode");
			timeSheetService.getAllEmployeByMission(mission.getId());
			//missRepo.delete(mission);
		} catch (Exception e) {
			l.error("Erreur dans getAllEmployeByMission() :" + e);
		}
	}
	
	@Test
	public void testValiderTimeSheet() {
		try {
			l.info("In ValiderTimeSheet() : ");
			l.debug("lancer methode");
			timeSheetService.validerTimesheet(1, 1, new Date(), new Date("2021-09-12"), 1);
		} catch (Exception e) {
			l.error("Erreur dans ValiderTimeSheet() :" + e);
		}

	}
	
	
	
	
	
	
	
	
	

}