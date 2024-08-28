package fatec.les.ecommerce.dao;

import fatec.les.ecommerce.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IDAO {
    private static final ClienteDAO instance = new ClienteDAO();

    private ClienteDAO() {
        // Construtor privado para evitar instanciamento
    }

    public static ClienteDAO getInstance() {return instance;}

    @Override
    public int insert(DomainEntity entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "INSERT INTO clientes (genero, nome, data_nascimento, cpf, telefone_tipo, telefone_numero, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getGenero());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getDataNascimento());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefoneTipo());
            stmt.setString(6, cliente.getTelefoneNumero());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getSenha());

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
        Cliente cliente = (Cliente) entidade;
        String sql = "UPDATE clientes SET genero = ?, nome = ?, data_nascimento = ?, cpf = ?, telefone_tipo = ?, telefone_numero = ?, email = ?, senha = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getGenero());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getDataNascimento());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefoneTipo());
            stmt.setString(6, cliente.getTelefoneNumero());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getSenha());
            stmt.setInt(9, cliente.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? "sucesso" : "erro";
        } catch (SQLException e) {
            return "erro";
        }
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
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
        List<DomainEntity> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int clienteId = rs.getInt("id_cliente");

                // Buscar enderecos
                List<Endereco> enderecos = EnderecoDAO.getInstance().selectByClienteId(clienteId);

                // Buscar cartoesCredito
                List<CartaoCredito> cartoesCredito = CartaoCreditoDAO.getInstance().selectByClienteId(clienteId);

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("genero"),
                        rs.getString("nome"),
                        rs.getString("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("telefone_tipo"),
                        rs.getString("telefone_numero"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        enderecos,
                        cartoesCredito
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public DomainEntity select(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id");

                // Buscar enderecos
                List<Endereco> enderecos = EnderecoDAO.getInstance().selectByClienteId(clienteId);

                // Buscar cartoesCredito
                List<CartaoCredito> cartoesCredito = CartaoCreditoDAO.getInstance().selectByClienteId(clienteId);

                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("genero"),
                        rs.getString("nome"),
                        rs.getString("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("telefone_tipo"),
                        rs.getString("telefone_numero"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        enderecos,
                        cartoesCredito
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
