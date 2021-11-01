CREATE TABLE public.companies
(
    id serial NOT NULL,
    name text NOT NULL,
    foundation_date date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.developers
(
    id serial NOT NULL,
    name text NOT NULL,
    birth_date date NOT NULL,
    sex text NOT NULL,
    company_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT company_fk FOREIGN KEY (company_id)
        REFERENCES public.companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public.skills
(
    id serial NOT NULL,
    area text NOT NULL,
    level text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.customers
(
    id serial NOT NULL,
    name text NOT NULL,
    country text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.projects
(
    id serial NOT NULL,
    name text NOT NULL,
    start_date date NOT NULL,
    customer_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT customer_fk FOREIGN KEY (customer_id)
        REFERENCES public.customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public.developers_skills
(
    id serial NOT NULL,
    dev_id integer NOT NULL,
    skill_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT dev_fk FOREIGN KEY (dev_id)
        REFERENCES public.developers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT skill_fk FOREIGN KEY (skill_id)
        REFERENCES public.skills (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public.developers_projects
(
    id serial NOT NULL,
    dev_id integer NOT NULL,
    proj_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT dev_fk FOREIGN KEY (dev_id)
        REFERENCES public.developers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT proj_fk FOREIGN KEY (proj_id)
        REFERENCES public.projects (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);