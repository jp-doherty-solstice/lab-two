package com.solstice.labtwostarter;

import com.solstice.labtwostarter.repository.QuoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabTwoStarterApplicationTests {

	@Autowired
	private QuoteRepository quoteRepository;



	@Test
	public void contextLoads() {
	}

}
