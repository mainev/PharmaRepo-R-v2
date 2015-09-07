--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = audit, pg_catalog;

--
-- Data for Name: audit_trail; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY audit_trail (id, username, datetime, table_name, method, action, old_value, new_value) FROM stdin;
68	mainevillarias@gmail.com	2015-08-19 16:31:31.627	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":145,"stock_card_id":0,"qty":512.0,"unit_id":5,"mbr_id":0}
69	mainevillarias@gmail.com	2015-08-19 16:31:31.768	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":146,"stock_card_id":0,"qty":100.0,"unit_id":9,"mbr_id":0}
70	mainevillarias@gmail.com	2015-08-19 16:31:31.866	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}
71	mainevillarias@gmail.com	2015-08-19 16:31:40.09	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":145,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}	
72	mainevillarias@gmail.com	2015-08-19 16:31:40.19	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":146,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}	
73	mainevillarias@gmail.com	2015-08-19 16:31:40.266	mbr	/mbr/mbr/pst_cancel_reservation	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}
74	mainevillarias@gmail.com	2015-08-19 16:34:25.671	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":132,"product_id":70,"batchSize":10.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ikm","status":"PENDING"}
75	mainevillarias@gmail.com	2015-08-19 16:46:42.756	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":147,"stock_card_id":0,"qty":512.0,"unit_id":5,"mbr_id":0}
76	mainevillarias@gmail.com	2015-08-19 16:46:43.028	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":148,"stock_card_id":0,"qty":100.0,"unit_id":9,"mbr_id":0}
77	mainevillarias@gmail.com	2015-08-19 16:46:43.129	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}
78	mainevillarias@gmail.com	2015-08-19 17:09:34.313	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":147,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}	
79	mainevillarias@gmail.com	2015-08-19 17:09:34.415	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":148,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}	
80	mainevillarias@gmail.com	2015-08-19 17:09:34.49	mbr	/mbr/mbr/pst_cancel_reservation	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}
81	mainevillarias@gmail.com	2015-08-19 17:09:46.214	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":149,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}
82	mainevillarias@gmail.com	2015-08-19 17:09:46.288	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":150,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}
83	mainevillarias@gmail.com	2015-08-19 17:09:46.352	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}
84	mainevillarias@gmail.com	2015-08-20 09:34:23.262	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":149,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}	
85	mainevillarias@gmail.com	2015-08-20 09:34:23.491	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":150,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}	
86	mainevillarias@gmail.com	2015-08-20 09:34:23.64	mbr	/mbr/mbr/pst_cancel_reservation	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}
87	mainevillarias@gmail.com	2015-08-20 13:21:09.619	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":151,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}
88	mainevillarias@gmail.com	2015-08-20 13:21:10.078	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":152,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}
89	mainevillarias@gmail.com	2015-08-20 13:21:10.204	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}
90	mainevillarias@gmail.com	2015-08-20 16:32:25.094	pack_size	/main/pack_size/pst_new_pack_size	INSERT		{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}
91	mainevillarias@gmail.com	2015-08-20 16:32:25.428	product	/main/product/pst_new_product	INSERT		{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}
92	mainevillarias@gmail.com	2015-08-20 16:32:25.494	udf	/mbr/udf/pst_new_udf	INSERT		{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"}}
93	mainevillarias@gmail.com	2015-08-20 16:32:25.61	manufacturing_procedure	/mbr/manufacturing_procedure/pst_new_mfg_proc	INSERT		{}
101	mainevillarias@gmail.com	2015-08-24 17:07:14.615	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}
94	mainevillarias@gmail.com	2015-08-20 16:32:25.825	raw_material_requirement	/mbr/raw_material_requirement/pst_new_raw_material_req	INSERT		{"id":87,"itemId":{},"quantity":111.0,"unitId":{"id":6,"name":"g"},"udfId":{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"},"productId":{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}},"part":1}
95	mainevillarias@gmail.com	2015-08-20 16:32:25.95	packaging_material_requirement	/mbr/packaging_material_requirement/pst_packg_material_req	INSERT		{"id":56,"itemId":{},"quantity":1.0,"unitId":{"id":9,"name":"pcs"},"udfId":{"id":71,"content":1.0,"unitId":{"id":7,"name":"kg"},"productId":{"id":71,"code":"p2","brandName":"colistilak fpp","genericName":"colistin sulfate","vrNo":"vrm-12-557","shelfLife":3,"areaId":{"id":3,"name":"POWDER AREA"},"classificationId":{"id":2,"description":"POWDER"},"companyId":{},"packSizeId":{"id":39,"quantity":10.0,"containerId":{"id":3,"name":"Pail"},"unitId":{"id":7,"name":"kg"}}}}}
96	mainevillarias@gmail.com	2015-08-20 16:32:26.087	compounding_procedure	/mbr/compounding_procedure/pst_new_compounding_proc	INSERT		{"stepNumber":1,"header":"mix all","footer":true,"manufacturingProcedureId":{}}
97	mainevillarias@gmail.com	2015-08-20 16:32:26.294	dosage	/mbr/dosage/pst_new_dosage	INSERT		{"id":39,"rawMaterialRequirementId":{"id":87,"itemId":{},"quantity":111.0,"unitId":{"id":6,"name":"g"},"part":1},"percentMultiplier":1.0,"compoundingProcedureId":{"stepNumber":1,"header":"mix all","footer":true,"manufacturingProcedureId":{}}}
98	mainevillarias@gmail.com	2015-08-20 16:32:26.408	equipment_requirement	/mbr/equipment_requirement/pst_create_new_equip_req	INSERT		{"id":31,"equipmentId":{"id":2,"code":"NEQ-130","name":"Encapsulating Machine"},"manufacturingProcedureId":{},"procedure":"COMPOUNDING"}
99	mainevillarias@gmail.com	2015-08-20 16:32:26.514	packaging_operation	/mbr/packaging_operation/pst_create_new_packg_operation	INSERT		{"id":41,"stepNumber":1,"header":"packaging opt","part":1,"manufacturingProcedureId":{},"doneBy":"","checkedBy":""}
100	mainevillarias@gmail.com	2015-08-20 16:32:26.602	powder_filling_procedure	/mbr/powder_filling/pst_new_powder_filling	INSERT		{"id":6,"stepNumber":1,"instruction":"procedure filling","requiresEquipment":false,"doneBy":"","checkedBy":"","manufacturingProcedureId":{}}
102	mainevillarias@gmail.com	2015-08-25 09:47:15.584	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":134,"product_id":71,"batchSize":1.0,"batchNo":"batch1","unit_id":1,"mfgDate":"2015-08-28T00:00:00","expDate":"2018-08-28T00:00:00","poNo":"ee","status":"PENDING"}
103	mainevillarias@gmail.com	2015-08-25 09:50:46.482	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":135,"product_id":71,"batchSize":15.0,"batchNo":"batch1","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"dede","status":"PENDING"}
104	mainevillarias@gmail.com	2015-08-25 09:55:12.388	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":136,"product_id":71,"batchSize":150.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"huh","status":"PENDING"}
105	mainevillarias@gmail.com	2015-08-25 09:56:08.573	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":137,"product_id":71,"batchSize":200.0,"batchNo":"batch1","unit_id":6,"mfgDate":"2015-08-19T00:00:00","expDate":"2018-08-19T00:00:00","poNo":"ttt","status":"PENDING"}
106	mainevillarias@gmail.com	2015-08-25 10:24:17.451	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":138,"product_id":71,"batchSize":100.0,"batchNo":"dsds","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"dsds","status":"PENDING"}
107	mainevillarias@gmail.com	2015-08-25 10:27:26.922	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":139,"product_id":70,"batchSize":1.0,"batchNo":"dddd","unit_id":7,"mfgDate":"2015-08-13T00:00:00","expDate":"2018-08-13T00:00:00","poNo":"dddd","status":"PENDING"}
108	mainevillarias@gmail.com	2015-08-25 11:16:42.053	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":153,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":133}
109	mainevillarias@gmail.com	2015-08-25 11:16:42.431	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":154,"stock_card_id":69,"qty":700.0,"unit_id":9,"mbr_id":133}
110	mainevillarias@gmail.com	2015-08-25 11:16:42.563	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}	{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"RESERVED"}
111	mainevillarias@gmail.com	2015-08-25 13:36:08.668	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":140,"product_id":71,"batchSize":57.0,"batchNo":"rtgf","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"5tgb","status":"PENDING"}
112	mainevillarias@gmail.com	2015-08-25 13:44:08.781	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":151,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":90}	
113	mainevillarias@gmail.com	2015-08-25 13:44:08.96	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":152,"stock_card_id":69,"qty":100.0,"unit_id":9,"mbr_id":90}	
114	mainevillarias@gmail.com	2015-08-25 13:44:09.074	mbr	/mbr/mbr/pst_cancel_reservation	UPDATE	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"RESERVED"}	{"id":90,"product_id":70,"batchSize":100.0,"batchNo":"batch1","unit_id":3,"mfgDate":"2015-08-14T00:00:00","expDate":"2018-08-14T00:00:00","poNo":"r55","status":"PENDING"}
115	mainevillarias@gmail.com	2015-08-25 13:44:11.23	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":153,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":133}	
116	mainevillarias@gmail.com	2015-08-25 13:44:11.367	stock_card_txn	/transaction/stock_card_txn/pst_delete_stock_card_txn	DELETE	{"id":154,"stock_card_id":69,"qty":700.0,"unit_id":9,"mbr_id":133}	
117	mainevillarias@gmail.com	2015-08-25 13:44:11.47	mbr	/mbr/mbr/pst_cancel_reservation	UPDATE	{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"RESERVED"}	{"id":133,"product_id":70,"batchSize":700.0,"batchNo":"batch1","unit_id":2,"mfgDate":"2015-07-31T00:00:00","expDate":"2018-07-31T00:00:00","poNo":"797","status":"PENDING"}
118	mainevillarias@gmail.com	2015-08-25 13:45:08.583	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":141,"product_id":71,"batchSize":1.0,"batchNo":"eee","unit_id":6,"mfgDate":"2015-08-27T00:00:00","expDate":"2018-08-27T00:00:00","poNo":"eee","status":"PENDING"}
119	mainevillarias@gmail.com	2015-08-25 13:46:57.627	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":142,"product_id":71,"batchSize":20.0,"batchNo":"batch5","unit_id":6,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"po09","status":"PENDING"}
120	mainevillarias@gmail.com	2015-08-25 14:15:09.748	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":143,"product_id":71,"batchSize":9.0,"batchNo":"bght","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"po9","status":"PENDING"}
121	mainevillarias@gmail.com	2015-08-25 14:20:31.985	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":144,"product_id":71,"batchSize":1.0,"batchNo":"iii","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"poi","status":"PENDING"}
122	mainevillarias@gmail.com	2015-08-25 14:25:30.114	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":145,"product_id":70,"batchSize":67.0,"batchNo":"777","unit_id":5,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"777","status":"PENDING"}
123	mainevillarias@gmail.com	2015-08-25 14:32:32.729	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"PENDING"}
124	mainevillarias@gmail.com	2015-08-25 14:34:23.007	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":147,"product_id":71,"batchSize":1.0,"batchNo":"jikm","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"pokl","status":"PENDING"}
125	mainevillarias@gmail.com	2015-08-25 14:40:17.226	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":148,"product_id":71,"batchSize":12.0,"batchNo":"batch44","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"poiuyt","status":"PENDING"}
126	mainevillarias@gmail.com	2015-08-25 14:41:28.886	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":149,"product_id":70,"batchSize":12.0,"batchNo":"ee","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"eee","status":"PENDING"}
127	mainevillarias@gmail.com	2015-08-25 14:44:30.876	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":150,"product_id":71,"batchSize":12.0,"batchNo":"ee","unit_id":5,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"ee","status":"PENDING"}
128	mainevillarias@gmail.com	2015-08-25 14:47:58.891	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":151,"product_id":71,"batchSize":12.0,"batchNo":"dwd","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"ded","status":"PENDING"}
129	mainevillarias@gmail.com	2015-08-25 14:49:39.795	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":152,"product_id":71,"batchSize":90.0,"batchNo":"jjj","unit_id":7,"mfgDate":"2015-08-20T00:00:00","expDate":"2018-08-20T00:00:00","poNo":"jjj","status":"PENDING"}
130	mainevillarias@gmail.com	2015-08-25 14:51:57.369	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"PENDING"}
131	mainevillarias@gmail.com	2015-08-25 15:00:35.176	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":154,"product_id":71,"batchSize":78.0,"batchNo":"yy","unit_id":6,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"yy","status":"PENDING"}
132	mainevillarias@gmail.com	2015-08-25 15:29:07.577	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":155,"product_id":71,"batchSize":123.0,"batchNo":"333","unit_id":7,"mfgDate":"2015-08-29T00:00:00","expDate":"2018-08-29T00:00:00","poNo":"333","status":"PENDING"}
133	mainevillarias@gmail.com	2015-08-25 15:31:02.111	mbr	/mbr/mbr/pst_new_batch	INSERT		{"id":156,"product_id":70,"batchSize":15.0,"batchNo":"555","unit_id":7,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"55","status":"PENDING"}
134	mainevillarias@gmail.com	2015-08-25 15:55:08.38	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":155,"stock_card_id":68,"qty":512.0,"unit_id":5,"mbr_id":146}
135	mainevillarias@gmail.com	2015-08-25 15:55:08.446	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":156,"stock_card_id":69,"qty":1.0,"unit_id":9,"mbr_id":146}
136	mainevillarias@gmail.com	2015-08-25 15:55:08.517	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"PENDING"}	{"id":146,"product_id":70,"batchSize":1.0,"batchNo":"batch4","unit_id":3,"mfgDate":"2015-08-21T00:00:00","expDate":"2018-08-21T00:00:00","poNo":"ded","status":"RESERVED"}
137	mainevillarias@gmail.com	2015-08-25 15:56:57.177	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":157,"stock_card_id":68,"qty":45.568,"unit_id":6,"mbr_id":153}
138	mainevillarias@gmail.com	2015-08-25 15:56:57.238	stock_card_txn	/transaction/stock_card_txn/pst_new_stock_card_txn	INSERT		{"id":158,"stock_card_id":69,"qty":89.0,"unit_id":9,"mbr_id":153}
139	mainevillarias@gmail.com	2015-08-25 15:56:57.307	mbr	/mbr/mbr/pst_reserve_mbr	UPDATE	{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"PENDING"}	{"id":153,"product_id":70,"batchSize":89.0,"batchNo":"gg","unit_id":5,"mfgDate":"2015-08-22T00:00:00","expDate":"2018-08-22T00:00:00","poNo":"ggg","status":"RESERVED"}
\.


