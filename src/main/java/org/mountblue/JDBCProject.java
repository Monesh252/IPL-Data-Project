package org.mountblue;

import java.sql.*;
import java.util.List;

public class JDBCProject {
    private static final String url = "jdbc:postgresql://localhost:5432/IPL?sslmode=disable";
    private static final String user = "postgres";
    private static final String pwd = "monesh";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pwd);
    }

    public static void findMatchesWonByAllTeams() throws SQLException{
        Statement query = getConnection().createStatement();
        ResultSet result = query.executeQuery("select winner, count(winner) as total " +
                "from Matches where winner is not null " +
                "group by winner " +
                "order by count(winner) desc;");

        while(result.next()) {
            System.out.printf("%-30s -> %4d \n",result.getString("winner"),
                    result.getInt("total"));
        }
    }

    public static void findMatchesPlayedPerYear() throws SQLException {
        Statement query = getConnection().createStatement();
        ResultSet result = query.executeQuery("select season, count(season) as total " +
                "from Matches " +
                "group by season " +
                "order by season;");

        while(result.next()) {
            System.out.printf("%-5d -> %4d \n",result.getInt("season"),
                     result.getInt("total"));
        }
    }

    public static void findExtrasConcededByATeamPerYear(int year) throws SQLException {
        String sqlQuery = "select bowling_team, sum(extra_runs) as total_extra " +
                "from Deliveries where match_id in (select id from Matches where season = ?)" +
                "group by bowling_team " +
                "order by sum(extra_runs) desc";

        PreparedStatement query = getConnection().prepareStatement(sqlQuery);
        query.setInt(1, year);
        ResultSet result = query.executeQuery();

        while (result.next()){
            System.out.printf("%-30s -> %4d extras \n",result.getString("bowling_team"),
                    + result.getInt("total_extra"));
        }
    }

    public static void findTopEconomicalBowler(int year) throws SQLException {
        String sqlQuery = "select bowler, (sum(total_runs - bye_runs - legbye_runs) * 6.0) / count(case when wide_runs = 0 and noball_runs = 0 then 1 end) as Economy " +
                "from Deliveries join Matches " +
                "on Matches.season = ? and Deliveries.match_id = Matches.id " +
                "group by bowler " +
                "order by economy asc";

        PreparedStatement query = getConnection().prepareStatement(sqlQuery);
        query.setDouble(1, year);
        ResultSet result = query.executeQuery();

        while (result.next()){
            System.out.printf("%-18s -> %4.2f\n",result.getString("bowler"),
                    result.getDouble("Economy"));
        }
    }
}
