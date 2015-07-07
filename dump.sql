--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: pharma_red_v2; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE pharma_red_v2 IS 'new version of pharma_red with the same structure to pharma_green';


--
-- Name: audit; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA audit;


ALTER SCHEMA audit OWNER TO postgres;

--
-- Name: main; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA main;


ALTER SCHEMA main OWNER TO postgres;

--
-- Name: SCHEMA main; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA main IS 'schema for main entities';


--
-- Name: mbr; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA mbr;


ALTER SCHEMA mbr OWNER TO postgres;

--
-- Name: sqlsvr_copy; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA sqlsvr_copy;


ALTER SCHEMA sqlsvr_copy OWNER TO postgres;

--
-- Name: SCHEMA sqlsvr_copy; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA sqlsvr_copy IS 'copy of sqlsvr/prd/Nutractech_DB';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = audit, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: audit; Type: TABLE; Schema: audit; Owner: postgres; Tablespace: 
--

CREATE TABLE audit (
    id integer NOT NULL,
    user_id smallint,
    machine character varying(20),
    datetime timestamp without time zone,
    action character varying(10),
    field character varying(20),
    pc_login_name character varying(20)
);


ALTER TABLE audit.audit OWNER TO postgres;

--
-- Name: audit_id_seq; Type: SEQUENCE; Schema: audit; Owner: postgres
--

CREATE SEQUENCE audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE audit.audit_id_seq OWNER TO postgres;

--
-- Name: audit_id_seq; Type: SEQUENCE OWNED BY; Schema: audit; Owner: postgres
--

ALTER SEQUENCE audit_id_seq OWNED BY audit.id;


--
-- Name: user; Type: TABLE; Schema: audit; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    id integer NOT NULL,
    username character varying(10),
    first_name character varying(100),
    last_name character varying(100),
    password character varying(12),
    "position" character varying(100)
);


ALTER TABLE audit."user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: audit; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE audit.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: audit; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


SET search_path = main, pg_catalog;

--
-- Name: area; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE area (
    id smallint NOT NULL,
    name character varying(50)
);


ALTER TABLE main.area OWNER TO postgres;

--
-- Name: area_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE area_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.area_id_seq OWNER TO postgres;

--
-- Name: area_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE area_id_seq OWNED BY area.id;


--
-- Name: classification; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE classification (
    id smallint NOT NULL,
    description character varying(15)
);


ALTER TABLE main.classification OWNER TO postgres;

--
-- Name: classification_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE classification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.classification_id_seq OWNER TO postgres;

--
-- Name: classification_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE classification_id_seq OWNED BY classification.id;


--
-- Name: client; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    id smallint NOT NULL,
    name character varying(10)
);


ALTER TABLE main.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- Name: container; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE container (
    id smallint NOT NULL,
    name character varying(20)
);


ALTER TABLE main.container OWNER TO postgres;

--
-- Name: container_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE container_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.container_id_seq OWNER TO postgres;

--
-- Name: container_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE container_id_seq OWNED BY container.id;


--
-- Name: equipment; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment (
    id smallint NOT NULL,
    code character varying(15),
    name character varying(100)
);


ALTER TABLE main.equipment OWNER TO postgres;

--
-- Name: equipment_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE equipment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.equipment_id_seq OWNER TO postgres;

--
-- Name: equipment_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE equipment_id_seq OWNED BY equipment.id;


--
-- Name: pack_size; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE pack_size (
    id integer NOT NULL,
    quantity double precision,
    unit_id smallint,
    container_id smallint
);


ALTER TABLE main.pack_size OWNER TO postgres;

--
-- Name: pack_size_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE pack_size_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.pack_size_id_seq OWNER TO postgres;

--
-- Name: pack_size_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE pack_size_id_seq OWNED BY pack_size.id;


--
-- Name: packaging_material; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_material (
    id integer NOT NULL,
    code character varying(15),
    description character varying(100),
    client_id smallint
);


ALTER TABLE main.packaging_material OWNER TO postgres;

--
-- Name: packaging_material_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE packaging_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.packaging_material_id_seq OWNER TO postgres;

--
-- Name: packaging_material_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE packaging_material_id_seq OWNED BY packaging_material.id;


--
-- Name: product; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE product (
    id integer NOT NULL,
    code character varying(5),
    brand_name character varying(200),
    generic_name character varying(200),
    classification_id smallint,
    client_id smallint,
    vr_no character varying(10),
    shelf_life smallint,
    area_id smallint,
    pack_size_id smallint
);


ALTER TABLE main.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: raw_material; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE raw_material (
    id integer NOT NULL,
    code character varying(15),
    name character varying(100),
    description character varying(100),
    classification_id smallint,
    client_id smallint
);


ALTER TABLE main.raw_material OWNER TO postgres;

--
-- Name: raw_material_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE raw_material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.raw_material_id_seq OWNER TO postgres;

--
-- Name: raw_material_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE raw_material_id_seq OWNED BY raw_material.id;


--
-- Name: unit; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE unit (
    id smallint NOT NULL,
    name character varying(15)
);


ALTER TABLE main.unit OWNER TO postgres;

--
-- Name: unit_id_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE unit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.unit_id_seq OWNER TO postgres;

