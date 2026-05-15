package de.ilume.jens.testapplication;

import io.camunda.client.CamundaClient;
import io.camunda.client.annotation.Deployment;
import io.camunda.client.api.response.EvaluateDecisionResponse;
import io.camunda.process.test.api.CamundaSpringProcessTest;
import io.camunda.process.test.api.TestDeployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@CamundaSpringProcessTest
class TestPruefDmn {
    public static final String ANMERKUNG_1 = "Anmerkung1";
    public static final String ANMERKUNG_2 = "Anmerkung2";
    public static final String ANMERKUNG_3 = "Anmerkung3";
    public static final String ANMERKUNG_4 = "Anmerkung4";

    public static final String DECISION_PRUEFUNG_AUSWERTEN = "Decision_Pruefungen_auswerten";
    public static final String DECISION_PERSONEN_PRUEFEN = "Decision_Personen_pruefen";
    public static final String DECISION_HINWEISE_SAMMELN = "Decision_Hinweise_sammeln";
    public static final String VORNAME_1 = "Vorname1";
    public static final String NACHNAME_1 = "Nachname1";

    @Autowired
    private CamundaClient client;

    @Test
    @TestDeployment(resources = "testProcessApplication/dmn/Prafauswertung.dmn")
    void testPruefungAuswerten() {
        final Map<String, Object> variables1 = Map.of("pruefung",
                Map.of("vertragsDaten", Map.of(
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1),
                        "personen", List.of(
                                Map.of(
                                        "vorname", VORNAME_1,
                                        "nachname", NACHNAME_1,
                                        "vollstaendig", true,
                                        "korrekt", true,
                                        "anmerkung", ANMERKUNG_2)
                        )));
        final EvaluateDecisionResponse decisionResult1 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables1).send().join();
        final String decisionOutput1 = decisionResult1.getDecisionOutput();
        System.out.printf("Test 1: %s\n", decisionOutput1);

