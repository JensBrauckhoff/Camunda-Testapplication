package de.ilume.jens.testapplication;

import io.camunda.client.CamundaClient;
import io.camunda.client.annotation.Deployment;
import io.camunda.client.api.response.EvaluateDecisionResponse;
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
    public static final String ANMERKUNG_1 = "Anmerkung1";

    @Autowired
    private CamundaClient client;
    @Autowired
    private CamundaProcessTestContext processTestContext;

    @Test
    @TestDeployment(resources = "testProcessApplication/dmn/Prafauswertung.dmn")
    public void testDmn() {
        final Map<String, Object> variables1 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                "vollstaendig", true,
                "korrekt", true,
                "anmerkung", ANMERKUNG_1)));
        final EvaluateDecisionResponse decisionResult1 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Vertragsunterlagen_pruefen")
                .variables(variables1).send().join();
        final String decisionOutput1 = decisionResult1.getDecisionOutput();
        System.out.printf("Test 1: %s\n", decisionOutput1);

        final Map<String, Object> variables2 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                "vollstaendig", true,
                "korrekt", false,
                "anmerkung", ANMERKUNG_1)));
        final EvaluateDecisionResponse decisionResult2 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Vertragsunterlagen_pruefen")
                .variables(variables2).send().join();
        final String decisionOutput = decisionResult2.getDecisionOutput();
        System.out.printf("Test 2: %s\n", decisionOutput);

        final Map<String, Object> variables3 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                "vollstaendig", false,
                "korrekt", false,
                "anmerkung", ANMERKUNG_1)));
        final EvaluateDecisionResponse decisionResult3 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Vertragsunterlagen_pruefen")
                .variables(variables3).send().join();
        final String decisionOutpu3 = decisionResult3.getDecisionOutput();
        System.out.printf("Test 3: %s\n", decisionOutpu3);

        final Map<String, Object> variables4 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                "vollstaendig", false,
                "korrekt", true,
                "anmerkung", ANMERKUNG_1)));
        final EvaluateDecisionResponse decisionResult4 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Vertragsunterlagen_pruefen")
                .variables(variables4).send().join();
        final String decisionOutput4 = decisionResult4.getDecisionOutput();
        System.out.printf("Test 4: %s\n", decisionOutput4);
    }

    @SpringBootApplication
    @Deployment(resources = "classpath*:/**/*.dmn")
    static class TestProcessApplication {
    }
}
