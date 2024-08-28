package fatec.les.ecommerce.dao;

import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.CartaoCredito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoCreditoDAO implements IDAO {
    private static final CartaoCreditoDAO instance = new CartaoCreditoDAO();

    private CartaoCreditoDAO() {
        // Construtor privado para evitar instanciamento
    }

    public static CartaoCreditoDAO getInstance() {return instance;}

    @Override
    public int insert(DomainEntity entidade) {
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;
        String sql = "INSERT INTO cartoes_credito (numero_cartao, nome_impresso, bandeira_cartao, codigo_seguranca, favorito, cliente_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cartaoCredito.getNumeroCartao());
            stmt.setString(2, cartaoCredito.getNomeImpresso());
            stmt.setString(3, cartaoCredito.getBandeiraCartao());
            stmt.setString(4, cartaoCredito.getCodigoSeguranca());
            stmt.setBoolean(5, cartaoCredito.isFavorito());
            stmt.setInt(6, cartaoCredito.getClienteId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String update(DomainEntity entidade) {
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;
        String sql = "UPDATE cartoes_credito SET numero_cartao = ?, nome_impresso = ?, bandeira_cartao = ?, codigo_seguranca = ?, favorito = ? WHERE id_cartao = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cartaoCredito.getNumeroCartao());
            stmt.setString(2, cartaoCredito.getNomeImpresso());
            stmt.setString(3, cartaoCredito.getBandeiraCartao());
            stmt.setString(4, cartaoCredito.getCodigoSeguranca());
            stmt.setBoolean(5, cartaoCredito.isFavorito());
            stmt.setInt(6, cartaoCredito.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? "sucesso" : "erro";
        } catch (SQLException e) {
            return "erro";
        }
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM cartoes_credito WHERE id_cartao = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? "sucesso" : "erro";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<DomainEntity> select() {
        List<DomainEntity> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM cartoes_credito";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CartaoCredito cartaoCredito = new CartaoCredito(
                        rs.getInt("id_cartao"),
                        rs.getString("numero_cartao"),
                        rs.getString("nome_impresso"),
                        rs.getString("bandeira_cartao"),
                        rs.getString("codigo_seguranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
                cartoes.add(cartaoCredito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartoes;
    }

    @Override
    public DomainEntity select(int id) {
        String sql = "SELECT * FROM cartoes_credito WHERE id_cartao = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CartaoCredito(
                        rs.getInt("id_cartao"),
                        rs.getString("numero_cartao"),
                        rs.getString("nome_impresso"),
                        rs.getString("bandeira_cartao"),
                        rs.getString("codigo_seguranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CartaoCredito> selectByClienteId(int clienteId) {
        List<CartaoCredito> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM cartoes_credito WHERE cliente_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartaoCredito cartaoCredito = new CartaoCredito(
                        rs.getInt("id_cartao"),
                        rs.getString("numero_cartao"),
                        rs.getString("nome_impresso"),
                        rs.getString("bandeira_cartao"),
                        rs.getString("codigo_seguranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
                cartoes.add(cartaoCredito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartoes;
    }

}