--
-- Name: audit_trail_id_seq; Type: SEQUENCE SET; Schema: audit; Owner: postgres
--

SELECT pg_catalog.setval('audit_trail_id_seq', 139, true);


SET search_path = line_clearance, pg_catalog;

--
-- Data for Name: type; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY type (id, code, description) FROM stdin;
1	START	Line clearance for starting
2	END	Line clearance for ending
\.


SET search_path = main, pg_catalog;

--
-- Data for Name: area; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY area (id, name) FROM stdin;
1	LIQUID VET
2	LIQUID HUMAN
3	POWDER AREA
4	TABLET HUMAN
5	TABLET VET
6	POWDER VET
7	CHP
8	PARENTERAL
\.


SET search_path = line_clearance, pg_catalog;

--
-- Data for Name: coding_room_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY coding_room_line_clearance (id, step_no, instruction, checked_by, verified_by, type, area) FROM stdin;
1	1	Are there leftover materials from previous batch?			1	3
2	2	Is the room / work area clean and in order?			1	3
3	3	Is the coding machine and accessories clean and bear equipment clean tags?			1	3
4	4	Are all the printed materials required for coding available and bear approved QA stickers, as applicable?			1	3
5	5	Are the workers wearing the correct / complete uniform and other suitable protective wear?			1	3
6	6	Is the PO (Packaging Order) available in the working area?			1	3
7	7	Is the Batch No. in the Coding Request (CR) consistent with the Batch No. reflected in the Batch Packaging Order (PO)?			1	3
8	1	Are all unused / rejected materials from previous run recorded and reconciled?			2	3
9	2	Are the coded materials labeled and "batched" and secured in a rack designated for?			2	3
10	3	Are all machines unplugged and switched off?			2	3
11	4	Is the MBR properly / completely documented and signed?			2	3
12	5	Are finished goods / work-in-process from the previous batch removed from the area?			2	3
\.


--
-- Data for Name: compounding_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY compounding_area_line_clearance (id, step_no, instruction, checked_by, verified_by, type, area) FROM stdin;
1	1	All materials from previous operation have been removed in the compounding area.			1	3
2	2	Unnecessary documents, product identification of previous product have been removed.			1	3
3	3	Check the entire area for cleanliness. Usage logbooks are available in their designated place.			1	3
4	4	Room production identification tag is properly attached.			1	3
5	5	Check the availability of the Approved Manufacturing Batch Record (MBR).			1	3
6	6	Check for the correctness  and completeness of the raw materials based on the MBR.			1	3
7	7	Equipment, Tools and Accessories\n 7.1 Paddle Mixer cleaned, sanitized and in good condition.\n 7.2 Blue Drums with double-lined PE Bag cleaned, sanitized and in good condition.\n 7.3 Dust collector cleaned and in good condition.\n 7.4 Mesh Screen, Scoops and Spatulas were cleaned, sanitized and in good condition.\n 7.5 With Certified Clean Tag attached and valid cleanliness.\n 7.6 In-Process Tag is properly accomplished and attached.			1	3
8	8	Check if the temperature and %RH is within specification/s.\n Temp. Specification: NMT 25°C       Actual Reading:_________________\n %RH Specification: NMT 50%       Actual Reading:_________________			1	3
\.


--
-- Name: dispensing_area_id_seq; Type: SEQUENCE SET; Schema: line_clearance; Owner: postgres
--

SELECT pg_catalog.setval('dispensing_area_id_seq', 21, true);


--
-- Data for Name: dispensing_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY dispensing_area_line_clearance (id, step_no, instruction, checked_by, verified_by, type, area) FROM stdin;
1	1	All materials from previous operation have been removed.	\N	\N	1	3
2	1	Check if the MBR is completely filled-up and signed.	\N	\N	2	3
3	2	Equipment and accessories are cleaned, cleared of previous materials.	\N	\N	1	3
\.


--
-- Data for Name: filling_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY filling_area_line_clearance (id, step_no, instruction, checked_by, verified_by, type, area) FROM stdin;
1	1	Check if the entire filling including the equipment is cleared of all excess product of previous lot.			1	3
2	2	Check if the area is cleared of any documents from previously used and product previously compounded.			1	3
3	3	Unnecessary documents have been removed from the area.			1	3
4	4	Inspect the entire premises for cleanliness. Usage logbooks are available in their designated place.			1	3
5	5	Room Production Identification Tag is attached.			1	3
6	6	Check for the availability of the MBR.			1	3
7	7	Check the correctness and completeness of the packaging materials received based on the specification of the MBR.			1	3
8	8	Check if the equipment to use has its valid Certified Clean Tag.			1	3
10	10	Check and monitor room temperature (NMT 25°C) and Relative Humidity (NMT 50%) using NF-QA-011-01-01\n(Controlled Area).\nActual Temperature Reading: _______________   Actual Reading: _______________			1	3
11	1	Check if the MBR is properly / completely documented and signed.			2	3
12	2	Check if the product material tags are properly signed.			2	3
13	3	Remove materials used for the ended lot from the area.			2	3
14	4	Clean all equipment from the bulk powder and packaging materials used.			2	3
15	5	Removed all rejected packaging materials.			2	3
16	6	Clean and clear the filling area including the floor, inspection tables, stools and weighing balance.			2	3
17	7	Place an equipment status tag after cleaning the equipment.			2	3
9	9	Equipment, Tools and Accessories\n 9.1 Scoops cleaned, sanitized and in good condition.\n 9.2 Weighing Balance and scoops cleaned, sanitized and in good condition.			1	3
\.


--
-- Data for Name: labeling_packg_area_line_clearance; Type: TABLE DATA; Schema: line_clearance; Owner: postgres
--

COPY labeling_packg_area_line_clearance (id, step_no, instruction, checked_by, verified_by, type, area) FROM stdin;
1	1	Check if the entire packaging area is cleared of all excess product of previous lot.			1	3
2	2	Check if the area is cleared of any documents and/or products from the previous finished goods.			1	3
3	3	Remove all unnecessary documents if these are no required for the work-in-process.			1	3
4	4	Inspect the whole area for cleanliness. Usage logbooks are available in their designated place.			1	3
5	5	Check for the availability of the MBR.			1	3
6	6	Check the correctness and completeness of the materials needed received based on the MBR.			1	3
7	7	Check if the temperature is NMT 25°C. (Actual Temp. ______________)			1	3
8	8	Check for the completeness and correctness of the total filled plastic bags received based on MBR.			1	3
9	1	Check if the MBR is properly / completely documented and signed.			2	3
10	2	Check if the finished product tags are properly signed and affixed on the finished product.			2	3
11	3	Remove all finished product of the ended lot from the area.			2	3
12	4	Clean and clear the packaging area including the floor and tables.			2	3
13	5	Account and reconcile the finished product before transferring to quarantine area.			2	3
14	6	Clean and clear the packaging area including the floor & tables.			2	3
\.


--
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: line_clearance; Owner: postgres
--

SELECT pg_catalog.setval('type_id_seq', 2, true);


SET search_path = main, pg_catalog;

--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 8, true);


--
-- Data for Name: classification; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY classification (id, description) FROM stdin;
1	LIQUID
2	POWDER
3	CAPSULE
\.


--
-- Name: classification_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('classification_id_seq', 3, true);


--
-- Data for Name: container; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY container (id, name) FROM stdin;
1	Bottle
3	Pail
2	Kraft bag
4	Gallon
\.


--
-- Name: container_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('container_id_seq', 4, true);


--
-- Data for Name: equipment; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY equipment (id, code, name) FROM stdin;
1	NEQ-039	Paddle Mixer
2	NEQ-130	Encapsulating Machine
3	\N	Scoops
4	\N	Spatula
5	\N	Weighing Balance
6	\N	PE bag
8	NQC-LAB-021	Analytical Weighing Balance
7	\N	Mesh Screen #20
\.


--
-- Name: equipment_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('equipment_id_seq', 8, true);


--
-- Data for Name: unit; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY unit (id, name) FROM stdin;
1	mcL
2	mL
3	L
4	mcg
5	mg
6	g
7	kg
8	roll
9	pcs
10	capsules
11	boxes
\.


--
-- Data for Name: pack_size; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY pack_size (id, quantity, unit_id, container_id) FROM stdin;
39	10	7	3
40	100	6	1
41	500	6	1
\.


--
-- Name: pack_size_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('pack_size_id_seq', 41, true);


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Data for Name: company; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY company (id, code, descs) FROM stdin;
1	01	VACCINE
2	08	100 TDB
3	03	PRO-BIOTICS
4	07	NUTRATECH
5	04	APT-HEALTH
6	02	APT-FIGHT
7	05	BIOCARE(PET CARE)
\.


SET search_path = main, pg_catalog;

--
-- Data for Name: product; Type: TABLE DATA; Schema: main; Owner: postgres
--

COPY product (id, code, brand_name, generic_name, classification_id, company_id, vr_no, shelf_life, area_id, pack_size_id) FROM stdin;
70	p1	brand 1	gen 1	1	1	vr01	3	1	41
71	p2	colistilak fpp	colistin sulfate	2	5	vrm-12-557	3	3	39
\.


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 71, true);


