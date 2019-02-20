package edu.tamu.scholars.middleware.discovery.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PersonTest extends AbstractSolrDocumentTest<Person> {

    @Override
    public Class<?> getType() {
        return Person.class;
    }

}
