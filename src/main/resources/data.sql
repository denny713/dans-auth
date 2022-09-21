/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : PostgreSQL
 Source Server Version : 140002
 Source Host           : 127.0.0.1:5432
 Source Catalog        : dans_auth
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140002
 File Encoding         : 65001

 Date: 21/09/2022 19:42:56
*/


-- ----------------------------
-- Table structure for md_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."md_token";
CREATE TABLE "public"."md_token" (
                                     "token_id" int8 NOT NULL DEFAULT nextval('md_token_seq'::regclass),
                                     "authorities" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "client" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "approved" bool NOT NULL,
                                     "grant_types" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "access_valid" int8 NOT NULL,
                                     "refresh_valid" int8 NOT NULL,
                                     "url" varchar(50) COLLATE "pg_catalog"."default",
                                     "scopes" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
                                     "secret" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                     "is_scope" bool NOT NULL,
                                     "is_secret" bool NOT NULL,
                                     "resources" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "created_by" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                     "created_date" timestamp(6) NOT NULL,
                                     "updated_by" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                     "updated_date" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of md_token
-- ----------------------------
INSERT INTO "public"."md_token" VALUES (1, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 'dans-auth', 't', 'password,authorization_code,refresh_token,implicit', 43200, 2592000, NULL, 'read,write,trust', 'b28841553dfca63968beb2fe23d837d20ffe3254d347b7270c58fdacbcc8dee4', 't', 't', 'oauth2-resource', 'denny123', '2021-04-29 10:24:09.522', 'denny123', '2021-04-29 10:24:09.522');

-- ----------------------------
-- Primary Key structure for table md_token
-- ----------------------------
ALTER TABLE "public"."md_token" ADD CONSTRAINT "md_token_pkey" PRIMARY KEY ("token_id");
/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : PostgreSQL
 Source Server Version : 140002
 Source Host           : 127.0.0.1:5432
 Source Catalog        : dans_auth
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140002
 File Encoding         : 65001

 Date: 21/09/2022 19:43:03
*/


-- ----------------------------
-- Table structure for md_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."md_user";
CREATE TABLE "public"."md_user" (
                                    "user_id" int8 NOT NULL DEFAULT nextval('md_user_seq'::regclass),
                                    "username" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                    "password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                    "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                    "aktif" int4 NOT NULL,
                                    "status" int4 NOT NULL,
                                    "log_failed" int4 NOT NULL,
                                    "email" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                    "created_by" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                    "created_date" timestamp(6) NOT NULL,
                                    "updated_by" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                    "updated_date" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of md_user
-- ----------------------------
INSERT INTO "public"."md_user" VALUES (1, 'denny123', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'Denny', 1, 0, 0, 'denny.afrizal713@gmail.com', 'admin', '2021-04-29 15:18:49.096', 'admin', '2021-04-29 15:20:34.248');

-- ----------------------------
-- Primary Key structure for table md_user
-- ----------------------------
ALTER TABLE "public"."md_user" ADD CONSTRAINT "md_user_pkey" PRIMARY KEY ("user_id");
