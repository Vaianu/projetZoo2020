module projetzoo2020.persistence {
	exports org.formation.zoo.service;
	exports org.formation.zoo.stockage;

	requires java.persistence;
	requires java.logging;
	requires java.sql;
//	requires org.junit.jupiter.api;
}