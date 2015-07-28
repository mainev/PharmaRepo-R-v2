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
-- Name: transaction; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA transaction;


ALTER SCHEMA transaction OWNER TO postgres;

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
    name character varying(50),
    code character varying(5)
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
    po_no character varying(15),
    status character varying(20)
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


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Name: company; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE company (
    id smallint NOT NULL,
    code character varying(5) NOT NULL,
    descs character varying(100)
);


ALTER TABLE sqlsvr_copy.company OWNER TO postgres;

--
-- Name: company_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.company_id_seq OWNER TO postgres;

--
-- Name: company_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE company_id_seq OWNED BY company.id;


--
-- Name: item; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE item (
    id integer NOT NULL,
    descs character varying(255),
    item_category_id smallint,
    item_type_id smallint,
    remarks character varying(255),
    item_cd character varying(20)
);


ALTER TABLE sqlsvr_copy.item OWNER TO postgres;

--
-- Name: item_category; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE item_category (
    id smallint NOT NULL,
    code character varying(10) NOT NULL,
    descs character varying(255),
    item_class_id smallint
);


ALTER TABLE sqlsvr_copy.item_category OWNER TO postgres;

--
-- Name: item_category_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE item_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.item_category_id_seq OWNER TO postgres;

--
-- Name: item_category_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE item_category_id_seq OWNED BY item_category.id;


--
-- Name: item_class; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE item_class (
    id smallint NOT NULL,
    code character varying(10) NOT NULL,
    descs character varying(255)
);


ALTER TABLE sqlsvr_copy.item_class OWNER TO postgres;

--
-- Name: item_class_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE item_class_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.item_class_id_seq OWNER TO postgres;

--
-- Name: item_class_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE item_class_id_seq OWNED BY item_class.id;


--
-- Name: item_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.item_id_seq OWNER TO postgres;

--
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE item_id_seq OWNED BY item.id;


--
-- Name: item_type; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE item_type (
    id smallint NOT NULL,
    code character varying(10) NOT NULL,
    descs character varying(255)
);


ALTER TABLE sqlsvr_copy.item_type OWNER TO postgres;

--
-- Name: item_type_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE item_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.item_type_id_seq OWNER TO postgres;

--
-- Name: item_type_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE item_type_id_seq OWNED BY item_type.id;


--
-- Name: stock_card; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE stock_card (
    id integer NOT NULL,
    company_id smallint,
    warehouse_id smallint,
    inout_mode character varying(1),
    item_id integer,
    unit_cost double precision,
    qty double precision,
    lot_no character varying(100),
    mfg_date date,
    exp_date date,
    control_no character varying(20),
    status character varying(10),
    uom character varying(10),
    stock_status character varying(10)
);


ALTER TABLE sqlsvr_copy.stock_card OWNER TO postgres;

--
-- Name: stock_card_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE stock_card_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.stock_card_id_seq OWNER TO postgres;

--
-- Name: stock_card_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE stock_card_id_seq OWNED BY stock_card.id;


--
-- Name: warehouse; Type: TABLE; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

CREATE TABLE warehouse (
    id smallint NOT NULL,
    code character varying(10) NOT NULL,
    descs character varying(255)
);


ALTER TABLE sqlsvr_copy.warehouse OWNER TO postgres;

--
-- Name: warehouse_id_seq; Type: SEQUENCE; Schema: sqlsvr_copy; Owner: postgres
--

CREATE SEQUENCE warehouse_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sqlsvr_copy.warehouse_id_seq OWNER TO postgres;

--
-- Name: warehouse_id_seq; Type: SEQUENCE OWNED BY; Schema: sqlsvr_copy; Owner: postgres
--

ALTER SEQUENCE warehouse_id_seq OWNED BY warehouse.id;


SET search_path = transaction, pg_catalog;

--
-- Name: stock_card_txn; Type: TABLE; Schema: transaction; Owner: postgres; Tablespace: 
--

CREATE TABLE stock_card_txn (
    id integer NOT NULL,
    stock_card_id integer,
    qty double precision,
    unit_id smallint,
    mbr_id integer
);


ALTER TABLE transaction.stock_card_txn OWNER TO postgres;

--
-- Name: stock_card_txn_id_seq; Type: SEQUENCE; Schema: transaction; Owner: postgres
--

CREATE SEQUENCE stock_card_txn_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transaction.stock_card_txn_id_seq OWNER TO postgres;

--
-- Name: stock_card_txn_id_seq; Type: SEQUENCE OWNED BY; Schema: transaction; Owner: postgres
--