--
-- Name: unit_id_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE unit_id_seq OWNED BY unit.id;


SET search_path = mbr, pg_catalog;

--
-- Name: bottling_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE bottling_procedure (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    content character varying(1000),
    step_number smallint
);


ALTER TABLE mbr.bottling_procedure OWNER TO postgres;

--
-- Name: bottling_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE bottling_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.bottling_procedure_id_seq OWNER TO postgres;

--
-- Name: bottling_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE bottling_procedure_id_seq OWNED BY bottling_procedure.id;


--
-- Name: compounding_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE compounding_procedure (
    id integer NOT NULL,
    step_number smallint,
    header character varying(500),
    footer boolean,
    done_by character varying(100),
    checked_by character varying(100),
    manufacturing_procedure_id integer
);


ALTER TABLE mbr.compounding_procedure OWNER TO postgres;

--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE compounding_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.compounding_procedure_id_seq OWNER TO postgres;

--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE compounding_procedure_id_seq OWNED BY compounding_procedure.id;


--
-- Name: dosage; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE dosage (
    id integer NOT NULL,
    raw_material_requirement_id integer,
    percent_multiplier double precision,
    compounding_procedure_id integer
);


ALTER TABLE mbr.dosage OWNER TO postgres;

--
-- Name: dosage_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE dosage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.dosage_id_seq OWNER TO postgres;

--
-- Name: dosage_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE dosage_id_seq OWNED BY dosage.id;


--
-- Name: equipment_requirement; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment_requirement (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    equipment_id smallint,
    procedure character varying(20)
);


ALTER TABLE mbr.equipment_requirement OWNER TO postgres;

--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_coding_equipment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_coding_equipment_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_coding_equipment_id_seq OWNED BY equipment_requirement.equipment_id;


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_coding_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_coding_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_coding_id_seq OWNED BY equipment_requirement.id;


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_coding_manufacturing_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_coding_manufacturing_procedure_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_coding_manufacturing_procedure_id_seq OWNED BY equipment_requirement.manufacturing_procedure_id;


--
-- Name: manufacturing_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE manufacturing_procedure (
    id integer NOT NULL
);


ALTER TABLE mbr.manufacturing_procedure OWNER TO postgres;

--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE manufacturing_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.manufacturing_procedure_id_seq OWNER TO postgres;

--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE manufacturing_procedure_id_seq OWNED BY manufacturing_procedure.id;


--
-- Name: mbr; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE mbr (
    id integer NOT NULL,
    product_id integer,
    batch_size double precision,
    batch_no character varying(10),
    unit_id smallint,
    mfg_date date,
    exp_date date,
    po_no character varying(15)
);


ALTER TABLE mbr.mbr OWNER TO postgres;

--
-- Name: mbr_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE mbr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.mbr_id_seq OWNER TO postgres;

--
-- Name: mbr_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE mbr_id_seq OWNED BY mbr.id;


--
-- Name: packaging_material_requirement; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_material_requirement (
    id integer NOT NULL,
    packaging_material_id integer,
    quantity double precision,
    unit_id smallint,
    udf_id integer
);


ALTER TABLE mbr.packaging_material_requirement OWNER TO postgres;

--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE packaging_material_requirement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.packaging_material_requirement_id_seq OWNER TO postgres;

--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE packaging_material_requirement_id_seq OWNED BY packaging_material_requirement.id;


--
-- Name: packaging_operation; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_operation (
    id integer NOT NULL,
    step_number smallint,
    header character varying(1000),
    manufacturing_procedure_id integer,
    part smallint,
    done_by character varying(100),
    checked_by character varying(100)
);


ALTER TABLE mbr.packaging_operation OWNER TO postgres;

--
-- Name: powder_filling_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE powder_filling_procedure (
    id integer NOT NULL,
    step_number smallint,
    instruction text,
    manufacturing_procedure_id integer,
    requires_equipment boolean,
    done_by character varying(100),
    checked_by character varying(100)
);


ALTER TABLE mbr.powder_filling_procedure OWNER TO postgres;

--
-- Name: TABLE powder_filling_procedure; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE powder_filling_procedure IS 'packaging procedure for filling process';


--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE packaging_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.packaging_procedure_id_seq OWNER TO postgres;

--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE packaging_procedure_id_seq OWNED BY powder_filling_procedure.id;


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE packaging_procedure_operation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.packaging_procedure_operation_id_seq OWNER TO postgres;

--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE packaging_procedure_operation_id_seq OWNED BY packaging_operation.id;


--
-- Name: primary_secondary_packaging; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE primary_secondary_packaging (
    id integer NOT NULL,
    primary_packaging_id integer NOT NULL,
    secondary_packaging_id integer NOT NULL
);


ALTER TABLE mbr.primary_secondary_packaging OWNER TO postgres;

--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE primary_secondary_packaging_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.primary_secondary_packaging_id_seq OWNER TO postgres;

--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE primary_secondary_packaging_id_seq OWNED BY primary_secondary_packaging.id;


--
-- Name: raw_material_requirement; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE raw_material_requirement (
    id integer NOT NULL,
    raw_material_id integer,
    quantity double precision,
    unit_id smallint,
    udf_id integer,
    part smallint
);


