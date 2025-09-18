package edu.cdut.AD;

import java.sql.*;

public class StudentQueries {
    // 根据你的环境调整 URL / USER / PASS
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/student";
    private static final String USER = "root";
    private static final String PASS = "050422";

    public static void main(String[] args) {
        try {
            // 确保驱动类能加载（Connector/J 8.x 推荐用下面这行）
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("找不到 MySQL 驱动，请确认已把 Connector/J 加入项目。");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // ==== 在这里取消注释你想执行的方法来“一个一个查” ====
            printAll(conn);                          // 查询全部学生信息
            // printById(conn, 1);                   // 按学号查询（把 1 改成你想查的学号）
            // printByNameLike(conn, "张");          // 名字模糊查询（查包含“张”的）
            // printStats(conn);                     // 信息统计（总人数、平均年龄、按专业统计）
            // addStudent(conn, "赵六", 22, "人工智能"); // 增：新增一条学生
            // updateStudent(conn, 1, "张三改", 21, "计算机"); // 改：按学号更新
            // deleteStudent(conn, 2);               // 删：按学号删除
            // ===========================================
        } catch (SQLException e) {
            System.err.println("数据库操作出错：");
            e.printStackTrace();
        }
    }

    // 1. 查询全部学生信息
    static void printAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM student";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-5s %-12s %-5s %-15s%n","id","name","age","major");
            while (rs.next()) {
                System.out.printf("%-5d %-12s %-5d %-15s%n",
                        rs.getInt("s_id"),
                        rs.getString("s_name"),
                        rs.getInt("age"),
                        rs.getString("major"));
            }
        }
    }

    // 2. 按学号查询学生信息
    static void printById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("找到： " +
                            rs.getInt("s_id") + " | " +
                            rs.getString("s_name") + " | " +
                            rs.getInt("age") + " | " +
                            rs.getString("major"));
                } else {
                    System.out.println("未找到学号为 " + id + " 的学生。");
                }
            }
        }
    }

    // 3. 按名字模糊查询学生信息（传入部分姓名）
    static void printByNameLike(Connection conn, String namePart) throws SQLException {
        String sql = "SELECT * FROM student WHERE s_name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + namePart + "%");
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println(
                            rs.getInt("s_id") + " | " +
                                    rs.getString("s_name") + " | " +
                                    rs.getInt("age") + " | " +
                                    rs.getString("major"));
                }
                if (!found) System.out.println("没有匹配名字包含 \"" + namePart + "\" 的学生。");
            }
        }
    }

    // 4. 信息统计：总人数、平均年龄、按专业分组的数量
    static void printStats(Connection conn) throws SQLException {
        String totalSql = "SELECT COUNT(*) AS total, AVG(age) AS avg_age FROM student";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(totalSql)) {
            if (rs.next()) {
                System.out.println("学生总数: " + rs.getInt("total") +
                        "，平均年龄: " + rs.getDouble("avg_age"));
            }
        }

        String byMajorSql = "SELECT major, COUNT(*) AS cnt FROM student GROUP BY major";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(byMajorSql)) {
            System.out.println("按专业统计：");
            while (rs.next()) {
                System.out.println(rs.getString("major") + " -> " + rs.getInt("cnt"));
            }
        }
    }

    // 5a. 信息管理 - 新增
    static void addStudent(Connection conn, String name, int age, String major) throws SQLException {
        String sql = "INSERT INTO student (s_name, age, major) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, major);
            int n = ps.executeUpdate();
            System.out.println("插入完成，受影响行数: " + n);
        }
    }

    // 5b. 信息管理 - 修改（按学号）
    static void updateStudent(Connection conn, int id, String name, int age, String major) throws SQLException {
        String sql = "UPDATE student SET s_name = ?, age = ?, major = ? WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, major);
            ps.setInt(4, id);
            int n = ps.executeUpdate();
            System.out.println("更新完成，受影响行数: " + n);
        }
    }

    // 5c. 信息管理 - 删除（按学号）
    static void deleteStudent(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM student WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            System.out.println("删除完成，受影响行数: " + n);
        }
    }
}
