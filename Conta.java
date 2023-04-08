// Estou importando a biblioteca para entrada e saida de dados
import java.util.Scanner;  // import eh uma diretiva de compilacao

// Estou implementando a classe pai chamada "Conta"
public class Contas {
    private       // Encapsulamento do atributo "Valor" com visibilidade apenas nessa classe
	float Valor; // Atributo responsavel por controlar o saldo da conta
    protected     // Encapsulamento dos atributos "Nome" e "Conta" com visibilidade 
       String Nome; // apenas nessa classe e em todas as classes filhas (subclasses)
       String Conta;// da classe conta 
	
    public int ExibirMenu() {   // Implementacao do metodo ExibirMenu() 
      // Na linha abaixo declaro o objeto "ler" da classe Scanner
      Scanner ler = new Scanner(System.in);
      System.out.println(" \n\n\n");
      System.out.println(" ******* Conta Bancaria ********");
      System.out.println(" 1) Consultar o Saldo");
      System.out.println(" 2) Depositar");
      System.out.println(" 3) Sacar");
      System.out.println(" 4) Sair");
      System.out.print(" Digite a opcao desejada: ");
      return ler.nextInt();
    }

    public void ConsultarSaldo() {  // Implementacao do metodo ConsultarSaldo() 
      System.out.println(" \n\n\n");
      System.out.println(" **** SALDO DA CONTA ****");
      System.out.println(" Nome do correntista: " + Nome);
      System.out.println(" Numero da conta: " + Conta);
      System.out.println(" Valor do Saldo: " + Valor);
      System.out.println(" ************************ \n\n\n");
    }

    public boolean Depositar(float Quanto) {
      if (Quanto <= 0) // Estou validando NOVAMENTE se valor informado e positivo
        return(false);   // A funcao retorna falso caso receba um valor invalido
      else {
        Valor = Valor + Quanto;  // Incrementei o valor do saldo
        return(true);            // Retornei verdadeiro para quem nos chamou
      }
    }

    public boolean Sacar(float QualValor) {
      if (QualValor <= 0) // Estou validando NOVAMENTE se valor informado e positivo
        return(false); // A funcao retorna falso caso receba um valor invalido
      else if (QualValor > Valor) {  // Validei se ha saldo suficiente na conta
  	System.out.println("\n Saldo insuficiente!");
  	return(false);
      }
      else {
        Valor = Valor - QualValor;  // Decrementei o valor do saldo
        return(true);            // Retornei verdadeiro para quem nos chamou
      }
    }

    public boolean AbrirConta(String Cliente, String numConta, float Quanto) {
      if (Quanto < 0) {
      	  System.out.println(" O saldo inicial da conta nao pode ser negativo!");
	  return false;
      }
      else {
          Nome = Cliente;
	  Valor = Quanto;
	  Conta = numConta;
	  return true;
      }
    }
  // Na linha a seguir estou criando o metodo principal da classe Contas  
  public static void main(String[] args) {
    // Na linha abaixo declarei o objeto "ler" da classe Scanner para entrada de dados
    Scanner ler = new Scanner(System.in);
    int resposta;
    float valor;
    System.out.print(" Informe o nome do correntista: ");
    String NomeCorrentista = ler.nextLine();
    System.out.print(" Informe o numero da conta: ");
    String NumeroConta = ler.nextLine();
    System.out.print(" Informe o valor de abertura: ");
    float vlrSaldo = ler.nextFloat();
    
    // Implementar o menu para a escolha do tipo da conta 1) Corrente; 2) Poupanca; 3) Salario;
      System.out.println(" ******* Escolha o Tipo da Conta ********");
      System.out.println(" 1) Corrente");
      System.out.println(" 2) Poupanca");
      System.out.println(" 3) Salario");
      System.out.println(" 4) Sair");
      System.out.print(" Digite a opcao desejada: ");
      resposta = ler.nextInt(); 
      
    // Na linha a seguir, estou instanciando o objeto minhaConta da classe Contas
    Contas minhaConta = null; 
    if (resposta == 1)
      minhaConta = new Contas();
    else if (resposta == 2)
      minhaConta = new Poupanca();
    else if (resposta == 3)
      minhaConta = new Salario();

    minhaConta.AbrirConta(NomeCorrentista, NumeroConta, vlrSaldo);
    do { 
      resposta = minhaConta.ExibirMenu();
      if (resposta == 1) {
        minhaConta.ConsultarSaldo();
      }  
      else if (resposta == 2) {
        System.out.printf(" Informe o valor a ser depositado: ");
        valor = ler.nextFloat();
        if (minhaConta.Depositar(valor))
          System.out.printf("\n Deposito de " + valor + " realizado com sucesso!");
        else
          System.out.printf("\n Erro ao fazer o deposito!");
      }  
      else if (resposta == 3) {
        System.out.printf(" Informe o valor do saque: ");
        valor = ler.nextFloat();
        if (minhaConta.Sacar(valor)) {
          System.out.printf("\n Saque de " + valor + " realizado com sucesso!");
          //Valor = 1000;
        }
        else
          System.out.println(" O Saque nao foi realizado!");
      }  
      else {
        System.out.println("\n\n Obrigado por usar o nosso banco!!! \n\n");
      }
    }  // Fechei o comando de repeticao "do"
   while (resposta < 4);

  }  // Estou fechando o metodo principal
  
} // Estou fechando a classe Contas

// Na linha abaixo estou criando a subclasse Popanca a partir da super classe Contas
class Poupanca extends Contas {
  protected
    float vlrLimiteDia = 300;
    float vlrTotalSaqueDia = 0;
  
  protected boolean aindaPodeSacar(float vlrSaque) {
    return (vlrSaque + vlrTotalSaqueDia <= vlrLimiteDia);
  }
  
  // Na linha a seguir estou reescrevendo o metodo sacar (Polimorfismo)
  // Polimorfismo de sobreposicao capitulo 06, pagina 134
    public boolean Sacar(float Quanto) {
      if (aindaPodeSacar(Quanto)) {
        super.Sacar(Quanto);
        vlrTotalSaqueDia = vlrTotalSaqueDia + Quanto;
        return true;
      }
      else {
        System.out.println(" Limite de saque por dia excedido!");
        return false;
      }
    }
}
  // 
  // Tarefa para casa: 
  // Implementar a classe conta salario com as seguinmtes caracteristicas:
  // Na conta salario somente um deposito por mes deve ser permitido, que eh o deposito do salario

  // Na linha abaixo estou criando a subclasse Salario a partir da classe Poupanca
class Salario extends Poupanca {
  private 
    // Atributo para controlar quantos depositos podem ser realizados no mes
    int limiteDepositosMes = 1; 
    // Atributo para controlar quantos depositos ja foram realizados no mes
    int totalDepositosRealizados = 0;
  
  protected boolean aindaPodeDepositar() {
    return (totalDepositosRealizados < limiteDepositosMes);
  }
  
  // Na linha a seguir estou reescrevendo o metodo Depositar (Polimorfismo)
  // Polimorfismo de sobreposicao capitulo 06, pagina 134
    public boolean Depositar(float Quanto) {
      if (aindaPodeDepositar()) {
        super.Depositar(Quanto);
        totalDepositosRealizados+=1; // Poderia ser totalDepositosRealizados++
        return true;
      }
      else {
        System.out.println(" Limite de Deposito por mes excedido!");
        return false;
      }
    }

}

;