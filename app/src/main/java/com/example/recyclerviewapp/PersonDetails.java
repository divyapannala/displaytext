package com.example.recyclerviewapp;

public class PersonDetails {

    public String personemail;
    public String personcontact;

    public PersonDetails(String personemail, String personcontact) {
        this.personemail = personemail;
        this.personcontact = personcontact;
    }

    public String getPersonemail() {
        return personemail;
    }

    public void setPersonemail(String personemail) {
        this.personemail = personemail;
    }

    public String getPersoncontact() {
        return personcontact;
    }

    public void setPersoncontact(String personcontact) {
        this.personcontact = personcontact;
    }
}