--
-- Name: unit_id_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('unit_id_seq', 11, true);


SET search_path = mbr, pg_catalog;

--
-- Data for Name: mbr; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY mbr (id, product_id, batch_size, batch_no, unit_id, mfg_date, exp_date, po_no, status) FROM stdin;
173	71	15	batch1	7	2015-09-26	2018-09-26	po90	PRINTED
172	70	1	dd	7	2015-09-18	2018-09-18	dd	PENDING
171	71	1	batch1	7	2015-09-25	2018-09-25	po90	RESERVED
\.


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Data for Name: item_class; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY item_class (id, code, descs) FROM stdin;
1	02	Products
2	01	Materials
\.


--
-- Data for Name: item_category; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY item_category (id, code, descs, item_class_id) FROM stdin;
1	FG	Finished Goods	2
2	RM	Raw Materials	2
3	PM	Packaging Materials	2
4	EM	Excipient Materials	2
\.


--
-- Data for Name: item_type; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY item_type (id, code, descs) FROM stdin;
1	DM	Direct Material
2	IDM	In-Direct Material
\.


--
-- Data for Name: item; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY item (id, descs, item_category_id, item_type_id, remarks, item_cd) FROM stdin;
628	ALBENDAZOLE	2	1	\N	1
629	AMOXICILLIN TRIHYDRATE COMPACTED	2	1	\N	2
630	AMOXICILLIN TRIHYDRATE POWDER	2	1	\N	3
631	AMITRAZ	2	1	\N	4
632	ATTAPULGITE	2	1	\N	5
633	ALPHA-LIPOIC ACID	2	1	\N	6
634	ALOE VERA	2	1	\N	7
635	BENZOIC ACID	2	1	\N	8
636	BIOPERINE	2	1	\N	9
637	BLACK PEPPER	2	1	\N	10
638	BACON FLAVOR	2	1	\N	11
639	BEEF FLAVOR	2	1	\N	12
640	BENZALKONIUM CHLORIDE 80%	2	1	\N	13
641	BENZYL ALCOHOL	2	1	\N	14
642	BUTYLATED HYDROXYTOLUENE (BHT)	2	1	\N	15
643	BIOTIN 2%	2	1	\N	16
644	BROMHEXINE HCL	2	1	\N	17
645	BREWERS YEAST	2	1	\N	18
646	CANOLA OIL	2	1	\N	19
647	CALCIUM CARBONATE	2	1	\N	20
648	CABOSIL	2	1	\N	21
649	CALCIUM PANTHOTENATE	2	1	\N	22
650	CALCIUM PROPIONATE	2	1	\N	23
651	CARAMEL FLAVOR LIQUID	2	1	\N	24
652	CALCIUM CHLORIDE	2	1	\N	25
653	CALCIUM GLUCONATE	2	1	\N	26
654	CALCIUM GLUBIONATE	2	1	\N	27
655	CARAMEL COLOR POWDER	2	1	\N	28
656	COCONUT DIETHANOLAMIDE (CDEA)	2	1	\N	29
657	CRYSTALLINE FRUCTOSE	2	1	\N	30
658	CORN OIL	2	1	\N	31
659	CHERRY FLAVOR LIQUID	2	1	\N	32
660	CEFTIOFUR HCL	2	1	\N	33
661	CIPROFLOXACIN	2	1	\N	34
662	CITRIC ACID	2	1	\N	35
663	COATSHINE	2	1	\N	36
664	COLLAGEN	2	1	\N	37
665	CHOLINE CHLORIDE 50% CORN COB BASE	2	1	\N	38
666	CORNSTARCH	2	1	\N	39
667	COLISTIN SULFATE	2	1	\N	40
668	XANTHAN GUM	2	1	\N	41
669	CHROMIUM PICOLINATE	2	1	\N	42
670	COPPER SULFATE	2	1	\N	43
671	CROSS POVIDONE/ POLYPLASDONE XL	2	1	\N	44
672	DEXTROUS ANHYDROUS	2	1	\N	45
673	DEXTROSE MONOHYDRATE	2	1	\N	46
674	DI-TAB	2	1	\N	47
675	DICLAZURIL	2	1	\N	48
676	DL-METHIONINE	2	1	\N	49
677	DMDM HYDANTOIN	2	1	\N	50
678	D-PANTHENOL	2	1	\N	51
679	DOXYCYCLINE HCL	2	1	\N	52
680	DRIER DRIED PEPPER	2	1	\N	53
681	DIMETRIDAZOLE	2	1	\N	54
682	DEXAMETHASONE	2	1	\N	55
683	ENROFLOXACIN HCL	2	1	\N	56
684	ETHOXYLATED HYDROGENATED CASTOR OIL	2	1	\N	57
685	ETHYL ALCOHOL	2	1	\N	58
686	ETHYL CELLULOSE	2	1	\N	59
687	EUCALYPTOL OIL 99%	2	1	\N	60
688	EGC SIZE #3 GREEN (10-23-5)/ WHITE (20-1)	2	1	\N	61
689	EGC SIZE #1 NATURAL (1-0) NATURAL (1-0)	2	1	\N	62
690	EGC SIZE #0  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE	2	1	\N	63
691	EGC SIZE #00  NATURAL (1-0) NATURAL (1-0) VEGGIE TYPE	2	1	\N	64
692	EGC SIZE #4 GREEN DULL (10-25-5)/ GRAY (9-75-5)	2	1	\N	65
693	EGC SIZE #2  NATURAL (1-0) NATURAL (1-0)	2	1	\N	66
694	EGC SIZE #00 NATURAL & GREEN (VEGGIE TYPE)	2	1	\N	67
695	EGC SIZE #0 NATURAL & GREEN (VEGGIE TYPE)	2	1	\N	68
696	EGC SIZE #00 VEGGIE CAPSULE (WHITE/PINK)	2	1	\N	69
697	EGC SIZE #00 VEGGIE CAPSULE (WHITE/WHITE)	2	1	\N	70
698	EGC SIZE #0 BOVINE CAPS WHITE/WHITE	2	1	\N	71
699	EGC SIZE #00 BOVINE CAPS WHITE/WHITE	2	1	\N	72
700	FD & C RED # 40	2	1	\N	73
701	FERROUS SULFATE	2	1	\N	74
702	FD & C YELLOW #6	2	1	\N	75
703	FD & C YELLOW #5	2	1	\N	76
704	FD& C BLUE #1	2	1	\N	77
705	FOLIC ACID	2	1	\N	78
706	FOLIC ACID PHARMA GRADE 	2	1	\N	79
707	FORMULA ONE BASE MIX VENISON	2	1	\N	80
708	FORMULA ONE BASE MIX LIVER	2	1	\N	81
709	FORMULA ONE BASE MIX SALMON	2	1	\N	82
710	FLORFENICOL	2	1	\N	83
711	SODIUM CITRATE	2	1	\N	84
712	FRESH FRAGRANCE	2	1	\N	85
713	FRESH YACOON	2	1	\N	86
714	FRESH LUYA	2	1	\N	87
715	FRESH SAMPALOK	2	1	\N	88
716	FULL BREEZE #6	2	1	\N	89
717	FUJICALIN	2	1	\N	90
718	GENTAMYCIN SULFATE STERILE	2	1	\N	91
719	GLUTARALDEHYDE 50%	2	1	\N	92
720	GLYCERINE PURE	2	1	\N	93
721	GLYCINE NF WSP	2	1	\N	94
722	GREEN STEVIA/30G	2	1	\N	95
723	GUT AIDE P4	2	1	\N	96
724	GUT AIDE S6	2	1	\N	97
725	GUYABANO FRUIT	2	1	\N	98
726	GUYABANO LEAVES POWDER	2	1	\N	99
727	GUAVA LEAVES EXTRACT	2	1	\N	100
728	GUYABANO PULP	2	1	\N	101
729	GLUTATHIONE POWDER	2	1	\N	102
730	HIGH FRUCTOSE CORN SYRUP (HFCS)	2	1	\N	103
731	HONEY	2	1	\N	104
732	HYDROXOCOBALAMINE ACETATE	2	1	\N	105
733	IRON DEXTRAN 20%	2	1	\N	106
734	KOLLICOAT IR SUNSET YELLOW	2	1	\N	107
735	KOLLICOAT IR  YELLOW	2	1	\N	108
736	KOLLICOAT SMARTSEAL 30D	2	1	\N	109
737	KOLLICOAT IR BLACK	2	1	\N	110
738	KOLLICOAT IR BRILLIANT BLUE 	2	1	\N	111
739	KOLLICOAT IR CARMINE	2	1	\N	112
740	KOLLICOAT IR WHITE II	2	1	\N	113
741	KOLLIDON VA64 FINE	2	1	\N	114
742	KAOLIN LIGHT	2	1	\N	115
743	LACTOSE MONOHYDRATE	2	1	\N	116
744	LAVENDER SCENT	2	1	\N	117
745	LAVENDER ESSENTIAL OIL	2	1	\N	118
746	LYCHEE FLAVOR	2	1	\N	119
747	L-GLUTATHIONE REDUCED	2	1	\N	120
748	L-CITRULLINE	2	1	\N	121
749	LEVAMISOLE HCL	2	1	\N	122
750	LIDOCAINE HCL 	2	1	\N	123
751	LINCOMYCIN HCL	2	1	\N	124
752	LABSA (LINEAR ALKYL BENZYL SULPHONIC ACID)	2	1	\N	125
753	LACTOSE DMV 	2	1	\N	126
754	LIQUID GLUCOSE 43 BE	2	1	\N	127
755	L-LYSINE HCL USP GRADE	2	1	\N	128
756	L-LYSINE HCL	2	1	\N	129
757	LUDIPRESS	2	1	\N	130
758	LUYANG DILAW (ORGANIC TURMERIC)	2	1	\N	131
759	LITTLE MATE BASE MIX LAMB	2	1	\N	132
760	LITTLE MATE BASE MIX BEEF	2	1	\N	133
761	MAGNESIUM STEARATE	2	1	\N	134
762	MANDURAMYCIN AMMONIUM	2	1	\N	135
763	MAGNESIUM SULFATE HEPTAHYDRATE	2	1	\N	136
764	MALTODEXTRIN	2	1	\N	137
765	MANGOSTEEN FRUIT	2	1	\N	138
766	MANGANESE SULFATE	2	1	\N	139
767	METHYL PARABEN	2	1	\N	140
768	METHANDRIOL DIPROPIONATE	2	1	\N	141
769	METHANDRIOL PROPIONATE	2	1	\N	142
770	MADURAMYCIN	2	1	\N	143
771	MICROCRYSTALLINE CELLULOSE PH-101	2	1	\N	144
772	MILK FLAVOR POWDER	2	1	\N	145
773	MINERAL OIL	2	1	\N	146
774	MONOSODIUM PHOSPHATE ANHYDROUS	2	1	\N	147
775	MORINGGA LEAVES POWDER	2	1	\N	148
776	MUSCOVADO SUGAR	2	1	\N	149
777	MYGLYOL 840	2	1	\N	150
778	MALATHION/MADISON	2	1	\N	151
779	MALIC ACID	2	1	\N	152
780	MENTHOL CRYSTAL	2	1	\N	153
781	MEATMEAL	2	1	\N	154
782	NANDROLONE PHENYLPROPIONATE	2	1	\N	155
783	NATURAL DALANDAN FLAVOR POWDER	2	1	\N	156
784	NEOMYCIN SULFATE	2	1	\N	157
785	NIACINAMIDE  / NICOTINAMIDE	2	1	\N	158
786	N-METHYL-PYRROLIDONE (NMP)	2	1	\N	159
787	NICLOSAMIDE	2	1	\N	160
788	NICOTINI ACID/ NIACIN (MICROVIT B3 PROMIX)	2	1	\N	161
789	NORFLOXACIN HCL	2	1	\N	162
790	NORFLOXACIN NICOTINATE	2	1	\N	163
791	OTI DERM	2	1	\N	164
792	ORANGE FLAVOR	2	1	\N	165
793	OXYTETRACYCLINE HCL	2	1	\N	166
794	OPAORY PINK	2	1	\N	167
795	OPAORY GREEN	2	1	\N	168
796	OPAORY BLACK	2	1	\N	169
797	OPAORY BROWN	2	1	\N	170
798	OPAORY LIGHT GREEN	2	1	\N	171
799	OPAORY WHITE	2	1	\N	172
800	OREGANO POWDER	2	1	\N	173
801	OPTICAL BRIGHTENER (STILIBENE, 3,5-DIHYDROXY)	2	1	\N	174
802	PALM OIL	2	1	\N	175
803	PVP K 30	2	1	\N	176
804	PERMETHRIN	2	1	\N	177
805	PECTIN CITRUS	2	1	\N	178
806	PET COLOGNE	2	1	\N	179
807	PEPPERMINT OIL	2	1	\N	180
808	PEPPERMINT ESSENTIAL OIL	2	1	\N	181
809	PIPERAZINE CITRATE	2	1	\N	182
810	POTASSIUM CHLORIDE	2	1	\N	183
811	POTASSIUM IODIDE	2	1	\N	184
812	POTASSIUM HYDROXIDE PELLETS (TG)	2	1	\N	185
813	PRAZIQUANTEL	2	1	\N	186
814	PREDNISOLONE ACETATE	2	1	\N	187
815	PROPYL PARABEN	2	1	\N	188
816	PROPYLENE GLYCOL	2	1	\N	189
817	POWDER SCENT	2	1	\N	190
818	PYRIMETHAMINE HCL	2	1	\N	191
819	RACTOPAMINE HCL	2	1	\N	192
820	REFINED SUGAR	2	1	\N	193
821	REFAMOL CAPSULE	2	1	\N	194
822	STEVIA REBAUDIANA	2	1	\N	195
823	SAMBONG LEAVES POWDER	2	1	\N	196
824	SANDALWOOD ESSENTIAL OIL	2	1	\N	197
825	SANDALWOOD SCENT	2	1	\N	198
826	SHAMPOO PEARLIZER	2	1	\N	199
827	SODIUM LAURYL ETHYL SULFATE (SLES)	2	1	\N	200
828	SD PORK LIVER POWDER	2	1	\N	201
829	SODIUM ASCORBATE	2	1	\N	202
830	SODA ASH LIGHT	2	1	\N	203
831	SODIUM BENZOATE	2	1	\N	204
832	SODIUM BICARBONATE	2	1	\N	205
833	SODIUM CHLORIDE	2	1	\N	206
834	SODIUM SELENITE	2	1	\N	207
835	SODIUM SULFADIAZINE	2	1	\N	208
836	SODIUM SULFADIMIDINE	2	1	\N	209
837	SODIUM SULFAQUINOXALINE	2	1	\N	210
838	SOLUBLE PORK LIVER POWDER	2	1	\N	211
839	SODIUM SULFAGUANIDINE	2	1	\N	212
840	SORBITOL SOLUTION	2	1	\N	213
841	STRAWBERRY FLAVOR  POWDER	2	1	\N	214
842	SODIUM CARBOXYMETHYLCISTEINE (SODIUM CMC)	2	1	\N	215
843	SODIUM SACCHARIN	2	1	\N	216
844	SODIUM METABISULFITE	2	1	\N	217
845	SODIUM STARCH GLYCOLATE	2	1	\N	218
846	STREPTOMYCIN SULFATE	2	1	\N	219
847	STEARIC ACID	2	1	\N	220
848	SULFAMETHAZINE (SULFADIMIDINE)	2	1	\N	221
849	SUN DRIED PEPPER	2	1	\N	222
850	SWEET DESIRE	2	1	\N	223
851	SWEET IRON	2	1	\N	224
852	TALC POWDER STERILIZED	2	1	\N	225
853	TAB - BASE	2	1	\N	226
854	TAURINE	2	1	\N	227
855	TEGO BETAIN L7	2	1	\N	228
856	TN500 	2	1	\N	229
857	TETRASODIUM EDTA	2	1	\N	230
858	THYROPROTEIN	2	1	\N	231
859	TIAMULIN HYDROGEN FUMERATE	2	1	\N	232
860	THIAMPHENICOL	2	1	\N	233
861	TRIETHYL CITRATE	2	1	\N	234
862	TRISODIUM PHOSPHATE	2	1	\N	235
863	TRIMETHOPRIM	2	1	\N	236
864	TYLOSIN TARTRATE	2	1	\N	237
865	TWEEN 80 / POLYSORBATE 80	2	1	\N	238
866	TWEEN 20	2	1	\N	239
867	UNFLAVORED JELLY POWDER	2	1	\N	240
868	VERNEL SCENT	2	1	\N	241
869	VIRGIN COCONUT OIL	2	1	\N	242
870	VEEGUM (MAGNESIUM ALUMINUM SILICATE)	2	1	\N	243
871	VANILLA FLAVOR POWDER	2	1	\N	244
872	VITAMIN A PROPIONATE OIL	2	1	\N	245
873	VITAMIN A PALMITATE 1.7MIU/G OIL	2	1	\N	246
874	VITAMIN A PALMITATE POWDER	2	1	\N	247
875	VITAMIN A ACETATE  (PROSOL)	2	1	\N	248
876	 VITAMIN A DRY 1 MIU (SUPRA)	2	1	\N	249
877	VITAMIN B1 HCL	2	1	\N	250
878	VITAMIN B6 HCL (MICROVIT B6 PROMIX)	2	1	\N	251
879	VITAMIN B2 PHOSPHATE / RIBOFLAVIN 5	2	1	\N	252
880	VITAMIN B2 80%	2	1	\N	253
881	VITAMIN B12 1%  FG	2	1	\N	254
882	VITAMIN B12 PURE	2	1	\N	255
883	VITAMIN C - COATED ( ASCORBIC ACID COATED)	2	1	\N	256
884	VITAMIN D3 OIL 4 MIU	2	1	\N	257
885	VITAMIN D3 500,000 IU/G FEEDGRADE	2	1	\N	258
886	VITAMIN - E ACETATE OIL	2	1	\N	259
887	VITAMIN E POWDER	2	1	\N	260
888	VITAMIN K3	2	1	\N	261
889	WHEAT GERM OIL	2	1	\N	262
890	WOODRUFF GREEN	2	1	\N	263
891	XYLENE	2	1	\N	264
892	YELLOW LAKE # 5	2	1	\N	265
893	YACON SEEDLING	2	1	\N	266
894	ZINC  OXIDE 	2	1	\N	267
895	ZINC SULPHATE HEPTAHYDRATE	2	1	\N	268
896	ZINC SULPHATE MONOHYDRATE	2	1	\N	269
897	TRYPTIC SOY BROTH HI-MEDIA	2	1	\N	270
898	DIFFERENTIAL CLOSFRIDIUM MEDIUM 500g	2	1	\N	271
899	MYP AGAR	2	1	\N	272
900	STERILE  WATER	2	1	\N	273
901	TRYPTIC SOY AGAR	2	1	\N	274
902	DIALYZING MEMBRANE	2	1	\N	275
903	FORMALDEHYDE	2	1	\N	276
904	MCCONKEY AGAR	2	1	\N	277
905	BRAIN HEART INFUSION BROTH	2	1	\N	278
906	MUELLER HINTON AGAR	2	1	\N	279
907	ALUMINUM HYDROXIDE REHYDRAGEL	2	1	\N	280
908	SABORAUD DEXTROSE AGAR	2	1	\N	281
909	SODIUM CHLORIDE 0.9% NSS 1000ML	2	1	\N	282
910	TRIPPLE DISTILLED WATER	2	1	\N	283
911	TRIPLE SUGAR IRON	2	1	\N	284
912	SILKWEED 	2	1	\N	285
913	TRYPTIC SOY BROTH	2	1	\N	286
914	YEAST EXTRACT	2	1	\N	287
915	MRS AGAR	2	1	\N	288
916	MRS BROTH	2	1	\N	289
917	EMB AGAR (EOSIN METHYLENE BLUE, BBL)	2	1	\N	290
918	BABY DIAPER (MEDIUM)	2	1	\N	291
919	TODD-HEWITT BROTH	2	1	\N	292
920	NALIDIXIC ACID (5G/BOTTLE)	2	1	\N	293
921	STUART TRANSPORT MEDIUM	2	1	\N	294
922	RAPPAPORT VASSILIADIS SALMONELLA ENRICHMENT BROTH	2	1	\N	295
923	RASPBERRY FLAVOR	2	1	\N	296
924	FEBANTEL	2	1	\N	297
925	VITAMIN D3 POWDER	2	1	\N	298
926	BEEF SPRAVY	2	1	\N	1001
927	SODIUM CITRATE ANHYDROUS	2	1	\N	1002
928	FD & C RED # 3	2	1	\N	1003
929	HYOSCINE HBR	2	1	\N	1004
930	LAMB SPRAVY	2	1	\N	1005
931	LANETTO	2	1	\N	1006
932	MELON SWEET	2	1	\N	1007
933	MY STRAWBERRY SCENT	2	1	\N	1008
934	PYRANTEL PAMOATE	2	1	\N	1009
935	BETAINE HCL	2	1	\N	1010
936	MR. VP, BBL BROTH	2	1	\N	1011
937	XYLOSE LYSINE DEOXYCHOLATE	2	1	\N	1012
938	SELENIUM SULFIDE	2	1	\N	1013
939	SILVER DIOXIDE	2	1	\N	1014
940	CHOLINE CHLORIDE 70% LIQUID	2	1	\N	1015
941	MONOPOTASSIUM PHOSPHATE	2	1	\N	1016
942	MY STRAWBERRY LIQUID	2	1	\N	1017
943	VIBRA RED	2	1	\N	1018
944	MONTANIDE IMS 1313 MINERAL OIL	2	1	\N	1019
945	IVERMECTIN	2	1	\N	1020
946	EUCALYPTUS OIL EXTRA	2	1	\N	1021
947	KAOLIN LIGHT	2	1	\N	1022
948	POWERED LAGUNDI LEAVES	2	1	\N	1023
949	DRIED LAGUNDI LEAVES	2	1	\N	1024
950	KOLLIDON CL	2	1	\N	1025
951	SPECTROMYCIN HCL	2	1	\N	1026
952	CHROMIUM TRIPICOLINATE	2	1	\N	1027
953	CYANOCOBALAMIN FG	2	1	\N	1028
954	SODIUM FORMALDEHYDE	2	1	\N	1029
955	SULPHOXYLATE	2	1	\N	1030
956	GLYCEROL FORMAL	2	1	\N	1031
957	POLYETHYLENE GLYCOL	2	1	\N	1032
958	BETA CAROTENE	2	1	\N	1033
959	POLYVINYL PYRROLIDONE K12	2	1	\N	1034
960	MAGNESIUM OXIDE	2	1	\N	1035
961	PROCAINE PENICILLIN	2	1	\N	1036
962	CHLORTETRACYCLINE HCL	2	1	\N	1037
963	L-TRYTOPHAN (POWDER)	2	1	\N	1038
964	BETAINE (POWDER)	2	1	\N	1039
965	NENSILIN	2	1	\N	1040
966	F-MELT ODT (CONNELL)	2	1	\N	1041
967	ZINC BACITRACIN	2	1	\N	1042
968	CARBARYL	2	1	\N	1043
969	PEG 400	2	1	\N	1044
970	CHREMOPHOR RH 40	2	1	\N	1045
971	ALUMINUM CAP FOR VIALS 10/100ML PLAIN	3	1	\N	1
972	ALUM. CAP FOR VIALS 10/100ML WITH APT LOGO	3	1	\N	2
973	AMBER VIALS 100ML	3	1	\N	3
974	AMBER VIALS 25ML	3	1	\N	4
975	AMBER BOTTLE BOSTON ROUND 500ML	3	1	\N	5
976	AMBER BOTTLE BOSTON ROUND 75ML	3	1	\N	6
977	BLACK PLASTIC 1 KG	3	1	\N	7
978	BLACK PLASTIC 500GMS.	3	1	\N	8
979	BLACK PLASTIC TROVITE 1KG with PRINT	3	1	\N	9
980	CAP GLUTASEP	3	1	\N	10
981	CAP KABEZEN	3	1	\N	11
982	CAP LAKTAMINO/LAKSOVIT 1LITER	3	1	\N	12
983	CAP SCOURBUST/COLIMOXYN  150 ML	3	1	\N	13
984	ALUM. CAP TRISULLAK 400ML	3	1	\N	14
985	CAPSEAL 1 L	3	1	\N	15
986	CAPLINER 1 L	3	1	\N	16
987	C.BOX 12 X 150 ML	3	1	\N	17
988	C.BOX 12 X 100 ML VIALS	3	1	\N	18
989	C.BOX 12 x 4 L, GLUTASEP	3	1	\N	19
990	C.BOX 12 x 1 L, KABEZEN	3	1	\N	20
991	C.BOX LAKTAMINO/LAKSOVIT 1 LITER	3	1	\N	21
992	C.BOX TRISULLAK/TRISULDINE 400ML	3	1	\N	22
993	C.BOX TROVITE 3KG	3	1	\N	23
994	CLEAR VIALS 100ML	3	1	\N	24
995	COVER, PLASTIC 10KG	3	1	\N	25
996	SILVER FOIL SACHETLAKRODOX 100G	3	1	\N	26
997	GAMMA CAP WHITE  28 X 400	3	1	\N	27
998	GLUE (gallon)	3	1	\N	28
999	INDIVIDUAL BOX TRISULDINE-S 400ML 	3	1	\N	29
1000	INDIVIDUAL BOX VIBEFLEX 10ML	3	1	\N	30
1001	JAR VERMGUARD 2 KG with COVER	3	1	\N	31
1002	KRAFT BAG BROWN 20KG	3	1	\N	32
1003	KRAFT BAG WHITE 20KG	3	1	\N	33
1004	LABEL ALBENDAZOLE 5%	3	1	\N	34
1005	LABEL AMOXYLAK 20% WSP	3	1	\N	35
1006	LABEL AMOXYLAK 50% FG 1 KG	3	1	\N	36
1007	LABEL AMOXYLAK 10KG	3	1	\N	37
1008	LABEL ACI-DEHYDE 4L	3	1	\N	38
1009	LABEL ACI-VIT 1KG	3	1	\N	39
1010	LABEL ACI-VITAMINO 1L	3	1	\N	40
1011	LABEL BETALIN 100ML	3	1	\N	41
1012	LABEL COLIMIX WSP 1KG	3	1	\N	42
1013	LABEL COLIMOXYN 150ML	3	1	\N	43
1014	LABEL COLISTILAK 10% 10KG	3	1	\N	44
1015	LABEL DEXTRAVIT 100ML	3	1	\N	45
1016	LABEL FLOXANE 100ML	3	1	\N	46
1017	LABEL FLOXANE 10% SOL'N 1L	3	1	\N	47
1018	LABEL GENCOTIL 5% 100ML	3	1	\N	48
1019	LABEL GENMOX 100ML  	3	1	\N	49
1020	LABEL GLUTASEP 1L	3	1	\N	50
1021	LABEL GLUTASEP 4 LITERS 	3	1	\N	51
1022	LABEL GUT-AIDE P4 PREMIX 1KG	3	1	\N	52
1023	LABEL GUT-AID P4 WSP 1 KG	3	1	\N	53
1024	LABEL GUT-AIDE S6 PREMIX 1KG 	3	1	\N	54
1025	LABEL GUT-AIDE S6 WSP 1KG	3	1	\N	55
1026	LABEL LAKSADE 100ML 	3	1	\N	56
1027	LABEL LAKSOVIT 1LITER (FRONT)	3	1	\N	57
1028	LABEL LAKSOVIT 1 LITER (BACK)	3	1	\N	58
1029	LABEL LAKTAMINO 1 LITER(FRONT)	3	1	\N	59
1030	LABEL LAKTAMINO 1 LITER (BACK)	3	1	\N	60
1031	LABEL LAKTOCIDE 4 LITERS FRONT & BACK	3	1	\N	61
1032	LABEL LAKXINOR 1KG	3	1	\N	62
1033	LABEL LEVAXANTEL 1LITER	3	1	\N	63
1034	LABEL LC-VITAMIN PREMIX 20KG	3	1	\N	64
1035	LABEL MILKOVET 500 GMS.	3	1	\N	65
1036	LABEL MILKOVET 100 GMS.	3	1	\N	66
1037	LABEL MILKOVET 20 KL.	3	1	\N	67
1038	LABEL NORFLOXACIN WSP 1KG (LAKXINOR) 	3	1	\N	68
1039	LABEL SCOURBUST 150ML 	3	1	\N	69
1040	LABEL SYNTHAMIN 1L	3	1	\N	70
1041	LABEL THIATYLONE 100ML	3	1	\N	71
1042	LABEL TIAMULIN 500GMS.	3	1	\N	72
1043	LABEL TRISULDINE-S 400ML	3	1	\N	73
1044	LABEL TRIZINE PLUS 1KG	3	1	\N	74
1045	LABEL TROBAZEN (FRONT)	3	1	\N	75
1046	LABEL TROBAZEN (BACK)	3	1	\N	76
1047	LABEL TYLAK 100ML 	3	1	\N	77
1048	LABEL TYLAK 1 LITER 	3	1	\N	78
1049	LABEL VIROSEPT 5KG (FRONT)	3	1	\N	79
1050	LABEL VIROSEPT 5KG (BACK)	3	1	\N	80
1051	 LABEL APTIOFUR 100ML	3	1	\N	81
1052	 MANUAL LABEL SUICOX 150ML (NEW) 	3	1	\N	82
1053	PACKAGING TAPE WITH APTVET LOGO	3	1	\N	83
1054	PAIL, PLASTIC 10 KG	3	1	\N	84
1055	PAIL TROVITE 3 KG 	3	1	\N	85
1056	PARTITION LAKTAMINO 1 LITER	3	1	\N	86
1057	PARTITION KABEZEN  1 LITER	3	1	\N	87
1058	PARTITION VERMGUARD 2 KG	3	1	\N	88
1059	PARTITION FOR VIALS 100ML	3	1	\N	89
1060	PARTITION GLUTASEP 4 LITERS	3	1	\N	90
1061	PLASTIC BOTTLE SCOURLAK 150ML	3	1	\N	91
1062	PLASTIC BOTTLE GLUTASEP 4 LITERS	3	1	\N	92
1063	PLASTIC BOTTLE CAP KABEZEN 1 LITER	3	1	\N	93
1064	PLASTIC BOTTLE LAKTAMINO/LAKSOVIT 1L	3	1	\N	94
1065	PLUG GLUTASEP 4 LITERS	3	1	\N	95
1066	PLUG FOR JAR 100 GMS.	3	1	\N	96
1067	PLUNGER SCOURLAK 150ML 	3	1	\N	97
1068	ROLL LABEL VIBEFLEX 100ML - JICKSTAR	3	1	\N	98
1069	ROLL LABEL VIBEFLEX 10ML - EXPORT	3	1	\N	99
1070	RUBBER BAND	3	1	\N	100
1071	RUBBER STOPPER 10/100ML	3	1	\N	101
1072	SILVER FOIL SACHET 100G	3	1	\N	102
1073	STICKER BLACK W/ APT LOGO	3	1	\N	103
1074	TRANSPARENT PLASTIC 1KG	3	1	\N	104
1075	LABEL MADURAMYCIN 500 G	3	1	\N	105
1076	LABEL FLOXAVET INJECTION 100ML	3	1	\N	106
1077	C.BOX SEALINE LARGE	3	1	\N	107
1078	C.BOX SEALINE 60ML\n	3	1	\N	108
1079	AMBER VIALS 10 ML	3	1	\N	109
1080	LABEL STERILE WATER 100 ML	3	1	\N	110
1081	LABEL FLOXAVET 100 ML	3	1	\N	111
1082	LABEL GENCOTIL 10%	3	1	\N	112
1083	LABEL LINCOGEN 100 ML	3	1	\N	113
1084	LABEL TROVITE 3 KG	3	1	\N	114
1085	LABEL PYRILLAK WSP	3	1	\N	115
1086	LABEL TRISULDINE INJECTION 100 ML	3	1	\N	116
1087	C.BOX VERMGUARD	3	1	\N	117
1088	PLASTIC SPOON	3	1	\N	118
1089	LABEL APTAMOX PFI	3	1	\N	119
1090	LABEL DOXYVET 1L	3	1	\N	120
1091	LABEL CHROMIUM 500 G	3	1	\N	121
1092	LABEL COCCIVET 1L	3	1	\N	122
1093	LABEL LC MINERAL PREMIX 20 KG	3	1	\N	123
1094	LABEL VIBEPLEX FORTE 100 ML	3	1	\N	124
1095	LABEL AMPIDEXTIN 100 ML	3	1	\N	125
1096	LABEL OXYVET LA 100 ML	3	1	\N	126
1097	LABEL PNEUMOFEN 100 ML	3	1	\N	127
1098	LABEL THIOXONE 100 ML	3	1	\N	128
1099	TRANSPARENT PLASTIC 2O KG	3	1	\N	129
1100	C.BOX SEALINE MEDIUM	3	1	\N	130
1101	LABEL WELLVIT FORMULA 1L	3	1	\N	131
1102	LABEL POULVIT EGG BOOSTER 3KG	3	1	\N	132
1103	LABEL SUICOX 150ML	3	1	\N	133
1104	PVC FILM VERMGUARD 2KG	3	1	\N	134
1105	LABEL ACI ADEC WSP 1KG	3	1	\N	135
1106	LABEL ACI ADEC OS IL	3	1	\N	136
1107	LABEL APTISOL CP 1L	3	1	\N	137
1108	LABEL RACTOVET ME 500G	3	1	\N	138
1109	LABEL RACTOVET ME 20 KG	3	1	\N	139
1110	LABEL BRONCHO AIDE 20KG	3	1	\N	140
1111	LABEL BRONCHO AIDE 500G	3	1	\N	141
1112	WELBEST JAR WITH CAP 2KG	3	1	\N	142
1113	CANOLA OIL CONTAINER 17KG	3	1	\N	143
1114	ALUM. CAP PLAIN 15 ML	3	1	\N	144
1115	ALUM. CAP W/ APTVET LOGO 60ML (new lay-out)	3	1	\N	145
1116	ALUM. CAP W/ APTVET LOGO 120ML 	3	1	\N	146
1117	AMBER BOTTLE  B.R. 15ML	3	1	\N	147
1118	AMBER BOTTLE  B.R. 30ML	3	1	\N	148
1119	AMBER BOTTLE 60ML	3	1	\N	149
1120	AMBER BOTTLE 120ML	3	1	\N	150
1121	CAP BIOCALCIUM	3	1	\N	151
1122	CAP DOG SHAMPOO 250ML	3	1	\N	152
1123	CAP MESS OUT 2 LITERS	3	1	\N	153
1124	CAP MESS OUT 50ml Blue	3	1	\N	154
1125	CAP PETCARE 15ML	3	1	\N	155
1126	CAP PUSH DOWN 75ML	3	1	\N	156
1127	C. BOX, Syrup  60ML	3	1	\N	157
1128	C. BOX BIOCALCIUM 9 X 50 	3	1	\N	158
1129	C. BOX COATCARE 300ML	3	1	\N	159
1130	C. BOX, Dermadex 60 ml	3	1	\N	160
1131	C. BOX DOG SHAMPOO 250ML	3	1	\N	161
1132	C. BOX MULTI SYRUP 120ML	3	1	\N	162
1133	IND. BOX COATSHINE 60ML	3	1	\N	163
1134	IND. BOX COATSHINE 120ML	3	1	\N	164
1135	IND. BOX COLIMOXYN 60ML	3	1	\N	165
1136	IND. BOX COLIMOXYN 60ML - (EXPORT)	3	1	\N	166
1137	IND. BOX DERMADEX 60ML	3	1	\N	167
1138	IND. BOX GENTIN 7ML	3	1	\N	168
1139	IND. BOX LC- SCOUR 60ML	3	1	\N	169
1140	IND. BOX LC- VIT 60ML	3	1	\N	170
1141	IND. BOX LC-SELEN 250ML	3	1	\N	171
1142	IND. BOX LC-VIT  120ML	3	1	\N	172
1143	IND. BOX LC-VIT 15 ML - EXPORT	3	1	\N	173
1144	IND. BOX LC-VIT  120ML (export)	3	1	\N	174
1145	IND. BOX NEMATOCIDE 15ML	3	1	\N	175
1146	IND. BOX NEMATOCIDE 60ML	3	1	\N	176
1147	IND. BOX NUTRICAL 60ml	3	1	\N	177
1148	IND. BOX NUTRICAL 120ml	3	1	\N	178
1149	INDUCTION LINER	3	1	\N	179
1150	INSERT DERMADEX 60ML 	3	1	\N	180
1151	LAMI PET/FOIL WITH ZIPLOCK (500GMS)	3	1	\N	181
1152	LABEL APT FORMULA ONE NUTRAMIX 1kg	3	1	\N	182
1153	LABEL APT FORMULA ONE NUTRAMIX 500g	3	1	\N	183
1154	LABEL COATSHINE 120ML	3	1	\N	184
1155	LABEL COATSHINE 60ML	3	1	\N	185
1156	LABEL COLIMOXYN 60ML (LOCAL)	3	1	\N	186
1157	LABEL COLIMOXYN 60ML (export)	3	1	\N	187
1158	LABEL DELTACAL TABLET	3	1	\N	188
1159	LABEL GROOM AID-30ML (SAMPLE SIZE)	3	1	\N	189
1160	LABEL GROOM AIDE (FRESH BLOSSOM)	3	1	\N	190
1161	LABEL GROOM AIDE (FRUITY BURST)	3	1	\N	191
1162	LABEL GROOM AIDE (SPRING FRESH)	3	1	\N	192
1163	LABEL GROOM AIDE (SWEET DESIRE)	3	1	\N	193
1164	LABEL GROOM AIDE (TUTTI FRUITY)	3	1	\N	194
1165	LABEL LC-SCOUR 60ML 	3	1	\N	195
1166	LABEL LC-VIT 15ML	3	1	\N	196
1167	LABEL LC-VIT 15ML - EXPORT	3	1	\N	197
1168	LABEL LC-VIT 60ML	3	1	\N	198
1169	LABEL LC-VIT 120ML	3	1	\N	199
1170	LABEL LC-VIT 120ML (export)	3	1	\N	200
1171	LABEL LC-VIT OB	3	1	\N	201
1172	LABEL LITTLE MATE 500gm	3	1	\N	202
1173	LABEL LITTLE MATE 1 KG	3	1	\N	203
1174	LABEL METHIOVET 	3	1	\N	204
1175	LABEL NEMATOCIDE 15ML	3	1	\N	205
1176	LABEL NEMATOCIDE 60ML	3	1	\N	206
1177	LABEL NUTRICAL 15ML	3	1	\N	207
1178	LABEL NUTRICAL 60ML	3	1	\N	208
1179	LABEL NUTRICAL 120ML	3	1	\N	209
1180	LABEL OTI-DERM 15ml	3	1	\N	210
1181	LABEL PROXANTEL TABLET 	3	1	\N	211
1182	LABEL REFAMOL CAPSULE	3	1	\N	212
1183	LABEL TRUE BLUE - BEEF	3	1	\N	213
1184	LABEL TRUE BLUE - CHICKEN	3	1	\N	214
1185	LABEL TRUE BLUE - FISH & RICE	3	1	\N	215
1186	LABEL TRUE BLUE - LAMB	3	1	\N	216
1187	LABEL TRUE BLUE - MEAT & VEGETABLES	3	1	\N	217
1188	MEDICINE DROPPER 15ML	3	1	\N	218
1189	MOISTURE ABSORBENT	3	1	\N	219
1190	PAPER LEAN	3	1	\N	220
1191	PARTITION BIOCALCIUM 9 X 50	3	1	\N	221
1192	PLASTIC BOTTLE BIOCALCIUM	3	1	\N	222
1193	PLASTIC BOTTLE MESS OUT 50ML	3	1	\N	223
1194	PLASTIC BOTTLE MESS OUT 2LITER	3	1	\N	224
1195	PLASTIC BOTTLE PET COLOGNE 150ml	3	1	\N	225
1196	PLASTIC BOTTLE PETCARE 15ML	3	1	\N	226
1197	PLASTIC BOTTLE DOG SHAMPOO 250ml	3	1	\N	227
1198	PLUG PETCARE 15ML	3	1	\N	228
1199	PUMP GROOM-AIDE 380ml	3	1	\N	229
1200	PVC RIGID FILM	3	1	\N	230
1201	SPRAYER PET COLOGNE	3	1	\N	231
1202	STICKER PROXANTEL 500MG TABS.	3	1	\N	232
1203	STICKER W/ APT LOGO (BLUE)	3	1	\N	233
1204	STICKER NEMATOCIDE	3	1	\N	234
1205	VIANNO BOTTLE	3	1	\N	235
1206	LABEL APT FORMULA 45 GRAMS	3	1	\N	236
1207	LABEL INNER AID	3	1	\N	237
1208	IND. BOX OTIDERM 15ML	3	1	\N	238
1209	LABEL DERMADEX 60ML	3	1	\N	239
1210	WELBEST JAR 1 KG WITH CAP	3	1	\N	240
1211	SILVER FOIL 500 G	3	1	\N	241
1212	FOIL, SILVER 1 KG	3	1	\N	242
1213	LABEL LITTLE MATE 45 G	3	1	\N	243
1214	STICKER, LITTLE MATE/APT FORMULA DOSAGE 500 G	3	1	\N	244
1215	LABEL GENTIN 7 ML	3	1	\N	245
1216	LABEL LACTOCARE 100 G	3	1	\N	246
1217	LABEL PETZYME	3	1	\N	247
1218	CREAM JAR WITH COVER 100 G	3	1	\N	248
1219	LABEL PET COLOGNE FRESH BLOSSOM 125 ML	3	1	\N	249
1220	LABEL PETZYME 20 G	3	1	\N	250
1221	LABEL LACTOCARE 20 G	3	1	\N	251
1222	LABEL LC DOX TABLET	3	1	\N	252
1223	LABEL LITTLE MATE 100 G	3	1	\N	253
1224	LABEL LC SELEN 250ML	3	1	\N	254
1225	PVC SHRINKABLE FILM 100G 	3	1	\N	255
1226	IND. BOX HEMACARE FE 60ML	3	1	\N	256
1227	IND. BOX HEMACARE FE 120ML	3	1	\N	257
1228	LABEL HEMACARE FE 60ML	3	1	\N	258
1229	LABEL HEMACARE FE 120ML	3	1	\N	259
1230	LABEL DERM AID SHAMPOO 250ML	3	1	\N	260
1231	IND. BOX DERM AID SHAMPOO 250ML	3	1	\N	261
1232	SHINDY BOTTLE WITH DOME CAP 30ML	3	1	\N	262
1233	TRIGGER SPRAYER	3	1	\N	263
1234	LABEL APT FORMULA NUTRAMIX 100G	3	1	\N	264
1235	STICKER LABEL FREE LITTLE MATE BASEMIX	3	1	\N	265
1236	PACKAGING TAPE NUTRA TECH LOGO	3	1	\N	266
1237	HOUSEBOX CYANOPRO 20G X 12 SCHT	3	1	\N	267
1238	HOUSEBOX PROLYTE 20G X 12 SCHT	3	1	\N	268
1239	IND. BOX BIO - IRON 60ML	3	1	\N	269
1240	IND. BOX BIO - IRON 120ML	3	1	\N	270
1241	IND. BOX BIOCAL 60ML	3	1	\N	271
1242	IND. BOX BIOCAL 120ML	3	1	\N	272
1243	IND. BOX GROWVITE 60ML	3	1	\N	273
1244	IND. BOX GROWVITE 120ML	3	1	\N	274
1245	LABEL  BIOCAL 15 ML	3	1	\N	275
1246	LABEL BIOCAL 60ML	3	1	\N	276
1247	LABEL BIOCAL 120ML	3	1	\N	277
1248	LABEL BIO-IRON 15ML	3	1	\N	278
1249	LABEL BIO-IRON 60ML	3	1	\N	279
1250	LABEL BIO-IRON 120ML	3	1	\N	280
1251	LABEL CYANOPRO 200GMS. (215MM X 95MM) JAR	3	1	\N	281
1252	LABEL CYANOPRO 200GMS. (90MM X 100MM) SACHET	3	1	\N	282
1253	LABEL CYANOPRO 1KG / 300MM X 154MM	3	1	\N	283
1254	LABEL CYANOPRO 20GMS. SACHET	3	1	\N	284
1255	LABEL COATCARE 300ML - LAVANDER(front)	3	1	\N	285
1256	LABEL COATCARE 300ML - LAVANDER(back)	3	1	\N	286
1257	LABEL COATCARE 300ML - MELON (front)	3	1	\N	287
1258	LABEL COATCARE 300ML - MELON (back)	3	1	\N	288
1259	LABEL COATCARE 300ML - TALC (front)	3	1	\N	289
1260	LABEL COATCARE 300ML - TALC (back)	3	1	\N	290
1261	LABEL COATCARE 300ML - SBS(front)	3	1	\N	291
1262	LABEL COATCARE 300ML - SBS(back)	3	1	\N	292
1263	LABEL GROWVITE 120 ML	3	1	\N	293
1264	LABEL GROWVITE 60 ML	3	1	\N	294
1265	LABEL GROWVITE 15ML	3	1	\N	295
1266	LAMI PET/FOIL WITH ZIPLOCK (200GMS)	3	1	\N	296
1267	WELBEST JAR WHITE  2 KG	3	1	\N	297
1268	LABEL, PROLYTE 1 KG	3	1	\N	298
1269	LABEL, PROLYTE 20 G	3	1	\N	299
1270	LABEL, PROLYTE 200 G	3	1	\N	300
1271	LABEL, COATCARE APPLE FRONT	3	1	\N	301
1272	LABEL, COATCARE APPLE BACK\n	3	1	\N	302
1273	LABEL, COATCARE PEPPERMINT FRONT\n	3	1	\N	303
1274	LABEL, COATCARE PEPPERMINT BACK\n	3	1	\N	304
1275	PVC BOTTLE COATCARE 300 ML	3	1	\N	305
1276	BOTTLE, W/CAP & PLUG (DEXTROSE 320 GM)	3	1	\N	306
1277	PVC FILM (DEXTROSE POWDER 320G)	3	1	\N	307
1278	C. BOX VERMGUARD 2KG	3	1	\N	308
1279	SHRINK WRAP (L-35CM, W-22CM)	3	1	\N	309
1280	LABEL MANOXAL 60ML	3	1	\N	310
1281	IND. BOX MANOXAL 400ML	3	1	\N	311
1282	NUTRAMED BAG	3	1	\N	312
1283	LABEL MANOXAL 400ML	3	1	\N	313
1284	100ML BR PET CLEAR BOTTLE WITH CAP	3	1	\N	314
1285	LABEL COATSHIELD 100ML	3	1	\N	315
1286	C. BOX PROLYTE CYANOPRO 1KG	3	1	\N	316
1287	ALUMINUM FOIL FOR STRIP PACKAGING OF TABLET	3	1	\N	317
1288	ALUMINUM FOIL WITH CELLOPHANE FOR STRIP PACKAGING OF TABLET	3	1	\N	318
1289	PVC FILM BLISTER FOR PACKAGING OF TABLET	3	1	\N	319
1290	ALUMINUM CAP W/OUT LOGO 120ML	3	1	\N	320
1291	ALUMINUM CAP W/OUT LOGO FOR 60ML	3	1	\N	321
1292	BLUE, SEMITRANSPARENT PLASTIC PUMP BOTTLE (100ML)	3	1	\N	322
1293	BLUE, SEMITRANSPARENT PLASTIC PUMP BOTTLE (120ML)	3	1	\N	323
1294	HDPE BOTTLE (50ML)	3	1	\N	324
1295	HDPE BOTTLE (30ML)	3	1	\N	325
1296	SILICA GEL 1GRAM	3	1	\N	326
1297	75ML WM HDPE BOTTLE (H-400MM; D-212MM)	3	1	\N	327
1298	PAPER LEAN 4X4, (CUT SIZE)	3	1	\N	328
1299	STRIP FOIL SIZE 150MM	3	1	\N	329
1300	PE BAG 20 X 40	3	1	\N	330
1301	PET BOTTLES	3	1	\N	331
1302	ICE CHEST MEDIUM (BOXH SW103)	3	1	\N	332
1303	ICE CHEST X-MED(BOX2)	3	1	\N	333
1304	ICE CHEST LARGE (BOX 302)	3	1	\N	334
1305	CENTRIFUDGE BOTTLE 500 ML	3	1	\N	335
1306	TROP-BIO VAC PMB BOX, 100ML	3	1	\N	336
1307	TROP-BIO VAC PMB LABEL, 100ML	3	1	\N	337
1308	PLASTIC CLEAR TRANSPARENT (20X30)	3	1	\N	338
1309	PLASTIC CLEAR TRANSPARENT (16X24)	3	1	\N	339
1310	NCD BOTTLE WITH CAP AND PLUG	3	1	\N	340
1311	PLASTIC CLEAR TRANSPARENT 25 X 50	3	1	\N	341
1312	TROP BIO VAC-HS (BOX)	3	1	\N	342
1313	LABEL, OLVAC B+6+R	3	1	\N	343
1314	LABEL, OLVAC A+B+6+R	3	1	\N	344
1315	TROP BIO VAC-HS (LABEL)	3	1	\N	345
1316	LABEL BRONCHOTECH 1L	3	1	\N	3000
1317	LABEL XGUARD 1L	3	1	\N	3001
1318	LABEL VIBEFLEX SP 1KG	3	1	\N	3002
1319	LABEL SPRAYVY 180ML	3	1	\N	3003
1320	INDIVIDUAL BOX SPRAYVY 180ML	3	1	\N	3004
1321	LABEL OLVAC A+B	3	1	\N	3005
1322	LABEL VIBEFLEX 100ML - EXPORT	3	1	\N	3006
1323	LABEL ACI VITE 100G	3	1	\N	3007
1324	LABEL PIGRAUCILLIN 20% 100G	3	1	\N	3008
1325	LABEL ACI-TMPS 100G	3	1	\N	3009
1326	LABEL PIGROW ADE 100ML	3	1	\N	3010
1327	LABEL PIGROW DEX PLUS 100ML	3	1	\N	3011
1328	LABEL PIGROW FLEX 100ML	3	1	\N	3012
1329	LABEL PIGROW MEC 1% 100ML	3	1	\N	3013
1330	LABEL PIGROWTYL 20% 100ML	3	1	\N	3014
1331	LABEL PIGROW CILLIN 100ML	3	1	\N	3015
1332	LABEL ACI-SCOUR 100ML	3	1	\N	3016
1333	LABEL ACI DOXYTIN 100G	3	1	\N	3017
1334	CREAM JAR WITH COVER 100 G	3	1	\N	3018
1335	LABEL LK TONIC	3	1	\N	3019
1336	GALENICAL BOTTLES FOR ACI-SCOUR	3	1	\N	3020
1337	60ML PLUG TYPE BOTTLE	3	1	\N	3021
1338	120ML PLUG TYPE CAPS	3	1	\N	3022
1339	60ML PLUG TYPE CAPS	3	1	\N	3023
1340	120ML GALENICAL PLASTIC BOTTLE	3	1	\N	3024
1341	120ML PLUG TYPE BOTTLE	3	1	\N	3025
1342	WHITE KRAFT BAG 20KG	3	1	\N	3026
1343	CLEAR VIALS 50ML	3	1	\N	3027
1344	PARTITION, COATSHIELD 100ML	3	1	\N	3028
1345	INDIVIDUAL BOX LC-VIT OB SYRUP 120ML	3	1	\N	3029
1346	PLUG FOR CREAM JAR 100G (LEDENYL PLUG 212)	3	1	\N	3030
1347	LABEL LC-VIT OB SYRUP 60 ML	3	1	\N	3031
1348	LABEL LC-VIT OB SYRUP 120 ML	3	1	\N	3032
1349	INDIVIDUAL BOX LC-VIT OB SYRUP 60ML	3	1	\N	3033
1350	C. BOX PROLYTE/CYANOPRO 200G	3	1	\N	3034
1351	PLASTIC BOTTLE SCOURLAK 150ML WITHOUT CAP	3	1	\N	3035
1352	INDIVIDUAL BOX VIBEFLEX 100ML	3	1	\N	3036
1353	C. BOX GROOM AID SHAMPOO	3	1	\N	3037
1354	C. BOX DERMADEX 75ML	3	1	\N	3038
1355	CAPSEAL FOR 300ML BOTTLE	3	1	\N	3039
\.


