package com.seuprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/Cliente";
    private static final String user = "postgres";
    private static final String password = "Jr611021";
    private Connection conn;

    public DatabaseConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para 'clientes'
    public void incluirCliente(String nome, String endereco) {
        try {
            String sql = "INSERT INTO clientes (nome, endereco) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);
            pstmt.executeUpdate();
            System.out.println("Cliente incluído com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarClientes() {
        try {
            String sql = "SELECT * FROM clientes";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Lista de Clientes:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Endereço: " + endereco);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para 'produtos'
    public void incluirProdutos() {
        try {
            String sql = "INSERT INTO produtos (produto, valor) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Incluindo 5 produtos novos
            pstmt.setString(1, "Produto A");
            pstmt.setInt(2, 100);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produto B");
            pstmt.setInt(2, 200);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produto C");
            pstmt.setInt(2, 300);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produto D");
            pstmt.setInt(2, 400);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produto E");
            pstmt.setInt(2, 500);
            pstmt.executeUpdate();

            System.out.println("5 Produtos incluídos com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarProduto(int id, String produto, int valor) {
        try {
            String sql = "UPDATE produtos SET produto = ?, valor = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto);
            pstmt.setInt(2, valor);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Produto alterado com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(int id, String produto, int valor) {
        try {
            String sql = "UPDATE produtos SET produto = ?, valor = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto);
            pstmt.setInt(2, valor);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProduto(int id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Produto excluído com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarProdutos() {
        try {
            String sql = "SELECT * FROM produtos";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Lista de Produtos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String produto = rs.getString("produto");
                int valor = rs.getInt("valor");
                System.out.println("ID: " + id + ", Produto: " + produto + ", Valor: " + valor);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();

        // Incluindo 5 produtos
        db.incluirProdutos();

        // Atualizando o produto com ID 1
        db.atualizarProduto(1, "Produto A Atualizado", 150);

        // Excluindo o produto com ID 2
        db.excluirProduto(2);

        // Listando todos os produtos
        db.listarProdutos();

        // Exemplos de como usar métodos de 'clientes'
        db.incluirCliente("Cliente X", "Endereço Y");
        db.listarClientes();
    }
}
