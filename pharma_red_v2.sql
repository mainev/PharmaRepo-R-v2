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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = main, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

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
    quantity smallint,
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
    quantity double precision,
    unit_id smallint,
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
-- Name: equipment_requirement_coding; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment_requirement_coding (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    equipment_id smallint
);


ALTER TABLE mbr.equipment_requirement_coding OWNER TO postgres;

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

ALTER SEQUENCE equipment_requirement_coding_equipment_id_seq OWNED BY equipment_requirement_coding.equipment_id;


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

ALTER SEQUENCE equipment_requirement_coding_id_seq OWNED BY equipment_requirement_coding.id;


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

ALTER SEQUENCE equipment_requirement_coding_manufacturing_procedure_id_seq OWNED BY equipment_requirement_coding.manufacturing_procedure_id;


--
-- Name: equipment_requirement_compounding; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment_requirement_compounding (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    equipment_id smallint
);


ALTER TABLE mbr.equipment_requirement_compounding OWNER TO postgres;

--
-- Name: equipment_requirement_compounding_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_compounding_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_compounding_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_compounding_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_compounding_id_seq OWNED BY equipment_requirement_compounding.id;


--
-- Name: equipment_requirement_encapsulation; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment_requirement_encapsulation (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    equipment_id smallint
);


ALTER TABLE mbr.equipment_requirement_encapsulation OWNER TO postgres;

--
-- Name: equipment_requirement_encapsulation_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_encapsulation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_encapsulation_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_encapsulation_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_encapsulation_id_seq OWNED BY equipment_requirement_encapsulation.id;


--
-- Name: equipment_requirement_packaging_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE equipment_requirement_packaging_procedure (
    id integer NOT NULL,
    manufacturing_procedure_id integer,
    equipment_id smallint
);


ALTER TABLE mbr.equipment_requirement_packaging_procedure OWNER TO postgres;

--
-- Name: equipment_requirement_packaging_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE equipment_requirement_packaging_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.equipment_requirement_packaging_procedure_id_seq OWNER TO postgres;

--
-- Name: equipment_requirement_packaging_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE equipment_requirement_packaging_procedure_id_seq OWNED BY equipment_requirement_packaging_procedure.id;