        final Map<String, Object> variables2 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                        "vollstaendig", true,
                        "korrekt", false,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of(
                                "vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_2)
                )));
        final EvaluateDecisionResponse decisionResult2 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables2).send().join();
        final String decisionOutput = decisionResult2.getDecisionOutput();
        System.out.printf("Test 2: %s\n", decisionOutput);

        final Map<String, Object> variables3 = Map.of("pruefung", Map.of("vertragsDaten", Map.of(
                        "vollstaendig", false,
                        "korrekt", false,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of("vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_2)
                )));
        final EvaluateDecisionResponse decisionResult3 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables3).send().join();
        final String decisionOutpu3 = decisionResult3.getDecisionOutput();
        System.out.printf("Test 3: %s\n", decisionOutpu3);

        final Map<String, Object> variables4 = Map.of("pruefung", Map.of("vertragsDaten",
                Map.of("vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of("vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_2)
                )));
        final EvaluateDecisionResponse decisionResult4 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables4).send().join();
        final String decisionOutput4 = decisionResult4.getDecisionOutput();
        System.out.printf("Test 4: %s\n", decisionOutput4);

        final Map<String, Object> variables5 = Map.of("pruefung",
                Map.of("vertragsDaten", Map.of("vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1),
                        "personen", List.of(
                                Map.of("vorname", VORNAME_1,
                                        "nachname", NACHNAME_1,
                                        "vollstaendig", false,
                                        "korrekt", true,
                                        "anmerkung", ANMERKUNG_2)
                        )));
        final EvaluateDecisionResponse decisionResult5 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables5).send().join();
        final String decisionOutput5 = decisionResult5.getDecisionOutput();
        System.out.printf("Test 5: %s\n", decisionOutput5);

        final Map<String, Object> variables6 = Map.of("pruefung",
                Map.of("vertragsDaten", Map.of(
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1),
                        "personen", List.of(
                                Map.of("vorname", VORNAME_1,
                                        "nachname", NACHNAME_1,
                                        "vollstaendig", true,
                                        "korrekt", true,
                                        "anmerkung", ANMERKUNG_2),
                                Map.of("vorname", "Vorname2",
                                        "nachname", "Nachname2",
                                        "vollstaendig", false,
                                        "korrekt", true,
                                        "anmerkung", ANMERKUNG_3),
                                Map.of("vorname", "Vorname3",
                                        "nachname", "Nachname3",
                                        "vollstaendig", true,
                                        "korrekt", true,
                                        "anmerkung", ANMERKUNG_4)
                        )));
        final EvaluateDecisionResponse decisionResult6 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PRUEFUNG_AUSWERTEN)
                .variables(variables6).send().join();
        final String decisionOutput6 = decisionResult6.getDecisionOutput();
        System.out.printf("Test 6: %s\n", decisionOutput6);
    }

    @Test
    @TestDeployment(resources = "testProcessApplication/dmn/Prafauswertung.dmn")
    void testPersonenPruefung() {
        final Map<String, Object> variables1 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1)
        )));
        final EvaluateDecisionResponse decisionResult1 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PERSONEN_PRUEFEN)
                .variables(variables1).send().join();
        final String decisionOutput1 = decisionResult1.getDecisionOutput();
        System.out.printf("Test 1: %s\n", decisionOutput1);

        final Map<String, Object> variables2 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of(
                        "vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1)
        )));
        final EvaluateDecisionResponse decisionResult2 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_PERSONEN_PRUEFEN)
                .variables(variables2).send().join();
        final String decisionOutput2 = decisionResult2.getDecisionOutput();
        System.out.printf("Test 2: %s\n", decisionOutput2);

        final Map<String, Object> variables3 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2)
        )));
        final EvaluateDecisionResponse decisionResult3 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables3).send().join();
        final String decisionOutput3 = decisionResult3.getDecisionOutput();
        System.out.printf("Test 3: %s\n", decisionOutput3);

        final Map<String, Object> variables4 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2)
        )));
        final EvaluateDecisionResponse decisionResult4 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables4).send().join();
        final String decisionOutput4 = decisionResult4.getDecisionOutput();
        System.out.printf("Test 4: %s\n", decisionOutput4);

        final Map<String, Object> variables5 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2)
        )));
        final EvaluateDecisionResponse decisionResult5 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables5).send().join();
        final String decisionOutput5 = decisionResult5.getDecisionOutput();
        System.out.printf("Test 5: %s\n", decisionOutput5);

        final Map<String, Object> variables6 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2)
        )));
        final EvaluateDecisionResponse decisionResult6 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables6).send().join();
        final String decisionOutput6 = decisionResult6.getDecisionOutput();
        System.out.printf("Test 6: %s\n", decisionOutput6);

        final Map<String, Object> variables21 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2),
                Map.of("vorname", "Vorname3",
                        "nachname", "Nachname3",
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_3)

        )));
        final EvaluateDecisionResponse decisionResult21 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables21).send().join();
        final String decisionOutput21 = decisionResult21.getDecisionOutput();
        System.out.printf("Test 21: %s\n", decisionOutput21);

        final Map<String, Object> variables22 = Map.of("pruefung", Map.of("personen", List.of(
                Map.of("vorname", "Vorname1",
                        "nachname", "Nachname1",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                Map.of("vorname", "Vorname2",
                        "nachname", "Nachname2",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_2),
                Map.of("vorname", "Vorname3",
                        "nachname", "Nachname3",
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_3)

        )));
        final EvaluateDecisionResponse decisionResult22 = client.newEvaluateDecisionCommand()
                .decisionId("Decision_Personen_pruefen")
                .variables(variables22).send().join();
        final String decisionOutput22 = decisionResult22.getDecisionOutput();
        System.out.printf("Test 21: %s\n", decisionOutput22);
    }

    @Test
    @TestDeployment(resources = "testProcessApplication/dmn/Prafauswertung.dmn")
    void testHinweiseSammeln() {
        final Map<String, Object> variables1 = Map.of("pruefung", Map.of(
                "vertragsDaten", Map.of(
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of(
                                "vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1)
                )
        ));
        final EvaluateDecisionResponse decisionResult1 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_HINWEISE_SAMMELN)
                .variables(variables1).send().join();
        final String decisionOutput1 = decisionResult1.getDecisionOutput();
        System.out.printf("Test 1: %s\n", decisionOutput1);

        final Map<String, Object> variables2 = Map.of("pruefung", Map.of(
                "vertragsDaten", Map.of(
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of(
                                "vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", true,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1)
                )
        ));
        final EvaluateDecisionResponse decisionResult2 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_HINWEISE_SAMMELN)
                .variables(variables2).send().join();
        final String decisionOutput2 = decisionResult2.getDecisionOutput();
        System.out.printf("Test 2: %s\n", decisionOutput2);

        final Map<String, Object> variables3 = Map.of("pruefung", Map.of(
                "vertragsDaten", Map.of(
                        "vollstaendig", true,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of(
                                "vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", false,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1)
                )
        ));
        final EvaluateDecisionResponse decisionResult3 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_HINWEISE_SAMMELN)
                .variables(variables3).send().join();
        final String decisionOutput3 = decisionResult3.getDecisionOutput();
        System.out.printf("Test 3: %s\n", decisionOutput3);

        final Map<String, Object> variables4 = Map.of("pruefung", Map.of(
                "vertragsDaten", Map.of(
                        "vollstaendig", false,
                        "korrekt", true,
                        "anmerkung", ANMERKUNG_1),
                "personen", List.of(
                        Map.of(
                                "vorname", VORNAME_1,
                                "nachname", NACHNAME_1,
                                "vollstaendig", false,
                                "korrekt", true,
                                "anmerkung", ANMERKUNG_1)
                )
        ));
        final EvaluateDecisionResponse decisionResult4 = client.newEvaluateDecisionCommand()
                .decisionId(DECISION_HINWEISE_SAMMELN)
                .variables(variables4).send().join();
        final String decisionOutput4 = decisionResult4.getDecisionOutput();
        System.out.printf("Test 4: %s\n", decisionOutput4);
    }

    @SpringBootApplication
    @Deployment(resources = "classpath*:/**/*.dmn")
    static class TestProcessApplication {
    }
}
