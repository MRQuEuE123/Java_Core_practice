# Module 13. SQL
[Главная страница](..)




## Managers with at Least 5 Direct Reports (570)
Задача: Найти менеджеров, у которых как минимум 5 подчиненных.
```sql
     SELECT name
     FROM  Employee
     WHERE id IN (
     SELECT managerId #, COUNT(*) as count
     FROM Employee
     GROUP BY managerId
     HAVING COUNT(*) >= 5
     );
```
## Confirmation Rate (1934)
Задача: Рассчитать средний уровень подтверждения для каждого пользователя.
```sql
SELECT S.user_id,ROUND(AVG(IF(c.action="confirmed",1,0)),2) AS confirmation_rate
FROM Signups AS S
LEFT JOIN Confirmations AS C ON S.user_id =C.user_id
GROUP BY S.User_id
 
```
## Monthly Transactions I (1193)
Задача: Посчитать количество транзакций, количество одобренных транзакций и суммы транзакций по месяцам и странам.

```sql
SELECT  SUBSTR(trans_date,1,7) as month, 
        country,
        count(id) as trans_count, 
        SUM(IF( state = 'approved' ,1,0)) as approved_count, 
        SUM(amount) as trans_total_amount, 
        SUM(CASE WHEN state = 'approved' then amount else 0 END) as approved_total_amount
FROM Transactions
GROUP BY month, country

```
## Immediate Food Delivery II (1174)
Задача: Определить процент немедленных доставок
``` sql
select ROUND(avg(if(order_date = customer_pref_delivery_date,1,0))*100,2) as immediate_percentage
from  Delivery
where   (customer_id, order_date) IN  (
SELECT customer_id, MIN(order_date) AS first_order_date
FROM Delivery
GROUP BY customer_id
)
```
## Game Play Analysis IV (550)
Задача: Найти долю игроков, которые вошли в игру на второй день.

```sql
SELECT ROUND(SUM(login)/COUNT(DISTINCT player_id), 2) AS fraction
FROM (
SELECT
player_id,
DATEDIFF(event_date, MIN(event_date) OVER(PARTITION BY player_id)) = 1 AS login
FROM Activity
) AS t
```