ALTER TABLE mbr.raw_material_requirement OWNER TO postgres;

--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE raw_material_requirement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.raw_material_requirement_id_seq OWNER TO postgres;

--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE raw_material_requirement_id_seq OWNED BY raw_material_requirement.id;


--
-- Name: udf; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE udf (
    id integer NOT NULL,
    content double precision,
    unit_id smallint
);


ALTER TABLE mbr.udf OWNER TO postgres;

--
-- Name: udf_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE udf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.udf_id_seq OWNER TO postgres;

--
-- Name: udf_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE udf_id_seq OWNED BY udf.id;


SET search_path = audit, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit ALTER COLUMN id SET DEFAULT nextval('audit_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


SET search_path = main, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY area ALTER COLUMN id SET DEFAULT nextval('area_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY classification ALTER COLUMN id SET DEFAULT nextval('classification_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY container ALTER COLUMN id SET DEFAULT nextval('container_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY equipment ALTER COLUMN id SET DEFAULT nextval('equipment_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY pack_size ALTER COLUMN id SET DEFAULT nextval('pack_size_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY packaging_material ALTER COLUMN id SET DEFAULT nextval('packaging_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY raw_material ALTER COLUMN id SET DEFAULT nextval('raw_material_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY unit ALTER COLUMN id SET DEFAULT nextval('unit_id_seq'::regclass);


SET search_path = mbr, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY bottling_procedure ALTER COLUMN id SET DEFAULT nextval('bottling_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY compounding_procedure ALTER COLUMN id SET DEFAULT nextval('compounding_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY dosage ALTER COLUMN id SET DEFAULT nextval('dosage_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement ALTER COLUMN id SET DEFAULT nextval('equipment_requirement_coding_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY mbr ALTER COLUMN id SET DEFAULT nextval('mbr_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_material_requirement ALTER COLUMN id SET DEFAULT nextval('packaging_material_requirement_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_operation ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_operation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY powder_filling_procedure ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement ALTER COLUMN id SET DEFAULT nextval('raw_material_requirement_id_seq'::regclass);


SET search_path = audit, pg_catalog;

--
-- Data for Name: audit; Type: TABLE DATA; Schema: audit; Owner: postgres
--



--
-- Name: audit_id_seq; Type: SEQUENCE SET; Schema: audit; Owner: postgres
--

SELECT pg_catalog.setval('audit_id_seq', 1, false);


--
-- Data for Name: user; Type: TABLE DATA; Schema: audit; Owner: postgres
--



--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: audit; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, false);


SET search_path = main, pg_catalog;

--
-- Data for Name: area; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO area VALUES (1, 'LIQUID VET');
INSERT INTO area VALUES (2, 'LIQUID HUMAN');
INSERT INTO area VALUES (3, 'POWDER AREA');
INSERT INTO area VALUES (4, 'TABLET HUMAN');
INSERT INTO area VALUES (5, 'TABLET VET');
INSERT INTO area VALUES (6, 'POWDER VET');


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 6, true);


--
-- Data for Name: classification; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO classification VALUES (1, 'LIQUID');
INSERT INTO classification VALUES (2, 'POWDER');
INSERT INTO classification VALUES (3, 'CAPSULE');


--
-- Name: classification_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('classification_id_seq', 3, true);


--
-- Data for Name: client; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO client VALUES (1, 'APT-HEALTH');
INSERT INTO client VALUES (2, 'DERMCLINIC');


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('client_id_seq', 2, true);


--
-- Data for Name: container; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO container VALUES (1, 'bottle');


--
-- Name: container_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('container_id_seq', 1, true);


--
-- Data for Name: equipment; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO equipment VALUES (1, 'NEQ-039', 'Paddle Mixer');
INSERT INTO equipment VALUES (2, 'NEQ-130', 'Encapsulating Machine');
INSERT INTO equipment VALUES (3, NULL, 'Scoops');
INSERT INTO equipment VALUES (4, NULL, 'Spatula');
INSERT INTO equipment VALUES (5, NULL, 'Weighing Balance');
INSERT INTO equipment VALUES (6, NULL, 'PE bag');
INSERT INTO equipment VALUES (8, 'NQC-LAB-021', 'Analytical Weighing Balance');
INSERT INTO equipment VALUES (7, NULL, 'Mesh Screen #20');


--
-- Name: equipment_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('equipment_id_seq', 8, true);


--
-- Data for Name: pack_size; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO pack_size VALUES (31, 30, 10, 1);


--
-- Name: pack_size_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('pack_size_id_seq', 31, true);


--
-- Data for Name: packaging_material; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO packaging_material VALUES (8, 'p1', 'temporary packaging', 1);


--
-- Name: packaging_material_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_id_seq', 8, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO product VALUES (56, 'p1', 'gluta', 'na', 3, 2, 'vr1', 2, 4, 31);
INSERT INTO product VALUES (57, 'p25', 'Nuderm Advance', 'l-glutathione', 2, 2, 'n/a', 2, 4, 31);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 57, true);


--
-- Data for Name: raw_material; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO raw_material VALUES (6, '0030', 'PIPERAZINE CITRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (7, '0144', 'COLISTIN SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (8, '0061', 'SODIUM SACCHARIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (9, '0158', 'TYLOSIN TARTRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (10, '0183', 'VITAMIN C PLAIN(ASCORBIC ACID)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (11, '0040', 'VITAMIN B1 HCL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (12, '0182', 'POTASSIUM HYDROXIDE - FLAKES', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (13, '0179', 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/PINK)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (14, '1005', 'LAMB SPRAVY', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (15, '0146', 'BENZALKONIUM CHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (16, '0128', 'SULFADIAZINE SODIUM', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (17, '0168', 'SAMBONG LEAVES POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (18, '0043', 'CALCIUM CARBONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (19, '0181', 'BIOPERINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (20, '0097', 'PECTIN POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (21, '0009', 'CORNSTARCH', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (22, '0077', 'LAVENDER ESSENTIAL OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (23, '0129', 'STREPTOMYCIN SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (24, '0002', 'CITRIC ACID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (25, '0095', 'EGC SIZE #2  NATURAL (1-0) NATURAL (1-0)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (26, '0211', 'UNFLAVORED JELLY POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (27, '0147', 'SILKWEED', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (28, '0137', 'CALCIUM GLUCONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (29, '0073', 'KOLLICOAT IR WHITE II', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (30, '0082', 'VERNEL SCENT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (31, '0174', 'L-GLUTATHIONE REDUCED', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (32, '0079', 'BLACK PEPPER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (33, '0170', 'ZINC SULPHATE MONOHYDRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (34, '0074', 'OREGANO POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (35, '0143', 'STRAWBERRY FLAVOR POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (36, '1010', 'BETAINE HCL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (37, '0194', 'EGC SIZE #0 BOVINE CAPS WHITE/WHITE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (38, '0150', 'CALCIUM PANTHOTENATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (39, '0113', 'POTASSIUM CHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (40, '0050', 'PORK LIVER POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (41, '0222', 'LABSA (LINEAR ALKYL BENZYL SULPHONIC ACID)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (42, '0031', 'FUJICALIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (43, '0011', 'REFINED SUGAR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (44, '0062', 'SODIUM METABISULFITE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (45, '0192', 'DEXAMETHASONE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (46, '0042', 'SODIUM ASCORBATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (47, '0014', 'TAURINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (48, '0217', 'NATURAL FLAVOR POWDER (DALANDAN)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (49, '1006', 'LANETTO', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (50, '1000', 'TUTTI FRUITY FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (51, '0085', 'SANDALWOOD SCENT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (52, '0191', 'DIMETRIDAZOLE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (53, '0224', 'SODIUM CMC (CARBOXYMETHYLCELLULOSE)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (54, '0021', 'OPAORY LIGHT GREEN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (55, '0163', 'PALM OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (56, '0068', 'KOLLICOAT SMARTSEAL 30D', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (57, '0189', 'GLUTATHIONE POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (58, '0135', 'CALCIUM PROPIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (59, '0204', 'YACON SEEDLING', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (60, '0071', 'KOLLICOAT IR BRILLIANT BLUE ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (61, '0209', 'GUAVA LEAVES EXTRACT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (62, '0216', 'VITAMIN A PALMITATE POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (63, '0006', 'PROPYLENE GLYCOL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (64, '0157', 'MANGANESE SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (65, '0111', 'EUCALYPTUS OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (66, '0057', 'VITAMIN D3 POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (67, '0229', 'TN500', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (68, '0090', 'EGC SIZE #3 GREEN (10-23-5)/ WHITE (20-1)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (69, '1007', 'MELON SWEET', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (70, '1016', 'XANTHAN GUM', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (71, '0157', 'MANGANESE SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (72, '0018', 'OPAORY GREEN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (73, '1002', 'SODIUM CITRATE ANHYDROUS', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (74, '0177', 'FRESH YACOON', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (75, '0205', 'GLUTARALDEHYDE 50%', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (76, '0007', 'METHYL PARABEN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (77, '0221', 'OPTICAL BRIGHTENER (STILIBENE, 3,5-DIHYDROXY)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (78, '0098', 'ORANGE FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (79, '0120', 'LUDIPRESS', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (80, '0084', 'MINERAL OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (81, '1012', 'XYLOSE LYSINE DEOXYCHOLATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (82, '0184', 'ENROFLOXACIN HYDROCHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (83, '0213', 'CRYSTALLINE FRUCTOSE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (84, '1013', 'SELENIUM SULFIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (85, '0107', 'MAGNESIUM SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (86, '0105', 'SODIUM SELENITE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (87, '0019', 'OPAORY BLACK', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (88, '0064', 'TWEEN 80', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (89, '0051', 'VITAMIN B2 PHOSPHATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (90, '0119', 'COPPER SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (91, '0200', 'ZINC OXIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (92, '0142', 'DOXYCYCLINE HCI', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (93, '0214', 'L-CITRULLINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (94, '0029', 'WHEAT GERM OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (95, '0206', 'EUCALYPTOL 99%', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (96, '0033', 'MUSCOVADO SUGAR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (97, '1001', 'BEEF SPRAVY', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (98, '0056', 'VITAMIN D3 OIL 4 MIU', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (99, '0037', 'VITAMIN A ACETATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (100, '0017', 'OPAORY PINK', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (101, '0020', 'OPAORY BROWN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (102, '0046', 'FD & C YELLOW #6', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (103, '0001', 'LIQUID GLUCOSE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (104, '0015', 'BUTYLATED HYDROXYTOLUENE (BHT)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (105, '0066', 'KOLLICOAT IR SUNSET YELLOW', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (106, '0148', 'EUCALYPTUS', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (107, '0044', 'CALCIUM LACTATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (108, '0136', 'TWEEN 20', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (109, '0110', 'FERROUS SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (110, '0013', 'SUN DRIED PEPPER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (111, '0167', 'EGC SIZE #0 NATURAL & GREEN (VEGGIE TYPE)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (112, '0092', 'EGC SIZE #0  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (113, '0058', 'VITAMIN E POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (114, '0115', 'CDEA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (115, '0154', 'SODIUM SULFAQUINOXALINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (116, '0130', 'CARAMEL COLOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (117, '0047', 'FOLIC ACID (FEED GRADE)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (118, '0096', 'MANGOSTEEN FRUIT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (119, '0045', 'CALCIUM D-PANTOTHENATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (120, '0003', 'TRISODIUM PHOSPHATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (121, '0210', 'GUYABANO PULP', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (122, '0208', 'FRESH LUYA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (123, '0141', 'VITAMIN K3', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (124, '0088', 'BLUE BOTTLE W/ MASSAGE OIL (100ML)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (125, '0083', 'VIRGIN COCONUT OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (126, '0218', 'STEVIA REBAUDIANA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (127, '0122', 'BIOTIN 2%', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (128, '0104', 'MALATHION (COMMERCIAL GRADE)100ML', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (129, '0176', 'HONEY', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (130, '0060', 'CARAMEL FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (131, '0039', 'NICOTINAMIDE ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (132, '0164', 'CORN OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (133, '0102', 'FLORFENICOL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (134, '0078', 'SANDALWOOD ESSENTIAL OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (135, '0180', 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/WHITE)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (136, '0212', 'FRESH SAMPALOK', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (137, '0201', 'CHERRY FLAVOR LIQUID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (138, '0022', 'OPAORY WHITE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (139, '0195', 'EGC SIZE #00 BOVINE CAPS WHITE/WHITE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (140, '0012', 'DRIER DRIED PEPPER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (141, '0223', 'VEEGUM', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (142, '0132', 'DL-METHIONINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (143, '0151', 'MILK POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (144, '0215', 'MALIC ACID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (145, '1009', 'PYRANTEL PAMOATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (146, '0093', 'EGC SIZE #00  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (147, '0165', 'CANOLA OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (148, '0187', 'VITAMIN A PALMITATE 1.7MIU', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (149, '0193', 'LUYANG DILAW (TURMERIC) POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (150, '0185', 'SODIUM CITRATE DIHYDRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (151, '0027', 'NANDROLONE PHENYLPROPIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (152, '0202', 'MALTODEXTRIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (153, '0219', 'ZINC SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (154, '0072', 'KOLLICOAT IR CARMINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (155, '0063', 'ETHYL ALCOHOL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (156, '0171', 'MALUNGGAY POWDER WITH COA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (157, '0081', 'MENTHOL CRYSTAL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (158, '0134', 'ALBENDAZOLE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (159, '0203', 'NORFLOXACIN HYDROCHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (160, '0070', 'KOLLICOAT IR BLACK', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (161, '0016', 'ZINC SULPHATE HEPTAHYDRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (162, '0091', 'EGC SIZE #1 NATURAL (1-0) NATURAL (1-0)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (163, '0053', 'NIACIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (164, '0034', 'GREEN STEVIA/30G', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (165, '0036', 'SORBITOL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (166, '0196', 'METHANDRIOL PROPIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (167, '0118', 'PEARLIZER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (168, '0172', 'COLLAGEN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (169, '0160', 'LEVAMISOLE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (170, '0197', 'THIAMPHENICOL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (171, '0133', 'SODIUM CHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (172, '0166', 'EGC SIZE #00 NATURAL & GREEN (VEGGIE TYPE)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (173, '0024', 'METHANDRIOL DIPROPIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (174, '0041', 'VITAMIN C COATED ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (175, '0026', 'SODIUM STARCH GLYCOLATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (176, '0005', 'VITAMIN E ACETATE OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (177, '0067', 'KOLLICOAT IR  YELLOW', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (178, '0032', 'MORINGGA LEAVES POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (179, '0198', 'MIGLYOL 840', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (180, '0121', 'SODIUM BICARBONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (181, '0086', 'POWDER SCENT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (182, '0048', 'GLYCERINE PURE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (183, '0023', 'HYDROXOCOBALAMINE ACETATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (184, '0190', 'N-METHYL-PYRROLIDONE (NMP)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (185, '0080', 'GUYABANO FRUIT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (186, '0075', 'LUYANG DILAW (ORGANIC TURMERIC)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (187, '0269', 'ZINC SULFATE MONOHYDRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (188, '0149', 'VITAMIN E USP', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (189, '0178', 'LYCHEE FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (190, '0156', 'SODIUM SULFAMONOMETHOXINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (191, '0124', 'SODA ASH LIGHT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (192, '1015', 'CHOLINE CHLORIDE 70% LIQUID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (193, '0186', 'VITAMIN E ACETATE 970 IU/G', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (194, '0127', 'XYLENE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (195, '0153', 'OXYTETRACYCLINE HCL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (196, '0125', 'MICROCRYSTALLINE CELLULOSE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (197, '0173', 'ALPHA-LIPOIC ACID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (198, '0139', 'TALC POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (199, '0052', 'TETRASODIUM EDTA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (200, '0089', 'KOLLIDON VA64 FINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (201, '0035', 'SODIUM BENZOATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (202, '0220', 'TRISODIUM PHOSPHATE DODECAHYDRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (203, '0138', 'CALCIUM GLUBIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (204, '0109', 'POTASSIUM IODINE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (205, '0131', 'DEXTROSE ANHYDROUS POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (206, '0116', 'STEARIC ACID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (207, '0159', 'LEVAMISOLE HCL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (208, '0296', 'RASPBERRY FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (209, '0162', 'MEATMEAL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (210, '0169', 'GUYABANO LEAVES POWDER', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (211, '1014', 'SILVER DIOXIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (212, '0126', 'DI-TAB', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (213, '0152', 'PERMETHRIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (214, '0025', 'PREDNISOLONE ACETATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (215, '0004', 'KAOLIN LIGHT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (216, '0094', 'EGC SIZE #4 GREEN DULL (10-25-5)/ GRAY (9-75-5)', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (217, '0101', 'CEFTIOFUR HYDROCHLORIDE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (218, '1011', 'MR. VP, BBL BROTH', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (219, '0010', 'MAGNESIUM STEARATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (220, '0069', 'TRIETHYL CITRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (221, '0099', 'CIPROFLOXACIN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (222, '1008', 'MY STRAWBERRY SCENT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (223, '0175', 'ALOE VERA', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (224, '0140', 'TRIMETHOPRIM', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (225, '0038', 'VITAMIN B12 PURE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (226, '0100', 'FOLIC ACID PHARMA GRADE ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (227, '0199', 'CAB-O-SIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (228, '0178', 'LYCHEE FLAVOR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (229, '0123', 'VITAMIN A DRY 1 MIU', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (230, '0108', 'PVP K 30', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (231, '0103', 'OXYTETRACYCLINE ANHYDROUS', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (232, '0106', 'COBALT SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (233, '0065', 'VITAMIN B6 HCL ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (234, '0155', 'NEOMYCIN SULFATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (235, '0054', 'SODIUM CITRATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (236, '0112', 'ETHOXYLATED HYDROGENATED CASTOR OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (237, '0117', 'SLES', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (238, '0114', 'AMITRAZ', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (239, '0188', 'CALCIUM CARBONATE LIGHT', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (240, '0076', 'PEPPERMINT ESSENTIAL OIL', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (241, '0059', 'VITAMIN A PROPIONATE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (242, '1004', 'HYOSCINE HBR', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (243, '0028', 'ETHYL CELLULOSE', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (244, '1003', 'FD & C RED # 3', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (245, '0049', 'L-LYSINE USP', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (246, '0207', 'BENZOIC ACID', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (247, '0008', 'PROPYL PARABEN', NULL, NULL, NULL);
INSERT INTO raw_material VALUES (248, '0161', 'L-LYSINE HCL WSP', NULL, NULL, NULL);


--
-- Name: raw_material_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_id_seq', 248, true);


--
-- Data for Name: unit; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO unit VALUES (1, 'mcL');
INSERT INTO unit VALUES (2, 'mL');
INSERT INTO unit VALUES (3, 'L');
INSERT INTO unit VALUES (4, 'mcg');
INSERT INTO unit VALUES (5, 'mg');
INSERT INTO unit VALUES (6, 'g');
INSERT INTO unit VALUES (7, 'kg');
INSERT INTO unit VALUES (8, 'roll');
INSERT INTO unit VALUES (9, 'pcs');
INSERT INTO unit VALUES (10, 'capsules');
INSERT INTO unit VALUES (11, 'boxes');


--
-- Name: unit_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('unit_id_seq', 11, true);


SET search_path = mbr, pg_catalog;

--
-- Data for Name: bottling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO bottling_procedure VALUES (4, 56, 'bottling procedure 1', 1);
INSERT INTO bottling_procedure VALUES (5, 57, 'Perform room/line clearance based in SOP.
	-Bottling Area', 1);


--
-- Name: bottling_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('bottling_procedure_id_seq', 5, true);


--
-- Data for Name: compounding_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO compounding_procedure VALUES (23, 1, 'compounding 1', true, NULL, NULL, 56);
INSERT INTO compounding_procedure VALUES (24, 2, 'compounding 2', false, NULL, NULL, 56);
INSERT INTO compounding_procedure VALUES (25, 1, 'Ensure all the equipment, including manufacturing area are cleaned and cleared from all traces of the previous batch and free from moisture.', true, NULL, NULL, 57);


--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('compounding_procedure_id_seq', 25, true);


--
-- Data for Name: dosage; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO dosage VALUES (21, 47, 1, 24);


--
-- Name: dosage_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('dosage_id_seq', 21, true);


--
-- Data for Name: equipment_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO equipment_requirement VALUES (15, 56, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (16, 57, 1, 'COMPOUNDING');


--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_equipment_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_id_seq', 16, true);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_manufacturing_procedure_id_seq', 1, false);


--
-- Data for Name: manufacturing_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO manufacturing_procedure VALUES (56);
INSERT INTO manufacturing_procedure VALUES (57);


--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('manufacturing_procedure_id_seq', 4, true);


--
-- Data for Name: mbr; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Name: mbr_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('mbr_id_seq', 17, true);


--
-- Data for Name: packaging_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_material_requirement VALUES (39, 8, 1, 9, 56);
INSERT INTO packaging_material_requirement VALUES (40, 8, 1, 9, 57);


--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_requirement_id_seq', 40, true);


--
-- Data for Name: packaging_operation; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_operation VALUES (19, 1, 'packaging operation 1', 56, 1, '', '');
INSERT INTO packaging_operation VALUES (20, 1, 'Room and Equipment Clearance
Perform room/line clearance check fir each of the following areas:
	-Label/Packaging Room', 57, 1, '', '');


--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_id_seq', 4, true);


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_operation_id_seq', 20, true);


--
-- Data for Name: powder_filling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Data for Name: primary_secondary_packaging; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO primary_secondary_packaging VALUES (56, 39, 39);
INSERT INTO primary_secondary_packaging VALUES (57, 40, 40);


--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('primary_secondary_packaging_id_seq', 1, true);


--
-- Data for Name: raw_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO raw_material_requirement VALUES (47, 57, 333, 5, 56, 0);
INSERT INTO raw_material_requirement VALUES (48, 57, 313, 5, 56, 2);
INSERT INTO raw_material_requirement VALUES (49, 57, 333, 5, 57, 1);
INSERT INTO raw_material_requirement VALUES (50, 57, 317, 5, 57, 2);


--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_requirement_id_seq', 50, true);


--
-- Data for Name: udf; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO udf VALUES (56, 1, 2);
INSERT INTO udf VALUES (57, 1, 6);


--
-- Name: udf_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('udf_id_seq', 2, true);


SET search_path = audit, pg_catalog;

--
-- Name: audit_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY audit
    ADD CONSTRAINT audit_pkey PRIMARY KEY (id);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user_username_key; Type: CONSTRAINT; Schema: audit; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_username_key UNIQUE (username);


SET search_path = main, pg_catalog;

--
-- Name: area_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY area
    ADD CONSTRAINT area_pkey PRIMARY KEY (id);


--
-- Name: classification_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY classification
    ADD CONSTRAINT classification_pkey PRIMARY KEY (id);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: container_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY container
    ADD CONSTRAINT container_pkey PRIMARY KEY (id);


--
-- Name: equipment_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (id);


--
-- Name: pack_size_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pack_size
    ADD CONSTRAINT pack_size_pkey PRIMARY KEY (id);


--
-- Name: packaging_material_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY packaging_material
    ADD CONSTRAINT packaging_material_pkey PRIMARY KEY (id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: raw_material_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY raw_material
    ADD CONSTRAINT raw_material_pkey PRIMARY KEY (id);


--
-- Name: unit_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (id);


SET search_path = mbr, pg_catalog;

--
-- Name: bottling_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bottling_procedure
    ADD CONSTRAINT bottling_procedure_pkey PRIMARY KEY (id);


--
-- Name: compounding_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY compounding_procedure
    ADD CONSTRAINT compounding_procedure_pkey PRIMARY KEY (id);


--
-- Name: dosage_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dosage
    ADD CONSTRAINT dosage_pkey PRIMARY KEY (id);


--
-- Name: equipment_requirement_coding_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipment_requirement
    ADD CONSTRAINT equipment_requirement_coding_pkey PRIMARY KEY (id);


--
-- Name: manufacturing_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY manufacturing_procedure
    ADD CONSTRAINT manufacturing_procedure_pkey PRIMARY KEY (id);


--
-- Name: mbr_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mbr
    ADD CONSTRAINT mbr_pkey PRIMARY KEY (id);


--
-- Name: packaging_material_requirement_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY packaging_material_requirement
    ADD CONSTRAINT packaging_material_requirement_pkey PRIMARY KEY (id);


--
-- Name: packaging_procedure_operation_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY packaging_operation
    ADD CONSTRAINT packaging_procedure_operation_pkey PRIMARY KEY (id);


--
-- Name: packaging_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY powder_filling_procedure
    ADD CONSTRAINT packaging_procedure_pkey PRIMARY KEY (id);


--
-- Name: primary_secondary_packaging_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_pkey PRIMARY KEY (id);


--
-- Name: primary_secondary_packaging_primary_packaging_id_key; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_primary_packaging_id_key UNIQUE (primary_packaging_id);


--
-- Name: primary_secondary_packaging_secondary_packaging_id_key; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_secondary_packaging_id_key UNIQUE (secondary_packaging_id);


--
-- Name: raw_material_requirement_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY raw_material_requirement
    ADD CONSTRAINT raw_material_requirement_pkey PRIMARY KEY (id);


--
-- Name: udf_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY udf
    ADD CONSTRAINT udf_pkey PRIMARY KEY (id);


SET search_path = audit, pg_catalog;

--
-- Name: audit_user_id_fkey; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit
    ADD CONSTRAINT audit_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


SET search_path = main, pg_catalog;

--
-- Name: pack_size_container_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY pack_size
    ADD CONSTRAINT pack_size_container_id_fkey FOREIGN KEY (container_id) REFERENCES container(id);


--
-- Name: pack_size_unit_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY pack_size
    ADD CONSTRAINT pack_size_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES unit(id);


--
-- Name: packaging_material_client_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY packaging_material
    ADD CONSTRAINT packaging_material_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: product_area_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_area_id_fkey FOREIGN KEY (area_id) REFERENCES area(id);


--
-- Name: product_classification_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_classification_id_fkey FOREIGN KEY (classification_id) REFERENCES classification(id);


--
-- Name: product_client_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: product_pack_size_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pack_size_id_fkey FOREIGN KEY (pack_size_id) REFERENCES pack_size(id);


--
-- Name: raw_material_classification_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY raw_material
    ADD CONSTRAINT raw_material_classification_id_fkey FOREIGN KEY (classification_id) REFERENCES classification(id);


--
-- Name: raw_material_client_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY raw_material
    ADD CONSTRAINT raw_material_client_id_fkey FOREIGN KEY (client_id) REFERENCES client(id);


SET search_path = mbr, pg_catalog;

--
-- Name: bottling_procedure_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY bottling_procedure
    ADD CONSTRAINT bottling_procedure_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: compounding_procedure_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY compounding_procedure
    ADD CONSTRAINT compounding_procedure_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: dosage_compounding_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY dosage
    ADD CONSTRAINT dosage_compounding_procedure_id_fkey FOREIGN KEY (compounding_procedure_id) REFERENCES compounding_procedure(id);


--
-- Name: dosage_raw_material_requirement_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY dosage
    ADD CONSTRAINT dosage_raw_material_requirement_id_fkey FOREIGN KEY (raw_material_requirement_id) REFERENCES raw_material_requirement(id);


--
-- Name: equipment_requirement_coding_equipment_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement
    ADD CONSTRAINT equipment_requirement_coding_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES main.equipment(id);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement
    ADD CONSTRAINT equipment_requirement_coding_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY manufacturing_procedure
    ADD CONSTRAINT manufacturing_procedure_id_fkey FOREIGN KEY (id) REFERENCES main.product(id);


--
-- Name: mbr_product_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY mbr
    ADD CONSTRAINT mbr_product_id_fkey FOREIGN KEY (product_id) REFERENCES main.product(id);


--
-- Name: mbr_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY mbr
    ADD CONSTRAINT mbr_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


--
-- Name: packaging_material_requirement_packaging_material_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_material_requirement
    ADD CONSTRAINT packaging_material_requirement_packaging_material_id_fkey FOREIGN KEY (packaging_material_id) REFERENCES main.packaging_material(id);


--
-- Name: packaging_material_requirement_udf_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_material_requirement
    ADD CONSTRAINT packaging_material_requirement_udf_id_fkey FOREIGN KEY (udf_id) REFERENCES udf(id);


--
-- Name: packaging_material_requirement_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_material_requirement
    ADD CONSTRAINT packaging_material_requirement_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


--
-- Name: packaging_procedure_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY powder_filling_procedure
    ADD CONSTRAINT packaging_procedure_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: packaging_procedure_operation_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_operation
    ADD CONSTRAINT packaging_procedure_operation_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: primary_secondary_packaging_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_id_fkey FOREIGN KEY (id) REFERENCES main.product(id);


--
-- Name: primary_secondary_packaging_primary_packaging_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_primary_packaging_id_fkey FOREIGN KEY (primary_packaging_id) REFERENCES packaging_material_requirement(id);


--
-- Name: primary_secondary_packaging_secondary_packaging_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_secondary_packaging_id_fkey FOREIGN KEY (secondary_packaging_id) REFERENCES packaging_material_requirement(id);


--
-- Name: raw_material_requirement_raw_material_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement
    ADD CONSTRAINT raw_material_requirement_raw_material_id_fkey FOREIGN KEY (raw_material_id) REFERENCES main.raw_material(id);


--
-- Name: raw_material_requirement_udf_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement
    ADD CONSTRAINT raw_material_requirement_udf_id_fkey FOREIGN KEY (udf_id) REFERENCES udf(id);


--
-- Name: raw_material_requirement_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement
    ADD CONSTRAINT raw_material_requirement_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


--
-- Name: udf_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY udf
    ADD CONSTRAINT udf_id_fkey FOREIGN KEY (id) REFERENCES main.product(id);


--
-- Name: udf_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY udf
    ADD CONSTRAINT udf_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


--
-- Name: main; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA main FROM PUBLIC;
REVOKE ALL ON SCHEMA main FROM postgres;
GRANT ALL ON SCHEMA main TO postgres;
GRANT ALL ON SCHEMA main TO PUBLIC;


--
-- PostgreSQL database dump complete
--

