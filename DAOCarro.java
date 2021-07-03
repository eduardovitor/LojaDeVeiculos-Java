/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadeveiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DAOCarro {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pstatement=null;
public boolean inserir(Carro carro){
    int linhasafetadas=0;
   try{
       con=MyConnection.getConnection();
       pstatement = con.prepareStatement("insert into carro(renavam,placa,cor,preco,idcarro,idpessoa,horario) values (?,?,?,?,?,?,?)");
       pstatement.setString(1, carro.getRenavam());
       pstatement.setString(2, carro.getPlaca());
       pstatement.setString(3, carro.getCor());
       pstatement.setDouble(4, carro.getPreco());
       pstatement.setInt(5, carro.getIdcarro());
       pstatement.setInt(6, carro.getProprietario().getId());
       pstatement.setString(7, carro.getHorario());
       linhasafetadas=pstatement.executeUpdate();
       pstatement.close();
   }catch(SQLException e){
       e.printStackTrace();
   }
   return linhasafetadas>0;
}
public ArrayList<Carro> getTodosOsCarros(){
       ArrayList<Carro> carros=new ArrayList<>();
       try{
           con=MyConnection.getConnection();
           String comandosql="Select *from carro";
           pstatement=con.prepareStatement(comandosql);
           rs=pstatement.executeQuery();
           ImplementaDAOPessoa daopessoa=new ImplementaDAOPessoa();
           while(rs.next()){
           Carro carro=new Carro();
           carro.setCor(rs.getString("cor"));
           carro.setPlaca(rs.getString("placa"));
           carro.setRenavam(rs.getString("renavam"));
           carro.setPreco(rs.getFloat("preco"));
           carro.setIdcarro(rs.getInt("idcarro"));
           carro.setProprietario(daopessoa.selectPessoaporID(rs.getInt("idpessoa")));
           carro.setHorario(rs.getString("horario"));          
           carros.add(carro);                                  
           }
           con.close();
           rs.close();
           st.close();
       } catch(SQLException e){
           e.printStackTrace();;
       }
       return carros;
}
public boolean deletarcarroporID(Integer id){
    int linhasafetadas;
    boolean resultado=false;
    try{
        con=MyConnection.getConnection();
        String comandosql="DELETE from carro where idcarro=? ";
        pstatement=con.prepareStatement(comandosql);
        pstatement.setInt(1, id);
        linhasafetadas=pstatement.executeUpdate();
        resultado=linhasafetadas>0;
        pstatement.close();
        con.close();
} catch(SQLException e){
    e.printStackTrace();
}
    return resultado;
}
}