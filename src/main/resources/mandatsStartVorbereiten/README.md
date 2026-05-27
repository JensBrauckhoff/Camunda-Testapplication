# Documentation

Das ist ein Beispiel, um die Vorbereitung des Mandatsstarts, der bei der DZ PRIVATBANK implementiert wurde, als ad-hoc Subprozess im Camunda 8 umzusetzen.

Die Implementierung bleibt dem Paradigma, dass jeder Task nur auf Input-Mappings arbeitet. Wenn die Daten aktualisiert werden, müssen User-Tasks beendet werden und neu erzeugt werden, um den aktuellen Stand der Daten zu reflektieren.