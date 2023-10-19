SET search_path TO shop;

--TODO genaue Startzahlen eintragen
ALTER SEQUENCE auftrag_a_id_seq START WITH 4;
ALTER SEQUENCE auftragsstatustyp_status_id_seq START WITH 9;
ALTER SEQUENCE benutzergruppe_bg_id_seq START WITH 5;
ALTER SEQUENCE einzelteil_teile_id_seq START WITH 28;
ALTER SEQUENCE fahrradtyp_typ_id_seq START WITH 3;
ALTER SEQUENCE lager_l_id_seq START WITH 28;
ALTER SEQUENCE modell_m_id_seq START WITH 5;
ALTER SEQUENCE teiltyp_tt_id_seq START WITH 11;
ALTER SEQUENCE warenkorb_w_id_seq START WITH 5;

ALTER SEQUENCE warenkorb_position_wp_id_seq START WITH 5;
ALTER SEQUENCE modell_einzelteil_me_id_seq START WITH 5;

