/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadeveiculo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neto
 */
public class ImplementaDAOPessoa  {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pstatement=null;

    public ArrayList<Pessoa> getAll() {
        ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

        try {

            con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * from pessoa");

            while (rs.next()) {
                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setNome(rs.getString("nome"));
                novaPessoa.setIdade(rs.getInt("idade"));
                novaPessoa.setCpf(rs.getString("cpf"));
                novaPessoa.setSenha(rs.getString("senha"));
                novaPessoa.setUsuario(rs.getString("usuario"));
                novaPessoa.setId(rs.getInt("id"));
                listaDePessoas.add(novaPessoa);
            }

            con.close();
            st.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            rs = null;
            st = null;
            con = null;
        }

        return listaDePessoas;
    }

    public Pessoa getPessoaByCPF(String cpf) {
        Pessoa pessoaPorCpf = new Pessoa();

        try {
            con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * from pessoa where cpf = '" + cpf + "'");

            if (rs.next()) {

                pessoaPorCpf.setNome(rs.getString("nome"));
                pessoaPorCpf.setIdade(rs.getInt("idade"));
                pessoaPorCpf.setCpf(rs.getString("cpf"));
                pessoaPorCpf.setSenha(rs.getString("senha"));
                pessoaPorCpf.setUsuario(rs.getString("usuario"));
                pessoaPorCpf.setId(rs.getInt("id"));
            }

            con.close();
            st.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pessoaPorCpf;
    }

    public boolean inserir(Pessoa pessoa) {
        boolean resultado=false;
     try{
         con =MyConnection.getConnection();
         st = con.createStatement();
         int linhas=0;
         linhas=st.executeUpdate("INSERT INTO pessoa(nome,cpf,idade,usuario,senha,id) values('"+pessoa.getNome()+"','"+pessoa.getCpf()+"',"+pessoa.getIdade()+",'"+pessoa.getUsuario()+"','"+pessoa.getSenha()+"',"+pessoa.getId()+")");
         resultado = linhas>0;
         con.close();
         st.close();
     } catch(SQLException ex){
         ex.printStackTrace();
     }
     return resultado;
    }

    public Pessoa getPessoaByNome(String nome){
        Pessoa pessoaPorNome = new Pessoa();
        
        try{
            con = MyConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * from pessoa where nome = '" + nome + "'");
            if(rs.next()){
                pessoaPorNome.setNome(rs.getString("nome"));
                pessoaPorNome.setCpf(rs.getString("cpf"));
                pessoaPorNome.setIdade(Integer.parseInt(rs.getString("idade")));
                pessoaPorNome.setUsuario(rs.getString("usuario"));
                pessoaPorNome.setSenha(rs.getString("senha"));
                pessoaPorNome.setId(Integer.parseInt(rs.getString("id")));
                
            }
            con.close();
            st.close();
            rs.close();
        } catch(SQLException exc){
            
        }
        return pessoaPorNome;
    }
    
    public boolean atualizaDadosPessoaPorCPF(Pessoa pessoa, String cpf){              // Implementar com o update
                boolean resultado=false;
        try{
            con = MyConnection.getConnection();
            st = con.createStatement();
            int linhas=st.executeUpdate("Update pessoa set nome='"+pessoa.getNome()+",cpf='"+pessoa.getCpf()+"',idade='"+pessoa.getIdade()+"',usuario='"+pessoa.getUsuario()+"',senha='"+pessoa.getSenha()+"',id='"+pessoa.getId()+"' where cpf = '"+cpf+"'");
            resultado=linhas>0;
            con.close();
            st.close();
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        return resultado;
        
    }
    
    public boolean apagarPessoaPorCPF(String cpf){   //Implementar com o Delete
        boolean resultado=false;
        try{
            con = MyConnection.getConnection();
            st = con.createStatement();
            int linhas=st.executeUpdate("DELETE from pessoa where cpf = '"+cpf+"'");
            con.close();
            st.close();
            resultado=linhas>0;
        }catch(SQLException exc){
            exc.printStackTrace();
        }
        return resultado;
    }
    public Pessoa selectPessoaporID(Integer id){
        Pessoa pessoa=new Pessoa();
       try{
           con=MyConnection.getConnection();
           pstatement=con.prepareStatement("select *from pessoa where id=?");
           pstatement.setInt(1, id);
           rs=pstatement.executeQuery();
           if(rs.next()){
               pessoa.setCpf(rs.getString("cpf"));
               pessoa.setId(rs.getInt("id"));
               pessoa.setNome(rs.getString("nome"));
               pessoa.setSenha(rs.getString("senha"));
               pessoa.setUsuario(rs.getString("usuario"));
               pessoa.setIdade(rs.getInt("idade"));
           }
           
    } catch(SQLException e){
        e.printStackTrace();
    }
       return pessoa;
}
}