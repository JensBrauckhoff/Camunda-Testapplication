package de.ilume.jens.testapplication;

import io.camunda.client.CamundaClient;
import io.camunda.client.annotation.Deployment;
import io.camunda.process.test.api.CamundaProcessTestContext;
import io.camunda.process.test.api.CamundaSpringProcessTest;
import io.camunda.process.test.api.TestDeployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@CamundaSpringProcessTest
public class TestPruefDmn {

    @Autowired
    private CamundaClient client;
    @Autowired
    private CamundaProcessTestContext processTestContext;

    @Test
    @TestDeployment(resources = "testProcessApplication/dmn/Prafauswertung.dmn")
    public void testDmn() {
        final Map<String, Object> variables = Map.of();
        final var decisionResult1 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Vertragsunterlagen_pruefen")
                .variables(variables)
                .send().join();
        final var evaluations1 = decisionResult1.getEvaluatedDecisions();
        System.out.println(evaluations1);
    }

    @SpringBootApplication
    @Deployment(resources = "classpath*:/**/*.dmn")
    static class TestProcessApplication {}
}
