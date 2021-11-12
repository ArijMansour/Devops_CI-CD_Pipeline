package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;

import org.apache.logging.log4j.LogManager;

@ContextConfiguration(classes = TimesheetSpringBootCoreDataJpaMvcRest1Application.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntrepriseServiceImpl.class)
public class EntrepriseServiceImplTest {
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
	private static final long DEFAULT_TIMEOUT = 10000;

	@Autowired
	EntrepriseServiceImpl emp;
	@Autowired
	IEntrepriseService ent;
/*sonar Analyse */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void ajouterEntrepriseTest() {
		Entreprise e = new Entreprise("SSII Consulting", "cite al ghazela");
		Entreprise ee = new Entreprise("Vermeg", "Lac1");
		Entreprise eee = new Entreprise("Visteon", "Borj sedreya");
		try {

			l.info("In ajouterEntrepriseTest() : ");

			emp.ajouterEntreprise(e);
			assertNotNull(e.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteEntrepriseById(e.getId());

			emp.ajouterEntreprise(ee);
			assertNotNull(ee.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteEntrepriseById(ee.getId());

			emp.ajouterEntreprise(eee);
			assertNotNull(eee.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteEntrepriseById(eee.getId());

		}

		catch (Exception e1) {
			l.error("Erreur dans ajouterEntreprise() : " + e1);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void ajouterDepartementTest() {
		Departement d = new Departement("RH");
		Departement dd = new Departement("Engineering");
		Departement ddd = new Departement("Quality");
		try {

			l.info("In ajouterDepartementTest() : ");

			emp.ajouterDepartement(d);
			assertNotNull(d.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteDepartementById(d.getId());

			emp.ajouterDepartement(dd);
			assertNotNull(dd.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteDepartementById(dd.getId());

			emp.ajouterDepartement(ddd);
			assertNotNull(ddd.getId());
			l.info("Out ajouterEntrepriseTest() without errors.");
			ent.deleteDepartementById(ddd.getId());

		}

		catch (Exception e1) {
			l.error("Erreur dans ajouterDepartementTest() : " + e1);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void affecterDepartementEntrepriseTest()

	{

		try {

			l.info("In affecterDepartementEntrepriseTest()  : ");

			emp.affecterDepartementAEntreprise(1, 1);
			l.info("Out affecterDepartementEntrepriseTest()  without errors.");

		}

		catch (Exception e1) {
			l.error("Erreur dans affecterDepartementEntrepriseTest()  : " + e1);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	/*getalldepartementsNamesByEntreprises*/
	public void getAllDepartementsNamesByEntrepriseTest() {

		try {

			l.info("In getAllDepartementsNamesByEntrepriseTest()  : ");

			List<String> s = emp.getAllDepartementsNamesByEntreprise(1);
			System.out.println(s);
			/*
			 * assertThat(s).size().isGreaterThan(0);
			 * 
			 * l.info(s.size() + "> 0");
			 */
			l.info("Out getAllDepartementsNamesByEntrepriseTest() without errors.");

		}

		catch (Exception e1) {
			l.error("Erreur dans getAllDepartementsNamesByEntrepriseTest()  : " + e1);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void deleteEntrepriseByIdTest() {

		try {

			l.info("In  deleteEntrepriseByIdTest() : ");
			emp.deleteEntrepriseById(1);

			l.info("Out deleteEntrepriseByIdTest() without errors.");

		}

		catch (Exception e1) {
			l.error("Erreur dans deleteEntrepriseByIdTest(): " + e1);
		}
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void deleteDepartementByIdTest() {

		try {

			l.info("In  deleteDepartementByIdTest() : ");
			emp.deleteDepartementById(1);
			l.info("Out  deleteDepartementByIdTest() without errors.");

		}

		catch (Exception e1) {
			l.error("Erreur dans  deleteDepartementByIdTest(): " + e1);
		}

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void getEntrepriseByIdTest() {

		try {

			l.info("In  getEntrepriseByIdTest() : ");
			Entreprise e = emp.getEntrepriseById(1);
			System.out.println(e);

			l.info("Out  getEntrepriseByIdTest() without errors.");

		}

		catch (Exception e1) {
			l.error("Erreur dans  getEntrepriseByIdTest(): " + e1);
		}

	}

}
