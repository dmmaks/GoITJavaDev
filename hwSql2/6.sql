WITH cheapest_project AS (SELECT id, cost FROM projects ORDER BY cost LIMIT 1)

SELECT AVG(salary) FROM developers INNER JOIN developers_projects ON developers.id = developers_projects.dev_id
INNER JOIN cheapest_project ON cheapest_project.id = developers_projects.proj_id GROUP BY proj_id