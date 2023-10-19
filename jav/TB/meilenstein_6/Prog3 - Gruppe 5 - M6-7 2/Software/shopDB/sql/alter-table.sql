SET search_path TO shop;

--Tabelle auftrag
ALTER TABLE auftrag ADD PRIMARY KEY (a_id);
ALTER TABLE auftrag ALTER COLUMN benutzername_fk SET NOT NULL;
ALTER TABLE auftrag ALTER COLUMN version SET DEFAULT 0;

--Tabelle auftragsstatus
ALTER TABLE auftragsstatus ADD PRIMARY KEY (a_id,status_id);
ALTER TABLE auftragsstatus ALTER COLUMN datum SET NOT NULL;
ALTER TABLE auftragsstatus ALTER COLUMN version SET DEFAULT 0;

--Tabelle auftragsstatustyp
ALTER TABLE auftragsstatustyp ADD PRIMARY KEY (status_id);
ALTER TABLE auftragsstatustyp ALTER COLUMN bezeichnung SET NOT NULL;
ALTER TABLE auftragsstatustyp ALTER COLUMN version SET DEFAULT 0;

--Tabelle benutzer
ALTER TABLE benutzer ADD PRIMARY KEY (benutzername);
ALTER TABLE benutzer ALTER COLUMN regdatum SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN passwort SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN gesperrt SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN vorname SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN nachname SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN geschlecht SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN strasse SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN plz SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN ort SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN geburtsdatum SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN email SET NOT NULL;
ALTER TABLE benutzer ALTER COLUMN newsletter SET NOT NULL;
ALTER TABLE benutzer ADD CHECK (geschlecht = 'w' OR geschlecht = 'm');
ALTER TABLE benutzer ALTER COLUMN gesperrt SET DEFAULT false;
ALTER TABLE benutzer ALTER COLUMN version SET DEFAULT 0;

--Tabelle benutzergruppe
ALTER TABLE benutzergruppe ADD PRIMARY KEY (bg_id);
ALTER TABLE benutzergruppe ADD CONSTRAINT name_unique UNIQUE(name);
ALTER TABLE benutzergruppe ALTER COLUMN name SET NOT NULL; 
ALTER TABLE benutzergruppe ALTER COLUMN version SET DEFAULT 0;

--Tabelle benutzer_gruppe
ALTER TABLE benutzer_gruppe ADD PRIMARY KEY (benutzername,bg_id);

--Tabelle einzelteil
ALTER TABLE einzelteil ADD PRIMARY KEY (teile_id);
ALTER TABLE einzelteil ALTER COLUMN beschreibung SET NOT NULL;
ALTER TABLE einzelteil ALTER COLUMN preis SET NOT NULL;
ALTER TABLE einzelteil ALTER COLUMN tt_id_fk SET NOT NULL;
ALTER TABLE einzelteil ALTER COLUMN version SET DEFAULT 0;

--Tabelle fahrradtyp
ALTER TABLE fahrradtyp ADD PRIMARY KEY (typ_id);
ALTER TABLE fahrradtyp ALTER COLUMN radtypbeschreibung SET NOT NULL;
ALTER TABLE fahrradtyp ALTER COLUMN version SET DEFAULT 0;

--Tabelle lager
ALTER TABLE lager ADD PRIMARY KEY (l_id);
ALTER TABLE lager ALTER COLUMN teilenr_fk SET NOT NULL;
ALTER TABLE lager ALTER COLUMN buchungsart SET NOT NULL;
ALTER TABLE lager ALTER COLUMN menge SET NOT NULL;
ALTER TABLE lager ADD CHECK (buchungsart = 's' OR buchungsart = 'h' );
ALTER TABLE lager ALTER COLUMN version SET DEFAULT 0;

