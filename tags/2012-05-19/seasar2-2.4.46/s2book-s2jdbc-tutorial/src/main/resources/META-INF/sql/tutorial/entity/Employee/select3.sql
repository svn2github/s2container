select name, salary from employee
where
/*IF minSalary != null*/
salary >= /*minSalary*/1000
/*END*/
/*IF maxSalary != null*/
and salary <= /*maxSalary*/2000
/*END*/
order by salary