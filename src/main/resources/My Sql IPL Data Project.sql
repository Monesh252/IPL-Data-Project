-- Number of matches played per year of all the years in IPL.
-- select concat(season, " -> ", count(season), " matches")
-- from IPL.Matches
-- group by season
-- order by season;

-- Number of matches won of all teams over all the years of IPL.
-- select concat(winner, " -> ", count(winner), " matches")
-- from IPL.Matches where winner != ""
-- group by winner
-- order by count(winner) desc; 

-- For the year 2016 get the extra runs conceded per team.
-- SELECT concat(bowling_team, " -> ", sum(extra_runs))
-- from Deliveries
-- where match_id in (select id from Matches where season = 2016)
-- group by bowling_team
-- order by sum(extra_runs) desc;

-- For the year 2015 get the top economical bowlers.
SELECT 
bowler,
(SUM(total_runs - bye_runs - legbye_runs) * 6.0) / 
count(CASE WHEN wide_runs = 0 AND noball_runs = 0 THEN 1 END) AS economy
FROM Deliveries join Matches
on Matches.season = 2015 and Deliveries.match_id = Matches.id
GROUP BY bowler
ORDER BY economy ASC;

-- RN ten Doeschate   -> 4.0000 
-- J Yadav            -> 4.1429 
-- V Kohli            -> 5.4545 
-- R Ashwin           -> 5.8462 
-- S Nadeem           -> 6.1429 
-- Parvez Rasool      -> 6.2000 
-- MC Henriques       -> 6.3200 
-- Z Khan             -> 6.4552 
-- MA Starc           -> 6.7674 
-- GB Hogg            -> 6.8571 
-- M Vijay            -> 7.0000 
-- Sandeep Sharma     -> 7.0000 
-- Bipul Sharma       -> 7.1429 
-- IC Pandey          -> 7.1935 
-- DJ Muthuswami      -> 7.2143 
-- A Nehra            -> 7.2419 
-- SP Narine          -> 7.3125 
-- Gurkeerat Singh    -> 7.3333 
-- S Aravind          -> 7.3333 
-- M Morkel           -> 7.3571 
-- STR Binny          -> 7.3684 
-- SL Malinga         -> 7.4000 
-- JP Duminy          -> 7.4118 
-- HV Patel           -> 7.4829 
-- M de Lange         -> 7.5000 
-- CH Gayle           -> 7.5000 
-- NM Coulter-Nile    -> 7.5529 
-- CH Morris          -> 7.5581 
-- PP Chawla          -> 7.6857 
-- A Mishra           -> 7.7178 
-- RA Jadeja          -> 7.7344 
-- SK Raina           -> 7.7500 
-- PV Tambe           -> 7.8235 
-- Harbhajan Singh    -> 7.8246 
-- B Kumar            -> 7.8774 
-- Iqbal Abdulla      -> 7.8947 
-- DS Kulkarni        -> 7.9143 
-- AD Russell         -> 7.9672 
-- RS Bopara          -> 8.0000 
-- Yuvraj Singh       -> 8.0000 


