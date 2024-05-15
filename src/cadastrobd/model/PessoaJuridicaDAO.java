/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PessoaJuridicaDAO {
    
    ConectorBD cb = new ConectorBD();
    SequenceManager sequenceManager = new SequenceManager();
    
    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoa = null;
        try {
            ResultSet rs = cb.getSelect("SELECT * FROM pessoa INNER JOIN pessoaJuridica pj on pj.idPessoa = pessoa.idPessoa where pj.idPessoa = " + id);
            while (rs.next()) {
                pessoa = new PessoaJuridica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                );
            }
            ConectorBD.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        try {
            ResultSet rs = cb.getSelect("SELECT * FROM pessoa INNER JOIN pessoaJuridica pj on pj.idPessoa = pessoa.idPessoa");
            while (rs.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                );
                pessoas.add(pessoa);
            }
            ConectorBD.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa){
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
            
            PreparedStatement pstmtPessoaJuridica = cb.getPrepared("INSERT INTO PessoaJuridica (idPessoa, cnpj) VALUES (?, ?)");
            pstmtPessoaJuridica.setInt(1, pessoa.id);
            pstmtPessoaJuridica.setString(2, pessoa.getCnpj());
            pstmtPessoaJuridica.executeUpdate();
            ConectorBD.close(pstmtPessoaJuridica);
            
            ConectorBD.close(cb.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoa) {
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
            
            PreparedStatement pstmtPessoaJuridica = cb.getPrepared("UPDATE PessoaJuridica set cnpj = (?) WHERE idPessoa = (?)");
            pstmtPessoaJuridica.setString(1, pessoa.getCnpj());
            pstmtPessoaJuridica.setInt(2, pessoa.id);
            
            pstmtPessoaJuridica.executeUpdate();
            
            ConectorBD.close(pstmtPessoaJuridica);
            ConectorBD.close(cb.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(int id) {
        try{
            PreparedStatement pstmtPessoaJuridica = cb.getPrepared("DELETE FROM pessoaJuridica where idPessoa = (?)");
            pstmtPessoaJuridica.setInt(1, id);
            pstmtPessoaJuridica.executeUpdate();
            ConectorBD.close(pstmtPessoaJuridica);
            
            PreparedStatement pstmtPessoa = cb.getPrepared("DELETE FROM pessoa where idPessoa = (?)");
            pstmtPessoa.setInt(1, id);
            pstmtPessoa.executeUpdate();
            ConectorBD.close(pstmtPessoa);
            
            ConectorBD.close(cb.getConnection());
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
