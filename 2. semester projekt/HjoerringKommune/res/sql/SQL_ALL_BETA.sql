USE dmaa0917_1067309

--CREATE TABLES 
CREATE TABLE ZIPCITY
(
	id      INT         IDENTITY(1,1),
	zipCode VARCHAR(4)  NOT NULL,
	city    VARCHAR(25) NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE EMPLOYEE
(
	id               INT            IDENTITY(1,1),
	fName            VARCHAR(25)    NOT NULL,
	lName            VARCHAR(25)    NOT NULL,
	address          VARCHAR(100)   NOT NULL,
	zipcity_id       INT            NOT NULL,
	phone            VARCHAR(12)    NOT NULL,
	sex              CHAR(1)        NOT NULL,
	emergencyContact VARCHAR(11),
	jobtitle         VARCHAR(100)   NOT NULL,
	picture          VARBINARY(MAX),
	email            VARCHAR(100)   NOT NULL,
	UNIQUE(email),
	UNIQUE(phone),

	PRIMARY KEY (id),
	FOREIGN KEY (zipcity_id) REFERENCES ZIPCITY (id)
);

CREATE TABLE JOURNAL
(
	id              INT          IDENTITY(1,1),
	disease         VARCHAR(100),
	allergies       VARCHAR(100),
	doctor          VARCHAR(25)  NOT NULL,
	dependent_cprNo VARCHAR(15)  NOT NULL,
	UNIQUE(dependent_cprNo),

	PRIMARY KEY (id)
);

CREATE TABLE DEPENDENT
(
	id               INT          IDENTITY(1,1),
	fName            VARCHAR(25) NOT NULL,
	lName            VARCHAR(25) NOT NULL,
	address          VARCHAR(100) NOT NULL,
	zipcity_id       INT          NOT NULL,
	phone            VARCHAR(12) NOT NULL,
	sex              CHAR(1) NOT NULL,
	partnerName      VARCHAR(100),
	emergencyContact VARCHAR(12),
	journal_id       INT          NOT NULL,
	UNIQUE(phone),

	PRIMARY KEY (id),
	FOREIGN  KEY (journal_id) REFERENCES JOURNAL (id),
	FOREIGN KEY (zipcity_id) REFERENCES ZIPCITY (id)
);

CREATE TABLE CAREPACKAGE
(
	id          INT          IDENTITY (1,1),
	serviceType VARCHAR(100) NOT NULL,
	duration    BIGINT       NOT NULL,
	price       FLOAT        NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE VISIT
(
	id             INT          IDENTITY (1,1),
	employee_id    INT          NOT NULL,
	dependent_id   INT          NOT NULL,
	note           VARCHAR(255),
	startDate      VARCHAR(20) NOT NULL,
	startTime      TIME         NOT NULL,
	visitEnd       TIME         NOT NULL,
	carePackage_id INT          NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (id),
	FOREIGN KEY (dependent_id) REFERENCES DEPENDENT (id),
	FOREIGN KEY (carePackage_id) REFERENCES CAREPACKAGE (id)
);

CREATE TABLE DEPENDENT_CAREPACKAGE
(
	id             INT IDENTITY(1,1),
	dependent_id   INT Not NULL,
	carepackage_id INT NOT NULL,
	UNIQUE(id),

	PRIMARY KEY (id),
	FOREIGN KEY (dependent_id) REFERENCES DEPENDENT (id),
	FOREIGN KEY (carepackage_id) REFERENCES CAREPACKAGE (id)
);

CREATE TABLE MEDICINE
(
	id         INT          IDENTITY (1,1),
	name       VARCHAR(100) NOT NULL,
	manufactor VARCHAR(150) NOT NULL,
	barcode    VARCHAR(20) NOT NULL,
	unit       VARCHAR(25),
	UNIQUE(barcode),

	PRIMARY KEY (id)
);

CREATE TABLE JOURNAL_MEDICINE
(
	journal_id  INT   NOT NULL,
	medicine_id INT   NOT NULL,
	amount      FLOAT,
	dosage      FLOAT,
	UNIQUE(journal_id),
	UNIQUE(medicine_id),

	PRIMARY KEY (journal_id, medicine_id),
	FOREIGN KEY (journal_id) REFERENCES JOURNAL (id),
	FOREIGN KEY (medicine_id) REFERENCES MEDICINE (id)
);

CREATE TABLE MEDICAL_HISTORY
(
	id          INT          IDENTITY (1,1),
	date        DATE         NOT NULL,
	description VARCHAR(255),
	journal_id  INT          NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (journal_id) REFERENCES JOURNAL (id)
);


-- INSERT ZIPCITY
INSERT INTO ZIPCITY
VALUES(7700, 'Thisted');
INSERT INTO ZIPCITY
VALUES
	(7730, 'Hanstholm');
INSERT INTO ZIPCITY
VALUES
	(7741, 'Frøstrup');
INSERT INTO ZIPCITY
VALUES
	(7742, 'Vesløs');
INSERT INTO ZIPCITY
VALUES
	(7752, 'Snedsted');
INSERT INTO ZIPCITY
VALUES
	(7755, 'Bedsted Thy');
INSERT INTO ZIPCITY
VALUES
	(7760, 'Hurup Thy');
INSERT INTO ZIPCITY
VALUES
	(7770, 'Vestervig');
INSERT INTO ZIPCITY
VALUES
	(7900, 'Nykøbing M');
INSERT INTO ZIPCITY
VALUES
	(7950, 'Erslev');
INSERT INTO ZIPCITY
VALUES
	(7960, 'Karby');
INSERT INTO ZIPCITY
VALUES
	(7970, 'Redsted M');
INSERT INTO ZIPCITY
VALUES
	(7980, 'Vils');
INSERT INTO ZIPCITY
VALUES
	(7990, 'Øster Assels');
INSERT INTO ZIPCITY
VALUES
	(9000, 'Aalborg');
INSERT INTO ZIPCITY
VALUES
	(9100, 'Aalborg (postboks)');
INSERT INTO ZIPCITY
VALUES
	(9200, 'Aalborg SV');
INSERT INTO ZIPCITY
VALUES
	(9210, 'Aalborg SØ');
INSERT INTO ZIPCITY
VALUES
	(9220, 'Aalborg Øst');
INSERT INTO ZIPCITY
VALUES
	(9230, 'Svenstrup J');
INSERT INTO ZIPCITY
VALUES
	(9240, 'Nibe');
INSERT INTO ZIPCITY
VALUES
	(9260, 'Gistrup');
INSERT INTO ZIPCITY
VALUES
	(9270, 'Klarup');
INSERT INTO ZIPCITY
VALUES
	(9280, 'Storvorde');
INSERT INTO ZIPCITY
VALUES
	(9293, 'Kongerslev');
INSERT INTO ZIPCITY
VALUES
	(9300, 'Sæby');
INSERT INTO ZIPCITY
VALUES
	(9310, 'Vodskov');
INSERT INTO ZIPCITY
VALUES
	(9320, 'Hjallerup');
INSERT INTO ZIPCITY
VALUES
	(9330, 'Dronninglund');
INSERT INTO ZIPCITY
VALUES
	(9340, 'Asaa');
INSERT INTO ZIPCITY
VALUES
	(9352, 'Dybvad');
INSERT INTO ZIPCITY
VALUES
	(9362, 'Gandrup');
INSERT INTO ZIPCITY
VALUES
	(9370, 'Hals');
INSERT INTO ZIPCITY
VALUES
	(9380, 'Vestbjerg');
INSERT INTO ZIPCITY
VALUES
	(9381, 'Sulsted');
INSERT INTO ZIPCITY
VALUES
	(9382, 'Tylstrup');
INSERT INTO ZIPCITY
VALUES
	(9400, 'Nørresundby');
INSERT INTO ZIPCITY
VALUES
	(9430, 'Vadum');
INSERT INTO ZIPCITY
VALUES
	(9440, 'Aabybro');
INSERT INTO ZIPCITY
VALUES
	(9460, 'Brovst');
INSERT INTO ZIPCITY
VALUES
	(9480, 'Løkken');
INSERT INTO ZIPCITY
VALUES
	(9490, 'Pandrup');
INSERT INTO ZIPCITY
VALUES
	(9492, 'Blokhus');
INSERT INTO ZIPCITY
VALUES
	(9493, 'Saltum');
INSERT INTO ZIPCITY
VALUES
	(9500, 'Hobro');
INSERT INTO ZIPCITY
VALUES
	(9510, 'Arden');
INSERT INTO ZIPCITY
VALUES
	(9520, 'Skørping');
INSERT INTO ZIPCITY
VALUES
	(9530, 'Støvring');
INSERT INTO ZIPCITY
VALUES
	(9541, 'Suldrup');
INSERT INTO ZIPCITY
VALUES
	(9550, 'Mariager');
INSERT INTO ZIPCITY
VALUES
	(9560, 'Hadsund');
INSERT INTO ZIPCITY
VALUES
	(9574, 'Bælum');
INSERT INTO ZIPCITY
VALUES
	(9575, 'Terndrup');
INSERT INTO ZIPCITY
VALUES
	(9600, 'Aars');
INSERT INTO ZIPCITY
VALUES
	(9610, 'Nørager');
INSERT INTO ZIPCITY
VALUES
	(9620, 'Aalestrup');
INSERT INTO ZIPCITY
VALUES
	(9631, 'Gedsted');
INSERT INTO ZIPCITY
VALUES
	(9640, 'Farsø');
INSERT INTO ZIPCITY
VALUES
	(9670, 'Løgstør');
INSERT INTO ZIPCITY
VALUES
	(9681, 'Ranum');
INSERT INTO ZIPCITY
VALUES
	(9690, 'Fjerritslev');
INSERT INTO ZIPCITY
VALUES
	(9700, 'Brønderslev');
INSERT INTO ZIPCITY
VALUES
	(9740, 'Jerslev J');
INSERT INTO ZIPCITY
VALUES
	(9750, 'Østervrå');
INSERT INTO ZIPCITY
VALUES
	(9760, 'Vrå');
INSERT INTO ZIPCITY
VALUES
	(9800, 'Hjørring');
INSERT INTO ZIPCITY
VALUES
	(9830, 'Tårs');
INSERT INTO ZIPCITY
VALUES
	(9850, 'Hirtshals');
INSERT INTO ZIPCITY
VALUES
	(9870, 'Sindal');
INSERT INTO ZIPCITY
VALUES
	(9881, 'Bindslev');
INSERT INTO ZIPCITY
VALUES
	(9900, 'Frederikshavn');
INSERT INTO ZIPCITY
VALUES
	(9940, 'Læsø');
INSERT INTO ZIPCITY
VALUES
	(9970, 'Strandby');
INSERT INTO ZIPCITY
VALUES
	(9981, 'Jerup');
INSERT INTO ZIPCITY
VALUES
	(9982, 'Ålbæk');
INSERT INTO ZIPCITY
VALUES
	(9990, 'Skagen');

--CREATE JOURNAL AND DEPENDENT
--1
INSERT INTO JOURNAL
VALUES('Herpes', 'Gulerødder', 'Emily Nielsen', '123456-1231');
INSERT INTO DEPENDENT
VALUES('Noah', 'Jensen', 'Lærkevej 208', (SELECT id
		FROM ZIPCITY
		where zipCode = '7700'), '+4598150011', 'm',
		'Sofia Jensen', '+4598151122', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '123456-1231'));
--2
INSERT INTO JOURNAL
VALUES('Demens', 'Gluten', 'Adam Nielsen', '020150-2233');
INSERT INTO DEPENDENT
VALUES('Victor', 'Jensen', 'Birkevej 188', (SELECT id
		FROM ZIPCITY
		where zipCode = '7730'), '+4598152233', 'm',
		'Alma Jensen', '+4598153344', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '020150-2233'));
