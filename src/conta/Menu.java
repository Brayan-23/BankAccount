package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaPoupanca;
import conta.model.ContaCorrente;
import conta.util.Cores;

public class Menu {

  public static Scanner leia = new Scanner(System.in);
  private static final ContaController controller = new ContaController();
  public static void main(String[] args) {

    int opcao, numero, agencia, tipo, aniversario;
    String titular;
    float saldo, limite;

    ContaCorrente cc1 = new ContaCorrente(controller.gerarNumero(), 123, 1, "João da Silva", 100f, 100.0f);
    controller.cadastrar(cc1);
    ContaCorrente cc2 = new ContaCorrente(controller.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
    controller.cadastrar(cc2);
    ContaPoupanca cc3 = new ContaPoupanca(controller.gerarNumero(), 125, 2, "Mariane dos Santos", 4000f, 12);
    controller.cadastrar(cc3);
    ContaPoupanca cc4 = new ContaPoupanca(controller.gerarNumero(), 126, 2, "Juliane Santos", 8000f, 15);
    controller.cadastrar(cc4);

    controller.listarTodas();

    while (true) {

      System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
          + "*****************************************************");
      System.out.println("                                                     ");
      System.out.println("                BANCO DO BRAZIL COM Z                ");
      System.out.println("                                                     ");
      System.out.println("*****************************************************");
      System.out.println("                                                     ");
      System.out.println("            1 - Criar Conta                          ");
      System.out.println("            2 - Listar todas as Contas               ");
      System.out.println("            3 - Buscar Conta por Numero              ");
      System.out.println("            4 - Atualizar Dados da Conta             ");
      System.out.println("            5 - Apagar Conta                         ");
      System.out.println("            6 - Sacar                                ");
      System.out.println("            7 - Depositar                            ");
      System.out.println("            8 - Transferir valores entre Contas      ");
      System.out.println("            9 - Sair                                 ");
      System.out.println("                                                     ");
      System.out.println("*****************************************************");
      System.out.println("Entre com a opção desejada:                          ");
      System.out.println("                                                     " + Cores.TEXT_RESET);

      try {
        opcao = leia.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("\nDigite valores inteiros!");
        leia.nextLine();
        opcao = 0;
      }

      if (opcao == 9) {
        System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
        leia.close();
        System.exit(0);
      }

      switch (opcao) {
        case 1 -> {
          System.out.println("\n Criar Conta");

          System.out.println("Digite sua Agência: ");
          agencia = leia.nextInt();
          System.out.println("Digite o nome do Titular: ");
          leia.skip("\\R?");
          titular = leia.nextLine();

          do {
            System.out.println("Digite o tipo de conta (1-CC ou 2-CP):");
            tipo = leia.nextInt();
          }while (tipo < 1 && tipo > 2);

          System.out.println("Digite o saldo da conta (R$:");
          saldo = leia.nextFloat();

          switch (tipo){
            case 1 -> {
              System.out.println("Digite o limite de Crédito (R$): ");
              limite = leia.nextFloat();
              controller.cadastrar(new ContaCorrente(controller.gerarNumero(), agencia, tipo, titular, saldo, limite));
            } case 2 -> {
              System.out.println("Digite o dia do Aniversario da Conta (R$): ");
              aniversario = leia.nextInt();
              controller.cadastrar(new ContaPoupanca(controller.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
            }
          }
          keyPress();
        }
        case 2 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Listar todas as Contas");
          controller.listarTodas();
          keyPress();
        }
        case 3 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Buscar Conta por número");
          System.out.println("Digite o número da Conta:");
          numero = leia.nextInt();

          controller.procurarPorNumero(numero);
          keyPress();
        }
        case 4 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Atualizar dados da Conta");

          System.out.println("Digite o número da Conta:");
          numero = leia.nextInt();

          if (controller.buscarNaCollection(numero) != null) {
            System.out.println("Digite sua Agência: ");
            agencia = leia.nextInt();
            System.out.println("Digite o nome do Titular:");
            leia.skip("\\R?");
            titular = leia.nextLine();

            System.out.println("Digite o saldo da conta (R$:");
            saldo = leia.nextFloat();

            tipo = controller.retornarTipo(numero);

            switch (tipo) {
              case 1 -> {
                System.out.println("Digite o limite de Crédito (R$): ");
                limite = leia.nextFloat();
                controller.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
              }
              case 2 -> {
                System.out.println("Digite o dia do Aniversario da Conta (R$): ");
                aniversario = leia.nextInt();
                controller.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
              }
              default -> System.out.println("Tipo de conta inválido!");
            }
          } else System.out.println("\nConta não encontrada!");
          keyPress();
        }
        case 5 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Apagar Conta");

          System.out.println("Digite o número da Conta:");
          numero = leia.nextInt();

          controller.deletar(numero);
          keyPress();
        }
        case 6 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Sacar");
          keyPress();
        }
        case 7 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Depositar");
          keyPress();
        }
        case 8 -> {
          System.out.println(Cores.TEXT_WHITE + "\n Transferir");
          keyPress();
        }
        default -> {
          System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);
          keyPress();
        }
      }
    }
  }

  public static void keyPress() {

    try {

      System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
      System.in.read();

    } catch (IOException e) {

      System.out.println("Você pressionou uma tecla diferente de enter!");

    }
  }
}