ALTER SEQUENCE stock_card_txn_id_seq OWNED BY stock_card_txn.id;


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


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY company ALTER COLUMN id SET DEFAULT nextval('company_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item_category ALTER COLUMN id SET DEFAULT nextval('item_category_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item_class ALTER COLUMN id SET DEFAULT nextval('item_class_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item_type ALTER COLUMN id SET DEFAULT nextval('item_type_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY stock_card ALTER COLUMN id SET DEFAULT nextval('stock_card_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY warehouse ALTER COLUMN id SET DEFAULT nextval('warehouse_id_seq'::regclass);


SET search_path = transaction, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn ALTER COLUMN id SET DEFAULT nextval('stock_card_txn_id_seq'::regclass);


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
INSERT INTO area VALUES (7, 'CHP');
INSERT INTO area VALUES (8, 'PARENTERAL');


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 8, true);


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

INSERT INTO client VALUES (1, 'VACCINE', '01');
INSERT INTO client VALUES (2, 'APT-FIGHT', '02');
INSERT INTO client VALUES (3, 'PRO-BIOTICS', '03');
INSERT INTO client VALUES (4, 'APT-HEALTH', '04');
INSERT INTO client VALUES (5, 'BIOCARE(PET CARE)', '05');
INSERT INTO client VALUES (6, 'NUTRATECH', '07');
INSERT INTO client VALUES (7, '100 TDB', '08');


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('client_id_seq', 7, true);


--
-- Data for Name: container; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO container VALUES (1, 'Bottle');
INSERT INTO container VALUES (3, 'Pail');
INSERT INTO container VALUES (2, 'Kraft bag');
INSERT INTO container VALUES (4, 'Gallon');


--
-- Name: container_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('container_id_seq', 4, true);


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
INSERT INTO pack_size VALUES (32, 1, 3, 1);
INSERT INTO pack_size VALUES (33, 20, 7, 1);
INSERT INTO pack_size VALUES (34, 20, 7, 2);
INSERT INTO pack_size VALUES (35, 4, 3, 4);
INSERT INTO pack_size VALUES (36, 56, 11, 4);


--
-- Name: pack_size_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('pack_size_id_seq', 36, true);


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
INSERT INTO product VALUES (58, '20', 'Laktamino', 'laktamino manasee', 1, 1, 'NF-AK-100', 3, 1, 32);
INSERT INTO product VALUES (59, '23', 'Enrofloxacin', 'Vit B, C E', 1, 1, 'FR-203-1K', 3, 1, 32);
INSERT INTO product VALUES (60, '25', 'Norfloxacin', 'Mega Vit A, B and Zinc', 2, 3, 'FR-540-TR0', 3, 3, 33);
INSERT INTO product VALUES (61, '26', 'Cattleya', 'Vit B, C, D, and E', 2, 3, 'FR-A02-RD', 3, 3, 34);
INSERT INTO product VALUES (62, '30', 'GLUTASEP', 'Glutaraldehyde 50% + Benzalkonium Chloride 80%', 2, 3, 'VRM-14-224', 3, 1, 35);
INSERT INTO product VALUES (63, 'p09', 'brand v', 'gen v', 1, 3, 'vr 5', 4, 6, 31);
INSERT INTO product VALUES (64, 'huj', 'bb', 'bb', 2, 2, 'bb', 5, 3, 36);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 64, true);


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
INSERT INTO compounding_procedure VALUES (26, 1, 'Open the Nitrogen Gas before loading the vitamins and mix Polyoxyl 40 Hydrogenated Castor Oil and Tween 80. Add gradually to the bulk with continuous mixing.', true, NULL, NULL, 58);
INSERT INTO compounding_procedure VALUES (27, 1, 'Heat the Eucalyptol 99%  at 65 Degree Celsius. add Calcium Carbonate Light.', false, NULL, NULL, 59);
INSERT INTO compounding_procedure VALUES (28, 1, 'This is the first compounding procedure .', true, NULL, NULL, 60);
INSERT INTO compounding_procedure VALUES (29, 1, 'asdasda', false, NULL, NULL, 61);
INSERT INTO compounding_procedure VALUES (30, 1, 'first compounding procedure..', true, NULL, NULL, 62);
INSERT INTO compounding_procedure VALUES (31, 1, 'content 1', true, NULL, NULL, 63);
INSERT INTO compounding_procedure VALUES (32, 1, 'gtgtgtg', true, NULL, NULL, 64);


--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('compounding_procedure_id_seq', 32, true);


--
-- Data for Name: dosage; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO dosage VALUES (21, 47, 1, 24);
INSERT INTO dosage VALUES (22, 51, 1, 26);
INSERT INTO dosage VALUES (23, 52, 1, 26);
INSERT INTO dosage VALUES (24, 63, 1, 27);
INSERT INTO dosage VALUES (25, 64, 1, 27);
INSERT INTO dosage VALUES (26, 65, 1, 28);
INSERT INTO dosage VALUES (27, 66, 0.10000000000000001, 28);
INSERT INTO dosage VALUES (28, 67, 1, 29);
INSERT INTO dosage VALUES (29, 68, 1, 29);
INSERT INTO dosage VALUES (30, 70, 1, 30);
INSERT INTO dosage VALUES (31, 74, 1, 31);


--
-- Name: dosage_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('dosage_id_seq', 31, true);


--
-- Data for Name: equipment_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO equipment_requirement VALUES (15, 56, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (16, 57, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (17, 58, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (18, 59, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (19, 60, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (20, 60, 2, 'ENCAPSULATION');
INSERT INTO equipment_requirement VALUES (21, 61, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (22, 62, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (23, 63, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (24, 64, 1, 'ENCAPSULATION');


--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_equipment_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_id_seq', 24, true);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_manufacturing_procedure_id_seq', 1, false);


--
-- Data for Name: manufacturing_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO manufacturing_procedure VALUES (56);
INSERT INTO manufacturing_procedure VALUES (57);
INSERT INTO manufacturing_procedure VALUES (58);
INSERT INTO manufacturing_procedure VALUES (59);
INSERT INTO manufacturing_procedure VALUES (60);
INSERT INTO manufacturing_procedure VALUES (61);
INSERT INTO manufacturing_procedure VALUES (62);
INSERT INTO manufacturing_procedure VALUES (63);
INSERT INTO manufacturing_procedure VALUES (64);


--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('manufacturing_procedure_id_seq', 4, true);


--
-- Data for Name: mbr; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO mbr VALUES (74, 58, 1000, 'batch1', 3, '2015-07-29', '2018-07-29', 'dded', 'PENDING');
INSERT INTO mbr VALUES (75, 59, 1500, 'batch1', 3, '2016-07-12', '2019-07-12', 'fsf', 'PENDING');
INSERT INTO mbr VALUES (73, 59, 1000, 'batch1', 3, '2015-07-25', '2018-07-25', 'ded', 'DISPENSED');


--
-- Name: mbr_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('mbr_id_seq', 75, true);


--
-- Data for Name: packaging_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_material_requirement VALUES (39, 8, 1, 9, 56);
INSERT INTO packaging_material_requirement VALUES (40, 8, 1, 9, 57);
INSERT INTO packaging_material_requirement VALUES (41, 8, 1, 9, 58);
INSERT INTO packaging_material_requirement VALUES (42, 8, 1, 9, 59);
INSERT INTO packaging_material_requirement VALUES (43, 8, 1, 9, 60);
INSERT INTO packaging_material_requirement VALUES (44, 8, 1, 3, 61);
INSERT INTO packaging_material_requirement VALUES (45, 8, 1, 9, 62);
INSERT INTO packaging_material_requirement VALUES (46, 8, 1, 9, 63);
INSERT INTO packaging_material_requirement VALUES (47, 8, 1, 7, 64);


--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_requirement_id_seq', 47, true);


--
-- Data for Name: packaging_operation; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_operation VALUES (19, 1, 'packaging operation 1', 56, 1, '', '');
INSERT INTO packaging_operation VALUES (20, 1, 'Room and Equipment Clearance
Perform room/line clearance check fir each of the following areas:
	-Label/Packaging Room', 57, 1, '', '');
INSERT INTO packaging_operation VALUES (21, 1, 'Room and Equipment Clearance
Perform room / line clearance check for each of the following areas:
Labelling/Packaging Room', 58, 2, '', '');
INSERT INTO packaging_operation VALUES (22, 2, 'Perform room / line clearance based in SOP
	-Liquid Vet Filling Area
	-Labelling and Packing Room', 58, 1, '', '');
INSERT INTO packaging_operation VALUES (23, 1, 'this is part 1 step 1 packaging procedure', 59, 1, '', '');
INSERT INTO packaging_operation VALUES (24, 2, 'this is part 1 step 1 packaging procedure', 59, 1, '_______________', '________________');
INSERT INTO packaging_operation VALUES (25, 3, 'this is part 1 step 1 packaging procedure', 59, 1, '_______________', '________________');
INSERT INTO packaging_operation VALUES (26, 4, 'this is part 1 step 1 packaging procedure', 59, 1, '_______________', '________________');
INSERT INTO packaging_operation VALUES (27, 5, 'this is part 2 step 1 packaging procedure', 59, 2, '_______________', '________________');
INSERT INTO packaging_operation VALUES (28, 1, 'This is the 1st part, 1st step packaging procedure', 60, 1, '', '');
INSERT INTO packaging_operation VALUES (29, 2, 'This is the 1st part, 2st step packaging procedure', 60, 1, '________________', '_________________');
INSERT INTO packaging_operation VALUES (30, 3, 'This is the 2nd part, 1st step packaging procedure', 60, 2, '________________', '_________________');
INSERT INTO packaging_operation VALUES (31, 4, 'This is the 2nd part, 2nd step packaging procedure', 60, 2, '________________', '_________________');
INSERT INTO packaging_operation VALUES (32, 1, 'asda', 61, 1, ' asd', 'asd');
INSERT INTO packaging_operation VALUES (33, 1, '1st packaging procedure', 62, 1, '-------------	', '------------------');
INSERT INTO packaging_operation VALUES (34, 1, 'pack opt 1', 63, 1, '', '');
INSERT INTO packaging_operation VALUES (35, 1, 'hybb', 64, 2, '', '');


--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_id_seq', 5, true);


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_operation_id_seq', 35, true);


--
-- Data for Name: powder_filling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO powder_filling_procedure VALUES (5, 1, 'bububububu', 63, true, '', '');


--
-- Data for Name: primary_secondary_packaging; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO primary_secondary_packaging VALUES (56, 39, 39);
INSERT INTO primary_secondary_packaging VALUES (57, 40, 40);
INSERT INTO primary_secondary_packaging VALUES (58, 41, 41);
INSERT INTO primary_secondary_packaging VALUES (59, 42, 42);
INSERT INTO primary_secondary_packaging VALUES (60, 43, 43);
INSERT INTO primary_secondary_packaging VALUES (61, 44, 44);
INSERT INTO primary_secondary_packaging VALUES (62, 45, 45);
INSERT INTO primary_secondary_packaging VALUES (63, 46, 46);
INSERT INTO primary_secondary_packaging VALUES (64, 47, 47);


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
INSERT INTO raw_material_requirement VALUES (51, 236, 750, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (52, 148, 63.170000000000002, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (53, 182, 0.25, 2, 58, 0);
INSERT INTO raw_material_requirement VALUES (54, 76, 10, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (55, 247, 5, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (56, 127, 2.5, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (57, 225, 55, 4, 58, 0);
INSERT INTO raw_material_requirement VALUES (58, 201, 12.5, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (59, 199, 10, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (60, 131, 110, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (61, 46, 61.600000000000001, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (62, 102, 0.5, 5, 58, 0);
INSERT INTO raw_material_requirement VALUES (63, 95, 23, 5, 59, 0);
INSERT INTO raw_material_requirement VALUES (64, 239, 45, 5, 59, 0);
INSERT INTO raw_material_requirement VALUES (65, 95, 25, 5, 60, 0);
INSERT INTO raw_material_requirement VALUES (66, 158, 10, 5, 60, 0);
INSERT INTO raw_material_requirement VALUES (67, 239, 20, 5, 61, 0);
INSERT INTO raw_material_requirement VALUES (68, 158, 45, 5, 61, 0);
INSERT INTO raw_material_requirement VALUES (69, 216, 20, 5, 61, 2);
INSERT INTO raw_material_requirement VALUES (70, 75, 150, 6, 62, 0);
INSERT INTO raw_material_requirement VALUES (71, 95, 1.5, 6, 62, 0);
INSERT INTO raw_material_requirement VALUES (72, 15, 115.425, 2, 62, 0);
INSERT INTO raw_material_requirement VALUES (73, 102, 1, 5, 62, 0);
INSERT INTO raw_material_requirement VALUES (74, 6, 100, 2, 63, 0);
INSERT INTO raw_material_requirement VALUES (75, 6, 56, 5, 64, 0);
INSERT INTO raw_material_requirement VALUES (76, 205, 10, 7, 58, 0);


--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_requirement_id_seq', 76, true);


--
-- Data for Name: udf; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO udf VALUES (56, 1, 2);
INSERT INTO udf VALUES (57, 1, 6);
INSERT INTO udf VALUES (58, 5, 2);
INSERT INTO udf VALUES (59, 5, 2);
INSERT INTO udf VALUES (60, 5, 5);
INSERT INTO udf VALUES (61, 5, 5);
INSERT INTO udf VALUES (62, 1, 3);
INSERT INTO udf VALUES (63, 5, 2);
INSERT INTO udf VALUES (64, 5, 11);


--
-- Name: udf_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('udf_id_seq', 2, true);


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Data for Name: company; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO company VALUES (1, '01', 'VACCINE');
INSERT INTO company VALUES (2, '08', '100 TDB');
INSERT INTO company VALUES (3, '03', 'PRO-BIOTICS');
INSERT INTO company VALUES (4, '07', 'NUTRATECH');
INSERT INTO company VALUES (5, '04', 'APT-HEALTH');
INSERT INTO company VALUES (6, '02', 'APT-FIGHT');
INSERT INTO company VALUES (7, '05', 'BIOCARE(PET CARE)');


--
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('company_id_seq', 7, true);


--
-- Data for Name: item; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO item VALUES (1, 'LIQUID GLUCOSE', 2, 1, NULL, '0001');
INSERT INTO item VALUES (2, 'CITRIC ACID', 2, 1, NULL, '0002');
INSERT INTO item VALUES (3, 'TRISODIUM PHOSPHATE', 2, 1, NULL, '0003');
INSERT INTO item VALUES (4, 'KAOLIN LIGHT', 2, 1, NULL, '0004');
INSERT INTO item VALUES (5, 'VITAMIN E ACETATE OIL', 2, 1, NULL, '0005');
INSERT INTO item VALUES (6, 'PROPYLENE GLYCOL', 2, 1, NULL, '0006');
INSERT INTO item VALUES (7, 'METHYL PARABEN', 2, 1, NULL, '0007');
INSERT INTO item VALUES (8, 'PROPYL PARABEN', 2, 1, NULL, '0008');
INSERT INTO item VALUES (9, 'CORNSTARCH', 2, 1, NULL, '0009');
INSERT INTO item VALUES (10, 'MAGNESIUM STEARATE', 2, 1, NULL, '0010');
INSERT INTO item VALUES (11, 'REFINED SUGAR', 2, 1, NULL, '0011');
INSERT INTO item VALUES (12, 'DRIER DRIED PEPPER', 2, 1, NULL, '0012');
INSERT INTO item VALUES (13, 'SUN DRIED PEPPER', 2, 1, NULL, '0013');
INSERT INTO item VALUES (14, 'TAURINE', 2, 1, NULL, '0014');
INSERT INTO item VALUES (15, 'BUTYLATED HYDROXYTOLUENE (BHT)', 2, 1, NULL, '0015');
INSERT INTO item VALUES (16, 'ZINC SULPHATE HEPTAHYDRATE', 2, 1, NULL, '0016');
INSERT INTO item VALUES (17, 'OPAORY PINK', 2, 1, NULL, '0017');
INSERT INTO item VALUES (18, 'OPAORY GREEN', 2, 1, NULL, '0018');
INSERT INTO item VALUES (19, 'OPAORY BLACK', 2, 1, NULL, '0019');
INSERT INTO item VALUES (20, 'OPAORY BROWN', 2, 1, NULL, '0020');
INSERT INTO item VALUES (21, 'OPAORY LIGHT GREEN', 2, 1, NULL, '0021');
INSERT INTO item VALUES (22, 'OPAORY WHITE', 2, 1, NULL, '0022');
INSERT INTO item VALUES (23, 'HYDROXOCOBALAMINE ACETATE', 2, 1, NULL, '0023');
INSERT INTO item VALUES (24, 'METHANDRIOL DIPROPIONATE', 2, 1, NULL, '0024');
INSERT INTO item VALUES (25, 'PREDNISOLONE ACETATE', 2, 1, NULL, '0025');
INSERT INTO item VALUES (26, 'SODIUM STARCH GLYCOLATE', 2, 1, NULL, '0026');
INSERT INTO item VALUES (27, 'NANDROLONE PHENYLPROPIONATE', 2, 1, NULL, '0027');
INSERT INTO item VALUES (28, 'ETHYL CELLULOSE', 2, 1, NULL, '0028');
INSERT INTO item VALUES (29, 'WHEAT GERM OIL', 2, 1, NULL, '0029');
INSERT INTO item VALUES (30, 'PIPERAZINE CITRATE', 2, 1, NULL, '0030');
INSERT INTO item VALUES (31, 'FUJICALIN', 2, 1, NULL, '0031');
INSERT INTO item VALUES (32, 'MORINGGA LEAVES POWDER', 2, 1, NULL, '0032');
INSERT INTO item VALUES (33, 'MUSCOVADO SUGAR', 2, 1, NULL, '0033');
INSERT INTO item VALUES (34, 'GREEN STEVIA/30G', 2, 1, NULL, '0034');
INSERT INTO item VALUES (35, 'SODIUM BENZOATE', 2, 1, NULL, '0035');
INSERT INTO item VALUES (36, 'SORBITOL', 2, 1, NULL, '0036');
INSERT INTO item VALUES (37, 'VITAMIN A ACETATE', 2, 1, NULL, '0037');
INSERT INTO item VALUES (38, 'VITAMIN B12 PURE', 2, 1, NULL, '0038');
INSERT INTO item VALUES (39, 'NICOTINAMIDE ', 2, 1, NULL, '0039');
INSERT INTO item VALUES (40, 'VITAMIN B1 HCL', 2, 1, NULL, '0040');
INSERT INTO item VALUES (41, 'VITAMIN C COATED ', 2, 1, NULL, '0041');
INSERT INTO item VALUES (42, 'SODIUM ASCORBATE', 2, 1, NULL, '0042');
INSERT INTO item VALUES (43, 'CALCIUM CARBONATE', 2, 1, NULL, '0043');
INSERT INTO item VALUES (44, 'CALCIUM LACTATE', 2, 1, NULL, '0044');
INSERT INTO item VALUES (45, 'CALCIUM D-PANTOTHENATE', 2, 1, NULL, '0045');
INSERT INTO item VALUES (46, 'FD & C YELLOW #6', 2, 1, NULL, '0046');
INSERT INTO item VALUES (47, 'FOLIC ACID (FEED GRADE)', 2, 1, NULL, '0047');
INSERT INTO item VALUES (48, 'GLYCERINE PURE', 2, 1, NULL, '0048');
INSERT INTO item VALUES (49, 'L-LYSINE USP', 2, 1, NULL, '0049');
INSERT INTO item VALUES (50, 'PORK LIVER POWDER', 2, 1, NULL, '0050');
INSERT INTO item VALUES (51, 'VITAMIN B2 PHOSPHATE', 2, 1, NULL, '0051');
INSERT INTO item VALUES (52, 'TETRASODIUM EDTA', 2, 1, NULL, '0052');
INSERT INTO item VALUES (53, 'NIACIN', 2, 1, NULL, '0053');
INSERT INTO item VALUES (54, 'SODIUM CITRATE', 2, 1, NULL, '0054');
INSERT INTO item VALUES (55, 'VITAMIN D3 OIL 4 MIU', 2, 1, NULL, '0056');
INSERT INTO item VALUES (56, 'VITAMIN D3 POWDER', 2, 1, NULL, '0057');
INSERT INTO item VALUES (57, 'VITAMIN E POWDER', 2, 1, NULL, '0058');
INSERT INTO item VALUES (58, 'VITAMIN A PROPIONATE', 2, 1, NULL, '0059');
INSERT INTO item VALUES (59, 'CARAMEL FLAVOR', 2, 1, NULL, '0060');
INSERT INTO item VALUES (60, 'SODIUM SACCHARIN', 2, 1, NULL, '0061');
INSERT INTO item VALUES (61, 'SODIUM METABISULFITE', 2, 1, NULL, '0062');
INSERT INTO item VALUES (62, 'ETHYL ALCOHOL', 2, 1, NULL, '0063');
INSERT INTO item VALUES (63, 'TWEEN 80', 2, 1, NULL, '0064');
INSERT INTO item VALUES (64, 'VITAMIN B6 HCL ', 2, 1, NULL, '0065');
INSERT INTO item VALUES (65, 'KOLLICOAT IR SUNSET YELLOW', 2, 1, NULL, '0066');
INSERT INTO item VALUES (66, 'KOLLICOAT IR  YELLOW', 2, 1, NULL, '0067');
INSERT INTO item VALUES (67, 'KOLLICOAT SMARTSEAL 30D', 2, 1, NULL, '0068');
INSERT INTO item VALUES (68, 'TRIETHYL CITRATE', 2, 1, NULL, '0069');
INSERT INTO item VALUES (69, 'KOLLICOAT IR BLACK', 2, 1, NULL, '0070');
INSERT INTO item VALUES (70, 'KOLLICOAT IR BRILLIANT BLUE ', 2, 1, NULL, '0071');
INSERT INTO item VALUES (71, 'KOLLICOAT IR CARMINE', 2, 1, NULL, '0072');
INSERT INTO item VALUES (72, 'KOLLICOAT IR WHITE II', 2, 1, NULL, '0073');
INSERT INTO item VALUES (73, 'OREGANO POWDER', 2, 1, NULL, '0074');
INSERT INTO item VALUES (74, 'LUYANG DILAW (ORGANIC TURMERIC)', 2, 1, NULL, '0075');
INSERT INTO item VALUES (75, 'PEPPERMINT ESSENTIAL OIL', 2, 1, NULL, '0076');
INSERT INTO item VALUES (76, 'LAVENDER ESSENTIAL OIL', 2, 1, NULL, '0077');
INSERT INTO item VALUES (77, 'SANDALWOOD ESSENTIAL OIL', 2, 1, NULL, '0078');
INSERT INTO item VALUES (78, 'BLACK PEPPER', 2, 1, NULL, '0079');
INSERT INTO item VALUES (79, 'GUYABANO FRUIT', 2, 1, NULL, '0080');
INSERT INTO item VALUES (80, 'MENTHOL CRYSTAL', 2, 1, NULL, '0081');
INSERT INTO item VALUES (81, 'VERNEL SCENT', 2, 1, NULL, '0082');
INSERT INTO item VALUES (82, 'VIRGIN COCONUT OIL', 2, 1, NULL, '0083');
INSERT INTO item VALUES (83, 'MINERAL OIL', 2, 1, NULL, '0084');
INSERT INTO item VALUES (84, 'SANDALWOOD SCENT', 2, 1, NULL, '0085');
INSERT INTO item VALUES (85, 'POWDER SCENT', 2, 1, NULL, '0086');
INSERT INTO item VALUES (86, 'BLUE BOTTLE W/ MASSAGE OIL (100ML)', 2, 1, NULL, '0088');
INSERT INTO item VALUES (87, 'KOLLIDON VA64 FINE', 2, 1, NULL, '0089');
INSERT INTO item VALUES (88, 'EGC SIZE #3 GREEN (10-23-5)/ WHITE (20-1)', 2, 1, NULL, '0090');
INSERT INTO item VALUES (89, 'EGC SIZE #1 NATURAL (1-0) NATURAL (1-0)', 2, 1, NULL, '0091');
INSERT INTO item VALUES (90, 'EGC SIZE #0  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', 2, 1, NULL, '0092');
INSERT INTO item VALUES (91, 'EGC SIZE #00  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', 2, 1, NULL, '0093');
INSERT INTO item VALUES (92, 'EGC SIZE #4 GREEN DULL (10-25-5)/ GRAY (9-75-5)', 2, 1, NULL, '0094');
INSERT INTO item VALUES (93, 'EGC SIZE #2  NATURAL (1-0) NATURAL (1-0)', 2, 1, NULL, '0095');
INSERT INTO item VALUES (94, 'MANGOSTEEN FRUIT', 2, 1, NULL, '0096');
INSERT INTO item VALUES (95, 'PECTIN POWDER', 2, 1, NULL, '0097');
INSERT INTO item VALUES (96, 'ORANGE FLAVOR', 2, 1, NULL, '0098');
INSERT INTO item VALUES (97, 'CIPROFLOXACIN', 2, 1, NULL, '0099');
INSERT INTO item VALUES (98, 'FOLIC ACID PHARMA GRADE ', 2, 1, NULL, '0100');
INSERT INTO item VALUES (99, 'CEFTIOFUR HYDROCHLORIDE', 2, 1, NULL, '0101');
INSERT INTO item VALUES (100, 'FLORFENICOL', 2, 1, NULL, '0102');
INSERT INTO item VALUES (101, 'OXYTETRACYCLINE ANHYDROUS', 2, 1, NULL, '0103');
INSERT INTO item VALUES (102, 'MALATHION (COMMERCIAL GRADE)100ML', 2, 1, NULL, '0104');
INSERT INTO item VALUES (103, 'SODIUM SELENITE', 2, 1, NULL, '0105');
INSERT INTO item VALUES (104, 'COBALT SULFATE', 2, 1, NULL, '0106');
INSERT INTO item VALUES (105, 'MAGNESIUM SULFATE', 2, 1, NULL, '0107');
INSERT INTO item VALUES (106, 'PVP K 30', 2, 1, NULL, '0108');
INSERT INTO item VALUES (107, 'POTASSIUM IODINE', 2, 1, NULL, '0109');
INSERT INTO item VALUES (108, 'FERROUS SULFATE', 2, 1, NULL, '0110');
INSERT INTO item VALUES (109, 'EUCALYPTUS OIL', 2, 1, NULL, '0111');
INSERT INTO item VALUES (110, 'ETHOXYLATED HYDROGENATED CASTOR OIL', 2, 1, NULL, '0112');
INSERT INTO item VALUES (111, 'POTASSIUM CHLORIDE', 2, 1, NULL, '0113');
INSERT INTO item VALUES (112, 'AMITRAZ', 2, 1, NULL, '0114');
INSERT INTO item VALUES (113, 'CDEA', 2, 1, NULL, '0115');
INSERT INTO item VALUES (114, 'STEARIC ACID', 2, 1, NULL, '0116');
INSERT INTO item VALUES (115, 'SLES', 2, 1, NULL, '0117');
INSERT INTO item VALUES (116, 'PEARLIZER', 2, 1, NULL, '0118');
INSERT INTO item VALUES (117, 'COPPER SULFATE', 2, 1, NULL, '0119');
INSERT INTO item VALUES (118, 'LUDIPRESS', 2, 1, NULL, '0120');
INSERT INTO item VALUES (119, 'SODIUM BICARBONATE', 2, 1, NULL, '0121');
INSERT INTO item VALUES (120, 'BIOTIN 2%', 2, 1, NULL, '0122');
INSERT INTO item VALUES (121, 'VITAMIN A DRY 1 MIU', 2, 1, NULL, '0123');
INSERT INTO item VALUES (122, 'SODA ASH LIGHT', 2, 1, NULL, '0124');
INSERT INTO item VALUES (123, 'MICROCRYSTALLINE CELLULOSE', 2, 1, NULL, '0125');
INSERT INTO item VALUES (124, 'DI-TAB', 2, 1, NULL, '0126');
INSERT INTO item VALUES (125, 'XYLENE', 2, 1, NULL, '0127');
INSERT INTO item VALUES (126, 'SULFADIAZINE SODIUM', 2, 1, NULL, '0128');
INSERT INTO item VALUES (127, 'STREPTOMYCIN SULFATE', 2, 1, NULL, '0129');
INSERT INTO item VALUES (128, 'CARAMEL COLOR', 2, 1, NULL, '0130');
INSERT INTO item VALUES (129, 'DEXTROSE ANHYDROUS POWDER', 2, 1, NULL, '0131');
INSERT INTO item VALUES (130, 'DL-METHIONINE', 2, 1, NULL, '0132');
INSERT INTO item VALUES (131, 'SODIUM CHLORIDE', 2, 1, NULL, '0133');
INSERT INTO item VALUES (132, 'ALBENDAZOLE', 2, 1, NULL, '0134');
INSERT INTO item VALUES (133, 'CALCIUM PROPIONATE', 2, 1, NULL, '0135');
INSERT INTO item VALUES (134, 'TWEEN 20', 2, 1, NULL, '0136');
INSERT INTO item VALUES (135, 'CALCIUM GLUCONATE', 2, 1, NULL, '0137');
INSERT INTO item VALUES (136, 'CALCIUM GLUBIONATE', 2, 1, NULL, '0138');
INSERT INTO item VALUES (137, 'TALC POWDER', 2, 1, NULL, '0139');
INSERT INTO item VALUES (138, 'TRIMETHOPRIM', 2, 1, NULL, '0140');
INSERT INTO item VALUES (139, 'VITAMIN K3', 2, 1, NULL, '0141');
INSERT INTO item VALUES (140, 'DOXYCYCLINE HCI', 2, 1, NULL, '0142');
INSERT INTO item VALUES (141, 'STRAWBERRY FLAVOR POWDER', 2, 1, NULL, '0143');
INSERT INTO item VALUES (142, 'COLISTIN SULFATE', 2, 1, NULL, '0144');
INSERT INTO item VALUES (143, 'BENZALKONIUM CHLORIDE', 2, 1, NULL, '0146');
INSERT INTO item VALUES (144, 'SILKWEED', 2, 1, NULL, '0147');
INSERT INTO item VALUES (145, 'EUCALYPTUS', 2, 1, NULL, '0148');
INSERT INTO item VALUES (146, 'VITAMIN E USP', 2, 1, NULL, '0149');
INSERT INTO item VALUES (147, 'CALCIUM PANTHOTENATE', 2, 1, NULL, '0150');
INSERT INTO item VALUES (148, 'MILK POWDER', 2, 1, NULL, '0151');
INSERT INTO item VALUES (149, 'PERMETHRIN', 2, 1, NULL, '0152');
INSERT INTO item VALUES (150, 'OXYTETRACYCLINE HCL', 2, 1, NULL, '0153');
INSERT INTO item VALUES (151, 'SODIUM SULFAQUINOXALINE', 2, 1, NULL, '0154');
INSERT INTO item VALUES (152, 'NEOMYCIN SULFATE', 2, 1, NULL, '0155');
INSERT INTO item VALUES (153, 'SODIUM SULFAMONOMETHOXINE', 2, 1, NULL, '0156');
INSERT INTO item VALUES (154, 'MANGANESE SULFATE', 2, 1, NULL, '0157');
INSERT INTO item VALUES (155, 'MANGANESE SULFATE', 2, 1, NULL, '0157');
INSERT INTO item VALUES (156, 'TYLOSIN TARTRATE', 2, 1, NULL, '0158');
INSERT INTO item VALUES (157, 'LEVAMISOLE HCL', 2, 1, NULL, '0159');
INSERT INTO item VALUES (158, 'LEVAMISOLE', 2, 1, NULL, '0160');
INSERT INTO item VALUES (159, 'L-LYSINE HCL WSP', 2, 1, NULL, '0161');
INSERT INTO item VALUES (160, 'MEATMEAL', 2, 1, NULL, '0162');
INSERT INTO item VALUES (161, 'PALM OIL', 2, 1, NULL, '0163');
INSERT INTO item VALUES (162, 'CORN OIL', 2, 1, NULL, '0164');
INSERT INTO item VALUES (163, 'CANOLA OIL', 2, 1, NULL, '0165');
INSERT INTO item VALUES (164, 'EGC SIZE #00 NATURAL & GREEN (VEGGIE TYPE)', 2, 1, NULL, '0166');
INSERT INTO item VALUES (165, 'EGC SIZE #0 NATURAL & GREEN (VEGGIE TYPE)', 2, 1, NULL, '0167');
INSERT INTO item VALUES (166, 'SAMBONG LEAVES POWDER', 2, 1, NULL, '0168');
INSERT INTO item VALUES (167, 'GUYABANO LEAVES POWDER', 2, 1, NULL, '0169');
INSERT INTO item VALUES (168, 'ZINC SULPHATE MONOHYDRATE', 2, 1, NULL, '0170');
INSERT INTO item VALUES (169, 'MALUNGGAY POWDER WITH COA', 2, 1, NULL, '0171');
INSERT INTO item VALUES (170, 'COLLAGEN', 2, 1, NULL, '0172');
INSERT INTO item VALUES (171, 'ALPHA-LIPOIC ACID', 2, 1, NULL, '0173');
INSERT INTO item VALUES (172, 'L-GLUTATHIONE REDUCED', 2, 1, NULL, '0174');
INSERT INTO item VALUES (173, 'ALOE VERA', 2, 1, NULL, '0175');
INSERT INTO item VALUES (174, 'HONEY', 2, 1, NULL, '0176');
INSERT INTO item VALUES (175, 'FRESH YACOON', 2, 1, NULL, '0177');
INSERT INTO item VALUES (176, 'LYCHEE FLAVOR', 2, 1, NULL, '0178');
INSERT INTO item VALUES (177, 'LYCHEE FLAVOR', 2, 1, NULL, '0178');
INSERT INTO item VALUES (178, 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/PINK)', 2, 1, NULL, '0179');
INSERT INTO item VALUES (179, 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/WHITE)', 2, 1, NULL, '0180');
INSERT INTO item VALUES (180, 'BIOPERINE', 2, 1, NULL, '0181');
INSERT INTO item VALUES (181, 'POTASSIUM HYDROXIDE - FLAKES', 2, 1, NULL, '0182');
INSERT INTO item VALUES (182, 'VITAMIN C PLAIN(ASCORBIC ACID)', 2, 1, NULL, '0183');
INSERT INTO item VALUES (183, 'ENROFLOXACIN HYDROCHLORIDE', 2, 1, NULL, '0184');
INSERT INTO item VALUES (184, 'SODIUM CITRATE DIHYDRATE', 2, 1, NULL, '0185');
INSERT INTO item VALUES (185, 'VITAMIN E ACETATE 970 IU/G', 2, 1, NULL, '0186');
INSERT INTO item VALUES (186, 'VITAMIN A PALMITATE 1.7MIU', 2, 1, NULL, '0187');
INSERT INTO item VALUES (187, 'CALCIUM CARBONATE LIGHT', 2, 1, NULL, '0188');
INSERT INTO item VALUES (188, 'GLUTATHIONE POWDER', 2, 1, NULL, '0189');
INSERT INTO item VALUES (189, 'N-METHYL-PYRROLIDONE (NMP)', 2, 1, NULL, '0190');
INSERT INTO item VALUES (190, 'DIMETRIDAZOLE', 2, 1, NULL, '0191');
INSERT INTO item VALUES (191, 'DEXAMETHASONE', 2, 1, NULL, '0192');
INSERT INTO item VALUES (192, 'LUYANG DILAW (TURMERIC) POWDER', 2, 1, NULL, '0193');
INSERT INTO item VALUES (193, 'EGC SIZE #0 BOVINE CAPS WHITE/WHITE', 2, 1, NULL, '0194');
INSERT INTO item VALUES (194, 'EGC SIZE #00 BOVINE CAPS WHITE/WHITE', 2, 1, NULL, '0195');
INSERT INTO item VALUES (195, 'METHANDRIOL PROPIONATE', 2, 1, NULL, '0196');
INSERT INTO item VALUES (196, 'THIAMPHENICOL', 2, 1, NULL, '0197');
INSERT INTO item VALUES (197, 'MIGLYOL 840', 2, 1, NULL, '0198');
INSERT INTO item VALUES (198, 'CAB-O-SIL', 2, 1, NULL, '0199');
INSERT INTO item VALUES (199, 'ZINC OXIDE', 2, 1, NULL, '0200');
INSERT INTO item VALUES (200, 'CHERRY FLAVOR LIQUID', 2, 1, NULL, '0201');
INSERT INTO item VALUES (201, 'MALTODEXTRIN', 2, 1, NULL, '0202');
INSERT INTO item VALUES (202, 'NORFLOXACIN HYDROCHLORIDE', 2, 1, NULL, '0203');
INSERT INTO item VALUES (203, 'YACON SEEDLING', 2, 1, NULL, '0204');
INSERT INTO item VALUES (204, 'GLUTARALDEHYDE 50%', 2, 1, NULL, '0205');
INSERT INTO item VALUES (205, 'EUCALYPTOL 99%', 2, 1, NULL, '0206');
INSERT INTO item VALUES (206, 'BENZOIC ACID', 2, 1, NULL, '0207');
INSERT INTO item VALUES (207, 'FRESH LUYA', 2, 1, NULL, '0208');
INSERT INTO item VALUES (208, 'GUAVA LEAVES EXTRACT', 2, 1, NULL, '0209');
INSERT INTO item VALUES (209, 'GUYABANO PULP', 2, 1, NULL, '0210');
INSERT INTO item VALUES (210, 'UNFLAVORED JELLY POWDER', 2, 1, NULL, '0211');
INSERT INTO item VALUES (211, 'FRESH SAMPALOK', 2, 1, NULL, '0212');
INSERT INTO item VALUES (212, 'CRYSTALLINE FRUCTOSE', 2, 1, NULL, '0213');
INSERT INTO item VALUES (213, 'L-CITRULLINE', 2, 1, NULL, '0214');
INSERT INTO item VALUES (214, 'MALIC ACID', 2, 1, NULL, '0215');
INSERT INTO item VALUES (215, 'VITAMIN A PALMITATE POWDER', 2, 1, NULL, '0216');
INSERT INTO item VALUES (216, 'NATURAL FLAVOR POWDER (DALANDAN)', 2, 1, NULL, '0217');
INSERT INTO item VALUES (217, 'STEVIA REBAUDIANA', 2, 1, NULL, '0218');
INSERT INTO item VALUES (218, 'ZINC SULFATE', 2, 1, NULL, '0219');
INSERT INTO item VALUES (219, 'TRISODIUM PHOSPHATE DODECAHYDRATE', 2, 1, NULL, '0220');
INSERT INTO item VALUES (220, 'OPTICAL BRIGHTENER (STILIBENE, 3,5-DIHYDROXY)', 2, 1, NULL, '0221');
INSERT INTO item VALUES (221, 'LABSA (LINEAR ALKYL BENZYL SULPHONIC ACID)', 2, 1, NULL, '0222');
INSERT INTO item VALUES (222, 'VEEGUM', 2, 1, NULL, '0223');
INSERT INTO item VALUES (223, 'SODIUM CMC (CARBOXYMETHYLCELLULOSE)', 2, 1, NULL, '0224');
INSERT INTO item VALUES (224, 'TN500', 2, 1, NULL, '0229');
INSERT INTO item VALUES (225, 'ZINC SULFATE MONOHYDRATE', 2, 1, NULL, '0269');
INSERT INTO item VALUES (226, 'RASPBERRY FLAVOR', 2, 1, NULL, '0296');
INSERT INTO item VALUES (227, 'TUTTI FRUITY FLAVOR', 2, 1, NULL, '1000');
INSERT INTO item VALUES (228, 'BEEF SPRAVY', 2, 1, NULL, '1001');
INSERT INTO item VALUES (229, 'SODIUM CITRATE ANHYDROUS', 2, 1, NULL, '1002');
INSERT INTO item VALUES (230, 'FD & C RED # 3', 2, 1, NULL, '1003');
INSERT INTO item VALUES (231, 'HYOSCINE HBR', 2, 1, NULL, '1004');
INSERT INTO item VALUES (232, 'LAMB SPRAVY', 2, 1, NULL, '1005');
INSERT INTO item VALUES (233, 'LANETTO', 2, 1, NULL, '1006');
INSERT INTO item VALUES (234, 'MELON SWEET', 2, 1, NULL, '1007');
INSERT INTO item VALUES (235, 'MY STRAWBERRY SCENT', 2, 1, NULL, '1008');
INSERT INTO item VALUES (236, 'PYRANTEL PAMOATE', 2, 1, NULL, '1009');
INSERT INTO item VALUES (237, 'BETAINE HCL', 2, 1, NULL, '1010');
INSERT INTO item VALUES (238, 'MR. VP, BBL BROTH', 2, 1, NULL, '1011');
INSERT INTO item VALUES (239, 'XYLOSE LYSINE DEOXYCHOLATE', 2, 1, NULL, '1012');
INSERT INTO item VALUES (240, 'SELENIUM SULFIDE', 2, 1, NULL, '1013');
INSERT INTO item VALUES (241, 'SILVER DIOXIDE', 2, 1, NULL, '1014');
INSERT INTO item VALUES (242, 'CHOLINE CHLORIDE 70% LIQUID', 2, 1, NULL, '1015');
INSERT INTO item VALUES (243, 'XANTHAN GUM', 2, 1, NULL, '1016');
INSERT INTO item VALUES (244, 'temporary packaging', 3, 1, 'temp', 'p1');


--
-- Data for Name: item_category; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO item_category VALUES (1, 'FG', 'Finished Goods', 2);
INSERT INTO item_category VALUES (2, 'RM', 'Raw Materials', 2);
INSERT INTO item_category VALUES (3, 'PM', 'Packaging Materials', 2);
INSERT INTO item_category VALUES (4, 'EM', 'Excipient Materials', 2);


--
-- Name: item_category_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_category_id_seq', 4, true);


--
-- Data for Name: item_class; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO item_class VALUES (1, '02', 'Products');
INSERT INTO item_class VALUES (2, '01', 'Materials');


--
-- Name: item_class_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_class_id_seq', 2, true);


--
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_id_seq', 244, true);


--
-- Data for Name: item_type; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO item_type VALUES (1, 'DM', 'Direct Material');
INSERT INTO item_type VALUES (2, 'IDM', 'In-Direct Material');


--
-- Name: item_type_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_type_id_seq', 2, true);


--
-- Data for Name: stock_card; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO stock_card VALUES (2, 4, 5, 'I', 47, 250, 100, 'CD400', '2015-06-19', '2015-06-30', 'NQ-1', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (3, 4, 5, 'I', 187, 100, 85, 'CD100', '2015-06-01', '2015-06-30', 'NQ-2', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (4, 4, 5, 'I', 187, 100, 85, 'CD100', '2015-06-01', '2015-06-30', 'NQ-3', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (5, 4, 5, 'I', 205, 90, 10, 'GF234234', '2015-06-26', '2015-06-30', 'NQ-4', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (6, 4, 5, 'I', 165, 20, 90, 'CDF23423', '2015-06-01', '2015-06-30', 'NQ-5', 'Draft', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (7, 3, 5, 'I', 211, 10, 90, 'CDwer', '2015-06-01', '2015-06-30', 'NQ-6', 'Draft', 'BAG', 'AVAILABLE');
INSERT INTO stock_card VALUES (8, 4, 5, 'I', 165, 20, 90, 'CDF23423', '2015-06-01', '2015-06-30', 'NQ-7', 'Draft', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (9, 4, 5, 'I', 164, 230, 110, 'CD300', '2015-06-01', '2015-06-30', 'NQ-8', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (10, 4, 5, 'I', 211, 50, 20, 'FG345', '2015-07-01', '2015-07-31', 'NQ-9', 'Draft', 'BAG', 'AVAILABLE');
INSERT INTO stock_card VALUES (11, 4, 5, 'I', 92, 40, 100, 'WER123', '2015-07-01', '2015-07-31', 'NQ-10', 'Draft', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (12, 4, 5, 'I', 165, 250, 20, 'we', '2015-06-01', '2015-06-30', 'NQ-11', 'Draft', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (13, 4, 5, 'I', 165, 20, 20, 'werew', '2015-07-01', '2015-07-31', 'NQ-12', 'Disapprove', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (14, 4, 5, 'I', 210, 89, 50, 'CD2000', '2015-06-01', '2015-06-30', 'NQ-13', 'Approved', 'G', 'AVAILABLE');
INSERT INTO stock_card VALUES (15, 4, 5, 'I', 228, 100, 100, 'CD2011', '2015-06-01', '2015-06-30', 'NQ-14', 'Approved', 'L', 'AVAILABLE');
INSERT INTO stock_card VALUES (16, 4, 5, 'I', 234, 20, 300, 'CD3000', '2015-06-01', '2015-06-30', 'NQ-15', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (17, 4, 5, 'I', 211, 40, 20, 'DF234', '2015-06-01', '2015-07-31', 'NQ-16', 'Draft', 'BAG', 'AVAILABLE');
INSERT INTO stock_card VALUES (18, 4, 5, 'I', 132, 20, 10, 'FG345', '2015-07-01', '2015-07-31', 'NQ-17', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (19, 4, 5, 'O', 143, 35, 50, 'CD9000', '2015-06-01', '2015-06-30', 'NQ-18', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (55, 4, 5, 'O', 164, 230, 20, 'CD300', '2015-06-01', '2015-06-30', 'NQ-54', 'Draft', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (56, 4, 5, 'O', 47, 250, 30, 'CD400', '2015-06-19', '2015-06-30', 'NQ-55', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (57, 4, 5, 'O', 165, 37.5, 45, 'CD1000', '2015-06-01', '2015-06-30', 'NQ-56', 'Draft', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (58, 4, 5, 'O', 187, 100, 20, 'CD100', '2015-06-01', '2015-06-30', 'NQ-57', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (59, 4, 5, 'O', 228, 100, 45, 'CD2011', '2015-06-01', '2015-06-30', 'NQ-58', 'Draft', 'L', 'DEPLETED');
INSERT INTO stock_card VALUES (60, 4, 5, 'O', 228, 100, 5, 'CD2011', '2015-06-01', '2015-06-30', 'NQ-59', 'Draft', 'L', 'DEPLETED');
INSERT INTO stock_card VALUES (61, 4, 5, 'O', 187, 100, 20, 'CD100', '2015-06-01', '2015-06-30', 'NQ-60', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (62, 4, 5, 'O', 136, 4.5, 200, 'F123123', '2015-07-01', '2015-07-31', 'NQ-61', 'Approved', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (67, 1, 2, 'I', 244, 10, 10000, 'lotpoopo', '2015-06-01', '2017-06-01', 'controlpm1', 'Approved', 'pcs', 'AVAILABLE');
INSERT INTO stock_card VALUES (66, 1, 5, 'I', 187, 50, 100, 'lotpo0', '2015-06-01', '2017-06-01', 'control99', 'Approved', 'kg', 'AVAILABLE');
INSERT INTO stock_card VALUES (64, 1, 5, 'I', 187, 50, 500, 'lot001', '2015-06-01', '2017-06-01', 'nq-990', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (63, 1, 5, 'I', 205, 90, 100, 'CD200', '2015-06-01', '2015-06-25', 'NQ-62', 'Approved', 'g', 'DEPLETED');
INSERT INTO stock_card VALUES (1, 1, 5, 'I', 205, 90, 100, 'CD200', '2015-06-01', '2015-06-30', 'NQ-0', 'Approved', 'kg', 'AVAILABLE');
INSERT INTO stock_card VALUES (23, 4, 5, 'I', 47, 500, 70, 'CD#402', '2015-06-01', '2015-06-30', 'NQ-22', 'Disapprove', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (24, 4, 5, 'I', 165, 80, 90, 'CD34534', '2015-06-01', '2015-06-30', 'NQ-23', 'Disapprove', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (25, 4, 5, 'I', 187, 100, 85, 'CD100', '2015-06-01', '2015-06-30', 'NQ-24', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (26, 4, 5, 'I', 205, 90, 10, 'GF234234', '2015-06-26', '2015-06-30', 'NQ-25', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (28, 3, 5, 'I', 230, 100, 30, 'CD901', '2015-07-01', '2016-03-31', 'NQ-27', 'Draft', 'G', 'AVAILABLE');
INSERT INTO stock_card VALUES (29, 4, 5, 'I', 165, 37.5, 45, 'CD1000', '2015-06-01', '2015-06-30', 'NQ-28', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (30, 4, 5, 'I', 92, 300, 40, 'CD400', '2015-06-25', '2015-06-30', 'NQ-29', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (31, 4, 5, 'I', 143, 35, 100, 'CD9000', '2015-06-01', '2015-06-30', 'NQ-30', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (32, 4, 5, 'I', 108, 120, 20, 'CD2000', '2015-06-01', '2015-06-30', 'NQ-31', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (33, 4, 5, 'I', 171, 30, 100, 'CD4000', '2015-06-01', '2015-06-30', 'NQ-32', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (34, 4, 5, 'I', 242, 20, 20, 'CD3000', '2015-06-01', '2015-06-30', 'NQ-33', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (35, 4, 5, 'I', 15, 30, 90, 'WE12312', '2015-06-01', '2015-06-30', 'NQ-34', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (36, 4, 5, 'I', 164, 30, 90, 'CD3000', '2015-06-01', '2015-07-31', 'NQ-35', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (37, 4, 5, 'I', 136, 4.5, 2000, 'F123123', '2015-07-01', '2015-07-31', 'NQ-36', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (38, 4, 5, 'I', 47, 5.75, 2000, 'FG1000', '2015-07-01', '2016-06-30', 'NQ-37', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (39, 4, 5, 'I', 136, 20, 400, 'CDFWER', '2015-06-02', '2015-06-30', 'NQ-38', 'Draft', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (40, 3, 5, 'I', 92, 100, 40, 'CD900', '2015-07-01', '2016-03-31', 'NQ-39', 'Draft', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (41, 3, 5, 'I', 187, 100, 45, 'CD100', '2015-06-01', '2015-06-30', 'NQ-40', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (47, 4, 5, 'I', 132, 5, 1000, 'FG890', '2015-07-01', '2015-12-31', 'NQ-46', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (20, 4, 5, 'O', 143, 35, 20, 'CD9000', '2015-06-01', '2015-06-30', 'NQ-19', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (21, 4, 5, 'O', 228, 100, 50, 'CD2011', '2015-06-01', '2015-06-30', 'NQ-20', 'Approved', 'L', 'DEPLETED');
INSERT INTO stock_card VALUES (22, 4, 5, 'O', 143, 35, 30, 'CD9000', '2015-06-01', '2015-06-30', 'NQ-21', 'Approved', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (27, 4, 5, 'O', 187, 100, 45, 'CD100', '2015-06-01', '2015-06-30', 'NQ-26', 'Approved', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (42, 3, 5, 'O', 187, 100, 25, 'CD100', '2015-06-01', '2015-06-30', 'NQ-41', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (43, 3, 5, 'O', 187, 100, 20, 'CD100', '2015-06-01', '2015-06-30', 'NQ-42', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (44, 4, 5, 'O', 136, 4.5, 1500, 'F123123', '2015-07-01', '2015-07-31', 'NQ-43', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (45, 4, 5, 'O', 15, 30, 45, 'WE12312', '2015-06-01', '2015-06-30', 'NQ-44', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (46, 4, 5, 'O', 210, 89, 50, 'CD2000', '2015-06-01', '2015-06-30', 'NQ-45', 'Draft', 'G', 'DEPLETED');
INSERT INTO stock_card VALUES (48, 4, 5, 'O', 164, 230, 50, 'CD300', '2015-06-01', '2015-06-30', 'NQ-47', 'Approved', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (49, 4, 5, 'O', 164, 230, 50, 'CD300', '2015-06-01', '2015-06-30', 'NQ-48', 'Approved', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (50, 4, 5, 'O', 92, 300, 40, 'CD400', '2015-06-25', '2015-06-30', 'NQ-49', 'Approved', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (51, 4, 5, 'O', 164, 230, 30, 'CD300', '2015-06-01', '2015-06-30', 'NQ-50', 'Draft', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (52, 4, 5, 'O', 47, 250, 50, 'CD400', '2015-06-19', '2015-06-30', 'NQ-51', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (53, 4, 5, 'O', 47, 250, 20, 'CD400', '2015-06-19', '2015-06-30', 'NQ-52', 'Draft', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (54, 4, 5, 'O', 164, 230, 10, 'CD300', '2015-06-01', '2015-06-30', 'NQ-53', 'Draft', 'PCS', 'DEPLETED');
INSERT INTO stock_card VALUES (65, 1, 5, 'I', 205, 50, 50, 'kimnh', '2015-06-01', '2017-06-01', 'controlno6', 'Approved', 'KG', 'DEPLETED');


--
-- Name: stock_card_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_id_seq', 67, true);


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

INSERT INTO warehouse VALUES (1, '00006', 'Finished Goods - DVO - WLO');
INSERT INTO warehouse VALUES (2, '00012', 'Packaging Materials- Balubad');
INSERT INTO warehouse VALUES (3, '00007', 'Packaging Materials - Langkiwa');
INSERT INTO warehouse VALUES (4, '00010', 'Promotionals - Bicutan');
INSERT INTO warehouse VALUES (5, '00011', 'Raw Materials - Balubad');
INSERT INTO warehouse VALUES (6, '00009', 'Raw Materials - Langkiwa');
INSERT INTO warehouse VALUES (7, '00003', 'Finished Goods - Balubad Warehouse');
INSERT INTO warehouse VALUES (8, '00005', 'Finished Goods - CEB - WLO');
INSERT INTO warehouse VALUES (9, '00001', 'Finished Goods - Langkiwa Warehouse');
INSERT INTO warehouse VALUES (10, '00008', 'Finished Goods - Tarlac Warehouse');
INSERT INTO warehouse VALUES (11, '00004', 'Finished Goods - CDO - WLO');
INSERT INTO warehouse VALUES (12, '00013', 'MMD - Balubad');
INSERT INTO warehouse VALUES (13, '00002', 'Finished Goods - Bicutan Warehouse');


--
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_seq', 13, true);


SET search_path = transaction, pg_catalog;

--
-- Data for Name: stock_card_txn; Type: TABLE DATA; Schema: transaction; Owner: postgres
--

INSERT INTO stock_card_txn VALUES (59, 1, 4.5999999999999996, 7, 73);
INSERT INTO stock_card_txn VALUES (60, 66, 9, 7, 73);
INSERT INTO stock_card_txn VALUES (61, 67, 1000, 9, 73);


--
-- Name: stock_card_txn_id_seq; Type: SEQUENCE SET; Schema: transaction; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_txn_id_seq', 61, true);


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


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Name: company_code_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_code_key UNIQUE (code);


--
-- Name: company_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- Name: item_category_code_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_category
    ADD CONSTRAINT item_category_code_key UNIQUE (code);


--
-- Name: item_category_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_category
    ADD CONSTRAINT item_category_pkey PRIMARY KEY (id);


--
-- Name: item_class_code_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_class
    ADD CONSTRAINT item_class_code_key UNIQUE (code);


--
-- Name: item_class_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_class
    ADD CONSTRAINT item_class_pkey PRIMARY KEY (id);


--
-- Name: item_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- Name: item_type_code_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_type
    ADD CONSTRAINT item_type_code_key UNIQUE (code);


--
-- Name: item_type_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_type
    ADD CONSTRAINT item_type_pkey PRIMARY KEY (id);


--
-- Name: stock_card_control_no_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stock_card
    ADD CONSTRAINT stock_card_control_no_key UNIQUE (control_no);


--
-- Name: stock_card_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stock_card
    ADD CONSTRAINT stock_card_pkey PRIMARY KEY (id);


--
-- Name: warehouse_code_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY warehouse
    ADD CONSTRAINT warehouse_code_key UNIQUE (code);


--
-- Name: warehouse_pkey; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY warehouse
    ADD CONSTRAINT warehouse_pkey PRIMARY KEY (id);


SET search_path = transaction, pg_catalog;

--
-- Name: stock_card_txn_pkey; Type: CONSTRAINT; Schema: transaction; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_pkey PRIMARY KEY (id);


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


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Name: item_category_item_class_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item_category
    ADD CONSTRAINT item_category_item_class_id_fkey FOREIGN KEY (item_class_id) REFERENCES item_class(id);


--
-- Name: item_item_category_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_item_category_id_fkey FOREIGN KEY (item_category_id) REFERENCES item_category(id);


--
-- Name: item_item_type_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_item_type_id_fkey FOREIGN KEY (item_type_id) REFERENCES item_type(id);


--
-- Name: stock_card_company_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY stock_card
    ADD CONSTRAINT stock_card_company_id_fkey FOREIGN KEY (company_id) REFERENCES company(id);


--
-- Name: stock_card_item_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY stock_card
    ADD CONSTRAINT stock_card_item_id_fkey FOREIGN KEY (item_id) REFERENCES item(id);


--
-- Name: stock_card_warehouse_id_fkey; Type: FK CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres
--

ALTER TABLE ONLY stock_card
    ADD CONSTRAINT stock_card_warehouse_id_fkey FOREIGN KEY (warehouse_id) REFERENCES warehouse(id);


SET search_path = transaction, pg_catalog;

--
-- Name: stock_card_txn_mbr_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_mbr_id_fkey FOREIGN KEY (mbr_id) REFERENCES mbr.mbr(id);


--
-- Name: stock_card_txn_stock_card_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_stock_card_id_fkey FOREIGN KEY (stock_card_id) REFERENCES sqlsvr_copy.stock_card(id);


--
-- Name: stock_card_txn_unit_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_unit_id_fkey FOREIGN KEY (unit_id) REFERENCES main.unit(id);


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

