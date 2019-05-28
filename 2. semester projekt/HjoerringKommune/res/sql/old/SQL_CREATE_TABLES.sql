USE dmaa0917_1067309


CREATE TABLE ZIPCITY
(
    id				 INT          IDENTITY(1,1),
    zipCode			 VARCHAR (4)  NOT NULL,
    city			 VARCHAR (25) NOT NULL,
	UNIQUE(id),

    PRIMARY KEY (id)
)

CREATE TABLE EMPLOYEE
(
    id               INT            IDENTITY(1,1),
    jobtitle         VARCHAR(100)   NOT NULL,
    picture          VARBINARY(MAX),
    email            VARCHAR(100)   NOT NULL,
    emergencyContact VARCHAR(11),
    fName            VARCHAR(25)    NOT NULL,
    lName            VARCHAR(25)    NOT NULL,
    address          VARCHAR(100)   NOT NULL,
    phone            VARCHAR(8)     NOT NULL,
    sex              CHAR(1)        NOT NULL,
	zipcity_id       INT            NOT NULL,
    UNIQUE(id, email, phone),

    PRIMARY KEY (id),
	FOREIGN KEY (zipcity_id) REFERENCES ZIPCITY (id)
)

CREATE TABLE JOURNAL
(
    id               INT             IDENTITY(1,1),
    diseases         VARCHAR (100),
    allergies        VARCHAR (100),
    doctor           VARCHAR (25)    NOT NULL,
	dependent_cprNo  VARCHAR (12),
    UNIQUE(id),
	UNIQUE(dependent_cprNo),
	
    PRIMARY KEY (id) 
)


CREATE TABLE DEPENDENT
(
    id               INT            IDENTITY(1,1),
    fName            VARCHAR(25)    NOT NULL,
    lName            VARCHAR(25)    NOT NULL,
    address          VARCHAR(100)   NOT NULL,
    phone            VARCHAR(15)    NOT NULL,
    sex              CHAR(1)        NOT NULL,
    partnerName      VARCHAR(25),
    emergencyContact VARCHAR(15),
    cprNo            VARCHAR (12)   NOT NULL,
	zipcity_id       INT            NOT NULL,
    UNIQUE(id, cprNo),

    PRIMARY KEY (id),
    CONSTRAINT FKJOURNAL FOREIGN  KEY (cprNo) REFERENCES JOURNAL (dependent_cprNo) ON DELETE CASCADE,
	FOREIGN KEY (zipcity_id) REFERENCES ZIPCITY (id)
)

CREATE TABLE VISIT
(
    id				INT            IDENTITY (1,1),
    visitStart		DATETIME       NOT NULL,
    employee_id		INT            NOT NULL,
    dependent_id	INT            NOT NULL,
    schedule_id		INT            NOT NULL,
    note            VARCHAR(250),
	date            DATE           NOT NULL,
	time            TIME           NOT NULL  
    UNIQUE(id),

    PRIMARY KEY (id),
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (id),
    CONSTRAINT FKDEPENDENT FOREIGN KEY (dependent_id) REFERENCES DEPENDENT (id) ON DELETE CASCADE
)

CREATE TABLE CAREPACKAGE

(
    id             INT           IDENTITY (1,1),
    type           VARCHAR (50)  NOT NULL,
    duration       TIME          NOT NULL,
    price          FLOAT         NOT NULL,
    UNIQUE(id),

    PRIMARY KEY (id)
)

CREATE TABLE DEPENDENT_CAREPACKAGE
(
    dependent_id   INT           IDENTITY(1,1),
    carepackage_id INT           NOT NULL,
    UNIQUE(dependent_id),

    PRIMARY KEY (dependent_id, carepackage_id),
    FOREIGN KEY (dependent_id) REFERENCES DEPENDENT (id),
    FOREIGN KEY (carepackage_id) REFERENCES CAREPACKAGE (id)

)

CREATE TABLE MEDICINE
(
    id              INT           IDENTITY (1,1),
    name            VARCHAR (100) NOT NULL,
    unit            VARCHAR (25),
    barcode         VARCHAR(128)  NOT NULL,
	manufactor      VARCHAR(150)  NOT NULL,
    UNIQUE(id),
	UNIQUE(barcode),

    PRIMARY KEY (id)
)

CREATE TABLE JOURNAL_MEDICINE
(
    journal_id     INT           IDENTITY(1,1),
    medicine_id    INT           NOT NULL,
    UNIQUE(journal_id, medicine_id),

    PRIMARY KEY (journal_id, medicine_id),
    FOREIGN KEY (journal_id) REFERENCES JOURNAL (id),
    FOREIGN KEY (medicine_id) REFERENCES MEDICINE (id)

)

CREATE TABLE MEDICAL_HISTORY
(
    id             INT           IDENTITY (1,1),
    date           DATE          NOT NULL,
    description    VARCHAR (250),
    journal_id     INT           NOT NULL,
    UNIQUE(id),

    PRIMARY KEY (id),
    FOREIGN KEY (journal_id) REFERENCES JOURNAL (id)
)
