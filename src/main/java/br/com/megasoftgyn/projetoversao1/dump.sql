PGDMP                         y            springboot-basico    13.2    13.2 9    	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16550    springboot-basico    DATABASE     ^   CREATE DATABASE "springboot-basico" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
 #   DROP DATABASE "springboot-basico";
                postgres    false            �            1259    16551    seq_bairro_id    SEQUENCE     v   CREATE SEQUENCE public.seq_bairro_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.seq_bairro_id;
       public          postgres    false            �            1259    16553    bairro    TABLE     �   CREATE TABLE public.bairro (
    bairro_id integer DEFAULT nextval('public.seq_bairro_id'::regclass) NOT NULL,
    nome character varying,
    valor_iptu numeric
);
    DROP TABLE public.bairro;
       public         heap    postgres    false    200            �            1259    16560    bairro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bairro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.bairro_id_seq;
       public          postgres    false    201                       0    0    bairro_id_seq    SEQUENCE OWNED BY     F   ALTER SEQUENCE public.bairro_id_seq OWNED BY public.bairro.bairro_id;
          public          postgres    false    202            �            1259    16562    seq_item_compra_id    SEQUENCE     {   CREATE SEQUENCE public.seq_item_compra_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.seq_item_compra_id;
       public          postgres    false            �            1259    16564    compra    TABLE     �   CREATE TABLE public.compra (
    compra_id integer DEFAULT nextval('public.seq_item_compra_id'::regclass) NOT NULL,
    pessoa character varying(14) NOT NULL,
    valor numeric
);
    DROP TABLE public.compra;
       public         heap    postgres    false    203            �            1259    16571    compra_id_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.compra_id_seq;
       public          postgres    false    204                       0    0    compra_id_seq    SEQUENCE OWNED BY     F   ALTER SEQUENCE public.compra_id_seq OWNED BY public.compra.compra_id;
          public          postgres    false    205            �            1259    16573    seq_endereco_id    SEQUENCE     x   CREATE SEQUENCE public.seq_endereco_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.seq_endereco_id;
       public          postgres    false            �            1259    16575    endereco    TABLE     �   CREATE TABLE public.endereco (
    endereco_id integer DEFAULT nextval('public.seq_endereco_id'::regclass) NOT NULL,
    rua character varying,
    numero integer,
    bairro integer,
    cep character varying(8)
);
    DROP TABLE public.endereco;
       public         heap    postgres    false    206            �            1259    16582    endereco_id_seq    SEQUENCE     �   CREATE SEQUENCE public.endereco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.endereco_id_seq;
       public          postgres    false    207                       0    0    endereco_id_seq    SEQUENCE OWNED BY     L   ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.endereco_id;
          public          postgres    false    208            �            1259    16584    item_compra    TABLE     �   CREATE TABLE public.item_compra (
    item_compra_id integer NOT NULL,
    compra integer NOT NULL,
    nome_item character varying,
    valor numeric NOT NULL
);
    DROP TABLE public.item_compra;
       public         heap    postgres    false            �            1259    16590    item_compra_id_seq    SEQUENCE     �   CREATE SEQUENCE public.item_compra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.item_compra_id_seq;
       public          postgres    false    209                       0    0    item_compra_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.item_compra_id_seq OWNED BY public.item_compra.item_compra_id;
          public          postgres    false    210            �            1259    16592    pessoa    TABLE     .  CREATE TABLE public.pessoa (
    cpf character varying(14) NOT NULL,
    tipo_pessoa integer,
    endereco integer NOT NULL,
    profissao integer NOT NULL,
    nome character varying(50) NOT NULL,
    sobrenome character varying(100) NOT NULL,
    idade integer NOT NULL,
    peso numeric NOT NULL
);
    DROP TABLE public.pessoa;
       public         heap    postgres    false            �            1259    16598    seq_profissao_id    SEQUENCE     y   CREATE SEQUENCE public.seq_profissao_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.seq_profissao_id;
       public          postgres    false            �            1259    16600 	   profissao    TABLE     �   CREATE TABLE public.profissao (
    profissao_id integer DEFAULT nextval('public.seq_profissao_id'::regclass) NOT NULL,
    nome character varying,
    salario numeric
);
    DROP TABLE public.profissao;
       public         heap    postgres    false    212            �            1259    16607    profissao_id_seq    SEQUENCE     �   CREATE SEQUENCE public.profissao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.profissao_id_seq;
       public          postgres    false    213                       0    0    profissao_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.profissao_id_seq OWNED BY public.profissao.profissao_id;
          public          postgres    false    214            �            1259    16609    seq_compra_id    SEQUENCE     v   CREATE SEQUENCE public.seq_compra_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.seq_compra_id;
       public          postgres    false            �            1259    16611    tipo_pessoa    TABLE     b   CREATE TABLE public.tipo_pessoa (
    tipo_id integer NOT NULL,
    nome character varying(20)
);
    DROP TABLE public.tipo_pessoa;
       public         heap    postgres    false            ^           2604    16614    item_compra item_compra_id    DEFAULT     |   ALTER TABLE ONLY public.item_compra ALTER COLUMN item_compra_id SET DEFAULT nextval('public.item_compra_id_seq'::regclass);
 I   ALTER TABLE public.item_compra ALTER COLUMN item_compra_id DROP DEFAULT;
       public          postgres    false    210    209            �          0    16553    bairro 
   TABLE DATA           =   COPY public.bairro (bairro_id, nome, valor_iptu) FROM stdin;
    public          postgres    false    201   H>       �          0    16564    compra 
   TABLE DATA           :   COPY public.compra (compra_id, pessoa, valor) FROM stdin;
    public          postgres    false    204   �>       �          0    16575    endereco 
   TABLE DATA           I   COPY public.endereco (endereco_id, rua, numero, bairro, cep) FROM stdin;
    public          postgres    false    207   	?       �          0    16584    item_compra 
   TABLE DATA           O   COPY public.item_compra (item_compra_id, compra, nome_item, valor) FROM stdin;
    public          postgres    false    209   �?                 0    16592    pessoa 
   TABLE DATA           e   COPY public.pessoa (cpf, tipo_pessoa, endereco, profissao, nome, sobrenome, idade, peso) FROM stdin;
    public          postgres    false    211   $@                 0    16600 	   profissao 
   TABLE DATA           @   COPY public.profissao (profissao_id, nome, salario) FROM stdin;
    public          postgres    false    213   �@                 0    16611    tipo_pessoa 
   TABLE DATA           4   COPY public.tipo_pessoa (tipo_id, nome) FROM stdin;
    public          postgres    false    216   �A                  0    0    bairro_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.bairro_id_seq', 45, true);
          public          postgres    false    202                       0    0    compra_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.compra_id_seq', 3, true);
          public          postgres    false    205                       0    0    endereco_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.endereco_id_seq', 8, true);
          public          postgres    false    208                       0    0    item_compra_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.item_compra_id_seq', 9, true);
          public          postgres    false    210                       0    0    profissao_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.profissao_id_seq', 12, true);
          public          postgres    false    214                       0    0    seq_bairro_id    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seq_bairro_id', 1, false);
          public          postgres    false    200                       0    0    seq_compra_id    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seq_compra_id', 1, false);
          public          postgres    false    215                       0    0    seq_endereco_id    SEQUENCE SET     >   SELECT pg_catalog.setval('public.seq_endereco_id', 1, false);
          public          postgres    false    206                       0    0    seq_item_compra_id    SEQUENCE SET     A   SELECT pg_catalog.setval('public.seq_item_compra_id', 1, false);
          public          postgres    false    203                       0    0    seq_profissao_id    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.seq_profissao_id', 1, false);
          public          postgres    false    212            a           2606    16616    bairro bairro_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.bairro
    ADD CONSTRAINT bairro_pkey PRIMARY KEY (bairro_id);
 <   ALTER TABLE ONLY public.bairro DROP CONSTRAINT bairro_pkey;
       public            postgres    false    201            c           2606    16618    compra compra_id_key 
   CONSTRAINT     T   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_id_key UNIQUE (compra_id);
 >   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_id_key;
       public            postgres    false    204            e           2606    16620    endereco endereco_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (endereco_id);
 @   ALTER TABLE ONLY public.endereco DROP CONSTRAINT endereco_pkey;
       public            postgres    false    207            g           2606    16622    item_compra item_compra_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.item_compra
    ADD CONSTRAINT item_compra_pkey PRIMARY KEY (item_compra_id);
 F   ALTER TABLE ONLY public.item_compra DROP CONSTRAINT item_compra_pkey;
       public            postgres    false    209            i           2606    16624    pessoa pessoa_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (cpf);
 <   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_pkey;
       public            postgres    false    211            k           2606    16626    profissao profissao_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.profissao
    ADD CONSTRAINT profissao_pkey PRIMARY KEY (profissao_id);
 B   ALTER TABLE ONLY public.profissao DROP CONSTRAINT profissao_pkey;
       public            postgres    false    213            m           2606    16628    tipo_pessoa tipo_pessoa_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.tipo_pessoa
    ADD CONSTRAINT tipo_pessoa_pkey PRIMARY KEY (tipo_id);
 F   ALTER TABLE ONLY public.tipo_pessoa DROP CONSTRAINT tipo_pessoa_pkey;
       public            postgres    false    216            n           2606    16629    compra compra_pessoa_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pessoa_fkey FOREIGN KEY (pessoa) REFERENCES public.pessoa(cpf);
 C   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_pessoa_fkey;
       public          postgres    false    211    3177    204            o           2606    16634    endereco endereco_bairro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_bairro_fkey FOREIGN KEY (bairro) REFERENCES public.bairro(bairro_id);
 G   ALTER TABLE ONLY public.endereco DROP CONSTRAINT endereco_bairro_fkey;
       public          postgres    false    207    3169    201            p           2606    16639 #   item_compra item_compra_compra_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.item_compra
    ADD CONSTRAINT item_compra_compra_fkey FOREIGN KEY (compra) REFERENCES public.compra(compra_id);
 M   ALTER TABLE ONLY public.item_compra DROP CONSTRAINT item_compra_compra_fkey;
       public          postgres    false    204    3171    209            q           2606    16644    pessoa pessoa_endereco_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_endereco_fkey FOREIGN KEY (endereco) REFERENCES public.endereco(endereco_id);
 E   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_endereco_fkey;
       public          postgres    false    211    3173    207            r           2606    16649    pessoa pessoa_profissao_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_profissao_fkey FOREIGN KEY (profissao) REFERENCES public.profissao(profissao_id);
 F   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_profissao_fkey;
       public          postgres    false    3179    211    213            s           2606    16654    pessoa pessoa_tipo_pessoa_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_tipo_pessoa_fkey FOREIGN KEY (tipo_pessoa) REFERENCES public.tipo_pessoa(tipo_id);
 H   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_tipo_pessoa_fkey;
       public          postgres    false    216    211    3181            �   d   x�3�J�MM��/�421�30�2�t�+I,J
���9}���R3�9-�L�,8��J��Jo����4��p�e�$*8�'rp��qqq `6X      �   =   x��� !�3c�{�����9c{JDO9(, ��Ǣ!�tl��[G{�x������M      �   t   x�̻
AF���S�	$3�f2����[�L��t��w�S~p&\6�e}���9z`/�	�������oo���hRaP�"Z2U,�����qh4ВJ3��{>rϊQ��r���P��      �   �   x�%�=�0��9ENP�-{3!1� ,� R�Q�p��Ø�߳�,X��Čz��U�;�h:c��0`-QO�N�����ʽ�������d��,Sn���<���@���+Wl�� (SM�?�vJ�/W�,         �   x�m�1
B1�zr�� l6�fS�X�vbe�`��|!����)�y�4s��9!BPp��n���V�Ü�+*��4昩�ʢ�u��0�^�?�_��X��>v�}"%(v%�RGVV~AZh��s_�d(q         �   x�U�;�0���>EO5�cFu�����E�qIۓp3G��0��џ~ٲ�}z��-en�6�ŏfpa�)K�qf�@��Z4
Kh�4���)f���y^Ӱ�&��N U%�^a[�q��G�ïPM�E�����mz��Ĩ�\h�t����P9#�:�)~M�lJ	;�;��f)9+�s6�>_#"���K         5   x�3�t;��839�ˈӫ����ۘӵ� 5931�˄ӿ��(��+F��� ���     