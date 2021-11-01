SELECT projects.id as project_id, SUM(developers.salary) as cost FROM developers INNER JOIN developers_projects
ON developers.id = developers_projects.dev_id INNER JOIN projects ON developers_projects.proj_id = projects.id
GROUP BY projects.id ORDER BY cost DESC LIMIT 1