## Product Sales Analysis III (1070)
Задача: Проанализировать продажи продуктов, включая количество проданных единиц и общую сумму продаж.
```sql
SELECT product_id, year as first_year, quantity, price 
FROM SALES 
WHERE ( product_id,YEAR) IN (
SELECT product_id,MIN(YEAR)
FROM Sales
GROUP BY product_id)

```
## Customers Who Bought All Products (1045)
Задача: Найти клиентов, которые купили все доступные продукты.
```sql
SELECT customer_id 
FROM Customer
GROUP BY customer_id
HAVING COUNT( DISTINCT product_key) = (
    SELECT COUNT(product_key) FROM Product
)

```
## Consecutive Numbers (180)
Задача: Найти последовательные числа в таблице.
```sql
# Write your MySQL query statement below
SELECT DISTINCT A.num AS ConsecutiveNums
FROM Logs as A
JOIN Logs as B ON A.id - 1 = B.id
JOIN Logs as C ON A.id + 1 = C.id
WHERE A.num = B.num AND B.num = C.num;

```
## Product Price at a Given Date (1164)
Задача: Определить цену продукта на определенную дату.
```sql
WITH T AS ( SELECT  product_id, MAX(change_date) AS Max_date
    FROM Products
    WHERE change_date <= '2019-08-16'
    GROUP BY product_id
) 
SELECT P.product_id , P.new_price price
FROM Products P JOIN T ON P.product_id=T.product_id AND P.change_date=T.Max_date
UNION 
SELECT Product_id, 10 AS price 
FROM Products 
WHERE product_id NOT IN (
    SELECT product_id 
    FROM T
    );

```
## Last Person to Fit in the Bus (1204)
Задача: Найти последнего человека, который сможет попасть в автобус с учетом количества мест.
```sql
WITH RollingSum AS (
    SELECT *,
        SUM(weight) OVER (ORDER BY turn ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS rolling_sum
    FROM Queue
    ORDER BY turn
)
SELECT person_name
FROM RollingSum
WHERE rolling_sum <= 1000
ORDER BY turn DESC
LIMIT 1;

```
## Count Salary Categories (1907)
Задача: Посчитать количество сотрудников в каждой категории зарплаты.
```sql
SELECT 'Low Salary' AS category, COUNT(*) AS accounts_count FROM Accounts WHERE income < 20000 
UNION
SELECT 'Average Salary' AS category, COUNT(*) AS accounts_count FROM Accounts WHERE income >= 20000 AND income <= 50000
UNION
SELECT 'High Salary' AS category, COUNT(*) AS accounts_count FROM Accounts WHERE income > 50000

```
## Second Highest Salary (176)
Задача: Найти вторую по величине зарплату среди сотрудников.
```sql
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);

```
## Exchange Seats (626)
Задача: Поменять местами сотрудников в таблице с указанием их номеров.
```sql
SELECT 
    id,
    CASE
        WHEN id % 2 = 0 THEN LAG(student) OVER(ORDER BY id)
        ELSE COALESCE(LEAD(student) OVER(ORDER BY id), student)
    END AS student
FROM Seat

```
## Movie Rating (1341)
Задача: Рассчитать средний рейтинг фильмов.
```sql
SQL 
(
SELECT u.name AS results
FROM MovieRating AS R
    LEFT JOIN Users u  ON (R.user_id = u.user_id)
GROUP BY R.user_id 
ORDER BY COUNT(R.movie_id) DESC, u.name 
LIMIT 1
)
UNION ALL

(
SELECT M.title AS results 
FROM MovieRating R
LEFT JOIN Movies M  ON (R.movie_id = M.movie_id)
WHERE R.created_at LIKE '2020-02%'
GROUP BY R.movie_id
ORDER BY AVG(R.rating) DESC, M.title 
LIMIT 1
)

```
## Restaurant Growth (1321)
Задача: Проанализировать рост ресторанов по количеству открытых заведений за период времени.
```sql
SELECT DISTINCT
    visited_on
    ,SUM(amount) OVER( ORDER BY visited_on RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND CURRENT ROW) AS amount
    ,ROUND(SUM(amount) OVER(ORDER BY visited_on RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND CURRENT ROW) / 7, 2) AS average_amount
FROM Customer
LIMIT 100000 -- The "LIMIT" clause does not allow dynamic expressions.
OFFSET 6

```
## Friend Requests II: Who Has the Most Friends (602)
Задача: Найти пользователя с наибольшим количеством друзей на платформе.
```sql
# Write your MySQL query statement below
WITH T AS(
SELECT requester_id FROM RequestAccepted
    UNION ALL
SELECT accepter_id FROM RequestAccepted
)

SELECT requester_id AS ID, COUNT(*) AS NUM
FROM T
GROUP BY ID
ORDER BY NUM DESC 
LIMIT 1

```
## Investments in 2016 (585)
Задача: Проанализировать инвестиции, сделанные в 2016 году, включая суммы и количество инвесторов.
```sql
SELECT ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM (
    SELECT tiv_2016
           ,COUNT(*) OVER(PARTITION BY tiv_2015) AS tiv_2015_count
           ,COUNT(*) OVER(PARTITION BY lat, lon) AS city_count
    FROM Insurance
) AS InsuranceWithCounts
WHERE tiv_2015_count > 1
  AND city_count = 1;
```
## Department Top Three Salaries (185)
Задача: Найти три самых высоких зарплаты в каждом отделе.
```sql
# Write your MySQL query statement below
WITH T AS(
SELECT E.NAME AS Employee
        ,E.salary AS Salary
        ,D.name AS Department
        ,DENSE_RANK() OVER (PARTITION BY D.id ORDER BY E.salary DESC) AS RRANK
FROM Employee E
 JOIN Department D ON D.id = E.departmentId
)
SELECT 
     Employee
    ,Salary
    ,Department
FROM T
WHERE RRANK<4;
```