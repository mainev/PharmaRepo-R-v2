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
-- Name: line_clearance; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA line_clearance;


ALTER SCHEMA line_clearance OWNER TO postgres;

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
-- Name: security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO postgres;

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
-- Name: audit_trail; Type: TABLE; Schema: audit; Owner: postgres; Tablespace: 
--

CREATE TABLE audit_trail (
    id integer NOT NULL,
    username character varying(100),
    datetime timestamp without time zone,
    table_name character varying(50),
    method character varying(200),
    action character varying(20),
    old_value character varying,
    new_value character varying
);


ALTER TABLE audit.audit_trail OWNER TO postgres;

--
-- Name: audit_trail_id_seq; Type: SEQUENCE; Schema: audit; Owner: postgres
--

CREATE SEQUENCE audit_trail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE audit.audit_trail_id_seq OWNER TO postgres;

--
-- Name: audit_trail_id_seq; Type: SEQUENCE OWNED BY; Schema: audit; Owner: postgres
--

ALTER SEQUENCE audit_trail_id_seq OWNED BY audit_trail.id;


SET search_path = line_clearance, pg_catalog;

--
-- Name: coding_room_line_clearance; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE coding_room_line_clearance (
    id integer NOT NULL,
    step_no smallint,
    instruction character varying,
    checked_by character varying(100),
    verified_by character varying(100),
    type smallint,
    area smallint
);


ALTER TABLE line_clearance.coding_room_line_clearance OWNER TO postgres;

--
-- Name: compounding_area_line_clearance; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE compounding_area_line_clearance (
    id integer NOT NULL,
    step_no smallint,
    instruction character varying,
    checked_by character varying(100),
    verified_by character varying(100),
    type smallint,
    area smallint
);


ALTER TABLE line_clearance.compounding_area_line_clearance OWNER TO postgres;

--
-- Name: dispensing_area_line_clearance; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE dispensing_area_line_clearance (
    id integer NOT NULL,
    step_no smallint,
    instruction character varying,
    checked_by character varying(100),
    verified_by character varying(100),
    type smallint,
    area smallint
);


ALTER TABLE line_clearance.dispensing_area_line_clearance OWNER TO postgres;

--
-- Name: dispensing_area_id_seq; Type: SEQUENCE; Schema: line_clearance; Owner: postgres
--

CREATE SEQUENCE dispensing_area_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE line_clearance.dispensing_area_id_seq OWNER TO postgres;

--
-- Name: dispensing_area_id_seq; Type: SEQUENCE OWNED BY; Schema: line_clearance; Owner: postgres
--

ALTER SEQUENCE dispensing_area_id_seq OWNED BY dispensing_area_line_clearance.id;


--
-- Name: filling_area_line_clearance; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE filling_area_line_clearance (
    id integer NOT NULL,
    step_no smallint,
    instruction character varying,
    checked_by character varying(100),
    verified_by character varying(100),
    type smallint,
    area smallint
);


ALTER TABLE line_clearance.filling_area_line_clearance OWNER TO postgres;

--
-- Name: labeling_packg_area_line_clearance; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE labeling_packg_area_line_clearance (
    id integer NOT NULL,
    step_no smallint,
    instruction character varying,
    checked_by character varying(100),
    verified_by character varying(100),
    type smallint,
    area smallint
);


ALTER TABLE line_clearance.labeling_packg_area_line_clearance OWNER TO postgres;

--
-- Name: type; Type: TABLE; Schema: line_clearance; Owner: postgres; Tablespace: 
--

CREATE TABLE type (
    id smallint NOT NULL,
    code character varying(5),
    description character varying(50)
);


ALTER TABLE line_clearance.type OWNER TO postgres;

--
-- Name: type_id_seq; Type: SEQUENCE; Schema: line_clearance; Owner: postgres
--

CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE line_clearance.type_id_seq OWNER TO postgres;

--
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: line_clearance; Owner: postgres
--

ALTER SEQUENCE type_id_seq OWNED BY type.id;


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
-- Name: product; Type: TABLE; Schema: main; Owner: postgres; Tablespace: 
--

CREATE TABLE product (
    id integer NOT NULL,
    code character varying(5),
    brand_name character varying(200),
    generic_name character varying(200),
    classification_id smallint,
    company_id smallint,
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
-- Name: batch_item_requirement; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE batch_item_requirement (
    id integer NOT NULL,
    batch_id integer,
    item_id integer,
    udf_qty double precision,
    udf_qty_unit_id smallint,
    required_qty double precision,
    required_qty_unit_id smallint,
    date_allocated timestamp without time zone,
    date_dispensed timestamp without time zone,
    item_req_status character varying(20),
    part smallint
);


ALTER TABLE mbr.batch_item_requirement OWNER TO postgres;

--
-- Name: TABLE batch_item_requirement; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE batch_item_requirement IS 'computation record for batch item requirement';


--
-- Name: batch_item_requirement_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE batch_item_requirement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.batch_item_requirement_id_seq OWNER TO postgres;

--
-- Name: batch_item_requirement_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE batch_item_requirement_id_seq OWNED BY batch_item_requirement.id;


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
-- Name: coding_spec; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE coding_spec (
    step_no smallint,
    instruction character varying,
    requires_equipment boolean,
    done_by character varying(100),
    checked_by character varying(100),
    area smallint,
    id integer NOT NULL
);


ALTER TABLE mbr.coding_spec OWNER TO postgres;

--
-- Name: TABLE coding_spec; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE coding_spec IS 'Coding specification';


--
-- Name: coding_spec_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE coding_spec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.coding_spec_id_seq OWNER TO postgres;

--
-- Name: coding_spec_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE coding_spec_id_seq OWNED BY coding_spec.id;


--
-- Name: compounding_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE compounding_procedure (
    id integer NOT NULL,
    step_number smallint,
    instruction character varying,
    time_monitored boolean,
    done_by character varying(100),
    checked_by character varying(100),
    manufacturing_procedure_id integer,
    requires_equipment boolean,
    remarks character varying,
    requires_rmreq boolean
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
    procedure_category character varying(20)
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
-- Name: main_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE main_procedure (
    id smallint NOT NULL,
    heading character varying(1),
    heading_title character varying(50),
    area smallint
);


ALTER TABLE mbr.main_procedure OWNER TO postgres;

--
-- Name: TABLE main_procedure; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE main_procedure IS 'first page of mbr';


--
-- Name: main_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE main_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.main_procedure_id_seq OWNER TO postgres;

--
-- Name: main_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE main_procedure_id_seq OWNED BY main_procedure.id;


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
    quantity double precision,
    unit_id smallint,
    udf_id integer,
    item_id integer
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
    instruction character varying(1000),
    manufacturing_procedure_id integer,
    part smallint,
    done_by character varying(100),
    checked_by character varying(100)
);


ALTER TABLE mbr.packaging_procedure OWNER TO postgres;

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

ALTER SEQUENCE packaging_procedure_operation_id_seq OWNED BY packaging_procedure.id;


--
-- Name: primary_secondary_packaging; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE primary_secondary_packaging (
    id integer NOT NULL,
    primary_packaging_id integer,
    secondary_packaging_id integer
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
    quantity double precision,
    unit_id smallint,
    udf_id integer,
    part smallint,
    item_id integer
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
-- Name: sub_procedure; Type: TABLE; Schema: mbr; Owner: postgres; Tablespace: 
--

CREATE TABLE sub_procedure (
    id integer NOT NULL,
    main_procedure smallint,
    sub_procedure character varying(200),
    step_no smallint
);


ALTER TABLE mbr.sub_procedure OWNER TO postgres;

--
-- Name: TABLE sub_procedure; Type: COMMENT; Schema: mbr; Owner: postgres
--

COMMENT ON TABLE sub_procedure IS 'for the first page of mbr';


--
-- Name: sub_procedure_id_seq; Type: SEQUENCE; Schema: mbr; Owner: postgres
--

CREATE SEQUENCE sub_procedure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mbr.sub_procedure_id_seq OWNER TO postgres;

--
-- Name: sub_procedure_id_seq; Type: SEQUENCE OWNED BY; Schema: mbr; Owner: postgres
--

ALTER SEQUENCE sub_procedure_id_seq OWNED BY sub_procedure.id;


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


SET search_path = security, pg_catalog;

--
-- Name: role_method; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE role_method (
    id smallint NOT NULL,
    role_id smallint,
    method_id smallint
);


ALTER TABLE security.role_method OWNER TO postgres;

--
-- Name: TABLE role_method; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON TABLE role_method IS 'mapping of role and method table';


--
-- Name: access_right_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE access_right_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.access_right_id_seq OWNER TO postgres;

--
-- Name: access_right_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE access_right_id_seq OWNED BY role_method.id;


--
-- Name: group; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE "group" (
    id smallint NOT NULL,
    group_name character varying(20)
);


ALTER TABLE security."group" OWNER TO postgres;

--
-- Name: TABLE "group"; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON TABLE "group" IS 'internal classification of users';


--
-- Name: group_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.group_id_seq OWNER TO postgres;

--
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE group_id_seq OWNED BY "group".id;


--
-- Name: user_role; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE user_role (
    id smallint NOT NULL,
    user_id integer,
    role_id smallint
);


ALTER TABLE security.user_role OWNER TO postgres;

--
-- Name: TABLE user_role; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON TABLE user_role IS 'mapping of user and role';


--
-- Name: group_role_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE group_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.group_role_id_seq OWNER TO postgres;

--
-- Name: group_role_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE group_role_id_seq OWNED BY user_role.id;


--
-- Name: role; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    id smallint NOT NULL,
    role_name character varying(20) NOT NULL
);


ALTER TABLE security.role OWNER TO postgres;

--
-- Name: id_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE id_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.id_id_seq OWNER TO postgres;

--
-- Name: id_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE id_id_seq OWNED BY role.id;


--
-- Name: method; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE method (
    id smallint NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(500)
);


ALTER TABLE security.method OWNER TO postgres;

--
-- Name: method_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE method_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.method_id_seq OWNER TO postgres;

--
-- Name: method_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE method_id_seq OWNED BY method.id;


--
-- Name: method_sub_method; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE method_sub_method (
    id integer NOT NULL,
    method_id integer,
    sub_method_id integer
);


ALTER TABLE security.method_sub_method OWNER TO postgres;

--
-- Name: TABLE method_sub_method; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON TABLE method_sub_method IS 'mapping of main method and sub method';


--
-- Name: method_sub_method_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE method_sub_method_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.method_sub_method_id_seq OWNER TO postgres;

--
-- Name: method_sub_method_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE method_sub_method_id_seq OWNED BY method_sub_method.id;


--
-- Name: sub_method; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE sub_method (
    id integer NOT NULL,
    uri character varying(200),
    description character varying(500)
);


ALTER TABLE security.sub_method OWNER TO postgres;

--
-- Name: TABLE sub_method; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON TABLE sub_method IS 'uri paths';


--
-- Name: submethod_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE submethod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.submethod_id_seq OWNER TO postgres;

--
-- Name: submethod_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE submethod_id_seq OWNED BY sub_method.id;


--
-- Name: user; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    id integer NOT NULL,
    email_ad character varying(100),
    first_name character varying(100),
    last_name character varying(100),
    "position" character varying(100),
    password character(100)
);


ALTER TABLE security."user" OWNER TO postgres;

--
-- Name: COLUMN "user".email_ad; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON COLUMN "user".email_ad IS 'serves as username';


--
-- Name: user_group; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE user_group (
    id integer NOT NULL,
    user_id integer NOT NULL,
    group_id smallint NOT NULL
);


ALTER TABLE security.user_group OWNER TO postgres;

--
-- Name: user_group_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE user_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.user_group_id_seq OWNER TO postgres;

--
-- Name: user_group_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE user_group_id_seq OWNED BY user_group.id;


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- Name: user_roles; Type: VIEW; Schema: security; Owner: postgres
--

CREATE VIEW user_roles AS
 SELECT u.email_ad,
    u.password,
    g.group_name
   FROM ((user_group ug
     JOIN "user" u ON ((u.id = ug.user_id)))
     JOIN "group" g ON ((g.id = ug.group_id)));


ALTER TABLE security.user_roles OWNER TO postgres;

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
    batch_item_req_id integer,
    transaction_type_id smallint
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


--
-- Name: transaction_type; Type: TABLE; Schema: transaction; Owner: postgres; Tablespace: 
--

CREATE TABLE transaction_type (
    id smallint NOT NULL,
    code character varying(50),
    description character varying(100)
);


ALTER TABLE transaction.transaction_type OWNER TO postgres;

--
-- Name: transaction_type_id_seq; Type: SEQUENCE; Schema: transaction; Owner: postgres
--

CREATE SEQUENCE transaction_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transaction.transaction_type_id_seq OWNER TO postgres;

--
-- Name: transaction_type_id_seq; Type: SEQUENCE OWNED BY; Schema: transaction; Owner: postgres
--

ALTER SEQUENCE transaction_type_id_seq OWNED BY transaction_type.id;


SET search_path = audit, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit_trail ALTER COLUMN id SET DEFAULT nextval('audit_trail_id_seq'::regclass);