--3
INSERT INTO JOURNAL
VALUES('Alzheimers', 'Mælk', 'Aya Nielsen', '030150-2230');
INSERT INTO DEPENDENT
VALUES('Oliver', 'Jensen', 'Vibevej 181', (SELECT id
		FROM ZIPCITY
		where zipCode = '7741'), '+4598154455', 'm',
		'Emma Jensen', '+4598155566', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '030150-2230'));
--4
INSERT INTO JOURNAL
VALUES('Gigt', 'Sukker', 'Elliot Nielsen', '040150-2238');
INSERT INTO DEPENDENT
VALUES('Oscar', 'Jensen', 'Vinkelvej 178', (SELECT id
		FROM ZIPCITY
		where zipCode = '7742'), '+4598156677', 'm',
		'Ella Jensen', '+4598157788', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '040150-2238'));
--5
INSERT INTO JOURNAL
VALUES('Diabetes', 'Havre', 'Nora Nielsen', '050150-2235');
INSERT INTO DEPENDENT
VALUES('William', 'Jensen', 'Østergade 176', (SELECT id
		FROM ZIPCITY
		where zipCode = '7752'), '+4598159900', 'm',
		'Ida Jensen', '+4598160011', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '050150-2235'));
--6
INSERT INTO JOURNAL
VALUES('Kræft', 'Jordnødder', 'Felix Nielsen', '060150-2232');
INSERT INTO DEPENDENT
VALUES('Lucas', 'Jensen', 'Engvej 173', (SELECT id
		FROM ZIPCITY
		where zipCode = '7755'), '+4598161122', 'm',
		'Freja Jensen', '+4598162233', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '060150-2232'));
