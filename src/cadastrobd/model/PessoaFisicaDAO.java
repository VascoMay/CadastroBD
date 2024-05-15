package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaFisicaDAO {
    
    ConectorBD cb = new ConectorBD();
    SequenceManager sequenceManager = new SequenceManager();
            
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoa = null;
        try {
            ResultSet rs = cb.getSelect("SELECT * FROM pessoa INNER JOIN pessoaFisica pf on pf.idPessoa = pessoa.idPessoa where pf.idPessoa = " + id);
            while (rs.next()) {
                pessoa = new PessoaFisica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
            }
            ConectorBD.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        try {
            ResultSet rs = cb.getSelect("SELECT * FROM pessoa INNER JOIN pessoaFisica pf on pf.idPessoa = pessoa.idPessoa");
            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
                pessoas.add(pessoa);
            }
            ConectorBD.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa){
        try {
            PreparedStatement pstmtPessoa = cb.getPrepared("INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            pessoa.id = sequenceManager.getValue("seq_idPessoa");
            
            pstmtPessoa.setInt(1, pessoa.id); 
            pstmtPessoa.setString(2, pessoa.nome);
            pstmtPessoa.setString(3, pessoa.logradouro);
            pstmtPessoa.setString(4, pessoa.cidade);
            pstmtPessoa.setString(5, pessoa.estado);
            pstmtPessoa.setString(6, pessoa.telefone);
            pstmtPessoa.setString(7, pessoa.email);
            pstmtPessoa.executeUpdate();
            ConectorBD.close(pstmtPessoa);
            
            PreparedStatement pstmtPessoaFisica = cb.getPrepared("INSERT INTO pessoaFisica (idPessoa, cpf) VALUES (?, ?)");
            pstmtPessoaFisica.setInt(1, pessoa.id);
            pstmtPessoaFisica.setString(2, pessoa.getCpf());
            pstmtPessoaFisica.executeUpdate();
            ConectorBD.close(pstmtPessoaFisica);
            
            ConectorBD.close(cb.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoa) {
       try {
            PreparedStatement pstmtPessoa = cb.getPrepared("UPDATE Pessoa set nome = (?), logradouro = (?), cidade = (?), estado = (?), telefone = (?), email = (?) WHERE idPessoa = (?)");
            pstmtPessoa.setString(1, pessoa.nome);
            pstmtPessoa.setString(2, pessoa.logradouro);
            pstmtPessoa.setString(3, pessoa.cidade);
            pstmtPessoa.setString(4, pessoa.estado);
            pstmtPessoa.setString(5, pessoa.telefone);
            pstmtPessoa.setString(6, pessoa.email);
            pstmtPessoa.setInt(7, pessoa.id); 
            
            pstmtPessoa.executeUpdate();
            ConectorBD.close(pstmtPessoa);
            
            PreparedStatement pstmtPessoaFisica = cb.getPrepared("UPDATE pessoaFisica set cpf = (?) WHERE idPessoa = (?)");
            pstmtPessoaFisica.setString(1, pessoa.getCpf());
            pstmtPessoaFisica.setInt(2, pessoa.id);
            
            pstmtPessoaFisica.executeUpdate();
            
            ConectorBD.close(pstmtPessoaFisica);
            ConectorBD.close(cb.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(int id) {
        try{
            PreparedStatement pstmtPessoaFisica = cb.getPrepared("DELETE FROM pessoaFisica where idPessoa = (?)");
            pstmtPessoaFisica.setInt(1, id);
            pstmtPessoaFisica.executeUpdate();
            ConectorBD.close(pstmtPessoaFisica);
            
            PreparedStatement pstmtPessoa = cb.getPrepared("DELETE FROM pessoa where idPessoa = (?)");
            pstmtPessoa.setInt(1, id);
            pstmtPessoa.executeUpdate();
            ConectorBD.close(pstmtPessoaFisica);
            
            ConectorBD.close(cb.getConnection());
            
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
