-- Number of matches played per year of all the years in IPL.
select (season || ' -> ' ||count(season)) as Total_Wins 
from Matches
group by season
order by season;

-- Number of matches won of all teams over all the years of IPL.
select (winner|| ' -> ' || count(winner)|| ' matches')
from Matches where winner != ''
group by winner
order by count(winner) desc; 

-- For the year 2016 get the extra runs conceded per team.
select (bowling_team|| ' -> ' || sum(extra_runs))
from Deliveries
where match_id in (select id from Matches where season = 2016)
group by bowling_team
order by sum(extra_runs) desc;

-- For the year 2015 get the top economical bowlers.
select bowler, (sum(total_runs - bye_runs - legbye_runs) * 6.0) / count(case when wide_runs = 0 and noball_runs = 0 then 1 end) as economy
from Deliveries join Matches
on Matches.season = 2015 and Deliveries.match_id = Matches.id
group by bowler
order by economy asc;

