package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;

public class DatabaseTest {

	private static Player player;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test 
//	public void testSignup() {
//		player = new Player("w", "w", "123456");
//		int p2=DatabaseDao.signup(player);
//		Assert.assertTrue(p2>0);
//	}
	
	@Test
	public void testLogin(){
//		DatabaseDao.loadDriverClass();
		String username = "bob";
		String password = "123456";
		player = DatabaseDao.login(username, password);
		Assert.assertNotNull(player);
		
	}
	
//	@Test
//	public void testLogin(){
//		String username = "bob";
//		String password = "12346";
//		player = DatabaseDao.login(username, password);
//		Assert.assertNotNull(player);
//	}

}
