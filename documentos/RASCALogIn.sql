PGDMP     .                    y            RASCA    13.4    13.4 0    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16412    RASCA    DATABASE     c   CREATE DATABASE "RASCA" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "RASCA";
                postgres    false            ?            1259    16538    Administrador    TABLE     ?   CREATE TABLE public."Administrador" (
    "IDAdministrador" bigint NOT NULL,
    "IDPersona" bigint NOT NULL,
    "IDCargo" bigint NOT NULL
);
 #   DROP TABLE public."Administrador";
       public         heap    postgres    false            ?            1259    16587    Beca    TABLE     Y   CREATE TABLE public."Beca" (
    "IDBeca" bigint NOT NULL,
    "Nombre" text NOT NULL
);
    DROP TABLE public."Beca";
       public         heap    postgres    false            ?            1259    16434    Cargo    TABLE     ?   CREATE TABLE public."Cargo" (
    "IDCargo" bigint NOT NULL,
    "Area" text NOT NULL,
    "Nombre" text NOT NULL,
    "IDFacultad" bigint NOT NULL
);
    DROP TABLE public."Cargo";
       public         heap    postgres    false            ?            1259    16558    Carrera    TABLE     ?   CREATE TABLE public."Carrera" (
    "IDCarrera" bigint NOT NULL,
    "IDFacultad" bigint NOT NULL,
    "Nombre" text NOT NULL
);
    DROP TABLE public."Carrera";
       public         heap    postgres    false            ?            1259    16520    Certificador    TABLE     ?   CREATE TABLE public."Certificador" (
    "IDCertificador" text NOT NULL,
    "IDCargo" bigint NOT NULL,
    "IDPersona" bigint NOT NULL
);
 "   DROP TABLE public."Certificador";
       public         heap    postgres    false            ?            1259    16571 
   Estudiante    TABLE     ?   CREATE TABLE public."Estudiante" (
    "IDEstudiante" bigint NOT NULL,
    "IDPersona" bigint NOT NULL,
    "IDCarrera" bigint NOT NULL,
    "IDBeca" bigint NOT NULL
);
     DROP TABLE public."Estudiante";
       public         heap    postgres    false            ?            1259    16421    Facultad    TABLE     a   CREATE TABLE public."Facultad" (
    "IDFacultad" bigint NOT NULL,
    "Nombre" text NOT NULL
);
    DROP TABLE public."Facultad";
       public         heap    postgres    false            ?            1259    16600    LogIn    TABLE     ?   CREATE TABLE public."LogIn" (
    "IDLog" bigint NOT NULL,
    "Usuario" text NOT NULL,
    "Contrasena" text NOT NULL,
    "HoraLogIn" time without time zone NOT NULL,
    "Fecha" date NOT NULL
);
    DROP TABLE public."LogIn";
       public         heap    postgres    false            ?            1259    16413    Persona    TABLE     >  CREATE TABLE public."Persona" (
    "Apellidos" text NOT NULL,
    "Contrasena" text NOT NULL,
    "Correo" text NOT NULL,
    "FechaNac" date NOT NULL,
    "IDPersona" bigint NOT NULL,
    "Nombres" text NOT NULL,
    "Telefono" text,
    "Usuario" text NOT NULL,
    "Fotografia" text,
    "Carnet" text NOT NULL
);
    DROP TABLE public."Persona";
       public         heap    postgres    false            ?          0    16538    Administrador 
   TABLE DATA           T   COPY public."Administrador" ("IDAdministrador", "IDPersona", "IDCargo") FROM stdin;
    public          postgres    false    204   ?8       ?          0    16587    Beca 
   TABLE DATA           4   COPY public."Beca" ("IDBeca", "Nombre") FROM stdin;
    public          postgres    false    207   ?8       ?          0    16434    Cargo 
   TABLE DATA           L   COPY public."Cargo" ("IDCargo", "Area", "Nombre", "IDFacultad") FROM stdin;
    public          postgres    false    202   ?8       ?          0    16558    Carrera 
   TABLE DATA           H   COPY public."Carrera" ("IDCarrera", "IDFacultad", "Nombre") FROM stdin;
    public          postgres    false    205   ?8       ?          0    16520    Certificador 
   TABLE DATA           R   COPY public."Certificador" ("IDCertificador", "IDCargo", "IDPersona") FROM stdin;
    public          postgres    false    203   9       ?          0    16571 
   Estudiante 
   TABLE DATA           Z   COPY public."Estudiante" ("IDEstudiante", "IDPersona", "IDCarrera", "IDBeca") FROM stdin;
    public          postgres    false    206   *9       ?          0    16421    Facultad 
   TABLE DATA           <   COPY public."Facultad" ("IDFacultad", "Nombre") FROM stdin;
    public          postgres    false    201   G9       ?          0    16600    LogIn 
   TABLE DATA           Y   COPY public."LogIn" ("IDLog", "Usuario", "Contrasena", "HoraLogIn", "Fecha") FROM stdin;
    public          postgres    false    208   d9       ?          0    16413    Persona 
   TABLE DATA           ?   COPY public."Persona" ("Apellidos", "Contrasena", "Correo", "FechaNac", "IDPersona", "Nombres", "Telefono", "Usuario", "Fotografia", "Carnet") FROM stdin;
    public          postgres    false    200   ?9       W           2606    16542     Administrador Administrador_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."Administrador"
    ADD CONSTRAINT "Administrador_pkey" PRIMARY KEY ("IDAdministrador");
 N   ALTER TABLE ONLY public."Administrador" DROP CONSTRAINT "Administrador_pkey";
       public            postgres    false    204            _           2606    16594    Beca Beca_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public."Beca"
    ADD CONSTRAINT "Beca_pkey" PRIMARY KEY ("IDBeca");
 <   ALTER TABLE ONLY public."Beca" DROP CONSTRAINT "Beca_pkey";
       public            postgres    false    207            R           2606    16449    Cargo Cargo_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public."Cargo"
    ADD CONSTRAINT "Cargo_pkey" PRIMARY KEY ("IDCargo");
 >   ALTER TABLE ONLY public."Cargo" DROP CONSTRAINT "Cargo_pkey";
       public            postgres    false    202            Y           2606    16565    Carrera Carrera_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Carrera"
    ADD CONSTRAINT "Carrera_pkey" PRIMARY KEY ("IDCarrera");
 B   ALTER TABLE ONLY public."Carrera" DROP CONSTRAINT "Carrera_pkey";
       public            postgres    false    205            U           2606    16527    Certificador Certificador_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Certificador"
    ADD CONSTRAINT "Certificador_pkey" PRIMARY KEY ("IDCertificador");
 L   ALTER TABLE ONLY public."Certificador" DROP CONSTRAINT "Certificador_pkey";
       public            postgres    false    203            H           2606    16616    Persona Contrasena 
   CONSTRAINT     Y   ALTER TABLE ONLY public."Persona"
    ADD CONSTRAINT "Contrasena" UNIQUE ("Contrasena");
 @   ALTER TABLE ONLY public."Persona" DROP CONSTRAINT "Contrasena";
       public            postgres    false    200            ]           2606    16575    Estudiante Estudiante_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Estudiante"
    ADD CONSTRAINT "Estudiante_pkey" PRIMARY KEY ("IDEstudiante");
 H   ALTER TABLE ONLY public."Estudiante" DROP CONSTRAINT "Estudiante_pkey";
       public            postgres    false    206            N           2606    16473    Facultad Facultad_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."Facultad"
    ADD CONSTRAINT "Facultad_pkey" PRIMARY KEY ("IDFacultad");
 D   ALTER TABLE ONLY public."Facultad" DROP CONSTRAINT "Facultad_pkey";
       public            postgres    false    201            c           2606    16607    LogIn LogIn_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public."LogIn"
    ADD CONSTRAINT "LogIn_pkey" PRIMARY KEY ("IDLog");
 >   ALTER TABLE ONLY public."LogIn" DROP CONSTRAINT "LogIn_pkey";
       public            postgres    false    208            a           2606    16629    Beca NombreBeca 
   CONSTRAINT     R   ALTER TABLE ONLY public."Beca"
    ADD CONSTRAINT "NombreBeca" UNIQUE ("Nombre");
 =   ALTER TABLE ONLY public."Beca" DROP CONSTRAINT "NombreBeca";
       public            postgres    false    207            [           2606    16627    Carrera NombreCarrera 
   CONSTRAINT     X   ALTER TABLE ONLY public."Carrera"
    ADD CONSTRAINT "NombreCarrera" UNIQUE ("Nombre");
 C   ALTER TABLE ONLY public."Carrera" DROP CONSTRAINT "NombreCarrera";
       public            postgres    false    205            P           2606    16631    Facultad NombreFacultad 
   CONSTRAINT     Z   ALTER TABLE ONLY public."Facultad"
    ADD CONSTRAINT "NombreFacultad" UNIQUE ("Nombre");
 E   ALTER TABLE ONLY public."Facultad" DROP CONSTRAINT "NombreFacultad";
       public            postgres    false    201            J           2606    16487    Persona Persona_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Persona"
    ADD CONSTRAINT "Persona_pkey" PRIMARY KEY ("IDPersona");
 B   ALTER TABLE ONLY public."Persona" DROP CONSTRAINT "Persona_pkey";
       public            postgres    false    200            L           2606    16609    Persona Usuario 
   CONSTRAINT     S   ALTER TABLE ONLY public."Persona"
    ADD CONSTRAINT "Usuario" UNIQUE ("Usuario");
 =   ALTER TABLE ONLY public."Persona" DROP CONSTRAINT "Usuario";
       public            postgres    false    200            S           1259    16458    fki_IDFacultad    INDEX     L   CREATE INDEX "fki_IDFacultad" ON public."Cargo" USING btree ("IDFacultad");
 $   DROP INDEX public."fki_IDFacultad";
       public            postgres    false    202            n           2606    16617    LogIn Contrasena    FK CONSTRAINT     ?   ALTER TABLE ONLY public."LogIn"
    ADD CONSTRAINT "Contrasena" FOREIGN KEY ("Contrasena") REFERENCES public."Persona"("Contrasena");
 >   ALTER TABLE ONLY public."LogIn" DROP CONSTRAINT "Contrasena";
       public          postgres    false    200    208    2888            l           2606    16595    Estudiante IDBeca    FK CONSTRAINT     |   ALTER TABLE ONLY public."Estudiante"
    ADD CONSTRAINT "IDBeca" FOREIGN KEY ("IDBeca") REFERENCES public."Beca"("IDBeca");
 ?   ALTER TABLE ONLY public."Estudiante" DROP CONSTRAINT "IDBeca";
       public          postgres    false    2911    207    206            f           2606    16548    Certificador IDCargo    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Certificador"
    ADD CONSTRAINT "IDCargo" FOREIGN KEY ("IDCargo") REFERENCES public."Cargo"("IDCargo");
 B   ALTER TABLE ONLY public."Certificador" DROP CONSTRAINT "IDCargo";
       public          postgres    false    203    202    2898            h           2606    16553    Administrador IDCargo    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Administrador"
    ADD CONSTRAINT "IDCargo" FOREIGN KEY ("IDCargo") REFERENCES public."Cargo"("IDCargo");
 C   ALTER TABLE ONLY public."Administrador" DROP CONSTRAINT "IDCargo";
       public          postgres    false    204    202    2898            j           2606    16577    Estudiante IDCarrera    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Estudiante"
    ADD CONSTRAINT "IDCarrera" FOREIGN KEY ("IDCarrera") REFERENCES public."Carrera"("IDCarrera");
 B   ALTER TABLE ONLY public."Estudiante" DROP CONSTRAINT "IDCarrera";
       public          postgres    false    206    205    2905            d           2606    16515    Cargo IDFacultad    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Cargo"
    ADD CONSTRAINT "IDFacultad" FOREIGN KEY ("IDFacultad") REFERENCES public."Facultad"("IDFacultad");
 >   ALTER TABLE ONLY public."Cargo" DROP CONSTRAINT "IDFacultad";
       public          postgres    false    2894    201    202            i           2606    16566    Carrera IDFacultad    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Carrera"
    ADD CONSTRAINT "IDFacultad" FOREIGN KEY ("IDFacultad") REFERENCES public."Facultad"("IDFacultad");
 @   ALTER TABLE ONLY public."Carrera" DROP CONSTRAINT "IDFacultad";
       public          postgres    false    205    201    2894            e           2606    16533    Certificador IDPersona    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Certificador"
    ADD CONSTRAINT "IDPersona" FOREIGN KEY ("IDPersona") REFERENCES public."Persona"("IDPersona");
 D   ALTER TABLE ONLY public."Certificador" DROP CONSTRAINT "IDPersona";
       public          postgres    false    2890    200    203            g           2606    16543    Administrador IDPersona    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Administrador"
    ADD CONSTRAINT "IDPersona" FOREIGN KEY ("IDPersona") REFERENCES public."Persona"("IDPersona");
 E   ALTER TABLE ONLY public."Administrador" DROP CONSTRAINT "IDPersona";
       public          postgres    false    2890    204    200            k           2606    16582    Estudiante IDPersona    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Estudiante"
    ADD CONSTRAINT "IDPersona" FOREIGN KEY ("IDPersona") REFERENCES public."Persona"("IDPersona");
 B   ALTER TABLE ONLY public."Estudiante" DROP CONSTRAINT "IDPersona";
       public          postgres    false    206    2890    200            m           2606    16610    LogIn Usuario    FK CONSTRAINT     }   ALTER TABLE ONLY public."LogIn"
    ADD CONSTRAINT "Usuario" FOREIGN KEY ("Usuario") REFERENCES public."Persona"("Usuario");
 ;   ALTER TABLE ONLY public."LogIn" DROP CONSTRAINT "Usuario";
       public          postgres    false    208    2892    200            ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?     