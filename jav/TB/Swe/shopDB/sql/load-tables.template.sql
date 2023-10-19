SET search_path TO shop;
SET datestyle TO 'DMY';

--   Voraussetzungen fuer COPY:
--   1) Superuser der Datenbank
--   2) Absoluter Pfadname fuer Datei oder relativ zu %PGDATA%

COPY auftrag
	FROM '@BASEDIR@/sql/csv/auftrag.csv'
	DELIMITER ';' CSV HEADER;

COPY auftragsstatus
	FROM '@BASEDIR@/sql/csv/auftragsstatus.csv'
	DELIMITER ';' CSV HEADER;

COPY auftragsstatustyp
	FROM '@BASEDIR@/sql/csv/auftragsstatustyp.csv'
	DELIMITER ';' CSV HEADER;

COPY benutzer
	FROM '@BASEDIR@/sql/csv/benutzer.csv'
	DELIMITER ';' CSV HEADER;

COPY benutzer_gruppe
	FROM '@BASEDIR@/sql/csv/benutzer_gruppe.csv'
	DELIMITER ';' CSV HEADER;

COPY benutzergruppe
	FROM '@BASEDIR@/sql/csv/benutzergruppe.csv'
	DELIMITER ';' CSV HEADER;

COPY einzelteil
	FROM '@BASEDIR@/sql/csv/einzelteil.csv'
	DELIMITER ';' CSV HEADER;

COPY fahrradtyp
	FROM '@BASEDIR@/sql/csv/fahrradtyp.csv'
	DELIMITER ';' CSV HEADER;

COPY lager
	FROM '@BASEDIR@/sql/csv/lager.csv'
	DELIMITER ';' CSV HEADER;

COPY modell
	FROM '@BASEDIR@/sql/csv/modell.csv'
	DELIMITER ';' CSV HEADER;

COPY modell_einzelteil
	FROM '@BASEDIR@/sql/csv/modell_einzelteil.csv'
	DELIMITER ';' CSV HEADER;
	
COPY teiltyp
	FROM '@BASEDIR@/sql/csv/teiltyp.csv'
	DELIMITER ';' CSV HEADER;
	
COPY warenkorb
	FROM '@BASEDIR@/sql/csv/warenkorb.csv'
	DELIMITER ';' CSV HEADER;
	
COPY warenkorb_position
	FROM '@BASEDIR@/sql/csv/warenkorb_position.csv'
	DELIMITER ';' CSV HEADER;
