package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {
	public void create(Produto produto) throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into produto(nome, descricao, preco, quantidade) values (?, ?, ?, ?)");
		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());

		statement.execute();

		connection.close();

	}

	public void update(Produto produto) throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// comando para atualizar
		PreparedStatement statement = connection
				.prepareStatement("update produto set nome=?, descricao=?, preco=?, quantidade=? where id=?");
		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());
		statement.setInt(5, produto.getId());

		statement.execute();

		connection.close();

	}

	public void delete(Integer id) throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from produto where id=?");
		statement.setInt(1, id);
		statement.execute();

		connection.close();

	}

	public List<Produto> findAll() throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from produto order by id");

		// executando e capturando uma consulta.
		ResultSet resultSet = statement.executeQuery();

		// declarando lista de produtos
		List<Produto> lista = new ArrayList<Produto>();

		// percorrer consulta
		while (resultSet.next()) {

			Produto produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));

			// adicionado produtos na lista
			lista.add(produto);

		}

		connection.close();

		return lista;

	}

	public Produto findById(Integer id) throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from produto where id=?");

		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		Produto produto = null;

		if (resultSet.next()) {

			produto = new Produto();

			produto.setId(resultSet.getInt("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));

		}

		connection.close();

		return produto;
	}

}
