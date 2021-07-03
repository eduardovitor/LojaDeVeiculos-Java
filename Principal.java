
package lojadeveiculo;
public class Principal {
    public static void main(String[] args) {
      DAOCarro daocarro=new DAOCarro();
      ImplementaDAOPessoa daopessoa=new ImplementaDAOPessoa();
      Carro carros[]=new Carro[5];
      Pessoa pessoas[]=new Pessoa[5];
      for(int i=0;i<5;i++){
          carros[i]=new Carro();
          pessoas[i]=new Pessoa();
      }
      pessoas[0].setNome("Julia");
      pessoas[0].setSenha("Julinhailove456");
      pessoas[0].setCpf("12345672456");
      pessoas[0].setId(1);
      pessoas[0].setUsuario("julia");
      pessoas[0].setIdade(19);
      carros[0].setCor("azul");
      carros[0].setHorario("13:30");
      carros[0].setIdcarro(2);
      carros[0].setPlaca("ED123IOL98");
      carros[0].setPreco(32.555);
      carros[0].setRenavam("12334253");
      carros[0].setProprietario(pessoas[0]);
      daopessoa.inserir(pessoas[0]);
      daocarro.inserir(carros[0]);
    }
}