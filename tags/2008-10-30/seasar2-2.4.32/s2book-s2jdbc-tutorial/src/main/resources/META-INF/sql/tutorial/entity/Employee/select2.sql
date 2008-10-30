select name, salary from employee
where
salary >= /*minSalary*/1000
and salary <= /*maxSalary*/2000
order by salary