SET search_path = line_clearance, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY dispensing_area_line_clearance ALTER COLUMN id SET DEFAULT nextval('dispensing_area_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY type ALTER COLUMN id SET DEFAULT nextval('type_id_seq'::regclass);


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

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY unit ALTER COLUMN id SET DEFAULT nextval('unit_id_seq'::regclass);


SET search_path = mbr, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY batch_item_requirement ALTER COLUMN id SET DEFAULT nextval('batch_item_requirement_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY bottling_procedure ALTER COLUMN id SET DEFAULT nextval('bottling_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY coding_spec ALTER COLUMN id SET DEFAULT nextval('coding_spec_id_seq'::regclass);


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

ALTER TABLE ONLY main_procedure ALTER COLUMN id SET DEFAULT nextval('main_procedure_id_seq'::regclass);


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

ALTER TABLE ONLY packaging_procedure ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_operation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY powder_filling_procedure ALTER COLUMN id SET DEFAULT nextval('packaging_procedure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement ALTER COLUMN id SET DEFAULT nextval('raw_material_requirement_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY sub_procedure ALTER COLUMN id SET DEFAULT nextval('sub_procedure_id_seq'::regclass);


SET search_path = security, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY "group" ALTER COLUMN id SET DEFAULT nextval('group_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY method ALTER COLUMN id SET DEFAULT nextval('method_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY method_sub_method ALTER COLUMN id SET DEFAULT nextval('method_sub_method_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('id_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY role_method ALTER COLUMN id SET DEFAULT nextval('access_right_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY sub_method ALTER COLUMN id SET DEFAULT nextval('submethod_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_group ALTER COLUMN id SET DEFAULT nextval('user_group_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_role ALTER COLUMN id SET DEFAULT nextval('group_role_id_seq'::regclass);


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


--
-- Name: id; Type: DEFAULT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY transaction_type ALTER COLUMN id SET DEFAULT nextval('transaction_type_id_seq'::regclass);


SET search_path = audit, pg_catalog;

--
-- Data for Name: audit_trail; Type: TABLE DATA; Schema: audit; Owner: postgres
--

INSERT INTO audit_trail VALUES (68, 'mainevillarias@gmail.com', '2015-08-19 16:31:31.627', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":145,"stock_card_id":0,"qty":512.0,"unit_id":5,"mbr_id":0}');
INSERT INTO audit_trail VALUES (69, 'mainevillarias@gmail.com', '2015-08-19 16:31:31.768', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":146,"stock_card_id":0,"qty":100.0,"unit_id":9,"mbr_id":0}');
INSERT INTO audit_trail VALUES (70, 'mainevillarias@gmail.com', '2015-08-19 16:31:31.866', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (71, 'mainevillarias@gmail.com', '2015-08-19 16:31:40.09', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":145,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (72, 'mainevillarias@gmail.com', '2015-08-19 16:31:40.19', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":146,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (73, 'mainevillarias@gmail.com', '2015-08-19 16:31:40.266', 'mbr', '/mbr/mbr/pst_cancel_reservation', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}');
INSERT INTO audit_trail VALUES (74, 'mainevillarias@gmail.com', '2015-08-19 16:34:25.671', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":132,"product_id":70,"batchSize":10.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ikm","status":"PENDING"}');
INSERT INTO audit_trail VALUES (75, 'mainevillarias@gmail.com', '2015-08-19 16:46:42.756', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":147,"stock_card_id":0,"qty":512.0,"unit_id":5,"mbr_id":0}');
INSERT INTO audit_trail VALUES (76, 'mainevillarias@gmail.com', '2015-08-19 16:46:43.028', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":148,"stock_card_id":0,"qty":100.0,"unit_id":9,"mbr_id":0}');
INSERT INTO audit_trail VALUES (77, 'mainevillarias@gmail.com', '2015-08-19 16:46:43.129', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (78, 'mainevillarias@gmail.com', '2015-08-19 17:09:34.313', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":147,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (79, 'mainevillarias@gmail.com', '2015-08-19 17:09:34.415', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":148,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (80, 'mainevillarias@gmail.com', '2015-08-19 17:09:34.49', 'mbr', '/mbr/mbr/pst_cancel_reservation', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}');
INSERT INTO audit_trail VALUES (81, 'mainevillarias@gmail.com', '2015-08-19 17:09:46.214', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":149,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}');
INSERT INTO audit_trail VALUES (82, 'mainevillarias@gmail.com', '2015-08-19 17:09:46.288', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":150,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}');
INSERT INTO audit_trail VALUES (83, 'mainevillarias@gmail.com', '2015-08-19 17:09:46.352', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (84, 'mainevillarias@gmail.com', '2015-08-20 09:34:23.262', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":149,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (85, 'mainevillarias@gmail.com', '2015-08-20 09:34:23.491', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":150,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (86, 'mainevillarias@gmail.com', '2015-08-20 09:34:23.64', 'mbr', '/mbr/mbr/pst_cancel_reservation', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}');
INSERT INTO audit_trail VALUES (87, 'mainevillarias@gmail.com', '2015-08-20 13:21:09.619', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":151,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}');
INSERT INTO audit_trail VALUES (88, 'mainevillarias@gmail.com', '2015-08-20 13:21:10.078', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":152,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}');
INSERT INTO audit_trail VALUES (89, 'mainevillarias@gmail.com', '2015-08-20 13:21:10.204', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (90, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.094', 'pack_size', '/main/pack_size/pst_new_pack_size', 'INSERT', '', '{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}');
INSERT INTO audit_trail VALUES (91, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.428', 'product', '/main/product/pst_new_product', 'INSERT', '', '{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}');
INSERT INTO audit_trail VALUES (92, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.494', 'udf', '/mbr/udf/pst_new_udf', 'INSERT', '', '{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"}}');
INSERT INTO audit_trail VALUES (93, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.61', 'manufacturing_procedure', '/mbr/manufacturing_procedure/pst_new_mfg_proc', 'INSERT', '', '{}');
INSERT INTO audit_trail VALUES (101, 'mainevillarias@gmail.com', '2015-08-24 17:07:14.615', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}');
INSERT INTO audit_trail VALUES (94, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.825', 'raw_material_requirement', '/mbr/raw_material_requirement/pst_new_raw_material_req', 'INSERT', '', '{"id":87,"itemId":{},"quantity":111.0,"unitId":{"id":6,"name":"g"},"udfId":{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"},"productId":{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}},"part":1}');
INSERT INTO audit_trail VALUES (95, 'mainevillarias@gmail.com', '2015-08-20 16:32:25.95', 'packaging_material_requirement', '/mbr/packaging_material_requirement/pst_packg_material_req', 'INSERT', '', '{"id":56,"itemId":{},"quantity":1.0,"unitId":{"id":9,"name":"pcs"},"udfId":{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"},"productId":{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}}}');
INSERT INTO audit_trail VALUES (96, 'mainevillarias@gmail.com', '2015-08-20 16:32:26.087', 'compounding_procedure', '/mbr/compounding_procedure/pst_new_compounding_proc', 'INSERT', '', '{"stepNumber":1,"header":"mix all","footer":true,"manufacturingProcedureId":{}}');
INSERT INTO audit_trail VALUES (97, 'mainevillarias@gmail.com', '2015-08-20 16:32:26.294', 'dosage', '/mbr/dosage/pst_new_dosage', 'INSERT', '', '{"id":39,"rawMaterialRequirementId":{"id":87,"itemId":{},"quantity":111.0,"unitId":{"id":6,"name":"g"},"part":1},"percentMultiplier":1.0,"compoundingProcedureId":{"stepNumber":1,"header":"mix all","footer":true,"manufacturingProcedureId":{}}}');
INSERT INTO audit_trail VALUES (98, 'mainevillarias@gmail.com', '2015-08-20 16:32:26.408', 'equipment_requirement', '/mbr/equipment_requirement/pst_create_new_equip_req', 'INSERT', '', '{"id":31,"equipmentId":{"id":2,"code":"NEQ-130","name":"Encapsulating Machine"},"manufacturingProcedureId":{},"procedure":"COMPOUNDING"}');
INSERT INTO audit_trail VALUES (99, 'mainevillarias@gmail.com', '2015-08-20 16:32:26.514', 'packaging_operation', '/mbr/packaging_operation/pst_create_new_packg_operation', 'INSERT', '', '{"id":41,"stepNumber":1,"header":"packaging opt","part":1,"manufacturingProcedureId":{},"doneBy":"","checkedBy":""}');
INSERT INTO audit_trail VALUES (100, 'mainevillarias@gmail.com', '2015-08-20 16:32:26.602', 'powder_filling_procedure', '/mbr/powder_filling/pst_new_powder_filling', 'INSERT', '', '{"id":6,"stepNumber":1,"instruction":"procedure filling","requiresEquipment":false,"doneBy":"","checkedBy":"","manufacturingProcedureId":{}}');
INSERT INTO audit_trail VALUES (102, 'mainevillarias@gmail.com', '2015-08-25 09:47:15.584', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":134,"product_id":71,"batchSize":1.0,"batchNo":"batch1","unit_id":1,"mfgDate":"2015-08-28T00:00:00","expDate":"2018-08-28T00:00:00","poNo":"ee","status":"PENDING"}');
INSERT INTO audit_trail VALUES (103, 'mainevillarias@gmail.com', '2015-08-25 09:50:46.482', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":135,"product_id":71,"batchSize":15.0,"batchNo":"batch1","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"dede","status":"PENDING"}');
INSERT INTO audit_trail VALUES (104, 'mainevillarias@gmail.com', '2015-08-25 09:55:12.388', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":136,"product_id":71,"batchSize":150.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"huh","status":"PENDING"}');
INSERT INTO audit_trail VALUES (105, 'mainevillarias@gmail.com', '2015-08-25 09:56:08.573', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":137,"product_id":71,"batchSize":200.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-19T00:00:00","expDate":"2018-08-19T00:00:00","poNo":"ttt","status":"PENDING"}');
INSERT INTO audit_trail VALUES (106, 'mainevillarias@gmail.com', '2015-08-25 10:24:17.451', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":138,"product_id":71,"batchSize":100.0,"batchNo":"dsds","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"dsds","status":"PENDING"}');
INSERT INTO audit_trail VALUES (107, 'mainevillarias@gmail.com', '2015-08-25 10:27:26.922', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":139,"product_id":70,"batchSize":1.0,"batchNo":"dddd","unit_id":7,"mfgDate":"2015-08-13T00:00:00","expDate":"2018-08-13T00:00:00","poNo":"dddd","status":"PENDING"}');
INSERT INTO audit_trail VALUES (108, 'mainevillarias@gmail.com', '2015-08-25 11:16:42.053', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":153,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":133}');
INSERT INTO audit_trail VALUES (109, 'mainevillarias@gmail.com', '2015-08-25 11:16:42.431', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":154,"stock_card_id":69,"qty":700.0,"unit_id":9,"mbr_id":133}');
INSERT INTO audit_trail VALUES (110, 'mainevillarias@gmail.com', '2015-08-25 11:16:42.563', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}', '{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (111, 'mainevillarias@gmail.com', '2015-08-25 13:36:08.668', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":140,"product_id":71,"batchSize":57.0,"batchNo":"rtgf","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"5tgb","status":"PENDING"}');
INSERT INTO audit_trail VALUES (112, 'mainevillarias@gmail.com', '2015-08-25 13:44:08.781', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":151,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (113, 'mainevillarias@gmail.com', '2015-08-25 13:44:08.96', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":152,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}', '');
INSERT INTO audit_trail VALUES (114, 'mainevillarias@gmail.com', '2015-08-25 13:44:09.074', 'mbr', '/mbr/mbr/pst_cancel_reservation', 'UPDATE', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}', '{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}');
INSERT INTO audit_trail VALUES (115, 'mainevillarias@gmail.com', '2015-08-25 13:44:11.23', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":153,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":133}', '');
INSERT INTO audit_trail VALUES (116, 'mainevillarias@gmail.com', '2015-08-25 13:44:11.367', 'stock_card_txn', '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'DELETE', '{"id":154,"stock_card_id":69,"qty":700.0,"unit_id":9,"mbr_id":133}', '');
INSERT INTO audit_trail VALUES (117, 'mainevillarias@gmail.com', '2015-08-25 13:44:11.47', 'mbr', '/mbr/mbr/pst_cancel_reservation', 'UPDATE', '{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"RESERVED"}', '{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}');
INSERT INTO audit_trail VALUES (118, 'mainevillarias@gmail.com', '2015-08-25 13:45:08.583', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":141,"product_id":71,"batchSize":1.0,"batchNo":"eee","unit_id":6,"mfgDate":"2015-08-27T00:00:00","expDate":"2018-08-27T00:00:00","poNo":"eee","status":"PENDING"}');
INSERT INTO audit_trail VALUES (119, 'mainevillarias@gmail.com', '2015-08-25 13:46:57.627', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":142,"product_id":71,"batchSize":20.0,"batchNo":"batch5","unit_id":6,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"po09","status":"PENDING"}');
INSERT INTO audit_trail VALUES (120, 'mainevillarias@gmail.com', '2015-08-25 14:15:09.748', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":143,"product_id":71,"batchSize":9.0,"batchNo":"bght","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"po9","status":"PENDING"}');
INSERT INTO audit_trail VALUES (121, 'mainevillarias@gmail.com', '2015-08-25 14:20:31.985', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":144,"product_id":71,"batchSize":1.0,"batchNo":"iii","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"poi","status":"PENDING"}');
INSERT INTO audit_trail VALUES (122, 'mainevillarias@gmail.com', '2015-08-25 14:25:30.114', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":145,"product_id":70,"batchSize":67.0,"batchNo":"777","unit_id":5,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"777","status":"PENDING"}');
INSERT INTO audit_trail VALUES (123, 'mainevillarias@gmail.com', '2015-08-25 14:32:32.729', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"PENDING"}');
INSERT INTO audit_trail VALUES (124, 'mainevillarias@gmail.com', '2015-08-25 14:34:23.007', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":147,"product_id":71,"batchSize":1.0,"batchNo":"jikm","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"pokl","status":"PENDING"}');
INSERT INTO audit_trail VALUES (125, 'mainevillarias@gmail.com', '2015-08-25 14:40:17.226', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":148,"product_id":71,"batchSize":12.0,"batchNo":"batch44","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"poiuyt","status":"PENDING"}');
INSERT INTO audit_trail VALUES (126, 'mainevillarias@gmail.com', '2015-08-25 14:41:28.886', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":149,"product_id":70,"batchSize":12.0,"batchNo":"ee","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"eee","status":"PENDING"}');
INSERT INTO audit_trail VALUES (127, 'mainevillarias@gmail.com', '2015-08-25 14:44:30.876', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":150,"product_id":71,"batchSize":12.0,"batchNo":"ee","unit_id":5,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"ee","status":"PENDING"}');
INSERT INTO audit_trail VALUES (128, 'mainevillarias@gmail.com', '2015-08-25 14:47:58.891', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":151,"product_id":71,"batchSize":12.0,"batchNo":"dwd","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"ded","status":"PENDING"}');
INSERT INTO audit_trail VALUES (129, 'mainevillarias@gmail.com', '2015-08-25 14:49:39.795', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":152,"product_id":71,"batchSize":90.0,"batchNo":"jjj","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"jjj","status":"PENDING"}');
INSERT INTO audit_trail VALUES (130, 'mainevillarias@gmail.com', '2015-08-25 14:51:57.369', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"PENDING"}');
INSERT INTO audit_trail VALUES (131, 'mainevillarias@gmail.com', '2015-08-25 15:00:35.176', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":154,"product_id":71,"batchSize":78.0,"batchNo":"yy","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"yy","status":"PENDING"}');
INSERT INTO audit_trail VALUES (132, 'mainevillarias@gmail.com', '2015-08-25 15:29:07.577', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":155,"product_id":71,"batchSize":123.0,"batchNo":"333","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"333","status":"PENDING"}');
INSERT INTO audit_trail VALUES (133, 'mainevillarias@gmail.com', '2015-08-25 15:31:02.111', 'mbr', '/mbr/mbr/pst_new_batch', 'INSERT', '', '{"id":156,"product_id":70,"batchSize":15.0,"batchNo":"555","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"55","status":"PENDING"}');
INSERT INTO audit_trail VALUES (134, 'mainevillarias@gmail.com', '2015-08-25 15:55:08.38', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":155,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":146}');
INSERT INTO audit_trail VALUES (135, 'mainevillarias@gmail.com', '2015-08-25 15:55:08.446', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":156,"stock_card_id":69,"qty":1.0,"unit_id":9,"mbr_id":146}');
INSERT INTO audit_trail VALUES (136, 'mainevillarias@gmail.com', '2015-08-25 15:55:08.517', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"PENDING"}', '{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"RESERVED"}');
INSERT INTO audit_trail VALUES (137, 'mainevillarias@gmail.com', '2015-08-25 15:56:57.177', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":157,"stock_card_id":68,"qty":45.568,"unit_id":6,"mbr_id":153}');
INSERT INTO audit_trail VALUES (138, 'mainevillarias@gmail.com', '2015-08-25 15:56:57.238', 'stock_card_txn', '/transaction/stock_card_txn/pst_new_stock_card_txn', 'INSERT', '', '{"id":158,"stock_card_id":69,"qty":89.0,"unit_id":9,"mbr_id":153}');
INSERT INTO audit_trail VALUES (139, 'mainevillarias@gmail.com', '2015-08-25 15:56:57.307', 'mbr', '/mbr/mbr/pst_reserve_mbr', 'UPDATE', '{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"PENDING"}', '{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"RESERVED"}');


--
-- Name: audit_trail_id_seq; Type: SEQUENCE SET; Schema: audit; Owner: postgres
--

SELECT pg_catalog.setval('audit_trail_id_seq', 139, true);


SET search_path = line_clearance, pg_catalog;

--
-- Data for Name: coding_room_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO coding_room_line_clearance VALUES (1, 1, 'Are there leftover materials from previous batch?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (2, 2, 'Is the room / work area clean and in order?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (3, 3, 'Is the coding machine and accessories clean and bear equipment clean tags?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (4, 4, 'Are all the printed materials required for coding available and bear approved QA stickers, as applicable?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (5, 5, 'Are the workers wearing the correct / complete uniform and other suitable protective wear?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (6, 6, 'Is the PO (Packaging Order) available in the working area?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (7, 7, 'Is the Batch No. in the Coding Request (CR) consistent with the Batch No. reflected in the Batch Packaging Order (PO)?', '', '', 1, 3);
INSERT INTO coding_room_line_clearance VALUES (8, 1, 'Are all unused / rejected materials from previous run recorded and reconciled?', '', '', 2, 3);
INSERT INTO coding_room_line_clearance VALUES (9, 2, 'Are the coded materials labeled and "batched" and secured in a rack designated for?', '', '', 2, 3);
INSERT INTO coding_room_line_clearance VALUES (10, 3, 'Are all machines unplugged and switched off?', '', '', 2, 3);
INSERT INTO coding_room_line_clearance VALUES (11, 4, 'Is the MBR properly / completely documented and signed?', '', '', 2, 3);
INSERT INTO coding_room_line_clearance VALUES (12, 5, 'Are finished goods / work-in-process from the previous batch removed from the area?', '', '', 2, 3);


--
-- Data for Name: compounding_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO compounding_area_line_clearance VALUES (1, 1, 'All materials from previous operation have been removed in the compounding area.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (2, 2, 'Unnecessary documents, product identification of previous product have been removed.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (3, 3, 'Check the entire area for cleanliness. Usage logbooks are available in their designated place.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (4, 4, 'Room production identification tag is properly attached.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (5, 5, 'Check the availability of the Approved Manufacturing Batch Record (MBR).', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (6, 6, 'Check for the correctness  and completeness of the raw materials based on the MBR.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (7, 7, 'Equipment, Tools and Accessories
 7.1 Paddle Mixer cleaned, sanitized and in good condition.
 7.2 Blue Drums with double-lined PE Bag cleaned, sanitized and in good condition.
 7.3 Dust collector cleaned and in good condition.
 7.4 Mesh Screen, Scoops and Spatulas were cleaned, sanitized and in good condition.
 7.5 With Certified Clean Tag attached and valid cleanliness.
 7.6 In-Process Tag is properly accomplished and attached.', '', '', 1, 3);
INSERT INTO compounding_area_line_clearance VALUES (8, 8, 'Check if the temperature and %RH is within specification/s.
 Temp. Specification: NMT 25°C       Actual Reading:_________________
 %RH Specification: NMT 50%       Actual Reading:_________________', '', '', 1, 3);


--
-- Name: dispensing_area_id_seq; Type: SEQUENCE SET; Schema: line_clearance; Owner: postgres
--

SELECT pg_catalog.setval('dispensing_area_id_seq', 39, true);


--
-- Data for Name: dispensing_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO dispensing_area_line_clearance VALUES (1, 1, 'All materials from previous operation have been removed.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (2, 1, 'Check if the MBR is completely filled-up and signed.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (3, 2, 'Equipment and accessories are cleaned, cleared of previous materials.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (22, 3, 'Unnecessary documents, product identification of previous have been removed. ', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (23, 4, 'Room status tag is properly accomplished and attached.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (24, 5, 'The whole premise has been cleaned. Usage logbooks are available in their designated place.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (25, 6, 'Weighing scale is cleaned, calibrated and ready to use.
Calibration Date: _________________    Calibration Due:_________________', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (26, 7, 'Check for the availability of approved and complete MBR.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (27, 8, 'Check the completeness and correctness if the raw materials versus MBR and with "QC Approved" tag.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (28, 9, 'Check if the scoops & other dispensing tools to be used are complete and with "Certified Clean" tag.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (29, 10, 'Check if the temperature is within the specification.
Temp. Specification: NMT 25°C      Actual Reading: ________', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (30, 11, 'Check if the RH reading is within the specification.
RH Specification: NMT 50%      Actual Reading: __________', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (31, 12, 'Sanitize the area with 70% ethyl alcohol.', NULL, NULL, 1, 3);
INSERT INTO dispensing_area_line_clearance VALUES (32, 2, 'Check if the dispensed raw material tags are filled up, signed and attached securely on the weighed raw materials.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (33, 3, 'Transfer weighed raw materials from the weighing area to the staging area.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (34, 4, 'Properly endorsed the batched materials to the production.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (35, 5, 'Remove all used dispensing scoops/equipments from the area.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (36, 6, 'Account and return all raw materials in their designated places.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (37, 7, 'Clean material/powder spills on the balance, floor and weighing balance according to written procedure.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (38, 8, 'Store equipment/utensils with status tags after cleaning in their designated places.', NULL, NULL, 2, 3);
INSERT INTO dispensing_area_line_clearance VALUES (39, 9, 'Update usage logbooks for dispensing room and equipment.', NULL, NULL, 2, 3);


--
-- Data for Name: filling_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO filling_area_line_clearance VALUES (1, 1, 'Check if the entire filling including the equipment is cleared of all excess product of previous lot.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (2, 2, 'Check if the area is cleared of any documents from previously used and product previously compounded.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (3, 3, 'Unnecessary documents have been removed from the area.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (4, 4, 'Inspect the entire premises for cleanliness. Usage logbooks are available in their designated place.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (5, 5, 'Room Production Identification Tag is attached.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (6, 6, 'Check for the availability of the MBR.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (7, 7, 'Check the correctness and completeness of the packaging materials received based on the specification of the MBR.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (8, 8, 'Check if the equipment to use has its valid Certified Clean Tag.', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (10, 10, 'Check and monitor room temperature (NMT 25°C) and Relative Humidity (NMT 50%) using NF-QA-011-01-01
(Controlled Area).
Actual Temperature Reading: _______________   Actual Reading: _______________', '', '', 1, 3);
INSERT INTO filling_area_line_clearance VALUES (11, 1, 'Check if the MBR is properly / completely documented and signed.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (12, 2, 'Check if the product material tags are properly signed.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (13, 3, 'Remove materials used for the ended lot from the area.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (14, 4, 'Clean all equipment from the bulk powder and packaging materials used.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (15, 5, 'Removed all rejected packaging materials.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (16, 6, 'Clean and clear the filling area including the floor, inspection tables, stools and weighing balance.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (17, 7, 'Place an equipment status tag after cleaning the equipment.', '', '', 2, 3);
INSERT INTO filling_area_line_clearance VALUES (9, 9, 'Equipment, Tools and Accessories
 9.1 Scoops cleaned, sanitized and in good condition.
 9.2 Weighing Balance and scoops cleaned, sanitized and in good condition.', '', '', 1, 3);


--
-- Data for Name: labeling_packg_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO labeling_packg_area_line_clearance VALUES (1, 1, 'Check if the entire packaging area is cleared of all excess product of previous lot.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (2, 2, 'Check if the area is cleared of any documents and/or products from the previous finished goods.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (3, 3, 'Remove all unnecessary documents if these are no required for the work-in-process.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (4, 4, 'Inspect the whole area for cleanliness. Usage logbooks are available in their designated place.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (5, 5, 'Check for the availability of the MBR.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (6, 6, 'Check the correctness and completeness of the materials needed received based on the MBR.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (7, 7, 'Check if the temperature is NMT 25°C. (Actual Temp. ______________)', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (8, 8, 'Check for the completeness and correctness of the total filled plastic bags received based on MBR.', '', '', 1, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (9, 1, 'Check if the MBR is properly / completely documented and signed.', '', '', 2, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (10, 2, 'Check if the finished product tags are properly signed and affixed on the finished product.', '', '', 2, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (11, 3, 'Remove all finished product of the ended lot from the area.', '', '', 2, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (12, 4, 'Clean and clear the packaging area including the floor and tables.', '', '', 2, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (13, 5, 'Account and reconcile the finished product before transferring to quarantine area.', '', '', 2, 3);
INSERT INTO labeling_packg_area_line_clearance VALUES (14, 6, 'Clean and clear the packaging area including the floor & tables.', '', '', 2, 3);


--
-- Data for Name: type; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

INSERT INTO type VALUES (1, 'START', 'Line clearance for starting');
INSERT INTO type VALUES (2, 'END', 'Line clearance for ending');


--
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: line_clearance; Owner: postgres
--

SELECT pg_catalog.setval('type_id_seq', 2, true);


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
INSERT INTO equipment VALUES (9, 'NEQ-011', 'Ink Jet Coding Machine');


--
-- Name: equipment_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('equipment_id_seq', 9, true);


--
-- Data for Name: pack_size; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO pack_size VALUES (39, 10, 7, 3);
INSERT INTO pack_size VALUES (40, 100, 6, 1);
INSERT INTO pack_size VALUES (41, 500, 6, 1);


--
-- Name: pack_size_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('pack_size_id_seq', 41, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO product VALUES (70, 'p1', 'brand 1', 'gen 1', 1, 1, 'vr01', 3, 1, 41);
INSERT INTO product VALUES (71, 'p2', 'colistilak fpp', 'colistin sulfate', 2, 5, 'vrm-12-557', 3, 3, 39);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 71, true);


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
-- Data for Name: batch_item_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO batch_item_requirement VALUES (31, 174, 647, 439.87, 6, 220.83199999999999, 7, NULL, NULL, 'ALLOCATED', 1);
INSERT INTO batch_item_requirement VALUES (32, 174, 647, 439.87, 6, 220.83199999999999, 7, NULL, NULL, 'ALLOCATED', 2);
INSERT INTO batch_item_requirement VALUES (33, 174, 648, 5, 6, 2.5099999999999998, 7, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (34, 174, 1356, 10, 6, 5.0199999999999996, 7, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (35, 174, 667, 105.26000000000001, 6, 52.844999999999999, 7, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (36, 174, 1357, 1.02, 9, 512, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (37, 174, 1014, 0.10199999999999999, 9, 51, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (38, 174, 1358, 1.02, 9, 512, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (39, 174, 977, 1.02, 9, 512, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (40, 174, 1070, 0.01, 11, 5, 11, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (41, 174, 1054, 0.10199999999999999, 9, 51, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (42, 174, 1359, 0.012, 9, 6, 9, NULL, NULL, 'ALLOCATED', 0);
INSERT INTO batch_item_requirement VALUES (43, 174, 1360, 0.10199999999999999, 9, 51, 9, NULL, NULL, 'ALLOCATED', 0);


--
-- Name: batch_item_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('batch_item_requirement_id_seq', 43, true);


--
-- Data for Name: bottling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--



--
-- Name: bottling_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('bottling_procedure_id_seq', 5, true);


--
-- Data for Name: coding_spec; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO coding_spec VALUES (1, 'Check the availability and cleanliness of the manufacturing equipment.', true, NULL, NULL, 3, 1);
INSERT INTO coding_spec VALUES (3, 'Code the label. Inspect for defects.', false, NULL, NULL, 3, 3);
INSERT INTO coding_spec VALUES (4, 'Submit the first coded label to QA for buy-off.', false, NULL, NULL, 3, 4);
INSERT INTO coding_spec VALUES (6, 'Transfer the coded labels to the labeling area.', false, NULL, NULL, 3, 6);
INSERT INTO coding_spec VALUES (7, 'Attach coded label sample to the box provided.', false, NULL, NULL, 3, 7);
INSERT INTO coding_spec VALUES (5, 'Count rejected and excess labels to the staging area.
Quantity Coded:
Quantity Rejected:
Percent Accepted:
Percent Rejected:', false, NULL, NULL, 3, 5);
INSERT INTO coding_spec VALUES (2, 'Set coding machine with the correct product information.
BATCH #:
MFG. DATE:
EXP. DATE:', NULL, NULL, NULL, 3, 2);


--
-- Name: coding_spec_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('coding_spec_id_seq', 7, true);


--
-- Data for Name: compounding_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO compounding_procedure VALUES (39, 1, 'State equipments/machines/utensils required.', false, NULL, NULL, 71, true, NULL, false);
INSERT INTO compounding_procedure VALUES (40, 2, 'Transfer the approved batched materials from the staging area to the Compounding Room.', false, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (41, 3, 'Monitor Room Temperature (NMT 25°C) and Relative Humidity (NMT 50%) using NF-QA-011-01-01 (Controlled Area) every 30 minutes throughout compounding.', false, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (42, 4, 'Check the completeness of issued materials.', false, NULL, NULL, 71, false, NULL, true);
INSERT INTO compounding_procedure VALUES (43, 5, 'Pass the following materials into Screen Mesh #20 then load into the paddle mixer.', true, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (44, 6, 'Mix the bulk for 30 minutes.', true, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (45, 7, 'Load the 2nd part-Calcium Carbonate into the paddle mixer passing thru screen mesh #20 then mix for 30 minutes.', true, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (46, 8, 'Transfer the bulk into blue drums and record the final weight.
   a. Gross Weight: __________
   b. Tare Weight: ___________
   c. Net Weight: ____________', true, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (48, 10, 'Cover the blue drums and put silica gel (2 silica gels) on top of each drum. Store the drums in a controlled room temperature while awaiting for QC Disposition. Attach quarantine sticker.', true, NULL, NULL, 71, false, NULL, false);
INSERT INTO compounding_procedure VALUES (49, 11, 'In-Process Control.
    a. Appearance: Off-white to gray white free flowing powder.
    b. Moisture Content: NMT 2.0%
    c. Assay:
       Colistin Sulfate: 90.00% - 120.00%', false, NULL, NULL, 71, false, 'QC Results:
    a. Appearance:
    b. Moisture Content:
    c. Assay:
       Colistin Sulfate:

Performed by: _____________
', NULL);
INSERT INTO compounding_procedure VALUES (47, 9, 'QC to collect sample for in-process Testing.
Top: 10g
Middle: 10g
Bottom: 10g
Moisture Content: 10g', true, NULL, NULL, 71, false, 'Sample Collected by/Date:', false);


--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('compounding_procedure_id_seq', 49, true);


--
-- Data for Name: dosage; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO dosage VALUES (40, 89, 1, 43);
INSERT INTO dosage VALUES (41, 91, 1, 43);
INSERT INTO dosage VALUES (42, 92, 1, 43);
INSERT INTO dosage VALUES (43, 93, 1, 43);
INSERT INTO dosage VALUES (44, 90, 1, 45);


--
-- Name: dosage_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('dosage_id_seq', 44, true);


--
-- Data for Name: equipment_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO equipment_requirement VALUES (34, 71, 1, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (35, 71, 7, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (36, 71, 3, 'COMPOUNDING');
INSERT INTO equipment_requirement VALUES (37, 71, 3, 'POWDER_FILLING');
INSERT INTO equipment_requirement VALUES (38, 71, 5, 'POWDER_FILLING');
INSERT INTO equipment_requirement VALUES (39, 71, 9, 'CODING_SPECS');


--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_equipment_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_id_seq', 39, true);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_manufacturing_procedure_id_seq', 1, false);


--
-- Data for Name: main_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO main_procedure VALUES (1, 'A', 'Documentation/Regulatory', 3);
INSERT INTO main_procedure VALUES (2, 'B', 'Dispensing', 3);
INSERT INTO main_procedure VALUES (3, 'C', 'Compounding', 3);
INSERT INTO main_procedure VALUES (4, 'D', 'Filling', 3);
INSERT INTO main_procedure VALUES (5, 'E', 'Packaging', 3);
INSERT INTO main_procedure VALUES (6, 'F', 'Finished Goods', 3);
INSERT INTO main_procedure VALUES (7, 'G', 'Finished Product (Approval and Release)', 3);
INSERT INTO main_procedure VALUES (8, 'H', 'Others', 3);
INSERT INTO main_procedure VALUES (9, '', 'If applicable', 3);


--
-- Name: main_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('main_procedure_id_seq', 9, true);


--
-- Data for Name: manufacturing_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO manufacturing_procedure VALUES (70);
INSERT INTO manufacturing_procedure VALUES (71);


--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('manufacturing_procedure_id_seq', 4, true);


--
-- Data for Name: mbr; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO mbr VALUES (174, 71, 502.04000000000002, '154812', 7, '2015-08-15', '2018-08-15', '128517', 'PRINTED');


--
-- Name: mbr_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('mbr_id_seq', 174, true);


--
-- Data for Name: packaging_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_material_requirement VALUES (57, 1.02, 9, 71, 1357);
INSERT INTO packaging_material_requirement VALUES (58, 0.10199999999999999, 9, 71, 1014);
INSERT INTO packaging_material_requirement VALUES (59, 1.02, 9, 71, 1358);
INSERT INTO packaging_material_requirement VALUES (60, 1.02, 9, 71, 977);
INSERT INTO packaging_material_requirement VALUES (61, 0.01, 11, 71, 1070);
INSERT INTO packaging_material_requirement VALUES (62, 0.10199999999999999, 9, 71, 1054);
INSERT INTO packaging_material_requirement VALUES (63, 0.012, 9, 71, 1359);
INSERT INTO packaging_material_requirement VALUES (64, 0.10199999999999999, 9, 71, 1360);


--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_requirement_id_seq', 64, true);


--
-- Data for Name: packaging_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO packaging_procedure VALUES (45, 3, 'Label the black plastic bag with the correct batch number. manufacturing and expiry date.', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (46, 4, 'Pack the filled transparent plastic bag into black plastic bag and seal using hand sealer.', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (47, 5, 'Pack the filled black plastic bag into pails then cover the pails. (10 bags per pail)', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (48, 6, 'Label the pails with the correct batch number, manufacturing and expiry date.', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (49, 7, 'Fill up the quarantine sticker and place it on the pail, beside the label. Ensure that there are no details omitted from placing the sticker. The loose box will bear the last number.', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (50, 8, 'Monitor and record the gross weight of each pail.', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (43, 1, 'Check the quantity of filled transparent plastic bags.
Total Quantity of transparent plastic bags: _________ pcs', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (44, 2, 'Withdraw packaging materials from the staging area for labeling/packaging material.
Black Plastic Bag: __________
Pails            : __________', 71, 1, NULL, NULL);
INSERT INTO packaging_procedure VALUES (51, 9, 'Withdraw samples for Retention/Stability
Samples for Retention: 1 plastic bag x 1kg
Samples for Stability: N/A', 71, 1, 'Submitted by/date:', 'Received by/date:');
INSERT INTO packaging_procedure VALUES (52, 10, 'Count yield and accomplish finished product reconciliation
Theoretical Yield (TY):__________kg
Actual Yield (AY):__________kg
QC/In-Process samples:__________kg
Total samples
(equivalent weight in kg):__________kg
Retention sample (RS):__________kg
Total Yield *:__________kg
% Yield **:__________%(NLT 98%)
* Total Yield = AY + QC + RS
** % Yield = Total Yield/TY', 71, 2, NULL, NULL);
INSERT INTO packaging_procedure VALUES (53, 11, 'Transfer finished products to the Finished Goods staging area while awaiting batch approval.', 71, 2, NULL, NULL);
INSERT INTO packaging_procedure VALUES (54, 12, 'Count rejected and excess packaging materials for material usage reconciliation, rejected and excess labels and boxes should be submitted to the Production-In-Charge for destruction. Accomplish Packaging Material and Finished Product Reconciliation sheet.', 71, 2, NULL, NULL);
INSERT INTO packaging_procedure VALUES (55, 13, 'Record actual yield in IS/PRIS (Inspection Slip/Production Record Inspection Slip) form and Finished Product Record. Transfer to the Warehouse finished  goods staging area.', 71, 2, NULL, NULL);


--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_id_seq', 12, true);


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_operation_id_seq', 55, true);


--
-- Data for Name: powder_filling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO powder_filling_procedure VALUES (7, 2, 'Transfer the compounded product to the filling section.', 71, false, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (6, 1, 'State the equipment/machine/utensils', 71, true, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (8, 3, 'Fill the approved bulk into transparent plastic bag (1kg) as per weight range. Filled Weight Range: 1000.00g - 1020.00g', 71, false, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (9, 4, 'Monitor and record the weight of the filled plastic baf every 30 minutes in the fill weight monitoring sheet
 -Provide weighing balance to be used during filling since specifications are in Grams.', 71, false, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (10, 5, 'Tie each transparent plastic bag with rubber band.', 71, false, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (11, 6, 'Wipe the plastic bag with lint free cloth and inspect for any holes or leakage.', 71, false, NULL, NULL);
INSERT INTO powder_filling_procedure VALUES (12, 7, 'Submit samples (1kg) to the QC Laboratory for final testing.', 71, false, NULL, NULL);


--
-- Data for Name: primary_secondary_packaging; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO primary_secondary_packaging VALUES (71, 62, NULL);


--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('primary_secondary_packaging_id_seq', 1, true);


--
-- Data for Name: raw_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO raw_material_requirement VALUES (89, 439.87, 6, 71, 1, 647);
INSERT INTO raw_material_requirement VALUES (90, 439.87, 6, 71, 2, 647);
INSERT INTO raw_material_requirement VALUES (91, 5, 6, 71, 0, 648);
INSERT INTO raw_material_requirement VALUES (92, 10, 6, 71, 0, 1356);
INSERT INTO raw_material_requirement VALUES (93, 105.26000000000001, 6, 71, 0, 667);


--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_requirement_id_seq', 93, true);


--
-- Data for Name: sub_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO sub_procedure VALUES (1, 1, 'Batch No., Shelf-life, Pack Size and VR No., of the product is correct.', 1);
INSERT INTO sub_procedure VALUES (2, 2, 'Line clearance accomplished and verified prior to weighing.', 1);
INSERT INTO sub_procedure VALUES (3, 2, 'Raw materials were correct, individually weighed and weights verified by pharmacist.', 2);
INSERT INTO sub_procedure VALUES (4, 3, 'Line clearance checked by Production-In-Charge and verified by QA.', 1);
INSERT INTO sub_procedure VALUES (5, 3, 'Equipment clean tags attached to the MBR.', 2);
INSERT INTO sub_procedure VALUES (6, 3, 'The dispensing tags attached to the weighed raw materials are filed with the batch documents.', 3);
INSERT INTO sub_procedure VALUES (32, 3, 'Production Operator, Production-In-Charge and QA signed the compounding procedures.', 4);
INSERT INTO sub_procedure VALUES (33, 3, 'The batch yield meets acceptance limits.', 5);
INSERT INTO sub_procedure VALUES (34, 3, 'QC test report attached; batch approved by Production, QC and Q for succeeding process.', 6);
INSERT INTO sub_procedure VALUES (35, 4, 'Line clearance for filling were accomplished, checked and verified.', 1);
INSERT INTO sub_procedure VALUES (37, 4, 'The batch yield meets acceptance limits.', 5);
INSERT INTO sub_procedure VALUES (38, 4, 'Process rejects accounted for reconciliation', 6);
INSERT INTO sub_procedure VALUES (39, 4, 'QC test report attached; batch approved by QC for succeeding process.', 7);
INSERT INTO sub_procedure VALUES (40, 5, 'Line clearance for packaging were done and checked.', 1);
INSERT INTO sub_procedure VALUES (41, 5, 'Packaging Direction Record is correct, accomplished and signed by warehouse and production personnel.', 2);
INSERT INTO sub_procedure VALUES (42, 5, 'Coding request and reconciliation are properly accomplished and signed.', 3);
INSERT INTO sub_procedure VALUES (43, 5, 'Page for coded label sample is available.', 5);
INSERT INTO sub_procedure VALUES (44, 5, 'Approved coded specimen is attached to the space provided.', 6);
INSERT INTO sub_procedure VALUES (45, 6, 'QC samples, stability samples and retention samples submitted and received.', 1);
INSERT INTO sub_procedure VALUES (46, 6, 'Reconciliation of approved finished goods and packaging materials usage is accomplished with correct and complete data.', 2);
INSERT INTO sub_procedure VALUES (47, 6, 'Unused and rejected packaging materials and finished goods are accounted and reconciled.', 3);
INSERT INTO sub_procedure VALUES (48, 7, 'All tests are done. Test parameters and technical specifications are correct.', 1);
INSERT INTO sub_procedure VALUES (49, 7, 'All necessary documents are completely attached to the MBR. ie. Work Sheets and attachments, etc.', 2);
INSERT INTO sub_procedure VALUES (50, 8, 'Direct labor sheet accomplished and signed.', 1);
INSERT INTO sub_procedure VALUES (51, 8, 'Material Transfer Slip (MTS) attached to the MBR.', 2);
INSERT INTO sub_procedure VALUES (52, 9, 'Deviation Report attached', 3);
INSERT INTO sub_procedure VALUES (53, 9, 'OOS Report attached', 4);
INSERT INTO sub_procedure VALUES (54, 4, 'Equipment clean tags attached to the MBR.', 2);
INSERT INTO sub_procedure VALUES (55, 4, 'The filling procedures reports properly accomplished and signed.', 3);
INSERT INTO sub_procedure VALUES (36, 4, 'In-process test reports properly accomplished and signed.', 4);
INSERT INTO sub_procedure VALUES (56, 5, 'The inspection and packaging procedures were done, checked and signed.', 4);


--
-- Name: sub_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('sub_procedure_id_seq', 56, true);


--
-- Data for Name: udf; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

INSERT INTO udf VALUES (70, 1, 5);
INSERT INTO udf VALUES (71, 1, 7);


--
-- Name: udf_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('udf_id_seq', 2, true);


SET search_path = security, pg_catalog;

--
-- Name: access_right_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('access_right_id_seq', 17, true);


--
-- Data for Name: group; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO "group" VALUES (2, 'USER');
INSERT INTO "group" VALUES (1, 'ADMIN');


--
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('group_id_seq', 3, true);


--
-- Name: group_role_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('group_role_id_seq', 6, true);


--
-- Name: id_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('id_id_seq', 4, true);


--
-- Data for Name: method; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO method VALUES (53, 'view_batch_record', 'View batch record');
INSERT INTO method VALUES (54, 'create_new_batch', 'Create new batch');
INSERT INTO method VALUES (55, 'view_product_list', 'View product list');
INSERT INTO method VALUES (56, 'create_new_product', 'Create new product');
INSERT INTO method VALUES (57, 'access_batch_projection', 'Access batch projection');
INSERT INTO method VALUES (58, 'print_batch_projection
', 'Print projection (bom)');
INSERT INTO method VALUES (60, 'check_material_availability
', 'Check the batch material requirements availability
');
INSERT INTO method VALUES (59, 'mmd_view_batch_record', 'Allow user to access the mmd batch management which only includes viewing all batch record
');
INSERT INTO method VALUES (61, 'cancel_reservation', 'Cancel material reservation. This will remove allocation of materials for the specified batch.');
INSERT INTO method VALUES (62, 'dispense_batch_materials', 'Dispense all material requirements for the selected batch');
INSERT INTO method VALUES (63, 'print_product_formulation', 'Print the product formulation of the selected batch');
INSERT INTO method VALUES (64, 'reserve_material_req', 'Allocate available materials (reserve) for the selected batch');


--
-- Name: method_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('method_id_seq', 64, true);


--
-- Data for Name: method_sub_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO method_sub_method VALUES (2, 53, 1);
INSERT INTO method_sub_method VALUES (3, 53, 20);
INSERT INTO method_sub_method VALUES (4, 53, 21);
INSERT INTO method_sub_method VALUES (5, 53, 19);
INSERT INTO method_sub_method VALUES (6, 53, 22);
INSERT INTO method_sub_method VALUES (7, 54, 35);
INSERT INTO method_sub_method VALUES (8, 54, 57);
INSERT INTO method_sub_method VALUES (9, 54, 23);
INSERT INTO method_sub_method VALUES (10, 54, 1);
INSERT INTO method_sub_method VALUES (11, 55, 35);
INSERT INTO method_sub_method VALUES (12, 55, 39);
INSERT INTO method_sub_method VALUES (13, 55, 44);
INSERT INTO method_sub_method VALUES (14, 55, 31);
INSERT INTO method_sub_method VALUES (15, 55, 13);
INSERT INTO method_sub_method VALUES (16, 56, 4);
INSERT INTO method_sub_method VALUES (18, 56, 2);
INSERT INTO method_sub_method VALUES (19, 56, 57);
INSERT INTO method_sub_method VALUES (20, 56, 9);
INSERT INTO method_sub_method VALUES (21, 56, 42);
INSERT INTO method_sub_method VALUES (22, 56, 12);
INSERT INTO method_sub_method VALUES (23, 56, 29);
INSERT INTO method_sub_method VALUES (24, 56, 36);
INSERT INTO method_sub_method VALUES (25, 56, 28);
INSERT INTO method_sub_method VALUES (26, 56, 40);
INSERT INTO method_sub_method VALUES (27, 56, 56);
INSERT INTO method_sub_method VALUES (28, 56, 18);
INSERT INTO method_sub_method VALUES (29, 56, 39);
INSERT INTO method_sub_method VALUES (30, 56, 43);
INSERT INTO method_sub_method VALUES (31, 56, 30);
INSERT INTO method_sub_method VALUES (32, 56, 7);
INSERT INTO method_sub_method VALUES (33, 56, 45);
INSERT INTO method_sub_method VALUES (34, 56, 10);
INSERT INTO method_sub_method VALUES (35, 56, 14);
INSERT INTO method_sub_method VALUES (36, 56, 33);
INSERT INTO method_sub_method VALUES (37, 56, 32);
INSERT INTO method_sub_method VALUES (38, 56, 3);
INSERT INTO method_sub_method VALUES (39, 56, 41);
INSERT INTO method_sub_method VALUES (40, 56, 35);
INSERT INTO method_sub_method VALUES (41, 56, 34);
INSERT INTO method_sub_method VALUES (42, 57, 35);
INSERT INTO method_sub_method VALUES (43, 57, 57);
INSERT INTO method_sub_method VALUES (44, 59, 1);
INSERT INTO method_sub_method VALUES (45, 60, 49);
INSERT INTO method_sub_method VALUES (46, 60, 57);
INSERT INTO method_sub_method VALUES (47, 61, 25);
INSERT INTO method_sub_method VALUES (48, 61, 1);
INSERT INTO method_sub_method VALUES (49, 62, 27);
INSERT INTO method_sub_method VALUES (50, 62, 1);
INSERT INTO method_sub_method VALUES (51, 64, 54);
INSERT INTO method_sub_method VALUES (52, 64, 24);
INSERT INTO method_sub_method VALUES (53, 64, 1);
INSERT INTO method_sub_method VALUES (54, 56, 5);
INSERT INTO method_sub_method VALUES (55, 61, 60);
INSERT INTO method_sub_method VALUES (56, 61, 61);
INSERT INTO method_sub_method VALUES (57, 54, 62);


--
-- Name: method_sub_method_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('method_sub_method_id_seq', 57, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO role VALUES (1, 'MMD_ROLE');
INSERT INTO role VALUES (2, 'RND_ROLE');
INSERT INTO role VALUES (3, 'unauthorized_user');
INSERT INTO role VALUES (4, 'tester');


--
-- Data for Name: role_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO role_method VALUES (13, 3, 53);
INSERT INTO role_method VALUES (14, 3, 55);
INSERT INTO role_method VALUES (15, 3, 57);
INSERT INTO role_method VALUES (16, 3, 59);


--
-- Data for Name: sub_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO sub_method VALUES (1, '/mbr/mbr/g_batch_list', 'View batch list');
INSERT INTO sub_method VALUES (2, '/main/area/g_area_list', 'View area list');
INSERT INTO sub_method VALUES (3, '/mbr/bottling_procedure/pst_new_bottling_proc', 'Create bottling procedure');
INSERT INTO sub_method VALUES (4, '/main/classification/g_classification_list', 'View classification list');
INSERT INTO sub_method VALUES (33, '/mbr/packaging_procedure/pst_new_packg_procedure', 'Create packg operation');
INSERT INTO sub_method VALUES (7, '/mbr/compounding_procedure/pst_new_compounding_proc', 'Create compounding procedure');
INSERT INTO sub_method VALUES (8, '/mbr/compounding_procedure/find_by_mfg_id', 'Search compounding procedure by mfg_id');
INSERT INTO sub_method VALUES (9, '/main/container/g_container_list', 'View container list');
INSERT INTO sub_method VALUES (10, '/mbr/dosage/pst_new_dosage', 'Create dosage');
INSERT INTO sub_method VALUES (11, '/mbr/dosage/find_by_cp_id', 'Search compounding procedure by id');
INSERT INTO sub_method VALUES (12, '/main/equipment/g_equipment_list', 'View equipment list');
INSERT INTO sub_method VALUES (13, '/mbr/equipment_requirement/g_find_by_mfg_id_and_procedure', 'Search equipment requirement by mfg_id and procedure');
INSERT INTO sub_method VALUES (14, '/mbr/equipment_requirement/pst_create_new_equip_req', 'Create equipment requirement');
INSERT INTO sub_method VALUES (15, '/sqlsvr_copy/itemc/g_itemc_list', 'View all item in sqlsvr_copy');
INSERT INTO sub_method VALUES (16, '/sqlsvr_copy/item_category_c/g_item_category_list', 'View all item category list');
INSERT INTO sub_method VALUES (17, '/sqlsvr_copy/item_type_c/g_item_type_list', 'View all item type');
INSERT INTO sub_method VALUES (18, '/mbr/manufacturing_procedure/pst_new_mfg_proc', 'Create mfg procedure');
INSERT INTO sub_method VALUES (19, '/mbr/mbr/g_batch_by_stat', 'Search batch by status');
INSERT INTO sub_method VALUES (20, '/mbr/mbr/g_batch_by_batch_no', 'Search batch by batch no');
INSERT INTO sub_method VALUES (21, '/mbr/mbr/g_batch_by_product_code', 'Search batch by product code');
INSERT INTO sub_method VALUES (22, '/mbr/mbr/g_batch_by_area', 'Search batch by area');
INSERT INTO sub_method VALUES (23, '/mbr/mbr/pst_new_batch', 'Create new batch');
INSERT INTO sub_method VALUES (24, '/mbr/mbr/pst_reserve_mbr', 'Update batch status to ''RESERVED''');
INSERT INTO sub_method VALUES (25, '/mbr/mbr/pst_cancel_reservation', 'Update batch status to ''PENDING''');
INSERT INTO sub_method VALUES (26, '/mbr/mbr/pst_print_batch', 'Update batch statys to ''PRINTED''/''RELEASED''');
INSERT INTO sub_method VALUES (27, '/mbr/mbr/pst_dispense_batch_material', 'Update batch status to ''DISPENSED''');
INSERT INTO sub_method VALUES (28, '/main/pack_size/pst_new_pack_size', 'Create new pack size');
INSERT INTO sub_method VALUES (30, '/mbr/packaging_material_requirement/pst_packg_material_req', 'Create packg material requirement');
INSERT INTO sub_method VALUES (31, '/mbr/packaging_material_requirement/g_packg_material_req_by_udf_id', 'Search packg materials by udf_id');
INSERT INTO sub_method VALUES (32, '/mbr/packaging_material_requirement/g_packg_material_req_by_details', 'Search packg material by qty, unit, and udf_id');
INSERT INTO sub_method VALUES (34, '/mbr/powder_filling/pst_new_powder_filling', 'Create powder filling procedure');
INSERT INTO sub_method VALUES (35, '/main/product/g_product_list', 'View product list');
INSERT INTO sub_method VALUES (36, '/main/product/g_is_code_valid', 'Checks if entered product code is valid');
INSERT INTO sub_method VALUES (37, '/main/product/g_primary_packg', 'Search product''s primary packg material');
INSERT INTO sub_method VALUES (38, '/main/product/g_secondary_packg', 'Search product''s secondary packg material');
INSERT INTO sub_method VALUES (39, '/main/product/g_product_by_id', 'Search product by id');
INSERT INTO sub_method VALUES (40, '/main/product/pst_new_product', 'Create product');
INSERT INTO sub_method VALUES (41, '/main/product/pst_prim_sec_packg', 'Set product''s primary and secondary packg material');
INSERT INTO sub_method VALUES (43, '/mbr/raw_material_requirement/pst_new_raw_material_req', 'Create raw material requirement');
INSERT INTO sub_method VALUES (44, '/mbr/raw_material_requirement/g_raw_material_req_by_udf_id', 'Search raw material by udf_id');
INSERT INTO sub_method VALUES (45, '/mbr/raw_material_requirement/g_raw_material_req_by_details', 'Search raw material by details');
INSERT INTO sub_method VALUES (46, '/sqlsvr_copy/stock_card_c/g_stock_card_list', 'View stockcardc list');
INSERT INTO sub_method VALUES (47, '/sqlsvr_copy/stock_card_c/g_stock_card_by_item_cd', 'Search stockcard by item code');
INSERT INTO sub_method VALUES (48, '/sqlsvr_copy/stock_card_c/g_stock_card_by_id', 'Search stockcard by id');
INSERT INTO sub_method VALUES (50, '/sqlsvr_copy/stock_card_c/pst_change_stock_card_status_to_depleted', 'Update stockcard status to depleted');
INSERT INTO sub_method VALUES (51, '/sqlsvr_copy/stock_card_c/g_stockcard_by_control_no', 'Search by stockcard control_no');
INSERT INTO sub_method VALUES (52, '/transaction/stock_card_txn/g_reserved_approved_by_item_cd', 'Search reserved and approved stockcard by item code');
INSERT INTO sub_method VALUES (53, '/transaction/stock_card_txn/g_reserved_approved_by_item_cd_company_cd', 'Search reserved and approved stockcard by item code and company code');
INSERT INTO sub_method VALUES (54, '/transaction/stock_card_txn/pst_new_stock_card_txn', 'Create stockcard transaction (issuance)');
INSERT INTO sub_method VALUES (55, '/mbr/udf/g_udf_by_id', 'Search udf by id');
INSERT INTO sub_method VALUES (56, '/mbr/udf/pst_new_udf', 'Create new udf');
INSERT INTO sub_method VALUES (57, '/main/unit/g_unit_list', 'View unit list');
INSERT INTO sub_method VALUES (58, '/security/user/g_user_list', 'View all users');
INSERT INTO sub_method VALUES (29, '/sqlsvr_copy/item/g_pm_item_list', 'View all packg material');
INSERT INTO sub_method VALUES (42, '/sqlsvr_copy/item/g_rm_item_list', 'View raw material list');
INSERT INTO sub_method VALUES (49, '/sqlsvr_copy/stock_card_c/g_stock_card_by_company_cd_and_item_id', 'Search stockcard by company code and item id');
INSERT INTO sub_method VALUES (5, '/sqlsvr_copy/company/g_company_list', 'View all company list');
INSERT INTO sub_method VALUES (59, '/audit/audit/g_audit_list', 'Get audit list');
INSERT INTO sub_method VALUES (60, '/transaction/stock_card_txn/pst_delete_stock_card_txn', 'Delete stockcardtxn in database');
INSERT INTO sub_method VALUES (61, '/mbr/mbr/g_batch_stock_card_txn_list', 'Get batch stockcard transaction');
INSERT INTO sub_method VALUES (62, '/mbr/mbr/g_batch_by_id', 'Get batch by id');
INSERT INTO sub_method VALUES (63, '/transaction/stock_card_txn/g_stockcardtxn_by_batch_no', 'Get all stockcard transaction with the same batch no');
INSERT INTO sub_method VALUES (64, '/transaction/stock_card_txn/g_stockcardtxn_by_batch_no_and_item_category', 'Get all stockcard transaction with the same batch no and item category');
INSERT INTO sub_method VALUES (65, '/transaction/stock_card_txn/g_stockcard', 'Returns the instance of the stockCardTxn''s stockCard');
INSERT INTO sub_method VALUES (66, 'mbr/batch_item_requirement/pst_new_batch_item_req', 'Save a new batch item requirement');
INSERT INTO sub_method VALUES (67, 'sqlsvr_copy/stock_card_c/g_available_stockcard', 'Get available stockcard by companyId, itemId');
INSERT INTO sub_method VALUES (68, 'mbr/batch_item_requirement/pst_update_batch_item_req_status', 'Update batch item requirement status');
INSERT INTO sub_method VALUES (69, 'sqlsvr_copy/stock_card_c/pst_update_stock_card_stock_status', 'Update stockcard stock status (AVAILABLE, DEPLETED)');


--
-- Name: submethod_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('submethod_id_seq', 68, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO "user" VALUES (2, 'mainevillarias@gmail.com', 'Marie Charmaine', 'Villarias', 'programmer', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    ');
INSERT INTO "user" VALUES (3, 'starlightlynx@gmail.com', 'HAWKE', 'HAWKE', 'CHAMPION', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    ');
INSERT INTO "user" VALUES (4, 'rnduser@gmail.com', 'Marie', 'Woodland', 'MANAGER', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    ');
INSERT INTO "user" VALUES (5, 'tester', 'charmaine', 'vill', 'programmer', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    ');
INSERT INTO "user" VALUES (6, 'tester2', 'na', 'na', 'na', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    ');


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO user_group VALUES (2, 2, 1);
INSERT INTO user_group VALUES (3, 3, 2);
INSERT INTO user_group VALUES (8, 4, 2);
INSERT INTO user_group VALUES (9, 5, 2);
INSERT INTO user_group VALUES (10, 6, 2);


--
-- Name: user_group_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('user_group_id_seq', 10, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 6, true);


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: security; Owner: postgres
--

INSERT INTO user_role VALUES (3, 3, 1);
INSERT INTO user_role VALUES (4, 4, 2);
INSERT INTO user_role VALUES (5, 5, 3);
INSERT INTO user_role VALUES (6, 6, 4);


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

INSERT INTO item VALUES (628, 'ALBENDAZOLE', 2, 1, NULL, '1');
INSERT INTO item VALUES (629, 'AMOXICILLIN TRIHYDRATE COMPACTED', 2, 1, NULL, '2');
INSERT INTO item VALUES (630, 'AMOXICILLIN TRIHYDRATE POWDER', 2, 1, NULL, '3');
INSERT INTO item VALUES (631, 'AMITRAZ', 2, 1, NULL, '4');
INSERT INTO item VALUES (632, 'ATTAPULGITE', 2, 1, NULL, '5');
INSERT INTO item VALUES (633, 'ALPHA-LIPOIC ACID', 2, 1, NULL, '6');
INSERT INTO item VALUES (634, 'ALOE VERA', 2, 1, NULL, '7');
INSERT INTO item VALUES (635, 'BENZOIC ACID', 2, 1, NULL, '8');
INSERT INTO item VALUES (636, 'BIOPERINE', 2, 1, NULL, '9');
INSERT INTO item VALUES (637, 'BLACK PEPPER', 2, 1, NULL, '10');
INSERT INTO item VALUES (638, 'BACON FLAVOR', 2, 1, NULL, '11');
INSERT INTO item VALUES (639, 'BEEF FLAVOR', 2, 1, NULL, '12');
INSERT INTO item VALUES (640, 'BENZALKONIUM CHLORIDE 80%', 2, 1, NULL, '13');
INSERT INTO item VALUES (641, 'BENZYL ALCOHOL', 2, 1, NULL, '14');
INSERT INTO item VALUES (642, 'BUTYLATED HYDROXYTOLUENE (BHT)', 2, 1, NULL, '15');
INSERT INTO item VALUES (643, 'BIOTIN 2%', 2, 1, NULL, '16');
INSERT INTO item VALUES (644, 'BROMHEXINE HCL', 2, 1, NULL, '17');
INSERT INTO item VALUES (645, 'BREWERS YEAST', 2, 1, NULL, '18');
INSERT INTO item VALUES (646, 'CANOLA OIL', 2, 1, NULL, '19');
INSERT INTO item VALUES (647, 'CALCIUM CARBONATE', 2, 1, NULL, '20');
INSERT INTO item VALUES (648, 'CABOSIL', 2, 1, NULL, '21');
INSERT INTO item VALUES (649, 'CALCIUM PANTHOTENATE', 2, 1, NULL, '22');
INSERT INTO item VALUES (650, 'CALCIUM PROPIONATE', 2, 1, NULL, '23');
INSERT INTO item VALUES (651, 'CARAMEL FLAVOR LIQUID', 2, 1, NULL, '24');
INSERT INTO item VALUES (652, 'CALCIUM CHLORIDE', 2, 1, NULL, '25');
INSERT INTO item VALUES (653, 'CALCIUM GLUCONATE', 2, 1, NULL, '26');
INSERT INTO item VALUES (654, 'CALCIUM GLUBIONATE', 2, 1, NULL, '27');
INSERT INTO item VALUES (655, 'CARAMEL COLOR POWDER', 2, 1, NULL, '28');
INSERT INTO item VALUES (656, 'COCONUT DIETHANOLAMIDE (CDEA)', 2, 1, NULL, '29');
INSERT INTO item VALUES (657, 'CRYSTALLINE FRUCTOSE', 2, 1, NULL, '30');
INSERT INTO item VALUES (658, 'CORN OIL', 2, 1, NULL, '31');
INSERT INTO item VALUES (659, 'CHERRY FLAVOR LIQUID', 2, 1, NULL, '32');
INSERT INTO item VALUES (660, 'CEFTIOFUR HCL', 2, 1, NULL, '33');
INSERT INTO item VALUES (661, 'CIPROFLOXACIN', 2, 1, NULL, '34');
INSERT INTO item VALUES (662, 'CITRIC ACID', 2, 1, NULL, '35');
INSERT INTO item VALUES (663, 'COATSHINE', 2, 1, NULL, '36');
INSERT INTO item VALUES (664, 'COLLAGEN', 2, 1, NULL, '37');
INSERT INTO item VALUES (665, 'CHOLINE CHLORIDE 50% CORN COB BASE', 2, 1, NULL, '38');
INSERT INTO item VALUES (666, 'CORNSTARCH', 2, 1, NULL, '39');
INSERT INTO item VALUES (667, 'COLISTIN SULFATE', 2, 1, NULL, '40');
INSERT INTO item VALUES (668, 'XANTHAN GUM', 2, 1, NULL, '41');
INSERT INTO item VALUES (669, 'CHROMIUM PICOLINATE', 2, 1, NULL, '42');
INSERT INTO item VALUES (670, 'COPPER SULFATE', 2, 1, NULL, '43');
INSERT INTO item VALUES (671, 'CROSS POVIDONE/ POLYPLASDONE XL', 2, 1, NULL, '44');
INSERT INTO item VALUES (672, 'DEXTROUS ANHYDROUS', 2, 1, NULL, '45');
INSERT INTO item VALUES (673, 'DEXTROSE MONOHYDRATE', 2, 1, NULL, '46');
INSERT INTO item VALUES (674, 'DI-TAB', 2, 1, NULL, '47');
INSERT INTO item VALUES (675, 'DICLAZURIL', 2, 1, NULL, '48');
INSERT INTO item VALUES (676, 'DL-METHIONINE', 2, 1, NULL, '49');
INSERT INTO item VALUES (677, 'DMDM HYDANTOIN', 2, 1, NULL, '50');
INSERT INTO item VALUES (678, 'D-PANTHENOL', 2, 1, NULL, '51');
INSERT INTO item VALUES (679, 'DOXYCYCLINE HCL', 2, 1, NULL, '52');
INSERT INTO item VALUES (680, 'DRIER DRIED PEPPER', 2, 1, NULL, '53');
INSERT INTO item VALUES (681, 'DIMETRIDAZOLE', 2, 1, NULL, '54');
INSERT INTO item VALUES (682, 'DEXAMETHASONE', 2, 1, NULL, '55');
INSERT INTO item VALUES (683, 'ENROFLOXACIN HCL', 2, 1, NULL, '56');
INSERT INTO item VALUES (684, 'ETHOXYLATED HYDROGENATED CASTOR OIL', 2, 1, NULL, '57');
INSERT INTO item VALUES (685, 'ETHYL ALCOHOL', 2, 1, NULL, '58');
INSERT INTO item VALUES (686, 'ETHYL CELLULOSE', 2, 1, NULL, '59');
INSERT INTO item VALUES (687, 'EUCALYPTOL OIL 99%', 2, 1, NULL, '60');
INSERT INTO item VALUES (688, 'EGC SIZE #3 GREEN (10-23-5)/ WHITE (20-1)', 2, 1, NULL, '61');
INSERT INTO item VALUES (689, 'EGC SIZE #1 NATURAL (1-0) NATURAL (1-0)', 2, 1, NULL, '62');
INSERT INTO item VALUES (690, 'EGC SIZE #0  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', 2, 1, NULL, '63');
INSERT INTO item VALUES (691, 'EGC SIZE #00  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE', 2, 1, NULL, '64');
INSERT INTO item VALUES (692, 'EGC SIZE #4 GREEN DULL (10-25-5)/ GRAY (9-75-5)', 2, 1, NULL, '65');
INSERT INTO item VALUES (693, 'EGC SIZE #2  NATURAL (1-0) NATURAL (1-0)', 2, 1, NULL, '66');
INSERT INTO item VALUES (694, 'EGC SIZE #00 NATURAL & GREEN (VEGGIE TYPE)', 2, 1, NULL, '67');
INSERT INTO item VALUES (695, 'EGC SIZE #0 NATURAL & GREEN (VEGGIE TYPE)', 2, 1, NULL, '68');
INSERT INTO item VALUES (696, 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/PINK)', 2, 1, NULL, '69');
INSERT INTO item VALUES (697, 'EGC SIZE #00 VEGGIE CAPSULE (WHITE/WHITE)', 2, 1, NULL, '70');
INSERT INTO item VALUES (698, 'EGC SIZE #0 BOVINE CAPS WHITE/WHITE', 2, 1, NULL, '71');
INSERT INTO item VALUES (699, 'EGC SIZE #00 BOVINE CAPS WHITE/WHITE', 2, 1, NULL, '72');
INSERT INTO item VALUES (700, 'FD & C RED # 40', 2, 1, NULL, '73');
INSERT INTO item VALUES (701, 'FERROUS SULFATE', 2, 1, NULL, '74');
INSERT INTO item VALUES (702, 'FD & C YELLOW #6', 2, 1, NULL, '75');
INSERT INTO item VALUES (703, 'FD & C YELLOW #5', 2, 1, NULL, '76');
INSERT INTO item VALUES (704, 'FD& C BLUE #1', 2, 1, NULL, '77');
INSERT INTO item VALUES (705, 'FOLIC ACID', 2, 1, NULL, '78');
INSERT INTO item VALUES (706, 'FOLIC ACID PHARMA GRADE ', 2, 1, NULL, '79');
INSERT INTO item VALUES (707, 'FORMULA ONE BASE MIX VENISON', 2, 1, NULL, '80');
INSERT INTO item VALUES (708, 'FORMULA ONE BASE MIX LIVER', 2, 1, NULL, '81');
INSERT INTO item VALUES (709, 'FORMULA ONE BASE MIX SALMON', 2, 1, NULL, '82');
INSERT INTO item VALUES (710, 'FLORFENICOL', 2, 1, NULL, '83');
INSERT INTO item VALUES (711, 'SODIUM CITRATE', 2, 1, NULL, '84');
INSERT INTO item VALUES (712, 'FRESH FRAGRANCE', 2, 1, NULL, '85');
INSERT INTO item VALUES (713, 'FRESH YACOON', 2, 1, NULL, '86');
INSERT INTO item VALUES (714, 'FRESH LUYA', 2, 1, NULL, '87');
INSERT INTO item VALUES (715, 'FRESH SAMPALOK', 2, 1, NULL, '88');
INSERT INTO item VALUES (716, 'FULL BREEZE #6', 2, 1, NULL, '89');
INSERT INTO item VALUES (717, 'FUJICALIN', 2, 1, NULL, '90');
INSERT INTO item VALUES (718, 'GENTAMYCIN SULFATE STERILE', 2, 1, NULL, '91');
INSERT INTO item VALUES (719, 'GLUTARALDEHYDE 50%', 2, 1, NULL, '92');
INSERT INTO item VALUES (720, 'GLYCERINE PURE', 2, 1, NULL, '93');
INSERT INTO item VALUES (721, 'GLYCINE NF WSP', 2, 1, NULL, '94');
INSERT INTO item VALUES (722, 'GREEN STEVIA/30G', 2, 1, NULL, '95');
INSERT INTO item VALUES (723, 'GUT AIDE P4', 2, 1, NULL, '96');
INSERT INTO item VALUES (724, 'GUT AIDE S6', 2, 1, NULL, '97');
INSERT INTO item VALUES (725, 'GUYABANO FRUIT', 2, 1, NULL, '98');
INSERT INTO item VALUES (726, 'GUYABANO LEAVES POWDER', 2, 1, NULL, '99');
INSERT INTO item VALUES (727, 'GUAVA LEAVES EXTRACT', 2, 1, NULL, '100');
INSERT INTO item VALUES (728, 'GUYABANO PULP', 2, 1, NULL, '101');
INSERT INTO item VALUES (729, 'GLUTATHIONE POWDER', 2, 1, NULL, '102');
INSERT INTO item VALUES (730, 'HIGH FRUCTOSE CORN SYRUP (HFCS)', 2, 1, NULL, '103');
INSERT INTO item VALUES (731, 'HONEY', 2, 1, NULL, '104');
INSERT INTO item VALUES (732, 'HYDROXOCOBALAMINE ACETATE', 2, 1, NULL, '105');
INSERT INTO item VALUES (733, 'IRON DEXTRAN 20%', 2, 1, NULL, '106');
INSERT INTO item VALUES (734, 'KOLLICOAT IR SUNSET YELLOW', 2, 1, NULL, '107');
INSERT INTO item VALUES (735, 'KOLLICOAT IR  YELLOW', 2, 1, NULL, '108');
INSERT INTO item VALUES (736, 'KOLLICOAT SMARTSEAL 30D', 2, 1, NULL, '109');
INSERT INTO item VALUES (737, 'KOLLICOAT IR BLACK', 2, 1, NULL, '110');
INSERT INTO item VALUES (738, 'KOLLICOAT IR BRILLIANT BLUE ', 2, 1, NULL, '111');
INSERT INTO item VALUES (739, 'KOLLICOAT IR CARMINE', 2, 1, NULL, '112');
INSERT INTO item VALUES (740, 'KOLLICOAT IR WHITE II', 2, 1, NULL, '113');
INSERT INTO item VALUES (741, 'KOLLIDON VA64 FINE', 2, 1, NULL, '114');
INSERT INTO item VALUES (742, 'KAOLIN LIGHT', 2, 1, NULL, '115');
INSERT INTO item VALUES (743, 'LACTOSE MONOHYDRATE', 2, 1, NULL, '116');
INSERT INTO item VALUES (744, 'LAVENDER SCENT', 2, 1, NULL, '117');
INSERT INTO item VALUES (745, 'LAVENDER ESSENTIAL OIL', 2, 1, NULL, '118');
INSERT INTO item VALUES (746, 'LYCHEE FLAVOR', 2, 1, NULL, '119');
INSERT INTO item VALUES (747, 'L-GLUTATHIONE REDUCED', 2, 1, NULL, '120');
INSERT INTO item VALUES (748, 'L-CITRULLINE', 2, 1, NULL, '121');
INSERT INTO item VALUES (749, 'LEVAMISOLE HCL', 2, 1, NULL, '122');
INSERT INTO item VALUES (750, 'LIDOCAINE HCL ', 2, 1, NULL, '123');
INSERT INTO item VALUES (751, 'LINCOMYCIN HCL', 2, 1, NULL, '124');
INSERT INTO item VALUES (752, 'LABSA (LINEAR ALKYL BENZYL SULPHONIC ACID)', 2, 1, NULL, '125');
INSERT INTO item VALUES (753, 'LACTOSE DMV ', 2, 1, NULL, '126');
INSERT INTO item VALUES (754, 'LIQUID GLUCOSE 43 BE', 2, 1, NULL, '127');
INSERT INTO item VALUES (755, 'L-LYSINE HCL USP GRADE', 2, 1, NULL, '128');
INSERT INTO item VALUES (756, 'L-LYSINE HCL', 2, 1, NULL, '129');
INSERT INTO item VALUES (757, 'LUDIPRESS', 2, 1, NULL, '130');
INSERT INTO item VALUES (758, 'LUYANG DILAW (ORGANIC TURMERIC)', 2, 1, NULL, '131');
INSERT INTO item VALUES (759, 'LITTLE MATE BASE MIX LAMB', 2, 1, NULL, '132');
INSERT INTO item VALUES (760, 'LITTLE MATE BASE MIX BEEF', 2, 1, NULL, '133');
INSERT INTO item VALUES (761, 'MAGNESIUM STEARATE', 2, 1, NULL, '134');
INSERT INTO item VALUES (762, 'MANDURAMYCIN AMMONIUM', 2, 1, NULL, '135');
INSERT INTO item VALUES (763, 'MAGNESIUM SULFATE HEPTAHYDRATE', 2, 1, NULL, '136');
INSERT INTO item VALUES (764, 'MALTODEXTRIN', 2, 1, NULL, '137');
INSERT INTO item VALUES (765, 'MANGOSTEEN FRUIT', 2, 1, NULL, '138');
INSERT INTO item VALUES (766, 'MANGANESE SULFATE', 2, 1, NULL, '139');
INSERT INTO item VALUES (767, 'METHYL PARABEN', 2, 1, NULL, '140');
INSERT INTO item VALUES (768, 'METHANDRIOL DIPROPIONATE', 2, 1, NULL, '141');
INSERT INTO item VALUES (769, 'METHANDRIOL PROPIONATE', 2, 1, NULL, '142');
INSERT INTO item VALUES (770, 'MADURAMYCIN', 2, 1, NULL, '143');
INSERT INTO item VALUES (771, 'MICROCRYSTALLINE CELLULOSE PH-101', 2, 1, NULL, '144');
INSERT INTO item VALUES (772, 'MILK FLAVOR POWDER', 2, 1, NULL, '145');
INSERT INTO item VALUES (773, 'MINERAL OIL', 2, 1, NULL, '146');
INSERT INTO item VALUES (774, 'MONOSODIUM PHOSPHATE ANHYDROUS', 2, 1, NULL, '147');
INSERT INTO item VALUES (775, 'MORINGGA LEAVES POWDER', 2, 1, NULL, '148');
INSERT INTO item VALUES (776, 'MUSCOVADO SUGAR', 2, 1, NULL, '149');
INSERT INTO item VALUES (777, 'MYGLYOL 840', 2, 1, NULL, '150');
INSERT INTO item VALUES (778, 'MALATHION/MADISON', 2, 1, NULL, '151');
INSERT INTO item VALUES (779, 'MALIC ACID', 2, 1, NULL, '152');
INSERT INTO item VALUES (780, 'MENTHOL CRYSTAL', 2, 1, NULL, '153');
INSERT INTO item VALUES (781, 'MEATMEAL', 2, 1, NULL, '154');
INSERT INTO item VALUES (782, 'NANDROLONE PHENYLPROPIONATE', 2, 1, NULL, '155');
INSERT INTO item VALUES (783, 'NATURAL DALANDAN FLAVOR POWDER', 2, 1, NULL, '156');
INSERT INTO item VALUES (784, 'NEOMYCIN SULFATE', 2, 1, NULL, '157');
INSERT INTO item VALUES (785, 'NIACINAMIDE  / NICOTINAMIDE', 2, 1, NULL, '158');
INSERT INTO item VALUES (786, 'N-METHYL-PYRROLIDONE (NMP)', 2, 1, NULL, '159');
INSERT INTO item VALUES (787, 'NICLOSAMIDE', 2, 1, NULL, '160');
INSERT INTO item VALUES (788, 'NICOTINI ACID/ NIACIN (MICROVIT B3 PROMIX)', 2, 1, NULL, '161');
INSERT INTO item VALUES (789, 'NORFLOXACIN HCL', 2, 1, NULL, '162');
INSERT INTO item VALUES (790, 'NORFLOXACIN NICOTINATE', 2, 1, NULL, '163');
INSERT INTO item VALUES (791, 'OTI DERM', 2, 1, NULL, '164');
INSERT INTO item VALUES (792, 'ORANGE FLAVOR', 2, 1, NULL, '165');
INSERT INTO item VALUES (793, 'OXYTETRACYCLINE HCL', 2, 1, NULL, '166');
INSERT INTO item VALUES (794, 'OPAORY PINK', 2, 1, NULL, '167');
INSERT INTO item VALUES (795, 'OPAORY GREEN', 2, 1, NULL, '168');
INSERT INTO item VALUES (796, 'OPAORY BLACK', 2, 1, NULL, '169');
INSERT INTO item VALUES (797, 'OPAORY BROWN', 2, 1, NULL, '170');
INSERT INTO item VALUES (798, 'OPAORY LIGHT GREEN', 2, 1, NULL, '171');
INSERT INTO item VALUES (799, 'OPAORY WHITE', 2, 1, NULL, '172');
INSERT INTO item VALUES (800, 'OREGANO POWDER', 2, 1, NULL, '173');
INSERT INTO item VALUES (801, 'OPTICAL BRIGHTENER (STILIBENE, 3,5-DIHYDROXY)', 2, 1, NULL, '174');
INSERT INTO item VALUES (802, 'PALM OIL', 2, 1, NULL, '175');
INSERT INTO item VALUES (803, 'PVP K 30', 2, 1, NULL, '176');
INSERT INTO item VALUES (804, 'PERMETHRIN', 2, 1, NULL, '177');
INSERT INTO item VALUES (805, 'PECTIN CITRUS', 2, 1, NULL, '178');
INSERT INTO item VALUES (806, 'PET COLOGNE', 2, 1, NULL, '179');
INSERT INTO item VALUES (807, 'PEPPERMINT OIL', 2, 1, NULL, '180');
INSERT INTO item VALUES (808, 'PEPPERMINT ESSENTIAL OIL', 2, 1, NULL, '181');
INSERT INTO item VALUES (809, 'PIPERAZINE CITRATE', 2, 1, NULL, '182');
INSERT INTO item VALUES (810, 'POTASSIUM CHLORIDE', 2, 1, NULL, '183');
INSERT INTO item VALUES (811, 'POTASSIUM IODIDE', 2, 1, NULL, '184');
INSERT INTO item VALUES (812, 'POTASSIUM HYDROXIDE PELLETS (TG)', 2, 1, NULL, '185');
INSERT INTO item VALUES (813, 'PRAZIQUANTEL', 2, 1, NULL, '186');
INSERT INTO item VALUES (814, 'PREDNISOLONE ACETATE', 2, 1, NULL, '187');
INSERT INTO item VALUES (815, 'PROPYL PARABEN', 2, 1, NULL, '188');
INSERT INTO item VALUES (816, 'PROPYLENE GLYCOL', 2, 1, NULL, '189');
INSERT INTO item VALUES (817, 'POWDER SCENT', 2, 1, NULL, '190');
INSERT INTO item VALUES (818, 'PYRIMETHAMINE HCL', 2, 1, NULL, '191');
INSERT INTO item VALUES (819, 'RACTOPAMINE HCL', 2, 1, NULL, '192');
INSERT INTO item VALUES (820, 'REFINED SUGAR', 2, 1, NULL, '193');
INSERT INTO item VALUES (821, 'REFAMOL CAPSULE', 2, 1, NULL, '194');
INSERT INTO item VALUES (822, 'STEVIA REBAUDIANA', 2, 1, NULL, '195');
INSERT INTO item VALUES (823, 'SAMBONG LEAVES POWDER', 2, 1, NULL, '196');
INSERT INTO item VALUES (824, 'SANDALWOOD ESSENTIAL OIL', 2, 1, NULL, '197');
INSERT INTO item VALUES (825, 'SANDALWOOD SCENT', 2, 1, NULL, '198');
INSERT INTO item VALUES (826, 'SHAMPOO PEARLIZER', 2, 1, NULL, '199');
INSERT INTO item VALUES (827, 'SODIUM LAURYL ETHYL SULFATE (SLES)', 2, 1, NULL, '200');
INSERT INTO item VALUES (828, 'SD PORK LIVER POWDER', 2, 1, NULL, '201');
INSERT INTO item VALUES (829, 'SODIUM ASCORBATE', 2, 1, NULL, '202');
INSERT INTO item VALUES (830, 'SODA ASH LIGHT', 2, 1, NULL, '203');
INSERT INTO item VALUES (831, 'SODIUM BENZOATE', 2, 1, NULL, '204');
INSERT INTO item VALUES (832, 'SODIUM BICARBONATE', 2, 1, NULL, '205');
INSERT INTO item VALUES (833, 'SODIUM CHLORIDE', 2, 1, NULL, '206');
INSERT INTO item VALUES (834, 'SODIUM SELENITE', 2, 1, NULL, '207');
INSERT INTO item VALUES (835, 'SODIUM SULFADIAZINE', 2, 1, NULL, '208');
INSERT INTO item VALUES (836, 'SODIUM SULFADIMIDINE', 2, 1, NULL, '209');
INSERT INTO item VALUES (837, 'SODIUM SULFAQUINOXALINE', 2, 1, NULL, '210');
INSERT INTO item VALUES (838, 'SOLUBLE PORK LIVER POWDER', 2, 1, NULL, '211');
INSERT INTO item VALUES (839, 'SODIUM SULFAGUANIDINE', 2, 1, NULL, '212');
INSERT INTO item VALUES (840, 'SORBITOL SOLUTION', 2, 1, NULL, '213');
INSERT INTO item VALUES (841, 'STRAWBERRY FLAVOR  POWDER', 2, 1, NULL, '214');
INSERT INTO item VALUES (842, 'SODIUM CARBOXYMETHYLCISTEINE (SODIUM CMC)', 2, 1, NULL, '215');
INSERT INTO item VALUES (843, 'SODIUM SACCHARIN', 2, 1, NULL, '216');
INSERT INTO item VALUES (844, 'SODIUM METABISULFITE', 2, 1, NULL, '217');
INSERT INTO item VALUES (845, 'SODIUM STARCH GLYCOLATE', 2, 1, NULL, '218');
INSERT INTO item VALUES (846, 'STREPTOMYCIN SULFATE', 2, 1, NULL, '219');
INSERT INTO item VALUES (847, 'STEARIC ACID', 2, 1, NULL, '220');
INSERT INTO item VALUES (848, 'SULFAMETHAZINE (SULFADIMIDINE)', 2, 1, NULL, '221');
INSERT INTO item VALUES (849, 'SUN DRIED PEPPER', 2, 1, NULL, '222');
INSERT INTO item VALUES (850, 'SWEET DESIRE', 2, 1, NULL, '223');
INSERT INTO item VALUES (851, 'SWEET IRON', 2, 1, NULL, '224');
INSERT INTO item VALUES (852, 'TALC POWDER STERILIZED', 2, 1, NULL, '225');
INSERT INTO item VALUES (853, 'TAB - BASE', 2, 1, NULL, '226');
INSERT INTO item VALUES (854, 'TAURINE', 2, 1, NULL, '227');
INSERT INTO item VALUES (855, 'TEGO BETAIN L7', 2, 1, NULL, '228');
INSERT INTO item VALUES (856, 'TN500 ', 2, 1, NULL, '229');
INSERT INTO item VALUES (857, 'TETRASODIUM EDTA', 2, 1, NULL, '230');
INSERT INTO item VALUES (858, 'THYROPROTEIN', 2, 1, NULL, '231');
INSERT INTO item VALUES (859, 'TIAMULIN HYDROGEN FUMERATE', 2, 1, NULL, '232');
INSERT INTO item VALUES (860, 'THIAMPHENICOL', 2, 1, NULL, '233');
INSERT INTO item VALUES (861, 'TRIETHYL CITRATE', 2, 1, NULL, '234');
INSERT INTO item VALUES (862, 'TRISODIUM PHOSPHATE', 2, 1, NULL, '235');
INSERT INTO item VALUES (863, 'TRIMETHOPRIM', 2, 1, NULL, '236');
INSERT INTO item VALUES (864, 'TYLOSIN TARTRATE', 2, 1, NULL, '237');
INSERT INTO item VALUES (865, 'TWEEN 80 / POLYSORBATE 80', 2, 1, NULL, '238');
INSERT INTO item VALUES (866, 'TWEEN 20', 2, 1, NULL, '239');
INSERT INTO item VALUES (867, 'UNFLAVORED JELLY POWDER', 2, 1, NULL, '240');
INSERT INTO item VALUES (868, 'VERNEL SCENT', 2, 1, NULL, '241');
INSERT INTO item VALUES (869, 'VIRGIN COCONUT OIL', 2, 1, NULL, '242');
INSERT INTO item VALUES (870, 'VEEGUM (MAGNESIUM ALUMINUM SILICATE)', 2, 1, NULL, '243');
INSERT INTO item VALUES (871, 'VANILLA FLAVOR POWDER', 2, 1, NULL, '244');
INSERT INTO item VALUES (872, 'VITAMIN A PROPIONATE OIL', 2, 1, NULL, '245');
INSERT INTO item VALUES (873, 'VITAMIN A PALMITATE 1.7MIU/G OIL', 2, 1, NULL, '246');
INSERT INTO item VALUES (874, 'VITAMIN A PALMITATE POWDER', 2, 1, NULL, '247');
INSERT INTO item VALUES (875, 'VITAMIN A ACETATE  (PROSOL)', 2, 1, NULL, '248');
INSERT INTO item VALUES (876, ' VITAMIN A DRY 1 MIU (SUPRA)', 2, 1, NULL, '249');
INSERT INTO item VALUES (877, 'VITAMIN B1 HCL', 2, 1, NULL, '250');
INSERT INTO item VALUES (878, 'VITAMIN B6 HCL (MICROVIT B6 PROMIX)', 2, 1, NULL, '251');
INSERT INTO item VALUES (879, 'VITAMIN B2 PHOSPHATE / RIBOFLAVIN 5', 2, 1, NULL, '252');
INSERT INTO item VALUES (880, 'VITAMIN B2 80%', 2, 1, NULL, '253');
INSERT INTO item VALUES (881, 'VITAMIN B12 1%  FG', 2, 1, NULL, '254');
INSERT INTO item VALUES (882, 'VITAMIN B12 PURE', 2, 1, NULL, '255');
INSERT INTO item VALUES (883, 'VITAMIN C - COATED ( ASCORBIC ACID COATED)', 2, 1, NULL, '256');
INSERT INTO item VALUES (884, 'VITAMIN D3 OIL 4 MIU', 2, 1, NULL, '257');
INSERT INTO item VALUES (885, 'VITAMIN D3 500,000 IU/G FEEDGRADE', 2, 1, NULL, '258');
INSERT INTO item VALUES (886, 'VITAMIN - E ACETATE OIL', 2, 1, NULL, '259');
INSERT INTO item VALUES (887, 'VITAMIN E POWDER', 2, 1, NULL, '260');
INSERT INTO item VALUES (888, 'VITAMIN K3', 2, 1, NULL, '261');
INSERT INTO item VALUES (889, 'WHEAT GERM OIL', 2, 1, NULL, '262');
INSERT INTO item VALUES (890, 'WOODRUFF GREEN', 2, 1, NULL, '263');
INSERT INTO item VALUES (891, 'XYLENE', 2, 1, NULL, '264');
INSERT INTO item VALUES (892, 'YELLOW LAKE # 5', 2, 1, NULL, '265');
INSERT INTO item VALUES (893, 'YACON SEEDLING', 2, 1, NULL, '266');
INSERT INTO item VALUES (894, 'ZINC  OXIDE ', 2, 1, NULL, '267');
INSERT INTO item VALUES (895, 'ZINC SULPHATE HEPTAHYDRATE', 2, 1, NULL, '268');
INSERT INTO item VALUES (896, 'ZINC SULPHATE MONOHYDRATE', 2, 1, NULL, '269');
INSERT INTO item VALUES (897, 'TRYPTIC SOY BROTH HI-MEDIA', 2, 1, NULL, '270');
INSERT INTO item VALUES (898, 'DIFFERENTIAL CLOSFRIDIUM MEDIUM 500g', 2, 1, NULL, '271');
INSERT INTO item VALUES (899, 'MYP AGAR', 2, 1, NULL, '272');
INSERT INTO item VALUES (900, 'STERILE  WATER', 2, 1, NULL, '273');
INSERT INTO item VALUES (901, 'TRYPTIC SOY AGAR', 2, 1, NULL, '274');
INSERT INTO item VALUES (902, 'DIALYZING MEMBRANE', 2, 1, NULL, '275');
INSERT INTO item VALUES (903, 'FORMALDEHYDE', 2, 1, NULL, '276');
INSERT INTO item VALUES (904, 'MCCONKEY AGAR', 2, 1, NULL, '277');
INSERT INTO item VALUES (905, 'BRAIN HEART INFUSION BROTH', 2, 1, NULL, '278');
INSERT INTO item VALUES (906, 'MUELLER HINTON AGAR', 2, 1, NULL, '279');
INSERT INTO item VALUES (907, 'ALUMINUM HYDROXIDE REHYDRAGEL', 2, 1, NULL, '280');
INSERT INTO item VALUES (908, 'SABORAUD DEXTROSE AGAR', 2, 1, NULL, '281');
INSERT INTO item VALUES (909, 'SODIUM CHLORIDE 0.9% NSS 1000ML', 2, 1, NULL, '282');
INSERT INTO item VALUES (910, 'TRIPPLE DISTILLED WATER', 2, 1, NULL, '283');
INSERT INTO item VALUES (911, 'TRIPLE SUGAR IRON', 2, 1, NULL, '284');
INSERT INTO item VALUES (912, 'SILKWEED ', 2, 1, NULL, '285');
INSERT INTO item VALUES (913, 'TRYPTIC SOY BROTH', 2, 1, NULL, '286');
INSERT INTO item VALUES (914, 'YEAST EXTRACT', 2, 1, NULL, '287');
INSERT INTO item VALUES (915, 'MRS AGAR', 2, 1, NULL, '288');
INSERT INTO item VALUES (916, 'MRS BROTH', 2, 1, NULL, '289');
INSERT INTO item VALUES (917, 'EMB AGAR (EOSIN METHYLENE BLUE, BBL)', 2, 1, NULL, '290');
INSERT INTO item VALUES (918, 'BABY DIAPER (MEDIUM)', 2, 1, NULL, '291');
INSERT INTO item VALUES (919, 'TODD-HEWITT BROTH', 2, 1, NULL, '292');
INSERT INTO item VALUES (920, 'NALIDIXIC ACID (5G/BOTTLE)', 2, 1, NULL, '293');
INSERT INTO item VALUES (921, 'STUART TRANSPORT MEDIUM', 2, 1, NULL, '294');
INSERT INTO item VALUES (922, 'RAPPAPORT VASSILIADIS SALMONELLA ENRICHMENT BROTH', 2, 1, NULL, '295');
INSERT INTO item VALUES (923, 'RASPBERRY FLAVOR', 2, 1, NULL, '296');
INSERT INTO item VALUES (924, 'FEBANTEL', 2, 1, NULL, '297');
INSERT INTO item VALUES (925, 'VITAMIN D3 POWDER', 2, 1, NULL, '298');
INSERT INTO item VALUES (926, 'BEEF SPRAVY', 2, 1, NULL, '1001');
INSERT INTO item VALUES (927, 'SODIUM CITRATE ANHYDROUS', 2, 1, NULL, '1002');
INSERT INTO item VALUES (928, 'FD & C RED # 3', 2, 1, NULL, '1003');
INSERT INTO item VALUES (929, 'HYOSCINE HBR', 2, 1, NULL, '1004');
INSERT INTO item VALUES (930, 'LAMB SPRAVY', 2, 1, NULL, '1005');
INSERT INTO item VALUES (931, 'LANETTO', 2, 1, NULL, '1006');
INSERT INTO item VALUES (932, 'MELON SWEET', 2, 1, NULL, '1007');
INSERT INTO item VALUES (933, 'MY STRAWBERRY SCENT', 2, 1, NULL, '1008');
INSERT INTO item VALUES (934, 'PYRANTEL PAMOATE', 2, 1, NULL, '1009');
INSERT INTO item VALUES (935, 'BETAINE HCL', 2, 1, NULL, '1010');
INSERT INTO item VALUES (936, 'MR. VP, BBL BROTH', 2, 1, NULL, '1011');
INSERT INTO item VALUES (937, 'XYLOSE LYSINE DEOXYCHOLATE', 2, 1, NULL, '1012');
INSERT INTO item VALUES (938, 'SELENIUM SULFIDE', 2, 1, NULL, '1013');
INSERT INTO item VALUES (939, 'SILVER DIOXIDE', 2, 1, NULL, '1014');
INSERT INTO item VALUES (940, 'CHOLINE CHLORIDE 70% LIQUID', 2, 1, NULL, '1015');
INSERT INTO item VALUES (941, 'MONOPOTASSIUM PHOSPHATE', 2, 1, NULL, '1016');
INSERT INTO item VALUES (942, 'MY STRAWBERRY LIQUID', 2, 1, NULL, '1017');
INSERT INTO item VALUES (943, 'VIBRA RED', 2, 1, NULL, '1018');
INSERT INTO item VALUES (944, 'MONTANIDE IMS 1313 MINERAL OIL', 2, 1, NULL, '1019');
INSERT INTO item VALUES (945, 'IVERMECTIN', 2, 1, NULL, '1020');
INSERT INTO item VALUES (946, 'EUCALYPTUS OIL EXTRA', 2, 1, NULL, '1021');
INSERT INTO item VALUES (947, 'KAOLIN LIGHT', 2, 1, NULL, '1022');
INSERT INTO item VALUES (948, 'POWERED LAGUNDI LEAVES', 2, 1, NULL, '1023');
INSERT INTO item VALUES (949, 'DRIED LAGUNDI LEAVES', 2, 1, NULL, '1024');
INSERT INTO item VALUES (950, 'KOLLIDON CL', 2, 1, NULL, '1025');
INSERT INTO item VALUES (951, 'SPECTROMYCIN HCL', 2, 1, NULL, '1026');
INSERT INTO item VALUES (952, 'CHROMIUM TRIPICOLINATE', 2, 1, NULL, '1027');
INSERT INTO item VALUES (953, 'CYANOCOBALAMIN FG', 2, 1, NULL, '1028');
INSERT INTO item VALUES (954, 'SODIUM FORMALDEHYDE', 2, 1, NULL, '1029');
INSERT INTO item VALUES (955, 'SULPHOXYLATE', 2, 1, NULL, '1030');
INSERT INTO item VALUES (956, 'GLYCEROL FORMAL', 2, 1, NULL, '1031');
INSERT INTO item VALUES (957, 'POLYETHYLENE GLYCOL', 2, 1, NULL, '1032');
INSERT INTO item VALUES (958, 'BETA CAROTENE', 2, 1, NULL, '1033');
INSERT INTO item VALUES (959, 'POLYVINYL PYRROLIDONE K12', 2, 1, NULL, '1034');
INSERT INTO item VALUES (960, 'MAGNESIUM OXIDE', 2, 1, NULL, '1035');
INSERT INTO item VALUES (961, 'PROCAINE PENICILLIN', 2, 1, NULL, '1036');
INSERT INTO item VALUES (962, 'CHLORTETRACYCLINE HCL', 2, 1, NULL, '1037');
INSERT INTO item VALUES (963, 'L-TRYTOPHAN (POWDER)', 2, 1, NULL, '1038');
INSERT INTO item VALUES (964, 'BETAINE (POWDER)', 2, 1, NULL, '1039');
INSERT INTO item VALUES (965, 'NENSILIN', 2, 1, NULL, '1040');
INSERT INTO item VALUES (966, 'F-MELT ODT (CONNELL)', 2, 1, NULL, '1041');
INSERT INTO item VALUES (967, 'ZINC BACITRACIN', 2, 1, NULL, '1042');
INSERT INTO item VALUES (968, 'CARBARYL', 2, 1, NULL, '1043');
INSERT INTO item VALUES (969, 'PEG 400', 2, 1, NULL, '1044');
INSERT INTO item VALUES (970, 'CHREMOPHOR RH 40', 2, 1, NULL, '1045');
INSERT INTO item VALUES (971, 'ALUMINUM CAP FOR VIALS 10/100ML PLAIN', 3, 1, NULL, '1');
INSERT INTO item VALUES (972, 'ALUM. CAP FOR VIALS 10/100ML WITH APT LOGO', 3, 1, NULL, '2');
INSERT INTO item VALUES (973, 'AMBER VIALS 100ML', 3, 1, NULL, '3');
INSERT INTO item VALUES (974, 'AMBER VIALS 25ML', 3, 1, NULL, '4');
INSERT INTO item VALUES (975, 'AMBER BOTTLE BOSTON ROUND 500ML', 3, 1, NULL, '5');
INSERT INTO item VALUES (976, 'AMBER BOTTLE BOSTON ROUND 75ML', 3, 1, NULL, '6');
INSERT INTO item VALUES (977, 'BLACK PLASTIC 1 KG', 3, 1, NULL, '7');
INSERT INTO item VALUES (978, 'BLACK PLASTIC 500GMS.', 3, 1, NULL, '8');
INSERT INTO item VALUES (979, 'BLACK PLASTIC TROVITE 1KG with PRINT', 3, 1, NULL, '9');
INSERT INTO item VALUES (980, 'CAP GLUTASEP', 3, 1, NULL, '10');
INSERT INTO item VALUES (981, 'CAP KABEZEN', 3, 1, NULL, '11');
INSERT INTO item VALUES (982, 'CAP LAKTAMINO/LAKSOVIT 1LITER', 3, 1, NULL, '12');
INSERT INTO item VALUES (983, 'CAP SCOURBUST/COLIMOXYN  150 ML', 3, 1, NULL, '13');
INSERT INTO item VALUES (984, 'ALUM. CAP TRISULLAK 400ML', 3, 1, NULL, '14');
INSERT INTO item VALUES (985, 'CAPSEAL 1 L', 3, 1, NULL, '15');
INSERT INTO item VALUES (986, 'CAPLINER 1 L', 3, 1, NULL, '16');
INSERT INTO item VALUES (987, 'C.BOX 12 X 150 ML', 3, 1, NULL, '17');
INSERT INTO item VALUES (988, 'C.BOX 12 X 100 ML VIALS', 3, 1, NULL, '18');
INSERT INTO item VALUES (989, 'C.BOX 12 x 4 L, GLUTASEP', 3, 1, NULL, '19');
INSERT INTO item VALUES (990, 'C.BOX 12 x 1 L, KABEZEN', 3, 1, NULL, '20');
INSERT INTO item VALUES (991, 'C.BOX LAKTAMINO/LAKSOVIT 1 LITER', 3, 1, NULL, '21');
INSERT INTO item VALUES (992, 'C.BOX TRISULLAK/TRISULDINE 400ML', 3, 1, NULL, '22');
INSERT INTO item VALUES (993, 'C.BOX TROVITE 3KG', 3, 1, NULL, '23');
INSERT INTO item VALUES (994, 'CLEAR VIALS 100ML', 3, 1, NULL, '24');
INSERT INTO item VALUES (995, 'COVER, PLASTIC 10KG', 3, 1, NULL, '25');
INSERT INTO item VALUES (996, 'SILVER FOIL SACHETLAKRODOX 100G', 3, 1, NULL, '26');
INSERT INTO item VALUES (997, 'GAMMA CAP WHITE  28 X 400', 3, 1, NULL, '27');
INSERT INTO item VALUES (998, 'GLUE (gallon)', 3, 1, NULL, '28');
INSERT INTO item VALUES (999, 'INDIVIDUAL BOX TRISULDINE-S 400ML ', 3, 1, NULL, '29');
INSERT INTO item VALUES (1000, 'INDIVIDUAL BOX VIBEFLEX 10ML', 3, 1, NULL, '30');
INSERT INTO item VALUES (1001, 'JAR VERMGUARD 2 KG with COVER', 3, 1, NULL, '31');
INSERT INTO item VALUES (1002, 'KRAFT BAG BROWN 20KG', 3, 1, NULL, '32');
INSERT INTO item VALUES (1003, 'KRAFT BAG WHITE 20KG', 3, 1, NULL, '33');
INSERT INTO item VALUES (1004, 'LABEL ALBENDAZOLE 5%', 3, 1, NULL, '34');
INSERT INTO item VALUES (1005, 'LABEL AMOXYLAK 20% WSP', 3, 1, NULL, '35');
INSERT INTO item VALUES (1006, 'LABEL AMOXYLAK 50% FG 1 KG', 3, 1, NULL, '36');
INSERT INTO item VALUES (1007, 'LABEL AMOXYLAK 10KG', 3, 1, NULL, '37');
INSERT INTO item VALUES (1008, 'LABEL ACI-DEHYDE 4L', 3, 1, NULL, '38');
INSERT INTO item VALUES (1009, 'LABEL ACI-VIT 1KG', 3, 1, NULL, '39');
INSERT INTO item VALUES (1010, 'LABEL ACI-VITAMINO 1L', 3, 1, NULL, '40');
INSERT INTO item VALUES (1011, 'LABEL BETALIN 100ML', 3, 1, NULL, '41');
INSERT INTO item VALUES (1012, 'LABEL COLIMIX WSP 1KG', 3, 1, NULL, '42');
INSERT INTO item VALUES (1013, 'LABEL COLIMOXYN 150ML', 3, 1, NULL, '43');
INSERT INTO item VALUES (1014, 'LABEL COLISTILAK 10% 10KG', 3, 1, NULL, '44');
INSERT INTO item VALUES (1015, 'LABEL DEXTRAVIT 100ML', 3, 1, NULL, '45');
INSERT INTO item VALUES (1016, 'LABEL FLOXANE 100ML', 3, 1, NULL, '46');
INSERT INTO item VALUES (1017, 'LABEL FLOXANE 10% SOL''N 1L', 3, 1, NULL, '47');
INSERT INTO item VALUES (1018, 'LABEL GENCOTIL 5% 100ML', 3, 1, NULL, '48');
INSERT INTO item VALUES (1019, 'LABEL GENMOX 100ML  ', 3, 1, NULL, '49');
INSERT INTO item VALUES (1020, 'LABEL GLUTASEP 1L', 3, 1, NULL, '50');
INSERT INTO item VALUES (1021, 'LABEL GLUTASEP 4 LITERS ', 3, 1, NULL, '51');
INSERT INTO item VALUES (1022, 'LABEL GUT-AIDE P4 PREMIX 1KG', 3, 1, NULL, '52');
INSERT INTO item VALUES (1023, 'LABEL GUT-AID P4 WSP 1 KG', 3, 1, NULL, '53');
INSERT INTO item VALUES (1024, 'LABEL GUT-AIDE S6 PREMIX 1KG ', 3, 1, NULL, '54');
INSERT INTO item VALUES (1025, 'LABEL GUT-AIDE S6 WSP 1KG', 3, 1, NULL, '55');
INSERT INTO item VALUES (1026, 'LABEL LAKSADE 100ML ', 3, 1, NULL, '56');
INSERT INTO item VALUES (1027, 'LABEL LAKSOVIT 1LITER (FRONT)', 3, 1, NULL, '57');
INSERT INTO item VALUES (1028, 'LABEL LAKSOVIT 1 LITER (BACK)', 3, 1, NULL, '58');
INSERT INTO item VALUES (1029, 'LABEL LAKTAMINO 1 LITER(FRONT)', 3, 1, NULL, '59');
INSERT INTO item VALUES (1030, 'LABEL LAKTAMINO 1 LITER (BACK)', 3, 1, NULL, '60');
INSERT INTO item VALUES (1031, 'LABEL LAKTOCIDE 4 LITERS FRONT & BACK', 3, 1, NULL, '61');
INSERT INTO item VALUES (1032, 'LABEL LAKXINOR 1KG', 3, 1, NULL, '62');
INSERT INTO item VALUES (1033, 'LABEL LEVAXANTEL 1LITER', 3, 1, NULL, '63');
INSERT INTO item VALUES (1034, 'LABEL LC-VITAMIN PREMIX 20KG', 3, 1, NULL, '64');
INSERT INTO item VALUES (1035, 'LABEL MILKOVET 500 GMS.', 3, 1, NULL, '65');
INSERT INTO item VALUES (1036, 'LABEL MILKOVET 100 GMS.', 3, 1, NULL, '66');
INSERT INTO item VALUES (1037, 'LABEL MILKOVET 20 KL.', 3, 1, NULL, '67');
INSERT INTO item VALUES (1038, 'LABEL NORFLOXACIN WSP 1KG (LAKXINOR) ', 3, 1, NULL, '68');
INSERT INTO item VALUES (1039, 'LABEL SCOURBUST 150ML ', 3, 1, NULL, '69');
INSERT INTO item VALUES (1040, 'LABEL SYNTHAMIN 1L', 3, 1, NULL, '70');
INSERT INTO item VALUES (1041, 'LABEL THIATYLONE 100ML', 3, 1, NULL, '71');
INSERT INTO item VALUES (1042, 'LABEL TIAMULIN 500GMS.', 3, 1, NULL, '72');
INSERT INTO item VALUES (1043, 'LABEL TRISULDINE-S 400ML', 3, 1, NULL, '73');
INSERT INTO item VALUES (1044, 'LABEL TRIZINE PLUS 1KG', 3, 1, NULL, '74');
INSERT INTO item VALUES (1045, 'LABEL TROBAZEN (FRONT)', 3, 1, NULL, '75');
INSERT INTO item VALUES (1046, 'LABEL TROBAZEN (BACK)', 3, 1, NULL, '76');
INSERT INTO item VALUES (1047, 'LABEL TYLAK 100ML ', 3, 1, NULL, '77');
INSERT INTO item VALUES (1048, 'LABEL TYLAK 1 LITER ', 3, 1, NULL, '78');
INSERT INTO item VALUES (1049, 'LABEL VIROSEPT 5KG (FRONT)', 3, 1, NULL, '79');
INSERT INTO item VALUES (1050, 'LABEL VIROSEPT 5KG (BACK)', 3, 1, NULL, '80');
INSERT INTO item VALUES (1051, ' LABEL APTIOFUR 100ML', 3, 1, NULL, '81');
INSERT INTO item VALUES (1052, ' MANUAL LABEL SUICOX 150ML (NEW) ', 3, 1, NULL, '82');
INSERT INTO item VALUES (1053, 'PACKAGING TAPE WITH APTVET LOGO', 3, 1, NULL, '83');
INSERT INTO item VALUES (1054, 'PAIL, PLASTIC 10 KG', 3, 1, NULL, '84');
INSERT INTO item VALUES (1055, 'PAIL TROVITE 3 KG ', 3, 1, NULL, '85');
INSERT INTO item VALUES (1056, 'PARTITION LAKTAMINO 1 LITER', 3, 1, NULL, '86');
INSERT INTO item VALUES (1057, 'PARTITION KABEZEN  1 LITER', 3, 1, NULL, '87');
INSERT INTO item VALUES (1058, 'PARTITION VERMGUARD 2 KG', 3, 1, NULL, '88');
INSERT INTO item VALUES (1059, 'PARTITION FOR VIALS 100ML', 3, 1, NULL, '89');
INSERT INTO item VALUES (1060, 'PARTITION GLUTASEP 4 LITERS', 3, 1, NULL, '90');
INSERT INTO item VALUES (1061, 'PLASTIC BOTTLE SCOURLAK 150ML', 3, 1, NULL, '91');
INSERT INTO item VALUES (1062, 'PLASTIC BOTTLE GLUTASEP 4 LITERS', 3, 1, NULL, '92');
INSERT INTO item VALUES (1063, 'PLASTIC BOTTLE CAP KABEZEN 1 LITER', 3, 1, NULL, '93');
INSERT INTO item VALUES (1064, 'PLASTIC BOTTLE LAKTAMINO/LAKSOVIT 1L', 3, 1, NULL, '94');
INSERT INTO item VALUES (1065, 'PLUG GLUTASEP 4 LITERS', 3, 1, NULL, '95');
INSERT INTO item VALUES (1066, 'PLUG FOR JAR 100 GMS.', 3, 1, NULL, '96');
INSERT INTO item VALUES (1067, 'PLUNGER SCOURLAK 150ML ', 3, 1, NULL, '97');
INSERT INTO item VALUES (1068, 'ROLL LABEL VIBEFLEX 100ML - JICKSTAR', 3, 1, NULL, '98');
INSERT INTO item VALUES (1069, 'ROLL LABEL VIBEFLEX 10ML - EXPORT', 3, 1, NULL, '99');
INSERT INTO item VALUES (1070, 'RUBBER BAND', 3, 1, NULL, '100');
INSERT INTO item VALUES (1071, 'RUBBER STOPPER 10/100ML', 3, 1, NULL, '101');
INSERT INTO item VALUES (1072, 'SILVER FOIL SACHET 100G', 3, 1, NULL, '102');
INSERT INTO item VALUES (1073, 'STICKER BLACK W/ APT LOGO', 3, 1, NULL, '103');
INSERT INTO item VALUES (1074, 'TRANSPARENT PLASTIC 1KG', 3, 1, NULL, '104');
INSERT INTO item VALUES (1075, 'LABEL MADURAMYCIN 500 G', 3, 1, NULL, '105');
INSERT INTO item VALUES (1076, 'LABEL FLOXAVET INJECTION 100ML', 3, 1, NULL, '106');
INSERT INTO item VALUES (1077, 'C.BOX SEALINE LARGE', 3, 1, NULL, '107');
INSERT INTO item VALUES (1078, 'C.BOX SEALINE 60ML
', 3, 1, NULL, '108');
INSERT INTO item VALUES (1079, 'AMBER VIALS 10 ML', 3, 1, NULL, '109');
INSERT INTO item VALUES (1080, 'LABEL STERILE WATER 100 ML', 3, 1, NULL, '110');
INSERT INTO item VALUES (1081, 'LABEL FLOXAVET 100 ML', 3, 1, NULL, '111');
INSERT INTO item VALUES (1082, 'LABEL GENCOTIL 10%', 3, 1, NULL, '112');
INSERT INTO item VALUES (1083, 'LABEL LINCOGEN 100 ML', 3, 1, NULL, '113');
INSERT INTO item VALUES (1084, 'LABEL TROVITE 3 KG', 3, 1, NULL, '114');
INSERT INTO item VALUES (1085, 'LABEL PYRILLAK WSP', 3, 1, NULL, '115');
INSERT INTO item VALUES (1086, 'LABEL TRISULDINE INJECTION 100 ML', 3, 1, NULL, '116');
INSERT INTO item VALUES (1087, 'C.BOX VERMGUARD', 3, 1, NULL, '117');
INSERT INTO item VALUES (1088, 'PLASTIC SPOON', 3, 1, NULL, '118');
INSERT INTO item VALUES (1089, 'LABEL APTAMOX PFI', 3, 1, NULL, '119');
INSERT INTO item VALUES (1090, 'LABEL DOXYVET 1L', 3, 1, NULL, '120');
INSERT INTO item VALUES (1091, 'LABEL CHROMIUM 500 G', 3, 1, NULL, '121');
INSERT INTO item VALUES (1092, 'LABEL COCCIVET 1L', 3, 1, NULL, '122');
INSERT INTO item VALUES (1093, 'LABEL LC MINERAL PREMIX 20 KG', 3, 1, NULL, '123');
INSERT INTO item VALUES (1094, 'LABEL VIBEPLEX FORTE 100 ML', 3, 1, NULL, '124');
INSERT INTO item VALUES (1095, 'LABEL AMPIDEXTIN 100 ML', 3, 1, NULL, '125');
INSERT INTO item VALUES (1096, 'LABEL OXYVET LA 100 ML', 3, 1, NULL, '126');
INSERT INTO item VALUES (1097, 'LABEL PNEUMOFEN 100 ML', 3, 1, NULL, '127');
INSERT INTO item VALUES (1098, 'LABEL THIOXONE 100 ML', 3, 1, NULL, '128');
INSERT INTO item VALUES (1099, 'TRANSPARENT PLASTIC 2O KG', 3, 1, NULL, '129');
INSERT INTO item VALUES (1100, 'C.BOX SEALINE MEDIUM', 3, 1, NULL, '130');
INSERT INTO item VALUES (1101, 'LABEL WELLVIT FORMULA 1L', 3, 1, NULL, '131');
INSERT INTO item VALUES (1102, 'LABEL POULVIT EGG BOOSTER 3KG', 3, 1, NULL, '132');
INSERT INTO item VALUES (1103, 'LABEL SUICOX 150ML', 3, 1, NULL, '133');
INSERT INTO item VALUES (1104, 'PVC FILM VERMGUARD 2KG', 3, 1, NULL, '134');
INSERT INTO item VALUES (1105, 'LABEL ACI ADEC WSP 1KG', 3, 1, NULL, '135');
INSERT INTO item VALUES (1106, 'LABEL ACI ADEC OS IL', 3, 1, NULL, '136');
INSERT INTO item VALUES (1107, 'LABEL APTISOL CP 1L', 3, 1, NULL, '137');
INSERT INTO item VALUES (1108, 'LABEL RACTOVET ME 500G', 3, 1, NULL, '138');
INSERT INTO item VALUES (1109, 'LABEL RACTOVET ME 20 KG', 3, 1, NULL, '139');
INSERT INTO item VALUES (1110, 'LABEL BRONCHO AIDE 20KG', 3, 1, NULL, '140');
INSERT INTO item VALUES (1111, 'LABEL BRONCHO AIDE 500G', 3, 1, NULL, '141');
INSERT INTO item VALUES (1112, 'WELBEST JAR WITH CAP 2KG', 3, 1, NULL, '142');
INSERT INTO item VALUES (1113, 'CANOLA OIL CONTAINER 17KG', 3, 1, NULL, '143');
INSERT INTO item VALUES (1114, 'ALUM. CAP PLAIN 15 ML', 3, 1, NULL, '144');
INSERT INTO item VALUES (1115, 'ALUM. CAP W/ APTVET LOGO 60ML (new lay-out)', 3, 1, NULL, '145');
INSERT INTO item VALUES (1116, 'ALUM. CAP W/ APTVET LOGO 120ML ', 3, 1, NULL, '146');
INSERT INTO item VALUES (1117, 'AMBER BOTTLE  B.R. 15ML', 3, 1, NULL, '147');
INSERT INTO item VALUES (1118, 'AMBER BOTTLE  B.R. 30ML', 3, 1, NULL, '148');
INSERT INTO item VALUES (1119, 'AMBER BOTTLE 60ML', 3, 1, NULL, '149');
INSERT INTO item VALUES (1120, 'AMBER BOTTLE 120ML', 3, 1, NULL, '150');
INSERT INTO item VALUES (1121, 'CAP BIOCALCIUM', 3, 1, NULL, '151');
INSERT INTO item VALUES (1122, 'CAP DOG SHAMPOO 250ML', 3, 1, NULL, '152');
INSERT INTO item VALUES (1123, 'CAP MESS OUT 2 LITERS', 3, 1, NULL, '153');
INSERT INTO item VALUES (1124, 'CAP MESS OUT 50ml Blue', 3, 1, NULL, '154');
INSERT INTO item VALUES (1125, 'CAP PETCARE 15ML', 3, 1, NULL, '155');
INSERT INTO item VALUES (1126, 'CAP PUSH DOWN 75ML', 3, 1, NULL, '156');
INSERT INTO item VALUES (1127, 'C. BOX, Syrup  60ML', 3, 1, NULL, '157');
INSERT INTO item VALUES (1128, 'C. BOX BIOCALCIUM 9 X 50 ', 3, 1, NULL, '158');
INSERT INTO item VALUES (1129, 'C. BOX COATCARE 300ML', 3, 1, NULL, '159');
INSERT INTO item VALUES (1130, 'C. BOX, Dermadex 60 ml', 3, 1, NULL, '160');
INSERT INTO item VALUES (1131, 'C. BOX DOG SHAMPOO 250ML', 3, 1, NULL, '161');
INSERT INTO item VALUES (1132, 'C. BOX MULTI SYRUP 120ML', 3, 1, NULL, '162');
INSERT INTO item VALUES (1133, 'IND. BOX COATSHINE 60ML', 3, 1, NULL, '163');
INSERT INTO item VALUES (1134, 'IND. BOX COATSHINE 120ML', 3, 1, NULL, '164');
INSERT INTO item VALUES (1135, 'IND. BOX COLIMOXYN 60ML', 3, 1, NULL, '165');
INSERT INTO item VALUES (1136, 'IND. BOX COLIMOXYN 60ML - (EXPORT)', 3, 1, NULL, '166');
INSERT INTO item VALUES (1137, 'IND. BOX DERMADEX 60ML', 3, 1, NULL, '167');
INSERT INTO item VALUES (1138, 'IND. BOX GENTIN 7ML', 3, 1, NULL, '168');
INSERT INTO item VALUES (1139, 'IND. BOX LC- SCOUR 60ML', 3, 1, NULL, '169');
INSERT INTO item VALUES (1140, 'IND. BOX LC- VIT 60ML', 3, 1, NULL, '170');
INSERT INTO item VALUES (1141, 'IND. BOX LC-SELEN 250ML', 3, 1, NULL, '171');
INSERT INTO item VALUES (1142, 'IND. BOX LC-VIT  120ML', 3, 1, NULL, '172');
INSERT INTO item VALUES (1143, 'IND. BOX LC-VIT 15 ML - EXPORT', 3, 1, NULL, '173');
INSERT INTO item VALUES (1144, 'IND. BOX LC-VIT  120ML (export)', 3, 1, NULL, '174');
INSERT INTO item VALUES (1145, 'IND. BOX NEMATOCIDE 15ML', 3, 1, NULL, '175');
INSERT INTO item VALUES (1146, 'IND. BOX NEMATOCIDE 60ML', 3, 1, NULL, '176');
INSERT INTO item VALUES (1147, 'IND. BOX NUTRICAL 60ml', 3, 1, NULL, '177');
INSERT INTO item VALUES (1148, 'IND. BOX NUTRICAL 120ml', 3, 1, NULL, '178');
INSERT INTO item VALUES (1149, 'INDUCTION LINER', 3, 1, NULL, '179');
INSERT INTO item VALUES (1150, 'INSERT DERMADEX 60ML ', 3, 1, NULL, '180');
INSERT INTO item VALUES (1151, 'LAMI PET/FOIL WITH ZIPLOCK (500GMS)', 3, 1, NULL, '181');
INSERT INTO item VALUES (1152, 'LABEL APT FORMULA ONE NUTRAMIX 1kg', 3, 1, NULL, '182');
INSERT INTO item VALUES (1153, 'LABEL APT FORMULA ONE NUTRAMIX 500g', 3, 1, NULL, '183');
INSERT INTO item VALUES (1154, 'LABEL COATSHINE 120ML', 3, 1, NULL, '184');
INSERT INTO item VALUES (1155, 'LABEL COATSHINE 60ML', 3, 1, NULL, '185');
INSERT INTO item VALUES (1156, 'LABEL COLIMOXYN 60ML (LOCAL)', 3, 1, NULL, '186');
INSERT INTO item VALUES (1157, 'LABEL COLIMOXYN 60ML (export)', 3, 1, NULL, '187');
INSERT INTO item VALUES (1158, 'LABEL DELTACAL TABLET', 3, 1, NULL, '188');
INSERT INTO item VALUES (1159, 'LABEL GROOM AID-30ML (SAMPLE SIZE)', 3, 1, NULL, '189');
INSERT INTO item VALUES (1160, 'LABEL GROOM AIDE (FRESH BLOSSOM)', 3, 1, NULL, '190');
INSERT INTO item VALUES (1161, 'LABEL GROOM AIDE (FRUITY BURST)', 3, 1, NULL, '191');
INSERT INTO item VALUES (1162, 'LABEL GROOM AIDE (SPRING FRESH)', 3, 1, NULL, '192');
INSERT INTO item VALUES (1163, 'LABEL GROOM AIDE (SWEET DESIRE)', 3, 1, NULL, '193');
INSERT INTO item VALUES (1164, 'LABEL GROOM AIDE (TUTTI FRUITY)', 3, 1, NULL, '194');
INSERT INTO item VALUES (1165, 'LABEL LC-SCOUR 60ML ', 3, 1, NULL, '195');
INSERT INTO item VALUES (1166, 'LABEL LC-VIT 15ML', 3, 1, NULL, '196');
INSERT INTO item VALUES (1167, 'LABEL LC-VIT 15ML - EXPORT', 3, 1, NULL, '197');
INSERT INTO item VALUES (1168, 'LABEL LC-VIT 60ML', 3, 1, NULL, '198');
INSERT INTO item VALUES (1169, 'LABEL LC-VIT 120ML', 3, 1, NULL, '199');
INSERT INTO item VALUES (1170, 'LABEL LC-VIT 120ML (export)', 3, 1, NULL, '200');
INSERT INTO item VALUES (1171, 'LABEL LC-VIT OB', 3, 1, NULL, '201');
INSERT INTO item VALUES (1172, 'LABEL LITTLE MATE 500gm', 3, 1, NULL, '202');
INSERT INTO item VALUES (1173, 'LABEL LITTLE MATE 1 KG', 3, 1, NULL, '203');
INSERT INTO item VALUES (1174, 'LABEL METHIOVET ', 3, 1, NULL, '204');
INSERT INTO item VALUES (1175, 'LABEL NEMATOCIDE 15ML', 3, 1, NULL, '205');
INSERT INTO item VALUES (1176, 'LABEL NEMATOCIDE 60ML', 3, 1, NULL, '206');
INSERT INTO item VALUES (1177, 'LABEL NUTRICAL 15ML', 3, 1, NULL, '207');
INSERT INTO item VALUES (1178, 'LABEL NUTRICAL 60ML', 3, 1, NULL, '208');
INSERT INTO item VALUES (1179, 'LABEL NUTRICAL 120ML', 3, 1, NULL, '209');
INSERT INTO item VALUES (1180, 'LABEL OTI-DERM 15ml', 3, 1, NULL, '210');
INSERT INTO item VALUES (1181, 'LABEL PROXANTEL TABLET ', 3, 1, NULL, '211');
INSERT INTO item VALUES (1182, 'LABEL REFAMOL CAPSULE', 3, 1, NULL, '212');
INSERT INTO item VALUES (1183, 'LABEL TRUE BLUE - BEEF', 3, 1, NULL, '213');
INSERT INTO item VALUES (1184, 'LABEL TRUE BLUE - CHICKEN', 3, 1, NULL, '214');
INSERT INTO item VALUES (1185, 'LABEL TRUE BLUE - FISH & RICE', 3, 1, NULL, '215');
INSERT INTO item VALUES (1186, 'LABEL TRUE BLUE - LAMB', 3, 1, NULL, '216');
INSERT INTO item VALUES (1187, 'LABEL TRUE BLUE - MEAT & VEGETABLES', 3, 1, NULL, '217');
INSERT INTO item VALUES (1188, 'MEDICINE DROPPER 15ML', 3, 1, NULL, '218');
INSERT INTO item VALUES (1189, 'MOISTURE ABSORBENT', 3, 1, NULL, '219');
INSERT INTO item VALUES (1190, 'PAPER LEAN', 3, 1, NULL, '220');
INSERT INTO item VALUES (1191, 'PARTITION BIOCALCIUM 9 X 50', 3, 1, NULL, '221');
INSERT INTO item VALUES (1192, 'PLASTIC BOTTLE BIOCALCIUM', 3, 1, NULL, '222');
INSERT INTO item VALUES (1193, 'PLASTIC BOTTLE MESS OUT 50ML', 3, 1, NULL, '223');
INSERT INTO item VALUES (1194, 'PLASTIC BOTTLE MESS OUT 2LITER', 3, 1, NULL, '224');
INSERT INTO item VALUES (1195, 'PLASTIC BOTTLE PET COLOGNE 150ml', 3, 1, NULL, '225');
INSERT INTO item VALUES (1196, 'PLASTIC BOTTLE PETCARE 15ML', 3, 1, NULL, '226');
INSERT INTO item VALUES (1197, 'PLASTIC BOTTLE DOG SHAMPOO 250ml', 3, 1, NULL, '227');
INSERT INTO item VALUES (1198, 'PLUG PETCARE 15ML', 3, 1, NULL, '228');
INSERT INTO item VALUES (1199, 'PUMP GROOM-AIDE 380ml', 3, 1, NULL, '229');
INSERT INTO item VALUES (1200, 'PVC RIGID FILM', 3, 1, NULL, '230');
INSERT INTO item VALUES (1201, 'SPRAYER PET COLOGNE', 3, 1, NULL, '231');
INSERT INTO item VALUES (1202, 'STICKER PROXANTEL 500MG TABS.', 3, 1, NULL, '232');
INSERT INTO item VALUES (1203, 'STICKER W/ APT LOGO (BLUE)', 3, 1, NULL, '233');
INSERT INTO item VALUES (1204, 'STICKER NEMATOCIDE', 3, 1, NULL, '234');
INSERT INTO item VALUES (1205, 'VIANNO BOTTLE', 3, 1, NULL, '235');
INSERT INTO item VALUES (1206, 'LABEL APT FORMULA 45 GRAMS', 3, 1, NULL, '236');
INSERT INTO item VALUES (1207, 'LABEL INNER AID', 3, 1, NULL, '237');
INSERT INTO item VALUES (1208, 'IND. BOX OTIDERM 15ML', 3, 1, NULL, '238');
INSERT INTO item VALUES (1209, 'LABEL DERMADEX 60ML', 3, 1, NULL, '239');
INSERT INTO item VALUES (1210, 'WELBEST JAR 1 KG WITH CAP', 3, 1, NULL, '240');
INSERT INTO item VALUES (1211, 'SILVER FOIL 500 G', 3, 1, NULL, '241');
INSERT INTO item VALUES (1212, 'FOIL, SILVER 1 KG', 3, 1, NULL, '242');
INSERT INTO item VALUES (1213, 'LABEL LITTLE MATE 45 G', 3, 1, NULL, '243');
INSERT INTO item VALUES (1214, 'STICKER, LITTLE MATE/APT FORMULA DOSAGE 500 G', 3, 1, NULL, '244');
INSERT INTO item VALUES (1215, 'LABEL GENTIN 7 ML', 3, 1, NULL, '245');
INSERT INTO item VALUES (1216, 'LABEL LACTOCARE 100 G', 3, 1, NULL, '246');
INSERT INTO item VALUES (1217, 'LABEL PETZYME', 3, 1, NULL, '247');
INSERT INTO item VALUES (1218, 'CREAM JAR WITH COVER 100 G', 3, 1, NULL, '248');
INSERT INTO item VALUES (1219, 'LABEL PET COLOGNE FRESH BLOSSOM 125 ML', 3, 1, NULL, '249');
INSERT INTO item VALUES (1220, 'LABEL PETZYME 20 G', 3, 1, NULL, '250');
INSERT INTO item VALUES (1221, 'LABEL LACTOCARE 20 G', 3, 1, NULL, '251');
INSERT INTO item VALUES (1222, 'LABEL LC DOX TABLET', 3, 1, NULL, '252');
INSERT INTO item VALUES (1223, 'LABEL LITTLE MATE 100 G', 3, 1, NULL, '253');
INSERT INTO item VALUES (1224, 'LABEL LC SELEN 250ML', 3, 1, NULL, '254');
INSERT INTO item VALUES (1225, 'PVC SHRINKABLE FILM 100G ', 3, 1, NULL, '255');
INSERT INTO item VALUES (1226, 'IND. BOX HEMACARE FE 60ML', 3, 1, NULL, '256');
INSERT INTO item VALUES (1227, 'IND. BOX HEMACARE FE 120ML', 3, 1, NULL, '257');
INSERT INTO item VALUES (1228, 'LABEL HEMACARE FE 60ML', 3, 1, NULL, '258');
INSERT INTO item VALUES (1229, 'LABEL HEMACARE FE 120ML', 3, 1, NULL, '259');
INSERT INTO item VALUES (1230, 'LABEL DERM AID SHAMPOO 250ML', 3, 1, NULL, '260');
INSERT INTO item VALUES (1231, 'IND. BOX DERM AID SHAMPOO 250ML', 3, 1, NULL, '261');
INSERT INTO item VALUES (1232, 'SHINDY BOTTLE WITH DOME CAP 30ML', 3, 1, NULL, '262');
INSERT INTO item VALUES (1233, 'TRIGGER SPRAYER', 3, 1, NULL, '263');
INSERT INTO item VALUES (1234, 'LABEL APT FORMULA NUTRAMIX 100G', 3, 1, NULL, '264');
INSERT INTO item VALUES (1235, 'STICKER LABEL FREE LITTLE MATE BASEMIX', 3, 1, NULL, '265');
INSERT INTO item VALUES (1236, 'PACKAGING TAPE NUTRA TECH LOGO', 3, 1, NULL, '266');
INSERT INTO item VALUES (1237, 'HOUSEBOX CYANOPRO 20G X 12 SCHT', 3, 1, NULL, '267');
INSERT INTO item VALUES (1238, 'HOUSEBOX PROLYTE 20G X 12 SCHT', 3, 1, NULL, '268');
INSERT INTO item VALUES (1239, 'IND. BOX BIO - IRON 60ML', 3, 1, NULL, '269');
INSERT INTO item VALUES (1240, 'IND. BOX BIO - IRON 120ML', 3, 1, NULL, '270');
INSERT INTO item VALUES (1241, 'IND. BOX BIOCAL 60ML', 3, 1, NULL, '271');
INSERT INTO item VALUES (1242, 'IND. BOX BIOCAL 120ML', 3, 1, NULL, '272');
INSERT INTO item VALUES (1243, 'IND. BOX GROWVITE 60ML', 3, 1, NULL, '273');
INSERT INTO item VALUES (1244, 'IND. BOX GROWVITE 120ML', 3, 1, NULL, '274');
INSERT INTO item VALUES (1245, 'LABEL  BIOCAL 15 ML', 3, 1, NULL, '275');
INSERT INTO item VALUES (1246, 'LABEL BIOCAL 60ML', 3, 1, NULL, '276');
INSERT INTO item VALUES (1247, 'LABEL BIOCAL 120ML', 3, 1, NULL, '277');
INSERT INTO item VALUES (1248, 'LABEL BIO-IRON 15ML', 3, 1, NULL, '278');
INSERT INTO item VALUES (1249, 'LABEL BIO-IRON 60ML', 3, 1, NULL, '279');
INSERT INTO item VALUES (1250, 'LABEL BIO-IRON 120ML', 3, 1, NULL, '280');
INSERT INTO item VALUES (1251, 'LABEL CYANOPRO 200GMS. (215MM X 95MM) JAR', 3, 1, NULL, '281');
INSERT INTO item VALUES (1252, 'LABEL CYANOPRO 200GMS. (90MM X 100MM) SACHET', 3, 1, NULL, '282');
INSERT INTO item VALUES (1253, 'LABEL CYANOPRO 1KG / 300MM X 154MM', 3, 1, NULL, '283');
INSERT INTO item VALUES (1254, 'LABEL CYANOPRO 20GMS. SACHET', 3, 1, NULL, '284');
INSERT INTO item VALUES (1255, 'LABEL COATCARE 300ML - LAVANDER(front)', 3, 1, NULL, '285');
INSERT INTO item VALUES (1256, 'LABEL COATCARE 300ML - LAVANDER(back)', 3, 1, NULL, '286');
INSERT INTO item VALUES (1257, 'LABEL COATCARE 300ML - MELON (front)', 3, 1, NULL, '287');
INSERT INTO item VALUES (1258, 'LABEL COATCARE 300ML - MELON (back)', 3, 1, NULL, '288');
INSERT INTO item VALUES (1259, 'LABEL COATCARE 300ML - TALC (front)', 3, 1, NULL, '289');
INSERT INTO item VALUES (1260, 'LABEL COATCARE 300ML - TALC (back)', 3, 1, NULL, '290');
INSERT INTO item VALUES (1261, 'LABEL COATCARE 300ML - SBS(front)', 3, 1, NULL, '291');
INSERT INTO item VALUES (1262, 'LABEL COATCARE 300ML - SBS(back)', 3, 1, NULL, '292');
INSERT INTO item VALUES (1263, 'LABEL GROWVITE 120 ML', 3, 1, NULL, '293');
INSERT INTO item VALUES (1264, 'LABEL GROWVITE 60 ML', 3, 1, NULL, '294');
INSERT INTO item VALUES (1265, 'LABEL GROWVITE 15ML', 3, 1, NULL, '295');
INSERT INTO item VALUES (1266, 'LAMI PET/FOIL WITH ZIPLOCK (200GMS)', 3, 1, NULL, '296');
INSERT INTO item VALUES (1267, 'WELBEST JAR WHITE  2 KG', 3, 1, NULL, '297');
INSERT INTO item VALUES (1268, 'LABEL, PROLYTE 1 KG', 3, 1, NULL, '298');
INSERT INTO item VALUES (1269, 'LABEL, PROLYTE 20 G', 3, 1, NULL, '299');
INSERT INTO item VALUES (1270, 'LABEL, PROLYTE 200 G', 3, 1, NULL, '300');
INSERT INTO item VALUES (1271, 'LABEL, COATCARE APPLE FRONT', 3, 1, NULL, '301');
INSERT INTO item VALUES (1272, 'LABEL, COATCARE APPLE BACK
', 3, 1, NULL, '302');
INSERT INTO item VALUES (1273, 'LABEL, COATCARE PEPPERMINT FRONT
', 3, 1, NULL, '303');
INSERT INTO item VALUES (1274, 'LABEL, COATCARE PEPPERMINT BACK
', 3, 1, NULL, '304');
INSERT INTO item VALUES (1275, 'PVC BOTTLE COATCARE 300 ML', 3, 1, NULL, '305');
INSERT INTO item VALUES (1276, 'BOTTLE, W/CAP & PLUG (DEXTROSE 320 GM)', 3, 1, NULL, '306');
INSERT INTO item VALUES (1277, 'PVC FILM (DEXTROSE POWDER 320G)', 3, 1, NULL, '307');
INSERT INTO item VALUES (1278, 'C. BOX VERMGUARD 2KG', 3, 1, NULL, '308');
INSERT INTO item VALUES (1279, 'SHRINK WRAP (L-35CM, W-22CM)', 3, 1, NULL, '309');
INSERT INTO item VALUES (1280, 'LABEL MANOXAL 60ML', 3, 1, NULL, '310');
INSERT INTO item VALUES (1281, 'IND. BOX MANOXAL 400ML', 3, 1, NULL, '311');
INSERT INTO item VALUES (1282, 'NUTRAMED BAG', 3, 1, NULL, '312');
INSERT INTO item VALUES (1283, 'LABEL MANOXAL 400ML', 3, 1, NULL, '313');
INSERT INTO item VALUES (1284, '100ML BR PET CLEAR BOTTLE WITH CAP', 3, 1, NULL, '314');
INSERT INTO item VALUES (1285, 'LABEL COATSHIELD 100ML', 3, 1, NULL, '315');
INSERT INTO item VALUES (1286, 'C. BOX PROLYTE CYANOPRO 1KG', 3, 1, NULL, '316');
INSERT INTO item VALUES (1287, 'ALUMINUM FOIL FOR STRIP PACKAGING OF TABLET', 3, 1, NULL, '317');
INSERT INTO item VALUES (1288, 'ALUMINUM FOIL WITH CELLOPHANE FOR STRIP PACKAGING OF TABLET', 3, 1, NULL, '318');
INSERT INTO item VALUES (1289, 'PVC FILM BLISTER FOR PACKAGING OF TABLET', 3, 1, NULL, '319');
INSERT INTO item VALUES (1290, 'ALUMINUM CAP W/OUT LOGO 120ML', 3, 1, NULL, '320');
INSERT INTO item VALUES (1291, 'ALUMINUM CAP W/OUT LOGO FOR 60ML', 3, 1, NULL, '321');
INSERT INTO item VALUES (1292, 'BLUE, SEMITRANSPARENT PLASTIC PUMP BOTTLE (100ML)', 3, 1, NULL, '322');
INSERT INTO item VALUES (1293, 'BLUE, SEMITRANSPARENT PLASTIC PUMP BOTTLE (120ML)', 3, 1, NULL, '323');
INSERT INTO item VALUES (1294, 'HDPE BOTTLE (50ML)', 3, 1, NULL, '324');
INSERT INTO item VALUES (1295, 'HDPE BOTTLE (30ML)', 3, 1, NULL, '325');
INSERT INTO item VALUES (1296, 'SILICA GEL 1GRAM', 3, 1, NULL, '326');
INSERT INTO item VALUES (1297, '75ML WM HDPE BOTTLE (H-400MM; D-212MM)', 3, 1, NULL, '327');
INSERT INTO item VALUES (1298, 'PAPER LEAN 4X4, (CUT SIZE)', 3, 1, NULL, '328');
INSERT INTO item VALUES (1299, 'STRIP FOIL SIZE 150MM', 3, 1, NULL, '329');
INSERT INTO item VALUES (1300, 'PE BAG 20 X 40', 3, 1, NULL, '330');
INSERT INTO item VALUES (1301, 'PET BOTTLES', 3, 1, NULL, '331');
INSERT INTO item VALUES (1302, 'ICE CHEST MEDIUM (BOXH SW103)', 3, 1, NULL, '332');
INSERT INTO item VALUES (1303, 'ICE CHEST X-MED(BOX2)', 3, 1, NULL, '333');
INSERT INTO item VALUES (1304, 'ICE CHEST LARGE (BOX 302)', 3, 1, NULL, '334');
INSERT INTO item VALUES (1305, 'CENTRIFUDGE BOTTLE 500 ML', 3, 1, NULL, '335');
INSERT INTO item VALUES (1306, 'TROP-BIO VAC PMB BOX, 100ML', 3, 1, NULL, '336');
INSERT INTO item VALUES (1307, 'TROP-BIO VAC PMB LABEL, 100ML', 3, 1, NULL, '337');
INSERT INTO item VALUES (1308, 'PLASTIC CLEAR TRANSPARENT (20X30)', 3, 1, NULL, '338');
INSERT INTO item VALUES (1309, 'PLASTIC CLEAR TRANSPARENT (16X24)', 3, 1, NULL, '339');
INSERT INTO item VALUES (1310, 'NCD BOTTLE WITH CAP AND PLUG', 3, 1, NULL, '340');
INSERT INTO item VALUES (1311, 'PLASTIC CLEAR TRANSPARENT 25 X 50', 3, 1, NULL, '341');
INSERT INTO item VALUES (1312, 'TROP BIO VAC-HS (BOX)', 3, 1, NULL, '342');
INSERT INTO item VALUES (1313, 'LABEL, OLVAC B+6+R', 3, 1, NULL, '343');
INSERT INTO item VALUES (1314, 'LABEL, OLVAC A+B+6+R', 3, 1, NULL, '344');
INSERT INTO item VALUES (1315, 'TROP BIO VAC-HS (LABEL)', 3, 1, NULL, '345');
INSERT INTO item VALUES (1316, 'LABEL BRONCHOTECH 1L', 3, 1, NULL, '3000');
INSERT INTO item VALUES (1317, 'LABEL XGUARD 1L', 3, 1, NULL, '3001');
INSERT INTO item VALUES (1318, 'LABEL VIBEFLEX SP 1KG', 3, 1, NULL, '3002');
INSERT INTO item VALUES (1319, 'LABEL SPRAYVY 180ML', 3, 1, NULL, '3003');
INSERT INTO item VALUES (1320, 'INDIVIDUAL BOX SPRAYVY 180ML', 3, 1, NULL, '3004');
INSERT INTO item VALUES (1321, 'LABEL OLVAC A+B', 3, 1, NULL, '3005');
INSERT INTO item VALUES (1322, 'LABEL VIBEFLEX 100ML - EXPORT', 3, 1, NULL, '3006');
INSERT INTO item VALUES (1323, 'LABEL ACI VITE 100G', 3, 1, NULL, '3007');
INSERT INTO item VALUES (1324, 'LABEL PIGRAUCILLIN 20% 100G', 3, 1, NULL, '3008');
INSERT INTO item VALUES (1325, 'LABEL ACI-TMPS 100G', 3, 1, NULL, '3009');
INSERT INTO item VALUES (1326, 'LABEL PIGROW ADE 100ML', 3, 1, NULL, '3010');
INSERT INTO item VALUES (1327, 'LABEL PIGROW DEX PLUS 100ML', 3, 1, NULL, '3011');
INSERT INTO item VALUES (1328, 'LABEL PIGROW FLEX 100ML', 3, 1, NULL, '3012');
INSERT INTO item VALUES (1329, 'LABEL PIGROW MEC 1% 100ML', 3, 1, NULL, '3013');
INSERT INTO item VALUES (1330, 'LABEL PIGROWTYL 20% 100ML', 3, 1, NULL, '3014');
INSERT INTO item VALUES (1331, 'LABEL PIGROW CILLIN 100ML', 3, 1, NULL, '3015');
INSERT INTO item VALUES (1332, 'LABEL ACI-SCOUR 100ML', 3, 1, NULL, '3016');
INSERT INTO item VALUES (1333, 'LABEL ACI DOXYTIN 100G', 3, 1, NULL, '3017');
INSERT INTO item VALUES (1334, 'CREAM JAR WITH COVER 100 G', 3, 1, NULL, '3018');
INSERT INTO item VALUES (1335, 'LABEL LK TONIC', 3, 1, NULL, '3019');
INSERT INTO item VALUES (1336, 'GALENICAL BOTTLES FOR ACI-SCOUR', 3, 1, NULL, '3020');
INSERT INTO item VALUES (1337, '60ML PLUG TYPE BOTTLE', 3, 1, NULL, '3021');
INSERT INTO item VALUES (1338, '120ML PLUG TYPE CAPS', 3, 1, NULL, '3022');
INSERT INTO item VALUES (1339, '60ML PLUG TYPE CAPS', 3, 1, NULL, '3023');
INSERT INTO item VALUES (1340, '120ML GALENICAL PLASTIC BOTTLE', 3, 1, NULL, '3024');
INSERT INTO item VALUES (1341, '120ML PLUG TYPE BOTTLE', 3, 1, NULL, '3025');
INSERT INTO item VALUES (1342, 'WHITE KRAFT BAG 20KG', 3, 1, NULL, '3026');
INSERT INTO item VALUES (1343, 'CLEAR VIALS 50ML', 3, 1, NULL, '3027');
INSERT INTO item VALUES (1344, 'PARTITION, COATSHIELD 100ML', 3, 1, NULL, '3028');
INSERT INTO item VALUES (1345, 'INDIVIDUAL BOX LC-VIT OB SYRUP 120ML', 3, 1, NULL, '3029');
INSERT INTO item VALUES (1346, 'PLUG FOR CREAM JAR 100G (LEDENYL PLUG 212)', 3, 1, NULL, '3030');
INSERT INTO item VALUES (1347, 'LABEL LC-VIT OB SYRUP 60 ML', 3, 1, NULL, '3031');
INSERT INTO item VALUES (1348, 'LABEL LC-VIT OB SYRUP 120 ML', 3, 1, NULL, '3032');
INSERT INTO item VALUES (1349, 'INDIVIDUAL BOX LC-VIT OB SYRUP 60ML', 3, 1, NULL, '3033');
INSERT INTO item VALUES (1350, 'C. BOX PROLYTE/CYANOPRO 200G', 3, 1, NULL, '3034');
INSERT INTO item VALUES (1351, 'PLASTIC BOTTLE SCOURLAK 150ML WITHOUT CAP', 3, 1, NULL, '3035');
INSERT INTO item VALUES (1352, 'INDIVIDUAL BOX VIBEFLEX 100ML', 3, 1, NULL, '3036');
INSERT INTO item VALUES (1353, 'C. BOX GROOM AID SHAMPOO', 3, 1, NULL, '3037');
INSERT INTO item VALUES (1354, 'C. BOX DERMADEX 75ML', 3, 1, NULL, '3038');
INSERT INTO item VALUES (1355, 'CAPSEAL FOR 300ML BOTTLE', 3, 1, NULL, '3039');
INSERT INTO item VALUES (1356, 'CALCIUM PROPIONATE CE-17', 2, 1, NULL, 'H-WH-RM-008');
INSERT INTO item VALUES (1357, 'LABEL COLISTILAK 1KG', 3, 1, NULL, 'PM1');
INSERT INTO item VALUES (1358, 'TRANSPARENT PLASTIC BAG 1KG', 3, 1, NULL, 'H-WH-PM-007');
INSERT INTO item VALUES (1359, 'SILICA GEL 100G', 3, 2, NULL, 'PM2');
INSERT INTO item VALUES (1360, 'QUARANTINE STICKER', 3, 2, NULL, 'PM3');


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

SELECT pg_catalog.setval('item_id_seq', 1360, true);


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

INSERT INTO stock_card VALUES (73, 5, 5, 'I', 647, NULL, 500, NULL, '2015-08-18', '2017-08-17', 'NQ-197', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (74, 5, 5, 'I', 648, NULL, 100, NULL, '2015-08-18', '2016-08-17', 'NQ-297', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (75, 5, 5, 'I', 1356, NULL, 100, NULL, '2015-08-18', '2016-08-17', 'NQ-156', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (76, 5, 5, 'I', 667, NULL, 100, NULL, '2015-08-18', '2017-08-17', 'NQ-126', 'Approved', 'KG', 'AVAILABLE');
INSERT INTO stock_card VALUES (78, 5, 2, 'I', 1357, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-003', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (79, 5, 2, 'I', 1014, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-445', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (80, 5, 2, 'I', 1358, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-908', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (81, 5, 2, 'I', 977, NULL, 1500, NULL, '2015-08-18', '2017-08-17', 'NQ-888', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (82, 5, 2, 'I', 1070, NULL, 1000, NULL, '2015-08-18', '2016-08-17', 'NQ-9PO', 'Approved', 'BOXES', 'AVAILABLE');
INSERT INTO stock_card VALUES (83, 5, 2, 'I', 1054, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-UIOP', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (84, 5, 2, 'I', 1359, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-IKMJ', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (85, 5, 2, 'I', 1360, NULL, 1000, NULL, '2015-08-18', '2017-08-17', 'NQ-ODFG', 'Approved', 'PCS', 'AVAILABLE');
INSERT INTO stock_card VALUES (72, 5, 5, 'I', 647, NULL, 100, NULL, '2015-08-18', '2016-08-17', 'NQ-196', 'Approved', 'KG', 'DEPLETED');
INSERT INTO stock_card VALUES (77, 5, 2, 'I', 1357, NULL, 100, NULL, '2015-08-18', '2016-08-17', 'NQ-002', 'Approved', 'PCS', 'DEPLETED');


--
-- Name: stock_card_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_id_seq', 85, true);


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

INSERT INTO stock_card_txn VALUES (199, 72, 100, 7, 31, NULL);
INSERT INTO stock_card_txn VALUES (200, 73, 120.83199999999999, 7, 31, NULL);
INSERT INTO stock_card_txn VALUES (201, 73, 220.83199999999999, 7, 32, NULL);
INSERT INTO stock_card_txn VALUES (202, 74, 2.5099999999999998, 7, 33, NULL);
INSERT INTO stock_card_txn VALUES (203, 75, 5.0199999999999996, 7, 34, NULL);
INSERT INTO stock_card_txn VALUES (204, 76, 52.844999999999999, 7, 35, NULL);
INSERT INTO stock_card_txn VALUES (205, 77, 100, 9, 36, NULL);
INSERT INTO stock_card_txn VALUES (206, 78, 412, 9, 36, NULL);
INSERT INTO stock_card_txn VALUES (207, 79, 51, 9, 37, NULL);
INSERT INTO stock_card_txn VALUES (208, 80, 512, 9, 38, NULL);
INSERT INTO stock_card_txn VALUES (209, 81, 512, 9, 39, NULL);
INSERT INTO stock_card_txn VALUES (210, 82, 5, 11, 40, NULL);
INSERT INTO stock_card_txn VALUES (211, 83, 51, 9, 41, NULL);
INSERT INTO stock_card_txn VALUES (212, 84, 6, 9, 42, NULL);
INSERT INTO stock_card_txn VALUES (213, 85, 51, 9, 43, NULL);


--
-- Name: stock_card_txn_id_seq; Type: SEQUENCE SET; Schema: transaction; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_txn_id_seq', 213, true);


--
-- Data for Name: transaction_type; Type: TABLE DATA; Schema: transaction; Owner: postgres
--



--
-- Name: transaction_type_id_seq; Type: SEQUENCE SET; Schema: transaction; Owner: postgres
--

SELECT pg_catalog.setval('transaction_type_id_seq', 1, false);


SET search_path = audit, pg_catalog;

--
-- Name: audit_trail_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY audit_trail
    ADD CONSTRAINT audit_trail_pkey PRIMARY KEY (id);


SET search_path = line_clearance, pg_catalog;

--
-- Name: coding_room_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coding_room_line_clearance
    ADD CONSTRAINT coding_room_pkey PRIMARY KEY (id);


--
-- Name: compounding_area_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY compounding_area_line_clearance
    ADD CONSTRAINT compounding_area_pkey PRIMARY KEY (id);


--
-- Name: dispensing_area_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dispensing_area_line_clearance
    ADD CONSTRAINT dispensing_area_pkey PRIMARY KEY (id);


--
-- Name: filling_area_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filling_area_line_clearance
    ADD CONSTRAINT filling_area_pkey PRIMARY KEY (id);


--
-- Name: labeling_packg_area_line_clearance_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY labeling_packg_area_line_clearance
    ADD CONSTRAINT labeling_packg_area_line_clearance_pkey PRIMARY KEY (id);


--
-- Name: type_pkey; Type: CONSTRAINT; Schema: line_clearance; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


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
-- Name: product_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: unit_pkey; Type: CONSTRAINT; Schema: main; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (id);


SET search_path = mbr, pg_catalog;

--
-- Name: batch_item_requirement_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY batch_item_requirement
    ADD CONSTRAINT batch_item_requirement_pkey PRIMARY KEY (id);


--
-- Name: bottling_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bottling_procedure
    ADD CONSTRAINT bottling_procedure_pkey PRIMARY KEY (id);


--
-- Name: coding_spec_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coding_spec
    ADD CONSTRAINT coding_spec_pkey PRIMARY KEY (id);


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
-- Name: main_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY main_procedure
    ADD CONSTRAINT main_procedure_pkey PRIMARY KEY (id);


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

ALTER TABLE ONLY packaging_procedure
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
-- Name: sub_procedure_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sub_procedure
    ADD CONSTRAINT sub_procedure_pkey PRIMARY KEY (id);


--
-- Name: udf_pkey; Type: CONSTRAINT; Schema: mbr; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY udf
    ADD CONSTRAINT udf_pkey PRIMARY KEY (id);


SET search_path = security, pg_catalog;

--
-- Name: access_right_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role_method
    ADD CONSTRAINT access_right_pkey PRIMARY KEY (id);


--
-- Name: group_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- Name: group_role_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT group_role_pkey PRIMARY KEY (id);


--
-- Name: id_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT id_pkey PRIMARY KEY (id);


--
-- Name: id_role_name_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT id_role_name_key UNIQUE (role_name);


--
-- Name: method_name_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY method
    ADD CONSTRAINT method_name_key UNIQUE (name);


--
-- Name: method_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY method
    ADD CONSTRAINT method_pkey PRIMARY KEY (id);


--
-- Name: method_sub_method_method_id_sub_method_id_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY method_sub_method
    ADD CONSTRAINT method_sub_method_method_id_sub_method_id_key UNIQUE (method_id, sub_method_id);


--
-- Name: method_sub_method_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY method_sub_method
    ADD CONSTRAINT method_sub_method_pkey PRIMARY KEY (id);


--
-- Name: role_method_role_id_method_id_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role_method
    ADD CONSTRAINT role_method_role_id_method_id_key UNIQUE (role_id, method_id);


--
-- Name: submethod_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sub_method
    ADD CONSTRAINT submethod_pkey PRIMARY KEY (id);


--
-- Name: submethod_uri_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sub_method
    ADD CONSTRAINT submethod_uri_key UNIQUE (uri);


--
-- Name: user_group_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user_username_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_username_key UNIQUE (email_ad);


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
-- Name: item_item_cd_item_category_id_item_type_id_key; Type: CONSTRAINT; Schema: sqlsvr_copy; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_item_cd_item_category_id_item_type_id_key UNIQUE (item_cd, item_category_id, item_type_id);


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


--
-- Name: transaction_type_code_key; Type: CONSTRAINT; Schema: transaction; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transaction_type
    ADD CONSTRAINT transaction_type_code_key UNIQUE (code);


--
-- Name: transaction_type_pkey; Type: CONSTRAINT; Schema: transaction; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transaction_type
    ADD CONSTRAINT transaction_type_pkey PRIMARY KEY (id);


SET search_path = line_clearance, pg_catalog;

--
-- Name: coding_room_area_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY coding_room_line_clearance
    ADD CONSTRAINT coding_room_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


--
-- Name: coding_room_type_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY coding_room_line_clearance
    ADD CONSTRAINT coding_room_type_fkey FOREIGN KEY (type) REFERENCES type(id);


--
-- Name: compounding_area_area_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY compounding_area_line_clearance
    ADD CONSTRAINT compounding_area_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


--
-- Name: compounding_area_type_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY compounding_area_line_clearance
    ADD CONSTRAINT compounding_area_type_fkey FOREIGN KEY (type) REFERENCES type(id);


--
-- Name: dispensing_area_area_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY dispensing_area_line_clearance
    ADD CONSTRAINT dispensing_area_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


--
-- Name: dispensing_area_type_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY dispensing_area_line_clearance
    ADD CONSTRAINT dispensing_area_type_fkey FOREIGN KEY (type) REFERENCES type(id);


--
-- Name: filling_area_area_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY filling_area_line_clearance
    ADD CONSTRAINT filling_area_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


--
-- Name: filling_area_type_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY filling_area_line_clearance
    ADD CONSTRAINT filling_area_type_fkey FOREIGN KEY (type) REFERENCES type(id);


--
-- Name: labeling_packg_area_line_clearance_area_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY labeling_packg_area_line_clearance
    ADD CONSTRAINT labeling_packg_area_line_clearance_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


--
-- Name: labeling_packg_area_line_clearance_type_fkey; Type: FK CONSTRAINT; Schema: line_clearance; Owner: postgres
--

ALTER TABLE ONLY labeling_packg_area_line_clearance
    ADD CONSTRAINT labeling_packg_area_line_clearance_type_fkey FOREIGN KEY (type) REFERENCES type(id);


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
    ADD CONSTRAINT product_client_id_fkey FOREIGN KEY (company_id) REFERENCES sqlsvr_copy.company(id);


--
-- Name: product_pack_size_id_fkey; Type: FK CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pack_size_id_fkey FOREIGN KEY (pack_size_id) REFERENCES pack_size(id);


SET search_path = mbr, pg_catalog;

--
-- Name: batch_item_requirement_batch_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY batch_item_requirement
    ADD CONSTRAINT batch_item_requirement_batch_id_fkey FOREIGN KEY (batch_id) REFERENCES mbr(id);


--
-- Name: batch_item_requirement_item_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY batch_item_requirement
    ADD CONSTRAINT batch_item_requirement_item_id_fkey FOREIGN KEY (item_id) REFERENCES sqlsvr_copy.item(id);


--
-- Name: batch_item_requirement_required_qty_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY batch_item_requirement
    ADD CONSTRAINT batch_item_requirement_required_qty_unit_id_fkey FOREIGN KEY (required_qty_unit_id) REFERENCES main.unit(id);


--
-- Name: batch_item_requirement_udf_qty_unit_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY batch_item_requirement
    ADD CONSTRAINT batch_item_requirement_udf_qty_unit_id_fkey FOREIGN KEY (udf_qty_unit_id) REFERENCES main.unit(id);


--
-- Name: bottling_procedure_manufacturing_procedure_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY bottling_procedure
    ADD CONSTRAINT bottling_procedure_manufacturing_procedure_id_fkey FOREIGN KEY (manufacturing_procedure_id) REFERENCES manufacturing_procedure(id);


--
-- Name: coding_spec_area_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY coding_spec
    ADD CONSTRAINT coding_spec_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


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
-- Name: main_procedure_area_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY main_procedure
    ADD CONSTRAINT main_procedure_area_fkey FOREIGN KEY (area) REFERENCES main.area(id);


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
-- Name: packaging_material_requirement_item_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY packaging_material_requirement
    ADD CONSTRAINT packaging_material_requirement_item_id_fkey FOREIGN KEY (item_id) REFERENCES sqlsvr_copy.item(id);


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

ALTER TABLE ONLY packaging_procedure
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
-- Name: raw_material_requirement_item_id_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY raw_material_requirement
    ADD CONSTRAINT raw_material_requirement_item_id_fkey FOREIGN KEY (item_id) REFERENCES sqlsvr_copy.item(id);


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
-- Name: sub_procedure_main_procedure_fkey; Type: FK CONSTRAINT; Schema: mbr; Owner: postgres
--

ALTER TABLE ONLY sub_procedure
    ADD CONSTRAINT sub_procedure_main_procedure_fkey FOREIGN KEY (main_procedure) REFERENCES main_procedure(id);


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


SET search_path = security, pg_catalog;

--
-- Name: group_access_method_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY role_method
    ADD CONSTRAINT group_access_method_id_fkey FOREIGN KEY (method_id) REFERENCES method(id);


--
-- Name: group_role_role_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT group_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: method_sub_method_method_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY method_sub_method
    ADD CONSTRAINT method_sub_method_method_id_fkey FOREIGN KEY (method_id) REFERENCES method(id);


--
-- Name: method_sub_method_sub_method_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY method_sub_method
    ADD CONSTRAINT method_sub_method_sub_method_id_fkey FOREIGN KEY (sub_method_id) REFERENCES sub_method(id);


--
-- Name: role_access_role_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY role_method
    ADD CONSTRAINT role_access_role_id_fkey FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: user_group_group_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT user_group_group_id_fkey FOREIGN KEY (group_id) REFERENCES "group"(id);


--
-- Name: user_group_user_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT user_group_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- Name: user_role_user_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


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
-- Name: stock_card_txn_batch_item_req_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_batch_item_req_id_fkey FOREIGN KEY (batch_item_req_id) REFERENCES mbr.batch_item_requirement(id);


--
-- Name: stock_card_txn_stock_card_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_stock_card_id_fkey FOREIGN KEY (stock_card_id) REFERENCES sqlsvr_copy.stock_card(id);


--
-- Name: stock_card_txn_transaction_type_id_fkey; Type: FK CONSTRAINT; Schema: transaction; Owner: postgres
--

ALTER TABLE ONLY stock_card_txn
    ADD CONSTRAINT stock_card_txn_transaction_type_id_fkey FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(id);


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

