--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "Sinema";
--
-- Name: Sinema; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Sinema" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


ALTER DATABASE "Sinema" OWNER TO postgres;

\connect "Sinema"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: yonetmenler_sayi_arttir(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.yonetmenler_sayi_arttir() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin

update yonetmenler set film_sayisi = film_sayisi + 1 where yonetmen_id = (select yonetmen_id from filmler order by film_id desc limit 1);
return new;
end;
$$;


ALTER FUNCTION public.yonetmenler_sayi_arttir() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: aboneler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aboneler (
    abone_id integer NOT NULL,
    user_id integer,
    abone_type integer,
    kalan_ucretsiz_bilet_sayisi integer
);


ALTER TABLE public.aboneler OWNER TO postgres;

--
-- Name: Aboneler_abone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.aboneler ALTER COLUMN abone_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Aboneler_abone_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actor (
    actor_id integer NOT NULL,
    ad text,
    soyad text
);


ALTER TABLE public.actor OWNER TO postgres;

--
-- Name: Actor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.actor ALTER COLUMN actor_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Actor_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: haberler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.haberler (
    haber_id integer NOT NULL,
    hangi_kullanici_turu integer,
    title text,
    haber text,
    tarih text,
    haber_kategorisi text
);


ALTER TABLE public.haberler OWNER TO postgres;

--
-- Name: Haberler_haber_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.haberler ALTER COLUMN haber_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Haberler_haber_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: kampanyalar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kampanyalar (
    kampanya_id integer NOT NULL,
    hangi_kullanici_turu integer,
    title text,
    kampanya text,
    tarih text,
    kampanya_kategorisi text
);


ALTER TABLE public.kampanyalar OWNER TO postgres;

--
-- Name: Kampanyalar_kampanya_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.kampanyalar ALTER COLUMN kampanya_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Kampanyalar_kampanya_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: seans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seans (
    seans_id integer NOT NULL,
    salon_id integer,
    vizyondaki_film_id integer,
    saat text
);


ALTER TABLE public.seans OWNER TO postgres;

--
-- Name: Seans_seans_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.seans ALTER COLUMN seans_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Seans_seans_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: sinema_salonlari; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sinema_salonlari (
    salon_id integer NOT NULL,
    salon_name text,
    koltuk_sayisi integer
);


ALTER TABLE public.sinema_salonlari OWNER TO postgres;

--
-- Name: Sinema_Salonlari_salon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.sinema_salonlari ALTER COLUMN salon_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Sinema_Salonlari_salon_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_name text,
    user_mail text,
    user_password text,
    user_type integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: Users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Users_user_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: bilgi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bilgi (
    giren_user_id integer
);


ALTER TABLE public.bilgi OWNER TO postgres;

--
-- Name: eski_filmler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eski_filmler (
    eski_film_id integer NOT NULL,
    film_id integer,
    hangi_aboneler_izleyebilir integer,
    aldigi_odul_sayisi integer
);


ALTER TABLE public.eski_filmler OWNER TO postgres;

--
-- Name: eski_filmler_eski_film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.eski_filmler ALTER COLUMN eski_film_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.eski_filmler_eski_film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: filmler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.filmler (
    film_id integer NOT NULL,
    film_name text,
    film_type text,
    film_suresi integer,
    yonetmen_id integer,
    kullanici_puani real DEFAULT 0 NOT NULL
);


ALTER TABLE public.filmler OWNER TO postgres;

--
-- Name: yonetmenler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.yonetmenler (
    yonetmen_id integer NOT NULL,
    ad text,
    soyad text,
    film_sayisi integer
);


ALTER TABLE public.yonetmenler OWNER TO postgres;

--
-- Name: eski_filmler_tablo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.eski_filmler_tablo AS
 SELECT filmler.film_name,
    filmler.film_type,
    filmler.film_suresi,
    filmler.kullanici_puani,
    yonetmenler.ad,
    yonetmenler.soyad,
    eski_filmler.hangi_aboneler_izleyebilir,
    eski_filmler.aldigi_odul_sayisi
   FROM ((public.eski_filmler
     JOIN public.filmler ON ((eski_filmler.film_id = filmler.film_id)))
     JOIN public.yonetmenler ON ((filmler.yonetmen_id = yonetmenler.yonetmen_id)));


ALTER TABLE public.eski_filmler_tablo OWNER TO postgres;

--
-- Name: film_actor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.film_actor (
    film_actor_id integer NOT NULL,
    film_id integer,
    actor_id integer
);


ALTER TABLE public.film_actor OWNER TO postgres;

--
-- Name: film_actor_film_actor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.film_actor ALTER COLUMN film_actor_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.film_actor_film_actor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: filmler_film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.filmler ALTER COLUMN film_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.filmler_film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: kullanici_degerlendirmesi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kullanici_degerlendirmesi (
    kullanici_degerlendirme_id integer NOT NULL,
    user_id integer,
    film_id integer,
    degerlendirme real
);


ALTER TABLE public.kullanici_degerlendirmesi OWNER TO postgres;

--
-- Name: kullanici_degerlendirmesi_kullanici_degerlendirme_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.kullanici_degerlendirmesi ALTER COLUMN kullanici_degerlendirme_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.kullanici_degerlendirmesi_kullanici_degerlendirme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: satin_alinan_biletler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.satin_alinan_biletler (
    satin_alinan_bilet_id integer NOT NULL,
    user_id integer,
    seans_id integer,
    koltuk_name text
);


ALTER TABLE public.satin_alinan_biletler OWNER TO postgres;

--
-- Name: satin_alinan_biletler_satin_alinan_bilet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.satin_alinan_biletler ALTER COLUMN satin_alinan_bilet_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.satin_alinan_biletler_satin_alinan_bilet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: vizyondaki_filmler; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vizyondaki_filmler (
    vizyondaki_film_id integer NOT NULL,
    film_id integer,
    vizyondan_kalkis_tarihi text,
    kullanici_puani integer,
    seans_sayisi integer
);


ALTER TABLE public.vizyondaki_filmler OWNER TO postgres;

--
-- Name: seanslar_tablo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.seanslar_tablo AS
 SELECT seans.seans_id,
    vizyondaki_filmler.vizyondaki_film_id,
    filmler.film_name,
    sinema_salonlari.salon_name,
    seans.saat
   FROM (((public.seans
     JOIN public.vizyondaki_filmler ON ((vizyondaki_filmler.vizyondaki_film_id = seans.vizyondaki_film_id)))
     JOIN public.filmler ON ((vizyondaki_filmler.film_id = filmler.film_id)))
     JOIN public.sinema_salonlari ON ((seans.salon_id = sinema_salonlari.salon_id)));


ALTER TABLE public.seanslar_tablo OWNER TO postgres;

--
-- Name: user_photos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_photos (
    user_photo_id integer NOT NULL,
    user_id integer NOT NULL,
    photo_name text NOT NULL,
    photo_parent text NOT NULL,
    photo_path text NOT NULL
);


ALTER TABLE public.user_photos OWNER TO postgres;

--
-- Name: user_photos_user_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.user_photos ALTER COLUMN user_photo_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_photos_user_photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: vizyondaki_filmler_tablo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vizyondaki_filmler_tablo AS
 SELECT vizyondaki_filmler.vizyondaki_film_id,
    filmler.film_id,
    filmler.film_name,
    filmler.film_type,
    filmler.film_suresi,
    yonetmenler.ad,
    yonetmenler.soyad,
    vizyondaki_filmler.vizyondan_kalkis_tarihi,
    filmler.kullanici_puani
   FROM ((public.vizyondaki_filmler
     JOIN public.filmler ON ((vizyondaki_filmler.film_id = filmler.film_id)))
     JOIN public.yonetmenler ON ((filmler.yonetmen_id = yonetmenler.yonetmen_id)));


ALTER TABLE public.vizyondaki_filmler_tablo OWNER TO postgres;

--
-- Name: vizyondaki_filmler_vizyondaki_film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.vizyondaki_filmler ALTER COLUMN vizyondaki_film_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.vizyondaki_filmler_vizyondaki_film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: yesil_olan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.yesil_olan (
    yesil_olan_id integer NOT NULL,
    koltuk_adi text NOT NULL
);


ALTER TABLE public.yesil_olan OWNER TO postgres;

--
-- Name: yesil_olan_yesil_olan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.yesil_olan ALTER COLUMN yesil_olan_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.yesil_olan_yesil_olan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: yonetmenler_yonetmen_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.yonetmenler ALTER COLUMN yonetmen_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.yonetmenler_yonetmen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: aboneler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aboneler (abone_id, user_id, abone_type, kalan_ucretsiz_bilet_sayisi) FROM stdin;
\.
COPY public.aboneler (abone_id, user_id, abone_type, kalan_ucretsiz_bilet_sayisi) FROM '$$PATH$$/3151.dat';

--
-- Data for Name: actor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actor (actor_id, ad, soyad) FROM stdin;
\.
COPY public.actor (actor_id, ad, soyad) FROM '$$PATH$$/3164.dat';

--
-- Data for Name: bilgi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bilgi (giren_user_id) FROM stdin;
\.
COPY public.bilgi (giren_user_id) FROM '$$PATH$$/3149.dat';

--
-- Data for Name: eski_filmler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eski_filmler (eski_film_id, film_id, hangi_aboneler_izleyebilir, aldigi_odul_sayisi) FROM stdin;
\.
COPY public.eski_filmler (eski_film_id, film_id, hangi_aboneler_izleyebilir, aldigi_odul_sayisi) FROM '$$PATH$$/3170.dat';

--
-- Data for Name: film_actor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.film_actor (film_actor_id, film_id, actor_id) FROM stdin;
\.
COPY public.film_actor (film_actor_id, film_id, actor_id) FROM '$$PATH$$/3166.dat';

--
-- Data for Name: filmler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.filmler (film_id, film_name, film_type, film_suresi, yonetmen_id, kullanici_puani) FROM stdin;
\.
COPY public.filmler (film_id, film_name, film_type, film_suresi, yonetmen_id, kullanici_puani) FROM '$$PATH$$/3168.dat';

--
-- Data for Name: haberler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.haberler (haber_id, hangi_kullanici_turu, title, haber, tarih, haber_kategorisi) FROM stdin;
\.
COPY public.haberler (haber_id, hangi_kullanici_turu, title, haber, tarih, haber_kategorisi) FROM '$$PATH$$/3159.dat';

--
-- Data for Name: kampanyalar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kampanyalar (kampanya_id, hangi_kullanici_turu, title, kampanya, tarih, kampanya_kategorisi) FROM stdin;
\.
COPY public.kampanyalar (kampanya_id, hangi_kullanici_turu, title, kampanya, tarih, kampanya_kategorisi) FROM '$$PATH$$/3161.dat';

--
-- Data for Name: kullanici_degerlendirmesi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kullanici_degerlendirmesi (kullanici_degerlendirme_id, user_id, film_id, degerlendirme) FROM stdin;
\.
COPY public.kullanici_degerlendirmesi (kullanici_degerlendirme_id, user_id, film_id, degerlendirme) FROM '$$PATH$$/3178.dat';

--
-- Data for Name: satin_alinan_biletler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.satin_alinan_biletler (satin_alinan_bilet_id, user_id, seans_id, koltuk_name) FROM stdin;
\.
COPY public.satin_alinan_biletler (satin_alinan_bilet_id, user_id, seans_id, koltuk_name) FROM '$$PATH$$/3176.dat';

--
-- Data for Name: seans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seans (seans_id, salon_id, vizyondaki_film_id, saat) FROM stdin;
\.
COPY public.seans (seans_id, salon_id, vizyondaki_film_id, saat) FROM '$$PATH$$/3157.dat';

--
-- Data for Name: sinema_salonlari; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sinema_salonlari (salon_id, salon_name, koltuk_sayisi) FROM stdin;
\.
COPY public.sinema_salonlari (salon_id, salon_name, koltuk_sayisi) FROM '$$PATH$$/3155.dat';

--
-- Data for Name: user_photos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_photos (user_photo_id, user_id, photo_name, photo_parent, photo_path) FROM stdin;
\.
COPY public.user_photos (user_photo_id, user_id, photo_name, photo_parent, photo_path) FROM '$$PATH$$/3180.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, user_name, user_mail, user_password, user_type) FROM stdin;
\.
COPY public.users (user_id, user_name, user_mail, user_password, user_type) FROM '$$PATH$$/3148.dat';

--
-- Data for Name: vizyondaki_filmler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vizyondaki_filmler (vizyondaki_film_id, film_id, vizyondan_kalkis_tarihi, kullanici_puani, seans_sayisi) FROM stdin;
\.
COPY public.vizyondaki_filmler (vizyondaki_film_id, film_id, vizyondan_kalkis_tarihi, kullanici_puani, seans_sayisi) FROM '$$PATH$$/3172.dat';

--
-- Data for Name: yesil_olan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.yesil_olan (yesil_olan_id, koltuk_adi) FROM stdin;
\.
COPY public.yesil_olan (yesil_olan_id, koltuk_adi) FROM '$$PATH$$/3174.dat';

--
-- Data for Name: yonetmenler; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.yonetmenler (yonetmen_id, ad, soyad, film_sayisi) FROM stdin;
\.
COPY public.yonetmenler (yonetmen_id, ad, soyad, film_sayisi) FROM '$$PATH$$/3153.dat';

--
-- Name: Aboneler_abone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Aboneler_abone_id_seq"', 10, true);


--
-- Name: Actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Actor_id_seq"', 16, true);


--
-- Name: Haberler_haber_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Haberler_haber_id_seq"', 6, true);


--
-- Name: Kampanyalar_kampanya_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Kampanyalar_kampanya_id_seq"', 12, true);


--
-- Name: Seans_seans_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Seans_seans_id_seq"', 12, true);


--
-- Name: Sinema_Salonlari_salon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Sinema_Salonlari_salon_id_seq"', 7, true);


--
-- Name: Users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Users_user_id_seq"', 15, true);


--
-- Name: eski_filmler_eski_film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.eski_filmler_eski_film_id_seq', 9, true);


--
-- Name: film_actor_film_actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.film_actor_film_actor_id_seq', 15, true);


--
-- Name: filmler_film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.filmler_film_id_seq', 36, true);


--
-- Name: kullanici_degerlendirmesi_kullanici_degerlendirme_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kullanici_degerlendirmesi_kullanici_degerlendirme_id_seq', 5, true);


--
-- Name: satin_alinan_biletler_satin_alinan_bilet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.satin_alinan_biletler_satin_alinan_bilet_id_seq', 11, true);


--
-- Name: user_photos_user_photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_photos_user_photo_id_seq', 4, true);


--
-- Name: vizyondaki_filmler_vizyondaki_film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vizyondaki_filmler_vizyondaki_film_id_seq', 11, true);


--
-- Name: yesil_olan_yesil_olan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.yesil_olan_yesil_olan_id_seq', 59, true);


--
-- Name: yonetmenler_yonetmen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.yonetmenler_yonetmen_id_seq', 3985, true);


--
-- Name: actor Actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actor
    ADD CONSTRAINT "Actor_pkey" PRIMARY KEY (actor_id);


--
-- Name: kampanyalar Kampanyalar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kampanyalar
    ADD CONSTRAINT "Kampanyalar_pkey" PRIMARY KEY (kampanya_id);


--
-- Name: aboneler abone_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aboneler
    ADD CONSTRAINT abone_id PRIMARY KEY (abone_id);


--
-- Name: eski_filmler eski_film_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eski_filmler
    ADD CONSTRAINT eski_film_id PRIMARY KEY (eski_film_id);


--
-- Name: film_actor film_actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film_actor
    ADD CONSTRAINT film_actor_pkey PRIMARY KEY (film_actor_id);


--
-- Name: filmler film_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filmler
    ADD CONSTRAINT film_id PRIMARY KEY (film_id);


--
-- Name: haberler haber_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.haberler
    ADD CONSTRAINT haber_id PRIMARY KEY (haber_id);


--
-- Name: kullanici_degerlendirmesi kullanici_degerlendirmesi_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanici_degerlendirmesi
    ADD CONSTRAINT kullanici_degerlendirmesi_pk PRIMARY KEY (kullanici_degerlendirme_id);


--
-- Name: sinema_salonlari salon_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sinema_salonlari
    ADD CONSTRAINT salon_id PRIMARY KEY (salon_id);


--
-- Name: satin_alinan_biletler satin_aliain_bilet_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.satin_alinan_biletler
    ADD CONSTRAINT satin_aliain_bilet_id_pk PRIMARY KEY (satin_alinan_bilet_id);


--
-- Name: seans seans_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seans
    ADD CONSTRAINT seans_id PRIMARY KEY (seans_id);


--
-- Name: users user_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_id PRIMARY KEY (user_id);


--
-- Name: user_photos user_id_photo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_photos
    ADD CONSTRAINT user_id_photo_pk PRIMARY KEY (user_photo_id);


--
-- Name: vizyondaki_filmler vizndaki_film_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vizyondaki_filmler
    ADD CONSTRAINT vizndaki_film_id PRIMARY KEY (vizyondaki_film_id);


--
-- Name: yesil_olan yesil_olan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.yesil_olan
    ADD CONSTRAINT yesil_olan_pkey PRIMARY KEY (yesil_olan_id);


--
-- Name: yonetmenler yonetmen_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.yonetmenler
    ADD CONSTRAINT yonetmen_id PRIMARY KEY (yonetmen_id);


--
-- Name: filmler yonetnem_sayi_arttir_trig; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER yonetnem_sayi_arttir_trig AFTER INSERT ON public.filmler FOR EACH ROW EXECUTE FUNCTION public.yonetmenler_sayi_arttir();


--
-- Name: film_actor actor_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film_actor
    ADD CONSTRAINT actor_id FOREIGN KEY (actor_id) REFERENCES public.actor(actor_id);


--
-- Name: eski_filmler film_id_eski_filmler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eski_filmler
    ADD CONSTRAINT film_id_eski_filmler FOREIGN KEY (film_id) REFERENCES public.filmler(film_id);


--
-- Name: vizyondaki_filmler film_id_vizyondaki_filmler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vizyondaki_filmler
    ADD CONSTRAINT film_id_vizyondaki_filmler FOREIGN KEY (film_id) REFERENCES public.filmler(film_id);


--
-- Name: kullanici_degerlendirmesi kullanici_degerlendirmesi_film_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanici_degerlendirmesi
    ADD CONSTRAINT kullanici_degerlendirmesi_film_id FOREIGN KEY (film_id) REFERENCES public.filmler(film_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: kullanici_degerlendirmesi kullanici_degerlendirmesi_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanici_degerlendirmesi
    ADD CONSTRAINT kullanici_degerlendirmesi_user_id FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: seans salon_id_seans; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seans
    ADD CONSTRAINT salon_id_seans FOREIGN KEY (salon_id) REFERENCES public.sinema_salonlari(salon_id);


--
-- Name: satin_alinan_biletler seans_id_satin_alinan_biletler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.satin_alinan_biletler
    ADD CONSTRAINT seans_id_satin_alinan_biletler FOREIGN KEY (seans_id) REFERENCES public.seans(seans_id);


--
-- Name: aboneler user_id_abone; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aboneler
    ADD CONSTRAINT user_id_abone FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: bilgi user_id_bilgi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bilgi
    ADD CONSTRAINT user_id_bilgi FOREIGN KEY (giren_user_id) REFERENCES public.users(user_id);


--
-- Name: satin_alinan_biletler user_id_satin_alinan_biletler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.satin_alinan_biletler
    ADD CONSTRAINT user_id_satin_alinan_biletler FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: user_photos user_id_user_photo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_photos
    ADD CONSTRAINT user_id_user_photo FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: filmler yonetmen_id_filmler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filmler
    ADD CONSTRAINT yonetmen_id_filmler FOREIGN KEY (yonetmen_id) REFERENCES public.yonetmenler(yonetmen_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