--7
INSERT INTO JOURNAL
VALUES('Blodtryk, for lavt', 'ingen', 'Lilly Nielsen', '070150-2221');
INSERT INTO DEPENDENT
VALUES('Carl', 'Jensen', 'Vestergade 171', (SELECT id
		FROM ZIPCITY
		where zipCode = '7760'), '+4598163344', 'm',
		'Clara Jensen', '+4598164455', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '070150-2221'));
--8
INSERT INTO JOURNAL
VALUES('Blodtryk, for højt', 'Chili', 'Benjamin Nielsen', '080150-2237');
INSERT INTO DEPENDENT
VALUES('Malthe', 'Jensen', 'Møllevej 171', (SELECT id
		FROM ZIPCITY
		where zipCode = '7770'), '+4598165566', 'm',
		'Anna Jensen', '+4598166677', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '080150-2237'));
--9
INSERT INTO JOURNAL
VALUES('Angst', 'Sæbe', 'Mille Nielsen', '090150-2234');
INSERT INTO DEPENDENT
VALUES('Emil', 'Jensen', 'Kirkevej 184', (SELECT id
		FROM ZIPCITY
		where zipCode = '7900'), '+4598167788', 'm',
		'Laura Jensen', '+4598168899', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '090150-2234'));
--10
INSERT INTO JOURNAL
VALUES('Blodmangel', 'Øl', 'Storm Nielsen', '100150-2235');
INSERT INTO DEPENDENT
VALUES('Alfred', 'Jensen', 'Bøgevej 163', (SELECT id
		FROM ZIPCITY
		where zipCode = '7950'), '+4598169900', 'm',
		'Alberte Jensen', '+4598170011', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '100150-2235'));
