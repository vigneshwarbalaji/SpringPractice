package com.org.action;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.org.model.UserAccounts;
import com.org.sampletest.SampleTest;


//     .setDefaultHighRepJobPolicyUnappliedJobPercentage(100)

public class TestControllerServlet 
{
	
	SampleTest s = new SampleTest();
	
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	
    private Closeable closeable;

    @Before
    public void setUp() {
        helper.setUp();
        ObjectifyService.init();
        ObjectifyService.register(UserAccounts.class);
//        ObjectifyService.begin();
//        ObjectifyRegistrar.SampleTest();
        closeable = ObjectifyService.begin();
        
		
    }


    @After
    public void tearDown() {
        closeable.close();

//        helper.tearDown();
    }
    
	
	
	@Test
	public void getLucky()
	{
		assertEquals(7,s.getLucky());
	}
	
	@Test
	public void testSignIn() 
	{
		assertEquals("please fill all the details",s.signIn("",""));
		assertEquals("User doesnot exist.Please register.",s.signIn("new","password"));
		assertEquals("Password incorrect",s.signIn("run@gmail.com","passjvnfjvnj"));
		assertEquals("success",s.signIn("abc@gmail.com","password"));
	}
	
	@Test
	public void testSignUp() 
	{
		assertEquals("please fill all the details", s.signUp("","", "", ""));
		assertEquals("Password doesn't match", s.signUp("run","run@gmail.com","password", "passward"));
		assertEquals("Password doesn't match", s.signUp("run","run@gmail.com","passworffvfdvf", "passward"));
		assertEquals("Password doesn't match", s.signUp("run","run@gmail.com","password", "passwardhjhjbhjbhj"));
		assertEquals("Password must atleast be of length 6", s.signUp("run","run@gmail.com","pass", "pass"));
		assertEquals("Account Already exists", s.signUp("run","run@gmail.com","password", "password"));
		
	}
  
}
