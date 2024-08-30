package fatec.les.ecommerce.dao;

import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements IDAO {
    private static final EnderecoDAO instance = new EnderecoDAO();

    private EnderecoDAO() {
        // Construtor privado para evitar instanciamento
    }

    public static EnderecoDAO getInstance() {return instance;}

    @Override
    public int insert(DomainEntity entidade) {
        Endereco endereco = (Endereco) entidade;
        String sql = "INSERT INTO enderecos (nome_endereco, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, obs_endereco, endereco_entrega, endereco_cobranca, favorito, cliente_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, endereco.getNomeEndereco());
            stmt.setString(2, endereco.getTipoResidencia());
            stmt.setString(3, endereco.getTipoLogradouro());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getNumero());
            stmt.setString(6, endereco.getBairro());
            stmt.setString(7, endereco.getCep());
            stmt.setString(8, endereco.getCidade());
            stmt.setString(9, endereco.getEstado());
            stmt.setString(10, endereco.getPais());
            stmt.setString(11, endereco.getObsEndereco());
            stmt.setBoolean(12, endereco.isEnderecoEntrega());
            stmt.setBoolean(13, endereco.isEnderecoCobranca());
            stmt.setBoolean(14, endereco.isFavorito());
            stmt.setInt(15, endereco.getClienteId());
            stmt.setInt(16, endereco.getId());

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
        Endereco endereco = (Endereco) entidade;
        String sql = "UPDATE enderecos SET nome_endereco = ?, tipo_residencia = ?, tipo_logradouro = ?, logradouro = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, pais = ?, obs_endereco = ?, " +
                "endereco_entrega = ?, endereco_cobranca = ?, favorito = ? WHERE id_endereco = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getNomeEndereco());
            stmt.setString(2, endereco.getTipoResidencia());
            stmt.setString(3, endereco.getTipoLogradouro());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getNumero());
            stmt.setString(6, endereco.getBairro());
            stmt.setString(7, endereco.getCep());
            stmt.setString(8, endereco.getCidade());
            stmt.setString(9, endereco.getEstado());
            stmt.setString(10, endereco.getPais());
            stmt.setString(11, endereco.getObsEndereco());
            stmt.setBoolean(12, endereco.isEnderecoEntrega());
            stmt.setBoolean(13, endereco.isEnderecoCobranca());
            stmt.setBoolean(14, endereco.isFavorito());
            stmt.setInt(15, endereco.getId());


            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? "sucesso" : "erro";
        } catch (SQLException e) {
            return "erro";
        }
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM enderecos WHERE id_endereco = ?";
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
        List<DomainEntity> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM enderecos";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("nome_endereco"),
                        rs.getString("tipo_residencia"),
                        rs.getString("tipo_logradouro"),
                        rs.getString("logradouro"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("obs_endereco"),
                        rs.getBoolean("endereco_entrega"),
                        rs.getBoolean("endereco_cobranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    @Override
    public DomainEntity select(int id) {
        String sql = "SELECT * FROM enderecos WHERE id_endereco = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("nome_endereco"),
                        rs.getString("tipo_residencia"),
                        rs.getString("tipo_logradouro"),
                        rs.getString("logradouro"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("obs_endereco"),
                        rs.getBoolean("endereco_entrega"),
                        rs.getBoolean("endereco_cobranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Endereco> selectByClienteId(int clienteId) {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM enderecos WHERE cliente_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("nome_endereco"),
                        rs.getString("tipo_residencia"),
                        rs.getString("tipo_logradouro"),
                        rs.getString("logradouro"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("obs_endereco"),
                        rs.getBoolean("endereco_entrega"),
                        rs.getBoolean("endereco_cobranca"),
                        rs.getBoolean("favorito"),
                        rs.getInt("cliente_id")
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

}
