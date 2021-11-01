INSERT INTO companies (name, foundation_date) VALUES ('company1', '2008-02-02')

INSERT INTO customers (name, country) VALUES ('customer1', 'USA')

INSERT INTO skills (area, level) VALUES ('Java', 'Junior'), ('Java', 'Middle'), ('Java', 'Senior'), ('Python', 'Junior'),
('Python', 'Middle'), ('Python', 'Senior')

INSERT INTO developers (name, birth_date, sex, company_id) VALUES ('name1', '1998-03-01', 'male', 1),
('name2', '1999-05-21', 'female', 1), ('name3', '2000-09-19', 'female', 1), ('name4', '2001-05-16', 'male', 1)

INSERT INTO projects (name, start_date, customer_id) VALUES ('projjj', '2020-12-01', 1), ('proooj', '2021-08-13', 1),
('prrroj', '2020-11-02', 1), ('ppproj', '2021-05-30', 1)

INSERT INTO developers_skills (dev_id, skill_id) VALUES (1, 1), (1, 6), (2, 2), (2, 5), (3, 3), (3, 4), (4, 6)

INSERT INTO developers_projects (dev_id, proj_id) VALUES (1, 1), (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), (4, 3), (4, 4)