--11
INSERT INTO JOURNAL
VALUES('Depression', 'Rug', 'Sara Nielsen', '110150-2232');
INSERT INTO DEPENDENT
VALUES('Frederik', 'Jensen', 'Industrivej 163', (SELECT id
		FROM ZIPCITY
		where zipCode = '7960'), '+4598171122', 'm',
		'Josefine Jensen', '+4598172233', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '110150-2232'));
--12
INSERT INTO JOURNAL
VALUES('Eksem', 'Mandler', 'Albert Nielsen', '120150-2221');
INSERT INTO DEPENDENT
VALUES('Valdemar', 'Jensen', 'Tværvej 160', (SELECT id
		FROM ZIPCITY
		where zipCode = '7970'), '+4598173344', 'm',
		'Agnes Jensen', '+4598174455', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '120150-2221'));
--13
INSERT INTO JOURNAL
VALUES('Ingen', 'Ingen', 'Frida Nielsen', '130150-2229');
INSERT INTO DEPENDENT
VALUES('Magnus', 'Jensen', 'Stationsvej 159', (SELECT id
		FROM ZIPCITY
		where zipCode = '7980'), '+4598175566', 'm',
		'Isabella Jensen', '+4598176677', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '130150-2229'));
--14
INSERT INTO JOURNAL
VALUES('Fedme', 'Bananer', 'Theodor Nielsen', '140150-2226');
INSERT INTO DEPENDENT
VALUES('Elias', 'Jensen', 'Elmevej 159', (SELECT id
		FROM ZIPCITY
		where zipCode = '7990'), '+4598178899', 'm',
		'Karla Jensen', '+4598179900', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '140150-2226'));