--Tabelle modell
ALTER TABLE modell ADD PRIMARY KEY (m_id);
ALTER TABLE modell ALTER COLUMN typ_id_fk SET NOT NULL;
ALTER TABLE modell ALTER COLUMN beschreibung SET NOT NULL;
ALTER TABLE modell ALTER COLUMN art SET NOT NULL;
ALTER TABLE modell ALTER COLUMN datum SET NOT NULL;
ALTER TABLE modell ALTER COLUMN startzeitpunkt SET NOT NULL;
ALTER TABLE modell ALTER COLUMN endzeitpunkt SET NOT NULL;
ALTER TABLE modell ALTER COLUMN preis SET NOT NULL;
ALTER TABLE modell ADD CHECK (art = 'a' OR art = 'k');
ALTER TABLE modell ALTER COLUMN version SET DEFAULT 0;

--Tabelle modell_einzelteil
ALTER TABLE modell_einzelteil ADD PRIMARY KEY (me_id);
ALTER TABLE modell_einzelteil ALTER COLUMN m_id_fk SET NOT NULL;
ALTER TABLE modell_einzelteil ALTER COLUMN teile_id_fk SET NOT NULL;
ALTER TABLE modell_einzelteil ALTER COLUMN menge SET NOT NULL;
ALTER TABLE modell_einzelteil ALTER COLUMN version SET DEFAULT 0;

--Tabelle teiltyp
ALTER TABLE teiltyp ADD PRIMARY KEY (tt_id);
ALTER TABLE teiltyp ALTER COLUMN typbeschreibung SET NOT NULL;
ALTER TABLE teiltyp ALTER COLUMN version SET DEFAULT 0;

--Tabelle warenkorb
ALTER TABLE warenkorb ADD PRIMARY KEY (w_id);
ALTER TABLE warenkorb ALTER COLUMN datum SET NOT NULL;
ALTER TABLE warenkorb ALTER COLUMN version SET DEFAULT 0;

--Tabelle warenkorb_position
ALTER TABLE warenkorb_position ADD PRIMARY KEY (wp_id);
ALTER TABLE warenkorb_position ALTER COLUMN w_id_fk SET NOT NULL;
ALTER TABLE warenkorb_position ALTER COLUMN m_id_fk SET NOT NULL;
ALTER TABLE warenkorb_position ALTER COLUMN anzahl SET NOT NULL;
ALTER TABLE warenkorb_position ALTER COLUMN version SET DEFAULT 0;

--Forein Key Constraints
ALTER TABLE auftrag ADD FOREIGN KEY (benutzername_fk) REFERENCES benutzer (benutzername);
ALTER TABLE auftragsstatus ADD FOREIGN KEY (a_id) REFERENCES auftrag (a_id);
ALTER TABLE auftragsstatus ADD FOREIGN KEY (status_id) REFERENCES auftragsstatustyp (status_id);
ALTER TABLE benutzer_gruppe ADD FOREIGN KEY (benutzername) REFERENCES benutzer (benutzername);
ALTER TABLE benutzer_gruppe ADD FOREIGN KEY (bg_id) REFERENCES benutzergruppe (bg_id);
ALTER TABLE einzelteil ADD FOREIGN KEY (tt_id_fk) REFERENCES teiltyp (tt_id);
ALTER TABLE lager ADD FOREIGN KEY (teilenr_fk) REFERENCES einzelteil (teile_id);
ALTER TABLE modell ADD FOREIGN KEY (typ_id_fk) REFERENCES fahrradtyp (typ_id);
ALTER TABLE modell_einzelteil ADD FOREIGN KEY (teile_id_fk) REFERENCES einzelteil (teile_id);
ALTER TABLE modell_einzelteil ADD FOREIGN KEY (m_id_fk) REFERENCES modell (m_id);
ALTER TABLE warenkorb ADD FOREIGN KEY (a_id_fk) REFERENCES auftrag (a_id);
ALTER TABLE warenkorb_position ADD FOREIGN KEY (m_id_fk) REFERENCES modell (m_id);
ALTER TABLE warenkorb_position ADD FOREIGN KEY (w_id_fk) REFERENCES warenkorb (w_id);