SET search_path = mbr, pg_catalog;

--
-- Data for Name: batch_item_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY batch_item_requirement (id, batch_id, item_id, udf_qty, udf_qty_unit_id, required_qty, required_qty_unit_id, date_allocated, date_dispensed, item_req_status, part) FROM stdin;
18	173	628	111	6	1.665	7	\N	\N	ALLOCATED	1
19	173	628	50	5	750	5	\N	\N	ALLOCATED	2
20	173	971	1	9	15	9	\N	\N	ALLOCATED	0
16	172	628	512	5	512	7	\N	\N	\N	0
17	172	971	1	9	1	9	\N	\N	\N	0
13	171	628	111	6	111	6	\N	\N	ALLOCATED	1
14	171	628	50	5	50	5	\N	\N	ALLOCATED	2
15	171	971	1	9	1	9	\N	\N	ALLOCATED	0
\.


--
-- Name: batch_item_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('batch_item_requirement_id_seq', 17, true);


--
-- Data for Name: manufacturing_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY manufacturing_procedure (id) FROM stdin;
70
71
\.


--
-- Data for Name: bottling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY bottling_procedure (id, manufacturing_procedure_id, content, step_number) FROM stdin;
\.


--
-- Name: bottling_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('bottling_procedure_id_seq', 5, true);