--15
INSERT INTO JOURNAL
VALUES('AIDS', 'ingen', 'Luna Nielsen', '150150-2231');
INSERT INTO DEPENDENT
VALUES('Christian', 'Jensen', 'Skovvej 154', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598180011', 'm',
		'Lærke Jensen', '+4598181122', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '150150-2231'));
--16
INSERT INTO JOURNAL
VALUES('Hjertekarsygdom', 'Gulerødder', 'Arthur Nielsen', '160150-2220');
INSERT INTO DEPENDENT
VALUES('Alexander', 'Jensen', 'Nørregade 152', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598182233', 'm',
		'Olivia Jensen', '+4598183344', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '160150-2220'));
--17
INSERT INTO JOURNAL
VALUES('Katar', 'Kikærter', 'Niels Nielsen', '170150-2228');
INSERT INTO DEPENDENT
VALUES('August', 'Jensen', 'Bakkevej 149', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598184455', 'm',
		'Victoria Jensen', '+4598185566', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '170150-2228'));
--18
INSERT INTO JOURNAL
VALUES('Kolik', 'Grønne ærter', 'Marie Nielsen', '180150-2233');
INSERT INTO DEPENDENT
VALUES('Anton', 'Jensen', 'Søndergade 148', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598187788', 'm',
		'Sofie Jensen', '+4598188899', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '180150-2233'));
--19
INSERT INTO JOURNAL
VALUES('Malaria', 'Bælgfrugter', 'Konrad Nielsen', '190150-2230');
INSERT INTO DEPENDENT
VALUES('Villads', 'Jensen', 'Skolevej 146', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598189900', 'm',
		'Maja Jensen', '+4598190011', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '190150-2230'));
--20
INSERT INTO JOURNAL
VALUES('Mavesår', 'Alkohol', 'Astrid Nielsen', '200150-2231');
INSERT INTO DEPENDENT
VALUES('Aksel', 'Jensen', 'Drosselvej 146', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598191122', 'm',
		'Mathilde Jensen', '+4598192233', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '200150-2231'));
--21
INSERT INTO JOURNAL
VALUES('Muskelsvind', 'Ost', 'Mads Nielsen', '210150-2239');
INSERT INTO DEPENDENT
VALUES('Nohr', 'Jensen', 'Fassanvej 146', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598193344', 'm',
		'Caroline Jensen', '+4598194455', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '210150-2239'));
--22
INSERT INTO JOURNAL
VALUES('Sklerose', 'ingen', 'Asta Nielsen', '220150-2236');
INSERT INTO DEPENDENT
VALUES('Johan', 'Jensen', 'Mosevej 144', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598195566', 'm',
		'Emilie Jensen', '+4598196677', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '220150-2236'));
--23
INSERT INTO JOURNAL
VALUES('Herpes', 'Gulerødder', 'Viggo Nielsen', '230150-2233');
INSERT INTO DEPENDENT
VALUES('Liam', 'Jensen', 'Lindevej 142', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598197788', 'm',
		'Liva Jensen', '+4598198899', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '230150-2233'));
--24
INSERT INTO JOURNAL
VALUES('HIV', 'ingen', 'Liv Nielsen', '240150-2230');
INSERT INTO DEPENDENT
VALUES('Sebastian', 'Jensen', 'Egevej 141', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598199900', 'm',
		'Esther Jensen', '+4598200011', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '240150-2230'));
--25
INSERT INTO JOURNAL
VALUES('ingen', 'vand', 'Mathias Nielsen', '250150-2238');
INSERT INTO DEPENDENT
VALUES('Mikkel', 'Jensen', 'Syrenvej 136', (SELECT id
		FROM ZIPCITY
		where zipCode = '9000'), '+4598201122', 'm',
		'Ellen Jensen', '+4598202233', (SELECT id
		FROM JOURNAL
		WHERE dependent_cprNo = '250150-2238'));

