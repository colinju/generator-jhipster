package <%=packageName%>;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import <%=packageName%>.web.rest.<%= entityClass %>Resource;
import <%=packageName%>.web.rest.<%= entityClass %>ResourceIntTest;

/**
 * <%= entityClass %>Base is the base class for spring cloud contract.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCustomGenApp.class)
public class <%= entityClass %>Base extends <%= entityClass %>ResourceIntTest{

}
