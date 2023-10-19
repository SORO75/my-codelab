SET search_path TO shop;

CREATE TABLE benutzer 
(
	benutzername varchar(20),
	version integer,
	regdatum timestamp with time zone,
	passwort char(44),
	gesperrt boolean,
	vorname varchar(20),
	nachname varchar(20),
	geschlecht char(1),
	strasse varchar(50),
	plz char(6),
	ort varchar(50),
	geburtsdatum date,
	email varchar(50),
	newsletter boolean
);


CREATE TABLE auftrag
(
	a_id serial,
	version integer,
	benutzername_fk varchar(20)
);


CREATE TABLE benutzergruppe
(
	bg_id serial,
	version integer,
	name varchar(50)
);


CREATE TABLE einzelteil
(
	teile_id serial,
	version integer,
	beschreibung varchar(50),
	preis numeric(5,2),
	tt_id_fk integer
);


CREATE TABLE modell
(
	m_id serial,
	version integer,
	typ_id_fk integer,
	beschreibung varchar(200),
	art char(1),
	datum timestamp with time zone,
	startzeitpunkt timestamp with time zone,
	endzeitpunkt timestamp with time zone,
	preis numeric(6,2),
	bild varchar(40)
);


CREATE TABLE modell_einzelteil
(
	me_id serial,
	m_id_fk integer,
	teile_id_fk integer,
	version integer,
	menge integer
);


CREATE TABLE warenkorb
(
	w_id serial,
	version integer,
	datum timestamp with time zone,
	a_id_fk integer
);


CREATE TABLE warenkorb_position
(
	wp_id serial,
	w_id_fk integer,
	m_id_fk integer,
	version integer,
	anzahl integer 
);


CREATE TABLE fahrradtyp
(
	typ_id serial,
	version integer,
	radtypbeschreibung varchar(50)
);


CREATE TABLE lager
(
	l_id serial,
	version integer,
	teilenr_fk integer,
	buchungsdatum timestamp with time zone,
	buchungsart char(1),
	menge integer
);


CREATE TABLE teiltyp
(
	tt_id serial,
	version integer,
	typbeschreibung varchar(100)
);


CREATE TABLE auftragsstatustyp
(
	status_id serial,
	version integer,
	bezeichnung varchar(50)
);


CREATE TABLE auftragsstatus
(
	a_id integer,
	status_id integer,
	version integer,
	datum timestamp with time zone
);


CREATE TABLE benutzer_gruppe
(
	benutzername varchar(20),
	bg_id integer
);