--CREATE EMPLOYEES
--1
INSERT INTO EMPLOYEE
VALUES('Arne', 'Nielsen', 'Allen 4', 7, '08702578', 'm', '74354800', 'SOSU', NULL, 'arne@hjemmepleje.hjoerring.dk');
--2
INSERT INTO EMPLOYEE
VALUES('Birgit', 'Petersen', 'vejen 2', 5, '123456779', 'f', '12345678', 'Planlægger', NULL, 'birgit@hjemmepleje.hjoerring.dk');
--3
INSERT INTO EMPLOYEE
VALUES('Bo', 'Pedersen', 'Bakken 10', 3, '7863450', 'm', '77520149', 'Planlægger', NULL, 'bo@hjemmepleje.hjoerring.dk');
--4
INSERT INTO EMPLOYEE
VALUES('Camilla', 'Nielsen', 'Stranden 20', 13, '11702578', 'f', '77354800', 'SOSU', NULL, 'camilla@hjemmepleje.hjoerring.dk');
--5
INSERT INTO EMPLOYEE
VALUES('Gert', 'Jensen', 'Vejen 30', 10, '702145', 'm', '12345678', 'SOSU', NULL, 'gert-j@hjemmepleje.hjoerring.dk');
--6
INSERT INTO EMPLOYEE
VALUES('Gert', 'Mikkelsen', 'Vejen 30', 4, '11223344', 'f', '987654321', 'SOSU', NULL, 'gert-m@hjemmepleje.hjoerring.dk');
--7
INSERT INTO EMPLOYEE
VALUES('Iver', 'Jensen', 'Bakken 54', 9, '44332211', 'm', '11448877', 'Planlægger', NULL, 'iver@hjemmepleje.hjoerring.dk');
--8
INSERT INTO EMPLOYEE
VALUES('Jens', 'Jensen', 'Allen 8', 11, '09702578', 'm', '75354800', 'SOSU', NULL, 'jens@hjemmepleje.hjoerring.dk');
--9
INSERT INTO EMPLOYEE
VALUES('Jørn', 'Nielsen', 'Kilden 5', 7, '48624862', 'm', '55486948', 'Planlægger', NULL, 'joern@hjemmepleje.hjoerring.dk');
--10
INSERT INTO EMPLOYEE
VALUES('Kamilla', 'Jensen', 'Stranden 89', 11, '12702578', 'f', '78354800', 'SOSU', NULL, 'kamilla@hjemmepleje.hjoerring.dk');
--11
INSERT INTO EMPLOYEE
VALUES('Kim', 'Nielsen', 'Nytorv 5', 8, '55667788', 'm', '45698740', 'SOSU', NULL, 'kim@hjemmepleje.hjoerring.dk');
--12
INSERT INTO EMPLOYEE
VALUES('Kurt', 'Jensen', 'Vænget 20', 6, '03020100', 'm', '02010304', 'Planlægger', NULL, 'kurt@hjemmepleje.hjoerring.dk');
--13
INSERT INTO EMPLOYEE
VALUES('Lars', 'Jensen', 'Vejen 5', 4, '05702878', 'm', '71354800', 'SOSU', NULL, 'lars@hjemmepleje.hjoerring.dk');
--14
INSERT INTO EMPLOYEE
VALUES('Line', 'Nielsen', 'Gaden 80', 16, '15702578', 'f', '81354800', 'SOSU', NULL, 'line@hjemmepleje.hjoerring.dk');
--15
INSERT INTO EMPLOYEE
VALUES('Lukas', 'Kurtsen', 'Stranden 7', 15, '10702578', 'm', '76354800', 'SOSU', NULL, 'lukas@hjemmepleje.hjoerring.dk');
--16
INSERT INTO EMPLOYEE
VALUES('Mads', 'Nielsen', 'Vænget 2', 14, '05702578', 'm', '68354800', 'SOSU', NULL, 'mads@hjemmepleje.hjoerring.dk');
--17
INSERT INTO EMPLOYEE
VALUES('Michael', 'Nielsen', 'Gaden 99 ', 4, '14702578', 'm', '80354800', 'SOSU', NULL, 'michael@hjemmepleje.hjoerring.dk');
--18
INSERT INTO EMPLOYEE
VALUES('Mikkel', 'Jørgensen', 'Gaden 10', 2, '13702578', 'm', '79354800', 'SOSU', NULL, 'mikkel@hjemmepleje.hjoerring.dk');
--19
INSERT INTO EMPLOYEE
VALUES('Peter', 'Nielsen', 'Vænget 8', 8, '03702578', 'm', '70354800', 'SOSU', NULL, 'peter@hjemmepleje.hjoerring.dk');
--20
INSERT INTO EMPLOYEE
VALUES('Pia', 'Nielsen', 'Vænget 1', 10, '04702578', 'f', '69354800', 'SOSU', NULL, 'pia@hjemmepleje.hjoerring.dk');
--21
INSERT INTO EMPLOYEE
VALUES('Rut', 'Steffensen', 'Vejen 2 ', 10, '23456779', 'f', '23456789', 'Planlægger', NULL, 'rut@hjemmepleje.hjoerring.dk');
--22
INSERT INTO EMPLOYEE
VALUES('Sofie', 'Nielsen', 'Torvet 75', 2, '06702578', 'f', '72354800', 'SOSU', NULL, 'sofie@hjemmepleje.hjoerring.dk');
--23
INSERT INTO EMPLOYEE
VALUES('Sophie', 'Larsen', 'Torvet 33', 15, '07702578', 'f', '73354800', 'SOSU', NULL, 'sophie@hjemmepleje.hjoerring.dk');
--24
INSERT INTO EMPLOYEE
VALUES('Tom', 'Jensen', 'Gaden 71', 15, '16702578', 'm', '69354800', 'SOSU', NULL, 'tom@hjemmepleje.hjoerring.dk');