--
-- Data for Name: coding_spec; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY coding_spec (step_no, instruction, requires_equipment, done_by, checked_by, area, id) FROM stdin;
1	Check the availability and cleanliness of the manufacturing equipment.	t	\N	\N	3	1
\.


--
-- Name: coding_spec_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('coding_spec_id_seq', 1, true);


--
-- Data for Name: compounding_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY compounding_procedure (id, step_number, instruction, time_monitored, done_by, checked_by, manufacturing_procedure_id, requires_equipment, remarks, requires_rmreq) FROM stdin;
38	1	mix all	t	\N	\N	70	\N	\N	\N
39	1	mix all	t	\N	\N	71	t	AMBOT	t
\.


--
-- Name: compounding_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('compounding_procedure_id_seq', 39, true);


--
-- Data for Name: udf; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY udf (id, content, unit_id) FROM stdin;
70	1	5
71	1	7
\.


--
-- Data for Name: raw_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY raw_material_requirement (id, quantity, unit_id, udf_id, part, item_id) FROM stdin;
86	512	5	70	0	628
87	111	6	71	1	628
88	50	5	71	2	628
\.


--
-- Data for Name: dosage; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY dosage (id, raw_material_requirement_id, percent_multiplier, compounding_procedure_id) FROM stdin;
38	86	1	38
39	87	1	39
\.