--
-- Name: manufacturing_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE manufacturing_procedure (
    id integer NOT NULL,
    product_id integer,
    is_active boolean
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
-- Name: packaging_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_procedure (
    id integer NOT NULL,
    step_number smallint,
    header character varying(200),
    manufacturing_procedure_id integer
);


ALTER TABLE mbr.packaging_procedure OWNER TO postgres;

--
-- Name: TABLE packaging_procedure; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE packaging_procedure IS 'packaging procedure for filling process';


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

ALTER SEQUENCE packaging_procedure_id_seq OWNED BY packaging_procedure.id;


--
-- Name: packaging_procedure_operation; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE packaging_procedure_operation (
    id integer NOT NULL,
    step_number smallint,
    header character varying(500),
    manufacturing_procedure_id integer
);


ALTER TABLE mbr.packaging_procedure_operation OWNER TO postgres;

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

ALTER SEQUENCE packaging_procedure_operation_id_seq OWNED BY packaging_procedure_operation.id;


--
-- Name: primary_secondary_packaging; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE primary_secondary_packaging (
    id integer NOT NULL,
    primary_packaging_id integer,
    secondary_packaging_id integer,
    product_id integer
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
    udf_id integer
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
    unit_id smallint,
    product_id integer,
    is_active boolean
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

ALTER TABLE ONLY compounding_procedure ALTER COLUMN id SET DEFAULT nextval('compounding_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY dosage ALTER COLUMN id SET DEFAULT nextval('dosage_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_coding ALTER COLUMN id SET DEFAULT nextval('equipment_requirement_coding_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_compounding ALTER COLUMN id SET DEFAULT nextval('equipment_requirement_compounding_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_encapsulation ALTER COLUMN id SET DEFAULT nextval('equipment_requirement_encapsulation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_packaging_procedure ALTER COLUMN id SET DEFAULT nextval('equipment_requirement_packaging_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY manufacturing_procedure ALTER COLUMN id SET DEFAULT nextval('manufacturing_procedure_id_seq'::regclass);


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

ALTER TABLE ONLY packaging_procedure ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_procedure_operation ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_operation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging ALTER COLUMN id SET DEFAULT nextval('primary_secondary_packaging_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement ALTER COLUMN id SET DEFAULT nextval('raw_material_requirement_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY udf ALTER COLUMN id SET DEFAULT nextval('udf_id_seq'::regclass);


SET search_path = main, pg_catalog;

--
-- Data for Name: area; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO area VALUES (1, 'LIQUID VET');
INSERT INTO area VALUES (2, 'LIQUID HUMAN');
INSERT INTO area VALUES (3, 'POWDER AREA');
INSERT INTO area VALUES (4, 'TABLET HUMAN');
INSERT INTO area VALUES (5, 'TABLET VET');


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 5, true);


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

INSERT INTO pack_size VALUES (1, 30, 10, 1);


--
-- Name: pack_size_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('pack_size_id_seq', 1, true);


--
-- Data for Name: packaging_material; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO packaging_material VALUES (1, 'pm1', 'HDPE Bottle with cap, (Glutathione)', 2);
INSERT INTO packaging_material VALUES (2, 'pm2', 'Paper Lean', 2);
INSERT INTO packaging_material VALUES (3, 'pm3', 'Moisture Adsorbent (0.50g)', 2);
INSERT INTO packaging_material VALUES (4, 'pm4', 'Labels', 2);
INSERT INTO packaging_material VALUES (5, 'pm5', 'Corrugated Box', 2);
INSERT INTO packaging_material VALUES (6, 'pm6', 'Packaging Tape (PLAIN)', 2);
INSERT INTO packaging_material VALUES (7, 'pm7', 'Silica Gels (100g)', 2);


--
-- Name: packaging_material_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_id_seq', 7, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO product VALUES (1, 'P25', 'NUDERM ADVANCE', 'L-Glutathione+ALA+Collagen+Vit.C 650mg/150mg/50mg.', 3, 2, 'N/A', 2, 4, 1);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 2, true);


--
-- Data for Name: raw_material; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO raw_material VALUES (1, '1', 'L-Glutathione Powder', 'White crystalline powder.', 2, 2);
INSERT INTO raw_material VALUES (2, '2', 'Alpha-lipoic Acid', 'Yellow crystalline powder.', 2, 2);
INSERT INTO raw_material VALUES (3, '3', 'Collagen', 'White or light yellow powder.', 2, 2);
INSERT INTO raw_material VALUES (4, '4', 'Vitamin C (Coated)', 'White or slightly yellow crystals or powder gradu...', 2, 2);
INSERT INTO raw_material VALUES (5, '5', 'Hard Empty Gelatin Capsule #00 (White/white)', NULL, 3, 2);


--
-- Name: raw_material_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_id_seq', 5, true);


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
-- Data for Name: compounding_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO compounding_procedure VALUES (1, 1, 'Ensure all the equipment, including manufacturing area cleaned and cleared from all traces of the previous batch tree from moisture.', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (2, 2, 'Monitor RH (NMT 50%) and Temperature (NMT 25°C) every 15 minutes. Fill up form NF-QA-011-01-01.', false, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (3, 3, 'Passing thru mesh #20 load Collage, Alpha-lipoic Acid and Vitamin C coated into the paddle mixer and mix for 3 minutes.', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (4, 4, 'Add 1st part of L-Glutathione Powder to the bulk passing thru mesh #20 and mix for 5 minutes.', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (5, 5, 'Load 2nd part of L-Glutathione Powder to the bulk passing thru mesh #20 and mix for 30 minutes.', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (6, 6, 'QC to collect samples from the bulk for In-Process Analysis. 
   a. Homogeneity Test
       -Top: 10g
       -Middle: 10g
       -Bottom: 10g
   b. Bulk Density: 10g
   c. Moisture Content: 10g
   d. Microbial Test: 5g', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (7, 7, 'Compute for the yield of the bulk.
   Theoretical Weight: _____
   Actual Weight: _____
   QC Samples: 55 g
   %Recovery: _____', true, NULL, NULL, 1);
INSERT INTO compounding_procedure VALUES (8, 8, 'Store the bulk in a double-lined PE bag with silica gel in between PE bags and place inside the blue drum. Attach quarantine sticker and store in a room with controlled temperature (NMT 25°C) while waiting for QC disposition for encapsulation.', true, NULL, NULL, 1);


--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('compounding_procedure_id_seq', 8, true);


--
-- Data for Name: dosage; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO dosage VALUES (1, 4, NULL, NULL, 1, 3);
INSERT INTO dosage VALUES (2, 3, NULL, NULL, 1, 3);
INSERT INTO dosage VALUES (3, 6, NULL, NULL, 1, 3);
INSERT INTO dosage VALUES (4, 1, NULL, NULL, 1, 4);
INSERT INTO dosage VALUES (5, 2, NULL, NULL, 1, 5);


--
-- Name: dosage_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('dosage_id_seq', 5, true);


--
-- Data for Name: equipment_requirement_coding; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_equipment_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_manufacturing_procedure_id_seq', 1, false);


--
-- Data for Name: equipment_requirement_compounding; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO equipment_requirement_compounding VALUES (1, 1, 1);
INSERT INTO equipment_requirement_compounding VALUES (2, 1, 3);
INSERT INTO equipment_requirement_compounding VALUES (3, 1, 4);
INSERT INTO equipment_requirement_compounding VALUES (4, 1, 5);
INSERT INTO equipment_requirement_compounding VALUES (5, 1, 6);
INSERT INTO equipment_requirement_compounding VALUES (6, 1, 7);


--
-- Name: equipment_requirement_compounding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_compounding_id_seq', 6, true);


--
-- Data for Name: equipment_requirement_encapsulation; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO equipment_requirement_encapsulation VALUES (1, 1, 2);
INSERT INTO equipment_requirement_encapsulation VALUES (2, 1, 8);


--
-- Name: equipment_requirement_encapsulation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_encapsulation_id_seq', 2, true);


--
-- Data for Name: equipment_requirement_packaging_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Name: equipment_requirement_packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_packaging_procedure_id_seq', 1, false);


--
-- Data for Name: manufacturing_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO manufacturing_procedure VALUES (1, 1, true);


--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('manufacturing_procedure_id_seq', 1, true);


--
-- Data for Name: mbr; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO mbr VALUES (16, 1, 30, 'batch1', 7, '2015-06-27', '2017-06-27', 'hjklop');
INSERT INTO mbr VALUES (17, 1, 90, 'batch1', 7, '2015-06-27', '2017-06-27', '90');


--
-- Name: mbr_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('mbr_id_seq', 17, true);


--
-- Data for Name: packaging_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_material_requirement VALUES (1, 1, 34, 9, 1);
INSERT INTO packaging_material_requirement VALUES (2, 6, 0.16700000000000001, 8, 1);
INSERT INTO packaging_material_requirement VALUES (3, 2, 34, 9, 1);
INSERT INTO packaging_material_requirement VALUES (4, 3, 34, 9, 1);
INSERT INTO packaging_material_requirement VALUES (5, 5, 0.67000000000000004, 11, 1);
INSERT INTO packaging_material_requirement VALUES (6, 4, 34.670000000000002, 9, 1);
INSERT INTO packaging_material_requirement VALUES (7, 7, 20, 9, 1);


--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_requirement_id_seq', 7, true);


--
-- Data for Name: packaging_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_id_seq', 1, false);


--
-- Data for Name: packaging_procedure_operation; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_procedure_operation VALUES (1, 1, 'Room and Equipment Clearance
 Perform room/line clearance check for each of the following areas:
Labeling/Packaging Room', 1);
INSERT INTO packaging_procedure_operation VALUES (2, 2, 'Check the quantity if Filled Bottles.
Total Quantity of Filled Bottles: ____________pcs', 1);
INSERT INTO packaging_procedure_operation VALUES (3, 3, 'Withdraw packaging materials/coded labels from the staging area for labeling and final packaging.
Coded Labels: _______________
Corrugated Box:______________
Packaging Tape:______________', 1);
INSERT INTO packaging_procedure_operation VALUES (4, 4, 'Check the completeness of requested packaging materials before using.
Requested materials:
Coded Labels:______________
Corrugated Box:_______________
Packaging Tape:_______________', 1);


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_operation_id_seq', 4, true);


--
-- Data for Name: primary_secondary_packaging; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO primary_secondary_packaging VALUES (1, 1, 5, 1);


--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('primary_secondary_packaging_id_seq', 1, true);


--
-- Data for Name: raw_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO raw_material_requirement VALUES (1, 1, 333, 5, 1);
INSERT INTO raw_material_requirement VALUES (2, 1, 317, 5, 1);
INSERT INTO raw_material_requirement VALUES (3, 2, 150, 5, 1);
INSERT INTO raw_material_requirement VALUES (4, 3, 50, 5, 1);
INSERT INTO raw_material_requirement VALUES (5, 4, 150, 5, 1);
INSERT INTO raw_material_requirement VALUES (6, 5, 1, 9, 1);


--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_requirement_id_seq', 6, true);


--
-- Data for Name: udf; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO udf VALUES (1, 1, 6, 1, true);


--
-- Name: udf_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('udf_id_seq', 1, true);


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

ALTER TABLE ONLY equipment_requirement_coding
    ADD CONSTRAINT equipment_requirement_coding_pkey PRIMARY KEY (id);


--
-- Name: equipment_requirement_compounding_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipment_requirement_compounding
    ADD CONSTRAINT equipment_requirement_compounding_pkey PRIMARY KEY (id);


--
-- Name: equipment_requirement_encapsulation_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipment_requirement_encapsulation
    ADD CONSTRAINT equipment_requirement_encapsulation_pkey PRIMARY KEY (id);


--
-- Name: equipment_requirement_packaging_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY equipment_requirement_packaging_procedure
    ADD CONSTRAINT equipment_requirement_packaging_procedure_pkey PRIMARY KEY (id);


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

ALTER TABLE ONLY packaging_procedure_operation
    ADD CONSTRAINT packaging_procedure_operation_pkey PRIMARY KEY (id);


--
-- Name: packaging_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY packaging_procedure
    ADD CONSTRAINT packaging_procedure_pkey PRIMARY KEY (id);


--
-- Name: primary_secondary_packaging_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_pkey PRIMARY KEY (id);


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
-- Name: dosage_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY dosage
    ADD CONSTRAINT dosage_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


--
-- Name: equipment_requirement_coding_equipment_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_coding
    ADD CONSTRAINT equipment_requirement_coding_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES main.equipment(id);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_coding
    ADD CONSTRAINT equipment_requirement_coding_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: equipment_requirement_compoundi_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_compounding
    ADD CONSTRAINT equipment_requirement_compoundi_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: equipment_requirement_compounding_equipment_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_compounding
    ADD CONSTRAINT equipment_requirement_compounding_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES main.equipment(id);


--
-- Name: equipment_requirement_encapsula_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_encapsulation
    ADD CONSTRAINT equipment_requirement_encapsula_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: equipment_requirement_encapsulation_equipment_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_encapsulation
    ADD CONSTRAINT equipment_requirement_encapsulation_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES main.equipment(id);


--
-- Name: equipment_requirement_packaging_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_packaging_procedure
    ADD CONSTRAINT equipment_requirement_packaging_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: equipment_requirement_packaging_procedure_equipment_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY equipment_requirement_packaging_procedure
    ADD CONSTRAINT equipment_requirement_packaging_procedure_equipment_id_fkey FOREIGN KEY (equipment_id) REFERENCES main.equipment(id);


--
-- Name: manufacturing_procedure_product_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY manufacturing_procedure
    ADD CONSTRAINT manufacturing_procedure_product_id_fkey FOREIGN KEY (product_id) REFERENCES main.product(id);


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

ALTER TABLE ONLY packaging_procedure
    ADD CONSTRAINT packaging_procedure_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: packaging_procedure_operation_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_procedure_operation
    ADD CONSTRAINT packaging_procedure_operation_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: primary_secondary_packaging_primary_packaging_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_primary_packaging_id_fkey FOREIGN KEY (primary_packaging_id) REFERENCES main.packaging_material(id);


--
-- Name: primary_secondary_packaging_product_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_product_id_fkey FOREIGN KEY (product_id) REFERENCES main.product(id);


--
-- Name: primary_secondary_packaging_secondary_packaging_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY primary_secondary_packaging
    ADD CONSTRAINT primary_secondary_packaging_secondary_packaging_id_fkey FOREIGN KEY (secondary_packaging_id) REFERENCES main.packaging_material(id);


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
-- Name: udf_product_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY udf
    ADD CONSTRAINT udf_product_id_fkey FOREIGN KEY (product_id) REFERENCES main.product(id);


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

