
CREATE TABLE STUDENT
(
  MNR        INTEGER                            NOT NULL,
  NACHNAME   VARCHAR2(25 BYTE)                  NOT NULL,
  VORNAME    VARCHAR2(25 BYTE)                  NOT NULL,
  GEB_DATUM  DATE                               NOT NULL,
  IMM_DATUM  DATE                               NOT NULL,
  EXM_DATUM  DATE
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE STUDENT ADD (
  PRIMARY KEY (MNR));




CREATE TABLE SEMESTER
(
  SEMESTER_BEGINN  DATE                         NOT NULL,
  SEMESTER_ENDE    DATE                         NOT NULL,
  SEMESTER_BEZ     VARCHAR2(4 BYTE)             NOT NULL
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE SEMESTER ADD (
  PRIMARY KEY (SEMESTER_BEGINN));







CREATE TABLE NOTENSKALA
(
  UNTERGRENZE  NUMBER(2,1)                      NOT NULL,
  OBERGRENZE   NUMBER(2,1)                      NOT NULL,
  NTEXT        VARCHAR2(12 BYTE)                NOT NULL
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE NOTENSKALA ADD (
  PRIMARY KEY (UNTERGRENZE));







CREATE TABLE LN
(
  MNR      INTEGER                              NOT NULL,
  FKBEZ    VARCHAR2(4 BYTE)                     NOT NULL,
  VNR      INTEGER                              NOT NULL,
  P_DATUM  DATE                                 NOT NULL,
  NOTE     NUMBER(2,1)                          NOT NULL
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE LN ADD (
  PRIMARY KEY (MNR, FKBEZ, VNR));


ALTER TABLE LN ADD (
  CONSTRAINT FK_F FOREIGN KEY (FKBEZ) 
    REFERENCES FACH (FKBEZ));

ALTER TABLE LN ADD (
  CONSTRAINT FK_S FOREIGN KEY (MNR) 
    REFERENCES STUDENT (MNR) DISABLE);











CREATE TABLE FACH
(
  FKBEZ          VARCHAR2(4 BYTE),
  FBESCHREIBUNG  VARCHAR2(25 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE FACH ADD (
  PRIMARY KEY (FKBEZ));