--
-- Name: dosage_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('dosage_id_seq', 39, true);


--
-- Data for Name: equipment_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY equipment_requirement (id, manufacturing_procedure_id, equipment_id, procedure) FROM stdin;
30	70	1	COMPOUNDING
31	71	2	COMPOUNDING
32	71	5	POWDER_FILLING
33	71	1	CODING_SPECS
\.


--
-- Name: equipment_requirement_coding_equipment_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_equipment_id_seq', 1, false);


--
-- Name: equipment_requirement_coding_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_id_seq', 33, true);


--
-- Name: equipment_requirement_coding_manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('equipment_requirement_coding_manufacturing_procedure_id_seq', 1, false);


--
-- Data for Name: main_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY main_procedure (id, heading, heading_title, area) FROM stdin;
1	A	Documentation/Regulatory	3
2	B	Dispensing	3
3	C	Compounding	3
4	D	Filling	3
5	E	Packaging	3
6	F	Finished Goods	3
7	G	Finished Product (Approval and Release)	3
8	H	Others	3
9		If applicable	3
\.


--
-- Name: main_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('main_procedure_id_seq', 9, true);


--
-- Name: manufacturing_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('manufacturing_procedure_id_seq', 4, true);


--
-- Name: mbr_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('mbr_id_seq', 172, true);


