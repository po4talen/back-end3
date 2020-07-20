package com.brainacad;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;

import java.net.Socket;
import java.util.Collection;

public class Hooks {
    private boolean isFirstRun =true;


    @Before (order = 1)
    public void init(){
        if (!isFirstRun) {
            System.out.println("before1 some work");
        }
         isFirstRun= false;
    }

    @Before (order = 19)
    public void init2(Scenario scenario){
        System.out.println("before2 some work");
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag:tags){
            if ("@Defect".equalsIgnoreCase(tag)) {

            //    throw new PendingException("Skip scenarion by tag " + tag + " Please check in Jira");
                throw new PendingException (String.format("Skip scenario by tag %s please %s check in Jira", tag,tag));
            }
            }

    }

    @After
    public void clear(Scenario scenario){
        System.out.println("after some work");
        System.out.println(scenario.isFailed()?"Scenario failed":"Scenario Passed");
    }

}
