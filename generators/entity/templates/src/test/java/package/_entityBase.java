package com.mycompany.myapp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.myapp.web.rest.<%= entityClass %>Resource;
import com.mycompany.myapp.web.rest.<%= entityClass %>ResourceIntTest;

/**
 * Test class for the CarotteResource REST controller.
 *
 * @see CarotteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCustomGenApp.class)
public class <%= entityClass %>Base extends <%= entityClass %>ResourceIntTest{

}