--
-- Data for Name: packaging_material_requirement; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY packaging_material_requirement (id, quantity, unit_id, udf_id, item_id) FROM stdin;
55	1	9	70	971
56	1	9	71	971
\.


--
-- Name: packaging_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_material_requirement_id_seq', 56, true);


--
-- Data for Name: packaging_operation; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY packaging_operation (id, step_number, instruction, manufacturing_procedure_id, part, done_by, checked_by) FROM stdin;
40	1	contne 	70	1		
41	1	packaging opt	71	1		
42	10	Count yield and accomplish finished product reconciliation\nTheoretical yield (TY):\nActual Yield (AY):\nQC/In-process samples:\nTotal samples:\n(equivalent weight in kg):\nRetention sample (RS):\nTotal Yield *:\n% Yield **:\n* Total Yield = AY + QC + RS\n** % Yield = Total Yield / TY	71	2	\N	\N
\.


--
-- Name: packaging_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_id_seq', 7, true);


--
-- Name: packaging_procedure_operation_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('packaging_procedure_operation_id_seq', 42, true);


--
-- Data for Name: powder_filling_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY powder_filling_procedure (id, step_number, instruction, manufacturing_procedure_id, requires_equipment, done_by, checked_by) FROM stdin;
6	1	procedure filling	71	t		
7	2	Transfer the compounded product to the filling section.	71	f	\N	\N
\.


--
-- Data for Name: primary_secondary_packaging; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY primary_secondary_packaging (id, primary_packaging_id, secondary_packaging_id) FROM stdin;
70	55	55
71	56	56
\.


--
-- Name: primary_secondary_packaging_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('primary_secondary_packaging_id_seq', 1, true);


--
-- Name: raw_material_requirement_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('raw_material_requirement_id_seq', 88, true);


--
-- Data for Name: sub_procedure; Type: TABLE DATA; Schema: mbr; Owner: postgres
--

COPY sub_procedure (id, main_procedure, sub_procedure, step_no) FROM stdin;
1	1	Batch No., Shelf-life, Pack Size and VR No., of the product is correct.	1
2	2	Line clearance accomplished and verified prior to weighing.	1
3	2	Raw materials were correct, individually weighed and weights verified by pharmacist.	2
4	3	Line clearance checked by Production-In-Charge and verified by QA.	1
5	3	Equipment clean tags attached to the MBR.	2
6	3	The dispensing tags attached to the weighed raw materials are filed with the batch documents.	3
\.


--
-- Name: sub_procedure_id_seq; Type: SEQUENCE SET; Schema: mbr; Owner: postgres
--

SELECT pg_catalog.setval('sub_procedure_id_seq', 31, true);


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

