package edu.tamu.scholars.middleware.discovery.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@Source(predicate = "http://xmlns.com/foaf/0.1/Person")
@SolrDocument(collection = "discovery")
public class Person {

    @Id
    @Field
    private String id;

    @Field
    @Source(predicate = "http://www.w3.org/2006/vcard/ns#givenName")
    @Indexed(name = "firstName", type = "string")
    private String firstName;

    @Field
    @Source(predicate = "http://www.w3.org/2006/vcard/ns#familyName")
    @Indexed(name = "lastName", type = "string")
    private String lastName;

    public Person() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
