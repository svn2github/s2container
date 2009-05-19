select name, salary from employee
/*BEGIN*/
where
/*IF minSalary != null*/
salary >= /*minSalary*/1000
/*END*/
/*IF maxSalary != null*/
and salary <= /*maxSalary*/2000
/*END*/
/*END*/
order by salary