COPY "group" (id, group_name) FROM stdin;
2	USER
1	ADMIN
\.


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

COPY method (id, name, description) FROM stdin;
53	view_batch_record	View batch record
54	create_new_batch	Create new batch
55	view_product_list	View product list
56	create_new_product	Create new product
57	access_batch_projection	Access batch projection
58	print_batch_projection\n	Print projection (bom)
60	check_material_availability\n	Check the batch material requirements availability\n
59	mmd_view_batch_record	Allow user to access the mmd batch management which only includes viewing all batch record\n
61	cancel_reservation	Cancel material reservation. This will remove allocation of materials for the specified batch.
62	dispense_batch_materials	Dispense all material requirements for the selected batch
63	print_product_formulation	Print the product formulation of the selected batch
64	reserve_material_req	Allocate available materials (reserve) for the selected batch
\.


--
-- Name: method_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('method_id_seq', 64, true);


--
-- Data for Name: sub_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY sub_method (id, uri, description) FROM stdin;
1	/mbr/mbr/g_batch_list	View batch list
2	/main/area/g_area_list	View area list
3	/mbr/bottling_procedure/pst_new_bottling_proc	Create bottling procedure
4	/main/classification/g_classification_list	View classification list
7	/mbr/compounding_procedure/pst_new_compounding_proc	Create compounding procedure
8	/mbr/compounding_procedure/find_by_mfg_id	Search compounding procedure by mfg_id
9	/main/container/g_container_list	View container list
10	/mbr/dosage/pst_new_dosage	Create dosage
11	/mbr/dosage/find_by_cp_id	Search compounding procedure by id
12	/main/equipment/g_equipment_list	View equipment list
13	/mbr/equipment_requirement/g_find_by_mfg_id_and_procedure	Search equipment requirement by mfg_id and procedure
14	/mbr/equipment_requirement/pst_create_new_equip_req	Create equipment requirement
15	/sqlsvr_copy/itemc/g_itemc_list	View all item in sqlsvr_copy
16	/sqlsvr_copy/item_category_c/g_item_category_list	View all item category list
17	/sqlsvr_copy/item_type_c/g_item_type_list	View all item type
18	/mbr/manufacturing_procedure/pst_new_mfg_proc	Create mfg procedure
19	/mbr/mbr/g_batch_by_stat	Search batch by status
20	/mbr/mbr/g_batch_by_batch_no	Search batch by batch no
21	/mbr/mbr/g_batch_by_product_code	Search batch by product code
22	/mbr/mbr/g_batch_by_area	Search batch by area
23	/mbr/mbr/pst_new_batch	Create new batch
24	/mbr/mbr/pst_reserve_mbr	Update batch status to 'RESERVED'
25	/mbr/mbr/pst_cancel_reservation	Update batch status to 'PENDING'
26	/mbr/mbr/pst_print_batch	Update batch statys to 'PRINTED'/'RELEASED'
27	/mbr/mbr/pst_dispense_batch_material	Update batch status to 'DISPENSED'
28	/main/pack_size/pst_new_pack_size	Create new pack size
30	/mbr/packaging_material_requirement/pst_packg_material_req	Create packg material requirement
31	/mbr/packaging_material_requirement/g_packg_material_req_by_udf_id	Search packg materials by udf_id
32	/mbr/packaging_material_requirement/g_packg_material_req_by_details	Search packg material by qty, unit, and udf_id
33	/mbr/packaging_operation/pst_create_new_packg_operation	Create packg operation
34	/mbr/powder_filling/pst_new_powder_filling	Create powder filling procedure
35	/main/product/g_product_list	View product list
36	/main/product/g_is_code_valid	Checks if entered product code is valid
37	/main/product/g_primary_packg	Search product's primary packg material
38	/main/product/g_secondary_packg	Search product's secondary packg material
39	/main/product/g_product_by_id	Search product by id
40	/main/product/pst_new_product	Create product
41	/main/product/pst_prim_sec_packg	Set product's primary and secondary packg material
43	/mbr/raw_material_requirement/pst_new_raw_material_req	Create raw material requirement
44	/mbr/raw_material_requirement/g_raw_material_req_by_udf_id	Search raw material by udf_id
45	/mbr/raw_material_requirement/g_raw_material_req_by_details	Search raw material by details
46	/sqlsvr_copy/stock_card_c/g_stock_card_list	View stockcardc list
47	/sqlsvr_copy/stock_card_c/g_stock_card_by_item_cd	Search stockcard by item code
48	/sqlsvr_copy/stock_card_c/g_stock_card_by_id	Search stockcard by id
50	/sqlsvr_copy/stock_card_c/pst_change_stock_card_status_to_depleted	Update stockcard status to depleted
51	/sqlsvr_copy/stock_card_c/g_stockcard_by_control_no	Search by stockcard control_no
52	/transaction/stock_card_txn/g_reserved_approved_by_item_cd	Search reserved and approved stockcard by item code
53	/transaction/stock_card_txn/g_reserved_approved_by_item_cd_company_cd	Search reserved and approved stockcard by item code and company code
54	/transaction/stock_card_txn/pst_new_stock_card_txn	Create stockcard transaction (issuance)
55	/mbr/udf/g_udf_by_id	Search udf by id
56	/mbr/udf/pst_new_udf	Create new udf
57	/main/unit/g_unit_list	View unit list
58	/security/user/g_user_list	View all users
29	/sqlsvr_copy/item/g_pm_item_list	View all packg material
42	/sqlsvr_copy/item/g_rm_item_list	View raw material list
49	/sqlsvr_copy/stock_card_c/g_stock_card_by_company_cd_and_item_id	Search stockcard by company code and item id
5	/sqlsvr_copy/company/g_company_list	View all company list
59	/audit/audit/g_audit_list	Get audit list
60	/transaction/stock_card_txn/pst_delete_stock_card_txn	Delete stockcardtxn in database
61	/mbr/mbr/g_batch_stock_card_txn_list	Get batch stockcard transaction
62	/mbr/mbr/g_batch_by_id	Get batch by id
63	/transaction/stock_card_txn/g_stockcardtxn_by_batch_no	Get all stockcard transaction with the same batch no
64	/transaction/stock_card_txn/g_stockcardtxn_by_batch_no_and_item_category	Get all stockcard transaction with the same batch no and item category
65	/transaction/stock_card_txn/g_stockcard	Returns the instance of the stockCardTxn's stockCard
66	mbr/batch_item_requirement/pst_new_batch_item_req	Save a new batch item requirement
67	sqlsvr_copy/stock_card_c/g_available_stockcard	Get available stockcard by companyId, itemId
68	mbr/batch_item_requirement/pst_update_batch_item_req_status	Update batch item requirement status
69	sqlsvr_copy/stock_card_c/pst_update_stock_card_stock_status	Update stockcard stock status (AVAILABLE, DEPLETED)
\.


--
-- Data for Name: method_sub_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY method_sub_method (id, method_id, sub_method_id) FROM stdin;
2	53	1
3	53	20
4	53	21
5	53	19
6	53	22
7	54	35
8	54	57
9	54	23
10	54	1
11	55	35
12	55	39
13	55	44
14	55	31
15	55	13
16	56	4
18	56	2
19	56	57
20	56	9
21	56	42
22	56	12
23	56	29
24	56	36
25	56	28
26	56	40
27	56	56
28	56	18
29	56	39
30	56	43
31	56	30
32	56	7
33	56	45
34	56	10
35	56	14
36	56	33
37	56	32
38	56	3
39	56	41
40	56	35
41	56	34
42	57	35
43	57	57
44	59	1
45	60	49
46	60	57
47	61	25
48	61	1
49	62	27
50	62	1
51	64	54
52	64	24
53	64	1
54	56	5
55	61	60
56	61	61
57	54	62
\.


--
-- Name: method_sub_method_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('method_sub_method_id_seq', 57, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY role (id, role_name) FROM stdin;
1	MMD_ROLE
2	RND_ROLE
3	unauthorized_user
4	tester
\.


--
-- Data for Name: role_method; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY role_method (id, role_id, method_id) FROM stdin;
13	3	53
14	3	55
15	3	57
16	3	59
\.


--
-- Name: submethod_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('submethod_id_seq', 68, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY "user" (id, email_ad, first_name, last_name, "position", password) FROM stdin;
2	mainevillarias@gmail.com	Marie Charmaine	Villarias	programmer	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    
3	starlightlynx@gmail.com	HAWKE	HAWKE	CHAMPION	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    
4	rnduser@gmail.com	Marie	Woodland	MANAGER	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    
5	tester	charmaine	vill	programmer	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    
6	tester2	na	na	na	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8                                    
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY user_group (id, user_id, group_id) FROM stdin;
2	2	1
3	3	2
8	4	2
9	5	2
10	6	2
\.


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

COPY user_role (id, user_id, role_id) FROM stdin;
3	3	1
4	4	2
5	5	3
6	6	4
\.


SET search_path = sqlsvr_copy, pg_catalog;

--
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('company_id_seq', 7, true);


--
-- Name: item_category_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_category_id_seq', 4, true);


--
-- Name: item_class_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_class_id_seq', 2, true);


--
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_id_seq', 1355, true);


--
-- Name: item_type_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('item_type_id_seq', 2, true);


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY warehouse (id, code, descs) FROM stdin;
1	00006	Finished Goods - DVO - WLO
2	00012	Packaging Materials- Balubad
3	00007	Packaging Materials - Langkiwa
4	00010	Promotionals - Bicutan
5	00011	Raw Materials - Balubad
6	00009	Raw Materials - Langkiwa
7	00003	Finished Goods - Balubad Warehouse
8	00005	Finished Goods - CEB - WLO
9	00001	Finished Goods - Langkiwa Warehouse
10	00008	Finished Goods - Tarlac Warehouse
11	00004	Finished Goods - CDO - WLO
12	00013	MMD - Balubad
13	00002	Finished Goods - Bicutan Warehouse
\.


--
-- Data for Name: stock_card; Type: TABLE DATA; Schema: sqlsvr_copy; Owner: postgres
--

COPY stock_card (id, company_id, warehouse_id, inout_mode, item_id, unit_cost, qty, lot_no, mfg_date, exp_date, control_no, status, uom, stock_status) FROM stdin;
69	1	2	I	971	15	10000	LOTOP	2015-08-18	2016-08-17	pmc	Approved	PCS	AVAILABLE
70	5	2	I	971	43	10000	lop0pk	2015-08-18	2016-08-17	control2	Approved	PCS	AVAILABLE
68	5	5	I	628	100	1000	lot1	2015-08-18	2016-08-17	control1	Approved	KG	AVAILABLE
71	5	5	I	628	56	1	HYNB	2015-08-18	2015-10-17	CONTROL3	Approved	KG	DEPLETED
\.


--
-- Name: stock_card_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_id_seq', 71, true);


--
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: sqlsvr_copy; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_seq', 13, true);


SET search_path = transaction, pg_catalog;

--
-- Data for Name: transaction_type; Type: TABLE DATA; Schema: transaction; Owner: postgres
--

COPY transaction_type (id, code, description) FROM stdin;
\.


--
-- Data for Name: stock_card_txn; Type: TABLE DATA; Schema: transaction; Owner: postgres
--

COPY stock_card_txn (id, stock_card_id, qty, unit_id, batch_item_req_id, transaction_type_id) FROM stdin;
203	71	1	7	18	\N
204	68	0.66500000000000004	7	18	\N
205	71	750	5	19	\N
206	70	15	9	20	\N
196	71	111	6	13	\N
197	71	50	5	14	\N
198	70	1	9	15	\N
\.


--
-- Name: stock_card_txn_id_seq; Type: SEQUENCE SET; Schema: transaction; Owner: postgres
--

SELECT pg_catalog.setval('stock_card_txn_id_seq', 198, true);


--
-- Name: transaction_type_id_seq; Type: SEQUENCE SET; Schema: transaction; Owner: postgres
--

SELECT pg_catalog.setval('transaction_type_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

