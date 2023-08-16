package conta.repository;

import conta.model.Conta;

public interface ContaRepository {

  // CRUD da Conta
   void procurarPorNumero(int numero);
   void listarTodas();
   void cadastrar(Conta conta);
   void atualizar(Conta conta);

  void deletar(int numero);

  // Métodos Bancários
  void sacar(int numero, float valor);

  void depositar(int numero, float valor);

  void transferir(int numeroOrigem, int numeroDestino, float valor);

}