--CREATE CAREPACKAGE
--1
INSERT INTO CAREPACKAGE
VALUES
	('Rengøring', 12, 65.00);
--2 
INSERT INTO CAREPACKAGE
VALUES
	('Tøjvaske', 12, 45.00);
--3 
INSERT INTO CAREPACKAGE
VALUES
	('Turkøb', 5, 30.00);
-- 4 
INSERT INTO CAREPACKAGE
VALUES
	('Indkøbningsordning', 12, 39.95);
--5
INSERT INTO CAREPACKAGE
VALUES
	('Personlig pleje', 12, 69.95);
--6 
INSERT INTO CAREPACKAGE
VALUES
	('Aflaste', 12, 99.95);

--CREATE DEPENDENT_CAREPACKAGE
--1 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(1, 1),
	(1, 2),
	(1, 6);
--2
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(2, 1),
	(2, 2);
--3 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(3, 1),
	(3, 5),
	(3, 3);
--4 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(4, 1),
	(4, 2),
	(4, 4);
--5 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(5, 1),
	(5, 5),
	(5, 6);
--6
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(6, 1),
	(6, 2),
	(6, 3);
--7
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(7, 1),
	(7, 5);
--8
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(8, 1),
	(8, 2),
	(8, 4);
--9 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(9, 1),
	(9, 5),
	(9, 3);
--10 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(10, 1),
	(10, 2),
	(10, 6);
--11 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(11, 1),
	(11, 5);
--12
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(12, 1),
	(12, 2),
	(12, 3),
	(12, 4);
--13 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(13, 1),
	(13, 5);
--14
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(14, 1),
	(14, 2);
--15 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(15, 1),
	(15, 5),
	(15, 6);
--16 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(16, 1),
	(16, 2),
	(16, 3),
	(16, 4);
--17 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(17, 1),
	(17, 5);
--18
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(18, 1),
	(18, 2);
--19 
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(19, 1),
	(19, 5),
	(19, 3);
--20
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(20, 1),
	(20, 2),
	(20, 6),
	(20, 4);

--21
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(21, 1),
	(21, 5);
--22
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(22, 1),
	(22, 2),
	(22, 3);
--23
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(23, 1),
	(23, 5);
--24
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(24, 1),
	(24, 2),
	(24, 4);
--25
INSERT INTO DEPENDENT_CAREPACKAGE
VALUES
	(25, 1),
	(25, 5),
	(25, 6),
	(25, 3);
